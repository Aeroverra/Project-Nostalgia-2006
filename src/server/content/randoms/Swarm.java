package server.content.randoms;

import core.util.Misc;
import server.Server;
import server.game.players.Client;

/**
 * Created with IntelliJ IDEA.
 * User: somedude
 * Date: 8/5/12
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Swarm {
    /**
     * Spawns the swarm.
     *
     * @param c
     */
    public static void spawnSwarm(Client c) {
        Server.npcHandler.spawnNpc(c, 411,
                c.absX + Misc.random(1),
                c.absY + Misc.random(1),
                c.heightLevel, 0,
                2000, //HP
                3, // maxhit
                0, //defence
                3 * c.combatLevel, true, false); // attack
    }
}
