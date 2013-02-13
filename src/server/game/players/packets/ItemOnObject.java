package server.game.players.packets;

/**
 * @author Ryan / Lmctruck30 / Aleksandr
 */

import server.game.items.UseItem;
import server.game.players.Client;
import server.game.players.PacketType;
import core.util.Misc;

public class ItemOnObject implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		/*
		 * a = ?
		 * b = ?
		 */
		c.getInStream().readUnsignedWord();
		int objectId = c.getInStream().readSignedWordBigEndian();
		c.objectY = c.getInStream().readSignedWordBigEndianA();
		c.getInStream().readUnsignedWord();
		c.objectX = c.getInStream().readSignedWordBigEndianA();
		int itemId = c.getInStream().readUnsignedWord();
		if(c.playerRights >= 3) {
			Misc.println("objectId: "+objectId+"  ObjectX: "+c.objectX+ "  objectY: "+c.objectY+" Xoff: "+ (c.getX() - c.objectX)+" Yoff: "+ (c.getY() - c.objectY)); 
		}		
		
		
		switch(c.objectId) {
		case 2781:
		    c.objectDistance = 2;
		    break;
			
		default:
			c.objectDistance = 1;
			c.objectXOffset = 0;
			c.objectYOffset = 0;
			break;	
				
		}
		
		if(c.goodDistance(c.objectX+c.objectXOffset, c.objectY+c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
			c.turnPlayerTo(c.objectX, c.objectY);
			UseItem.ItemonObject(c, objectId, c.objectX, c.objectY, itemId);
		} else {
			c.clickObjectType = 4;
			
		}
		
	}

}