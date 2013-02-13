package server.content.quests;

import server.content.music.Music;
import server.game.players.Client;

/**
* Imp Catcher
* @author Clayton
*/

public class RuneMysteries {

	public Client c;
	
	public RuneMysteries(Client client) {
		this.c = client;
	}
	public static void mysteriesReward(Client c) {
		c.getPA().sendFrame126("You have completed the Rune Mysteries Quest!" ,12144);
		c.getPA().sendFrame126("" ,12147);
		c.getPA().sendFrame126("You are awarded:" ,12150);
		c.getPA().sendFrame126("1 Quest Point" ,12151);
		c.getPA().sendFrame126("Runecrafting skill" ,12152);
		c.getPA().sendFrame126("Air Talisman" ,12153);
		c.getPA().sendFrame126("" ,12154);
		c.getPA().sendFrame126("" ,12155);
		c.getPA().sendFrame126(""+(c.questPoints) ,12156);
		c.getPlayerAssistant().sendFrame246(12145, 250, 1265);
		c.getPA().showInterface(12140);
		Music.sendQuickSong(c,71,23);
		c.questPoints += 1;
	}
	
	public static void showInformation(Client c) {
		for(int i = 8144; i < 8195; i++) {
			c.getPA().sendFrame126("", i);
		}
		c.getPA().sendFrame126("@dre@Rune Mysteries", 8144);
		c.getPA().sendFrame126("", 8145);
		if(c.runeM == 0) {
			c.getPA().sendFrame126("I can start this quest by speaking to Duke Horacio who is", 8147);
			c.getPA().sendFrame126("on the second floor in the Lumbridge Castle.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("There aren't any requirements for this quest.", 8151);
		} else if(c.runeM == 1) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Duke Horacio who is", 8147);
			c.getPA().sendFrame126("@str@on the second floor in the Lumbridge Castle.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Horacio has asked you to bring a mysterious talisman", 8150);
			c.getPA().sendFrame126("to Wizard Sedridor in the Wizards Tower", 8151);
			c.getPA().sendFrame126("south of Draynor", 8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA().sendFrame126("", 8154); 
		} else if(c.runeM == 3) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Duke Horacio who is", 8147);
			c.getPA().sendFrame126("@str@on the second floor in the Lumbridge Castle.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("@str@Horacio has asked you to bring a mysterious talisman", 8150);
			c.getPA().sendFrame126("@str@to Wizard Sedridor in the Wizards Tower", 8151);
			c.getPA().sendFrame126("@str@south of Draynor", 8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA().sendFrame126("I spoke to Sedridor and he asked me to bring his research", 8154);
			c.getPA().sendFrame126("to Aubury, the rune salesman in Varrock.", 8155);
			c.getPA().sendFrame126("", 8156);
		} else if(c.runeM == 6) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Duke Horacio who is", 8147);
			c.getPA().sendFrame126("@str@on the second floor in the Lumbridge Castle.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("@str@Horacio has asked you to bring a mysterious talisman", 8150);
			c.getPA().sendFrame126("@str@to Wizard Sedridor in the Wizards Tower", 8151);
			c.getPA().sendFrame126("@str@south of Draynor", 8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA().sendFrame126("@str@I spoke to Sedridor and he asked me to bring his research", 8154);
			c.getPA().sendFrame126("@str@to Aubury, the rune salesman in Varrock.", 8155);
			c.getPA().sendFrame126("@str@", 8156);
			c.getPA().sendFrame126("", 8157);
			c.getPA().sendFrame126("Aubury gave me his notes to give to Sedridor. I should return", 8158);
			c.getPA().sendFrame126("to the Wizards tower to complete the quest!", 8159);
		} else if(c.runeM == 7) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to Duke Horacio who is", 8147);
			c.getPA().sendFrame126("@str@on the second floor in the Lumbridge Castle.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("@str@Horacio has asked you to bring a mysterious talisman", 8150);
			c.getPA().sendFrame126("@str@to Wizard Sedridor in the Wizards Tower", 8151);
			c.getPA().sendFrame126("@str@south of Draynor", 8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA().sendFrame126("@str@I spoke to Sedridor and he asked me to bring his research", 8154);
			c.getPA().sendFrame126("@str@to Aubury, the rune salesman in Varrock.", 8155);
			c.getPA().sendFrame126("@str@", 8156);
			c.getPA().sendFrame126("", 8157);
			c.getPA().sendFrame126("@str@Aubury gave me his notes to give to Sedridor. I should return", 8158);
			c.getPA().sendFrame126("@str@to the Wizards tower to complete the quest!", 8159);
			c.getPA().sendFrame126("", 8160);
			c.getPA().sendFrame126("I brought the notes to Sedridor and he explained the mystery of", 8161);
			c.getPA().sendFrame126("the talisman to me. Apparently it allows me to make runes!", 8162);
			c.getPA().sendFrame126("", 8163);
			c.getPA().sendFrame126("Quest Complete!", 8164);
			}
		c.getPA().showInterface(8134);
	}
	
}
