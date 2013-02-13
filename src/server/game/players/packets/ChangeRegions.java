package server.game.players.packets;

import server.Server;
import server.content.music.Music;
import server.game.items.GlobalDropsHandler;
import server.game.players.Client;
import server.game.players.PacketType;
import server.game.players.PlayerSave;

/**
 * Change Regions
 */
public class ChangeRegions implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
	    System.out.println("Region packet received!");
		Music.playMusic(c);
		if (c.playerRights == 3 || c.playerRights == 5)
			Music.checkMusic(c);

		Server.itemHandler.reloadItems(c);
		Server.objectHandler.updateObjects(c);
		GlobalDropsHandler.load(c);

		PlayerSave.saveGame(c);// temp

		if(c.skullTimer > 0) {
			c.isSkulled = true;
			c.headIconPk = 0;
			c.getPA().requestUpdates();
		}

	}

}
