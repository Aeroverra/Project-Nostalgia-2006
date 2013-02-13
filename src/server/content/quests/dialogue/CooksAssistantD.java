package server.content.quests.dialogue;

import server.content.quests.misc.QuestHandling;
import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * Made by someotherdude
 */
public class CooksAssistantD {

	/**
	 * Cook's Assistant dialogue
	 * 
	 * @param c
	 * @param dialogue
	 * @param npcId
	 */
	public static void dialogue(Client c, int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		case 100:
			DialogueHandler.sendNpcChat1(c, "Oh dear, what am I going to do?",
					c.talkingNpc, "Cook");
			c.nextChat = 101;
			break;
		case 101:
			DialogueHandler.sendPlayerChat1(c, "What's wrong?");
			c.nextChat = 102;
			break;
		case 102:
			DialogueHandler
					.sendNpcChat2(
							c,
							"It's the king's birthday and I'm supposed to make a cake.",
							"But I have forgotten to get the ingredients.",
							c.talkingNpc, "Cook");
			c.nextChat = 103;
			break;
		case 103:
			DialogueHandler.sendNpcChat2(c,
					"The king is going to kill me if I don't make him a cake!",
					"Can you please help me get the ingredients?",
					c.talkingNpc, "Cook");
			c.nextChat = 104;
			break;
		case 104:
			DialogueHandler.sendOption2(c, "Yes, I'll help you.",
					"No, I don't have time, sorry.");
			c.dialogueAction = 100;
			break;
		case 105: // accept quest
			c.cooksA = 1;
			c.getPA().loadQuests();
			DialogueHandler.sendNpcChat1(c, "Thank you so much, "
					+ (c.playerName) + "!", c.talkingNpc, "Cook");
			c.nextChat = 106;
			break;
		case 106:
			DialogueHandler.sendNpcChat1(c,
					"Please get me milk, flour and an egg.", c.talkingNpc,
					"Cook");
			c.nextChat = 0;
			break;
		case 107:// refuse quest
			DialogueHandler.sendPlayerChat1(c, "I don't have time, sorry.");
			c.nextChat = 0;
			break;
		case 108:
			DialogueHandler.sendNpcChat1(c,
					"How are you getting on with the ingredients?",
					c.talkingNpc, "Cook");
			c.nextChat = 109;
			break;
		case 109:
			if (c.getItems().playerHasItem(1927)
					&& c.getItems().playerHasItem(1944)
					&& c.getItems().playerHasItem(1933)) {
				c.getItems().deleteItem(1927, 1);
				c.getItems().deleteItem(1944, 1);
				c.getItems().deleteItem(1933, 1);
				DialogueHandler.sendPlayerChat1(c, "Here are the ingredients.");
				c.cooksA = 2;
				c.getPA().loadQuests();
				c.nextChat = 110;
			} else {
				DialogueHandler.sendPlayerChat1(c, "I'm still working on it.");
				c.nextChat = 0;
			}
			break;
		case 110:
			DialogueHandler.sendNpcChat2(c, "Thank you so much for the help!",
					"I will reward you for this!", c.talkingNpc, "Cook");
			c.getPA().loadQuests();
			c.nextChat = 111;
			break;
		case 111:
			QuestHandling.cookFinish(c);
			c.cooksA = 3;
			c.nextChat = 0;
			break;
		case 112:
			DialogueHandler.sendNpcChat1(c, "It's such a beautiful day!",
					c.talkingNpc, "Cook");
			c.nextChat = 0;
			break;
		}

	}
}
