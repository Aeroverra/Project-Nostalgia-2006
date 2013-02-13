package server.content.randoms;

import server.Server;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.items.ItemList;
import server.game.npcs.NPC;
import server.game.npcs.NPCHandler;
import server.game.players.Client;
import core.util.Misc;
/*
 * Damn. This code is soo messy lol
 */
/**
 * Created with IntelliJ IDEA. User: somedude Date: 8/5/12 Time: 5:35 PM
 * Purpose: Mime event
 */
public class Mime extends EventHandler {
    private static int think = 857, climbrope = 0x46A, lean = 0x469,
	    glassbox = 0x46B, cry = 860, laugh = 861, dance = 866,
	    glasswall = 0x468;
    /*
     * mimeTimers for stages
     */
    private static int stage1 = 5, stage2 = 10, stage3 = 15, stage4 = 20,
	    stage5 = 25, stage6 = 30, stage7 = 35, stage8 = 40;

    /*
     * Mime interface that contains the emotes
     */
    private static int mimeInterface = 6543;
    /*
     * Mime stage coord
     */
    private static int[] mimeStage = { 2008, 4762 };
    /*
     * Mime npc ID
     */
    private static int mime = 1056;
    /*
     * Rewards
     */				 
    private static int[] rewards = {i("mime top"), i("mime gloves"), 
	i("mime mask"), i("mime legs"), i("mime boots")};

    /**
     * Handles the main event.
     * 
     * @param c
     */
    public static void handleEvent(final Client c) {
	c.lastX = c.getX();
	c.lastY = c.getY();
	c.lastHeight = c.heightLevel;
	EventHandler.changeToSidebar(c, 8, 5065);
	c.getPA().movePlayer(mimeStage[0], mimeStage[1], 0);
	/**************** NEW NPC SPAWN ************/
	int slot = -1;
	for (int i1 = 1; i1 < NPCHandler.maxNPCs; i1++) {
	    if (NPCHandler.npcs[i1] == null) {
		slot = i1;
		break;
	    }
	}
	if (slot == -1) {
	    return;
	}
	final NPC mime1 = new NPC(slot, mime);
	mime1.absX = 2011;
	mime1.absY = 4762;
	mime1.makeX = 2011;
	mime1.makeY = 4762;
	mime1.heightLevel = c.heightLevel;
	mime1.walkingType = 0;
	mime1.spawnedBy = c.getId();
	NPCHandler.npcs[slot] = mime1;
	/**************** NEW NPC SPAWN ************/
	CycleEventHandler.getSingleton().addEvent(null, new CycleEvent() {
	   
	    @Override
	    public void execute(CycleEventContainer container) {
		if(c.properLogout || c.session == null) {
		    mime1.deleteNPC(mime1);
		    container.stop();
		    return;
	
		}
		if (c.failure == 3) {
		  container.setTick(6);
		  c.failure = 4;
		    return;
		}
		if(c.failure >= 4) {
		    mime1.animNumber = cry;
		    mime1.animUpdateRequired = true;
		    mime1.deleteNPC(mime1);
		    changeBackSidebars(c);
		    c.sendMessage("You have failed to complete this event.");
		    c.getPA().movePlayer(3040,3398, 0);
		    c.mimeevent = 0;
		    c.mimeTimer = 0;
		    c.getPA().closeAllWindows();
		    c.failure = 0;
		    container.stop();
		    return;
		}
		mime1.turnNpc(c.getX(), c.getY());
		c.turnPlayerTo(mime1.getX(), mime1.getY());
		if (c.mimeTimer == stage1 && c.mimeevent == 0) { // stage 1
		    mime1.animNumber = climbrope;
		    mime1.animUpdateRequired = true;
		    c.getPA().sendFrame164(mimeInterface);
		} else if (c.mimeTimer == stage2 && c.mimeevent == 1) {
		    mime1.animNumber = think;
		    mime1.animUpdateRequired = true;
		    c.getPA().sendFrame164(mimeInterface);
		} else if (c.mimeTimer == stage3 && c.mimeevent == 2) {
		    mime1.animNumber = laugh;
		    mime1.animUpdateRequired = true;
		    c.getPA().sendFrame164(mimeInterface);
		} else if (c.mimeTimer == stage4 && c.mimeevent == 3) {
		    mime1.animNumber = lean;
		    mime1.animUpdateRequired = true;
		    c.getPA().sendFrame164(mimeInterface);
		} else if (c.mimeTimer == stage5 && c.mimeevent == 4) {
		    mime1.animNumber = glassbox;
		    mime1.animUpdateRequired = true;
		    c.getPA().sendFrame164(mimeInterface);
		} else if (c.mimeTimer == stage6 && c.mimeevent == 5) {
		    mime1.animNumber = dance;
		    mime1.animUpdateRequired = true;
		    c.getPA().sendFrame164(mimeInterface);
		} else if (c.mimeTimer == stage7 && c.mimeevent == 6) {
		    mime1.animNumber = cry;
		    mime1.animUpdateRequired = true;
		    c.getPA().sendFrame164(mimeInterface);
		} else if (c.mimeTimer == stage8 && c.mimeevent == 7) {
		    mime1.animNumber = glasswall;
		    mime1.animUpdateRequired = true;
		    c.getPA().sendFrame164(mimeInterface);
		} else if (c.mimeevent == 8) {
		    mime1.animNumber = 863;//wave
		    mime1.animUpdateRequired = true;
		    c.getPA().removeAllWindows();
		    handleSucess(c,mime1);
		    changeBackSidebars(c);
		    container.stop();
		    return;
		}

		c.mimeTimer++;
	    }

	    @Override
	    public void stop() {
		c.mimeTimer = 0;
	    }
	}, 1);
    }

    /**
     * Better handler for failure
     * 
     * @param c
     * @param emote
     */
    private static void failure(final Client c, int emote) {
	if(c.failure >= 4)
	    return;
	c.failure++;
	c.startAnimation(emote);
	c.getPA().removeAllWindows();
	c.sendMessage("That's not the right emote.");
	CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
	    @Override
	    public void execute(CycleEventContainer container) {
		c.startAnimation(cry);
		c.getPA().sendFrame164(mimeInterface);
		container.stop();
	    }

	    @Override
	    public void stop() {

	    }
	}, 6);
    }
    
    public static void handleSucess(final Client c, final NPC npc) {
	  CycleEventHandler.getSingleton().addEvent(c,
		    new CycleEvent() {// animation
			@Override
			public void execute(
				CycleEventContainer container) {
			   npc.deleteNPC(npc);
			    c.getPA().movePlayer(c.lastX, c.lastY, c.lastHeight);
			    c.getItems().addItem(rewards[Misc.random(0, 4)], 1);
			    c.sendMessage("You have been awarded!");
			    c.mimeevent = 0;
			    c.mimeTimer = 0;
			    container.stop();
			}

			@Override
			public void stop() {

			}
		    }, 6);
    }
    
    /**
     * Handles the stages.
     * 
     * @param c
     */
    public static void handleStages(final Client c, int actionButton) {
	switch (actionButton) {
	case 25147: // think
	    if (c.mimeevent == 1) {
		c.startAnimation(think);
		c.getPA().removeAllWindows();
		c.mimeevent = 2;
		c.mimeTimer = stage2;
	    } else {
		failure(c, think);
	    }
	    break;
	case 25148: // laugh
	    if (c.mimeevent == 2) {
		c.startAnimation(laugh);
		c.getPA().removeAllWindows();
		c.mimeevent = 3;
		c.mimeTimer = stage3;
	    } else {
		failure(c, laugh);
	    }
	    break;
	case 25150: // climb rope
	    if (c.mimeevent == 0) {
		c.startAnimation(climbrope);
		c.getPA().removeAllWindows();
		c.mimeevent = 1;
		c.mimeTimer = stage1;
	    } else {
		failure(c, climbrope);
	    }
	    break;
	case 25153: // glass box
	    if (c.mimeevent == 4) {
		c.startAnimation(glassbox);
		c.getPA().removeAllWindows();
		c.mimeevent = 5;
		c.mimeTimer = stage5;
	    } else {
		failure(c, glassbox);
	    }
	    break;
	case 25146: // cry
	    if (c.mimeevent == 6) {
		c.startAnimation(cry);
		c.getPA().removeAllWindows();
		c.mimeevent = 7;
		c.mimeTimer = stage7;
	    } else {
		failure(c, cry);
	    }
	    break;
	case 25149: // dance
	    if (c.mimeevent == 5) {
		c.startAnimation(dance);
		c.getPA().removeAllWindows();
		c.mimeevent = 6;
		c.mimeTimer = stage6;
	    } else {
		failure(c, dance);
	    }
	    break;
	case 25151: // lean
	    if (c.mimeevent == 3) {
		c.startAnimation(lean);
		c.getPA().removeAllWindows();
		c.mimeevent = 4;
		c.mimeTimer = stage4;

	    } else {
		failure(c, lean);
	    }
	    break;
	case 25152: // glass wall
	    if (c.mimeevent == 7) {
		c.startAnimation(glasswall);
		c.getPA().removeAllWindows();
		c.mimeevent = 8;
		c.mimeTimer = stage8;
	    } else {
		failure(c, glasswall);
	    }
	    break;
	}

    }
	/**
	 * Gets the item name
	 * 
	 * @param ItemID
	 * @return
	 */
	public static int i(String ItemName) {
		return getItemId(ItemName);
	}

	/**
	 * Item name main method
	 * 
	 * @param itemName
	 * @return
	 */
	public static int getItemId(String itemName) {
		for (ItemList i : Server.itemHandler.ItemList) {
			if (i != null) {
				if (i.itemName.equalsIgnoreCase(itemName)) {
					return i.itemId;
				}
			}
		}
		return -1;
	}

}
