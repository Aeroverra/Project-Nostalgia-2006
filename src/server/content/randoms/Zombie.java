package server.content.randoms;

import server.Server;
import server.game.players.Client;
import core.util.Misc;

public class Zombie {

	private static int[][] zombie = {
		{3, 	10, 	419, 	19, 	1},
		{11, 	20, 	420, 	40, 	3},
		{21, 	40, 	421, 	60, 	5},
		{41, 	60, 	422, 	80, 	8},
		{61, 	90, 	423, 	105, 	11},
		{91, 	138, 	424, 	120, 	13},
	};

	public static void spawnZombie(Client c) {
		for(int i = 0; i < zombie.length; i++) {
			if(c.combatLevel >= zombie[i][0] && c.combatLevel <= zombie[i][1]) {
				Server.npcHandler.spawnNpc(c, zombie[i][2], 
						c.absX + Misc.random(1),
						c.absY+ Misc.random(1), 
						c.heightLevel, 0, 
						zombie[i][3], //HP
						zombie[i][4], // maxhit
						(int) (Server.npcHandler.getNpcListCombat(zombie[i][3]) * 1.5), //defence
						(int) (Server.npcHandler.getNpcListCombat(zombie[i][3]) * 1.5), true, false); // attack
			}
		}
	}
}