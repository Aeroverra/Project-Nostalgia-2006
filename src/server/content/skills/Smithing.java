package server.content.skills;

import core.util.Misc;
import server.Config;
import server.content.quests.misc.Tutorialisland;
import server.content.skills.misc.SkillHandler;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * 
 * @author Smelting credits to abyss, Smithing credits to somedude
 * 
 */
public class Smithing extends SmithingData {

	private static int anim = 898, hammer = 2347, eventId = 13;

	/**
	 * Starts Smithing. It is called in many classes to start smithing. Sets the
	 * ints etc.
	 * 
	 * @param c
	 * @param itemId
	 * @param amount
	 */
	public static void smithing(final Client c, final int itemId, int amount) {
		/*
		 * Checks if Smithing is enabled or not.
		 */
		if(!Config.SMITHING_ENABLED) {
			c.sendMessage(c.disabled());
			return;
		}
		
		/*
		 * Doamount meaning the amount of an item to be smithed.
		 */
		c.doAmount = amount;
		
		/*
		 * Loops through the data enum.
		 */
		for (final data s : data.values()) {
			if (itemId == s.getItemId(itemId)) {
				Smithbar(c, itemId, s.getReqBar(), s.getBarAmount(itemId),
						s.getLvlReq(itemId), s.getXp(itemId));
			}
		}
	}

	/**
	 * Main method.
	 * 
	 * @param c
	 * @param itemId
	 * @param level
	 *            - level requirements
	 * @param amount
	 */
	public static void Smithbar(final Client c, final int itemId,
			final int reqbar, final int reqbaramount, final int level,
			final int XP) {
		/*
		 * If player is already smithing, return.
		 */
		if (c.playerSkilling[13] == true) {
			return;
		}
		
		/*
		 * Checks if the player has a hammer.
		 */
		if (!c.getItems().playerHasItem(hammer, 1)) {
			c.sendMessage("You need a hammer to smith on an anvil.");
			return;
		}
		/*
		 * Checks if the player has the right level requirements.
		 */
		if (c.playerLevel[13] < level) {
			c.sendMessage("You need a smithing level of " + level
					+ " in order to make a "
					+ c.getItems().getItemName(itemId).toLowerCase() + ".");
			c.getPA().removeAllWindows();
			return;
		}
		/*
		 * Checks if the player has the required amount of bars to smith
		 * a item.
		 */
		if (!c.getItems().playerHasItem(reqbar, reqbaramount)) {
			resetSmithing(c);
			c.sendMessage("You need at least " + reqbaramount
					+ " bars to make a "
					+ c.getItems().getItemName(itemId).toLowerCase() + ".");
			return;
		}

		c.playerSkilling[13] = true;
		c.stopPlayerSkill = true;
		c.getPA().removeAllWindows();
		c.startAnimation(anim);
		c.getPA().sendSound(468, 100, 1);
		if(c.tutorialprog == 20){
		    Tutorialisland.sendDialogue(c, 3066, 0);
		}
		
		if(c.playerRights >= 2)
		c.sendMessage("@blu@[DEBUG] " + "Item - " + itemId + " - Level - "
				+ level + " - bars - " + reqbaramount + " - XP - " + XP
				+ "- Final XP " + XP * c.doAmount * 10);

		CycleEventHandler.getSingleton().addEvent(eventId, c, new CycleEvent() {
			@Override
			/*
			 * Main cycle event.
			 * @see server.event.CycleEvent#execute(server.event.CycleEventContainer)
			 */
			public void execute(CycleEventContainer container) {
				if (!c.playerSkilling[13] || c.doAmount == 0
						|| !c.stopPlayerSkill) {
					resetSmithing(c);
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(reqbar, reqbaramount)) {
					resetSmithing(c);
					container.stop();
					return;
				}
				c.getPA()
						.addSkillXP(XP, c.playerSmithing);
				c.sendMessage(check(c, itemId)
						+ c.getItems().getItemName(itemId).toLowerCase() + ".");
				c.getItems().deleteItem2(reqbar, reqbaramount);
				c.getItems().addItem(itemId, 1);
				SkillHandler.deleteTime(c);// deletes the amount todo
			}

			@Override
			public void stop() {

			}
		}, (int) 5.8);
		CycleEventHandler.getSingleton().addEvent(eventId, c, new CycleEvent() {
			@Override
			// animation/Sound
			public void execute(CycleEventContainer container) {
				if (!c.playerSkilling[13] || c.doAmount == 0
						|| !c.stopPlayerSkill) {
					resetSmithing(c);
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(hammer, 1)) {
					c.sendMessage("You need a hammer to smith on an anvil.");
					return;
				}
				if (!c.getItems().playerHasItem(reqbar, reqbaramount)) {
					resetSmithing(c);
					container.stop();
					return;
				}
				c.startAnimation(anim);
				c.getPA().sendSound(468, 100, 1);
			}

			@Override
			public void stop() {

			}
		}, 5);
	}

	/************************ SMELTING *******************************/

	// Made by abyss, Merged by somedude
	private static int COPPER = 436, TIN = 438, IRON = 440, COAL = 453,
			MITH = 447, ADDY = 449, RUNE = 451, GOLD = 444, SILVER = 442;
	private final static int[] SMELT_FRAME = { 2405, 2406, 2407, 2409, 2410,
			2411, 2412, 2413 };
	private final static int[] SMELT_BARS = { 2349, 2351, 2355, 2353, 2357,
			2359, 2361, 2363 };

	/**
	 * Handles Smelting data.
	 */
	public static int[][] data1 = {
			// index ,lvl required, XP, item required, item2, COAL AMOUNT,
			// Final item(BAR)
			{ 1, 1, 6, COPPER, TIN, -1, 2349 }, // Bronze
			{ 2, 15, 12, IRON, -1, -1, 2351 }, // iron
			{ 3, 20, 17, IRON, COAL, 2, 2353 }, // Steel
			{ 4, 50, 30, MITH, COAL, 4, 2359 }, // Mith
			{ 5, 70, 37, ADDY, COAL, 6, 2361 }, // Addy
			{ 6, 85, 50, RUNE, COAL, 8, 2363 }, // Rune
			/** ---------------OTHERS------------/ */
			{ 7, 20, 13, SILVER, -1, -1, 2355 }, // Silver
			{ 8, 40, 22, GOLD, -1, -1, 2357 } // GOLD
	};

	/**
	 * Sends the interface
	 * 
	 * @param c
	 */
	public static void startSmelting(Client c, int object) {
		if(!Config.SMITHING_ENABLED) {
			c.sendMessage(c.disabled());
			return;
		}
		for (int j = 0; j < SMELT_FRAME.length; j++) {
			c.getPA().sendFrame246(SMELT_FRAME[j], 150, SMELT_BARS[j]);
		}
		c.getPA().sendFrame164(2400);
		c.playerisSmelting = true;
	}

	/**
	 * Sets the amount of bars that can be smelted. (EG. 5,10,28 times)
	 * 
	 * @param c
	 * @param amount
	 */
	public static void doAmount(Client c, int amount, int bartype) {
		c.doAmount = amount;
		smeltBar(c, bartype);
	}

	/**
	 * Main method. Smelting
	 * 
	 * @param c
	 */
	private static void smeltBar(final Client c, final int bartype) {
		for (int i = 0; i < data1.length; i++) {
			if (bartype == data1[i][0]) {
			    if (c.playerLevel[c.playerSmithing] < data1[i][1]) {
					DialogueHandler
							.sendStatement(c,
									"You need a smithing level of at least "
											+ data1[i][1]
											+ " in order smelt this bar.");
					return;
				}

				if (data1[i][3] > 0 && data1[i][4] > 0) { // All OTHER bars
					if (!c.getItems().playerHasItem(data1[i][3])
							|| !c.getItems().playerHasItem(data1[i][4])) {
						c.sendMessage("You need an "
								+ c.getItems().getItemName(data1[i][3])
										.toLowerCase()
								+ " and "
								+ data1[i][5]
								+ " "
								+ c.getItems().getItemName(data1[i][4])
										.toLowerCase() + " to make this bar.");

						c.getPA().removeAllWindows();
						return;
					}
				}

				if (data1[i][4] < 0) { // Iron bar, Gold & Silver requirements
					if (!c.getItems().playerHasItem(data1[i][3])) {
						c.sendMessage("You need an "
								+ c.getItems().getItemName(data1[i][3])
										.toLowerCase() + " to make this bar.");
						c.getPA().removeAllWindows();
						return;
					}
				}

				if (data1[i][5] > 0) { // Bars with more than 1 coal requirement
					if (!c.getItems().playerHasItem(data1[i][4], data1[i][5])) {
						c.sendMessage("You need an "
								+ c.getItems().getItemName(data1[i][3])
										.toLowerCase() + " and " + data1[i][5]
								+ " coal ores to make this bar.");
						c.getPA().removeAllWindows();
						return;
					}
				}

				if (c.playerSkilling[13]) {
					return;
				}

				c.playerSkilling[13] = true;
				c.stopPlayerSkill = true;

				c.playerSkillProp[13][0] = data1[i][0];// index
				c.playerSkillProp[13][1] = data1[i][1];// Level required
				c.playerSkillProp[13][2] = data1[i][2];// XP
				c.playerSkillProp[13][3] = data1[i][3];// item required
				c.playerSkillProp[13][4] = data1[i][4];// item required 2
				c.playerSkillProp[13][5] = data1[i][5];// coal amount
				c.playerSkillProp[13][6] = data1[i][6];// Final Item

				c.getPA().removeAllWindows();
				c.startAnimation(899);
				c.getPA().sendSound(352, 100, 1);
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						SkillHandler.deleteTime(c);
						if(Misc.random(2) == 0 && c.playerSkillProp[13][0] == 2) {
						    c.sendMessage("You have failed to refine this ore.");
						    c.getItems().deleteItem(c.playerSkillProp[13][3], 1);
						    if(c.doAmount <= 0) {
						    resetSmithing(c);
							container.stop();
						    }
						    return;
						}
						c.getItems().deleteItem(c.playerSkillProp[13][3], 1);
						if (c.playerSkillProp[13][5] == -1) {
							c.getItems()
									.deleteItem(c.playerSkillProp[13][4], 1);
						}
						if (c.playerSkillProp[13][5] > 0) {// if coal amount is
															// >0
							c.getItems().deleteItem2(c.playerSkillProp[13][4],
									c.playerSkillProp[13][5]);
						}

						c.sendMessage("You receive an " // Message
								+ c.getItems()
										.getItemName(c.playerSkillProp[13][6])
										.toLowerCase() + ".");
						
						    if (c.tutorialprog == 19) {
							Tutorialisland.sendDialogue(c, 3062, 0);
							}

						/*
						 * / if(bartype == 2 && Misc.random(2) == 0) {
						 * c.getPA().addSkillXP(c.playerSkillProp[13][2] // XP
						 * Config.SMITHING_EXPERIENCE, 13);
						 * 
						 * c.getItems().addItem(c.playerSkillProp[13][6],
						 * 1);//item return; } /
						 */
						c.getPA().addSkillXP(c.playerSkillProp[13][2] // XP
								, 13);

						c.getItems().addItem(c.playerSkillProp[13][6], 1);// item
						
						

						// ///////////////////////////////CHECKING//////////////////////
						if (!c.getItems().playerHasItem(
								c.playerSkillProp[13][3], 1)) {
							c.sendMessage("You don't have enough ores to continue smelting!");
							resetSmithing(c);
							container.stop();
						}
						if (c.playerSkillProp[13][4] > 0) {
							if (!c.getItems().playerHasItem(
									c.playerSkillProp[13][4], 1)) {
								c.sendMessage("You don't have enough ores to continue smelting!");
								resetSmithing(c);
								container.stop();
							}
						}
						if (c.doAmount <= 0) {
							resetSmithing(c);
							container.stop();
						}
						if (!c.playerSkilling[13]) {
							resetSmithing(c);
							container.stop();
						}
						if (!c.stopPlayerSkill) {
							resetSmithing(c);
							container.stop();
						}

					}

					@Override
					public void stop() {

					}
				}, (int) 5.9);

				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {// animation
							@Override
							public void execute(CycleEventContainer container) {
								if (!c.playerSkilling[13]) {
									resetSmithing(c);
									container.stop();
									return;
								}
								c.startAnimation(899);
								c.getPA().sendSound(352, 100, 1);
							if (!c.stopPlayerSkill) {
									container.stop();
								}
							}

							@Override
							public void stop() {

							}
						}, (int) 5.8);

			}
		}
	}

	/**
	 * Gets the index from DATA for which bar to smelt
	 */
	public static void getBar(Client c, int i) {
		switch (i) {
		case 15147: // bronze (1)
			doAmount(c, 1, 1);// (c,amount,Index in data)
			break;
		case 15146: // bronze (5)
			doAmount(c, 5, 1);
			break;
		case 10247: // bronze (10)
			doAmount(c, 10, 1);
			break;
		case 9110:// bronze (X)
			doAmount(c, 28, 1);
			break;

		case 15151: // iron (1)
			doAmount(c, 1, 2);
			break;
		case 15150: // iron (5)
			doAmount(c, 5, 2);
			break;
		case 15149: // iron (10)
			doAmount(c, 10, 2);
			break;
		case 15148:// Iron (X)
			doAmount(c, 28, 2);
			break;

		case 15159: // Steel (1)
			doAmount(c, 1, 3);
			break;
		case 15158: // Steel (5)
			doAmount(c, 5, 3);
			break;
		case 15157: // Steel (10)
			doAmount(c, 10, 3);
			break;
		case 15156:// Steel (X)
			doAmount(c, 28, 3);
			break;

		case 29017: // mith (1)
			doAmount(c, 1, 4);
			break;
		case 29016: // mith (5)
			doAmount(c, 5, 4);
			break;
		case 24253: // mith (10)
			doAmount(c, 10, 4);
			break;
		case 16062:// Mith (X)
			doAmount(c, 28, 4);
			break;

		case 29022: // Addy (1)
			doAmount(c, 1, 5);
			break;
		case 29020: // Addy (5)
			doAmount(c, 5, 5);
			break;
		case 29019: // Addy (10)
			doAmount(c, 10, 5);
			break;
		case 29018:// Addy (X)
			doAmount(c, 28, 5);
			break;

		case 29026: // RUNE (1)
			doAmount(c, 1, 6);
			break;
		case 29025: // RUNE (5)
			doAmount(c, 5, 6);
			break;
		case 29024: // RUNE (10)
			doAmount(c, 10, 6);
			break;
		case 29023:// Rune (X)
			doAmount(c, 28, 6);
			break;

		case 15155:// SILVER (1)
			doAmount(c, 1, 7);
			break;
		case 15154:// SILVER (5)
			doAmount(c, 5, 7);
			break;
		case 15153:// SILVER (10)
			doAmount(c, 10, 7);
			break;
		case 15152:// SILVER (28)
			doAmount(c, 28, 7);
			break;

		case 15163:// Gold (1)
			doAmount(c, 1, 8);
			break;
		case 15162:// Gold (5)
			doAmount(c, 5, 8);
			break;
		case 15161:// Gold (10)
			doAmount(c, 10, 8);
			break;
		case 15160:// Gold (28)
			doAmount(c, 28, 8);
			break;

		}

	}

	/**
	 * Reset Smithing.
	 * 
	 * @param c
	 */
	public static void resetSmithing(Client c) {
		c.startAnimation(65535);
		c.playerSkilling[13] = false;
		c.stopPlayerSkill = false;
		c.playerisSmelting = false;
		c.doAmount = 0;
		SkillHandler.stopEvents(c, eventId);// STOPS SMITHING EVENT.
		for (int i = 0; i < 7; i++) {
			c.playerSkillProp[13][i] = -1;
		}
	}
}
