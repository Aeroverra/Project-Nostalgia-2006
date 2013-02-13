package server.content.skillguilds;

import server.content.dialoguesystem.DialogueSystem;
import server.game.players.Client;

/*
 * Eclipse IDE
 * User: somedude
 * Date: Aug 22, 2012
 * Time: 8:16:33 PM
 * TODO:
 */
public class MiningGuild extends SkillGuilds {

    public static boolean handleGuild(Client c, int object) {
	if(object == 2113 || object == 2112) {
	if (!checkRequirements(c, c.playerMining, 60)) {
	    DialogueSystem.sendStatement(c,
		    "You need a mining level of 60 to access this guild.");
	    return false;
	}
	if (object == 2113)
	    handleLadder(c,2113, c.absX, c.absY + 6400);
	else if(object == 2112) {
	    if(c.getX() == 3046 && c.getY() == 9757) {
		 handleDoor(c, 2112,3046,9756);
		c.getPA().walkTo(0, -1);
	    c.sendMessage("You enter the mining guild.");
	    } else if(c.getPA().getCoords(3046, 9756)){
		 handleDoor(c, 2112,3046,9756);
		c.getPA().walkTo(0, +1);
	    c.sendMessage("You leave the mining guild.");
	    }
	}
	return true;
	}
	return false;
    }
}

