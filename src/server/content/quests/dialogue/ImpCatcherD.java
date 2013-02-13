package server.content.quests.dialogue;

import server.content.quests.ImpCatcher;
import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * Made by someotherdude
 */
public class ImpCatcherD {

	/**
	 * Imp Catcher dialogue
	 * 
	 * @param c
	 * @param dialogue
	 * @param npcId
	 */
	public static void dialogue(Client c, int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		case 328:
			DialogueHandler.sendPlayerChat1(c, "Give me a quest!");
			c.nextChat = 329;
			break;
		case 329:
			DialogueHandler.sendNpcChat1(c, "Give me a quest what?",
					c.talkingNpc, "Wizard Mizgog");
			c.nextChat = 330;
			break;
		case 330:
			DialogueHandler.sendOption3(c, "Give me a quest please.",
					"Give me a quest or else!",
					"Just stop messing around and give me a quest!");
			c.dialogueAction = 312;
			break;
		case 331:
			DialogueHandler.sendPlayerChat1(c, "Give me a quest please.");
			c.nextChat = 336;
			break;
		case 332:
			DialogueHandler.sendPlayerChat1(c, "Give me a quest or else!");
			c.nextChat = 334;
			break;
		case 333:
			DialogueHandler.sendPlayerChat1(c,
					"Just stop messing around and give me a quest!");
			c.nextChat = 335;
			break;
		case 334:
			DialogueHandler.sendNpcChat2(c,
					"You don't scare me! Come back when",
					"you learn some manners.", c.talkingNpc, "Wizard Mizgog");
			c.nextChat = 0;
			break;
		case 335:
			DialogueHandler.sendNpcChat2(c, "I don't deal with rude people.",
					"Come back once you can ask nicely.", c.talkingNpc,
					"Wizard Mizgog");
			c.nextChat = 0;
			break;
		case 336:
			DialogueHandler.sendNpcChat2(c,
					"Well seeing as you asked nicely... I could do with some",
					"help.", c.talkingNpc, "Wizard Mizgog");
			c.nextChat = 337;
			break;
		case 337:
			DialogueHandler.sendNpcChat2(c,
					"The wizard Grayzag next door decided he didn't like",
					"me so he enlisted an army of hundreds of imps.",
					c.talkingNpc, "Wizard Mizgog");
			c.nextChat = 338;
			break;
		case 338:
			DialogueHandler.sendNpcChat3(c,
					"These imps stole all sorts of my things. Most of these",
					"things I don't really care about, just eggs and balls of",
					"string and things.", c.talkingNpc, "Wizard Mizgog");
			c.nextChat = 339;
			break;
		case 339:
			DialogueHandler.sendNpcChat2(c,
					"But they stole my four magical beads. There was a red",
					"one, a yellow one, a black one, and a white one.",
					c.talkingNpc, "Wizard Mizgog");
			c.nextChat = 340;
			break;
		case 340:
			DialogueHandler.sendNpcChat2(c,
					"These imps have now spread out all over the kingdom.",
					"Could you get my beads back for me?", c.talkingNpc,
					"Wizard Mizgog");
			c.nextChat = 341;
			break;
		case 341:
			DialogueHandler.sendOption2(c, "I'll try.",
					"I've better things to do than chase imps.");
			c.dialogueAction = 313;
			break;
		case 342:
			DialogueHandler.sendPlayerChat1(c, "I'll try");
			c.impC++; //1
			c.getPA().loadQuests();
			c.nextChat = 344;
			break;
		case 343:
			DialogueHandler.sendPlayerChat1(c,
					"I've better things to do than chase imps.");
			c.nextChat = 0;
			break;
		case 344:
			DialogueHandler.sendNpcChat1(c, "That's great, thank you.",
					c.talkingNpc, "Wizard Mizgog");
			c.nextChat = 0;
			break;
		case 346:
			DialogueHandler.sendNpcChat1(c,
					"So how are you doing finding my beads?", c.talkingNpc,
					"Wizard Mizgog");
			c.nextChat = 347;
			break;
		case 347:
			if (c.getItems().playerHasItem(1470, 1)
					&& c.getItems().playerHasItem(1472, 1)
					&& c.getItems().playerHasItem(1474, 1)
					&& c.getItems().playerHasItem(1476, 1)) {
				DialogueHandler
						.sendPlayerChat1(c,
								"I've got all four beads. It was hard work I can tell you.");
				c.nextChat = 348;
			} else {
				DialogueHandler.sendPlayerChat1(c, "I'm still working on it.");
				c.nextChat = 0;
			}
			break;
		case 348:
			DialogueHandler
					.sendNpcChat3(
							c,
							"Give them here and I'll check that they really are MY",
							"beads, before I give you your reward. You'll like it, it's",
							"an amulet of accuracy.", c.talkingNpc,
							"Wizard Mizgog");
			c.nextChat = 349;
			break;
		case 349:
			DialogueHandler.sendStatement(c,
					"You give four coloured beads to Wizard Mizgog");
			c.getItems().deleteItem(1470, 1);
			c.getItems().deleteItem(1472, 1);
			c.getItems().deleteItem(1474, 1);
			c.getItems().deleteItem(1476, 1);
			c.nextChat = 350;
			break;
		case 350:
			c.getItems().addItem(1478, 1);
			c.getPA().addSkillXP(875, 6);
			c.impC++;
			c.getPA().loadQuests();
			c.getPA().addSkillXP(875, 6);
			ImpCatcher.impReward(c);
			break;
		case 352:
			DialogueHandler.sendNpcChat1(c, "Thank you for helping me",
					c.talkingNpc, "Wizard Mizgog");
			c.nextChat = 0;
			break;
		}

	}
}
