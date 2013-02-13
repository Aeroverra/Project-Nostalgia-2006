package server.content.randoms;

import server.Server;
import server.game.players.Client;
import core.util.Misc;

public class RockGolem {

	private static int[][] golem = {
		{14, 	413, 	19, 	1},
		{29, 	414, 	40, 	3},
		{49, 	415, 	60, 	5},
		{79, 	416, 	80, 	8},
		{120, 	417, 	105, 	11},
		{159, 	418, 	120, 	13},
	};

	public static void spawnGolem(Client c) {
		for(int i = 0; i < golem.length; i++) {
			if(c.combatLevel >= golem[i][0] && c.combatLevel <= golem[i][1]) {
				Server.npcHandler.spawnNpc(c, golem[i][1],
						c.absX + Misc.random(1),
						c.absY+ Misc.random(1), 
						c.heightLevel, 0, 
						golem[i][2], //HP
						golem[i][3], // maxhit
						(int) (Server.npcHandler.getNpcListCombat(golem[i][1]) * 1.5), //defence
						(int) (Server.npcHandler.getNpcListCombat(golem[i][1]) * 1.5), true, false); // attack
			}
		}
	}
}