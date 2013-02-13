package server.game.objects.ladders;

import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;
import server.game.players.Client;

/**
 * @author Genesis
 */
public class LadderHandler {

	public static void climbLadder(final Client c, final int x, final int y, final int currentHeight, final int heightLevel) {
		if (currentHeight != c.heightLevel) {
			return;
		}
		if (!c.cantClimbLadder && c.heightLevel == currentHeight) {
			c.startAnimation(828);
			c.cantClimbLadder = true;
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer p) {
					if (c != null && c.outStream != null)
						c.getPA().movePlayer(x, y, heightLevel);
					p.stop();
				}
			}, 1000);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					container.stop();
				}

				@Override
				public void stop() {
					if (c != null && c.outStream != null)
						c.cantClimbLadder = false;
				}
			}, 3);
		}
	}

	public final static int[] Coordinates = {
		2400, 3107,
		2399, 3100,
		2430, 3081,
		2369, 3126};

	public static void climbStairs(final Client c, final int x, final int y, final int currentHeight, final int heightLevel) {
		if (currentHeight != c.heightLevel) {
			return;
		}
		if (!c.cantClimbLadder && c.heightLevel == currentHeight) {
			c.cantClimbLadder = true;
			if (c != null && c.outStream != null)
				c.getPA().movePlayer(x, y, heightLevel);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					container.stop();
				}

				@Override
				public void stop() {
					if (c != null && c.outStream != null)
						c.cantClimbLadder = false;
				}
			}, 3);
		}
	}
}
