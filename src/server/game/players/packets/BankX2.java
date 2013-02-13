package server.game.players.packets;

import server.game.players.Client;
import server.game.players.PacketType;
/**
 * Bank X Items
 **/
public class BankX2 implements PacketType {
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int Xamount = c.getInStream().readDWord();
		if (Xamount == 0)
			Xamount = 1;
		
		if(c.playerRights == 3) {
		int level = Xamount;
		int skill = c.selectedSkill;
		if (level > 99)
			level = 99;
		else if (level < 0)
			level = 1;
		c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
		c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
		c.getPA().refreshSkill(skill);
		}
    

		switch(c.xInterfaceId) {
		case 5064:
			c.getItems().bankItem(c.playerItems[c.xRemoveSlot] , c.xRemoveSlot, Xamount);
			break;
		case 7423:
			if (c.storing) {
			return;
			}
			c.getItems().bankItem(c.playerItems[c.xRemoveSlot] , c.xRemoveSlot, Xamount);
			c.getItems().resetItems(7423);
			break;
		case 5382:
			c.getItems().fromBank(c.bankItems[c.xRemoveSlot] , c.xRemoveSlot, Xamount);
			break;

		case 3322:
			if(!c.getItems().playerHasItem(c.xRemoveId, Xamount))
				return;
			if (c.duelStatus <= 0) {
				if (Xamount > c.getItems().getItemAmount(c.xRemoveId))
					c.getTradeAndDuel().tradeItem(c.xRemoveId, c.xRemoveSlot,
							c.getItems().getItemAmount(c.xRemoveId));
				else
					c.getTradeAndDuel().tradeItem(c.xRemoveId, c.xRemoveSlot,
							Xamount);
			} else {
				if (Xamount > c.getItems().getItemAmount(c.xRemoveId))
					c.getTradeAndDuel().stakeItem(c.xRemoveId, c.xRemoveSlot,
							c.getItems().getItemAmount(c.xRemoveId));
				else
					c.getTradeAndDuel().stakeItem(c.xRemoveId, c.xRemoveSlot,
							Xamount);
			}
			break;

		case 3415:
			if(!c.getItems().playerHasItem(c.xRemoveId, Xamount))
				return;
			if(c.duelStatus <= 0) {
				c.getTradeAndDuel().fromTrade(c.xRemoveId, c.xRemoveSlot, Xamount);
			}
			break;

		case 6669:
			if(!c.getItems().playerHasItem(c.xRemoveId, Xamount))
				return;
			c.getTradeAndDuel().fromDuel(c.xRemoveId, c.xRemoveSlot, Xamount);
			break;
		}
	}
}