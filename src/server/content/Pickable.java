package server.content;

import server.Server;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;
/**
 * Handles pickable items
 * @author Administrator
 *
 */
public class Pickable {

	public static int[][] pickableItems = {
		{1161, 1965}, // Cabbage
		{313, 1947}, // Wheat
		{5585, 1947},
		{5584, 1947},
		{5585, 1947},
		{312, 1942},  // Potato
		{3366, 1957}, // Onion
	};

	/**
	 * PickObjects
	 *
	 * @param c             Client c
	 * @param objectClickId Object ID
	 * @param objectX       X Axis.
	 * @param objectY       Y Axis.
	 */

	public static void pickObject(final Client c, final int objectClickId, final int objectX, final int objectY) {
		if (c.miscTimer + 1800 > System.currentTimeMillis())
			return;
		for (int[] data : pickableItems) {
			final int objectId = data[0];
			int itemId = data[1];
			if (objectClickId == objectId) {
				c.getItems().addItem(itemId, 1);
				break;
			}
		}
		if (c.getItems().freeSlots() > 0) {
			c.turnPlayerTo(objectX, objectY);
			c.startAnimation(827);
			if ((objectClickId == 2646 && random(3) == 0) || objectClickId != 2646) {
				if (c.outStream != null) {
					Server.objectHandler.createAnObject(c, -1, objectX, objectY);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							container.stop();
						}
						@Override
						public void stop() {
							if (c.outStream != null)
								Server.objectHandler.createAnObject(c, objectClickId, objectX, objectY);
						}
					}, 5);
				}
			}
			c.getPA().sound(358);
			c.miscTimer = System.currentTimeMillis();
		} else {
			c.sendMessage("Your inventory is too full to hold any more items!");
		}
	}

	public static int random(int range) {
		return (int) (java.lang.Math.random() * (range + 1));
	}
}
