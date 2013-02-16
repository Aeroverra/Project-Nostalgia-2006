package server.game.players;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Future;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

import server.Config;
import server.Connection;
import server.Packet;
import server.Packet.Type;
import server.Server;
import server.content.music.MusicPlayer;
import server.content.quests.dialogue.VampireSlayer;
import server.content.quests.misc.Tutorialisland;
import server.content.randoms.MimeEvent;
import server.content.skills.Magic;
import server.content.skills.Prayer;
import server.event.CycleEventHandler;
import server.game.items.Item;
import server.game.items.ItemAssistant;
import server.game.items.Weight;
import server.game.players.adminpowers.AttackOtherPlayers;
import server.game.shops.ShopAssistant;
import core.util.Misc;
import core.util.Stream;

public class Client extends Player {

	public byte buffer[] = null;
	public Stream inStream = null, outStream = null;
	public Channel session;

	private ItemAssistant itemAssistant = new ItemAssistant(this);
	private ShopAssistant shopAssistant = new ShopAssistant(this);
	private TradeAndDuel tradeAndDuel = new TradeAndDuel(this);
	private PlayerAssistant playerAssistant = new PlayerAssistant(this);
	private CombatAssistant combatAssistant = new CombatAssistant(this);
	private ActionHandler actionHandler = new ActionHandler(this);
	private final Queue<Packet> queuedPackets = new LinkedList<Packet>();

	/**
	 * Quests TODO clean out the quests to become non instancedlol
	 */
	private VampireSlayer vampireslayer = new VampireSlayer(this);

	/**
	 * Skill instances
	 */
	private Prayer prayer = new Prayer(this);
	private Magic magic = new Magic(this);


	private Future<?> currentTask;
	public int currentRegion = 0;
	public int selectedSkill;

	public Client(Channel s, int _playerId) {
		super(_playerId);
		this.session = s;
		synchronized(this) {
			outStream = new Stream(new byte[Config.BUFFER_SIZE]);
			outStream.currentOffset = 0;

		inStream = new Stream(new byte[Config.BUFFER_SIZE]);
		inStream.currentOffset = 0;
		buffer = new byte[Config.BUFFER_SIZE];
                }
	}
	public Client getClient(int index) {
		return ((Client) PlayerHandler.players[index]);
	}
	public boolean validClient(int index) {
		Client p = (Client) PlayerHandler.players[index];
		if ((p != null) && !p.disconnected) {
			return true;
		}
		return false;
	}
	/**
	 * Shakes the player's screen.
	 * Parameters 1, 0, 0, 0 to reset.
	 * @param verticleAmount How far the up and down shaking goes (1-4).
	 * @param verticleSpeed How fast the up and down shaking is.
	 * @param horizontalAmount How far the left-right tilting goes.
	 * @param horizontalSpeed How fast the right-left tiling goes..
	 */

	public void shakeScreen(int verticleAmount, int verticleSpeed, int horizontalAmount, int horizontalSpeed) {
		outStream.createFrame(35); // Creates frame 35.
		outStream.writeByte(verticleAmount);
		outStream.writeByte(verticleSpeed);
		outStream.writeByte(horizontalAmount);
		outStream.writeByte(horizontalSpeed);
	}

	/**
	 * Resets the shaking of the player's screen.
	 */
	//public void resetShaking() {
	//	shakeScreen(0, 0, 0, 0);
	//}
	 public void follow(int slot, int type, int distance) {
	        if (slot > 0) {
	            if (type == 0) {
	                if (slot == followId2 && followDistance >= distance && distance != 1) {
	                    return;
	                }
	            }
	            if (type == 1) {
	                if (slot == followId && followDistance != distance && (usingBow || usingMagic)) {
	                    return;
	                }
	            }
	        }
	        if (freezeTimer > 0) {
	            return;
	        }
	        switch (type) {
	            case 0:
	                followId = 0;
	                followId2 = slot;
	                followDistance = distance;
	                faceUpdate(followId2);
	                break;
	            case 1:
	                followId = slot;
	                followId2 = 0;
	                followDistance = distance;
	                faceUpdate(32768 + followId);
	                break;
	            case 3:
	                followId = 0;
	                followId2 = 0;
	                followDistance = 0;
	                resetWalkingQueue();
	                faceUpdate(65535);
	                break;
	        }
	    }
	public void flushOutStream() {
		if (!session.isConnected() || disconnected || outStream.currentOffset == 0)
			return;

		byte[] temp = new byte[outStream.currentOffset];
		System.arraycopy(outStream.buffer, 0, temp, 0, temp.length);
		Packet packet = new Packet(-1, Type.FIXED, ChannelBuffers.wrappedBuffer(temp));
		session.write(packet);
		outStream.currentOffset = 0;

	}
	public int calcCombat() {
		int j = getLevelForXP(playerXP[playerAttack]);
		int k = getLevelForXP(playerXP[playerDefence]);
		int l = getLevelForXP(playerXP[playerStrength]);
		int i1 = getLevelForXP(playerXP[playerHitpoints]);
		int j1 = getLevelForXP(playerXP[playerPrayer]);
		int k1 = getLevelForXP(playerXP[playerRanged]);
		int l1 = getLevelForXP(playerXP[playerMagic]);
		int combatLevel = (int) (((k + i1) + Math.floor(j1 / 2)) * 0.25D) + 1;
		double d = (j + l) * 0.32500000000000001D;
		double d1 = Math.floor(k1 * 1.5D) * 0.32500000000000001D;
		double d2 = Math.floor(l1 * 1.5D) * 0.32500000000000001D;
		if (d >= d1 && d >= d2) {
			combatLevel += d;
		} else if (d1 >= d && d1 >= d2) {
			combatLevel += d1;
		} else if (d2 >= d && d2 >= d1) {
			combatLevel += d2;
		}
		return combatLevel;
	}

	public static final int PACKET_SIZES[] = {
		0, 0, 0, 1, -1, 0, 0, 0, 0, 0, //0
		0, 0, 0, 0, 8, 0, 6, 2, 2, 0,  //10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, //20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2,  //30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, //40
		0, 0, 0, 12, 0, 0, 0, 8, 8, 12, //50
		8, 8, 0, 0, 0, 0, 0, 0, 0, 0,  //60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, //70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0,  //80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, //90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0,//100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0,  //110
		1, 0, 6, 0, 0, 0, -1, 0, 2, 6, //120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2,  //130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0,  //140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0,  //150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0,//160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  //170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1,  //180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, //190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0,  //200
		4, 0, 0, 0, 7, 8, 0, 0, 10, 0, //210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, //220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0,  //230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4,//240
		0, 0, 6, 6, 0, 0, 0            //250
	};
	public void destruct() {
		if(underAttackBy > 0 || underAttackBy2 > 0) // Prevents X-LOG
			return;
			if(session == null)
				return;
		//	Server.panel.removeEntity(playerName);
			String IP = ((InetSocketAddress) getSession().getRemoteAddress()).getAddress().getHostAddress();
			    Connection.removeIpFromLoginList(IP);
		//	PlayerSaving.getSingleton().requestSave(playerId);
			Misc.println("[OFFLINE]: "+ Misc.capitalize(playerName) +"");
			CycleEventHandler.getSingleton().stopEvents(this);
			disconnected = true;
			session.close();
			session = null;
			inStream = null;
			outStream = null;
			isActive = false;
			buffer = null;
			if(MimeEvent.mime != null)
			    MimeEvent.mime.deleteNPC(MimeEvent.mime);
			super.destruct();
		}


	public void membersonly() {
		sendMessage("You need to be on a members world to do that.");
	}
	public final String disabled() {
		return "Skill is disabled for testing period.";
	}

	public final void sendMessage(String s) {
		if (getOutStream() != null) {
			outStream.createFrameVarSize(253);
			outStream.writeString(s);
			outStream.endFrameVarSize();

		}
	}


	public void setSidebarInterface(int menuId, int form) {
		//synchronized (this) {
		if(getOutStream() != null) {
			outStream.createFrame(71);
			outStream.writeWord(form);
			outStream.writeByteA(menuId);
		}

	}
	public void initialize() {
	    String IP = ((InetSocketAddress) getSession().getRemoteAddress()).getAddress().getHostAddress();
	    Connection.addIpToLoginList(IP);
	   // Server.panel.addEntity(playerName);
	    for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (j == playerId)
				continue;
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].playerName
			.equalsIgnoreCase(playerName))
				disconnected = true;
			}
		}
		
		
		if(isRunning2) {
			getPA().sendFrame36(504, 1);
			getPA().sendFrame36(173, 1);
		} else {
			getPA().sendFrame36(504, 0);
			getPA().sendFrame36(173, 0);
		}
		Weight.updateWeight(this);
		calcCombat();
		outStream.createFrame(249);
		outStream.writeByteA(1); // 1 for members, zero for free
		outStream.writeWordBigEndianA(playerId);

		for (int i = 0; i < 25; i++) {
			getPA().setSkillLevel(i, playerLevel[i], playerXP[i]);
			getPA().refreshSkill(i);
		}
		for (int p = 0; p < PRAYER.length; p++) { // reset prayer glows
			prayerActive[p] = false;
			getPA().sendFrame36(PRAYER_GLOW[p], 0);
		}
		getPlayList().fixAllColors();
		getPA().handleWeaponStyle();
		getPA().handleLoginText();
		accountFlagged = getPA().checkForFlags();
		getPA().sendFrame36(108, 0);// resets autocast button
		getPA().sendFrame36(172, 1);
		getPA().sendFrame107(); // reset screen
		getPA().setChatOptions(0, 0, 0); // reset private messaging options
		setSidebarInterface(10, 2449);
		setSidebarInterface(1, 3917);
		setSidebarInterface(2, 638);
		setSidebarInterface(3, 3213);
		setSidebarInterface(4, 1644);
		setSidebarInterface(5, 5608);
		if (playerMagicBook == 0) {
			setSidebarInterface(6, 1151); // modern
		} else {
			setSidebarInterface(6, 12855); // ancient
		}
		if (!splitChat) {
			getPA().sendFrame36(502, 0);
			getPA().sendFrame36(287, 0);
		} else
		if (splitChat) {
			getPA().sendFrame36(502, 1);
			getPA().sendFrame36(287, 1);
		}
		brightness();
		setSidebarInterface(7, -1);//invisible
		setSidebarInterface(8, 5065);
		setSidebarInterface(9, 5715);
		setSidebarInterface(11, 904); // wrench tab
		setSidebarInterface(12, 147); // run tab
		setSidebarInterface(13, 962);
		setSidebarInterface(0, 2423);
		equipmentlogin();
		sendMessage("Welcome to " + Config.SERVER_NAME + ".");
		getPA().showOption(4, 0,"Follow", 4);
		    getPA().showOption(5, 0,"Trade With", 3);
		if(AttackOtherPlayers.attackOtherPlayers(this))
		    getPA().showOption(3, 0, "Attack", 1);
		getItems().resetItems(3214);
		getPA().logIntoPM();
		getItems().addSpecialBar(playerEquipment[playerWeapon]);
		saveTimer = Config.SAVE_TIMER;
		saveCharacter = true;
		Misc.println("[ONLINE]: " + Misc.capitalize(playerName) + "");
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		flushOutStream();
		getPA().resetFollow();

		if (autoRet == 1)
			getPA().sendFrame36(172, 1);
		else
			getPA().sendFrame36(172, 0);
		totalLevel = getPA().totalLevel();
		xpTotal = getPA().xpTotal();
		HighscoresConfig.updateHighscores(this);
		if (tutorialprog == 0) {
			Tutorialisland.sendDialogue(this, 3000, 0);
			
		}if (tutorialprog == 1) {
			Tutorialisland.sendDialogue(this, 3008, 945);
			
		}if (tutorialprog == 2) {
			Tutorialisland.sendDialogue(this, 3011, 0);
			
		}if (tutorialprog == 3) {
		    Tutorialisland.chatbox(this, 6180);
			Tutorialisland.chatboxText
			(this, "You can click on the backpack icon at any time to view the",
					"items that you currently have in your inventory. You will see",
					"that you now have an axe in your inventory. Use this to get",
					"some logs by clicking on one of the trees in the area.",
					"Cut down a tree");
			this.getPA().createArrow(3099, 3095, this.getHeightLevel(), 2);
			Tutorialisland.chatbox(this, 6179);
			
		}else if (tutorialprog == 4){
		    Tutorialisland.sendDialogue(this, 3015, 0);
		}else if(tutorialprog == 3){
		    Tutorialisland.sendDialogue(this, 3010, 0);
		}else if(tutorialprog == 6){
		    Tutorialisland.sendDialogue(this, 3018, 0);
		}if(tutorialprog == 7){
		    Tutorialisland.sendDialogue(this, 3018, 0);
		}else if(tutorialprog == 8){
		    Tutorialisland.sendDialogue(this, 3025, 0);
		}else if(tutorialprog == 9){
		    Tutorialisland.sendDialogue(this, 3037, 0);
		}else if(tutorialprog == 11){
		    Tutorialisland.sendDialogue(this, 3039, 0);
		}else if(tutorialprog == 12){
		    Tutorialisland.sendDialogue(this, 3042, 0);
		}else if(tutorialprog == 14){
		    Tutorialisland.sendDialogue(this, 3050, 0);
		}else if(tutorialprog == 15){
		    Tutorialisland.sendDialogue(this, 3055, 0);
		}else if(tutorialprog == 17){
		    Tutorialisland.sendDialogue(this, 3060, 0);
		}else if(tutorialprog == 19){
		    Tutorialisland.sendDialogue(this, 3061, 0);
		}
	}

	private Highscores highscores = new Highscores(this);

	public Highscores getHighscores() {
		return highscores;
	}

	public void equipmentlogin() {
		isFullHelm = Item.isFullHelm(playerEquipment[playerHat]);
		isFullMask = Item.isFullMask(playerEquipment[playerHat]);
		isFullBody = Item.isFullBody(playerEquipment[playerChest]);
		getItems().sendWeapon(playerEquipment[playerWeapon],
				getItems().getItemName(playerEquipment[playerWeapon]));
		getItems().resetBonus();
		getItems().getBonus();
		getItems().writeBonus();
		getItems().setEquipment(playerEquipment[playerHat], 1, playerHat);
		getItems().setEquipment(playerEquipment[playerCape], 1, playerCape);
		getItems().setEquipment(playerEquipment[playerAmulet], 1, playerAmulet);
		getItems().setEquipment(playerEquipment[playerArrows],
				playerEquipmentN[playerArrows], playerArrows);
		getItems().setEquipment(playerEquipment[playerChest], 1, playerChest);
		getItems().setEquipment(playerEquipment[playerShield], 1, playerShield);
		getItems().setEquipment(playerEquipment[playerLegs], 1, playerLegs);
		getItems().setEquipment(playerEquipment[playerHands], 1, playerHands);
		getItems().setEquipment(playerEquipment[playerFeet], 1, playerFeet);
		getItems().setEquipment(playerEquipment[playerRing], 1, playerRing);
		getItems().setEquipment(playerEquipment[playerWeapon],
				playerEquipmentN[playerWeapon], playerWeapon);
		getCombat().getPlayerAnimIndex(
				getItems().getItemName(playerEquipment[playerWeapon])
						.toLowerCase());
	}
	public void update() {
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		flushOutStream();

	}
	public void handleWelcomeScreen() {
	   getPA().sendFrame126("Welcome to F2P server!", 15259); 
	   getPA().sendFrame126("@red@ please visit our forum!", 15260); 
	    getPA().sendFrame126("", 15261);
	    getPA().sendFrame126("", 15262);
	   getPA().sendFrame126("CLICK HERE TO PLAY", 15263);
	    getPA().sendFrame126("", 15264);
	   getPA().sendFrame126("", 15265);
	   getPA().sendFrame126("", 15266);
	   getPA().sendFrame126("", 15270);
	}
	/**
	 * Loads the brightness setting
	 */
	public void brightness() {
		switch(brightness) {
		case 1:
			getPA().sendFrame36(505, 1);
			getPA().sendFrame36(506, 0);
			getPA().sendFrame36(507, 0);
			getPA().sendFrame36(508, 0);
			getPA().sendFrame36(166, 1);
			break;
		case 2:
			getPA().sendFrame36(505, 0);
			getPA().sendFrame36(506, 1);
			getPA().sendFrame36(507, 0);
			getPA().sendFrame36(508, 0);
			getPA().sendFrame36(166,2);
			break;
		case 3:
			getPA().sendFrame36(505, 0);
			getPA().sendFrame36(506, 0);
			getPA().sendFrame36(507, 1);
			getPA().sendFrame36(508, 0);
			getPA().sendFrame36(166,3);
			break;
		case 4:
			getPA().sendFrame36(505, 0);
			getPA().sendFrame36(506, 0);
			getPA().sendFrame36(507, 0);
			getPA().sendFrame36(508, 1);
			getPA().sendFrame36(166,4);
			break;
		}
	}
	public void logout() {
	    String IP = ((InetSocketAddress) getSession().getRemoteAddress()).getAddress().getHostAddress();
	    Connection.removeIpFromLoginList(IP);
		if(System.currentTimeMillis() - logoutDelay > 10000) {
			outStream.createFrame(109);
			CycleEventHandler.getSingleton().stopEvents(this);
			properLogout = true;
			if(MimeEvent.mime != null)
			    MimeEvent.mime.deleteNPC(MimeEvent.mime);


		} else {
			sendMessage("You must wait a few seconds from being out of combat to logout.");
		}


	}
	public int packetSize = 0, packetType = -1;
	public int totalPlaytime(){
		return (pTime / 2);}
	public String getPlaytime(){
		int DAY = (totalPlaytime() / 86400);
		int HR = (totalPlaytime() / 3600) - (DAY * 24);
		int MIN = (totalPlaytime() / 60) - (DAY * 1440) - (HR * 60);
		return (DAY+" days "+HR+" hours "+MIN+" minutes");
	}


    public void updateWalkEntities() {
    	if(inWild()) {
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = (((modY - 3520) / 8) + 1);
			getPA().walkableInterface(197);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
				} else {
					getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
				}
			} else {
				getPA().multiWay(0);
				getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
			}
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inDuelArena()) {
			getPA().walkableInterface(201);
			if(duelStatus == 5) {
				getPA().showOption(3, 0, "Attack", 1);
			} else {
				getPA().showOption(3, 0, "Challenge", 1);
			}


		}
		if (!inWild()) {
			getPA().walkableInterface(-1);
			getPA().sendFrame99(0);
			if(!AttackOtherPlayers.attackOtherPlayers(this))
			getPA().showOption(3, 0, "Null", 1);
		}
    }

	public void process() {
		if(!isResting) {
			if (playerEnergy < 100 && System.currentTimeMillis() - lastIncrease >= getPA().raiseTimer()) {
				playerEnergy += 1;
				lastIncrease = System.currentTimeMillis();
			}
		}
		if(isResting) {
			if (playerEnergy < 100 && System.currentTimeMillis() - lastIncrease >= getPA().raiseTimer2()) {
				playerEnergy += 1;
				lastIncrease = System.currentTimeMillis();
			}
		}
		if(!isRunning2) {
			getPA().sendFrame36(504, 0);
			getPA().sendFrame36(173, 0);
		}
		getPA().writeEnergy();
		if(System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT) {
			specDelay = System.currentTimeMillis();
			if(specAmount < 10) {
				specAmount += .5;
				if (specAmount > 10)
					specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		}

		if(followId > 0) {
			getPA().followPlayer();
		} else if (followId2 > 0) {
			getPA().followNpc();
		}
		getCombat().handlePrayerDrain();
		if(System.currentTimeMillis() - singleCombatDelay >  3300) {
			underAttackBy = 0;
		}
		if (System.currentTimeMillis() - singleCombatDelay2 > 3300) {
			underAttackBy2 = 0;
		}

		if(System.currentTimeMillis() - restoreStatsDelay >  60000) {
			restoreStatsDelay = System.currentTimeMillis();
			for (int level = 0; level < playerLevel.length; level++)  {
				if (playerLevel[level] < getLevelForXP(playerXP[level])) {
					if(level != 5) { // prayer doesn't restore
						playerLevel[level] += 1;
						getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
						getPA().refreshSkill(level);
					}
				} else if (playerLevel[level] > getLevelForXP(playerXP[level])) {
					playerLevel[level] -= 1;
					getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
					getPA().refreshSkill(level);
				}
			}
		}

		if(!hasMultiSign && inMulti()) {
			hasMultiSign = true;
			getPA().multiWay(1);
		}

		if(hasMultiSign && !inMulti()) {
			hasMultiSign = false;
			getPA().multiWay(-1);
		}

		if(skullTimer > 0) {
			skullTimer--;
			if(skullTimer == 1) {
				isSkulled = false;
				attackedPlayers.clear();
				headIconPk = -1;
				skullTimer = -1;
				getPA().requestUpdates();
			}
		}

		if(isDead && respawnTimer == -6) {
			getPA().applyDead();
		}

		if(respawnTimer == 7) {
			respawnTimer = -6;
			getPA().giveLife();
		} else if(respawnTimer == 12) {
			respawnTimer--;
			startAnimation(0x900);
			poisonDamage = -1;
		}

		if(respawnTimer > -6) {
			respawnTimer--;
		}
		if(freezeTimer > -6) {
			freezeTimer--;
			if (frozenBy > 0) {
				if (PlayerHandler.players[frozenBy] == null) {
					freezeTimer = -1;
					frozenBy = -1;
				} else if (!goodDistance(absX, absY, PlayerHandler.players[frozenBy].absX, PlayerHandler.players[frozenBy].absY, 20)) {
					freezeTimer = -1;
					frozenBy = -1;
				}
			}
		}

		if(hitDelay > 0) {
			hitDelay--;
		}
		if(pTime != 2147000000){
			pTime++;}
		if(teleTimer > 0) {
			teleTimer--;
			if (!isDead) {
				if(teleTimer == 1 && newLocation > 0) {
					teleTimer = 0;
					getPA().changeLocation();
				}
				if(teleTimer == 5) {
					teleTimer--;
					getPA().processTeleport();
				}
				if(teleTimer == 9 && teleGfx > 0) {
					teleTimer--;
					gfx100(teleGfx);
				}
			} else {
				teleTimer = 0;
			}
		}

		if(hitDelay == 1) {
			if(oldNpcIndex > 0) {
				getCombat().delayedHit(oldNpcIndex);
			}
			if(oldPlayerIndex > 0) {
				getCombat().playerDelayedHit(oldPlayerIndex);
			}
		}

		if(attackTimer > 0) {
			attackTimer--;
		}

		if(attackTimer == 1){
			if(npcIndex > 0 && clickNpcType == 0) {
				getCombat().attackNpc(npcIndex);
			}
			if(playerIndex > 0) {
				getCombat().attackPlayer(playerIndex);
			}
		} else if (attackTimer <= 0 && (npcIndex > 0 || playerIndex > 0)) {
			if (npcIndex > 0) {
				attackTimer = 0;
				getCombat().attackNpc(npcIndex);
			} else if (playerIndex > 0) {
				attackTimer = 0;
				getCombat().attackPlayer(playerIndex);
			}
		}
		if(inTrade && tradeResetNeeded){
			Client o = (Client) PlayerHandler.players[tradeWith];
			if(o != null){
				if(o.tradeResetNeeded){
					getTradeAndDuel().resetTrade();
					o.getTradeAndDuel().resetTrade();
				}
			}
		}
	}

	public void setCurrentTask(Future<?> task) {
		currentTask = task;
	}

	public Future<?> getCurrentTask() {
		return currentTask;
	}

	public synchronized Stream getInStream() {
		return inStream;
	}

	public synchronized int getPacketType() {
		return packetType;
	}

	public synchronized int getPacketSize() {
		return packetSize;
	}

	public synchronized Stream getOutStream() {
		return outStream;
	}

	public ItemAssistant getItems() {
		return itemAssistant;
	}

	public PlayerAssistant getPA() {
		return playerAssistant;
	}

	public ShopAssistant getShops() {
		return shopAssistant;
	}

	public TradeAndDuel getTradeAndDuel() {
		return tradeAndDuel;
	}

	public CombatAssistant getCombat() {
		return combatAssistant;
	}

	public ActionHandler getActions() {
		return actionHandler;
	}




	private boolean isBusy = false;
	public boolean isBusyFollow = false;

	public boolean checkBusy() {
		/*if (getCombat().isFighting()) {
			return true;
		}*/
		if (isBusy) {
			//actionAssistant.sendMessage("You are too busy to do that.");
		}
		return isBusy;
	}


	public PlayerAssistant getPlayerAssistant() {
		return playerAssistant;
	}

	public VampireSlayer getVS() {
		return vampireslayer;
	}

	/**
	 * Skill Constructors
	 */
	public Magic getMagic() {
		return magic;
	}

	public Prayer getPrayer() {
		return prayer;
	}
	public Channel getSession() {
	    return session;
	}
	public void queueMessage(Packet arg1) {
		synchronized (queuedPackets) {
			queuedPackets.add(arg1);
		}
	}

	public boolean processQueuedPackets() {
		synchronized (queuedPackets) {
			Packet p = null;
			while ((p = queuedPackets.poll()) != null) {
				inStream.currentOffset = 0;
				packetType = p.getOpcode();
				packetSize = p.getLength();
				inStream.buffer = p.getPayload().array();
				if (packetType > 0) {
					PacketHandler.processPacket(this, packetType, packetSize);
				}
			}
		}
		return true;
	}
	private final MusicPlayer playList = new MusicPlayer(this);

	public MusicPlayer getPlayList() {
		return playList;
	}

	public Object getTemporary(String name) {
		return temporary.get(name);
	}
	private final Map<String, Object> temporary = new HashMap<String, Object>();

	public void addTemporary(String name, Object value) {
		if (name.equals("BUSY"))
			System.out.println("added: " + name);
		temporary.put(name, value);
	}
	/*/public synchronized boolean processPacket(Packet p) {
		synchronized (this) {
			if(p == null) {
				return false;
			}
			inStream.currentOffset = 0;
			packetType = p.getId();
			packetSize = p.getLength();
			inStream.buffer = p.getData();
			if(packetType > 0) {
				//sendMessage("PacketType: " + packetType);
				PacketHandler.processPacket(this, packetType, packetSize);
			}
			timeOutCounter = 0;
			return true;
		}
		/*/
	}
