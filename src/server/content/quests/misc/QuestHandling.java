package server.content.quests.misc;
import server.content.music.Music;
import server.game.players.Client;

public class QuestHandling {

	public static void showInterface(Client client, int i) {
		client.getOutStream().createFrame(97);
		client.getOutStream().writeWord(i);
		client.flushOutStream();
	}

	public static void sendQuest(Client client, String s, int i) {
		client.getOutStream().createFrameVarSizeWord(126);
		client.getOutStream().writeString(s);
		client.getOutStream().writeWordA(i);
		client.getOutStream().endFrameVarSizeWord();
		client.flushOutStream();
	}
	public static void sendQuestSong(Client c) {
		Music.sendQuickSong(c,71,23);
	}

	//TODO Demonslayer,droisquest,ernestchicken,impcatcher,knightsword,
	//piratestresure,runemysteries,princealirescue
	public static void VampireFinish(Client c) {
		c.questPoints += 3;
		c.getPA().loadQuests();
		c.getPA().addSkillXP(4825, 0);
		c.getPA().QuestReward(c,
				"Vampire Slayer",
				"3 Quest Points",
				"4825 Attack XP",
				"",
				"",
				"", "", 1549);
		sendQuest(c, " " + (c.questPoints), 12147);
		sendQuestSong(c);
	}
	public static void cookFinish(Client c) {
		c.cooksA = 3;
		c.questPoints++;
		c.getPA().loadQuests();
		c.getPA().addSkillXP(300, 7);
		c.getItems().addItem(326, 20); // sardines
		c.getItems().addItem(995, 500); // coins
		c.getPA().QuestReward(c,
				"Cook's Assistant",
				"1 Quest Points",
				"300 Cooking XP",
				"500 coins",
				"20 sardines",
				"Access to the cook's range", "", 221);
		sendQuest(c, "" + (c.questPoints), 12147);
		sendQuestSong(c);
	}
	public static void WitchFinish(Client c) {
		c.witchspot++;
		c.questPoints++;
		c.getPA().loadQuests();
		c.getPA().addSkillXP(325, 6);
		c.getPA().QuestReward(c,
				"Witch's Potion",
				"1 Quest Points",
				"325 Magic Xp",
				"",
				"",
				"", "", 221);
		sendQuest(c, "" + (c.questPoints), 12147);
		sendQuestSong(c);
	}

	//TODO Demonslayer,droisquest,ernestchicken,impcatcher,knightsword,
	//piratestresure,runemysteries,vampireslayer,princealirescue



	public static void sendQuestTab(Client c){
		if(c.vampireslay == 0) {
			sendFrame126(c, "Vampire Slayer", 7347);
		} else if(c.vampireslay == 5) {
			sendFrame126(c, "@gre@Vampire Slayer", 7347);
		} else {
			sendFrame126(c, "@yel@Vampire Slayer", 7347);
		}
		if(c.witchspot == 0) {
			sendFrame126(c, "Witch's Potion", 7348);
		} else if(c.witchspot == 2) {
			sendFrame126(c, "@gre@Witch's Potion", 7348);
		} else {
			sendFrame126(c, "@yel@Witch's Potion",7348);
		}

		sendFrame126(c, "@or1@Quest Points: "+c.questPoints, 640);
	}


	public static void sendFrame126(Client c,String s, int id) {
		if(c.getOutStream() != null && c != null ) {
			c.getOutStream().createFrameVarSizeWord(126);
			c.getOutStream().writeString(s);
			c.getOutStream().writeWordA(id);
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}


}