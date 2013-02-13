package server.content.randoms;

import server.Server;
import server.game.players.Client;
import server.game.players.DialogueHandler;
import core.util.Misc;

/**
 * @author somedude
 * 
 */
public class Genie {
	public static int lamp = 2528, genie = 409, skillmenu = 2808, skill = -1;

	/**
	 * Handles the actionbuttons
	 * 
	 * @param c
	 * @param id
	 */
	public static void buttons(Client c, int id) {
		switch (id) {
		case 10252:
			skill = 0;
			c.sendMessage("You select Attack.");
			break;
		case 10253:
			skill = 2;
			c.sendMessage("You select Strength.");
			break;
		case 10254:
			skill = 4;
			c.sendMessage("You select Ranged.");
			break;
		case 10255:
			skill = 6;
			c.sendMessage("You select Magic.");
			break;
		case 11000:
			skill = 1;
			c.sendMessage("You select Defence.");
			break;
		case 11001:
			skill = 3;
			c.sendMessage("You select Hitpoints.");
			break;
		case 11002:
			skill = 5;
			c.sendMessage("You select Prayer.");
			break;
		case 11003:
			c.membersonly();
			skill = -1;
			break;
		case 11004:
			c.membersonly();
			skill = -1;
			break;
		case 11005:
			c.membersonly();
			skill = -1;
			break;
		case 11006:
			skill = 12;
			c.sendMessage("You select Crafting.");
			break;
		case 11007:
			skill = 20;
			c.sendMessage("You select Runecrafting.");
			break;
		case 47002:
			c.membersonly();
			skill = -1;
			break;
		case 54090:
			c.membersonly();
			skill = -1;
			break;
		case 11008:
			skill = 14;
			c.sendMessage("You select Mining.");
			break;
		case 11009:
			skill = 13;
			c.sendMessage("You select Smithing.");
			break;
		case 11010:
			skill = 10;
			c.sendMessage("You select Fishing.");
			break;
		case 11011:
			skill = 7;
			c.sendMessage("You select Cooking.");
			break;
		case 11012:
			skill = 11;
			c.sendMessage("You select Firemaking.");
			break;
		case 11013:
			skill = 8;
			c.sendMessage("You select Woodcutting.");
			break;
		case 11014:
			c.membersonly();
			skill = -1;
			break;
		case 11015:
			if (skill == -1) {
				c.sendMessage("You need to select a skill before continuing.");
				return;
			}
			if (c.getItems().playerHasItem(lamp, 1)) {
				int xp = c.getPA().getLevelForXP(c.playerXP[skill]);
				c.getPA().addSkillXP(xp, skill);
				c.getItems().deleteItem(lamp, 1);
                DialogueHandler.sendStatement2(c, "@blu@Your wish has been granted!"
                		, "You have been awarded " + xp * 100 + " experience in your selected skill!");
			//	c.getPA().removeAllWindows();
			}
			break;
		}
	}

	/**
	 * Rubbing the lamp. ClickItem
	 * 
	 * @param c
	 * @param id
	 */
	public static void rubLamp(Client c, int id) {
		c.sendMessage("You rub the lamp.");
		c.getPA().showInterface(skillmenu);
	}

	/**
	 * Spawns the genie. Start of randomevent
	 * 
	 * @param c
	 */
	public static void spawnGenie(Client c) {
		Server.npcHandler.spawnGenie(c, genie, c.absX + Misc.random(1), c.absY
				+ Misc.random(1), c.heightLevel, 0, 0, 0, 0, 0, false, false,
				true);
	}

}
