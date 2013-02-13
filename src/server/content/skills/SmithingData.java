package server.content.skills;

import server.Server;
import server.game.items.ItemList;
import server.game.players.Client;

/**
 * Handles the Smithing data.
 * @author somedude
 *
 */
public class SmithingData {
	
	public static enum data {
		//************Item,Exp,LvlRequired,barsAmount//*************//
		BRONZE(new int[][] {
			{i("bronze axe"), 12, 1, 1}, {i("bronze dagger"), 12, 1, 1},
			{i("bronze mace"),12, 2, 1}, {i("bronze med helm"),12, 3, 1},
			{i("bronze sword"),12, 4, 1}, {i("bronze nails"), 12, 4, 1},
			{i("bronze scimitar"),25, 5, 2},
			{i("bronze pickaxe"),25, 5, 2}, {i("bronze longsword"),25, 6, 2},
			{i("bronze full helm"),25, 7, 2}, {i("bronze sq shield"),25, 8, 2},
			{i("bronze warhammer"),37, 9, 3}, {i("bronze battleaxe"),37, 10, 3},
			{i("bronze chainbody"),37, 11, 3},{i("bronze kiteshield"),37, 12, 3},
			{i("bronze 2h sword"),37, 14, 3}, {i("bronze plateskirt"),37, 16, 3},
			{i("bronze platelegs"),37, 16, 3}, {i("bronze platebody"),62, 18, 5}
			}, i("bronze bar")),
		IRON(new int[][] {
			{i("iron dagger"),25, 15, 1}, {i("iron axe"), 25, 16, 1}, 
			{i("iron mace"),25, 17, 1}, {i("iron med helm"),25, 18, 1},
			{i("iron sword"),25, 19, 1},{i("iron scimitar"),50, 20, 2},
			{i("iron pickaxe"),50, 20, 2}, {i("iron longsword"),50, 21, 2}, 
			{i("iron full helm"),50, 22, 2}, {i("iron sq shield"),50, 23, 2}, 
			{i("iron warhammer"),75, 24, 3}, {i("iron battleaxe"),75, 25, 3}, 
			{i("iron chainbody"),75, 26, 3},{i("iron kiteshield"),75, 27, 3}, 
			{i("iron 2h sword"),75, 29, 3}, {i("iron plateskirt"),75, 31, 3}, 
			{i("iron platelegs"),75, 31, 3}, {i("iron platebody"),125, 33, 5}
			}, i("iron bar")),
		STEEL(new int[][] {
			{i("steel dagger"),37, 30, 1}, {i("steel axe"), 37, 31, 1}, 
			{i("steel mace"),37, 32, 1}, {i("steel med helm"),37, 33, 1},
			{i("steel sword"),37, 34, 1},{i("steel scimitar"),75, 35, 2},
			{i("steel pickaxe"),75, 35, 2}, {i("steel longsword"),75, 36, 2}, 
			{i("steel full helm"),75, 37, 2}, {i("steel sq shield"),75, 38, 2}, 
			{i("steel warhammer"),112, 39, 3}, {i("steel battleaxe"),112, 40, 3}, 
			{i("steel chainbody"),112, 41, 3},{i("steel kiteshield"),112, 42, 3}, 
			{i("steel 2h sword"),112, 44, 3}, {i("steel plateskirt"),112, 46, 3}, 
			{i("steel platelegs"),112, 46, 3}, {i("steel platebody"),187, 48, 5}
			}, i("steel bar")),
		MITHRIL(new int[][] {
			{i("mithril dagger"),50, 50, 1}, {i("mithril axe"), 50, 51, 1}, 
			{i("mithril mace"),50, 52, 1}, {i("mithril med helm"),50, 53, 1},
			{i("mithril sword"),50, 54, 1},{i("mithril scimitar"),100, 55, 2},
			{i("mithril pickaxe"),100, 55, 2}, {i("mithril longsword"),100, 56, 2}, 
			{i("mithril full helm"),100, 57, 2}, {i("mithril sq shield"),100, 58, 2}, 
			{i("mithril warhammer"),150, 59, 3}, {i("mithril battleaxe"),150, 60, 3}, 
			{i("mithril chainbody"),150, 61, 3},{i("mithril kiteshield"),150, 62, 3}, 
			{i("mithril 2h sword"),150, 64, 3}, {i("mithril plateskirt"),150, 66, 3}, 
			{i("mithril platelegs"),150, 66, 3}, {i("mithril platebody"),250, 68, 5}
			}, i("mithril bar")),
		ADAMANT(new int[][] {
			{i("adamant dagger"),62, 70, 1}, {i("adamant axe"), 62, 71, 1}, 
			{i("adamant mace"),62, 72, 1}, {i("adamant med helm"),62, 73, 1},
			{i("adamant sword"),62, 74, 1},{i("adamant scimitar"),125, 75, 2},
			{i("adamant pickaxe"),125, 75, 2}, {i("adamant longsword"),125, 76, 2}, 
			{i("adamant full helm"),125, 77, 2}, {i("adamant sq shield"),125, 78, 2}, 
			{i("adamant warhammer"),187, 79, 3}, {i("adamant battleaxe"),187, 80, 3}, 
			{i("adamant chainbody"),187, 81, 3},{i("adamant kiteshield"),187, 82, 3}, 
			{i("adamant 2h sword"),187, 84, 3}, {i("adamant plateskirt"),187, 86, 3}, 
			{i("adamant platelegs"),187, 86, 3}, {i("adamant platebody"),312, 88, 5}
			}, i("adamantite bar")),
		RUNE(new int[][] {
			{i("rune dagger"),75, 85, 1}, {i("rune axe"), 75, 86, 1}, 
			{i("rune mace"),75, 87, 1}, {i("rune med helm"),75, 88, 1},
			{i("rune sword"),75, 89, 1},{i("rune scimitar"),150, 90, 2},
			{i("rune pickaxe"),150, 90, 2}, {i("rune longsword"),150, 91, 2}, 
			{i("rune full helm"),150, 92, 2}, {i("rune sq shield"),150, 93, 2}, 
			{i("rune warhammer"),225, 94, 3}, {i("rune battleaxe"),225, 95, 3}, 
			{i("rune chainbody"),225, 96, 3},{i("rune kiteshield"),225, 97, 3}, 
			{i("rune 2h sword"),225, 99, 3}, {i("rune plateskirt"),225, 99, 3}, 
			{i("rune platelegs"),225, 99, 3}, {i("rune platebody"),375, 99, 5}
			}, i("runite bar"));
		
		public int[][] items;
		public int reqbar;
		
		private data(final int[][] items, final int reqbar) {
		     this.items = items;
		     this.reqbar = reqbar;
		}
		public int getItemId(final int item) {
			for (int i = 0; i < items.length; i++) {
				if (item == items[i][0]) {
					return items[i][0];
				}
			}
			return -1;
		}
		public int getXp(final int xp) {
			for (int i = 0; i < items.length; i++) {
				if (xp == items[i][0]) {
					return items[i][1];
				}
			}
			return -1;
		}
		public int getLvlReq(int item) {
			for (int i = 0; i < items.length; i++) {
				if(item == items[i][0])
					return items[i][2];
			}
			return -1;
		}
		public int getBarAmount(final int amount) {
			for (int i = 0; i < items.length; i++) {
				if (amount == items[i][0]) {
					return items[i][3];
				}
			}
			return -1;
		}
		
		public int getReqBar() {
			return reqbar;
		}

	}
	
	/**
	 * Gets the item name.(Shortform)
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
	 * Check if the item is platelegs, it changes message
	 * 
	 * 
	 * @return
	 */
	public static String check(Client c, int item) {
		for (final data s : data.values()) {
			for (int i = 0; i < s.items.length; i++) {
				if (c.getItems().getItemName(item).contains("platelegs")) {
					return "You make a pair of ";
				}
			}
		}

		return "You make an ";
	}
	
	
}
