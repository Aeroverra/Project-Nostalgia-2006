package server.game.players.packets;

/**
 * @author Ryan / Lmctruck30
 */


import server.content.skills.Firemaking;
import server.content.skills.cooking.MixItems;
import server.content.skills.crafting.JewelryMaking;
import server.game.items.UseItem;
import server.game.objects.Objects;
import server.game.players.Client;
import server.game.players.PacketType;

public class ItemOnItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int usedWithSlot = c.getInStream().readUnsignedWord();
		int itemUsedSlot = c.getInStream().readUnsignedWordA();
		int useWith = c.playerItems[usedWithSlot] - 1;
		int itemUsed = c.playerItems[itemUsedSlot] - 1;
		if(!c.getItems().playerHasItem(useWith, 1, usedWithSlot) || !c.getItems().playerHasItem(itemUsed, 1, itemUsedSlot)) {
			return;
		}
		if(MixItems.mixItems(c, itemUsed, useWith, itemUsedSlot, usedWithSlot))
	            return;
		if (Firemaking.playerLogs(c, itemUsed, useWith)) {
			Firemaking.grabData(c,itemUsed, useWith);

		}
		
		if (itemUsed == 1759 || useWith == 1759) {
			JewelryMaking.stringAmulet(c, itemUsed, useWith);
		}
		
		/*} else if((useWith == 1511 || itemUsed == 1511) && (useWith == 946 || itemUsed == 946)) {
			Fletching.normal(c, itemUsed, useWith);
		} else if(useWith == 946 || itemUsed == 946) {
			Fletching.others(c, itemUsed, useWith);
		} else if(Fletching.arrows(c, itemUsed, useWith)) {
			Fletching.makeArrows(c, itemUsed, useWith);
		}*/
		UseItem.ItemonItem(c, itemUsed, useWith);
	}
}

