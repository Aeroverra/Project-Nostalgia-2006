package server.game.npcs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import server.Config;
import server.Server;
import server.clip.region.Region;
import server.content.GoblinVillage;
import server.content.music.Sound;
import server.content.quests.misc.QuestHandling;
import server.content.quests.misc.Tutorialisland;
import server.content.randoms.Genie;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.npcs.drops.NPCDropsHandler;
import server.game.players.Client;
import server.game.players.PlayerHandler;
import server.world.TileControl;
import core.util.Misc;

public class NPCHandler {

	public static int maxNPCs = 4000;
	public static int maxListedNPCs = 4000;
	public static NPC npcs[] = new NPC[maxNPCs];
	public static NPCList NpcList[] = new NPCList[maxListedNPCs];

	public NPCHandler() {
		for(@SuppressWarnings("unused") NPC i : npcs) {
			i = null;
		}
		for(@SuppressWarnings("unused") NPCList i : NpcList) {
			i = null;
		}
		loadNPCList("./Data/cfg/npc.cfg");
		loadAutoSpawn("./Data/cfg/spawn-config.cfg");
		loadNPCSounds("./Data/cfg/npcsounds.cfg");
	}
public static boolean pathBlocked(NPC attacker, Client victim) {
		
		double offsetX = Math.abs(attacker.absX - victim.absX);
		double offsetY = Math.abs(attacker.absY - victim.absY);
		
		int distance = TileControl.calculateDistance(attacker, victim);
		
		if (distance == 0) {
			return true;
		}
		
		offsetX = offsetX > 0 ? offsetX / distance : 0;
		offsetY = offsetY > 0 ? offsetY / distance : 0;

		int[][] path = new int[distance][5];
		
		int curX = attacker.absX;
		int curY = attacker.absY;
		int next = 0;
		int nextMoveX = 0;
		int nextMoveY = 0;
		
		double currentTileXCount = 0.0;
		double currentTileYCount = 0.0;

		while(distance > 0) {
			distance--;
			nextMoveX = 0;
			nextMoveY = 0;
			if (curX > victim.absX) {
				currentTileXCount += offsetX;
				if (currentTileXCount >= 1.0) {
					nextMoveX--;
					curX--;	
					currentTileXCount -= offsetX;
				}		
			} else if (curX < victim.absX) {
				currentTileXCount += offsetX;
				if (currentTileXCount >= 1.0) {
					nextMoveX++;
					curX++;
					currentTileXCount -= offsetX;
				}
			}
			if (curY > victim.absY) {
				currentTileYCount += offsetY;
				if (currentTileYCount >= 1.0) {
					nextMoveY--;
					curY--;	
					currentTileYCount -= offsetY;
				}	
			} else if (curY < victim.absY) {
				currentTileYCount += offsetY;
				if (currentTileYCount >= 1.0) {
					nextMoveY++;
					curY++;
					currentTileYCount -= offsetY;
				}
			}
			path[next][0] = curX;
			path[next][1] = curY;
			path[next][2] = attacker.heightLevel;//getHeightLevel();
			path[next][3] = nextMoveX;
			path[next][4] = nextMoveY;
			next++;	
		}
		for (int i = 0; i < path.length; i++) {
			if (!Region./*getSingleton().*/getClipping(path[i][0], path[i][1], path[i][2], path[i][3], path[i][4])  && !Region.blockedShot(path[i][0], path[i][1], path[i][2])) {
				return true;	
			}
		}
		return false;
	}

	public void multiAttackGfx(int i, int gfx) {
		if (npcs[i].projectileId < 0)
			return;
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client)PlayerHandler.players[j];
				if (c.heightLevel != npcs[i].heightLevel)
					continue;
				if (PlayerHandler.players[j].goodDistance(c.absX, c.absY, npcs[i].absX, npcs[i].absY, 15)) {
					int nX = NPCHandler.npcs[i].getX() + offset(i);
					int nY = NPCHandler.npcs[i].getY() + offset(i);
					int pX = c.getX();
					int pY = c.getY();
					int offX = (nY - pY)* -1;
					int offY = (nX - pX)* -1;
					c.getPA().createPlayersProjectile(nX, nY, offX, offY, 50, getProjectileSpeed(i), npcs[i].projectileId, 43, 31, -c.getId() - 1, 65);
				}
			}
		}
	}

	public boolean switchesAttackers(int i) {
		switch(npcs[i].npcType) {
		case 2551:
		case 2552:
		case 2553:
		case 2559:
		case 2560:
		case 2561:
		case 2563:
		case 2564:
		case 2565:
		case 2892:
		case 2894:
			return true;

		}

		return false;
	}

	public void multiAttackDamage(int i) {
		int max = getMaxHit(i);
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client)PlayerHandler.players[j];
				if (c.isDead || c.heightLevel != npcs[i].heightLevel)
					continue;
				if (PlayerHandler.players[j].goodDistance(c.absX, c.absY, npcs[i].absX, npcs[i].absY, 15)) {
					if (npcs[i].attackType == 2) {
						if (!c.prayerActive[16]) {
							if (Misc.random(500) + 200 > Misc.random(c.getCombat().mageDef())) {
								int dam = Misc.random(max);
								c.dealDamage(dam);
								c.handleHitMask(dam);
							} else {
								c.dealDamage(0);
								c.handleHitMask(0);
							}
						} else {
							c.dealDamage(0);
							c.handleHitMask(0);
						}
					} else if (npcs[i].attackType == 1) {
						if (!c.prayerActive[17]) {
							int dam = Misc.random(max);
							if (Misc.random(500) + 200 > Misc.random(c.getCombat().calculateRangeDefence())) {
								c.dealDamage(dam);
								c.handleHitMask(dam);
							} else {
								c.dealDamage(0);
								c.handleHitMask(0);
							}
						} else {
							c.dealDamage(0);
							c.handleHitMask(0);
						}
					}
					if (npcs[i].endGfx > 0) {
						c.gfx0(npcs[i].endGfx);
					}
				}
				c.getPA().refreshSkill(3);
			}
		}
	}

	public int getClosePlayer(int i) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (j == npcs[i].spawnedBy)
					return j;
				if (goodDistance(PlayerHandler.players[j].absX, PlayerHandler.players[j].absY, npcs[i].absX, npcs[i].absY, 2 + distanceRequired(i) + followDistance(i))) {
					if ((PlayerHandler.players[j].underAttackBy <= 0 && PlayerHandler.players[j].underAttackBy2 <= 0) || PlayerHandler.players[j].inMulti())
						if (PlayerHandler.players[j].heightLevel == npcs[i].heightLevel)
							return j;
				}
			}
		}
		return 0;
	}

	public int getCloseRandomPlayer(int i) {
		ArrayList<Integer> players = new ArrayList<Integer>();
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (goodDistance(PlayerHandler.players[j].absX, PlayerHandler.players[j].absY, npcs[i].absX, npcs[i].absY, 2 + distanceRequired(i) + followDistance(i))) {
					if ((PlayerHandler.players[j].underAttackBy <= 0 && PlayerHandler.players[j].underAttackBy2 <= 0) || PlayerHandler.players[j].inMulti())
						if (PlayerHandler.players[j].heightLevel == npcs[i].heightLevel)
							players.add(j);
				}
			}
		} 
		
		if (players.size() > 0)
			return players.get(Misc.random(players.size() -1));
		else
			return 0;
	}

	public int npcSize(int i) {
		switch (npcs[i].npcType) {
		case 2883:
		case 2882:
		case 2881:
			return 3;
		}
		return 0;
	}
	
	public int npcSizes(int i) {
	    return NPCSize.getNPCSize(npcs[i].npcType);
	}
	
	   public void removeNpc(NPC npc) {
			npc.setAbsX(0);
		    npc.setAbsY(0);
	        npcs[npc.npcId] = null;
	    }
	   public void removeNpc(int i) {
	        npcs[i] = null;
	    }


	/**
	 * Summon npc, barrows, etc
	 **/
	public void spawnNpc(final Client c, int npcType, int x, int y, int heightLevel, int WalkingType, int HP, int maxHit, int attack, int defence, boolean attackPlayer, boolean headIcon) {
		// first, search for a free slot
		int slot = -1;
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					for (int i = 1; i < maxNPCs; i++) {
						if(npcs[i] != null) {
					if(npcs[i].spawnedBy > 0) { // delete summons npc
						if(PlayerHandler.players[npcs[i].spawnedBy] == null
								|| PlayerHandler.players[npcs[i].spawnedBy].heightLevel != npcs[i].heightLevel
								|| PlayerHandler.players[npcs[i].spawnedBy].respawnTimer > 0
								|| !PlayerHandler.players[npcs[i].spawnedBy].goodDistance(npcs[i].getX(), npcs[i].getY(), PlayerHandler.players[npcs[i].spawnedBy].getX(), PlayerHandler.players[npcs[i].spawnedBy].getY(), 20)) {

							if(PlayerHandler.players[npcs[i].spawnedBy] != null) {
							npcs[i] = null;
							container.stop();
							}
						}
						}
						}
					}
				}
				@Override
				public void stop() {
				}
			}, 1);
			for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
			}
		
		if(slot == -1) {
			//Misc.println("No Free Slot");
			return;		// no free slot found
		}
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		newNPC.spawnedBy = c.getId();
		if(headIcon)
			c.getPA().drawHeadicon(1, slot, 0, 0);
		if(attackPlayer) {
			newNPC.underAttack = true;
			newNPC.killerId = c.playerId;
		}
		
		npcs[slot] = newNPC;
	}
	
 
	public static int timer = 0;
	/**
	 * Random events
	 **/
	public void spawnGenie(final Client c, int npcType, int x, int y, int heightLevel, int WalkingType, int HP, int maxHit, int attack, int defence, boolean attackPlayer, boolean headIcon, boolean followplayer) {
		// first, search for a free slot
		int slot = -1;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				for (int i = 1; i < maxNPCs; i++) {
					if(npcs[i] != null) {
				if(npcs[i].spawnedBy > 0) { // delete summons npc
					if(PlayerHandler.players[npcs[i].spawnedBy] == null
							|| PlayerHandler.players[npcs[i].spawnedBy].heightLevel != npcs[i].heightLevel
							|| PlayerHandler.players[npcs[i].spawnedBy].respawnTimer > 0
							|| !PlayerHandler.players[npcs[i].spawnedBy].goodDistance(npcs[i].getX(), npcs[i].getY(), PlayerHandler.players[npcs[i].spawnedBy].getX(), PlayerHandler.players[npcs[i].spawnedBy].getY(), 20)) {

						if(PlayerHandler.players[npcs[i].spawnedBy] != null) {
						npcs[i] = null;
						container.stop();
						}
					}
					}
					}
				}
			}
			@Override
			public void stop() {
			}
		}, 1);
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if(slot == -1) {
			//Misc.println("No Free Slot");
			return;		// no free slot found
		}
		final NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		newNPC.spawnedBy = c.getId();
		if(headIcon)
			c.getPA().drawHeadicon(1, slot, 0, 0);
		if(attackPlayer) {
			newNPC.underAttack = true;
			newNPC.killerId = c.playerId;
		}	
		if(followplayer) {
	newNPC.forceChat("Greetings, " + Misc.capitalize(c.playerName) + "!");
	CycleEventHandler.getSingleton().addEvent(c,
			new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
			       	if(!PlayerHandler.players[newNPC.spawnedBy].goodDistance(newNPC.getX(), newNPC.getY(), PlayerHandler.players[newNPC.spawnedBy].getX(), PlayerHandler.players[newNPC.spawnedBy].getY(), 20))
                	{
			       		removeNpc(newNPC); // removed NPC
                	container.stop();	
                	return;
                	}
					if(c.talkedto) {
                    		newNPC.forceChat("Enjoy your gift, " + c.playerName + ".");
                    		newNPC.animNumber = 863;
                    		newNPC.animUpdateRequired = true;
                    		c.pgotLamp = true;
                    	}
					followPlayer(newNPC.npcId, c.playerId);
                    	newNPC.turnNpc(c.getX(), c.getY());        	
                    	timer++;     
                    	if(timer == 1000 || c.pgotLamp) {
                    		c.getItems().addItem(Genie.lamp, 1);
                    		c.talkedto = false;
                    		container.stop();	
                    	}
                    	
                }

				@Override
				public void stop() {
					
				}
            }, 1);
			CycleEventHandler.getSingleton().addEvent(c,
					new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							if(timer == 1000 || c.pgotLamp) {
							removeNpc(newNPC); // removed NPC
                    		container.stop();
							}
						}

						@Override
						public void stop() {
                      timer = 0;
                      c.talkedto = false;
                      c.pgotLamp = false;
						}
					}, 7);
			CycleEventHandler.getSingleton().addEvent(c,
					new CycleEvent() { // Random messages
						@Override
						public void execute(CycleEventContainer container) {
							String[] genieRandomTalk = {
							"Please stop ignoring me, " + c.playerName + "!",
							"I'm here to grant you a wish, " + c.playerName + "!"	
									};
						   newNPC.forceChat(genieRandomTalk[Misc.random(1)]);				  
						   if(timer == 1000 || c.pgotLamp)
							   container.stop();
						}

						@Override
						public void stop() {
                      timer = 0;
						}
					}, 20);
		}
		npcs[slot] = newNPC;

	}
	


	/*/public void getDtLastKill(int i) {
		int dtNpcs[] = {
				1975, 1914, 1977, 1913
		};
		for(int dtNpc : dtNpcs) {
			if(npcs[i].npcType == dtNpc) {
				Client p = (Client) PlayerHandler.players[npcs[i].killedBy];
				if(p != null) {
					p.lastDtKill = dtNpc;
				}
			}
		}
	}

/*/


	public void spawnNpc2(int npcType, int x, int y, int heightLevel, int WalkingType, int HP, int maxHit, int attack, int defence) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if(slot == -1) {
			//Misc.println("No Free Slot");
			return;		// no free slot found
		}
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		npcs[slot] = newNPC;
	}



	/**
	 * Emotes
	 **/

	public static int getAttackEmote(int i) {
	    String npc = Server.npcHandler.getNpcListName(NPCHandler.npcs[i].npcType).toLowerCase();
	    if(npc.equalsIgnoreCase("goblin"))
		return 309;
	    switch(NPCHandler.npcs[i].npcType) {
		case 49:
			return 158;
		case 1618:
			return 1551;
		case 1612:
			return 1523;
		case 73:
			return 299;
		case 90:
			return 260;
		case 2607:
			return 2610;
		case 97:
		case 141:
		case 1558:
		case 96:
			return 75;
		case 1459:
			return 1402;
		case 72:
		case 396:
			if (npcs[i].attackType == 1)
				return 285;
			if (npcs[i].attackType == 0)
				return 284;
		case 181:
			if (npcs[i].attackType == 2)
				return 717;
			if (npcs[i].attackType == 0)
				return 422;
		case 172:
		case 174:
			if(npcs[i].attackType == 1)
				return 711;
		case 795:
			if (npcs[i].attackType == 1)
				return 1979;
			if (npcs[i].attackType == 0)
				return 422;
		case 115:
			return 359;
		case 3066:
			return 1658;
		case 13: //wizards
			return 711;

		case 103:
		case 655:
			return 123;

		case 1624:
			return 1557;

		case 1648:
			return 1590;

		case 2783: //dark beast
			return 2733;

		case 1615: //abby demon
			return 1537;

		case 1613: //nech
			return 1528;

		case 1610: case 1611: //garg
			return 1519;

		case 1616: //basilisk
			return 1546;

		case 50://drags
		case 53:
		case 54:
		case 55:
		case 941:
		case 1590:
		case 1591:
		case 1592:
			return 80;

		case 124: //earth warrior
			return 390;

		case 803: //monk
			return 422;

		case 52: //baby drag
			return 25;

		case 58: //Shadow Spider
		case 59: //Giant Spider
		case 60: //Giant Spider
		case 61: //Spider
		case 62: //Jungle Spider
		case 63: //Deadly Red Spider
		case 64: //Ice Spider
		case 134:
			return 143;

		case 105: //Bear
		case 106:  //Bear
			return 41;

		case 412:
		case 78:
			return 30;

		case 2033: //rat
			return 138;

		case 2031: // bloodworm
			return 2070;

		case 101: // goblin
			return 309;

		case 81: // cow
			return 0x03B;

		case 21: // hero
			return 451;

		case 41: // chicken
			return 55;

		case 9: // guard
		case 32: // guard
		case 20: // paladin
			return 451;

		case 1338: // dagannoth
		case 1340:
		case 1342:
			return 1341;

		case 19: // white knight
			return 406;

		case 110:
		case 111: // ice giant
		case 112:
		case 117:
			return 128;

		case 2452:
			return 1312;

		case 2889:
			return 2859;

		case 118:
		case 119:
			return 99;

		case 82://Lesser Demon
		case 83://Greater Demon
		case 84://Black Demon
		case 1472://jungle demon
			return 64;

		case 1267:
		case 1265:
			return 1312;

		case 125: // ice warrior
		case 178:
			return 451;

		case 1153: //Kalphite Worker
		case 1154: //Kalphite Soldier
		case 1155: //Kalphite guardian
		case 1156: //Kalphite worker
		case 1157: //Kalphite guardian
			return 1184;

		case 123:
		case 122:
			return 164;

		case 2028: // karil
			return 2075;

		case 2025: // ahrim
			return 729;

		case 2026: // dharok
			return 2067;

		case 2027: // guthan
			return 2080;

		case 2029: // torag
			return 0x814;

		case 2030: // verac
			return 2062;

		case 2881: //supreme
			return 2855;

		case 2882: //prime
			return 2854;

		case 2883: //rex
			return 2851;

		case 3200:
			return 3146;

		case 2745:
			if (npcs[i].attackType == 2)
				return 2656;
			else if (npcs[i].attackType == 1)
				return 2652;
			else if (npcs[i].attackType == 0)
				return 2655;


		default:
			return 0x326;
		}
	}


	public int getDeadEmote(int i) {
		switch(npcs[i].npcType) {
		case 49:
			return 161;
		case 73:
			return 302;
		case 90:
			return 263;
		case 96:
		case 97:
		case 141:
		case 1558:
			return 78;
		case 1459:
			return 1404;
		case 396:
		case 72:
			return 287;
		case 115:
			return 361;
		case 117:
			return 131;
		case 3066:
			return 1342;
		case 1612: //banshee
			return 1524;
		case 2558:
			return 3503;
		case 2559:
		case 2560:
		case 2561:
			return 6956;
		case 2607:
			return 2607;
		case 2627:
			return 2620;
		case 2630:
			return 2627;
		case 2631:
			return 2630;
		case 2738:
			return 2627;
		case 2741:
			return 2638;
		case 2746:
			return 2638;
		case 2743:
			return 2646;
		case 2745:
			return 2654;

		case 3777:
		case 3778:
		case 3779:
		case 3780:
			return -1;

		case 3200:
			return 3147;

		case 2035: //spider
			return 146;

		case 2033: //rat
			return 141;

		case 2031: // bloodvel
			return 2073;

		case 101: //goblin
			return 313;

		case 81: // cow
			return 0x03E;

		case 41: // chicken
			return 57;

		case 1338: // dagannoth
		case 1340:
		case 1342:
			return 1342;

		case 2881:
		case 2882:
		case 2883:
			return 2856;

		case 111: // ice giant
			return 131;

		case 125: // ice warrior
			return 843;

		case 751://Zombies!!
			return 302;

		case 1626:
		case 1627:
		case 1628:
		case 1629:
		case 1630:
		case 1631:
		case 1632: //turoth!
			return 1597;

		case 1616: //basilisk
			return 1548;

		case 1653: //hand
			return 1590;

		case 82://demons
		case 83:
		case 84:
			return 67;

		case 1605://abby spec
			return 1508;

		case 51://baby drags
		case 52:
		case 1589:
		case 3376:
			return 28;

		case 1610:
		case 1611:
			return 1518;

		case 1618:
		case 1619:
			return 1553;

		case 1620: case 1621:
			return 1563;

		case 2783:
			return 2732;

		case 1615:
			return 1538;

		case 1624:
			return 1558;

		case 1613:
			return 1530;

		case 1633: case 1634: case 1635: case 1636:
			return 1580;

		case 1648: case 1649: case 1650: case 1651: case 1652: case 1654: case 1655: case 1656: case 1657:
			return 1590;

		case 100: case 102:
			return 313;

		case 105:
		case 106:
			return 44;

		case 412:
		case 78:
			return 36;

		case 122:
		case 123:
			return 167;

		case 58: case 59: case 60: case 61: case 62: case 63: case 64: case 134:
			return 146;

		case 1153: case 1154: case 1155: case 1156: case 1157:
			return 1190;

		case 103: case 104:
			return 123;

		case 118: case 119:
			return 102;


		case 50://drags
		case 53:
		case 54:
		case 55:
		case 941:
		case 1590:
		case 1591:
		case 1592:
			return 92;


		default:
			return 2304;
		}
	}

	/**
	 * Attack delays
	 **/
	public static int getNpcDelay(int i) {
		switch(npcs[i].npcType) {
		case 2025:
		case 2028:
			return 7;

		case 2745:
			return 8;

		case 2558:
		case 2559:
		case 2560:
		case 2561:
		case 2550:
			return 6;
			//saradomin gw boss
		case 2562:
			return 2;

		default:
			return 5;
		}
	}

	/**
	 * Hit delays
	 **/
	public static int getHitDelay(int i) {
		switch(npcs[i].npcType) {
		case 2881:
		case 2882:
		case 3200:
		case 2892:
		case 2894:
			return 3;

		case 2743:
		case 2631:
		case 2558:
		case 2559:
		case 2560:
			return 3;

		case 2745:
			if (npcs[i].attackType == 1 || npcs[i].attackType == 2)
				return 5;
			else
				return 2;

		case 2025:
			return 4;
		case 2028:
			return 3;

		default:
			return 2;
		}
	}

	/**
	 * Npc respawn time
	 **/
	public int getRespawnTime(int i) {
		switch(npcs[i].npcType) {
		case 2881:
		case 2882:
		case 2883:
		case 2558:
		case 2559:
		case 2560:
		case 2561:
		case 2562:
		case 2563:
		case 2564:
		case 2550:
		case 2551:
		case 2552:
		case 2553:
			return 100;
		case 3777:
		case 3778:
		case 3779:
		case 3780:
			return 500;
		default:
			return 25;
		}
	}



	/* 	public String[] guardRandomTalk = {
	"We must not fail!",
	"Take down the portals",
	"The Void Knights will not fall!",
	"Hail the Void Knights!",
	"We are beating these scum!"
	}; */

	public void newNPC(int npcType, int x, int y, int heightLevel, int WalkingType, int HP, int maxHit, int attack, int defence) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found

		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		npcs[slot] = newNPC;
	}

	public void newNPCList(int npcType, String npcName, int combat, int HP) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found

		NPCList newNPCList = new NPCList(npcType);
		newNPCList.npcName = npcName;
		newNPCList.npcCombat = combat;
		newNPCList.npcHealth = HP;
		NpcList[slot] = newNPCList;
	}



	public void process() {
		for (NPC i : NPCHandler.npcs) {
			if (i == null) continue;
			i.clearUpdateFlags();

		}
		/*         if (npcs[i].npcType == 812){
				if (Misc.random(10) == 4)
				npcs[i].forceChat(guardRandomTalk[Misc.random3(guardRandomTalk.length)]);
			}  */
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] != null) {
				if (npcs[i].actionTimer > 0) {
					npcs[i].actionTimer--;
				}

				if (npcs[i].freezeTimer > 0) {
					npcs[i].freezeTimer--;
				}

				if (npcs[i].hitDelayTimer > 0) {
					npcs[i].hitDelayTimer--;
				}
				if (npcs[i].hitDelayTimer == 1) {
					npcs[i].hitDelayTimer = 0;
					if (npcs[i].underAttackByNpc && !npcs[i].underAttack && npcs[i].killerId <= 0)
						GoblinVillage.applyNpcDamage(i);
					else
						applyDamage(i);
				}

				if(npcs[i].attackTimer > 0) {
					npcs[i].attackTimer--;
				}

		
				if (npcs[i] == null) continue;

				/**
				 * Attacking player
				 **/			
				if (NPCAggressive.isAggressive(npcs[i].npcType) && !npcs[i].underAttack && !npcs[i].isDead && !switchesAttackers(i)) {
					Client player = (Client)PlayerHandler.players[getCloseRandomPlayer(i)];
					if (player != null && getNpcListCombat(npcs[i].npcType) * 2 > player.combatLevel) {
						npcs[i].killerId = getCloseRandomPlayer(i);
					}
				
				} else if (NPCAggressive.isAggressive(npcs[i].npcType) && !npcs[i].underAttack && !npcs[i].isDead && switchesAttackers(i)) {
					Client player = (Client)PlayerHandler.players[getCloseRandomPlayer(i)];
					if (player != null && getNpcListCombat(npcs[i].npcType) * 2 > player.combatLevel) {
						npcs[i].killerId = getCloseRandomPlayer(i);
					}
				}
				if (System.currentTimeMillis() - npcs[i].lastDamageTaken > 5000)
					npcs[i].underAttackBy = 0;

				if((npcs[i].killerId > 0 || npcs[i].underAttack)&& retaliates(npcs[i].npcType)) {
					if(!npcs[i].isDead) {
						int p = npcs[i].killerId;
						if(PlayerHandler.players[p] != null) {
							Client c = (Client) PlayerHandler.players[p];
							followPlayer(i, c.playerId);
							if (npcs[i] == null) continue;
							if(npcs[i].attackTimer == 0) {
								if(c != null) {
									attackPlayer(c, i);
									int otherX = npcs[i].getX();
									int otherY = npcs[i].getY();
									if (otherX == c.absX && otherY == c.absY && npcSizes(i) < 2) {
										stepAway(i);// On top
									}
								}
							}
						} else {
							npcs[i].killerId = 0;
							npcs[i].underAttack = false;
							npcs[i].facePlayer(0);
						}
					}
				}
				if (npcs[i] != null) {
					GoblinVillage.followNpc(i, npcs[i].attackingNpc);
			    	if (!npcs[i].underAttack && npcs[i].killerId <= 0) {
						if (!npcs[i].isDead) {
							if (npcs[i].underAttackByNpc) {
								if (npcs[i].attackTimer == 0 && npcs[i].npcType != 3783) {
							//	GoblinVillage.followNpc(i, npcs[i].attackingNpc);
									GoblinVillage.attackNpc(i, npcs[i].attackingNpc);
								}
							}
							}
						}
					}	
				if(!npcs[i].isDead) {
					Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
					if (c != null) {
						//c.getPA().sound(getNpcListDieSound(npcs[i].npcType));
						c.getPA().sound(Sound.getNPCSound(npcs[i].npcType, "Death"));
					}
				
				}
				if (npcs[i].isDead == true) {
					if(npcs[i].actionTimer == 3 && npcs[i].applyDead && !npcs[i].needRespawn) {
						Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
						if (c != null) {
						//  c.getPA().sound(getNpcListDieSound(npcs[i].npcType));
							c.getPA().sound(Sound.getNPCSound(npcs[i].npcType, "Death"));
						}
					}


					if (npcs[i].actionTimer == 0 && npcs[i].applyDead == false && npcs[i].needRespawn == false) {
						npcs[i].updateRequired = true;
						npcs[i].facePlayer(0);
						npcs[i].killedBy = getNpcKillerId(i);
						npcs[i].animNumber = getDeadEmote(i); // dead emote
						npcs[i].animUpdateRequired = true;
						npcs[i].freezeTimer = 0;
						npcs[i].applyDead = true;
						npcs[i].actionTimer = 4; // delete time
						resetPlayersInCombat(i);
					} else if (npcs[i].actionTimer == 0 && npcs[i].applyDead == true &&  npcs[i].needRespawn == false) {
						npcs[i].needRespawn = true;
						npcs[i].actionTimer = getRespawnTime(i); // respawn time
						dropItems(i); // npc drops items!
						Client player = (Client) PlayerHandler.players[npcs[i].killedBy];
						if(player != null) {
						if(player.tutorialprog == 24) {
							handleratdeath(i);
						}else 
						if(player.tutorialprog == 25 && player.ratdied2 == true) {
							handleratdeath2(i);
						}
						
						}
						npcs[i].absX = npcs[i].makeX;
						npcs[i].absY = npcs[i].makeY;
						npcs[i].HP = npcs[i].MaxHP;
						npcs[i].animNumber = 0x328;
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;
						if (npcs[i].npcType == 757)
							handleCountDray(i);
							
						if (npcs[i].npcType >= 2440 && npcs[i].npcType <= 2446) {
							Server.objectManager.removeObject(npcs[i].absX, npcs[i].absY);
						}
					} else if (npcs[i].actionTimer == 0 && npcs[i].needRespawn == true) {
						Client player = (Client) PlayerHandler.players[npcs[i].spawnedBy];
						if(player != null) {
							npcs[i] = null;
						} else {
							int old1 = npcs[i].npcType;
							int old2 = npcs[i].makeX;
							int old3 = npcs[i].makeY;
							int old4 = npcs[i].heightLevel;
							int old5 = npcs[i].walkingType;
							int old6 = npcs[i].MaxHP;
							int old7 = npcs[i].maxHit;
							int old8 = npcs[i].attack;
							int old9 = npcs[i].defence;

							npcs[i] = null;
							newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9);
				
						}
					}
				}
				
							/**
				 * Random walking and walking home
				 **/
				if (npcs[i] == null) continue;
				if((!npcs[i].underAttack || npcs[i].walkingHome) && npcs[i].randomWalk && !npcs[i].isDead) {
					npcs[i].facePlayer(0);
					npcs[i].killerId = 0;
					if(npcs[i].spawnedBy == 0) {
						if((npcs[i].absX > npcs[i].makeX + Config.NPC_RANDOM_WALK_DISTANCE) || (npcs[i].absX < npcs[i].makeX - Config.NPC_RANDOM_WALK_DISTANCE) || (npcs[i].absY > npcs[i].makeY + Config.NPC_RANDOM_WALK_DISTANCE) || (npcs[i].absY < npcs[i].makeY - Config.NPC_RANDOM_WALK_DISTANCE)) {
							npcs[i].walkingHome = true;
						}
					}

					if (npcs[i].walkingHome && npcs[i].absX == npcs[i].makeX && npcs[i].absY == npcs[i].makeY) {
						npcs[i].walkingHome = false;
					} else if(npcs[i].walkingHome) {
						npcs[i].moveX = GetMove(npcs[i].absX, npcs[i].makeX);
						npcs[i].moveY = GetMove(npcs[i].absY, npcs[i].makeY);
						npcs[i].getNextNPCMovement(i);
						npcs[i].updateRequired = true;
					}
					if(npcs[i].walkingType == 1) {
						if(Misc.random(3)== 1 && !npcs[i].walkingHome) {
							int MoveX = 0;
							int MoveY = 0;
							int Rnd = Misc.random(9);
							if (Rnd == 1) {
								MoveX = 1;
								MoveY = 1;
							} else if (Rnd == 2) {
								MoveX = -1;
							} else if (Rnd == 3) {
								MoveY = -1;
							} else if (Rnd == 4) {
								MoveX = 1;
							} else if (Rnd == 5) {
								MoveY = 1;
							} else if (Rnd == 6) {
								MoveX = -1;
								MoveY = -1;
							} else if (Rnd == 7) {
								MoveX = -1;
								MoveY = 1;
							} else if (Rnd == 8) {
								MoveX = 1;
								MoveY = -1;
							}

							if (MoveX == 1) {
								if (npcs[i].absX + MoveX < npcs[i].makeX + 1) {
									npcs[i].moveX = MoveX;
								} else {
									npcs[i].moveX = 0;
								}
							}

							if (MoveX == -1) {
								if (npcs[i].absX - MoveX > npcs[i].makeX - 1)  {
									npcs[i].moveX = MoveX;
								} else {
									npcs[i].moveX = 0;
								}
							}

							if(MoveY == 1) {
								if(npcs[i].absY + MoveY < npcs[i].makeY + 1) {
									npcs[i].moveY = MoveY;
								} else {
									npcs[i].moveY = 0;
								}
							}

							if(MoveY == -1) {
								if(npcs[i].absY - MoveY > npcs[i].makeY - 1)  {
									npcs[i].moveY = MoveY;
								} else {
									npcs[i].moveY = 0;
								}
							}
							handleClipping(i);
							npcs[i].getNextNPCMovement(i);
							npcs[i].updateRequired = true;
						}
					}
				}


			
			}
		}
	}
	
	
	
	
	public void stepAway(int i) {
		if(npcs[i].freezeTimer > 0) {// prevents from moving lol
			return;
		}
		if (Region.getClipping(npcs[i].getX() - 1, npcs[i].getY(), npcs[i].heightLevel, -1, 0)) {
			npcs[i].moveX = -1;
			npcs[i].getNextNPCMovement(i);
			npcs[i].updateRequired = true;
	         } else if (Region.getClipping(npcs[i].getX() + 1, npcs[i].getY(), npcs[i].heightLevel, 1, 0)) {
	        	 npcs[i].moveX = 1;
	 			npcs[i].getNextNPCMovement(i);
	 			npcs[i].updateRequired = true;
	        } else if (Region.getClipping(npcs[i].getX(), npcs[i].getY() - 1, npcs[i].heightLevel, 0, -1)) {
	        	npcs[i].moveY = -1;
				npcs[i].getNextNPCMovement(i);
				npcs[i].updateRequired = true;
	        } else if (Region.getClipping(npcs[i].getX(), npcs[i].getY() + 1, npcs[i].heightLevel, 0, 1)) {
	        	npcs[i].moveY = 1;
				npcs[i].getNextNPCMovement(i);
				npcs[i].updateRequired = true;
	        }
	}
	
	private void handleCountDray(int i) {
		Client c = (Client)PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			c.vampireslay = 5; //5 FINISH
			QuestHandling.VampireFinish(c);
			c.getPA().loadQuests();
			//c.getVS().finish

		}		}	
	
	private void handleratdeath(int i) {
		final Client c = (Client)PlayerHandler.players[npcs[i].killedBy];
			if (c != null) {			
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "",
						"Pass through the gate and talk to the Combat Instructor, he",
						"will give you your next task.",
						"",
						"Well done, you've made your first kill!");
				Tutorialisland.chatbox(c, 6179);
				c.getPA().drawHeadicon(1, 6, 0, 0); // draws headicon to combat ude
				c.tutorialprog = 25;
			}		}
	private void handleratdeath2(int i) {
		Client c = (Client)PlayerHandler.players[npcs[i].killedBy];
			if (c != null) {			
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "You have completed the tasks here. To move on, click on the",
						"ladder shown. If you need to go over any of what you learnt",
						"here, just talk to the Combat Instructor and he'll tell you what",
						"he can.",
						"Moving on");
				Tutorialisland.chatbox(c, 6179);
				
				c.tutorialprog = 26;
				c.getPA().createArrow(3111, 9525, c.getHeightLevel(), 2); //send hint to furnace
			}		}





	public boolean getsPulled(int i) {
		switch (npcs[i].npcType) {
		case 2550:
			if (npcs[i].firstAttacker > 0)
				return false;
			break;
		}
		return true;
	}

	public boolean multiAttacks(int i) {
		switch (npcs[i].npcType) {
		case 2558:
			return true;
		case 2562:
			if (npcs[i].attackType == 2)
				return true;
		case 2550:
			if (npcs[i].attackType == 1)
				return true;
		default:
			return false;
		}


	}

	/**
	 * Npc killer id?
	 **/

	public int getNpcKillerId(int npcId) {
		int oldDamage = 0;
		int killerId = 0;
		for (int p = 1; p < Config.MAX_PLAYERS; p++)  {
			if (PlayerHandler.players[p] != null) {
				if(PlayerHandler.players[p].lastNpcAttacked == npcId) {
					if(PlayerHandler.players[p].totalDamageDealt > oldDamage) {
						oldDamage = PlayerHandler.players[p].totalDamageDealt;
						killerId = p;
					}
					PlayerHandler.players[p].totalDamageDealt = 0;
				}
			}
		}
		return killerId;
	}


	/**
	 * Dropping Items!
	 **/
	public void dropItems(int i) {
		int npc = 0;
		Client c = (Client)PlayerHandler.players[npcs[i].killedBy];
		if(c != null) {
			for(npc = 0; npc < NPCDropsHandler.NPC_DROPS(getNpcListName(npcs[i].npcType).toLowerCase(),npcs[i].npcType).length; npc++){
					if(Misc.random(NPCDropsHandler.NPC_DROPS(getNpcListName(npcs[i].npcType).toLowerCase(),npcs[i].npcType)[npc][2]) == 0) {
						Server.itemHandler.createGroundItem(c, NPCDropsHandler.NPC_DROPS(getNpcListName(npcs[i].npcType).toLowerCase(),npcs[i].npcType)[npc][0], npcs[i].absX, npcs[i].absY, Misc.random(NPCDropsHandler.NPC_DROPS(getNpcListName(npcs[i].npcType).toLowerCase(),npcs[i].npcType)[npc][1]), c.playerId);
				}
			}
		}
	}


	/**
	 *	Resets players in combat
	 */

	public void resetPlayersInCombat(int i) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null)
				if (PlayerHandler.players[j].underAttackBy2 == i)
					PlayerHandler.players[j].underAttackBy2 = 0;
		}
	}

	/**
	 * Npc names
	 **/

	
	public String getNpcListName(int npcId) {
		for (NPCList i : NpcList) {
			if (i != null) {
				if (i.npcId == npcId) {
					return i.npcName;
				}
			}
		}
		return "nothing";
	}

	/**
	 * Npc Follow Player
	 **/

	public int GetMove(int Place1,int Place2) {
		if ((Place1 - Place2) == 0) {
			return 0;
		} else if ((Place1 - Place2) < 0) {
			return 1;
		} else if ((Place1 - Place2) > 0) {
			return -1;
		}
		return 0;
	}

	public boolean followPlayer(int i) {
		switch (npcs[i].npcType) {
		case 2892:
		case 2894:
			return false;
		}
		return true;
	}

	public void followPlayer(int i, int playerId) {
		if (PlayerHandler.players[playerId] == null) {
			return;
		}
		if (PlayerHandler.players[playerId].respawnTimer > 0) {
			npcs[i].facePlayer(0);
			npcs[i].randomWalk = true;
			npcs[i].underAttack = false;
			return;
		}

		if (!followPlayer(i)) {
			npcs[i].facePlayer(playerId);
			return;
		}

		int playerX = PlayerHandler.players[playerId].absX;
		int playerY = PlayerHandler.players[playerId].absY;
		npcs[i].randomWalk = false;
		if (goodDistance(npcs[i].getX(), npcs[i].getY(), playerX, playerY, distanceRequired(i)))
			return;

		if((npcs[i].spawnedBy > 0) || ((npcs[i].absX < npcs[i].makeX + Config.NPC_FOLLOW_DISTANCE) && (npcs[i].absX > npcs[i].makeX - Config.NPC_FOLLOW_DISTANCE) && (npcs[i].absY < npcs[i].makeY + Config.NPC_FOLLOW_DISTANCE) && (npcs[i].absY > npcs[i].makeY - Config.NPC_FOLLOW_DISTANCE))) {
			if(npcs[i].heightLevel == PlayerHandler.players[playerId].heightLevel) {
				if(PlayerHandler.players[playerId] != null && npcs[i] != null) {
					if(playerY < npcs[i].absY) {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if(playerY > npcs[i].absY) {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if(playerX < npcs[i].absX) {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if(playerX > npcs[i].absX)  {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if(playerX == npcs[i].absX || playerY == npcs[i].absY) {
						int o = Misc.random(3);
						switch(o) {
						case 0:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY+1);
							break;

						case 1:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY-1);
							break;

						case 2:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX+1);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY);
							break;

						case 3:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX-1);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY);
							break;
						}
					}
					npcs[i].facePlayer(playerId);
					handleClipping(i);
					npcs[i].getNextNPCMovement(i);
					npcs[i].facePlayer(playerId);
					npcs[i].updateRequired = true;
				}
			}
		} else {
			npcs[i].facePlayer(0);
			npcs[i].randomWalk = true;
			npcs[i].underAttack = false;
		}
	}

	public static void handleClipping(int i) {
		NPC npc = npcs[i];
		if(npc.moveX == 1 && npc.moveY == 1) {
			if((Region.getClipping(npc.absX + 1, npc.absY + 1, npc.heightLevel) & 0x12801e0) != 0)  {
				npc.moveX = 0; npc.moveY = 0;
				if((Region.getClipping(npc.absX, npc.absY + 1, npc.heightLevel) & 0x1280120) == 0)
					npc.moveY = 1;
				else
					npc.moveX = 1;
			}
		} else if(npc.moveX == -1 && npc.moveY == -1) {
			if((Region.getClipping(npc.absX - 1, npc.absY - 1, npc.heightLevel) & 0x128010e) != 0)  {
				npc.moveX = 0; npc.moveY = 0;
				if((Region.getClipping(npc.absX, npc.absY - 1, npc.heightLevel) & 0x1280102) == 0)
					npc.moveY = -1;
				else
					npc.moveX = -1;
			}
		} else if(npc.moveX == 1 && npc.moveY == -1) {
			if((Region.getClipping(npc.absX + 1, npc.absY - 1, npc.heightLevel) & 0x1280183) != 0)  {
				npc.moveX = 0; npc.moveY = 0;
				if((Region.getClipping(npc.absX, npc.absY - 1, npc.heightLevel) & 0x1280102) == 0)
					npc.moveY = -1;
				else
					npc.moveX = 1;
			}
		} else if(npc.moveX == -1 && npc.moveY == 1) {
			if((Region.getClipping(npc.absX - 1, npc.absY + 1, npc.heightLevel) & 0x128013) != 0)  {
				npc.moveX = 0; npc.moveY = 0;
				if((Region.getClipping(npc.absX, npc.absY + 1, npc.heightLevel) & 0x1280120) == 0)
					npc.moveY = 1;
				else
					npc.moveX = -1;
			}
		} //Checking Diagonal movement.

		if (npc.moveY == -1 ) {
			if ((Region.getClipping(npc.absX, npc.absY - 1, npc.heightLevel) & 0x1280102) != 0)
				npc.moveY = 0;
		} else if( npc.moveY == 1) {
			if((Region.getClipping(npc.absX, npc.absY + 1, npc.heightLevel) & 0x1280120) != 0)
				npc.moveY = 0;
		} //Checking Y movement.
		if(npc.moveX == 1) {
			if((Region.getClipping(npc.absX + 1, npc.absY, npc.heightLevel) & 0x1280180) != 0)
				npc.moveX = 0;
		} else if(npc.moveX == -1) {
			if((Region.getClipping(npc.absX - 1, npc.absY, npc.heightLevel) & 0x1280108) != 0)
				npc.moveX = 0;
		} //Checking X movement.
	}

	/**
	 * load spell
	 **/
	public void loadSpell2(int i) {
		npcs[i].attackType = 3;
		int random = Misc.random(3);
		if (random == 0) {
			npcs[i].projectileId = 393; //red
			npcs[i].endGfx = 430;
		} else if (random == 1) {
			npcs[i].projectileId = 394; //green
			npcs[i].endGfx = 429;
		} else if (random == 2) {
			npcs[i].projectileId = 395; //white
			npcs[i].endGfx = 431;
		} else if (random == 3) {
			npcs[i].projectileId = 396; //blue
			npcs[i].endGfx = 428;
		}
	}
	public int random;
	public int random2;

	
	public void loadSpell(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].oldIndex];
		switch(npcs[i].npcType) {
		case 795:
			random = Misc.random(4);
			if (random == 0 || random == 1 || random == 2 || random == 3)
				npcs[i].attackType = 0;
			else {
				//c.gfx0(369);
				npcs[i].freezeTimer = 10;
				//c.freezeTimer = 10;
				npcs[i].attackType = 1;
				//npcs[i].forceChat("Freeze, feel the power of ice!");
				//c.sendMessage("@red@Ice Queen: @dbl@Freeze, feel the power of ice!");
				npcs[i].forceChat("Hereby, I unleash my power of ice!");
				//c.sendMessage("@red@Ice queen: @dbl@Hereby, I unleash my power of ice!");
			}
			break;
		case 396:
			random = Misc.random(4);
			if (random == 0 || random == 1 || random == 2 || random == 3)
				npcs[i].attackType = 0;
			else {
				npcs[i].attackType = 1;
				npcs[i].forceChat("Grrrhoaaar!");
				//c.sendMessage("@red@River troll: @dbl@Grrrhoaaar!");
			}
			break;
		case 181:
			int randomer = Misc.random(2);
			if (randomer == 0) {
				npcs[i].projectileId = -1; //melee
				npcs[i].endGfx = -1;
				npcs[i].attackType = 0;
			} else if (randomer == 1) {
				npcs[i].projectileId = 178; //snare
				npcs[i].attackType = 2;
				npcs[i].endGfx = 180;
				if (c.freezeTimer <= 0) {
					c.freezeTimer = 5;
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					c.sendMessage("You have been frozen.");
				}
			}
			break;
		case 172:
		case 174:
			int randomer1 = Misc.random(5);
		if (randomer1 > 0) {
				npcs[i].projectileId = 94;
				npcs[i].gfx0(93);
				npcs[i].attackType = 1;
				npcs[i].endGfx = 95;
				
			}
			
			break;
		case 2892:
			npcs[i].projectileId = 94;
			npcs[i].attackType = 2;
			npcs[i].endGfx = 95;
			break;
		case 2894:
			npcs[i].projectileId = 298;
			npcs[i].attackType = 1;
			break;
		case 54:
		case 55:
		case 53:
		case 941:
			int randoms = Misc.random(2);
			if (randoms == 0) {
				npcs[i].projectileId = 393; //red
				npcs[i].endGfx = 430;
				npcs[i].attackType = 3;
			} else if (randoms == 1) {
				npcs[i].projectileId = -1; //melee
				npcs[i].endGfx = -1;
				npcs[i].attackType = 0;
			}
			break;
		case 50:
			int random = Misc.random(4);
			if (random == 0) {
				npcs[i].projectileId = 393; //red
				npcs[i].endGfx = 430;
				npcs[i].attackType = 3;
			} else if (random == 1) {
				npcs[i].projectileId = 394; //green
				npcs[i].endGfx = 429;
				npcs[i].attackType = 3;
			} else if (random == 2) {
				npcs[i].projectileId = 395; //white
				npcs[i].endGfx = 431;
				npcs[i].attackType = 3;
			} else if (random == 3) {
				npcs[i].projectileId = 396; //blue
				npcs[i].endGfx = 428;
				npcs[i].attackType = 3;
			} else if (random == 4) {
				npcs[i].projectileId = -1; //melee
				npcs[i].endGfx = -1;
				npcs[i].attackType = 0;
			}
			break;
			//arma npcs
		case 2561:
			npcs[i].attackType = 0;
			break;
		case 2560:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1190;
			break;
		case 2559:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 1203;
			break;
		case 2558:
			random = Misc.random(1);
			npcs[i].attackType = 1 + random;
			if (npcs[i].attackType == 1) {
				npcs[i].projectileId = 1197;
			} else {
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1198;
			}
			break;
			//sara npcs
		case 2562: //sara
			random = Misc.random(1);
			if (random == 0) {
				npcs[i].attackType = 2;
				npcs[i].endGfx = 1224;
				npcs[i].projectileId = -1;
			} else if (random == 1)
				npcs[i].attackType = 0;
			break;
		case 2563: //star
			npcs[i].attackType = 0;
			break;
		case 2564: //growler
			npcs[i].attackType = 2;
			npcs[i].projectileId = 1203;
			break;
		case 2565: //bree
			npcs[i].attackType = 1;
			npcs[i].projectileId = 9;
			break;
			//bandos npcs
		case 2550:
			random = Misc.random(2);
			if (random == 0 || random == 1)
				npcs[i].attackType = 0;
			else {
				npcs[i].attackType = 1;
				npcs[i].endGfx = 1211;
				npcs[i].projectileId = 288;
			}
			break;
		case 2551:
			npcs[i].attackType = 0;
			break;
		case 2552:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 1203;
			break;
		case 2553:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1206;
			break;
		case 2025:
			npcs[i].attackType = 2;
			int r = Misc.random(3);
			if(r == 0) {
				npcs[i].gfx100(158);
				npcs[i].projectileId = 159;
				npcs[i].endGfx = 160;
			}
			if(r == 1) {
				npcs[i].gfx100(161);
				npcs[i].projectileId = 162;
				npcs[i].endGfx = 163;
			}
			if(r == 2) {
				npcs[i].gfx100(164);
				npcs[i].projectileId = 165;
				npcs[i].endGfx = 166;
			}
			if(r == 3) {
				npcs[i].gfx100(155);
				npcs[i].projectileId = 156;
			}
			break;
		case 2881://supreme
		npcs[i].attackType = 1;
		npcs[i].projectileId = 298;
		break;

		case 2882://prime
			npcs[i].attackType = 2;
			npcs[i].projectileId = 162;
			npcs[i].endGfx = 477;
			break;

		case 2028:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 27;
			break;

		case 3200:
			int r2 = Misc.random(1);
			if (r2 == 0) {
				npcs[i].attackType = 1;
				npcs[i].gfx100(550);
				npcs[i].projectileId = 551;
				npcs[i].endGfx = 552;
			} else {
				npcs[i].attackType = 2;
				npcs[i].gfx100(553);
				npcs[i].projectileId = 554;
				npcs[i].endGfx = 555;
			}
			break;
		case 2745:
			int r3 = 0;
			if (goodDistance(npcs[i].absX, npcs[i].absY, PlayerHandler.players[npcs[i].spawnedBy].absX, PlayerHandler.players[npcs[i].spawnedBy].absY, 1))
				r3 = Misc.random(2);
			else
				r3 = Misc.random(1);
			if (r3 == 0) {
				npcs[i].attackType = 2;
				npcs[i].endGfx = 157;
				npcs[i].projectileId = 448;
			} else if (r3 == 1) {
				npcs[i].attackType = 1;
				npcs[i].endGfx = 451;
				npcs[i].projectileId = -1;
			} else if (r3 == 2) {
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
			}
			break;
		case 2743:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 445;
			npcs[i].endGfx = 446;
			break;

		case 2631:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 443;
			break;
		}
	}

	/**
	 * Distanced required to attack
	 **/
	public int distanceRequired(int i) {
		switch(npcs[i].npcType) {
		case 2025:
		case 2028:
			return 6;
		case 50:
		case 2562:
			return 2;
		case 172:
		case 174:
			return 4;
		case 2881://dag kings
		case 2882:
		case 3200://chaos ele
		case 2743:
		case 2631:
		case 2745:
			return 8;
		case 2883://rex
			return 1;
		case 2552:
		case 2553:
		case 2556:
		case 2557:
		case 2558:
		case 2559:
		case 2560:
		case 2564:
		case 2565:
			return 9;
			//things around dags
		case 2892:
		case 2894:
			return 10;
		default:
			return 1;
		}
	}

	public int followDistance(int i) {
		switch (npcs[i].npcType) {
		case 2550:
		case 2551:
		case 2562:
		case 2563:
			return 8;
		case 2883:
			return 4;
		case 2881:
		case 2882:
			return 1;

		}
		return 0;


	}

	public int getProjectileSpeed(int i) {
		switch(npcs[i].npcType) {
		case 2881:
		case 2882:
		case 3200:
			return 85;

		case 2745:
			return 130;

		case 50:
			return 90;

		case 2025:
			return 85;

		case 2028:
			return 80;

		default:
			return 85;
		}
	}

	/**
	 *NPC Attacking Player
	 **/

	public void attackPlayer(Client c, int i) {
		if(npcs[i] != null) {
			if (npcs[i].isDead)
				return;
			if (!npcs[i].inMulti() && npcs[i].underAttackBy > 0 && npcs[i].underAttackBy != c.playerId) {
				npcs[i].killerId = 0;
				return;
			}
			if (pathBlocked(npcs[i], c) && npcSizes(i) <= 1)
				return;
			if (!npcs[i].inMulti() && (c.underAttackBy > 0 || (c.underAttackBy2 > 0 && c.underAttackBy2 != i))) {
				npcs[i].killerId = 0;
				return;
			}
			if (npcs[i].heightLevel != c.heightLevel) {
				npcs[i].killerId = 0;
				return;
			}
			npcs[i].facePlayer(c.playerId);
			boolean special = false;//specialCase(c,i);
			if(goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(), c.getY(), distanceRequired(i)) || special) {
				if(c.respawnTimer <= 0) {
					npcs[i].facePlayer(c.playerId);
					npcs[i].attackTimer = getNpcDelay(i);
					npcs[i].hitDelayTimer = getHitDelay(i);
					npcs[i].attackType = 0;
					if (special)
						loadSpell2(i);
					else
						loadSpell(i);
					if (npcs[i].attackType == 3)
						npcs[i].hitDelayTimer += 2;
					if (multiAttacks(i)) {
						multiAttackGfx(i, npcs[i].projectileId);
						startAnimation(getAttackEmote(i), i);
						npcs[i].oldIndex = c.playerId;
						c.getPA().sendSound(Server.npcHandler.getNpcListAttackSound(NPCHandler.npcs[i].npcType), 100 , 32);
						//c.getPA().sendSound(getNpcListAttackSound(npcs[i].npcType), 100, 32);
						return;
					}
					if(npcs[i].projectileId > 0) {
						int nX = NPCHandler.npcs[i].getX() + offset(i);
						int nY = NPCHandler.npcs[i].getY() + offset(i);
						int pX = c.getX();
						int pY = c.getY();
						int offX = (nY - pY)* -1;
						int offY = (nX - pX)* -1;
						c.getPA().createPlayersProjectile(nX, nY, offX, offY, 50, getProjectileSpeed(i), npcs[i].projectileId, 43, 31, -c.getId() - 1, 65);
					}
					c.underAttackBy2 = i;
					c.singleCombatDelay2 = System.currentTimeMillis();
					npcs[i].oldIndex = c.playerId;
					startAnimation(getAttackEmote(i), i);
			//		c.getPA().sendSound(getNpcListAttackSound(npcs[i].npcType), 100, 32);
					c.getPA().sendSound(Server.npcHandler.getNpcListAttackSound(NPCHandler.npcs[i].npcType), 100 , 32);
					c.getPA().removeAllWindows();
				}
			}
		}
	}


	public int offset(int i) {
		switch (npcs[i].npcType) {
		case 50:
			return 2;
		case 2881:
		case 2882:
			return 1;
		case 2745:
		case 2743:
			return 1;
		}
		return 0;
	}

	public boolean specialCase(Client c, int i) { //responsible for npcs that much
		if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(), c.getY(), 8) && !goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(), c.getY(), distanceRequired(i)))
			return true;
		return false;
	}

	public boolean retaliates(int npcType) {
		return npcType < 3777 || npcType > 3780 && !(npcType >= 2440 && npcType <= 2446);
	}

	public void applyDamage(int i) {
		if(npcs[i] != null) {
			if(PlayerHandler.players[npcs[i].oldIndex] == null) {
				return;
			}

			if (npcs[i].isDead)
				return;
			Client c = (Client) PlayerHandler.players[npcs[i].oldIndex];
			if (multiAttacks(i)) {
				multiAttackDamage(i);
				return;
			}
			if (c.playerIndex <= 0 && c.npcIndex <= 0)
				if (c.autoRet == 1)
					c.npcIndex = i;
			if(c.attackTimer <= 3 || c.attackTimer == 0 && c.npcIndex == 0 && c.oldNpcIndex == 0) {
				c.startAnimation(c.getCombat().getBlockEmote(c.getItems().getItemName(c.playerEquipment[c.playerShield]).toLowerCase()));
				c.getPA().sendSound(c.getCombat().getBlockSound(c.getItems().getItemName(c.playerEquipment[c.playerChest]).toLowerCase()), 100,30);
				c.getPA().sendSound(c.getCombat().getBlockSoundShield(c.getItems().getItemName(c.playerEquipment[c.playerShield]).toLowerCase()), 100, 30);
			}
			if(c.respawnTimer <= 0) {
				int damage = 0;
				if(npcs[i].attackType == 0) {
					damage = Misc.random(npcs[i].maxHit);
					if (10 + Misc.random(c.getCombat().calculateMeleeDefence()) > Misc.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
					}
					if(damage > 0)
						c.getPA().sendSound(72,100,30);
					if(c.prayerActive[18]) { // protect from melee
						if (npcs[i].npcType == 2030)
							damage = (damage / 2);
						else
							damage = 0;
					}
					if (c.playerLevel[3] - damage < 0) {
						damage = c.playerLevel[3];
					}
				}

				if(npcs[i].attackType == 1) { // range
					damage = Misc.random(npcs[i].maxHit);
					if (10 + Misc.random(c.getCombat().calculateRangeDefence()) > Misc.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
					}
					if(c.prayerActive[17]) { // protect from range
						damage = 0;
					}
					if (c.playerLevel[3] - damage < 0) {
						damage = c.playerLevel[3];
					}
				}

				if(npcs[i].attackType == 2) { // magic
					damage = Misc.random(npcs[i].maxHit);
					boolean magicFailed = false;
					if (10 + Misc.random(c.getCombat().mageDef()) > Misc.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
						magicFailed = true;
					}
					if(c.prayerActive[16]) { // protect from magic
						damage = 0;
						magicFailed = true;
					}
					if (c.playerLevel[3] - damage < 0) {
						damage = c.playerLevel[3];
					}
					if(npcs[i].endGfx > 0 && (!magicFailed)) {
						c.gfx100(npcs[i].endGfx);
					} else {
						c.gfx100(85);
					}
				}

				if (npcs[i].attackType == 3) { //fire breath
					int anti = c.getPA().antiFire();
					if (anti == 0) {
						damage = Misc.random(30) + 10;
						c.sendMessage("You are badly burnt by the dragon fire!");
					} else if (anti == 1)
						damage = Misc.random(20);
					else if (anti == 2)
						damage = Misc.random(5);
					if (c.playerLevel[3] - damage < 0)
						damage = c.playerLevel[3];
					c.gfx100(npcs[i].endGfx);
				}
				handleSpecialEffects(c, i, damage);
				c.logoutDelay = System.currentTimeMillis(); // logout delay
				//c.setHitDiff(damage);
				c.handleHitMask(damage);
				c.playerLevel[3] -= damage;
				c.getPA().refreshSkill(3);
				c.updateRequired = true;
				//c.setHitUpdateRequired(true);
			}
		}
	}

	public void handleSpecialEffects(Client c, int i, int damage) {
		if (npcs[i].npcType == 2892 || npcs[i].npcType == 2894) {
			if (damage > 0) {
				if (c != null) {
					if (c.playerLevel[5] > 0) {
						c.playerLevel[5]--;
						c.getPA().refreshSkill(5);
						c.getPA().appendPoison(12);
					}
				}
			}
		}

	}



	public static void startAnimation(int animId, int i) {
		npcs[i].animNumber = animId;
		npcs[i].animUpdateRequired = true;
		npcs[i].updateRequired = true;
	}

	public boolean goodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
		return Math.sqrt(Math.pow(objectX - playerX, 2) + Math.pow(objectY - playerY, 2)) <= distance;
	}


	public int getMaxHit(int i) {
		switch (npcs[i].npcType) {
		case 2558:
			if (npcs[i].attackType == 2)
				return 28;
			else
				return 68;
		case 2562:
			return 31;
		case 2550:
			return 36;
		case 172:
		case 174:
			return 4;
		}
		return 1;
	}


	public boolean loadAutoSpawn(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			Misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			Misc.println(FileName+": error loading file.");
			return false;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				 int maxHit = 0;
				 int attacklvl = 0;
				 
				 if(getNpcListCombat(Integer.parseInt(token3[0])) < 19)
				 attacklvl = (int) (getNpcListCombat(Integer.parseInt(token3[0])) * 6.5);
				 else
				 attacklvl = (int) (getNpcListCombat(Integer.parseInt(token3[0]))* 2.5);
				 
				 if (getNpcListHP(Integer.parseInt(token3[0])) < 17)
				 maxHit = getNpcListHP(Integer.parseInt(token3[0]))/3;
				 else
				 maxHit = getNpcListHP(Integer.parseInt(token3[0]))/9;
				 
				 if (token.equals("spawn")) {
					newNPC(Integer.parseInt(token3[0]), Integer.parseInt(token3[1]), Integer.parseInt(token3[2]), Integer.parseInt(token3[3]), Integer.parseInt(token3[4]), getNpcListHP(Integer.parseInt(token3[0])), 
					maxHit, (attacklvl), (int) ((getNpcListCombat(Integer.parseInt(token3[0])))* 1.7));
					}        // attack                                             //defence
			} else {
				if (line.equals("[ENDOFSPAWNLIST]")) {
					try { characterfile.close(); } catch(IOException ioexception) { }
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}

	public int getNpcListHP(int npcId) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == npcId) {
					return NpcList[i].npcHealth;
				}
			}
		}
		return 0;
	}
	
	private int getNpcListAttackSound(int npcId) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == npcId) {
					return NpcList[i].attackSound;
				}
			}
		}
		
		return 477;
	}
	
	public int getNpcListBlockSound(int npcId) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == npcId) {
					return NpcList[i].blockSound;
				}
			}
		}
		return 477;
	}
	public int getNpcListDieSound(int npcId) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == npcId) {
					return NpcList[i].dieSound;
				}
			}
		}
		return 70;

	}
	public int getNpcListCombat(int npcId) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == npcId) {
					return NpcList[i].npcCombat;
				}
			}
		}
		return 0;
	}

	public boolean loadNPCSounds(String FileName) {
		String line = "";
		boolean EndOfFile = false;
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			return false;
		}
		try {
			line = file.readLine();
		} catch(IOException ioexception) {
			return false;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			try {
				line = file.readLine();
				if(line.equals(".")) {
					file.close();
					return true;
				}
				String[] split = line.split("	");
				if(!line.startsWith("//") && !line.startsWith(".")) {
					newNPCSound(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
				}
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try {
			file.close();
		} catch(IOException ioexception) {
		}
		return false;
	}

	public void newNPCSound(int npcId, int attack, int block, int die) {
		System.out.println("New npc sound: "+npcId+", "+attack+", "+block+", "+die);
		int slot = -1;
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i].npcId == npcId) {
				slot = i;
				break;
			}
		}

		if(slot == -1) 
			
			return;		// npc entry not found

		NpcList[slot].attackSound = attack;
		NpcList[slot].blockSound = block;
		NpcList[slot].dieSound = die;
	}
	public boolean loadNPCList(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			Misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			Misc.println(FileName+": error loading file.");
			try {
				characterfile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("npc")) {
					newNPCList(Integer.parseInt(token3[0]), token3[1], Integer.parseInt(token3[2]), Integer.parseInt(token3[3]));
				}
			} else {
				if (line.equals("[ENDOFNPCLIST]")) {
					try { characterfile.close(); } catch(IOException ioexception) { }
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}


}
