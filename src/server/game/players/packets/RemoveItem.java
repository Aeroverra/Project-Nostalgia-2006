package server.game.players.packets;

import server.content.skills.Smithing;
import server.content.skills.crafting.JewelryMaking;
import server.game.players.Client;
import server.game.players.PacketType;

/**
 * Remove Item
 **/
public class RemoveItem implements PacketType {

    @Override
    public void processPacket(Client c, int packetType, int packetSize) {
	int interfaceId = c.getInStream().readUnsignedWordA();
	int removeSlot = c.getInStream().readUnsignedWordA();
	int removeId = c.getInStream().readUnsignedWordA();
	if (c.playerRights == 3)
	    System.out.println("interface " + interfaceId);

	switch (interfaceId) {

	case 4233:
	    JewelryMaking.jewelryMaking(c, "RING", removeId, 1);
	    break;
	case 4239:
	    JewelryMaking.jewelryMaking(c, "NECKLACE", removeId, 1);
	    break;
	case 4245:
	    JewelryMaking.jewelryMaking(c, "AMULET", removeId, 1);
	    break;
	case 7423:
	    if (c.inTrade) {
		c.getTradeAndDuel().declineTrade(true);
		return;
	    }
	    c.getItems().bankItem(removeId, removeSlot, 1);
	    c.getItems().resetItems(7423);
	    break;
	case 1688:
	    c.getItems().removeItem(removeId, removeSlot);
	    break;

	case 5064:
	    c.getItems().bankItem(removeId, removeSlot, 1);
	    break;

	case 5382:
	    c.getItems().fromBank(removeId, removeSlot, 1);
	    break;

	case 3900:
	    if (c.inTrade) {
                c.getTradeAndDuel().declineTrade(true);
                return;
            }
	    c.getShops().buyFromShopPrice(removeId);
	    break;

	case 3823:
	    if (c.inTrade) {
                c.getTradeAndDuel().declineTrade(true);
                return;
            }
	    c.getShops().sellToShopPrice(removeId);
	    break;

	case 3322:
	    if (c.duelStatus <= 0) {
		c.getTradeAndDuel().tradeItem(removeId, removeSlot, 1);
	    } else {
		c.getTradeAndDuel().stakeItem(removeId, removeSlot, 1);
	    }
	    break;

	case 3415:
	    if (c.duelStatus <= 0) {
		c.getTradeAndDuel().fromTrade(removeId, removeSlot, 1);
	    }
	    break;

	case 6669:
	    c.getTradeAndDuel().fromDuel(removeId, removeSlot, 1);
	    break;
	case 1119:
	case 1120:
	case 1121:
	case 1122:
	case 1123:
	    Smithing.smithing(c, removeId, 1);
	    break;

	}
    }

}
