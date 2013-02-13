package server.content.skills;

import server.Config;
import server.game.players.Client;

/**
 * RuneCrafting.java
 *
 * @ somedude. Cleaned and now everything is static
 * 
 **/

public class Runecrafting {

	/**
	 * Rune essence ID constant.
	 */
	private static final int RUNE_ESS = 1436;

	/**
	 * Pure essence ID constant.
	 */
	private static final int PURE_ESS = 7936;
	
	/**
	 * An array containing the rune item numbers.
	 */
	public static int[] runes = {
			556, 558, 555, 557, 554, 559, 564, 562, 561, 563, 560, 565
	};

	private enum Altars {
		AIR_ALTAR(2452, new int[] {1438, 5527}, new int[] {2842, 4829}),
		MIND_ALTAR(2453, new int[] {1448, 5529}, new int[] {2793, 4828}),
		WATER_ALTAR(2454, new int[] {1444, 5531}, new int[] {2713, 4836}),
		EARTH_ALTAR(2455, new int[] {1440, 5535}, new int[] {2655, 4831}),
		FIRE_ALTAR(2456, new int[] {1442, 5537}, new int[] {2577, 4845}),
		BODY_ALTAR(2457, new int[] {1446, 5533}, new int[] {2521, 4834}),
		COSMIC_ALTAR(2458, new int[] {1454, 5539}, new int[] {2162, 4833 }),
		CHAOS_ALTAR(2461, new int[] {1452, 5543}, new int[] {2268, 4842}),
		NATURE_ALTAR(2460, new int[] {1462, 5541}, new int[] {2400, 4835}),
		LAW_ALTAR(2459, new int[] {1458, 5545}, new int[] {2464, 4819}),
		DEATH_ALTAR(2462, new int[] {1456, 5547}, new int[] {2208, 4831});


		int objId;
		int[] keys, loc;

		private Altars(int objId, int[] keys, int[] loc) {
			this.objId = objId;
			this.loc = loc;
			this.keys = keys;
		}

		private int getObj() {
			return objId;
		}

		private int[] getKeys() {
			return keys;
		}

		private int[] getNewLoc() {
			return loc;
		}
	}

	public static Altars forAltar(int id) {
		for (Altars a : Altars.values()) {
			if (a.getObj() == id) {
				return a;
			}
		}
		return null;
	}

	public static void enterAltar(Client c, int objId, int itemUse) {
		if(!Config.RUNECRAFTING_ENABLED) {
			c.sendMessage(c.disabled());
			return;
		}
		c.lastX = c.getX();
		c.lastY = c.getY();
		Altars a = forAltar(objId);
		if (a != null) {
			if (a.getKeys()[1] == c.playerEquipment[c.playerWeapon] || a.getKeys()[0] == itemUse) {
			c.getPA().movePlayer(a.getNewLoc()[0], a.getNewLoc()[1], 0);
			c.sendMessage("You enter the mysterious ruins.");
			} else {
				c.sendMessage("Nothing interesting happens.");
			}
		}
	}
	
	public static void exitAltar(Client c) {
		c.getPA().movePlayer(c.lastX, c.lastY, c.heightLevel);
		c.lastX = 0;
		c.lastY = 0;
	}
	
	/**
	 * An array containing the object IDs of the runecrafting altars.
	 */
	public static int[] altarID = {
			2478, 2479, 2480, 2481, 2482, 2483, 2484, 2487, 2486, 2485, 2488, 2489
	};

	/**
	 * 2D Array containing the levels required to craft the specific rune.
	 */
	public static int[][] craftLevelReq = {
			{556, 1}, {558, 2}, {555, 5}, {557, 9}, {554, 14}, {559, 20},
			{564, 27}, {562, 35}, {561, 44}, {563, 54}, {560, 65}, {565, 77}
	};

	/**
	 * 2D Array containing the levels that you can craft multiple runes.
	 */
	public static int[][] multipleRunes = {
			{11, 22, 33, 44, 55, 66, 77, 88, 99},
			{14, 28, 42, 56, 70, 84, 98},
			{19, 38, 57, 76, 95},
			{26, 52, 78},
			{35, 70},
			{46, 92},
			{59},
			{74},
			{91},
			{100},
			{100},
			{100}
	};

	public static int[] runecraftExp = {
			5, 6, 6, 7, 7, 8, 9, 9, 10, 11, 11, 11
	};
	/**
	 * Checks through all 28 item inventory slots for the specified item.
	 */
	private static boolean itemInInv(Client c,int itemID, int slot, boolean checkWholeInv) {
		if (checkWholeInv) {
			for (int i = 0; i < 28; i++) {
				if (c.playerItems[i] == itemID + 1) {
					return true;
				}
			}
		} else {
			if (c.playerItems[slot] == itemID + 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Replaces essence in the inventory with the specified rune.
	 */
	private static void replaceEssence(Client c,int essType, int runeID, int multiplier, int index) {
		System.out.println("multipler: " + multiplier);
		int exp = 0;
		for (int i = 0; i < 28; i++) {
			if (itemInInv(c,essType, i, false)) {
				c.getItems().deleteItem(essType, i, 1);
				c.getItems().addItem(runeID, 1 * multiplier);
				exp += runecraftExp[index];
			}
		}
		c.getPA().addSkillXP(exp, c.playerRunecrafting);
	}
	/**
	 * Handles tiara wearing
	 */
    public static void handleTiara(Client c) {
    	int[] tiaras = {5527, 5529, 5531, 5535, 5537, 5533, 5539, 5543, 5541, 5545, 5547};
    	if (c.wearId >= tiaras[0] && c.wearId <= tiaras[10]) {
    		for (int i = 0; i < tiaras.length; i++) {
    			if (c.wearId == tiaras[i]) {
    				int tempInt = 1;
    				int loc = i;
    				while (loc > 0) {
    					tempInt *= 2;
    					loc--;
    				}
    				c.getPA().setConfig(491, tempInt);
    			}
    		}
    	}
    }
    /**
     * Locates where the altar is.
     * 
     */
    public static void locate(Client c, int xPos, int yPos) {
		String X = "";
		String Y = "";
		if (c.absX >= xPos) {
			X = "west";
		}
		if (c.absY > yPos) {
			Y = "south";
		}
		if (c.absX < xPos) {
			X = "east";
		}
		if (c.absY <= yPos) {
			Y = "north";
		}
		c.sendMessage("The talisman pulls towards the "+Y+"-"+X+".");
	}
    
	/**
	 * Crafts the specific rune.
	 */
	public static void craftRunes(Client c,int altarId) {
		int runeID = 0;

		for (int i = 0; i < altarID.length; i++) {
			if (altarId == altarID[i]) {
				runeID = runes[i];
			}
		}
		for (int i = 0; i < craftLevelReq.length; i++) {
			if (runeID == runes[i]) {
				if (c.playerLevel[20] >= craftLevelReq[i][1]) {
					if (c.getItems().playerHasItem(RUNE_ESS) || c.getItems().playerHasItem(PURE_ESS)) {
						int multiplier = 1;
						for (int j = 0; j < multipleRunes[i].length; j++) {
							if (c.playerLevel[20] >= multipleRunes[i][j]) {
								multiplier += 1;
							}
						}
						replaceEssence(c,RUNE_ESS, runeID, multiplier, i);
						c.startAnimation(791);
						c.getPA().sendSound(481, 100, 1);
						c.gfx100(186);
						return;
					}
					c.sendMessage("You need to have essence to craft runes!");
					return;
				}
				c.sendMessage("You need a higher runecrafting level to craft this rune.");
			}
		}
	}

}