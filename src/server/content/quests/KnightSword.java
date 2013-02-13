package server.content.quests;

import server.game.players.Client;

/**
* Sheep Shearer
* @author Justin
*/

public class KnightSword {


	public static void questReward(Client c) {
		c.getPA().addSkillXP(12725, 14); 
		c.getPA().sendFrame126("You have completed the Knight's Sword!" ,12144);
		c.getPA().sendFrame126(""+(c.questPoints) ,12147);
		c.getPA().sendFrame126("1 Quest point" ,12150);
		c.getPA().sendFrame126("12,725 Smithing XP" ,12151);
		c.getPA().sendFrame126("" ,12152);
		c.getPA().sendFrame126("" ,12153);
		c.getPA().sendFrame126("" ,12154);
		c.getPA().sendFrame126("" ,12155);
		c.getPlayerAssistant().sendFrame246(12145, 250, 1265);
		c.getPA().showInterface(12140);
		c.knightS = 2;
	}
	
	
	public static void showInformation(Client c) {
		for(int i = 8144; i < 8195; i++) {
			c.getPA().sendFrame126("", i);
		}
		c.getPA().sendFrame126("@dre@The Knight's Sword", 8144);
		c.getPA().sendFrame126("", 8145);
		if(c.knightS == 0) {
			c.getPA().sendFrame126("I can start this quest by speaking to the squire", 8147);
			c.getPA().sendFrame126("who is in the courtyard of the White Knight's castle", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("I will need at least 10 mining to complete this quest", 8150);
		} 
		if(c.knightS == 1) {
			c.getPA().sendFrame126("The squire has lost Sir Vyvin's sword and asked me", 8147);
			c.getPA().sendFrame126("to find a replacement. He suggested that I start", 8148);
			c.getPA().sendFrame126("by speaking to Reldo, the librarian in the Varrock Castle", 8149);
		} 
		if(c.knightS == 2) {
			c.getPA().sendFrame126("@str@The squire has lost Sir Vyvin's sword and asked me", 8147);
			c.getPA().sendFrame126("@str@to find a replacement. He suggested that I start", 8148);
			c.getPA().sendFrame126("@str@by speaking to Reldo, the librarian in the Varrock Castle", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("Reldo told me there may be an Imcando dwarf living near the", 8151);
			c.getPA().sendFrame126("Asgarnian peninsula. He said I should bring him some Red Berry pie", 8152);
			c.getPA().sendFrame126("to get him to be willing to talk to me", 8153);
		
		} 
		if(c.knightS == 3) {
			c.getPA().sendFrame126("@str@The squire has lost Sir Vyvin's sword and asked me", 8147);
			c.getPA().sendFrame126("@str@to find a replacement. He suggested that I start", 8148);
			c.getPA().sendFrame126("@str@by speaking to Reldo, the librarian in the Varrock Castle", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@str@Reldo told me there may be an Imcando dwarf living near the", 8151);
			c.getPA().sendFrame126("@str@Asgarnian peninsula. He said I should bring him some Red Berry pie", 8152);
			c.getPA().sendFrame126("@str@to get him to be willing to talk to me", 8153);
			c.getPA().sendFrame126("", 8154);
			c.getPA().sendFrame126("I found the Imcando dwarf named Thurgo and gave him some Red Berry pie", 8155);
			c.getPA().sendFrame126("Now that he likes me I should talk to him and find out if he'll make the", 8156);
			c.getPA().sendFrame126("sword for me.", 8157);
		
		} 
		if(c.knightS == 4) {
			c.getPA().sendFrame126("@str@The squire has lost Sir Vyvin's sword and asked me", 8147);
			c.getPA().sendFrame126("@str@to find a replacement. He suggested that I start", 8148);
			c.getPA().sendFrame126("@str@by speaking to Reldo, the librarian in the Varrock Castle", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@str@Reldo told me there may be an Imcando dwarf living near the", 8151);
			c.getPA().sendFrame126("@str@Asgarnian peninsula. He said I should bring him some Red Berry pie", 8152);
			c.getPA().sendFrame126("@str@to get him to be willing to talk to me", 8153);
			c.getPA().sendFrame126("", 8154);
			c.getPA().sendFrame126("@str@I found the Imcando dwarf named Thurgo and gave him some Red Berry pie", 8155);
			c.getPA().sendFrame126("@str@Now that he likes me I should talk to him and find out if he'll make the", 8156);
			c.getPA().sendFrame126("@str@sword for me.", 8157);
			c.getPA().sendFrame126("", 8158);
			c.getPA().sendFrame126("Thurgo says he needs a picture of the sword.", 8159);
			c.getPA().sendFrame126("Maybe the squire will have one?", 8160);
			c.getPA().sendFrame126("", 8161);
		
		} 
		if(c.knightS == 5) {
			c.getPA().sendFrame126("@str@The squire has lost Sir Vyvin's sword and asked me", 8147);
			c.getPA().sendFrame126("@str@to find a replacement. He suggested that I start", 8148);
			c.getPA().sendFrame126("@str@by speaking to Reldo, the librarian in the Varrock Castle", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@str@Reldo told me there may be an Imcando dwarf living near the", 8151);
			c.getPA().sendFrame126("@str@Asgarnian peninsula. He said I should bring him some Red Berry pie", 8152);
			c.getPA().sendFrame126("@str@to get him to be willing to talk to me", 8153);
			c.getPA().sendFrame126("", 8154);
			c.getPA().sendFrame126("@str@I found the Imcando dwarf named Thurgo and gave him some Red Berry pie", 8155);
			c.getPA().sendFrame126("@str@Now that he likes me I should talk to him and find out if he'll make the", 8156);
			c.getPA().sendFrame126("@str@sword for me.", 8157);
			c.getPA().sendFrame126("", 8158);
			c.getPA().sendFrame126("Thurgo says he needs a picture of the sword.", 8159);
			c.getPA().sendFrame126("@str@Maybe the squire will have one?", 8160);
			c.getPA().sendFrame126("", 8161);
			c.getPA().sendFrame126("The squire thinks Sir Vyvin keeps a picture of the sword in a cupboard in", 8162);
			c.getPA().sendFrame126("his room, but I must be very careful not to get caught.", 8163);
			c.getPA().sendFrame126("", 8164);
			c.getPA().sendFrame126("", 8165);
			c.getPA().sendFrame126("", 8166);
			c.getPA().sendFrame126("", 8167);
			c.getPA().sendFrame126("", 8168);
			c.getPA().sendFrame126("", 8169);
		
		} 
		if(c.knightS == 6) {
			c.getPA().sendFrame126("@str@The squire has lost Sir Vyvin's sword and asked me", 8147);
			c.getPA().sendFrame126("@str@to find a replacement. He suggested that I start", 8148);
			c.getPA().sendFrame126("@str@by speaking to Reldo, the librarian in the Varrock Castle", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@str@Reldo told me there may be an Imcando dwarf living near the", 8151);
			c.getPA().sendFrame126("@str@Asgarnian peninsula. He said I should bring him some Red Berry pie", 8152);
			c.getPA().sendFrame126("@str@to get him to be willing to talk to me", 8153);
			c.getPA().sendFrame126("", 8154);
			c.getPA().sendFrame126("@str@I found the Imcando dwarf named Thurgo and gave him some Red Berry pie", 8155);
			c.getPA().sendFrame126("@str@Now that he likes me I should talk to him and find out if he'll make the", 8156);
			c.getPA().sendFrame126("@str@sword for me.", 8157);
			c.getPA().sendFrame126("", 8158);
			c.getPA().sendFrame126("Thurgo says he needs a picture of the sword.", 8159);
			c.getPA().sendFrame126("@str@Maybe the squire will have one?", 8160);
			c.getPA().sendFrame126("", 8161);
			c.getPA().sendFrame126("@str@The squire thinks Sir Vyvin keeps a picture of the sword in a cupboard in", 8162);
			c.getPA().sendFrame126("@str@his room, but I must be very careful not to get caught.", 8163);
			c.getPA().sendFrame126("", 8164);
			c.getPA().sendFrame126("I should bring the picture to Thurgo", 8165);
			c.getPA().sendFrame126("", 8166);
			c.getPA().sendFrame126("", 8167);
			c.getPA().sendFrame126("", 8168);
			c.getPA().sendFrame126("", 8169);
		
		} 
		if(c.knightS == 7) {
			c.getPA().sendFrame126("The squire has lost Sir Vyvin's sword and asked me", 8147);
			c.getPA().sendFrame126("to find a replacement.@str@ He suggested that I start", 8148);
			c.getPA().sendFrame126("@str@by speaking to Reldo, the librarian in the Varrock Castle", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@str@Reldo told me there may be an Imcando dwarf living near the", 8151);
			c.getPA().sendFrame126("@str@Asgarnian peninsula. He said I should bring him some Red Berry pie", 8152);
			c.getPA().sendFrame126("@str@to get him to be willing to talk to me", 8153);
			c.getPA().sendFrame126("", 8154);
			c.getPA().sendFrame126("@str@I found the Imcando dwarf named Thurgo and gave him some Red Berry pie", 8155);
			c.getPA().sendFrame126("@str@Now that he likes me I should talk to him and find out if he'll make the", 8156);
			c.getPA().sendFrame126("@str@sword for me.", 8157);
			c.getPA().sendFrame126("", 8158);
			c.getPA().sendFrame126("@str@Thurgo says he needs a picture of the sword.", 8159);
			c.getPA().sendFrame126("@str@Maybe the squire will have one?", 8160);
			c.getPA().sendFrame126("", 8161);
			c.getPA().sendFrame126("@str@The squire thinks Sir Vyvin keeps a picture of the sword in a cupboard in", 8162);
			c.getPA().sendFrame126("@str@his room, but I must be very careful not to get caught.", 8163);
			c.getPA().sendFrame126("", 8164);
			c.getPA().sendFrame126("@str@I should bring the picture to Thurgo", 8165);
			c.getPA().sendFrame126("", 8166);
			c.getPA().sendFrame126("Thurgo has asked me to bring him 2 iron bars and 1 blurite ore for him to make the", 8167);
			c.getPA().sendFrame126("sword with. He says blurite can be mined in the cave by his home, but it is guarded", 8168);
			c.getPA().sendFrame126("by dangerous monsters. So I should be very careful.", 8169);
		
		} 
		if(c.knightS == 8) {
			c.getPA().sendFrame126("The squire has lost Sir Vyvin's sword and asked me", 8147);
			c.getPA().sendFrame126("to find a replacement.@str@ He suggested that I start", 8148);
			c.getPA().sendFrame126("@str@by speaking to Reldo, the librarian in the Varrock Castle", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@str@Reldo told me there may be an Imcando dwarf living near the", 8151);
			c.getPA().sendFrame126("@str@Asgarnian peninsula. He said I should bring him some Red Berry pie", 8152);
			c.getPA().sendFrame126("@str@to get him to be willing to talk to me", 8153);
			c.getPA().sendFrame126("", 8154);
			c.getPA().sendFrame126("@str@I found the Imcando dwarf named Thurgo and gave him some Red Berry pie", 8155);
			c.getPA().sendFrame126("@str@Now that he likes me I should talk to him and find out if he'll make the", 8156);
			c.getPA().sendFrame126("@str@sword for me.", 8157);
			c.getPA().sendFrame126("", 8158);
			c.getPA().sendFrame126("@str@Thurgo says he needs a picture of the sword.", 8159);
			c.getPA().sendFrame126("@str@Maybe the squire will have one?", 8160);
			c.getPA().sendFrame126("", 8161);
			c.getPA().sendFrame126("@str@The squire thinks Sir Vyvin keeps a picture of the sword in a cupboard in", 8162);
			c.getPA().sendFrame126("@str@his room, but I must be very careful not to get caught.", 8163);
			c.getPA().sendFrame126("", 8164);
			c.getPA().sendFrame126("@str@I should bring the picture to Thurgo", 8165);
			c.getPA().sendFrame126("", 8166);
			c.getPA().sendFrame126("@str@Thurgo has asked me to bring him 2 iron bars and 1 blurite ore for him to make the", 8167);
			c.getPA().sendFrame126("@str@sword with. He says blurite can be mined in the cave by his home, but it is guarded", 8168);
			c.getPA().sendFrame126("@str@by dangerous monsters. So I should be very careful.", 8169);
			c.getPA().sendFrame126("", 8170);
			c.getPA().sendFrame126("Thurgo made me the sword, I should bring it back to the knight to get my reward!", 8171);
			c.getPA().sendFrame126("", 8172);
			c.getPA().sendFrame126("", 8173);
			c.getPA().sendFrame126("", 8174);
		} 
		if(c.knightS == 9) {
			c.getPA().sendFrame126("@str@The squire has lost Sir Vyvin's sword and asked me", 8147);
			c.getPA().sendFrame126("@str@to find a replacement. He suggested that I start", 8148);
			c.getPA().sendFrame126("@str@by speaking to Reldo, the librarian in the Varrock Castle", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().sendFrame126("@str@Reldo told me there may be an Imcando dwarf living near the", 8151);
			c.getPA().sendFrame126("@str@Asgarnian peninsula. He said I should bring him some Red Berry pie", 8152);
			c.getPA().sendFrame126("@str@to get him to be willing to talk to me", 8153);
			c.getPA().sendFrame126("", 8154);
			c.getPA().sendFrame126("@str@I found the Imcando dwarf named Thurgo and gave him some Red Berry pie", 8155);
			c.getPA().sendFrame126("@str@Now that he likes me I should talk to him and find out if he'll make the", 8156);
			c.getPA().sendFrame126("@str@sword for me.", 8157);
			c.getPA().sendFrame126("", 8158);
			c.getPA().sendFrame126("@str@Thurgo says he needs a picture of the sword.", 8159);
			c.getPA().sendFrame126("@str@Maybe the squire will have one?", 8160);
			c.getPA().sendFrame126("", 8161);
			c.getPA().sendFrame126("@str@The squire thinks Sir Vyvin keeps a picture of the sword in a cupboard in", 8162);
			c.getPA().sendFrame126("@str@his room, but I must be very careful not to get caught.", 8163);
			c.getPA().sendFrame126("", 8164);
			c.getPA().sendFrame126("@str@I should bring the picture to Thurgo", 8165);
			c.getPA().sendFrame126("", 8166);
			c.getPA().sendFrame126("@str@Thurgo has asked me to bring him 2 iron bars and 1 blurite ore for him to make the", 8167);
			c.getPA().sendFrame126("@str@sword with. He says blurite can be mined in the cave by his home, but it is guarded", 8168);
			c.getPA().sendFrame126("@str@by dangerous monsters. So I should be very careful.", 8169);
			c.getPA().sendFrame126("", 8170);
			c.getPA().sendFrame126("@str@Thurgo made me the sword, I should bring it back to the knight to get my reward!", 8171);
			c.getPA().sendFrame126("", 8172);
			c.getPA().sendFrame126("Quest Complete!", 8173);
			c.getPA().sendFrame126("", 8174);
		} 
		
		c.getPA().showInterface(8134);
	}
	
}
