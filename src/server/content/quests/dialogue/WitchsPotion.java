package server.content.quests.dialogue;

import server.content.quests.misc.QuestHandling;
import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * First quest made by me 100%
 * 
 * @author somedude
 * 
 */
public class WitchsPotion {
	public static boolean finalstage;
	public static int RATS_TAIL = 300, ONION = 1957, BURNT_MEAT = 2146,
			EYEOF = 221;

	public static void handledialogue(Client c, int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		case 700: // start.
			DialogueHandler.sendNpcChat1(c,
					"What could you want with an old woman like me?",
					c.talkingNpc, "Hetty");
			c.nextChat = 701;
			break;
		case 701: // options
			DialogueHandler.sendOption2(c, "I am in search of a quest.",
					"I heard that you are a witch.");
			c.dialogueAction = 701;
			break;
		case 702: // 1rst
			DialogueHandler.sendPlayerChat1(c, "I am in search of a quest.");
			c.nextChat = 703;
			break;
		case 703:
			DialogueHandler.sendNpcChat1(c,
					"Hmmm... Maybe I can think of something for you.",
					c.talkingNpc, "Hetty");
			c.nextChat = 704;
			break;
		case 704:
			DialogueHandler.sendNpcChat1(c,
					"Would you like to become proficient in the dark arts?",
					c.talkingNpc, "Hetty");
			c.nextChat = 705;
			break;
		case 705: // more options 3
			DialogueHandler.sendOption3(c,
					"Yes, help me become one with my darker side.",
					"No, I have my principles and honour.",
					"What, you mean improve my magic?");
			c.dialogueAction = 702;
			break;
		case 706:
			DialogueHandler.sendPlayerChat1(c,
					"Yes, help me become one with my darker side.");
			c.nextChat = 707;
			break;
		case 707:
			DialogueHandler.sendNpcChat2(c,
					"Ok I'm going to make a potion to help bring out your",
					"darker self.", c.talkingNpc, "Hetty");
			c.nextChat = 708;
			break;
		case 708:
			DialogueHandler
					.sendNpcChat1(c, "You will need certain ingredients.",
							c.talkingNpc, "Hetty");
			c.nextChat = 709;
			break;
		case 709:
			DialogueHandler.sendPlayerChat1(c, "What do I need?");
			c.nextChat = 710;
			break;
		case 710:
			DialogueHandler.sendNpcChat2(c,
					"You need an eye of newt, a rat's tail, an onion...",
					"Oh and a piece of burnt meat.", c.talkingNpc, "Hetty");
			c.nextChat = 711;
			c.witchspot++;// 1
			c.getPA().loadQuests(); // yellow time oll
			break;
		case 711:
			DialogueHandler.sendPlayerChat1(c, "Great, I'll go get them.");
			c.nextChat = 0;
			break;
		case 712:
			DialogueHandler.sendNpcChat1(c,
					"So have you found the things for the potion?",
					c.talkingNpc, "Hetty");
			c.nextChat = 713;
			break;
		case 713:
			if (c.getItems().playerHasItem(EYEOF, 1)
					&& c.getItems().playerHasItem(BURNT_MEAT, 1)
					&& c.getItems().playerHasItem(ONION, 1)
					&& c.getItems().playerHasItem(RATS_TAIL, 1)) {
				c.nextChat = 714;
				DialogueHandler.sendPlayerChat1(c, "Yes, I have all of them!");
			} else {
				c.nextChat = 715;
				DialogueHandler.sendPlayerChat1(c,
						"No, I'm still working on it");

			}
			break;
		case 714: // success
			DialogueHandler.sendNpcChat1(c, "Excellent. Can I have them then?",
					c.talkingNpc, "Hetty");
			c.nextChat = 716;
			break;
		case 716:
			DialogueHandler
					.sendStatement4(
							c,
							"You pass the ingredients to Hetty ",
							" and she puts them all in her",
							"cauldron. Hetty closes her eyes and begins to chant. The cauldron",
							"bubbles mysteriously.");
			c.getItems().deleteItem(EYEOF, 1);
			c.getItems().deleteItem(BURNT_MEAT, 1);
			c.getItems().deleteItem(ONION, 1);
			c.getItems().deleteItem(RATS_TAIL, 1);
			c.nextChat = 717;
			break;
		case 717:
			DialogueHandler.sendPlayerChat1(c, "Well, is it ready?");
			c.nextChat = 718;
			break;
		case 718:
			DialogueHandler.sendNpcChat1(c, "Ok, now drink from the cauldron.",
					c.talkingNpc, "Hetty");
			c.nextChat = 0;
			finalstage = true;
			break;
		case 719: // finished
			QuestHandling.WitchFinish(c);
			c.nextChat = 0;
			finalstage = false;
			break;

		case 720: // finished
			DialogueHandler.sendNpcChat1(c,
					"So have you felt your darker self yet?", c.talkingNpc,
					"Hetty");
			c.nextChat = 721;
			break;

		case 721: // finished
			DialogueHandler.sendPlayerChat1(c, "No, not really.");
			c.nextChat = 0;
			break;

		case 715: // fail
			DialogueHandler.sendNpcChat1(c, "Go get them and then come back.",
					c.talkingNpc, "Hetty");
			c.nextChat = 0;
			break;

		}

	}

	public static void handleOptions(Client c, int actionbuttonId) {
		switch (actionbuttonId) { // new dialogue action handler
		case 9157:
			switch (c.dialogueAction) { // new dialogue action handler
			case 701:
				handledialogue(c, 702, 307);
				break;

			}
			break;

		case 9167:
			switch (c.dialogueAction) { // new dialogue action handler
			case 702:
				handledialogue(c, 706, 307);
				break;

			}

			break;

		}

	}

	public static void showInformation(Client c) {
		for (int i = 8144; i < 8195; i++) {
			c.getPA().sendFrame126("", i);
		}
		c.getPA().sendFrame126("@dre@Witch's Potion", 8144);
		c.getPA().sendFrame126("", 8145);
		if (c.witchspot == 0) {
			c.getPA().sendFrame126("Witch's Potion", 8144);
			c.getPA().sendFrame126(
					"I can start this quest by speaking to @red@Hetty. She is",
					8147);
			c.getPA().sendFrame126("@red@located in her house in Rimmington.",
					8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("There are no minimum requirments.", 8150);

		} else if (c.witchspot == 1) {
			c.getPA().sendFrame126("Witch's Potion", 8144);
			c.getPA()
					.sendFrame126(
							"@str@I can start this quest by speaking to @red@Hetty. She is",
							8147);
			c.getPA().sendFrame126(
					"@str@@red@located in her house in Rimmington", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Hetty needs me to bring the following:",
					8150);

			if (c.getItems().playerHasItem(ONION, 1))
				c.getPA().sendFrame126("@str@@blu@An onion", 8151);
			else
				c.getPA().sendFrame126("@blu@An onion", 8151);

			if (c.getItems().playerHasItem(RATS_TAIL, 1))
				c.getPA().sendFrame126("@str@@blu@A rat's tail", 8152);
			else
				c.getPA().sendFrame126("@blu@A rat's tail", 8152);

			if (c.getItems().playerHasItem(BURNT_MEAT, 1))
				c.getPA().sendFrame126("@str@@blu@A piece of burnt meat", 8153);
			else
				c.getPA().sendFrame126("@blu@A piece of burnt meat", 8153);

			if (c.getItems().playerHasItem(EYEOF, 1))
				c.getPA().sendFrame126("@str@@blu@And a eye of newt.", 8154);
			else

				c.getPA().sendFrame126("@blu@And a eye of newt.", 8154);

			if (c.getItems().playerHasItem(EYEOF, 1)
					&& c.getItems().playerHasItem(BURNT_MEAT, 1)
					&& c.getItems().playerHasItem(ONION, 1)
					&& c.getItems().playerHasItem(RATS_TAIL, 1)) {
				c.getPA().sendFrame126(
						"Now I need to bring these items back to Hetty.", 8155);
			} else {
				c.getPA().sendFrame126("", 8155);
			}

		} else if (c.witchspot == 2) { // last part.
			c.getPA().sendFrame126("Witch's Potion", 8144);
			c.getPA()
					.sendFrame126(
							"@str@I can start this quest by speaking to @red@Hetty. She is",
							8147);
			c.getPA().sendFrame126(
					"@str@@red@located in her house in Rimmington", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126(
					"@str@Hetty needs me to bring the following:", 8150);

			c.getPA().sendFrame126("@str@@blu@An onion", 8151);
			c.getPA().sendFrame126("@str@@blu@A rat's tail", 8152);
			c.getPA().sendFrame126("@str@@blu@A piece of burnt meat", 8153);
			c.getPA().sendFrame126("@str@@blu@And a eye of newt.", 8154);
			c.getPA().sendFrame126(
					"Congratulations! You have completed this quest!", 8155);

		}
		c.getPA().showInterface(8134);
	}

}
