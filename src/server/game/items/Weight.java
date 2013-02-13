package server.game.items;

import server.Server;
import server.game.players.Client;

/**
 * @author somedude, credits to Galkon for item weights
 * 
 */
public class Weight extends ItemDefinitions {

	/**
	 * Calculates the weight when doing actions
	 * 
	 * @param c
	 * @param item
	 * @param action
	 *            - deleteitem, additem, equip, unequip.
	 */
	public static void calcWeight(Client c, int item, String action) {
		if (action.equalsIgnoreCase("deleteitem")) {
			if (getWeight(item) > 99.20) {
				c.weight -= getWeight(item) / 100;
				if (c.weight < 0)
					c.weight = 0.0;
				c.getPA().writeWeight((int) c.weight);
				return;
			}
			c.weight -= getWeight(item) / 10;
			if (c.weight < 0) 
				c.weight = 0.0;
			c.getPA().writeWeight((int) c.weight);
		} else if (action.equalsIgnoreCase("additem")) {
			if (getWeight(item) > 99.20) {
				c.weight += getWeight(item) / 100;
				c.getPA().writeWeight((int) c.weight);
				return;
			}
			c.weight += getWeight(item) / 10;
			c.getPA().writeWeight((int) c.weight);
		}
	}

	/**
	 * Updates the weight for inventory and equipment.
	 * 
	 * @param c
	 */
	public static void updateWeight(Client c) {
		if (c != null) {
			c.getPA().writeWeight((int) c.weight);
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] > -1) {// inventory
					for (ItemList i1 : Server.itemHandler.ItemList) {
						if (i1 != null) {
							if (i1.itemId == c.playerItems[i]) {
								calcWeight(c, c.playerItems[i], "addItem");
							}
						}
					}
				}
			} // equipment
			for (int i = 0; i < c.playerEquipment.length; i++) {
				if (c.playerEquipment[i] > -1) {// equipment
					for (ItemList i1 : Server.itemHandler.ItemList) {
						if (i1 != null) {
							if (i1.itemId == c.playerEquipment[i]) {
								calcWeight(c, c.playerEquipment[i], "addItem");
							}
						}
					}
				}
			}
		}
		c.getPA().writeWeight((int) c.weight);
	}
}
