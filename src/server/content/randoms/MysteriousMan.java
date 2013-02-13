package server.content.randoms;

import server.Server;
import server.game.players.Client;
import core.util.Misc;

/**
 * @author someotherdude
 * 
 */
public class MysteriousMan {
	public static int box = 2528, mysteriousman = 410, boxmenu = 2808, skill = -1;

	@SuppressWarnings("unused")
	private static int[][] boxprizes = {
		{995, 	20 }, {995, 40 }, {995, 80 },
		{995, 	160 }, {995, 320}, {995, 640},
		{1617, 1}, {1619, 1}, {1621, 1}, {1623, 1},
	};
	/**
	 * Handles the actionbuttons
	 * 
	 * @param c
	 * @param id
	 */
	public static void buttons(Client c, int id) {
		switch (id) {

		}
	}

	/**
	 * Rubbing the lamp. ClickItem
	 * 
	 * @param c
	 * @param id
	 */
	public static void openBox(Client c, int id) {
		c.sendMessage("You open the strange box.");
		c.getPA().showInterface(boxmenu);
	}

	/**
	 * Spawns the genie. Start of randomevent
	 * 
	 * @param c
	 */
	public static void spawnMan(Client c) {
		Server.npcHandler.spawnGenie(c, mysteriousman, c.absX + Misc.random(1), c.absY
				+ Misc.random(1), c.heightLevel, 0, 0, 0, 0, 0, false, false,
				false);
	}

}
