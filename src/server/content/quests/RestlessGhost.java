package server.content.quests;

import server.content.music.Music;
import server.game.players.Client;

/**
* Restless Ghost
* @author Justin
*/

public class RestlessGhost {

	public static void ghostReward(Client c) {
		c.questPoints++; //+1
		c.getPA().loadQuests();
		c.getPA().addSkillXP(1125, 5);
		c.getPA().sendFrame126("You have completed the Restless Ghost Quest!" ,12144);
		c.getPA().sendFrame126(""+(c.questPoints) ,12147);
		c.getPA().sendFrame126("1 Quest point" ,12150);
		c.getPA().sendFrame126("1125 pray xp" ,12151);
		c.getPA().sendFrame126("" ,12152);
		c.getPA().sendFrame126("" ,12153);
		c.getPA().sendFrame126("" ,12154);
		c.getPA().sendFrame126("" ,12155);
		c.getPlayerAssistant().sendFrame246(12145, 250, 1265);
		c.getPA().showInterface(12140);
		Music.sendQuickSong(c,71,23);
	}
	
	
	public static void showInformation(Client c) {
		for(int i = 8144; i < 8195; i++) {
			c.getPA().sendFrame126("", i);
		}
		c.getPA().sendFrame126("@dre@Restless Ghost", 8144);
		c.getPA().sendFrame126("", 8145);
		if(c.restlessG == 0) {
			c.getPA().sendFrame126("I can start this quest by speaking to @dre@Father Aereck", 8147);
			c.getPA().sendFrame126("who is in the church in @blu@Lumbridge.", 8148);
			c.getPA().sendFrame126("There aren't any requirements for thise quest", 8149);
		} 
		if(c.restlessG == 1) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to @dre@Father Aereck", 8147);
			c.getPA().sendFrame126("@str@who is in the church in @blu@Lumbridge.", 8148);
			c.getPA().sendFrame126("@str@There aren't any requirements for thise quest", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@dre@Father Aereck @bla@has told me to investigate the @dre@ghost", 8151);
			c.getPA().sendFrame126("that is supposedly haunting the @blu@cemetary.", 8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA().sendFrame126("@dre@Father Aereck@bla@ suggests that I", 8154);
			c.getPA().sendFrame126("speak to his colleague in the @blu@Lumbridge Swamp@bla@. Maybe he can", 8155);
			c.getPA().sendFrame126("offer some assistance.", 8156);
		} 
		if(c.restlessG == 2) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to @dre@Father Aereck", 8147);
			c.getPA().sendFrame126("@str@who is in the church in @blu@Lumbridge.", 8148);
			c.getPA().sendFrame126("@str@There aren't any requirements for thise quest", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@dre@Father Aereck@bla@ has told me to investigate the @dre@ghost", 8151);
			c.getPA().sendFrame126("that is supposedly haunting the @blu@cemetary.", 8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA().sendFrame126("@str@Father Aereck suggests that I", 8154);
			c.getPA().sendFrame126("@str@speak to his colleague in the Lumbridge Swamp. Maybe he can", 8155);
			c.getPA().sendFrame126("@str@offer some assistance.", 8156);
			c.getPA().sendFrame126("", 8157);
			c.getPA().sendFrame126("@dre@Father Urhney@bla@ has given me an @blu@Amulet of Ghostspeak", 8158);
			c.getPA().sendFrame126("so that I can speak to the @dre@ghost@bla@ and find out what is wrong", 8159);
			c.getPA().sendFrame126("", 8160);
			c.getPA().sendFrame126("", 8161);		
		} 
		if(c.restlessG == 3) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to @dre@Father Aereck", 8147);
			c.getPA().sendFrame126("@str@who is in the church in @blu@Lumbridge.", 8148);
			c.getPA().sendFrame126("@str@There aren't any requirements for thise quest", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@dre@Father Aereck@bla@ has told me to investigate the @dre@ghost", 8151);
			c.getPA().sendFrame126("that is supposedly haunting the @blu@cemetary.", 8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA().sendFrame126("@str@Father Aereck suggests that I", 8154);
			c.getPA().sendFrame126("@str@speak to his colleague in the Lumbridge Swamp. Maybe he can", 8155);
			c.getPA().sendFrame126("@str@offer some assistance.", 8156);
			c.getPA().sendFrame126("", 8157);
			c.getPA().sendFrame126("@str@Father Urhney has given me an Amulet of Ghostspeak", 8158);
			c.getPA().sendFrame126("@str@so that I can speak to the ghost and find out what is wrong", 8159);
			c.getPA().sendFrame126("", 8160);
			c.getPA().sendFrame126("Apparently a wizard stole the ghost's skull. I should go to", 8161);
			c.getPA().sendFrame126("the @blu@wizards' tower@bla@ that is south-west of", 8162);
			c.getPA().sendFrame126("@blu@Lumbridge@bla@ to search for it", 8163);
		} 
		if(c.restlessG >= 4) {
			c.getPA().sendFrame126("@str@I can start this quest by speaking to @dre@Father Aereck", 8147);
			c.getPA().sendFrame126("@str@who is in the church in @blu@Lumbridge.", 8148);
			c.getPA().sendFrame126("@str@There aren't any requirements for thise quest", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@str@Father Aereck has told me to investigate the ghost", 8151);
			c.getPA().sendFrame126("@str@that is supposedly haunting the cemetary.", 8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA().sendFrame126("@str@Father Aereck suggests that I", 8154);
			c.getPA().sendFrame126("@str@speak to his colleague in the Lumbridge Swamp. Maybe he can", 8155);
			c.getPA().sendFrame126("@str@offer some assistance.", 8156);
			c.getPA().sendFrame126("", 8157);
			c.getPA().sendFrame126("@str@Father Urhney has given me an Amulet of Ghostspeak", 8158);
			c.getPA().sendFrame126("@str@so that I can speak to the ghost and find out what is wrong", 8159);
			c.getPA().sendFrame126("", 8160);
			c.getPA().sendFrame126("@str@Apparently a wizard stole the ghost's skull. I should go to the", 8161);
			c.getPA().sendFrame126("@str@wizards' tower that is south-west of Lumbridge to search for it", 8162);		
			c.getPA().sendFrame126("", 8163);
			c.getPA().sendFrame126("I returned the @blu@ghost's@dre@ skull@bla@ to his coffin and he has moved on.", 8164);
			c.getPA().sendFrame126("", 8165);
			c.getPA().sendFrame126("Quest Complete!", 8166);
		} 
		c.getPA().showInterface(8134);
	}
	
	
}
