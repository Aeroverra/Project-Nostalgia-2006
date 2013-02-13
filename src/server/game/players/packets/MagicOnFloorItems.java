package server.game.players.packets;
import server.Server;
import server.game.players.Client;
import server.game.players.PacketType;


/**
 * Magic on floor items
 **/
public class MagicOnFloorItems implements PacketType {

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		final int itemY = c.getInStream().readSignedWordBigEndian();
		final int itemId = c.getInStream().readUnsignedWord();
		final int itemX = c.getInStream().readSignedWordBigEndian();
		c.getInStream().readUnsignedWordA();

		if(!Server.itemHandler.itemExists(itemId, itemX, itemY)) {
			c.stopMovement();
			return;
		}
		c.usingMagic = true;
		if(!c.getCombat().checkMagicReqs(36)) {
			c.stopMovement();
			return;
		}
// dupe fixed!
		if(c.goodDistance(c.getX(), c.getY(), itemX, itemY, 12)) {
			if (System.currentTimeMillis() - c.lastGrab < 1200)
				return;								
			int offY = (c.getX() - itemX) * -1;
			int offX = (c.getY() - itemY) * -1;
			c.teleGrabX = itemX;
			c.teleGrabY = itemY;
			c.teleGrabItem = itemId;
			c.turnPlayerTo(itemX, itemY);	
			c.teleGrabDelay = System.currentTimeMillis();
			c.startAnimation(c.MAGIC_SPELLS[36][2]);
			Server.itemHandler.removeGroundItem(c, itemId, itemX, itemY, true);
			c.gfx100(c.MAGIC_SPELLS[36][3]);
			c.getPA().createPlayersStillGfx(144, itemX, itemY, 0, 72);
			c.getPA().createPlayersProjectile(c.getX(), c.getY(), offX, offY, 50, 70, c.MAGIC_SPELLS[36][4], 50, 10, 0, 50);
			c.getPA().addSkillXP(c.MAGIC_SPELLS[36][7], 6);
			c.getPA().refreshSkill(6);
			c.stopMovement();
		}
	}

}
