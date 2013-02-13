package server.game.players.packets;

import server.content.SpinningPlates;
import server.content.randoms.Genie;
import server.game.items.food.Food;
import server.game.items.food.Potions;
import server.game.items.food.SpecialConsumables;
import server.game.players.Client;
import server.game.players.PacketType;
import core.util.Misc;

/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {

	private static final int[] CASKET_REWARDS = { 2577, 2581, 7388, 7392, 7396,
			2583, 2585, 2587, 2589, 2599, 2601, 2603, 2605, 2623, 2625, 2627,
			2629, 3472, 3474, 3477, 7364, 7368, 7372, 7376, 7380, 7384, 7388,
			7392, 7396 };

	public static int getLength() {
		return CASKET_REWARDS.length;
	}

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		// ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
		}
		if (SpecialConsumables.isConsumable(c, itemId, itemSlot))
			return;
		switch (itemId) {
		case 550://newcomer
			c.getPA().showInterface(5392);
			break;
		case 2528:// genie
			Genie.rubLamp(c, itemId);
			break;
		case 4613:// spinning plate
			if (itemId == 4613) {
				if (!c.spinningPlate)
					SpinningPlates.spinningPlate(c, itemId, itemSlot);
			}
			break;
		}
		if (itemId == 2520) {
			c.forcedChat(horseChat[Misc.random(0, 2)]);
			c.startAnimation(918);
		}
		if (itemId == 2522) {
			c.forcedChat(horseChat[Misc.random(0,2)]);
			c.startAnimation(919);
		}
		if (itemId == 2524) {
			c.forcedChat(horseChat[Misc.random(0,2)]);
			c.startAnimation(920);
		}
		if (itemId == 2526) {
			c.forcedChat(horseChat[Misc.random(0,2)]);
			c.startAnimation(921);
		}
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
		if (Food.isFood(itemId))
			Food.eat(c, itemId, itemSlot);
		if (itemId == 1971)
			Food.eatKebab(c, itemSlot);
		if (c.getPrayer().IsABone(itemId))
			c.getPrayer().buryBone(itemId, itemSlot);
		if (itemId == 405) {
			c.getItems().deleteItem(405, 1);
			c.getItems().addItem(995, Misc.random(50000));
			c.sendMessage("You find some coins in the casket.");
			if (Misc.random(19) == 0) {
				c.getItems().addItem(
						CASKET_REWARDS[Misc.random(getLength() - 1)], 1);
				c.sendMessage("Congratulations, you find a rare item in the casket!");
			}
		}
		if (Potions.isPotion(c, itemId))
			Potions.handlePotion(c, itemId, itemSlot);
		if (itemId == 952) {
			c.sendMessage("You start digging...");
			if (c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if (c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if (c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if (c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if (c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if (c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			} else if (c.inArea(2835, 3336, 2835, 3336)) {
				c.teleTimer = 3;
				c.newLocation = 7;
			} else if (c.inArea(2834, 3336, 2834, 3336)) {
				c.teleTimer = 3;
				c.newLocation = 8;
			}
		}
	}

	public String[] horseChat = { "Come on Dobbin, we can win the race!",
			"Hi-ho Silver, and away!", "Neaahhhyyy! Giddy-up horsey!" };

}
