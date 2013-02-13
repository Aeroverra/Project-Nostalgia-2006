package server.content.randoms;

import server.Server;
import server.game.players.Client;
import core.util.Misc;

public class EvilChicken {

	private static int[][] chicken = {
		{3, 	10, 	2463, 	19, 	1},
		{11, 	20, 	2464, 	40, 	1},
		{21, 	40, 	2465, 	60, 	2},
		{41, 	60, 	2466, 	80, 	3},
		{61, 	90, 	2467, 	105, 	4},
		{91, 	138, 	2468, 	120, 	5},
	};

	public static void spawnChicken(Client c) {
		for(int i = 0; i < chicken.length; i++) {
			if(c.combatLevel >= chicken[i][0] && c.combatLevel <= chicken[i][1]) {
				Server.npcHandler.spawnNpc(c, chicken[i][2], 
						c.absX + Misc.random(1),
						c.absY+ Misc.random(1), 
						c.heightLevel, 0, 
						chicken[i][3], //HP
						chicken[i][4], // maxhit
						(int) (Server.npcHandler.getNpcListCombat(chicken[i][2]) * 1.5), //defence
						(int) (Server.npcHandler.getNpcListCombat(chicken[i][2]) * 1.5), true, false); // attack
			}
		}
	}
}