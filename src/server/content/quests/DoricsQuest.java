package server.content.quests;

import server.content.music.Music;
import server.game.players.Client;

/**
 * Doric's Quest
 * @author Clayton
 */

public class DoricsQuest {

	public static void doricReward(Client c) {
		c.getItems().addItem(995, 180);
		c.getItems().addItem(1265, 1);
		c.getPA().addSkillXP(1300, 14);
		c.getPA().sendFrame126("You have completed Doric's Quest!" ,12144);
		c.getPA().sendFrame126(""+(c.questPoints) ,12147);
		c.questPoints += 1;
		c.getPA().sendFrame126("You are awarded:" ,12150);
		c.getPA().sendFrame126("180 coins." ,12151);
		c.getPA().sendFrame126("1300 mining experience." ,12152);
		c.getPA().sendFrame126("The ability to use Doric's anvils." ,12153);
		c.getPA().sendFrame126("1 bronze pickaxe." ,12154);
		c.getPlayerAssistant().sendFrame246(12145, 250, 1265);
		c.getPA().showInterface(12140);
		Music.sendQuickSong(c,71,23);
	}

	public static void showInformation(Client c) {
		for(int i = 8144; i < 8195; i++) {
			c.getPA().sendFrame126("", i);
		}
		c.getPA().sendFrame126("@dre@Doric's Quest", 8144);
		c.getPA().sendFrame126("", 8145);
		if(c.doricsQ == 0) {
			c.getPA().sendFrame126("To start the quest, you should talk with Doric", 8147);
			c.getPA().sendFrame126("He is in his home north of Falador.", 8148);
			c.getPA().sendFrame126("A mining level of 15 is reccomended.", 8149);
		} else if(c.doricsQ == 1) {
			c.getPA().sendFrame126("@str@To start the quest, you should talk with Doric", 8147);
			c.getPA().sendFrame126("@str@He is in his home north of Falador.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Doric has asked you to get the following materials:", 8150);
			c.getPA().sendFrame126("6 lumps of clay", 8151);
			c.getPA().sendFrame126("4 copper ores", 8152);
			c.getPA().sendFrame126("2 iron ores", 8153);
		} else if(c.doricsQ == 2) {
			c.getPA().sendFrame126("@str@To start the quest, you should talk with Doric", 8147);
			c.getPA().sendFrame126("@str@He is in his home north of Falador.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("@str@Doric has asked you to get the following materials:", 8150);
			c.getPA().sendFrame126("@str@6 lumps of clay", 8151);
			c.getPA().sendFrame126("@str@4 copper ores", 8152);
			c.getPA().sendFrame126("@str@2 iron ores", 8153);
		} else if(c.doricsQ == 3) {
			c.getPA().sendFrame126("@str@To start the quest, you should talk with Doric", 8147);
			c.getPA().sendFrame126("@str@He is in his home north of Falador.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("@str@Doric has asked you to get the following materials:", 8150);
			c.getPA().sendFrame126("@str@6 lumps of clay", 8151);
			c.getPA().sendFrame126("@str@4 copper ores", 8152);
			c.getPA().sendFrame126("@str@2 iron ores", 8153);
			c.getPA().sendFrame126("", 8154);
			c.getPA().sendFrame126("You have completed this quest!", 8155);
		}
		c.getPA().showInterface(8134);
	}

}
