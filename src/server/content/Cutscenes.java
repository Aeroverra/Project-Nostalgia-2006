package server.content;

import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;

/**
 * Camera Movement
 * c.getPA().sendFrame177(a, b, c, d, e);
 * <p/>
 * A = X position of the camera. Use it INSIDE of the current map viewing area of the player. So if the player is standing in the Edgeville bank, don't make it go look at Lumbridge.
 * <p/>
 * B = Y position. Same as above.
 * <p/>
 * C = The height level of the camera. Same as A and B.
 * <p/>
 * D = Speed that the camera will move to this position.
 * <p/>
 * E = Angle of the camera.
 * <p/>
 * All of the above are integers.
 * <p/>
 * Start Earthquake
 * getPA().sendFrame35(a, b, c, d);
 * <p/>
 * I'm not completely sure what the individual arguments do, but they control the intensity of the earthquake. Play with them a bit to figure out what each of them do.
 * <p/>
 * Stop Earthquake/Reset Camera
 * getPA().sendFrame107();
 * <p/>
 * No arguments, this just resets camera and stops earthquakes.
 * <p/>
 * ADDITIONAL:
 * <p/>
 * Add this to client.java to make an earthquake randomizing method. Basically just call it and it will make an earthquake of random intensity.
 */

public class Cutscenes {

	public static void sendFrame177(Client c, int x, int y, int height, int speed, int angle) {
		c.getOutStream().createFrame(177);
		c.getOutStream().writeByte(x / 64); // X coord within your loaded map area
		c.getOutStream().writeByte(y / 64); // Y coord within your loaded map area
		c.getOutStream().writeWord(height); // HeightLevel
		c.getOutStream().writeByte(speed); //Camera Speed
		c.getOutStream().writeByte(angle); //Angle
	}

	public void sendFrame35(Client c, int i1, int i2, int i3, int i4) {
		c.getOutStream().createFrame(35);
		c.getOutStream().writeByte(i1);
		c.getOutStream().writeByte(i2);
		c.getOutStream().writeByte(i3);
		c.getOutStream().writeByte(i4);
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
	}

	public static void sendFrame107(Client c) {
		synchronized(c) {
			if(c.getOutStream() != null) {
				c.getOutStream().createFrame(107);
				c.flushOutStream();
			}
		}
	}

	/**
	 * Tutorial Cutscene
	 */

	/**
	 * Welcome to Falador Cutscene
	 * @param c Client c
	 */

	public static void handleTutorial(final Client c) {
		c.canDoStuff = false;
		sendFrame177(c, c.absX + 1000, - 20, 0, 2, 1);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.canDoStuff && c.outStream != null && !c.disconnected)
					sendFrame177(c, 3000, 3386, 0, 2, 1);
				container.stop();
			}

			@Override
			public void stop() {
				sendFrame107(c);
			}
		}, 13);
	}

}
