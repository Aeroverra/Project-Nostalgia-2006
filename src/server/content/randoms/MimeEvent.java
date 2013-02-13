package server.content.randoms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
 * Eclipse IDE
 * User: somedude
 * Date: 2012-08-11
 * Time: 11:33:32 AM
 * TODO: Bugs- You can get more than one reward, test more.
 */
public class MimeEvent extends EventHandler {
    /*
     * The mime NPC.
     */
    public static NPC mime;
    /*
     * The mime interface that contains the emotes.
     */
    private static int mimeInterface = 6543;
    /*
     * The mime coords.
     */
    private static int[] mimeCoords = { 2008, 4762 };
    /*
     * Stage face coords
     */
    private static int[] faceStage = { 2011, 4760 };
    /*
     * Contains the mime npc id.
     */
    private static int mimeId = 1056;
    /*
     * The rewards upon completing this event.
     */
    private static int[] rewards = {i("mime top"), i("mime gloves"), 
	i("mime mask"), i("mime legs"), i("mime boots")};
    /*
     * Contains the anims that are NOT used in the actual event.
     */
    private static int BOW = 858;
    /**
     * Contains the emotes that are used in this random event.
     * 
     */
    public static enum emotes {

	THINK(25147, 857, false), 
	CLIMB_ROPE(25150, 0x46A, false), 
	GLASS_WALL(25152, 0x468, false), 
	LEAN(25151, 0x469, false), 
	DANCE(25149,866, false), 
	CRY(25146, 860, false), 
	LAUGH(25148, 861, false), 
	GLASS_BOX(25153, 0x46B, false);

	public int actionButtonId, animId;
	public boolean emote = false;
	private static final List<emotes> VALUES = Collections
		.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	private emotes(int actionButtonId, int animId, boolean emote) {
	    this.actionButtonId = actionButtonId;
	    this.animId = animId;
	    this.emote = emote;
	}

	public emotes randomEmote() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}

	public int getButtonId() {
	    return actionButtonId;
	}

	public int getAnim() {
	    return animId;
	}

	public boolean getBol() {
	    return emote;
	}

	public void setBol(boolean emote) {
	    this.emote = emote;
	}

    }
    /**
     * Sends the mime interface in the chatbox area.
     * @param c
     */
    private static void sendInterface(Client c) {
	c.getPA().sendFrame164(mimeInterface);
    }
    
    /**
     * The start and handler of the random event.
     * 
     * @param c
     */
    public static void handleEvent(final Client c) {
	c.getPA().movePlayer(mimeCoords[0], mimeCoords[1], 0);
	changeToSidebar(c, 8, 5065);
	spawnMime(c);
	mime.startAnim(BOW, 3);
	getNextEmote(c);
	CycleEventHandler.getSingleton().addEvent(1, c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			if(c.mimeSuccess == 8) {// if player succeeds 8 times
			    mime.startAnim(863, 1);
			    handleSuccess(c, "success");
			    return;
			} else if(c.mimeFail == 3) {// if player fails 3 times
			    mime.startAnim(860, 1);
			    handleSuccess(c, "failure");
			    return;
			}
			if(c.mimeEmote > 0) {
			    mime.startAnim(c.mimeEmote, 2);
			    mime.turnNpc(c.getX(), c.getY());
			    c.mimeEmote = 0;
			    sendInterface(c);
			}
			
		    }
		    @Override
		    public void stop() {

		    }
		}, 1);

    }
    
    /**
     * Gets the next random emote. It only gets called when it needs to and it stops
     * when it doesn't need to get another emote.
     * @param c
     */
    private static void getNextEmote(final Client c) {
	CycleEventHandler.getSingleton().addEvent(1, c,new CycleEvent() {
	    	//gets an random emote every 5 cycles.
		    @Override//only if the player has done the emote. also handles successes
		    public void execute(
			    CycleEventContainer container) {
			if(!c.curMimeEmote) { //resets the last emote.
			    for (final emotes e : emotes
				    .values()) {
				e.setBol(false); //sets all to false.
			    }
			}
			if (c.mimeEmote == 0 && !c.curMimeEmote) { //gets the random emote.
			    for (final emotes e : emotes
				    .values()) {
				if (e.getAnim() == e.randomEmote().getAnim()) {
				    e.setBol(true);
				    c.curMimeEmote = e.getBol();
				    c.mimeEmote = e.getAnim();
				container.stop();
				}
			    }
			    return;
			}
		    }
		    @Override
		    public void stop() {

		    }
		}, 4);
    }
    /**
     * Spawns the mime.
     * 
     * @param c
     */
    private static void spawnMime(Client c) {
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
	NPC mime1 = new NPC(slot, mimeId);
	mime1.absX = 2011;
	mime1.absY = 4762;
	mime1.makeX = 2011;
	mime1.makeY = 4762;
	mime1.heightLevel = c.heightLevel;
	mime1.walkingType = 0;
	mime1.spawnedBy = c.getId();
	NPCHandler.npcs[slot] = mime1;
	mime1.turnNpc(faceStage[0], faceStage[1]); 
	mime = mime1; // sets the null mime to the spawned mime.
    }
    /**
     * Handles the action button ids on the mime interface containing emotes.
     * 
     * @param c
     * @param button
     */
    public static void handleButtons(final Client c, int button) {
	for (final emotes e : emotes.values()) {
	    if (button == e.getButtonId()) {
		if (!e.getBol()) { // not the correct emote.
		    c.sendMessage("That's not the right emote.");
		    c.mimeFail++;// increase failure by 1
		    c.getPA().removeAllWindows();
		    CycleEventHandler.getSingleton().addEvent(1, c,
			    new CycleEvent() {//extra delay
				@Override
				public void execute(
					CycleEventContainer container) {
				    c.startAnimation(860);    
				    sendInterface(c);
				    container.stop();
				}
				@Override
				public void stop() {
				}
			    }, 5);
		} else { // success!
		    c.sendMessage("Correct!");
		    mime.turnNpc(faceStage[0], faceStage[1]);
		    c.curMimeEmote = false;
		    c.mimeSuccess++; //increases success.
		    c.getPA().removeAllWindows();
		    getNextEmote(c);
		}
		c.startAnimation(e.getAnim());
	    }

	}
    }


    /**
     * Handles success or failure.
     * 
     * @param c
     * @param type
     *            - Success type
     *            - Failure, success
     */
    private static void handleSuccess(Client c, String type) {
	switch (type) {
	case "success":
	    c.sendMessage("You have been rewarded!");
	    c.getPA().movePlayer(c.pLastX, c.pLastY, c.pLastH);
	    c.getItems().addItem(rewards[Misc.random(rewards.length)], 1);
	    break;
	case "failure":
	    c.sendMessage("You have failed to complete this event.");
	    c.getPA().movePlayer(3040,3398, 0);
	    c.pLastX = 0;
	    c.pLastY = 0;
	    c.pLastH = 0;
	    break;
	}
	CycleEventHandler.getSingleton().stopEvents(c, 1);
	c.mimeSuccess = 0;
	c.mimeFail = 0;
	c.curMimeEmote = false;
	c.mimeEmote = 0;
	mime.deleteNPC(mime);
	changeBackSidebars(c);
	c.getPA().removeAllWindows();
	for (final emotes e : emotes.values()) {
	    e.setBol(false);//sets all emote booleans to false;
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
