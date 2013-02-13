package server.content.skills.crafting;

import server.Config;
import server.content.skills.misc.SkillHandler;
import server.content.skills.misc.SkillMenu;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;

/*
 * Eclipse IDE
 * User: somedude
 * Date: Aug 15, 2012
 * Time: 6:08:18 PM
 * Time Finished: 6:15:44 PM
 */
public class Pottery extends SkillHandler {

    public static final int SOFT_CLAY = 1761;
    private static final int POTTERY_ANIM_UNFIRED = 894;
    private static final int POTTERY_ANIM_FIRED = 896;
    
    /**
     * Contains all of the action buttons for members items 
     */
    private static final int[] membersOnly = 
	{35001,35000,34255,34254,35005,35004,35003,35002};
    
    /**
     * Handles the pottery data.
     * @author somedude
     * 
     */
    public static enum potteryData {
	//In order........................................................lvl, exp,unfired,fireditem
	POT(new int[][] {{34245, 1}, {34244, 5}, {34243, 10}, {34242, 28}}, 1, 12.6, 1787, 1931),
	PIE_DISH(new int[][] {{34249, 1}, {34248, 5}, {34247, 10}, {34246, 28}}, 7, 25, 1789, 2313),
	BOWL(new int[][] {{34253, 1}, {34252, 5}, {34251, 10}, {34250, 28}}, 8, 33, 1791, 1923);

	private int[][] buttonId;
	private int level, unFired, fired;
	private double exp;

	private potteryData(final int[][] buttonId, final int level, final double exp, final int unFired, final int fired) {
	    this.buttonId = buttonId;
	    this.level = level;
	    this.exp = exp;
	    this.unFired = unFired;
	    this.fired = fired;
	    
	}

	public int getButtonId(final int button) {
	    for (int i = 0; i < buttonId.length; i++) {
		if (button == buttonId[i][0]) {
		    return buttonId[i][0];
		}
	    }
	    return -1;
	}

	public int getAmount(final int button) {
	    for (int i = 0; i < buttonId.length; i++) {
		if (button == buttonId[i][0]) {
		    return buttonId[i][1];
		}
	    }
	    return -1;
	}

	public int getLevel() {
	    return level;
	}
	
	public double getExp() {
	    return exp;
	}
	
	public int getUnFiredItem() {
	    return unFired;
	}
	
	public int getFiredItem() {
	    return fired;
	}
    }
    
    /**
     * Sends the pottery interface
     * 
     * @param c
     */
    public static void sendPotteryInterface(Client c, int usedWith, String type) {
	switch (type.toLowerCase()) {
	case "unfired":
	    if (!c.getItems().playerHasItem(SOFT_CLAY) || (!(usedWith == SOFT_CLAY))) {
		    c.sendMessage("Nothing interesting happens.");
		    return;
		}	
		SkillMenu.sendInterface(c, "unfired");
		c.unfired = true;
		c.fired = false;
	    break;
	case "fired":
	    SkillMenu.sendInterface(c, "fired");
		c.unfired = false;
		c.fired = true;
	    break;
	}
    }
    
    /**
     * Mains method. It handles making the pottery.
     * @param c
     * @param buttonId
     */
    public static void makePottery(final Client c, int buttonId) {
	/* Checks */
	if(!c.unfired && !c.fired)
	    return;
	
	if (!Config.CRAFTING_ENABLED) {
	    c.sendMessage(c.disabled());
	    return;
	}
	if (c.playerSkilling[c.playerCrafting]|| c.stopPlayerSkill) {
		return;
	}
	if (!checkReqs(c, buttonId)) {
	    return;
	}
	for(int members : membersOnly) {
	    if(buttonId == members) {
		c.getPA().removeAllWindows();
		c.membersonly();
		return;
	    }
	}
	for (final potteryData s : potteryData.values()) {
	    if (buttonId == s.getButtonId(buttonId)) {
		if (!c.getItems().playerHasItem(SOFT_CLAY) && c.unfired) {
		    c.getPA().removeAllWindows();
		    c.sendMessage("You need some soft clay to do this.");
		    return;
		} else if(!c.getItems().playerHasItem(s.getUnFiredItem()) && c.fired) {
		    c.getPA().removeAllWindows();
		    c.sendMessage("You need a " + c.getItems().getItemName(s.getUnFiredItem()).toLowerCase() +
			    " to do that.");
		    return;
		}
		c.doAmount = s.getAmount(buttonId);
		c.getPA().removeAllWindows();
		if(c.unfired) {
			c.startAnimation(POTTERY_ANIM_UNFIRED);
		}
			else {
			    c.startAnimation(POTTERY_ANIM_FIRED);
			    c.getPA().sendSound(352, 100, 1);
			}
		c.playerSkilling[c.playerCrafting] = true;
		c.stopPlayerSkill = true;
		CycleEventHandler.getSingleton().addEvent(c.playerCrafting, c, new CycleEvent() {
		    @Override // Method
		    public void execute(CycleEventContainer container) {
			if(!c.stopPlayerSkill || !c.playerSkilling[c.playerCrafting] || 
				!c.getItems().playerHasItem(SOFT_CLAY) && c.unfired || c.doAmount == 0 ||
				!c.getItems().playerHasItem(s.getUnFiredItem()) && c.fired) {
			    resetPotteryMaking(c);
			    container.stop();
			    return;
			}
			deleteTime(c);
    			c.getItems().deleteItem((c.unfired ? SOFT_CLAY : s.getUnFiredItem()), 1);
    			c.getItems().addItem((c.unfired ? s.getUnFiredItem() : s.getFiredItem()), 1);
    			c.sendMessage((c.unfired ? "You make the soft clay into a " + c.getItems().getItemName(s.getUnFiredItem())
    				.toLowerCase() + "." :  "You fire the "+ c.getItems().getItemName(s.getUnFiredItem()).toLowerCase()+
    				" into a fired " + c.getItems().getItemName(s.getFiredItem())
    				.toLowerCase() + "."));
    			c.getPA().addSkillXP((int) s.getExp(), c.playerCrafting);
		    }

		    @Override
		    public void stop() {

		    }
		}, (int) 5.8);
		CycleEventHandler.getSingleton().addEvent(c.playerCrafting, c, new CycleEvent() {
		    @Override // Animation
		    public void execute(CycleEventContainer container) {
			if(!c.stopPlayerSkill || !c.playerSkilling[c.playerCrafting] || 
				!c.getItems().playerHasItem(SOFT_CLAY) && c.unfired || c.doAmount == 0 ||
				!c.getItems().playerHasItem(s.getUnFiredItem()) && c.fired) {
			    resetPotteryMaking(c);
			    container.stop();
			    return;
			}
			if(c.unfired) {
			c.startAnimation(POTTERY_ANIM_UNFIRED);
			}
			else {
			    c.startAnimation(POTTERY_ANIM_FIRED);
			    c.getPA().sendSound(352, 100, 1);
			}
			
		    }

		    @Override
		    public void stop() {

		    }
		}, 5);
	    }
	}

    }
    
    /**
     * Checks if the player has the correct requirements.
     * @param c
     * @param buttonId
     * @return lol
     */
    private static boolean checkReqs(Client c, int buttonId) {
	for (final potteryData s : potteryData.values()) {
	    if (buttonId == s.getButtonId(buttonId)) {
		if (c.playerLevel[c.playerCrafting] < s.getLevel()) {
		    c.sendMessage("You need a crafting level of " + s.getLevel()
			    + " to make this.");
		    c.getPA().removeAllWindows();
		    return false;
		}
	    }
	}
	return true;
    }
    
    public static void resetPotteryMaking(Client c) {
	c.playerSkilling[c.playerCrafting] = false;
	c.stopPlayerSkill = false;
	stopEvents(c, c.playerCrafting);
	c.unfired = false;
	c.fired = false;
	c.doAmount = 0;
    }

}
