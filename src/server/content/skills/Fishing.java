package server.content.skills;

import server.Config;
import server.content.dialoguesystem.DialogueSystem;
import server.content.quests.misc.Tutorialisland;
import server.content.randoms.Whirlpool;
import server.content.skills.misc.SkillHandler;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;
import server.game.players.DialogueHandler;
import core.util.Misc;

	/**
	 * 	Class Fishing
	 * Handles:	Fishing
	 * @author:	PapaDoc edited by Dark.
	 * START:	22:07 23/12/2010
	 * FINISH:	22:28 23/12/2010
	 */

public class Fishing extends SkillHandler {
	/**
	 * Resets the Event according to the ID.
	 * id = 10 (According to skill.)
	 */
	protected static int eventId = 10; 
	
	
	public static int[][] data = {
		{1, 1, 303, -1, 317, 10, 621, 321, 15, 30},		//SHRIMP + ANCHOVIES
		{2, 5, 307, 313, 327, 20, 622, 345, 10, 30},		//SARDINE + HERRING
		{3, 16, 305, -1, 353, 20, 620, -1, -1, -1},		//MACKEREL
		{4, 20, 309, 314, 335, 50, 622, 331, 30, 70},		//TROUT
		{5, 23, 305, -1, 341, 45, 619, 363, 46, 100},		//BASS + COD
		{6, 25, 309, 313, 349, 60, 622, -1, -1, -1},		//PIKE
		{7, 35, 311, -1, 359, 80, 618, 371, 50, 100},		//TUNA + SWORDIE
		{8, 40, 301, -1, 377, 90, 619, -1, -1, -1},		//LOBSTER
	};
	private static String[][] messages = {
		{"You cast out your net."},		//SHRIMP + ANCHOVIES
		{"You cast out your line." },		//SARDINE + HERRING
		{"You start harpooning fish."},		//TUNA + SWORDIE
		{"You attempt to catch a lobster."},		//LOBSTER
	};

	private static void attemptdata(final Client c, final int npcId) {
		if(!Config.FISHING_ENABLED) {
			c.sendMessage(c.disabled());
			return;
		}
		if(c.playerSkillProp[10][4] > 0) {
			c.playerSkilling[10] = false;
			return;
		}
		if (!noInventorySpace(c, "fishing")) {
			return;
		}
 		resetFishing(c);
		for(int i = 0; i < data.length; i++) {
			if(npcId == data[i][0]) {
				if (c.playerLevel[c.playerFishing] < data[i][1]) {
					DialogueHandler.sendStatement(c, "You need a fishing level of at least "+ data[i][1] +" in order to fish at this spot.");
					return;
				}                                
				if(!hasFishingEquipment(c, data[i][2])) {
				    return;
				}
				if(data[i][3] > 0) {
					if (!c.getItems().playerHasItem(data[i][3])) {
						DialogueSystem.sendStatement(c, "You need more " + c.getItems().getItemName(data[i][3]).toLowerCase().toLowerCase() + " in order to fish at this spot.");
						return;
					}
				}
				c.playerSkillProp[10][0] = data[i][6]; //	ANIM
				c.playerSkillProp[10][1] = data[i][4]; //	FISH
				c.playerSkillProp[10][2] = data[i][5]; //	XP
				c.playerSkillProp[10][3] = data[i][3]; //	BAIT
				c.playerSkillProp[10][4] = data[i][2]; //	EQUIP
				c.playerSkillProp[10][5] = data[i][7]; //	sFish
				c.playerSkillProp[10][6] = data[i][8]; //	sLvl
				c.playerSkillProp[10][7] = data[i][4]; //	FISH
				c.playerSkillProp[10][8] = data[i][9]; //	sXP
				c.playerSkillProp[10][9] = Misc.random(1) == 0 ? 7 : 5;
				c.playerSkillProp[10][10] = data[i][0]; //	INDEX

				if(c.playerSkilling[10]) {
					return;
				}
				c.playerSkilling[10] = true;
				 if(Whirlpool.isWhirlPoolNpc(c, c.npcType)) {
				    Whirlpool.fishWhirlPool(c, npcId);
				    return;
				}
				if(c.tutorialprog == 6) { // if tutorial prog = 6
					c.startAnimation(c.playerSkillProp[10][0]);
					c.stopPlayerSkill = true;
					c.getPA().drawHeadicon(0, 0, 0, 0); // deletes headicon
					Tutorialisland.chatbox(c, 6180);
					Tutorialisland.chatboxText
					(c, "This should only take a few seconds.",
							"As you gain Fishing experience you'll find that there are many",
							"types of fish and many ways to catch them.",
							"",
							"Please wait");
					Tutorialisland.chatbox(c, 6179);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {

							if(c.playerSkillProp[10][5] > 0) {
								if(c.playerLevel[c.playerFishing] >= c.playerSkillProp[10][6]) {
									c.playerSkillProp[10][1] = c.playerSkillProp[10][Misc.random(1) == 0 ? 7 : 5];
								}
							}
							
							
							if(!c.stopPlayerSkill) {
								container.stop();
							}
							if(!c.playerSkilling[10]) {
								container.stop();
							}
							
							if(c.playerSkillProp[10][1] > 0) {
								c.startAnimation(c.playerSkillProp[10][0]);
							}						
						
						}
						@Override
						public void stop() {
							resetFishing(c);
						}
					}, 5);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							if(c.playerSkillProp[10][5] > 0) {
								if(c.playerLevel[c.playerFishing] >= c.playerSkillProp[10][6]) {
									c.playerSkillProp[10][1] = c.playerSkillProp[10][Misc.random(1) == 0 ? 7 : 5];
								}
							}
							if(c.playerSkillProp[10][1] > 0) {
								c.getItems().addItem(c.playerSkillProp[10][1], 1);
								c.startAnimation(c.playerSkillProp[10][0]);
								Tutorialisland.sendDialogue(c, 3019, 0);
								container.stop();
							}
							if (!noInventorySpace(c, "fishing")) {
								container.stop();
							}
							
							if(!c.stopPlayerSkill) {
								container.stop();
							}
							if(!c.playerSkilling[10]) {
								container.stop();
							}
						}
						@Override
						public void stop() {
							resetFishing(c);
						}
					}, getTimer(c, npcId) + 5 + playerFishingLevel(c));
					return;
				}
				
				// end of tutorial island fishing
				
				c.sendMessage("" + messages(c));
				c.getPA().sendSound(379, 100, 1); // fishing
				c.startAnimation(c.playerSkillProp[10][0]);
				c.stopPlayerSkill = true;
				Whirlpool.eventTimer(c, npcId);
				CycleEventHandler.getSingleton().addEvent(eventId,c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
					    if(c.fishingWhirlPool) {
						    stopEvents(c, eventId);
						    Whirlpool.fishWhirlPool(c, npcId);
						    container.stop();
						    return;
						}
					    if(c.playerSkillProp[10][3] > 0) {
						
							if (!c.getItems().playerHasItem(c.playerSkillProp[10][3])) {
								c.sendMessage("You don't have any "+ c.getItems().getItemName(c.playerSkillProp[10][3]) +" left!");
								c.sendMessage("You need "+ c.getItems().getItemName(c.playerSkillProp[10][3]) +" to fish here.");
								resetFishing(c);
								container.stop();
							}
						}
						if(c.playerSkillProp[10][5] > 0) {
							if(c.playerLevel[c.playerFishing] >= c.playerSkillProp[10][6]) {
								c.playerSkillProp[10][1] = c.playerSkillProp[10][Misc.random(1) == 0 ? 7 : 5];
							}
						}
	
						if (!hasFishingEquipment(c, c.playerSkillProp[10][4])) {
							resetFishing(c);
							container.stop();
						}
						if (!noInventorySpace(c, "fishing")) {
							resetFishing(c);
							container.stop();
						}
						if (Misc.random(55) == 0) {
							resetFishing(c);
							container.stop();
						}
						if(!c.stopPlayerSkill) {
							container.stop();
						}
						if(!c.playerSkilling[10]) {
							resetFishing(c);
							container.stop();
						}
						if(c.playerSkillProp[10][1] > 0) {
							c.startAnimation(c.playerSkillProp[10][0]);
							c.getPA().sendSound(379, 100, 1); // fishing
						}						
					
					}
					@Override
					public void stop() {
					}
				}, 5);
				CycleEventHandler.getSingleton().addEvent(eventId,c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if(c.playerSkillProp[10][5] > 0) {
							if(c.playerLevel[c.playerFishing] >= c.playerSkillProp[10][6]) {
								c.playerSkillProp[10][1] = c.playerSkillProp[10][Misc.random(1) == 0 ? 7 : 5];
							}
						}
						if(c.playerSkillProp[10][1] > 0) {
							c.sendMessage("You catch " +
							(c.playerSkillProp[10][1] == 321 || 
							c.playerSkillProp[10][1] == 317 ? "some " : "a ") + 
							c.getItems().getItemName(c.playerSkillProp[10][1]).toLowerCase().replace("raw ", "") + ".");
						} 
						if(c.playerSkillProp[10][1] > 0) {
							c.getItems().deleteItem(c.playerSkillProp[10][3], c.getItems().getItemSlot(c.playerSkillProp[10][3]), 1);
							c.getItems().addItem(c.playerSkillProp[10][1], 1);
							c.startAnimation(c.playerSkillProp[10][0]);
						}
						if(c.playerSkillProp[10][2] > 0) {
							c.getPA().addSkillXP(c.playerSkillProp[10][2], c.playerFishing);
						}
						if(c.playerSkillProp[10][3] > 0) {
							if (!c.getItems().playerHasItem(c.playerSkillProp[10][3])) {
								DialogueSystem.sendStatement(c, "You have run out of " + c.getItems().getItemName(c.playerSkillProp[10][3]).toLowerCase().toLowerCase() + ".");
								//	c.sendMessage("You don't have any "+ c.getItems().getItemName(c.playerSkillProp[10][3]) +" left!");
								//c.sendMessage("You need "+ c.getItems().getItemName(c.playerSkillProp[10][3]) +" to fish here.");
								container.stop();
							}
						}
						if (!hasFishingEquipment(c, c.playerSkillProp[10][4])) {
							resetFishing(c);
							container.stop();
						}
						if (!noInventorySpace(c, "fishing")) {
							resetFishing(c);
							container.stop();
						}
						if (Misc.random(55) == 0) {
							resetFishing(c);
							container.stop();
						}
						if(!c.stopPlayerSkill) {
							container.stop();
						}
						if(!c.playerSkilling[10]) {
							resetFishing(c);
							container.stop();
						}
					}
					@Override
					public void stop() {
					}
				}, getTimer(c, npcId) + 5 + playerFishingLevel(c));
			}
		}
	}

	public static boolean hasFishingEquipment(Client c, int equipment) {
		if (!c.getItems().playerHasItem(equipment)) {
			if(equipment == 311) {
				if(!c.getItems().playerHasItem(311) && !c.getItems().playerHasItem(10129) && c.playerEquipment[3] != 10129) {
					c.sendMessage("You need a "+ c.getItems().getItemName(equipment).toLowerCase() +" to fish here.");
					resetFishing(c);
					return false;
				}
			} else {
				resetFishing(c);
				c.sendMessage("You need a "+ c.getItems().getItemName(equipment) +" to fish here.");
				return false;
			}
		}
		return true;
	}

	public static void resetFishing(Client c) {
		c.startAnimation(65535);
		c.stopPlayerSkill = false;
		c.playerSkilling[10] = false;
		c.fishingWhirlPool = false;
		  stopEvents(c, eventId);
		for(int i = 0; i < 11; i++) {
			c.playerSkillProp[10][i] = -1;
		}
	}
	public static String messages(Client c) {
		if(c.playerSkillProp[10][10] == 1) // Shrimps etc
			return messages[0][0];
		
		if(c.playerSkillProp[10][10] == 2 || c.playerSkillProp[10][10] == 3
			|| c.playerSkillProp[10][10] == 4 || c.playerSkillProp[10][10] == 5 ||
			c.playerSkillProp[10][10] == 6)		
			return messages[1][0];
		
		if(c.playerSkillProp[10][10] == 7) // swordfish
			return messages[2][0];
		
		if(c.playerSkillProp[10][10] == 8)
			return messages[3][0];
		
		return null;
	}

	private static int playerFishingLevel(Client c) {
		return (10 - (int)Math.floor(c.playerLevel[c.playerFishing] / 10));
	}

	private final static int getTimer(Client c, int npcId) {
		switch (npcId) {
			case 1: return 2;
			case 2:	return 3;
			case 3:	return 4;
			case 4: return 4;
			case 5: return 4;
			case 6: return 5;
			case 7: return 5;
			case 8: return 5;
			case 9: return 5;
			case 10: return 5;
			case 11: return 9;
			case 12: return 9;
			default: return -1;
		}
	}

	public static void fishingNPC(Client c, int i, int l) {
		switch (i) {
			case 1:
				switch (l) {
					case 319:
					case 329:
					case 323:
					case 325:
					case 326:
					case 327:
					case 330:
					case 332:
					case 404:
					case 316: //NET + BAIT
						Fishing.attemptdata(c, 1);
						break;
					case 334:
					case 313: //NET + HARPOON
						Fishing.attemptdata(c, 3);
						break;
					case 322: //NET + HARPOON
						Fishing.attemptdata(c, 5);
						break;

					case 309: //LURE
					case 310:
					case 403:
					case 311:
					case 314:
					case 315:
					case 317:
					case 318:
					case 328:
					case 331:
						Fishing.attemptdata(c, 4);
						break;

					case 312:
					case 321:
					case 405:
					case 324: //CAGE + HARPOON
						Fishing.attemptdata(c, 8);
						break;
				}
				break;
			case 2:
				switch (l) {
					case 326:
					case 327:
					case 330:
					case 332:
					case 404:
					case 316: //BAIT + NET
						Fishing.attemptdata(c, 2);
						break;
					case 319:
					case 323:
					case 325: //BAIT + NET
						Fishing.attemptdata(c, 9);
						break;
					case 310:
					case 311:
					case 314:
					case 315:
					case 317:
					case 318:
					case 328:
					case 329:
					case 331:
					case 403:
					case 309: //BAIT + LURE
						Fishing.attemptdata(c, 6);
						break;
					case 312:
					case 321:
					case 405:
					case 324://SWORDIES+TUNA-CAGE+HARPOON
						Fishing.attemptdata(c, 7);
						break;
					case 313:
					case 322:
					case 334:	//NET+HARPOON
						Fishing.attemptdata(c, 10);
						break;
				}
				break;
		}
	}

	public static boolean fishingNPC(Client c, int npc) {
		for(int i = 308; i < 335; i++) {
			if(npc == i) {
				return true;
			}
			if(Whirlpool.isWhirlPoolNpc(c, npc))
			    return true;
		}
		return false;
	}
}