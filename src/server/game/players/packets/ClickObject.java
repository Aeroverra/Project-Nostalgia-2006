package server.game.players.packets;

import server.content.skills.Runecrafting;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.objects.doors.Doors;
import server.game.players.Client;
import server.game.players.PacketType;
import core.util.Misc;

/**
 * Click Object
 */
public class ClickObject implements PacketType {

	public static final int FIRST_CLICK = 132, SECOND_CLICK = 252,
			THIRD_CLICK = 70;

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		if (c.spinningPlate) {
			return;
		}
		c.clickObjectType = 0;
		c.objectX = 0;
		c.objectId = 0;
		c.objectY = 0;
		c.objectYOffset = 0;
		c.objectXOffset = 0;
		c.lastX = c.getX();
		c.lastY = c.getY();
		c.getPA().resetFollow();
		c.getCombat().resetPlayerAttack();
		c.getPA().requestUpdates();
		switch (packetType) {

		case FIRST_CLICK:
			c.objectX = c.getInStream().readSignedWordBigEndianA();
			c.objectId = c.getInStream().readUnsignedWord();
			c.objectY = c.getInStream().readUnsignedWordA();
			c.objectDistance = 1;
			c.turnPlayerTo(c.objectX, c.objectY);


			if (c.playerRights >= 3) {
				Misc.println("objectId: " + c.objectId + "  ObjectX: "
						+ c.objectX + "  objectY: " + c.objectY + " Xoff: "
						+ (c.getX() - c.objectX) + " Yoff: "
						+ (c.getY() - c.objectY));
			} else if (c.playerRights == 3) {
				c.sendMessage("objectId: " + c.objectId + " objectX: "
						+ c.objectX + " objectY: " + c.objectY);
			}
			if (Math.abs(c.getX() - c.objectX) > 25
					|| Math.abs(c.getY() - c.objectY) > 25) {
				c.resetWalkingQueue();
				break;
			}
			for (int i = 0; i < Runecrafting.altarID.length; i++) {
				if (c.objectId == Runecrafting.altarID[i]) {
					Runecrafting.craftRunes(c, c.objectId);
				}
			}
			if (Math.abs(c.getX() - c.objectX) > 25
					|| Math.abs(c.getY() - c.objectY) > 25) {
				c.resetWalkingQueue();
				break;
			}
			switch (c.objectId) {
			case 2491:
				c.objectDistance = 5;
				break;
			case 1722:
				c.objectYOffset = 3;
				c.objectDistance = 2;
				break;
			case 1723:
				c.objectYOffset = -1;
				c.objectDistance = 2;
				break;
			case 3192:
				c.getHighscores().openHighscores();
				break;
			case 3033:
				c.objectXOffset = 2;
				c.objectYOffset = 1;
				break;
			case 1733:
				if(c.objectX == 3058 && c.objectY == 3376) {
				    c.objectXOffset = 3;
					c.objectYOffset = 0;
				}
				break;
			case 3044:
				c.objectDistance = 3;
				break;
			case 1739:
			case 1738:
				if (c.objectY == 3447 && c.objectX == 3144) {
					c.objectDistance = (int) 1.8;
					break;
				}
				c.objectXOffset = 1;
				c.objectYOffset = 2;
				break;
			case 245:
				c.objectYOffset = -1;
				c.objectDistance = 0;
				break;
			case 272:
				c.objectYOffset = 1;
				c.objectDistance = 0;
				break;

			case 273:
				c.objectYOffset = 1;
				c.objectDistance = 0;
				break;

			case 246:
				c.objectYOffset = 1;
				c.objectDistance = 0;
				break;
			case 11666:
				c.objectDistance = 2;
				break;
			case 4493:
			case 4494:
			case 4496:
			case 4495:
				c.objectDistance = 5;
				break;
			case 10229:
			case 6522:
				c.objectDistance = 2;
				break;
			case 8959:
				c.objectYOffset = 1;
				break;
			case 4417:
				if (c.objectX == 2425 && c.objectY == 3074)
					c.objectYOffset = 2;
				break;
			case 4420:
				if (c.getX() >= 2383 && c.getX() <= 2385) {
					c.objectYOffset = 1;
				} else {
					c.objectYOffset = -2;
				}
			case 2617:
				c.objectYOffset = 3;
				c.objectXOffset = 2;
				break;
			case 6552:
			case 409:
			case 399:
			case 398:
			case 2145:
			case 2146:
				c.objectDistance = 2;
				break;
			case 2879:
			case 2878:
				c.objectDistance = 3;
				break;
			case 2558:
				c.objectDistance = 0;
				if (c.absX > c.objectX && c.objectX == 3044)
					c.objectXOffset = 1;
				if (c.absY > c.objectY)
					c.objectYOffset = 1;
				if (c.absX < c.objectX && c.objectX == 3038)
					c.objectXOffset = -1;
				break;
			case 9356:
				c.objectDistance = 2;
				break;
			case 5959:
			case 1815:
			case 5960:
			case 1816:
				c.objectDistance = 0;
				break;

			case 9293:
				c.objectDistance = 2;
				break;
			case 4418:
				if (c.objectX == 2374 && c.objectY == 3131)
					c.objectYOffset = -2;
				else if (c.objectX == 2369 && c.objectY == 3126)
					c.objectXOffset = 2;
				else if (c.objectX == 2380 && c.objectY == 3127)
					c.objectYOffset = 2;
				else if (c.objectX == 2369 && c.objectY == 3126)
					c.objectXOffset = 2;
				else if (c.objectX == 2374 && c.objectY == 3131)
					c.objectYOffset = -2;
				break;
			case 9706:
				c.objectDistance = 0;
				c.objectXOffset = 1;
				break;
			case 9707:
				c.objectDistance = 0;
				c.objectYOffset = -1;
				break;
			case 4419:
			case 6707: // verac
				c.objectYOffset = 3;
				break;
			case 6823:
				c.objectDistance = 2;
				c.objectYOffset = 1;
				break;
			case 12536: // wizard stairs
				c.objectXOffset = 2;
				c.objectYOffset = 1;
				break;
			case 12537: // wizard stairs
				c.objectXOffset = 1;
				c.objectYOffset = 2;
				break;
			case 6773:
				c.objectDistance = 2;
				c.objectXOffset = 1;
				c.objectYOffset = 1;
				break;
			case 6821:
				c.objectDistance = 2;
				c.objectXOffset = 1;
				c.objectYOffset = 1;
				break;
			case 1276:
			case 3879:
			case 3883:
			case 1278:// trees
			case 1281: // oak
			case 1308: // willow
			case 1307: // maple
			case 1309: // yew
			case 1306: // yew
			case 5551: // willow
			case 5553: // willow
			case 5552:// willow
				c.objectDistance = 3;
				break;
			default:
				c.objectDistance = 1;
				c.objectXOffset = 0;
				c.objectYOffset = 0;
				break;
			}
			if (c.goodDistance(c.objectX + c.objectXOffset, c.objectY
					+ c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
				if (Doors.getSingleton().handleDoor(c, c.objectId, c.objectX,
						c.objectY, c.heightLevel)) {
				}
				c.getActions().firstClickObject(c.objectId, c.objectX,
						c.objectY);
			
			} else {
				c.clickObjectType = 1;
				c.walkingtoObject = true;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {// animation
							@Override
							public void execute(CycleEventContainer container) {
								if (!c.walkingtoObject)
									container.stop();
								if (c.goodDistance(c.objectX + c.objectXOffset,
										c.objectY + c.objectYOffset, c.getX(),
										c.getY(), c.objectDistance)) {
									if (Doors.getSingleton().handleDoor(c, c.objectId, c.objectX,
											c.objectY, c.heightLevel)) {
									}
									c.getActions().firstClickObject(c.objectId,
											c.objectX, c.objectY);
									
									container.stop();
								}

							}

							@Override
							public void stop() {
								c.walkingtoObject = false;
							}
						}, 1);
			}
			break;

		case SECOND_CLICK:
			c.objectId = c.getInStream().readUnsignedWordBigEndianA();
			c.objectY = c.getInStream().readSignedWordBigEndian();
			c.objectX = c.getInStream().readUnsignedWordA();
			c.objectDistance = 1;
			c.turnPlayerTo(c.objectX, c.objectY);
			if (c.playerRights >= 3) {
				Misc.println("objectId: " + c.objectId + "  ObjectX: "
						+ c.objectX + "  objectY: " + c.objectY + " Xoff: "
						+ (c.getX() - c.objectX) + " Yoff: "
						+ (c.getY() - c.objectY));
			}

			switch (c.objectId) {
			case 2491:
				c.objectDistance = 5;
				break;
			case 2781:
				c.objectXOffset = 3;
				c.objectYOffset = 1;
				break;
			case 6163:
			case 6165:
			case 6166:
			case 6164:
			case 6162:
				c.objectDistance = 2;
				break;
			default:
				c.objectDistance = 1;
				c.objectXOffset = 0;
				c.objectYOffset = 0;
				break;

			}
			if (c.goodDistance(c.objectX + c.objectXOffset, c.objectY
					+ c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
				c.getActions().secondClickObject(c.objectId, c.objectX,
						c.objectY);
			} else {
				c.clickObjectType = 2;
				c.clickObjectType = 1;
				c.walkingtoObject = true;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {// animation
							@Override
							public void execute(CycleEventContainer container) {
								if (!c.walkingtoObject)
									container.stop();
								if (c.goodDistance(c.objectX + c.objectXOffset,
										c.objectY + c.objectYOffset, c.getX(),
										c.getY(), c.objectDistance)) {
									c.getActions().secondClickObject(c.objectId,
											c.objectX, c.objectY);
									
									container.stop();
								}

							}

							@Override
							public void stop() {
								c.walkingtoObject = false;
							}
						}, 1);
			}
			break;

		case THIRD_CLICK:
			c.turnPlayerTo(c.objectX, c.objectY);
			c.objectX = c.getInStream().readSignedWordBigEndian();
			c.objectY = c.getInStream().readUnsignedWord();
			c.objectId = c.getInStream().readUnsignedWordBigEndianA();

			if (c.playerRights >= 3) {
				Misc.println("objectId: " + c.objectId + "  ObjectX: "
						+ c.objectX + "  objectY: " + c.objectY + " Xoff: "
						+ (c.getX() - c.objectX) + " Yoff: "
						+ (c.getY() - c.objectY));
			}

			switch (c.objectId) {
			default:
				c.objectDistance = 1;
				c.objectXOffset = 0;
				c.objectYOffset = 0;
				break;
			}
			if (c.goodDistance(c.objectX + c.objectXOffset, c.objectY
					+ c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
				c.getActions().secondClickObject(c.objectId, c.objectX,
						c.objectY);
			} else {
				c.clickObjectType = 3;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if (c.clickObjectType == 3
								&& c.goodDistance(c.objectX + c.objectXOffset,
										c.objectY + c.objectYOffset, c.getX(),
										c.getY(), c.objectDistance)) {
							c.getActions().thirdClickObject(c.objectId,
									c.objectX, c.objectY);
							container.stop();
						}
						if (c.clickObjectType < 3)
							container.stop();
					}

					@Override
					public void stop() {
						c.clickObjectType = 0;
					}
				}, 1);
			}
			break;
		}

	}

	public void handleSpecialCase(Client c, int id, int x, int y) {

	}

}
