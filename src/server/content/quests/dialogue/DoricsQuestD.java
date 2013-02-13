package server.content.quests.dialogue;

import server.content.quests.DoricsQuest;
import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * Made by someotherdude
 */
public class DoricsQuestD {

	/**
	 * Doric's Quest dialogue
	 * 
	 * @param c
	 * @param dialogue
	 * @param npcId
	 */
	public static void dialogue(Client c, int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		case 300:
			DialogueHandler.sendNpcChat1(c,
					"Hello traveller, what brings you to my humble smithy?",
					c.talkingNpc, "Doric");
			c.nextChat = 301;
			break;
		case 301:
			DialogueHandler.sendOption4(c, "I wanted to see use your anvils.",
					"Mind your own business, shortstuff!",
					"I was just checking out the landscape.",
					"What do you make here?");
			c.dialogueAction = 309;
			break;
		case 302:
			DialogueHandler.sendNpcChat4(c,
					"My anvils get enough work with my own use. I make",
					"pickaxes, and it takes a lot of hard work. If you could",
					"get me some more materials, then I could let you use",
					"them.", c.talkingNpc, "Doric");
			c.nextChat = 303;
			break;
		case 303:
			DialogueHandler.sendOption2(c,
					"Yes, I will get you the materials.",
					"No, hitting rocks is for boring people, sorry.");
			c.dialogueAction = 310;
			break;
		case 304: // accept quest
			DialogueHandler.sendPlayerChat1(c,
					"Yes, I will get you the materials");
			c.nextChat = 305;
			break;
		case 305:
			c.getPA().loadQuests();
			DialogueHandler.sendNpcChat4(c,
					"Clay is what I use more than anything to make casts.",
					"Could you get me 6 clay, 4 copper ore, and 2 iron ore",
					"please? I could pay a little, and let you use my anvils.",
					"Take this pickaxe with you just in case you need it.",
					c.talkingNpc, "Doric");
			c.doricsQ += 1;
			c.getPA().loadQuests();
			c.nextChat = 306;
			break;
		case 306:
			DialogueHandler.sendOption2(c, "Certainly, I'll be right back!",
					"Where can I find those?");
			c.getItems().addItem(1265, 1);
			c.dialogueAction = 311;
			break;
		case 307:
			DialogueHandler
					.sendPlayerChat1(c, "Certainly, I'll be right back!");
			c.nextChat = 0;
			break;
		case 308:
			DialogueHandler.sendNpcChat1(c,
					"Have you got my materials yet, traveller?", c.talkingNpc,
					"Doric");
			c.nextChat = 309;
			break;
		case 309:
			if (c.getItems().playerHasItem(436, 4)
					&& c.getItems().playerHasItem(434, 6)
					&& c.getItems().playerHasItem(440, 2)) {
				DialogueHandler.sendPlayerChat1(c,
						"I have everything you need.");
				c.nextChat = 310;
			} else {
				DialogueHandler.sendPlayerChat1(c, "I'm still working on it.");
				c.nextChat = 0;
			}
			break;
		case 310:
			DialogueHandler.sendNpcChat3(c,
					"Many thanks. Pass them here, please. I can spare you",
					"some coins for your trouble, and please use my anvils",
					"any time you want.", c.talkingNpc, "Doric");
			c.getPA().loadQuests();
			c.nextChat = 311;
			break;
		case 311:
			DialogueHandler.sendStatement(c,
					"You hand the clay, copper, and iron to Doric.");
			c.getItems().deleteItem(436, 1);
			c.getItems().deleteItem(436, 1);
			c.getItems().deleteItem(436, 1);
			c.getItems().deleteItem(436, 1);
			c.getItems().deleteItem(434, 1);
			c.getItems().deleteItem(434, 1);
			c.getItems().deleteItem(434, 1);
			c.getItems().deleteItem(434, 1);
			c.getItems().deleteItem(434, 1);
			c.getItems().deleteItem(434, 1);
			c.getItems().deleteItem(440, 1);
			c.getItems().deleteItem(440, 1);
			c.questPoints += 1;
			c.getPA().loadQuests();
			c.nextChat = 314;
			break;
		case 312:
			DialogueHandler.sendNpcChat1(c,
					"I hope my anvils are working well for you!", c.talkingNpc,
					"Doric");
			c.nextChat = 0;
			break;
		case 314:
			c.doricsQ = 3;
			c.getPA().addSkillXP(1300, 14);
			c.getItems().addItem(995, 180);
			DoricsQuest.doricReward(c);
			c.getPA().loadQuests();
			break;
		}

	}
}
