package server.content.randoms;

import server.Server;
import server.game.players.Client;
import core.util.Misc;

public class Shade {

	private static int[][] shade = {
		{3, 	10, 	425, 	19, 	1},
		{11, 	20, 	426, 	40, 	3},
		{21, 	40, 	427, 	60, 	5},
		{41, 	60, 	428, 	80, 	8},
		{61, 	90, 	429, 	105, 	11},
		{91, 	138, 	430, 	120, 	13},
	};

	public static void spawnShade(Client c) {
		for(int i = 0; i < shade.length; i++) {
			if(c.combatLevel >= shade[i][0] && c.combatLevel <= shade[i][1]) {
				Server.npcHandler.spawnNpc(c, shade[i][2], 
						c.absX + Misc.random(1),
						c.absY+ Misc.random(1), 
						c.heightLevel, 0, 
						shade[i][3], //HP
						shade[i][4], // maxhit
						(int) (Server.npcHandler.getNpcListCombat(shade[i][3]) * 1.5), //defence
						(int) (Server.npcHandler.getNpcListCombat(shade[i][3]) * 1.5), true, false); // attack
			}
		}
	}
}