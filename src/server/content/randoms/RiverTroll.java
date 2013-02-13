package server.content.randoms;

import core.util.Misc;
import server.Server;
import server.game.players.Client;

/**
 * @author somedude
 */
public class RiverTroll {
	/*
	 * Contains the combat levels and HP of the river troll
	 * level, NPCID,  HP,   MAXHIT
	 */
	private static int[][] troll = {
		{14,  	391, 	19, 	3},
		{29, 	392, 	40, 	5},
		{49, 	393, 	60, 	7},
		{79, 	394, 	80, 	9},
		{120, 	395, 	105, 	11},
		{159, 	396, 	120, 	14},
	};
	
	/**
	 * Spawns the river troll based on player's combat level.
	 * 
	 * @param c
	 */
	public static void spawnTroll(Client c) {
		for(int i = 0; i < troll.length; i++) {
			if(c.combatLevel >= troll[i][0] && c.combatLevel <= troll[i][0]) {
				Server.npcHandler.spawnNpc(c, troll[i][1],
						c.absX + Misc.random(1),
						c.absY+ Misc.random(1), 
						c.heightLevel, 0, 
						troll[i][2], //HP
						troll[i][3], // maxhit
						(int) (Server.npcHandler.getNpcListCombat(troll[i][1]) * 1.5), //defence
						(int) (Server.npcHandler.getNpcListCombat(troll[i][1]) * 1.5), true, false); // attack
			}
		}
	}

}
