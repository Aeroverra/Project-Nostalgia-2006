package server.content.skillguilds;

import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;

/*
 * Eclipse IDE
 * User: somedude
 * Date: Aug 22, 2012
 * Time: 8:06:10 PM
 * TODO:
 */
public class SkillGuilds {
    /** 
     * Door object ids for each guild
     */
    private static final int MINING_GUILD = 2112;

    /**
     * Handles the object face of an DOOR used by a guild. 
     * Format - ObjectId,
     * openface, closeface
     */
    public static int[][] objectId = { 
	{ MINING_GUILD, 2, 1}
	
    
    };
    
    /**
     * Handles all guilds in one class.
     * @param c
     * @param object
     * @param x
     * @param y
     */
    public static boolean handleGuilds(Client c, int object, int x ,int y) {
	if(MiningGuild.handleGuild(c, object))
	    return true;
	return false;
    }

    /**
     * Handles ladders
     * 
     * @param c
     * @param objectType
     *            - ladder
     */
    public static void handleLadder(Client c, int object, int x, int y) {
	c.getPA().movePlayer(x, y, 0);
    }

    /**
     * Handles doors
     * 
     * @param c
     * @param x
     * @param y
     * @param state
     *            - open, closed
     */
    public static void handleDoor(final Client c, final int object,
	    final int x, final int y) {
	for (final int[] objects : objectId) {
	    if (objects[0] == object) {
		c.getPA().object(object, x, y, // open door
			objects[1], 0);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			    @Override
			    public void execute(CycleEventContainer container) {
				c.getPA().object(object, x, y, // close door
					objects[2], 0);
				container.stop();
			    }

			    @Override
			    public void stop() {

			    }
			}, 3);
	    }
	}
    }

    /**
     * Checks the player's level to see if it matches the requirement of the
     * guild.
     * 
     * @param c
     * @param skill
     * @param lvlreq
     * @return
     */
    public static boolean checkRequirements(Client c, int skill, int lvlreq) {
	if (c.playerLevel[skill] < lvlreq) {
	    return false;
	}
	return true;
    }
}
