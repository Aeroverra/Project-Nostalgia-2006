package server.content.randoms;

import core.util.Misc;
import server.Server;
import server.content.skills.Woodcutting;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.npcs.NPC;
import server.game.npcs.NPCHandler;
import server.game.players.Client;

/**
 * @author 100% by somedude
 * 
 */
public class Ent {
	private static int eventId = 8;
	public static enum entdata {
		NORMAL_TREE(new int[] { 3033, 1276, 1277, 1278, 1279, 1280, 1282, 1283,
				1284, 1285, 1286, 1287, 1288, 1289, 1290, 1291, 1301, 1303,
				1304, 1305, 1318, 1319, 1315, 1316, 1330, 1331, 1332, 1333,
				1383, 1384, 2409, 2447, 2448, 3033, 3034, 3035, 3036, 3879,
				3881, 3883, 3893, 3885, 3886, 3887, 3888, 3892, 3889, 3890,
				3891, 3928, 3967, 3968, 4048, 4049, 4050, 4051, 4052, 4053,
				4054, 4060, 5004, 5005, 5045, 5902, 5903, 5904, 8973, 8974, },
				1721), // last one is ent npc id

		OAK_TREE(new int[] { 1281, 3037, 8462, 8463, 8464, 8465, 8466, 8467,
				10083, 13413, 13420 }, 1739), // last one is ent npc id

		WILLOW_TREE(new int[] { 1308, 5551, 5552, 5553, 8481, 8482, 8483, 8484,
				8485, 8486, 8487, 8488, 8496, 8497, 8498, 8499, 8500, 8501,
				13414, 13421 }, 1736), // last one is ent npc id

		YEW_TREE(new int[] { 1309, 8503, 8504, 8505, 8506, 8507, 8508, 8509,
				8510, 8511, 8512, 8513, 13416, 13422 }, 1740);// last one is ent
																// npc id

		private int[] treeId;
		private int entId;

		private entdata(final int[] treeId, final int entId) {
			this.treeId = treeId;
			this.entId = entId;
		}

		public int getTree(int object) {
			for (int i = 0; i < treeId.length; i++) {
				if (object == treeId[i]) {
					return treeId[i];
				}
			}
			return treeId[0];
		}

		public int getEntId() {
			return entId;
		}
	}

	/**
	 * Data of broken axes
	 */
	public static int[][] axes = {
			// Axe,BrokenAxe
			{ 1351, 494 }, // bronze
			{ 1349, 496 }, // iron
			{ 1353, 498 }, // steel
			{ 1361, 500 }, // black
			{ 1355, 502 }, // mith
			{ 1357, 504 }, // addy
			{ 1359, 506 }, // rune
	};

	/**
	 * Breaks the axe.
	 */
	public static void breakAxe(Client c, int axeType) {
		if (hasAxe(c)) {
			for (int i = 0; i < axes.length; i++) {
				if (axeType == axes[i][0]) {// if axeType == Current AXE
					c.getItems().deleteItem(axeType, 2);// deletes orig axe.
				    c.getItems().removeItem(axeType, 3);
					c.getItems().addItem(axes[i][1], 1);// adds broken axe
					c.sendMessage("The tree breaks your axe!");
				}
			}
		}
	}

	/**
	 * Spawns an ent depending on treeid type.
	 * 
	 * @param c
	 */
	public static void spawnEnt(final Client c, final int tree, final int obX,
			final int obY) {
		for (final entdata ent : entdata.values()) {
			if (tree == ent.getTree(tree)) {
				/**************** NEW NPC SPAWN **********/
				int slot = -1;
				for (int i = 1; i < NPCHandler.maxNPCs; i++) {
					if (NPCHandler.npcs[i] == null) {
						slot = i;
						break;
					}
				}
				if (slot == -1) {
					return;
				}
				final NPC newNPC = new NPC(slot, ent.getEntId());
				newNPC.absX = obX;
				newNPC.absY = obY;
				newNPC.makeX = obX;
				newNPC.makeY = obY;
				newNPC.heightLevel = c.heightLevel;
				newNPC.walkingType = 0;
				newNPC.spawnedBy = c.getId();
				NPCHandler.npcs[slot] = newNPC;
				/**************** NEW NPC SPAWN ************/
				c.sendMessage("ent.getTree " + ent.getTree(tree));
				Server.objectManager.removeObject(obX, obY);// remove tree
				Woodcutting.resetWoodcutting(c);
				chopEnt(c, ent.getEntId());
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						Server.npcHandler.removeNpc(newNPC);// removes the ent
						//c.getPA().object(ent.getTree(tree), obX, obY, 0, 10);
						Server.objectHandler.createAnObject(c, ent.getTree(tree), obX, obY);
						Woodcutting.resetWoodcutting(c);
						container.stop();

					}

					@Override
					public void stop() {

					}
				}, 30 + Misc.random(c.playerLevel[8] / 4));
			}
		}
	}

	/**
	 * Handles chopping the ent tree.
	 * 
	 * @param c
	 * @param tree
	 */
	public static void chopEnt(final Client c, int entid) {
		for (final entdata ent : entdata.values()) {
			if (entid == ent.getEntId()) {// Chopping Ent
				if (hasAxe(c)) {
					c.playerSkilling[8] = true;
					c.stopPlayerSkill = true;
					c.startAnimation(getAnimation(c));
					CycleEventHandler.getSingleton().addEvent(eventId, c,
							new CycleEvent() {
								@Override
								public void execute(
										CycleEventContainer container) {
									if (!c.playerSkilling[8]) {
										Woodcutting.resetWoodcutting(c);
										container.stop();
									}
									if (!c.stopPlayerSkill) {
										Woodcutting.resetWoodcutting(c);
										container.stop();
									}
									c.getPA().sound(472);
									c.startAnimation(getAnimation(c));

								}

								@Override
								public void stop() {
								}
							}, 4);
					CycleEventHandler.getSingleton().addEvent(eventId, c,
							new CycleEvent() {
								@Override
								// BREAK AXE!
								public void execute(
										CycleEventContainer container) {
									if (c.playerSkilling[8]) {
										Woodcutting.resetWoodcutting(c);
										breakAxe(c, getAxeID(c));
										container.stop();
									}
								}

								@Override
								public void stop() {
								}
							}, 8);
				}
			}
		}
	}

	/**
	 * If player has axe.
	 * 
	 * @param c
	 * @return
	 */
	private static boolean hasAxe(Client c) {
		for (int i = 0; i < axes.length; i++) {
			if (c.getItems().playerHasItem(axes[i][0])
					|| c.playerEquipment[3] == axes[i][0]) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Gets the animation
	 * @param c
	 * @return
	 */
	private static int getAnimation(Client c) {
		for (int i = 0; i < Woodcutting.axes.length; i++) {
			if (c.getItems().playerHasItem(Woodcutting.axes[i][0])
					|| c.playerEquipment[3] == Woodcutting.axes[i][0]) {
				return Woodcutting.axes[i][2];
			}
		}
		return -1;
	}
	
   /**
    * Gets axe id that player has
    * @param c
    * @return
    */
	private static int getAxeID(Client c) {
		int axe = -1;
		for (int i = 0; i < axes.length; i++) {
			if (c.getItems().playerHasItem(axes[i][0])
					|| c.playerEquipment[3] == axes[i][0]) {
				axe = axes[i][0];
			}
		}
		return axe;
	}
	
	 /**
	    * Timer
	    * @param c
	    * @return TODO Increase rates when hosting.
	    */
		public static int getTime(Client c) {
			if(c.playerLevel[8] < 30) {
				return Misc.random(c.playerLevel[8], 50) * 7 + 25;
			}
		return Misc.random(c.playerLevel[8]) * 2 + 25;
		}


}
