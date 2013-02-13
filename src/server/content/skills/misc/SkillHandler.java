package server.content.skills.misc;

import core.util.Misc;
import server.Config;
import server.content.randoms.EventHandler;
import server.content.skills.Firemaking;
import server.content.skills.Fishing;
import server.content.skills.Mining;
import server.content.skills.Smithing;
import server.content.skills.Woodcutting;
import server.content.skills.cooking.Cooking;
import server.content.skills.crafting.Pottery;
import server.content.skills.crafting.SpinningWheel;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;

public class SkillHandler extends SkillConstants {

    public static final boolean view190 = true;

    private static boolean startRandomEvent;

    public static void resetallSkills(Client c) {
	if (c.playerSkilling[WOODCUTTING])
	    Woodcutting.resetWoodcutting(c);
	if (c.playerSkilling[MINING])
	    Mining.resetMining(c);
	if (c.playerSkilling[FISHING])
	    Fishing.resetFishing(c);
	if (c.playerSkilling[COOKING])
	    Cooking.resetCooking(c);
	if (c.playerSkilling[SMITHING])
	    Smithing.resetSmithing(c);
	if (c.playerIsFiremaking)
	    Firemaking.resetFM(c);
	if (c.playerSkilling[CRAFTING]) {
	    SpinningWheel.resetSpin(c);
	    Pottery.resetPotteryMaking(c);
	}
	c.lastFire = 0;
	// add more resetting skills. if needed
    }

    public static boolean noInventorySpace(Client c, String skill) {
	if (c.getItems().freeSlots() == 0) {
	    c.sendMessage("You don't have enough inventory space to continue "
		    + skill + "!");
	    return false;
	}
	return true;
    }

    public static boolean hasRequiredLevel(final Client c, int id, int lvlReq,
	    String skill, String event) {
	if (c.playerLevel[id] < lvlReq) {
	    c.sendMessage("You don't have a high enough " + skill
		    + " level to " + event + ".");
	    return false;
	}
	if (Config.RANDOMS_ENABLED && !startRandomEvent) {
	    startRandomEvent = true;
	    CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		@Override
		public void execute(CycleEventContainer container) {
		    EventHandler.getRandomEvent(c);
		    startRandomEvent = false;
		    container.stop();
		}

		@Override
		public void stop() {

		}
	    }, 2500 + Misc.random(1500) - Misc.random(c.totalLevel)); // 2500 +
								      // Misc.random(1000)
								      // -
								      // c.totalLevel)
	    return true;
	}
	return true;
    }

    public static void deleteTime(Client c) {
	c.doAmount--;
    }

    /**
     * Stops the event ID. Useful for many things.(WALKING)
     * 
     * @param c
     * @param eventId
     */
    public static void stopEvents(Client c, int eventId) {
	CycleEventHandler.getSingleton().stopEvents(c, eventId);
    }
}