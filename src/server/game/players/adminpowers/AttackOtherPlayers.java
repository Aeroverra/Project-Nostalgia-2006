package server.game.players.adminpowers;

import server.game.players.Client;

/*
 * Eclipse IDE
 * User: somedude
 * Date: Aug 27, 2012
 * Time: 4:54:29 PM
 * TODO:
 */
public class AttackOtherPlayers {
    /**
     * Checks if the administrator can attack other players outside 
     * the wilderness.
     * @param c
     * @return
     */
    public static boolean attackOtherPlayers(Client c) {
	if(c.playerRights >= 3 && c.attackOthers) 
	    return true;
	return false;	
    }
}
