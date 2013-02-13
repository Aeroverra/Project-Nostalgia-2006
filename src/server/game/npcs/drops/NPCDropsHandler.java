/**
 *  Handler. New drop system. Gets drops by NPC NAME
 *  
 */
package server.game.npcs.drops;

import server.Server;
import server.game.items.ItemList;
import core.util.Misc;

/**
 * @author somedude NPC/ID/AMOUNT/POSSIBILITY
 * 
 */
public class NPCDropsHandler {
	/**
	 * Rarity
	 * 
	 * @author somedude
	 * 
	 */
	protected static int // TODO Adjust the drop rate
			ALWAYS = 0,
			COINSRATE = 3, COMMON = 25 + r(5),
			UNCOMMON = 45 + r(5),
			RARE = 60 + r(10), VERY_RARE = 75 + r(5);

	/**
	 * Handles the npc drops for the npc names.
	 * 
	 * @param NPCId
	 * @return
	 */
	public static final int[][] NPC_DROPS(String npc, int NPCId) {
		switch (npc) {
		case "man":
		case "woman":
			return NPCDrops.man;
			
		case "goblin":
			return NPCDrops.goblin;
			
		case "lesser_demon":
			return NPCDrops.lesserdemon;
			
		case "guard":
		case "jail_guard":
		case "al-kharid_warrior":
			return NPCDrops.guard;
			
		case "ice_warrior":
			return NPCDrops.icewarrior;
			
		case "ice_giant":
			return NPCDrops.icegiant;

		case "hobgoblin":
			return NPCDrops.hobgoblin;

		case "pirate":
			return NPCDrops.pirate;

		case "zombie":
			return NPCDrops.zombie;

		case "skeleton":
			return NPCDrops.skeleton;

		case "deadly_red_spider":
			return NPCDrops.deadlyredspider;

		case "rat":
			return NPCDrops.rat;

		case "imp":
			return NPCDrops.imp;

		case "cow":
			return NPCDrops.cow;

		case "chicken":
			return NPCDrops.chicken;

		case "hill_giant":
		case "moss_giant":
			return NPCDrops.hillgiant;
			
		case "dark_wizard":
			return NPCDrops.darkwizard;
			
		default:
			return NPCDrops.DEFAULT;
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

	/**
	 * Misc.random in shorter form
	 * 
	 * @param random
	 * @return
	 */
	public static int r(int random) {
		return Misc.random(random);
	}

}
