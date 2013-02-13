package server.content.skills;

import core.util.Misc;
import server.Config;
import server.Server;
import server.content.quests.misc.Tutorialisland;
import server.content.skills.misc.SkillHandler;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;
import server.game.players.Client;
import server.game.players.PlayerHandler;

/**
 * Class Mining Handles Mining
 * 
 * @author 2012 20:16 22/01/2011
 */

public class Mining extends SkillHandler {
    /**
     * Resets the Event according to the ID. id = 14 (According to skill.)
     */
    private static int eventId = 14;

    public static void mineEss(final Client c, final int object) {
	if (!Config.MINING_ENABLED) {
	    c.sendMessage(c.disabled());
	    return;
	}
	if (!noInventorySpace(c, "mining")) {
	    resetMining(c);
	    return;
	}
	if (!hasPickaxe(c)) {
	    c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
	    return;
	}
	if (hasPickaxe(c) && !pickReqs(c)) {
	    c.sendMessage("Your Mining level is too low to use this pickaxe.");
	    return;
	}
	if (c.playerSkilling[14]) {
	    return;
	}

	c.playerSkilling[14] = true;
	c.stopPlayerSkill = true;

	c.startAnimation(getAnimation(c));
	c.sendMessage("You swing your pick at the rock.");
	CycleEventHandler.getSingleton().addEvent(eventId, c, new CycleEvent() {
	    @Override
	    public void execute(CycleEventContainer container) {

		c.getItems().addItem(1436, 1);
		c.sendMessage("You manage to mine some "
			+ c.getItems().getItemName(1436).toLowerCase() + ".");
		c.getPA().addSkillXP(25, c.playerMining);
		c.startAnimation(getAnimation(c));
		// resetMining(c); // makes it reset mining when ur done
		if (!hasPickaxe(c)) {
		    c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
		    resetMining(c);// resets mining
		    container.stop();
		}
		if (!c.stopPlayerSkill) {
		    resetMining(c);
		    container.stop();
		}
		if (!noInventorySpace(c, "mining")) {
		    resetMining(c);
		    container.stop();
		}
		if (Misc.random(50) <= 5) {
		    resetMining(c);
		    container.stop();
		}

	    }

	    @Override
	    public void stop() {

	    }
	}, playerMiningLevel(c) + getTime(c) + Misc.random(3));
	/*
	 * Animation Repeat!
	 */
	CycleEventHandler.getSingleton().addEvent(eventId, c, new CycleEvent() {// repeats
										// mining
										// event
		    @Override
		    public void execute(CycleEventContainer container) {
			if (c.playerSkilling[14]) {
			    c.startAnimation(getAnimation(c));
			}
			if (!c.stopPlayerSkill || !c.playerSkilling[14]) {
			    resetMining(c);
			    container.stop();
			}
		    }

		    @Override
		    public void stop() {

		    }
		}, 3);

	/*
	 * Sound
	 */
	CycleEventHandler.getSingleton().addEvent(eventId, c, new CycleEvent() { // repeats
										 // sound
		    @Override
		    public void execute(CycleEventContainer container) {
			if (c.playerSkilling[14]) {
			    c.getPA().sendSound(432, 100, 1);
			}
			if (!c.stopPlayerSkill || !c.playerSkilling[14]) {
			    resetMining(c);
			    container.stop();
			}
		    }

		    @Override
		    public void stop() {

		    }
		}, (int) 1.5);
    }

    public static void attemptData(final Client c, final int object,
	    final int obX, final int obY) {
	if (!Config.MINING_ENABLED) {
	    c.sendMessage(c.disabled());
	    return;
	}
	// /TUTORIAL ONLY
	if (c.tutorialprog == 17 || c.tutorialprog == 18) { // TUTORIAL ISALND
	    if (!hasPickaxe(c)) {
		c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
		return;
	    }
	    if (c.playerSkilling[14]) {
		return;
	    }

	    c.playerSkilling[14] = true;
	    c.stopPlayerSkill = true;

	    for (int i = 0; i < data.length; i++) {
		if (object == data[i][0]) {

		    c.playerSkillProp[14][0] = data[i][1];
		    c.playerSkillProp[14][1] = data[i][3];

		    c.startAnimation(getAnimation(c));
		    Tutorialisland.chatbox(c, 6180);
		    Tutorialisland
			    .chatboxText(
				    c,
				    "",
				    "Your character is now attempting to mine the rock.",
				    "This should only take a few seconds.", "",
				    "Please wait");
		    Tutorialisland.chatbox(c, 6179);
		    CycleEventHandler.getSingleton().addEvent(c,
			    new CycleEvent() {
				@Override
				public void execute(
					CycleEventContainer container) {

				    if (c.playerSkillProp[14][0] > 0
					    && c.tutorialprog == 17) {
					c.getItems().addItem(
						c.playerSkillProp[14][0], 1);
					c.sendMessage("You manage to mine some "
						+ c.getItems()
							.getItemName(
								c.playerSkillProp[14][0])
							.toLowerCase() + ".");
					c.getPA().createArrow(3086, 9501,
						c.getHeightLevel(), 2);
					Tutorialisland
						.chatboxText(
							c,
							"Now you have some tin ore you must need some copper ore, then",
							"you'll have all you need to create a bronze bar. As you did before",
							"riger click on the copper rock and select 'mine'.",
							"", "Mining");
					c.tutorialprog = 18;
				    } else if (c.playerSkillProp[14][0] > 0
					    && c.tutorialprog == 18) {
					c.getItems().addItem(
						c.playerSkillProp[14][0], 1);
					c.sendMessage("You manage to mine some "
						+ c.getItems()
							.getItemName(
								c.playerSkillProp[14][0])
							.toLowerCase() + ".");
					Tutorialisland.TII(c, 45, 10); // 45
								       // percent
					c.getPA().createArrow(3078, 9495, 0, 2);
					Tutorialisland.sendDialogue(c, 3061, 0);

				    }
				    if (c.playerSkillProp[14][1] > 0) {
					c.getPA().addSkillXP(
						c.playerSkillProp[14][1],
						c.playerMining);
					Server.objectHandler.createAnObject(c,
						451, obX, obY);
				    }
				    if (!hasPickaxe(c)) {
					c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
					resetMining(c);
					container.stop();
				    }
				    if (!c.stopPlayerSkill) {
					resetMining(c);
					container.stop();
				    }
				    if (!noInventorySpace(c, "mining")) {
					resetMining(c);
					container.stop();
				    }

				    resetMining(c);

				    container.stop();
				}

				@Override
				public void stop() {

				}
			    }, getTimer(c, object));
		    CycleEventHandler.getSingleton().addEvent(c,
			    new CycleEvent() {
				@Override
				public void execute(
					CycleEventContainer container) {
				    Server.objectHandler.createAnObject(c,
					    object, obX, obY);
				    container.stop();
				}

				@Override
				public void stop() {

				}
			    }, getTimer(c, object) + getRespawnTime(c, object));

		    CycleEventHandler.getSingleton().addEvent(c,
			    new CycleEvent() {
				@Override
				public void execute(
					CycleEventContainer container) {
				    if (c.playerSkilling[14]) {
					c.startAnimation(625);

				    }
				    if (!c.stopPlayerSkill
					    || !c.playerSkilling[14]) {
					resetMining(c);
					container.stop();
				    }
				}

				@Override
				public void stop() {

				}
			    }, 4);
		}
	    }
	    return;
	}
	    c.miningRock = obX + obY;
	// Normal mining
	if (!noInventorySpace(c, "mining")) {
	    resetMining(c);
	    return;
	}
	if (!hasRequiredLevel(c, 14, getLevelReq(c, object), "mining",
		"mine here")) {
	    return;
	}
	if (!hasPickaxe(c)) {
	    c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
	    return;
	}
	if (hasPickaxe(c) && !pickReqs(c)) {
	    c.sendMessage("Your Mining level is too low to use this pickaxe.");
	    return;
	}
	c.sendMessage("You swing your pick at the rock.");

	if (c.playerSkilling[14]) {
	    return;
	}

	c.playerSkilling[14] = true;
	c.stopPlayerSkill = true;

	c.startAnimation(getAnimation(c));

	for (int i = 0; i < data.length; i++) {
	    if (object == data[i][0]) {

		c.playerSkillProp[14][0] = data[i][1];
		c.playerSkillProp[14][1] = data[i][3];

		c.startAnimation(getAnimation(c));

		/*
		 * Main Mining Event.
		 */
		CycleEventHandler.getSingleton().addEvent(eventId, c,
			new CycleEvent() {
			    @Override
			    public void execute(CycleEventContainer container) {

				if (c.playerSkillProp[14][0] > 0) {
				    addRandomGem(c);
				    c.getItems().addItem(
					    c.playerSkillProp[14][0], 1);
				    c.sendMessage("You manage to mine some "
					    + c.getItems()
						    .getItemName(
							    c.playerSkillProp[14][0])
						    .toLowerCase() + ".");
				}
				if (c.playerSkillProp[14][1] > 0) {
				    c.getPA().addSkillXP(
					    c.playerSkillProp[14][1],
					    c.playerMining);
				    Server.objectHandler.createAnObject(c, 451,
					    obX, obY);
				    rockDepleted(c);
				}
				if (!hasPickaxe(c)) {
				    c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
				    resetMining(c);
				    container.stop();
				}
				if (!c.stopPlayerSkill) {
				    resetMining(c);
				    container.stop();
				}
				if (!noInventorySpace(c, "mining")) {
				    resetMining(c);
				    container.stop();
				}

				resetMining(c);

				container.stop();
			    }

			    @Override
			    public void stop() {

			    }
			}, getTimer(c, object));
		/*
		 * Object respawning
		 */
		CycleEventHandler.getSingleton().addEvent(null, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			Server.objectHandler
				.createAnObject(c, object, obX, obY);
			container.stop();
		    }

		    @Override
		    public void stop() {

		    }
		}, getTimer(c, object) + getRespawnTime(c, object));

		/*
		 * Animation Repeat!
		 */
		CycleEventHandler.getSingleton().addEvent(eventId, c,
			new CycleEvent() {// repeats mining event
			    @Override
			    public void execute(CycleEventContainer container) {
				if (c.playerSkilling[14]) {
				    c.startAnimation(getAnimation(c));
				}
				if (!c.stopPlayerSkill || !c.playerSkilling[14]) {
				    resetMining(c);
				    container.stop();
				}
			    }

			    @Override
			    public void stop() {

			    }
			}, 3);

		/*
		 * Sound
		 */
		CycleEventHandler.getSingleton().addEvent(eventId, c,
			new CycleEvent() { // repeats sound
			    @Override
			    public void execute(CycleEventContainer container) {
				if (c.playerSkilling[14]) {
				    c.getPA().sendSound(432, 100, 1);
				}
				if (!c.stopPlayerSkill || !c.playerSkilling[14]) {
				    resetMining(c);
				    container.stop();
				}
			    }

			    @Override
			    public void stop() {

			    }
			}, (int) 1.5);
	    }
	}
    }

    private static void rockDepleted(Client c) {
	for (int i = 0; i < Config.MAX_PLAYERS; i++) {
	    if (PlayerHandler.players[i] != null) {
		Client person = (Client) PlayerHandler.players[i];
		if (person != null) {
		    Client p = (Client) person;
		    if (p.distanceToPoint(c.absX, c.absY) <= 10) {
			if (c.miningRock == p.miningRock) {
			    resetMining(p);
			    p.miningRock = -1;
			}
		    }
		}
	    }
	}
    }

    private static int getTimer(Client c, int i) {
	return (getMineTime(c, i) + getTime(c) + playerMiningLevel(c));
    }

    private static final int[] RANDOM_GEM = { 1617, 1619, 1621, 1623 };

    private static void addRandomGem(Client c) {
	final int RANDOM_GEM_CHANCE = 150;
	int slots = c.getItems().freeSlots();
	int chance = Misc.random(RANDOM_GEM_CHANCE);
	int gem;
	gem = RANDOM_GEM[Misc.random(RANDOM_GEM.length - 1)];
	if (chance == 0) {
	    c.getItems().addItem(gem, 1);
	    c.sendMessage("You found an uncut gem!");
	}
	if (slots == 0 && chance == 0) {
	    c.sendMessage("You found an uncut gem!");
	    Server.itemHandler.createGroundItem(c, gem, c.absX, c.absY, 1,
		    c.playerId);
	}
    }

    private static int getMineTime(Client c, int object) {
	for (int i = 0; i < data.length; i++) {
	    if (object == data[i][0]) {
		return data[i][4];
	    }
	}
	return -1;
    }

    private static int playerMiningLevel(Client c) {
	return (10 - (int) Math.floor(c.playerLevel[14] / 10));
    }

    private static int getTime(Client c) {
	for (int i = 0; i < pickaxe.length; i++) {
	    if (c.getItems().playerHasItem(pickaxe[i][0])
		    || c.playerEquipment[3] == pickaxe[i][0]) {
		if (c.playerLevel[c.playerMining] >= pickaxe[i][1]) {
		    return pickaxe[i][2];
		}
	    }
	}
	return 10;
    }

    public static void resetMining(Client c) {
	stopEvents(c, eventId);
	c.playerSkilling[14] = false;
	for (int i = 0; i < 2; i++) {
	    c.playerSkillProp[14][i] = -1;
	}
	//c.miningRock = -1;
	c.startAnimation(65535);
    }

    public static boolean miningRocks(Client c, int object) {
	for (int i = 0; i < data.length; i++) {
	    if (object == data[i][0]) {
		return true;
	    }
	}
	return false;
    }

    private static int getRespawnTime(Client c, int object) {
	for (int i = 0; i < data.length; i++) {
	    if (object == data[i][0]) {
		return data[i][5];
	    }
	}
	return -1;
    }

    private static int getLevelReq(Client c, int object) {
	for (int i = 0; i < data.length; i++) {
	    if (object == data[i][0]) {
		return data[i][2];
	    }
	}
	return -1;
    }

    private static boolean hasPickaxe(Client c) {
	for (int i = 0; i < animation.length; i++) {
	    if (c.getItems().playerHasItem(animation[i][0])
		    || c.playerEquipment[3] == animation[i][0]) {
		return true;
	    }
	}
	return false;
    }

    private static int getAnimation(Client c) {
	for (int i = 0; i < animation.length; i++) {
	    if (c.getItems().playerHasItem(animation[i][0])
		    || c.playerEquipment[3] == animation[i][0]) {
		return animation[i][1];
	    }
	}
	return -1;
    }

    private static int[][] animation = { { 1275, 624 }, { 1271, 628 },
	    { 1273, 629 }, { 1269, 627 }, { 1267, 626 }, { 1265, 625 }, };

    private static int[][] pickaxe = { { 1275, 41, 0 }, // RUNE
	    { 1271, 31, 1 }, // ADDY
	    { 1273, 21, 2 }, // MITH
	    { 1269, 6, 3 }, // STEEL
	    { 1267, 1, 3 }, // IRON
	    { 1265, 1, 4 }, // BRONZE
    };

    private static boolean pickReqs(Client c) {
	boolean pickaxe = false;
	if (performCheck(c, 1275, 41) || performCheck(c, 1271, 31)
		|| performCheck(c, 1273, 21) || performCheck(c, 1269, 6)
		|| performCheck(c, 1267, 1) || performCheck(c, 1265, 1)) {
	    pickaxe = true;
	}
	return pickaxe;
    }

    private static boolean performCheck(Client c, int i, int l) {
	return (c.getItems().playerHasItem(i) || c.playerEquipment[3] == i)
		&& c.playerLevel[14] >= l;
    }

    private static int[][] data = { { 3042, 436, 1, 18, 1, 5 }, // COPPER
	    { 2091, 436, 1, 18, 1, 5 }, // COPPER
	    { 2090, 436, 1, 18, 1, 5 }, // COPPER
	    { 9708, 436, 1, 18, 1, 5 }, // COPPER
	    { 9709, 436, 1, 18, 1, 5 }, // COPPER
	    { 9710, 436, 1, 18, 1, 5 }, // COPPER
	    { 2094, 438, 1, 18, 1, 5 }, // TIN
	    { 3043, 438, 1, 18, 1, 5 }, // TIN TUTORIAL ISLAND
	    { 2095, 438, 1, 18, 1, 5 }, // TIN
	    { 9714, 438, 1, 18, 1, 5 }, // TIN
	    { 9716, 438, 1, 18, 1, 5 }, // TIN
	    { 9713, 434, 1, 5, 7, 5 }, // CLAY
	    { 9711, 434, 1, 5, 7, 5 }, // CLAY
	    { 2109, 434, 1, 5, 7, 5 }, // CLAY
	    { 2108, 434, 1, 5, 7, 5 }, // CLAY
	    { 2093, 440, 15, 35, 9, 8 }, // IRON
	    { 2092, 440, 15, 35, 9, 8 }, // IRON
	    { 9718, 440, 15, 35, 9, 8 }, // IRON
	    { 9717, 440, 15, 35, 9, 8 }, // IRON
	    { 9719, 440, 15, 35, 9, 8 }, // IRON
	    { 2097, 453, 30, 50, 23, 25 }, // COAL
	    { 2096, 453, 30, 50, 23, 25 }, // COAL
	    { 2098, 444, 40, 65, 22, 23 }, // GOLD
	    { 2099, 444, 40, 65, 22, 23 }, // GOLD
	    { 9720, 444, 40, 65, 22, 23 }, // GOLD
	    { 9722, 444, 40, 65, 22, 23 }, // GOLD
	    { 2103, 447, 55, 80, 38, 45 }, // MITH
	    { 2102, 447, 55, 80, 38, 45 }, // MITH
	    { 2105, 449, 70, 95, 46, 60 }, // ADDY
	    { 2104, 449, 70, 95, 48, 60 }, // ADDY
	    { 2110, 442, 20, 40, 18, 20 }, // SILVER
	    { 2101, 442, 20, 40, 18, 20 }, // SILVER
	    { 2100, 442, 20, 40, 17, 20 }, // SILVER
	    { 14860, 451, 85, 125, 40, 150 }, // RUNE
	    { 14859, 451, 85, 125, 40, 150 }, // RUNE
	    { 2106, 451, 85, 125, 40, 150 }, // RUNE
	    { 2107, 451, 85, 125, 40, 150 }, // RUNE

    };

    public static void prospectRock(final Client c, final String itemName) {
	if (c.tutorialprog == 15 || c.tutorialprog == 16) {
	    c.getPA().removeAllWindows();
	    Tutorialisland.chatbox(c, 6180);
	    Tutorialisland
		    .chatboxText(
			    c,
			    "Please wait.",
			    "Your character is now attempting to prospect the rock. This should",
			    "only take a few seconds.", "", "");
	    Tutorialisland.chatbox(c, 6179);
	    EventManager.getSingleton().addEvent(new Event() {

		@Override
		public void execute(EventContainer container) {
		    if (c.tutorialprog == 15) {
			c.sendMessage("This rock contains "
				+ itemName.toLowerCase() + ".");
			Tutorialisland.chatbox(c, 6180);
			Tutorialisland.chatboxText(
				c,
				"",
				"So now you know there's tin in the grey rocks. Try prospecting",
				"the brown ones next.", "", "It's tin");
			c.getPA()
				.createArrow(3086, 9501, c.getHeightLevel(), 2);
			Tutorialisland.chatbox(c, 6179);
			c.tutorialprog = 16;
			container.stop();
			return;
		    } else if (c.tutorialprog == 16) {
			c.sendMessage("This rock contains "
				+ itemName.toLowerCase() + ".");
			Tutorialisland.chatbox(c, 6180);
			Tutorialisland
				.chatboxText(
					c,
					"Talk to the Mining Instructor to find out about these types of",
					"ore and how you can mine them. He'll even give you the",
					"required tools.", "", "It's copper");
			c.getPA().createArrow(1, 5);
			Tutorialisland.chatbox(c, 6179);
			container.stop();
			return;
		    }
		    c.sendMessage("This rock contains "
			    + itemName.toLowerCase() + ".");
		    container.stop();
		}

	    }, 2000);
	    return;
	}
	// normal prospecting
	c.sendMessage("You examine the rock for ores...");
	EventManager.getSingleton().addEvent(new Event() {

	    @Override
	    public void execute(EventContainer container) {
		c.sendMessage("This rock contains " + itemName.toLowerCase()
			+ ".");
		container.stop();
	    }

	}, 2500);
    }

    public static void prospectNothing(final Client c) {
	c.sendMessage("You examine the rock for ores...");
	EventManager.getSingleton().addEvent(new Event() {

	    @Override
	    public void execute(EventContainer container) {
		c.sendMessage("There is no ore left in this rock.");
		container.stop();
	    }

	}, 1000);
    }

}
