package server.clip.region;
import java.util.HashMap;

import server.game.players.Client;

/**
 * @author Genesis
 */

public class RegionHandler {

	public static HashMap<Client, Integer> areaRegionX = new HashMap<Client, Integer>();
	public static HashMap<Client, Integer> areaRegionY = new HashMap<Client, Integer>();

	public static void printRegion(Client c) {
		if (c.playerRights > 2) {
			c.sendMessage("RegionX: " + c.getX() / 64);
			c.sendMessage("RegionY: " + c.getY() / 64);
		}
	}

	public static boolean inRegion(Client c) {
		for (Client player : areaRegionX.keySet()) {
			int regionX =  areaRegionX.get(player);
			if (c.getX()/64 == regionX && player != c) {
				return true;
			}
		}
		return false;
	}
}
