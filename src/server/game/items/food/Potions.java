package server.game.items.food;

import server.game.players.Client;

/**
 * 
 * Potions.java
 * @author Izumi
 * 
 */

public class Potions {


	public static void handlePotion(Client c,int itemId, int slot) {
		if (c.duelRule[5]) {
			c.sendMessage("You may not drink potions in this duel.");
			return;
		}
		if (System.currentTimeMillis() - c.potDelay >= 1200) {
			c.potDelay = System.currentTimeMillis();
			c.foodDelay = c.potDelay;
			c.getCombat().resetPlayerAttack();
			c.attackTimer++;
			c.getPA().sound(334);
			c.sendMessage("You drink some of your "+ server.game.items.Item.getItemName(itemId) +".");
			String item =  server.game.items.Item.getItemName(itemId);
			if(item.endsWith("(4)"))
			{
				c.sendMessage("You have 3 doses of potion left.");
			} else if(item.endsWith("(3)"))
			{
				c.sendMessage("You have 2 doses of potion left.");
			} else if(item.endsWith("(2)"))
			{
				c.sendMessage("You have 1 dose of potion left.");
			} else if(item.endsWith("(1)"))
			{
				c.sendMessage("You have finished your potion.");
			}
			switch (itemId) {
			//Magic pots
			case 3040:
				drinkMagicPotion(c,itemId,3042,slot,6,false);
				break;
			case 3042:
				drinkMagicPotion(c,itemId,3044,slot,6,false);
				break;
			case 3044:
				drinkMagicPotion(c,itemId,3046,slot,6,false);
				break;
			case 3046:
				drinkMagicPotion(c,itemId,229,slot,6,false);
				break;
			case 6685:	//brews
				doTheBrew(c,itemId, 6687, slot);
				break;
			case 6687:
				doTheBrew(c,itemId, 6689, slot);
				break;
			case 6689:
				doTheBrew(c,itemId, 6691, slot);
				break;
			case 6691:
				doTheBrew(c,itemId, 229, slot);
				break;
			case 2436:
				drinkStatPotion(c,itemId,145,slot,0,true); //sup attack
				break;
			case 145:
				drinkStatPotion(c,itemId,147,slot,0,true);
				break;
			case 147:
				drinkStatPotion(c,itemId,149,slot,0,true);
				break;
			case 149:
				drinkStatPotion(c,itemId,229,slot,0,true);
				break;
			case 2440:
				drinkStatPotion(c,itemId,157,slot,2,true); //sup str
				break;
			case 157:
				drinkStatPotion(c,itemId,159,slot,2,true);
				break;
			case 159:
				drinkStatPotion(c,itemId,161,slot,2,true);
				break;
			case 161:
				drinkStatPotion(c,itemId,229,slot,2,true);
				break;
			case 2444:
				drinkStatPotion(c,itemId,169,slot,4,false); //range pot
				break;
			case 169:
				drinkStatPotion(c,itemId,171,slot,4,false);
				break;
			case 171:
				drinkStatPotion(c,itemId,173,slot,4,false);
				break;
			case 173:
				drinkStatPotion(c,itemId,229,slot,4,false);
				break;
			case 2432:
				drinkStatPotion(c,itemId,133,slot,1,false); //def pot
				break;
			case 133:
				drinkStatPotion(c,itemId,135,slot,1,false);
				break;
			case 135:
				drinkStatPotion(c,itemId,137,slot,1,false);
				break;
			case 137:
				drinkStatPotion(c,itemId,229,slot,1,false);
				break;
			case 113:
				drinkStatPotion(c,itemId,115,slot,2,false); //str pot
				break;
			case 115:
				drinkStatPotion(c,itemId,117,slot,2,false);
				break;
			case 117:
				drinkStatPotion(c,itemId,119,slot,2,false);
				break;
			case 119:
				drinkStatPotion(c,itemId,229,slot,2,false);
				break;
			case 2428:
				drinkStatPotion(c,itemId,121,slot,0,false); //attack pot
				break;
			case 121:
				drinkStatPotion(c,itemId,123,slot,0,false);
				break;
			case 123:
				drinkStatPotion(c,itemId,125,slot,0,false);
				break;
			case 125:
				drinkStatPotion(c,itemId,229,slot,0,false);
				break;
			case 2442:
				drinkStatPotion(c,itemId,163,slot,1,true); //super def pot
				break;
			case 163:
				drinkStatPotion(c,itemId,165,slot,1,true);
				break;
			case 165:
				drinkStatPotion(c,itemId,167,slot,1,true);
				break;
			case 167:
				drinkStatPotion(c,itemId,229,slot,1,true);
				break;
			case 3024:
				drinkPrayerPot(c,itemId,3026,slot,true); //sup restore
				break;
			case 3026:
				drinkPrayerPot(c,itemId,3028,slot,true);
				break;
			case 3028:
				drinkPrayerPot(c,itemId,3030,slot,true);
				break;
			case 3030:
				drinkPrayerPot(c,itemId,229,slot,true);
				break;
			case 10925:
				drinkPrayerPot(c,itemId,10927,slot,true); //sanfew serums
				curePoison(c,300000);
				break;
			case 10927:
				drinkPrayerPot(c,itemId,10929,slot,true);
				curePoison(c,300000);
				break;
			case 10929:
				drinkPrayerPot(c,itemId,10931,slot,true);
				curePoison(c,300000);
				break;
			case 10931:
				drinkPrayerPot(c,itemId,229,slot,true);
				curePoison(c,300000);
				break;
			case 2434:
				drinkPrayerPot(c,itemId,139,slot,false); //pray pot
				break;
			case 139:
				drinkPrayerPot(c,itemId,141,slot,false);
				break;
			case 141:
				drinkPrayerPot(c,itemId,143,slot,false);
				break;
			case 143:
				drinkPrayerPot(c,itemId,229,slot,false);
				break;
			case 2446:
				drinkAntiPoison(c,itemId,175,slot,30000); //anti poisons
				break;
			case 175:
				drinkAntiPoison(c,itemId,177,slot,30000);
				break;
			case 177:
				drinkAntiPoison(c,itemId,179,slot,30000);
				break;
			case 179:
				drinkAntiPoison(c,itemId,229,slot,30000);
				break;
			case 2448:
				drinkAntiPoison(c,itemId,181,slot,300000); //anti poisons
				break;
			case 181:
				drinkAntiPoison(c,itemId,183,slot,300000);
				break;
			case 183:
				drinkAntiPoison(c,itemId,185,slot,300000);
				break;
			case 185:
				drinkAntiPoison(c,itemId,229,slot,300000);
				break;
			}
		}
	}

	public static void drinkAntiPoison(Client c,int itemId, int replaceItem, int slot, long delay) {
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		curePoison(c,delay);
	}

	public static void curePoison(Client c, long delay) {
		c.poisonDamage = 0;
		c.poisonImmune = delay;
		c.lastPoisonSip = System.currentTimeMillis();
	}

	public static void drinkStatPotion(Client c, int itemId, int replaceItem, int slot, int stat, boolean sup) {
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		enchanceStat(c,stat,sup);
	}

	public static void drinkMagicPotion(Client c,int itemId, int replaceItem, int slot, int stat, boolean sup) {
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		enchanceMagic(c,stat,sup);

	}

	public static void enchanceMagic(Client c, int skillID, boolean sup) {
		c.playerLevel[skillID] += getBoostedMagic(c,skillID, sup);
		c.getPA().refreshSkill(skillID);
	}

	public static int getBoostedMagic(Client c, int skill, boolean sup) {
		int increaseBy = 0;
		if (sup)
			increaseBy = (int)(c.getLevelForXP(c.playerXP[skill])*.06);
		else
			increaseBy = (int)(c.getLevelForXP(c.playerXP[skill])*.06);
		if (c.playerLevel[skill] + increaseBy > c.getLevelForXP(c.playerXP[skill]) + increaseBy + 1) {
			return c.getLevelForXP(c.playerXP[skill]) + increaseBy - c.playerLevel[skill];
		}
		return increaseBy;
	}

	public static void drinkPrayerPot(Client c,int itemId, int replaceItem, int slot, boolean rest) {
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		c.playerLevel[5] += (c.getLevelForXP(c.playerXP[5]) * .33);
		if (rest)
			c.playerLevel[5] += 1;
		if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
		c.getPA().refreshSkill(5);
		if (rest)
			restoreStats(c);
	}

	public static void restoreStats(Client c) {
		for (int j = 0; j <= 6; j++) {
			if (j == 5 || j == 3)
				continue;
			if (c.playerLevel[j] < c.getLevelForXP(c.playerXP[j])) {
				c.playerLevel[j] += (c.getLevelForXP(c.playerXP[j]) * .33);
				if (c.playerLevel[j] > c.getLevelForXP(c.playerXP[j])) {
					c.playerLevel[j] = c.getLevelForXP(c.playerXP[j]);
				}
				c.getPA().refreshSkill(j);
				c.getPA().setSkillLevel(j, c.playerLevel[j], c.playerXP[j]);
			}
		}
	}

	public static void doTheBrew(Client c,int itemId, int replaceItem, int slot) {
		if (c.duelRule[6]) {
			c.sendMessage("You may not eat in this duel.");
			return;
		}
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		int[] toDecrease = {0,2,4,6};

		for (int tD : toDecrease) {
			c.playerLevel[tD] -= getBrewStat(c,tD, .10);
			if (c.playerLevel[tD] < 0)
				c.playerLevel[tD] = 1;
			c.getPA().refreshSkill(tD);
			c.getPA().setSkillLevel(tD, c.playerLevel[tD], c.playerXP[tD]);
		}
		c.playerLevel[1] += getBrewStat(c,1, .20);
		if (c.playerLevel[1] > (c.getLevelForXP(c.playerXP[1])*1.2 + 1)) {
			c.playerLevel[1] = (int)(c.getLevelForXP(c.playerXP[1])*1.2);
		}
		c.getPA().refreshSkill(1);

		c.playerLevel[3] += getBrewStat(c,3, .15);
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.17 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.17);
		}
		c.getPA().refreshSkill(3);
	}

	public static void enchanceStat(Client c,int skillID, boolean sup) {
		c.playerLevel[skillID] += getBoostedStat(c,skillID, sup);
		c.getPA().refreshSkill(skillID);
	}

	public static int getBrewStat(Client c,int skill, double amount) {
		return (int)(c.getLevelForXP(c.playerXP[skill]) * amount);
	}

	public static int getBoostedStat(Client c,int skill, boolean sup) {
		int increaseBy = 0;
		if (sup)
			increaseBy = (int)(c.getLevelForXP(c.playerXP[skill])*.20);
		else
			increaseBy = (int)(c.getLevelForXP(c.playerXP[skill])*.13) + 1;
		if (c.playerLevel[skill] + increaseBy > c.getLevelForXP(c.playerXP[skill]) + increaseBy + 1) {
			return c.getLevelForXP(c.playerXP[skill]) + increaseBy - c.playerLevel[skill];
		}
		return increaseBy;
	}

	public static boolean isPotion(Client c,int itemId) {
		String name = c.getItems().getItemName(itemId);
		return name.contains("(4)") || name.contains("(3)") || name.contains("(2)") || name.contains("(1)");
	}
	public static boolean potionNames(Client c,int itemId) {
		String name = c.getItems().getItemName(itemId);
		return name.endsWith("potion(4)") || name.endsWith("potion(3)") || name.endsWith("potion(2)") || name.endsWith("potion(1)")
				|| name.contains("saradomin brew") || name.contains("zamorak brew");
	}
}