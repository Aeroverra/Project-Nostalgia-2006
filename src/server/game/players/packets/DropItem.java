package server.game.players.packets;

import server.Config;
import server.Server;
import server.content.skills.misc.SkillHandler;
import server.game.players.Client;
import server.game.players.PacketType;

/**
 * Drop Item
 **/
public class DropItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readUnsignedWordA();
		c.getInStream().readUnsignedByte();
		c.getInStream().readUnsignedByte();
		int slot = c.getInStream().readUnsignedWordA();
		c.alchDelay = System.currentTimeMillis();
		if(c.arenas()) {
			c.sendMessage("You can't drop items inside the arena!");
			return;
		}

		if(itemId == 4045) {
			int explosiveHit = 15;
			c.startAnimation(827);
			c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
			c.handleHitMask(explosiveHit);
			c.dealDamage(explosiveHit);
			c.getPA().refreshSkill(3);
			c.forcedText = "Ow! That really hurt!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
		}

		boolean droppable = true;
		for (int i : Config.UNDROPPABLE_ITEMS) {
			if (i == itemId) {
				droppable = false;
				break;
			}
		}
		if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
			if(droppable) {
				if (c.underAttackBy > 0) {
					if (c.getShops().getItemShopValue(itemId) > 1000) {
						c.sendMessage("You cannot drop an item more than 1000 coins during combat.");
						return;
					}
				}
				// Reset skills

				SkillHandler.resetallSkills(c);
				c.getPA().sound(376);
				Server.itemHandler.createGroundItem(c, itemId, c.getX(), c.getY(), c.playerItemsN[slot], c.getId());
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
			} else {
				c.sendMessage("This items cannot be dropped.");
			}
		}

	}
}
