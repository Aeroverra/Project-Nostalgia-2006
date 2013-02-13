package server.game.items.food;

import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;

/**
 * Handles the eating of special consumables, such as pizzas, cakes, etc.
 * 
 * @author Relex lawl / Relex
 * 
 */

public class SpecialConsumables {

	private static enum Consumable {
		/**
		 * Cake.
		 */
		CAKE(4, 1891, 1893, 1895),
		/**
		 * Chocolate cake.
		 */
		CHOCOLATE_CAKE(5, 1897, 1899, 1901),
		/**
		 * Plain pizza.
		 */
		PLAIN_PIZZA(7, 2289, 2291),
		/**
		 * Meat pizza.
		 */
		MEAT_PIZZA(8, 2293, 2295),
		/**
		 * Anchovy pizza.
		 */
		ANCHOVY_PIZZA(9, 2297, 2299),
		/**
		 * Pineapple pizza.
		 */
		PINEAPPLE_PIZZA(11, 2301, 2303),
		/**
		 * Apple pies.
		 */
		APPLE_PIE(7, 2323, 2335),
		/**
		 * Redberry pies.
		 */
		REDBERRY_PIE(5, 2325, 2333),
		/**
		 * Meat pies.
		 */
		MEAT_PIE(6, 2327, 2331);
		/**
		 * The amount food heals.
		 */
		private int recoveryAmount;
		/**
		 * The id of the consumables.
		 */
		private int[] consumableIds;

		/**
		 * Constructor for enum.
		 * 
		 * @param recoveryAmount
		 *            Amount of constitution food heals.
		 * @param primaryId
		 *            Starting food id.
		 * @param leftOvers
		 *            Id of left over food.
		 */
		private Consumable(int recoveryAmount, int... consumableIds) {
			this.recoveryAmount = recoveryAmount;
			this.consumableIds = consumableIds;
		}

		/**
		 * Gets the amount of constitution player gets healed for.
		 */
		private final int getRecoveryAmount() {
			return recoveryAmount;
		}

		/**
		 * Gets the consumables' ids.
		 * 
		 * @return consumableIds.
		 */
		private final int[] getConsumableIds() {
			return consumableIds;
		}

		/**
		 * Gets the consumable's name.
		 * 
		 * @return Consumable's name.
		 */
		private final String getName() {
			return toString().toLowerCase().replaceAll("_", " ");
		}
	}

	/**
	 * Handles checking if food is a special consumable.
	 */
	public static final boolean isConsumable(Client c, final int itemId,
			final int slot) {
		Consumable consumable = getConsumableForId(itemId);
		if (consumable != null) {
			eatConsumable(c, itemId, slot, consumable);
			return true;
		}
		return false;
	}

	/**
	 * Handles eating the special consumables.
	 * 
	 * @param c
	 *            Client (player) eating the food.
	 * @param itemId
	 *            Item (id) player is eating.
	 * @param slot
	 *            Inventory slot item is being taken from.
	 * @param consumable
	 *            Consumable enum.
	 */
	private static void eatConsumable(final Client c, final int itemId,
			final int slot, Consumable consumable) {
		if (!c.getItems().playerHasItem(itemId) || c.playerLevel[3] <= 0)
			return;
		if (System.currentTimeMillis() - c.foodDelay >= 1500) {
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(itemId, slot, 1);
			if (getNextConsumableId(consumable, itemId) != -1)
				c.getItems()
						.addItem(getNextConsumableId(consumable, itemId), 1);
			c.playerLevel[3] = c.playerLevel[3]
					+ consumable.getRecoveryAmount() > c.getPA().getLevelForXP(
					c.playerXP[3]) ? c.getPA().getLevelForXP(c.playerXP[3])
					: c.playerLevel[3] + consumable.getRecoveryAmount();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the " + consumable.getName() + ".");
			c.foodDelay = System.currentTimeMillis();
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					c.sendMessage("It heals some health.");
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 2);
		}
	}

	/**
	 * Gets Consumable enum for a specific itemId.
	 * 
	 * @param itemId
	 *            Item id to make Food enum loop through to find.
	 */
	private static Consumable getConsumableForId(final int itemId) {
		for (Consumable c : Consumable.values()) {
			for (int i : c.getConsumableIds()) {
				if (i == itemId)
					return c;
			}
		}
		return null;
	}

	/**
	 * Gets the next consumable's item id.
	 * 
	 * @param consumable
	 *            Consumable enum.
	 * @param consumableId
	 *            Currect consumable's item id.
	 */
	private static final int getNextConsumableId(Consumable c,
			final int consumableId) {
		for (int i = 0; i < c.getConsumableIds().length; i++) {
			if (c.getConsumableIds()[i] == consumableId)
				return c.getConsumableIds()[i + 1];
		}
		return -1;
	}
}