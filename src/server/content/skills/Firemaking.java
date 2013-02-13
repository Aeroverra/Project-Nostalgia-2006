package server.content.skills;

import server.Config;
import server.Server;
import server.clip.region.Region;
import server.content.quests.misc.Tutorialisland;
import server.content.skills.cooking.Cooking;
import server.content.skills.misc.SkillHandler;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.objects.Objects;
import server.game.players.Client;
import server.world.Tile;
import core.util.Misc;

public class Firemaking extends SkillHandler {
private static int eventId = 11;
	private static int[][] logsdata = {
		{1511, 1,  40,  2732},
		{2862, 1,  40,  2732},
		{1521, 15, 60,  2732},
		{1519, 30, 105, 2732},
		{6333, 35, 105, 2732},
		{1517, 45, 135, 2732},
		{10810,45, 135, 2732},
		{6332, 50, 158, 2732},
		{1515, 60, 203, 2732},
		{1513, 75, 304, 2732},
	};

	public static boolean playerLogs(Client c, int i, int l) {
		boolean flag = false;
		for(int kl = 0; kl < logsdata.length; kl++) {
			if((i == logsdata[kl][0] && requiredItem(c, l)) || (requiredItem(c, i) && l == logsdata[kl][0])) {
				flag = true;
			}
		}
		return flag;
	}

	private static int getAnimation(Client c, int item, int item1) {
		c.getPA().sendSound(375, 100, 1); // firemake sound
		return 733;
	}

	private static boolean requiredItem(Client c, int i) {
		int[] data = {590};
		for(int l = 0; l < data.length; l++) {
			if(i == data[l]) {
				return true;
			}
		}
		return false;
	}

	public static void grabData(final Client c, final int useWith, final int withUse) {
		if(!Config.FIREMAKING_ENABLED) {
			c.sendMessage("Firemaking is currently disabled.");
			return;
		}
		for (Objects o : Server.objectHandler.globalObjects) {
			if (o.getObjectX() == c.getX() && o.getObjectY() == c.getY() && o.objectId != -1) {
				c.sendMessage("You can't light a fire here!");
				return;
			}
		}
		if(c.tutorialprog == 4) {
			final int[] coords = new int[2];
			coords[0] = c.absX;
			coords[1] = c.absY;
			for(int i = 0; i < logsdata.length; i++) {
				if((requiredItem(c, useWith) && withUse == logsdata[i][0] || useWith == logsdata[i][0] && requiredItem(c, withUse))) {
					if(c.playerLevel[11] < logsdata[i][1]) {
						c.sendMessage("You need a higher firemaking level to light this log!");
						return;
					}
					if (System.currentTimeMillis() - c.lastFire > 1200) {

						if(c.playerIsFiremaking) {
							return;
						}

						final int[] time = new int[3];
						final int log = logsdata[i][0];
						final int fire = logsdata[i][3];
						if(System.currentTimeMillis() - c.lastFire > 3000) {
							c.startAnimation(getAnimation(c, useWith, withUse));
							time[0] = 4;
							time[1] = 3;
						} else {
							time[0] = 1;
							time[1] = 2;
						}
						Tutorialisland.chatbox(c, 6180);
						Tutorialisland.chatboxText
						(c, "",
								"Your character is now attempting to light the fire.",
								"This should only take a few seconds.",
								"",
								"Please wait");
						Tutorialisland.chatbox(c, 6179);
						c.playerIsFiremaking = true;
						Server.itemHandler.createGroundItem(c, log, coords[0], coords[1], 1, c.getId());
						CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer container) {
								Server.objectHandler.createAnObject(c, fire, coords[0], coords[1]);
								Server.itemHandler.removeGroundItem(c, log, coords[0], coords[1], false);
								c.playerIsFiremaking = false;
								container.stop();
							}
							@Override
							public void stop() {

							}
						}, time[0]);
						new Tile(c.absX - 1, c.absY, c.heightLevel);

						if (Region.getClipping(c.getX() - 1, c.getY(), c.heightLevel, -1, 0)) {
							c.getPA().walkTo(-1, 0);
						} else if (Region.getClipping(c.getX() + 1, c.getY(), c.heightLevel, 1, 0)) {
							c.getPA().walkTo(1, 0);
						} else if (Region.getClipping(c.getX(), c.getY() - 1, c.heightLevel, 0, -1)) {
							c.getPA().walkTo(0, -1);
						} else if (Region.getClipping(c.getX(), c.getY() + 1, c.heightLevel, 0, 1)) {
							c.getPA().walkTo(0, 1);
						}
						c.getPA().drawHeadicon(1, 2, 0, 0); // sends to Survival Expert
						CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer container) {
								c.startAnimation(65535);
								container.stop();
							}
							@Override
							public void stop() {
							}
						}, time[1]);
						CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer container) {
								Server.objectHandler.createAnObject(c, -1, coords[0], coords[1]);
								Server.itemHandler.createGroundItem(c, 592, coords[0], coords[1], 1, c.getId());
								container.stop();
							}
							@Override
							public void stop() {

							}
						}, 120);
						c.getItems().deleteItem(logsdata[i][0], c.getItems().getItemSlot(logsdata[i][0]), 1);
						c.turnPlayerTo(c.absX+1, c.absY);
						Tutorialisland.sendDialogue(c, 3016, 0);
						c.getPA().addXPTut(83, 11); // levels up to 2
						c.lastFire = System.currentTimeMillis();
					}
				}
			}
			return;
		}
		// normal firemake
		final int[] coords = new int[2];
		coords[0] = c.absX;
		coords[1] = c.absY;

		for(int i = 0; i < logsdata.length; i++) {
			if((requiredItem(c, useWith) && withUse == logsdata[i][0] || useWith == logsdata[i][0] && requiredItem(c, withUse))) {
				if(c.playerLevel[11] < logsdata[i][1]) {
					c.sendMessage("You need a higher firemaking level to light these logs.");
					return;
				}
				if (System.currentTimeMillis() - c.lastFire > 1200) {

					if(c.playerIsFiremaking) {
						return;
					}
					/* /**********Timers********** *///
					final int lvl = c.playerLevel[11];
					int newTimer = Misc.random(17, 24) + logsdata[i][1]
							- lvl ;
					if (newTimer < 0) {
						newTimer = 4;
					}
					if (logsdata[i][1] == 1 && lvl >= 15)
						newTimer = 4;//if log&lvl > 15, no long burn.
					if (logsdata[i][1] == 15 && lvl >= 30)
						newTimer = 4;//if Oak&lvl > 30, no long burn.
					if (logsdata[i][1] == 30 && lvl >= 45)
						newTimer = 4;//if Willow&lvl > 30, no long burn.
					else
					if (lvl >= 70) {
						newTimer = 4;
					}
					/* /**********Timers********** *///
					final int[] time = new int[3];
					final int log = logsdata[i][0];
					final int fire = logsdata[i][3];
					if(System.currentTimeMillis() - c.lastFire > (newTimer == 4 ? 3600 : 15500)) {
						//Misc.random(3000,15500)
						c.startAnimation(getAnimation(c, useWith, withUse));
						time[0] = newTimer;
						c.sendMessage("" + newTimer);
					} else {
						time[0] = 1;
					}

					c.playerIsFiremaking = true;
					Server.itemHandler.createGroundItem(c, log, coords[0], coords[1], 1, c.getId());
					CycleEventHandler.getSingleton().addEvent(eventId,c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							c.sendMessage("The fire catches and the logs begin to burn.");
							Server.objectHandler.createAnObject(c, fire, coords[0], coords[1]);
							Server.itemHandler.removeGroundItem(c, log, coords[0], coords[1], false);
							resetFM(c);
							for(int i = 0; i < logsdata.length; i++) {
								if((requiredItem(c, useWith) && withUse == logsdata[i][0] || useWith == logsdata[i][0] && requiredItem(c, withUse))) {
									c.getPA().addSkillXP(logsdata[i][2], 11);
								}
							}
							container.stop();
						}
						@Override
						public void stop() {

						}
					}, time[0]);
					CycleEventHandler.getSingleton().addEvent(eventId,c, new CycleEvent() {
						@Override // animation
						public void execute(CycleEventContainer container) {
							if(!c.playerIsFiremaking) {
								c.startAnimation(65535);
								resetFM(c);
								container.stop();
								return;
							}
							c.startAnimation(getAnimation(c, useWith, withUse));
						}
						@Override
						public void stop() {

						}
					}, 13);
					CycleEventHandler.getSingleton().addEvent(eventId,c, new CycleEvent() {
						@Override // sound
						public void execute(CycleEventContainer container) {
							if(!c.playerIsFiremaking) {
								c.startAnimation(65535);
								resetFM(c);
								container.stop();
								return;
							}
							c.getPA().sendSound(375, 100, 1); // firemake sound
						}
						@Override
						public void stop() {

						}
					}, 4);
					new Tile(c.absX - 1, c.absY, c.heightLevel);

					if (Region.getClipping(c.getX() - 1, c.getY(), c.heightLevel, -1, 0)) {
						c.getPA().walkTo(-1, 0);
					} else if (Region.getClipping(c.getX() + 1, c.getY(), c.heightLevel, 1, 0)) {
						c.getPA().walkTo(1, 0);
					} else if (Region.getClipping(c.getX(), c.getY() - 1, c.heightLevel, 0, -1)) {
						c.getPA().walkTo(0, -1);
					} else if (Region.getClipping(c.getX(), c.getY() + 1, c.heightLevel, 0, 1)) {
						c.getPA().walkTo(0, 1);
					}
					c.sendMessage("You light the logs on fire.");
					
					CycleEventHandler.getSingleton().addEvent(null, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							Server.objectHandler.createAnObject(c, -1, coords[0], coords[1]);
							if (c != null && c.outStream != null)
							Server.itemHandler.createGroundItem(c, 592, coords[0], coords[1], 1, c.getId());					
							container.stop();
						}
						@Override
						public void stop() {

						}
					}, 120);
					c.getItems().deleteItem(logsdata[i][0], c.getItems().getItemSlot(logsdata[i][0]), 1);
					c.turnPlayerTo(c.absX+1, c.absY);
					c.lastFire = System.currentTimeMillis();
				}
			}
		}
	}
	public static void resetFM(Client c) {
		stopEvents(c,eventId);
		c.playerIsFiremaking = false;
		c.startAnimation(65535);
	}
}