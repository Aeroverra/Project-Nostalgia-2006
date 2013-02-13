package server.game.players.packets;

import server.Server;
import server.game.players.Client;
import server.game.players.PacketType;
import core.util.Misc;

public class ItemOnGroundItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.getInStream().readSignedWordBigEndian();
		int itemUsed = c.getInStream().readSignedWordA();
		int groundItem = c.getInStream().readUnsignedWord();
		int gItemY = c.getInStream().readSignedWordA();
		int itemUsedSlot = c.getInStream().readSignedWordBigEndianA();
		int gItemX = c.getInStream().readUnsignedWord();
		if(!c.getItems().playerHasItem(itemUsed, 1, itemUsedSlot)) {
			return;
		}
		if(!Server.itemHandler.itemExists(groundItem, gItemX, gItemY)) {
			return;
		}

		switch(itemUsed) {

		default:
			if(c.playerRights == 3)
				Misc.println("ItemUsed "+itemUsed+" on Ground Item "+groundItem);
			break;
		}
	}

}
