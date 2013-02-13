package server.content.quests;

import server.game.players.Client;

/*
 * Made by Vox`
 * 
 */

public class CooksAssistant {

	public static void showInformation(final Client c) {
		for(int i = 8144; i < 8195; i++) {
			c.getPA().sendFrame126("", i);
		}
		c.getPA().sendFrame126("@dre@Cook's Assistant", 8144);
		c.getPA().sendFrame126("", 8145);
		if(c.cooksA == 0) {
			c.getPA().sendFrame126("Cook's Assistant", 8144);
			c.getPA().sendFrame126("I can start this quest by speaking to the Cook in the", 8147);
			c.getPA().sendFrame126("Lumbridge Castle kitchen.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("There are no minimum requirments.", 8150);
		} else if(c.cooksA == 1) {
			c.getPA().sendFrame126("Cook's Assistant", 8144);
			c.getPA().sendFrame126("@str@I've talked to the cook.", 8147);
			c.getPA().sendFrame126("He wants me to gather the following materials:", 8148);
			if(c.getItems().playerHasItem(1944,1)) {
				c.getPA().sendFrame126("@str@1 egg", 8149);
			} else {
				c.getPA().sendFrame126("@red@1 egg", 8149);
			}
			if(c.getItems().playerHasItem(1927,1)) {
				c.getPA().sendFrame126("@str@1 bucket of milk", 8150);
			} else {
				c.getPA().sendFrame126("@red@1 bucket of milk", 8150);
			}
			if(c.getItems().playerHasItem(1933,1)) {
				c.getPA().sendFrame126("@str@1 heap of flour", 8151);
			} else {
				c.getPA().sendFrame126("@red@1 pot of flour", 8151);
			}
		} else if(c.cooksA == 2) {
			c.getPA().sendFrame126("Cook's Assistant", 8144);
			c.getPA().sendFrame126("@str@I talked to the cook.", 8147);
			c.getPA().sendFrame126("@str@I gave the cook his items.", 8148);
			c.getPA().sendFrame126("I should go speak to the cook.", 8149);
		} else if(c.cooksA == 3) {
			c.getPA().sendFrame126("@str@To start the quest, you should talk with Cook", 8147);
			c.getPA().sendFrame126("@str@found in Lumbridge.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("@str@Cook have asked you to get the following items:", 8150);
			c.getPA().sendFrame126("@str@Bucket of milk", 8151);
			c.getPA().sendFrame126("@str@Pot of Flour", 8152);
			c.getPA().sendFrame126("@str@an Egg", 8153);
			c.getPA().sendFrame126("", 8154);
			c.getPA().sendFrame126("You have completed this quest!", 8155);
		}
		c.getPA().showInterface(8134);
	}


}