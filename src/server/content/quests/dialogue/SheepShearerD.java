package server.content.quests.dialogue;

import server.content.quests.SheepShearer;
import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * Made by someotherdude
 */
public class SheepShearerD {

	/**
	 * Sheep Shearer
	 * 
	 * @param c
	 * @param dialogue
	 * @param npcId
	 */
	public static void dialogue(Client c, int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		case 515:
			DialogueHandler.sendNpcChat3(c,
					"What are you doing on my land? You're not the one",
					"keeps leaving all my gates open and letting out all my",
					"sheep?", c.talkingNpc, "Fred the Farmer");
			c.nextChat = 516;
			break;
		case 516:
			DialogueHandler.sendOption3(c, "I'm looking for a quest.",
					"I'm looking for something to kill.", "I'm lost.");
			c.dialogueAction = 516;
			break;
		case 517:
			DialogueHandler.sendPlayerChat1(c, "I'm looking for a quest.");
			c.nextChat = 523;
			break;
		case 518:
			DialogueHandler.sendPlayerChat1(c,
					"I'm looking for something to kill.");
			c.nextChat = 520;
			break;
		case 519:
			DialogueHandler.sendPlayerChat1(c, "I'm lost.");
			c.nextChat = 521;
			break;
		case 520:
			DialogueHandler.sendNpcChat2(c,
					"There's been a lot of goblins around here lately.",
					"It would be nice to see them gone.", c.talkingNpc,
					"Fred the Farmer");
			c.nextChat = 522;
			break;
		case 521:
			DialogueHandler.sendNpcChat1(c,
					"Do I look like someone that can help you with that?",
					c.talkingNpc, "Fred the Farmer");
			c.nextChat = 0;
			break;
		case 522:
			DialogueHandler.sendPlayerChat1(c, "Ok, Thanks!");
			c.nextChat = 0;
			break;
		case 523:
			DialogueHandler.sendNpcChat2(c,
					"You're after a quest, you say? Actually I could do with",
					"a bit of help.", c.talkingNpc, "Fred the Farmer");
			c.nextChat = 524;
			break;
		case 524:
			DialogueHandler.sendNpcChat3(c,
					"My sheep are getting mighty woolly. I'd be much",
					"obliged if you could shear them. And while you're at it",
					"spin the wool for me too.", c.talkingNpc,
					"Fred the Farmer");
			c.nextChat = 525;
			break;
		case 525:
			DialogueHandler.sendNpcChat3(c,
					"Yes, that's it. Bring me 20 balls of wool. And I'm sure",
					"I could sort out some sort of payment. Of course,",
					"there's the small matter of The Thing.", c.talkingNpc,
					"Fred the Farmer");
			c.nextChat = 526;
			break;
		case 526:
			DialogueHandler.sendOption3(c, " Yes okay. I can do that.",
					"That doesn't sound a very exciting quest.",
					"what do you mean, The Thing?");
			c.dialogueAction = 526;
			break;
		case 527:
			DialogueHandler.sendPlayerChat1(c, "Yes okay. I can do that.");
			c.sheepS++; // 1
			c.nextChat = 532;
			c.getPA().loadQuests(); // yellow time oll
			break;
		case 528:
			DialogueHandler.sendPlayerChat1(c,
					"That doesn't sound a very exciting quest.");
			c.nextChat = 531;
			break;
		case 529:
			DialogueHandler.sendPlayerChat1(c, "What do you mean, The Thing?");
			c.nextChat = 530;
			break;
		case 530:
			DialogueHandler
					.sendNpcChat3(
							c,
							"Well, something seems to happen to people who try to shear",
							"the sheep, but I've never seen what does it.",
							"You'll probably be fine though.", c.talkingNpc,
							"Fred the Farmer");
			c.nextChat = 526;
			break;
		case 531:
			DialogueHandler.sendNpcChat1(c,
					"Well if you don't want it why come barging in my house?",
					c.talkingNpc, "Fred the Farmer");
			c.nextChat = 526;
			break;
		case 532:
			DialogueHandler.sendNpcChat1(c,
					"Ok I'll see you when you have some wool.", c.talkingNpc,
					"Fred the Farmer");
			c.nextChat = 0;
			break;
		case 533:
			DialogueHandler.sendNpcChat1(c,
					"How are you doing getting those balls of wool?",
					c.talkingNpc, "Fred the Farmer");
			c.nextChat = 534;
			break;
		case 534:
			if (c.getItems().playerHasItem(1759, 20)) {
				DialogueHandler.sendPlayerChat1(c, "I have some");
				c.nextChat = 536;
			} else {
				DialogueHandler.sendPlayerChat1(c, "I'm still working on it");
				c.nextChat = 535;
			}
			break;
		case 535:
			DialogueHandler.sendNpcChat1(c, "Come back when you have the rest",
					c.talkingNpc, "Fred the Farmer");
			c.nextChat = 0;
			break;
		case 536:
			DialogueHandler.sendNpcChat1(c, "Give 'em here then", c.talkingNpc,
					"Fred the Farmer");
			c.nextChat = 537;
			break;
		case 537:
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			c.sendMessage("You give Fred a ball of wool");
			c.getItems().deleteItem(1759, 1);
			DialogueHandler
					.sendPlayerChat1(c, "I have your last ball of wool.");
			c.nextChat = 538;
			break;
		case 538:
			DialogueHandler.sendNpcChat1(c, "I guess I'd better pay you then.",
					c.talkingNpc, "Fred the Farmer");
			c.nextChat = 539;
			break;
		case 539:
			c.getItems().deleteItem(1759, 1);
			c.sheepS++;
			c.questPoints++;
			c.getPA().addSkillXP(150, 12);
			c.getItems().addItem(995, 60);
			SheepShearer.sheepReward(c);
			c.getPA().loadQuests();
			break;
		case 540:
			DialogueHandler.sendNpcChat1(c,
					"Feel free to shear my sheep for wool any time.",
					c.talkingNpc, "Fred the Farmer");
			c.nextChat = 0;
			break;
		}

	}
}
