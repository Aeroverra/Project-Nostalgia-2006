package server.game.players.packets;

import server.content.HandleEmpty;
import server.content.skills.Runecrafting;
import server.game.items.food.Potions;
import server.game.players.Client;
import server.game.players.DialogueHandler;
import server.game.players.PacketType;
import core.util.Misc;

/**
 * Item Click 3 Or Alternative Item Option 1
 * 
 * @author Ryan / Lmctruck30
 * 
 * Proper Streams
 */

public class ItemClick3 implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId11 = c.getInStream().readSignedWordBigEndianA();
		int itemId1 = c.getInStream().readSignedWordA();
		int itemId = c.getInStream().readSignedWordA();
		if(!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		if (Potions.potionNames(c,itemId)) {
			HandleEmpty.handleEmptyItem(c, itemId, 229);
			return;
		}
		if (HandleEmpty.canEmpty(itemId)) {
			HandleEmpty.handleEmptyItem(c, itemId, HandleEmpty.filledToEmpty(itemId));
			return;
		}
		/* 		final String name = c.getItems().getItemName(itemId);
		if (c.getPotions().isPotion(itemId)) {
			c.sendMessage("There are still some potion left...");
			c.sendMessage("You empty it anyways.");
			c.getItems().deleteItem(itemId, 1);
			c.getItems().addItem(229, 1);
		}
		 */
		switch (itemId) {
		case 1438:// Air Talisman
			Runecrafting.locate(c, 2985, 3292);
			break;
		case 1440:// Earth Talisman
			Runecrafting.locate(c, 3306, 3474);
			break;
		case 1442:// Fire Talisman
			Runecrafting.locate(c, 3313, 3255);
			break;
		case 1444:// Water Talisman
			Runecrafting.locate(c, 3185, 3165);
			break;
		case 1446:// Body Talisman
			Runecrafting.locate(c, 3053, 3445);
			break;
		case 1448:// Mind Talisman
			Runecrafting.locate(c, 2982, 3514);
			break;
		case 1712:
			c.getPA().handleGlory(itemId);
			break;
		case 3853:
			c.getPA().handleGlory(itemId);
			break;
		case 2552:
			DialogueHandler.sendDialogues(c, 68, 0);
			break;

		default:
			if (c.playerRights == 3)
				Misc.println(c.playerName+ " - Item3rdOption: "+itemId+" : "+itemId11+" : "+itemId1);
			break;
		}

	}

}
