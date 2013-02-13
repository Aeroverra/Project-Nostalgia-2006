package server.content.randoms;

import server.Server;
import server.content.skills.Fishing;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.npcs.NPC;
import server.game.npcs.NPCHandler;
import server.game.players.Client;
import core.util.Misc;

/*
 * Eclipse IDE
 * User: somedude
 * Date: 2012-08-08
 * Time: 5:24:36 PM
 * Purpose: WhirlPool random event(Fishing)
 */
public class Whirlpool extends Fishing {
    /**
     * Contains the fishing npc ids and whirlpool ids.
     * 
     * @author ron
     * 
     */
    private static enum fishingNpcs {
		NETTING_BAITING(
		new int[] { 319, 329, 323, 325, 326, 327, 330, 332, 316 }, 404), 
		LURING_BAITING(
		new int[] { 309, 310, 311, 314, 315, 317, 318, 328, 331 }, 403), 
		CAGING_HARPOONING(
		new int[] { 312, 321, 324 }, 405);

	private int[] fishId;
	private int whirlPool;

	private fishingNpcs(final int[] fishId, final int whirlPool) {
	    this.fishId = fishId;
	    this.whirlPool = whirlPool;
	}

	public int getFishId(int fish) {
	    for (int i = 0; i < fishId.length; i++) {
		if (fish == fishId[i]) {
		    return fishId[i];
		}
	    }
	    return fishId[0];
	}

	public int getWhirlPool() {
	    return whirlPool;
	}

    }
    /**
     * Whirlpool npc Ids
     */
    private static int[] whirlPools = {403,404,405};

    /**
     * Checks if the fishing spot is a whirlpool.
     * 
     * @param c
     * @param npc
     *            - whirlpool npc ID
     * @return
     */
    public static boolean isWhirlPoolNpc(Client c, int npc) {
	for(int i = 0; i < whirlPools.length; i++) {
		if (npc == whirlPools[i]) {
		    return true;
		}
	    }
	return false;
    }
    
    /**
     * Time to spawn event.
     * @param c
     * @param fishId
     */
    public static void eventTimer(final Client c, final int fishId) {
	CycleEventHandler.getSingleton().addEvent(eventId,c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			int timer = 6000 - Misc.random(c.expGained / (c.expGained >= 20000 ? 10 : 4)) 
				+ c.playerLevel[FISHING];
			c.whirlTime += Misc.random(1, 5);
			if(c.whirlTime >= timer) {
			    spawnWhirlPool(c, fishId);
			    c.expGained = 0;
			    c.whirlTime = 0;
			    return;
			}
			if(c.whirlTime > 6000)
			    c.whirlTime = 0;
			
		    }

		    @Override
		    public void stop() {

		    }
		}, 1);

    }
   
    /**
     * Spawns the correct whirlpool for the fishing spots and handles it.
     * 
     * @param c
     * @param fishID
     */
    public static void spawnWhirlPool(final Client c, int fishID) {
	fishID = c.fishNPC;
	final NPC origf = NPCHandler.npcs[c.npcClickIndex];
	for (final fishingNpcs f : fishingNpcs.values()) {
	    if (fishID == f.getFishId(fishID)) {
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
		final NPC whirlpool = new NPC(slot, f.getWhirlPool());
		whirlpool.absX = origf.getX();
		whirlpool.absY = origf.getY();
		whirlpool.makeX = origf.getX();
		whirlpool.makeY = origf.getY();
		whirlpool.heightLevel = c.heightLevel;
		whirlpool.walkingType = 0;
		whirlpool.spawnedBy = c.getId();
		NPCHandler.npcs[slot] = whirlpool;
		/**************** NEW NPC SPAWN ************/
		origf.deleteNPC(origf);// deletes orig. fish
		c.whirlpool = true;
		c.fishingWhirlPool = true;
		CycleEventHandler.getSingleton().addEvent(null, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			// respawns the orig fishing spot
			Server.npcHandler.spawnNpc(c, c.fishNPC, NPC.lastX,
				NPC.lastY, c.heightLevel, 0, 0, 0, 0, 0, false,
				false);
			whirlpool.deleteNPC(whirlpool);
			c.whirlpool = false;
			resetFishing(c);
			container.stop();
		    }

		    @Override
		    public void stop() {
		    }
		}, 30 + Misc.random(30));
	    }
	}
    }
    
    /**
     * If the whirl pool is spawned, start fishing normally.
     * Called in Fishing.java.
     * @param c
     * @param npcId
     */
    public static void fishWhirlPool(final Client c, int npcId) {
	c.fishingWhirlPool = true;
	c.sendMessage("" + messages(c));
	c.getPA().sendSound(379, 100, 1); // fishing
	c.startAnimation(c.playerSkillProp[10][0]);
	c.stopPlayerSkill = true;
	CycleEventHandler.getSingleton().addEvent(eventId, c, new CycleEvent() {
	    @Override
	    public void execute(CycleEventContainer container) {
		if (c.playerSkillProp[10][3] > 0) {

		    if (!c.getItems().playerHasItem(c.playerSkillProp[10][3])) {
			c.sendMessage("You don't have any "
				+ c.getItems().getItemName(
					c.playerSkillProp[10][3]) + " left!");
			c.sendMessage("You need "
				+ c.getItems().getItemName(
					c.playerSkillProp[10][3])
				+ " to fish here.");
			resetFishing(c);
			container.stop();
		    }
		}
		if (c.playerSkillProp[10][5] > 0) {
		    if (c.playerLevel[c.playerFishing] >= c.playerSkillProp[10][6]) {
			c.playerSkillProp[10][1] = c.playerSkillProp[10][Misc
				.random(1) == 0 ? 7 : 5];
		    }
		}

		if (!hasFishingEquipment(c, c.playerSkillProp[10][4])) {
		    resetFishing(c);
		    container.stop();
		}
		if (!noInventorySpace(c, "fishing")) {
		    resetFishing(c);
		    container.stop();
		}
		if (!c.stopPlayerSkill) {
		    container.stop();
		}
		if (!c.playerSkilling[10]) {
		    resetFishing(c);
		    container.stop();
		}
		if (c.playerSkillProp[10][1] > 0) {
		    c.startAnimation(c.playerSkillProp[10][0]);
		    c.getPA().sendSound(379, 100, 1); // fishing
		}
		if(c.fishingWhirlPool) {
			c.getItems().deleteItem(c.playerSkillProp[10][4],1);
			c.sendMessage("You lost your " + c.getItems().getItemName(c.playerSkillProp[10][4])
				.toLowerCase()+ " in the whirlpool!");
			resetFishing(c);
			container.stop();
			return;
		}
		if (!c.whirlpool) {
		    resetFishing(c);
		    container.stop();
		    return;
		}

	    }

	    @Override
	    public void stop() {
	    }
	}, 8);
	
    }

}