package server.content;

import server.Server;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;
import core.util.Misc;

/**
 * @author Genesis, modified by dark
 */
public class SpinningPlates {
    public static void spinningPlate(final Client c, final int itemId, final int itemSlot) {
        c.spinningPlate = true;
        c.startAnimation(1902);
        CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
            @Override
            public void execute(CycleEventContainer container) {
                container.stop();
            }

            @Override
            public void stop() {
                if (c != null && c.outStream != null) {
                    if (Misc.random(1) > 0) {
                        c.startAnimation(1905);
                        CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
                            @Override
                            public void execute(CycleEventContainer container) {
                                c.startAnimation(862);// if sucess
                                c.spinningPlate = false;
                                container.stop();
                            }

                            @Override
                            public void stop() {
                            }
                        }, Misc.random(5, 7));
                    } else {
                        c.startAnimation(1906);
                        c.sendMessage("Your plate drops on the ground.");
                        if (c.getItems().playerHasItem(itemId, 1, itemSlot)) {
                            Server.itemHandler.createGroundItem(c, itemId, c.getX(), c.getY(), 1, c.playerId);
                            c.getItems().deleteItem(itemId, itemSlot, 1);
                        }
                        CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
                            @Override
                            public void execute(CycleEventContainer container) {
                                c.startAnimation(860);// if fail
                                c.spinningPlate = false;
                                container.stop();
                            }

                            @Override
                            public void stop() {
                            }
                        }, Misc.random(4, 7));
                    }
                }
            }
        }, Misc.random(5, 8));
    }
}
