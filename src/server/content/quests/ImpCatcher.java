package server.content.quests;

import server.content.music.Music;
import server.game.players.Client;

/**
* Imp Catcher
* @author Clayton
*/

public class ImpCatcher {


	public static void impReward(Client c) {
		c.getPA().sendFrame126("You have completed the Imp Catcher Quest!" ,12144);
		c.getPA().sendFrame126("" ,12147);
		c.getPA().sendFrame126("You are awarded:" ,12150);
		c.getPA().sendFrame126("1 Quest Point" ,12151);
		c.getPA().sendFrame126("875 Magic XP" ,12152);
		c.getPA().sendFrame126("Amulet of Accuracy" ,12153);
		c.getPA().sendFrame126("" ,12154);
		c.getPA().sendFrame126("" ,12155);
		c.getPA().sendFrame126(""+(c.questPoints) ,12156);
		c.getPlayerAssistant().sendFrame246(12145, 250, 1265);
		c.getPA().showInterface(12140);
		Music.sendQuickSong(c,71,23);
	}
	
	public static void showInformation(Client c) {
		for(int i = 8144; i < 8195; i++) {
			c.getPA().sendFrame126("", i);
		}
		c.getPA().sendFrame126("@dre@Imp Catcher", 8144);
		c.getPA().sendFrame126("", 8145);
		if(c.impC == 0) {
			c.getPA().sendFrame126("I can start this quest by speaking to Wizard Mizgog who is", 8147);
			c.getPA().sendFrame126("in the wizard's tower.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("There aren't any requirements for this quest.", 8151);
		} else if(c.impC == 1) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Wizard Mizgog who is", 8147);
			c.getPA().sendFrame126("@str@in the wizard's tower.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Mizgog has asked you to get the following:", 8150);
			c.getPA().sendFrame126("1 Red Bead", 8151);
			c.getPA().sendFrame126("1 Yellow Bead", 8152);
			c.getPA().sendFrame126("1 White Bead", 8153);
			c.getPA().sendFrame126("1 Black Bead", 8154);
		} else if(c.impC == 1 && c.getItems().playerHasItem(1470, 1)) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Wizard Mizgog who is", 8147);
			c.getPA().sendFrame126("@str@in the wizard's tower.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Mizgog has asked you to get the following:", 8150);
			c.getPA().sendFrame126("@str@1 Red Bead", 8151);
			c.getPA().sendFrame126("1 Yellow Bead", 8152);
			c.getPA().sendFrame126("1 White Bead", 8153);
			c.getPA().sendFrame126("1 Black Bead", 8154);
		} else if(c.impC == 1 && c.getItems().playerHasItem(1472, 1)) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Wizard Mizgog who is", 8147);
			c.getPA().sendFrame126("@str@in the wizard's tower.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Mizgog has asked you to get the following:", 8150);
			c.getPA().sendFrame126("1 Red Bead", 8151);
			c.getPA().sendFrame126("@str@1 Yellow Bead", 8152);
			c.getPA().sendFrame126("1 White Bead", 8153);
			c.getPA().sendFrame126("1 Black Bead", 8154);
		} else if(c.impC == 1 && c.getItems().playerHasItem(1474, 1)) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Wizard Mizgog who is", 8147);
			c.getPA().sendFrame126("@str@in the wizard's tower.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Mizgog has asked you to get the following:", 8150);
			c.getPA().sendFrame126("1 Red Bead", 8151);
			c.getPA().sendFrame126("1 Yellow Bead", 8152);
			c.getPA().sendFrame126("1 White Bead", 8153);
			c.getPA().sendFrame126("@str@1 Black Bead", 8154);
		} else if(c.impC == 1 && c.getItems().playerHasItem(1476, 1)) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Wizard Mizgog who is", 8147);
			c.getPA().sendFrame126("@str@in the wizard's tower.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Mizgog has asked you to get the following:", 8150);
			c.getPA().sendFrame126("1 Red Bead", 8151);
			c.getPA().sendFrame126("1 Yellow Bead", 8152);
			c.getPA().sendFrame126("@str@1 White Bead", 8153);
			c.getPA().sendFrame126("1 Black Bead", 8154);
		} else if(c.impC == 1 && c.getItems().playerHasItem(1476, 1) && c.getItems().playerHasItem(1474, 1)) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Wizard Mizgog who is", 8147);
			c.getPA().sendFrame126("@str@in the wizard's tower.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Mizgog has asked you to get the following:", 8150);
			c.getPA().sendFrame126("1 Red Bead", 8151);
			c.getPA().sendFrame126("1 Yellow Bead", 8152);
			c.getPA().sendFrame126("@str@1 White Bead", 8153);
			c.getPA().sendFrame126("@str@1 Black Bead", 8154);
		} else if(c.impC == 1 && c.getItems().playerHasItem(1476, 1) && c.getItems().playerHasItem(1474, 1) && c.getItems().playerHasItem(1472, 1)) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Wizard Mizgog who is", 8147);
			c.getPA().sendFrame126("@str@in the wizard's tower.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Mizgog has asked you to get the following:", 8150);
			c.getPA().sendFrame126("1 Red Bead", 8151);
			c.getPA().sendFrame126("@str@1 Yellow Bead", 8152);
			c.getPA().sendFrame126("@str@1 White Bead", 8153);
			c.getPA().sendFrame126("@str@1 Black Bead", 8154);
		} else if(c.impC == 1 && c.getItems().playerHasItem(1476, 1) && c.getItems().playerHasItem(1474, 1) && c.getItems().playerHasItem(1472, 1) && c.getItems().playerHasItem(1470, 1)) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Wizard Mizgog who is", 8147);
			c.getPA().sendFrame126("@str@in the wizard's tower.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Mizgog has asked you to get the following:", 8150);
			c.getPA().sendFrame126("@str@1 Red Bead", 8151);
			c.getPA().sendFrame126("@str@1 Yellow Bead", 8152);
			c.getPA().sendFrame126("@str@1 White Bead", 8153);
			c.getPA().sendFrame126("@str@1 Black Bead", 8154);
			c.getPA().sendFrame126("", 8155);
			c.getPA().sendFrame126("I should bring the beads to Wizard Mizgog", 8155);
		} else if(c.impC == 2) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Wizard Mizgog who is", 8147);
			c.getPA().sendFrame126("@str@in the wizard's tower.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Mizgog has asked you to get the following:", 8150);
			c.getPA().sendFrame126("@str@1 Red Bead", 8151);
			c.getPA().sendFrame126("@str@1 Yellow Bead", 8152);
			c.getPA().sendFrame126("@str@1 White Bead", 8153);
			c.getPA().sendFrame126("@str@1 Black Bead", 8154);
			c.getPA().sendFrame126("", 8155);
			c.getPA().sendFrame126("You have completed the quest!", 8156);
		}
		c.getPA().showInterface(8134);
	}
	
}
