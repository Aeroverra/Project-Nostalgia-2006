package server.content.quests.dialogue;

import server.content.quests.RestlessGhost;
import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * Made by someotherdude
 */
public class RestlessGhostD {

	/**
	 * The Restless Ghost dialogue
	 * 
	 * @param c
	 * @param dialogue
	 * @param npcId
	 */
	public static void dialogue(Client c, int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		case 541:
			DialogueHandler.sendNpcChat1(c,
					"Welcome to the church of holy Saradomin.", 456,
					"Father Aereck");
			c.nextChat = 542;
			break;
		case 542:
			DialogueHandler.sendOption3(c, "Who's Saradomin?",
					"Nice place you've got here.", "I'm looking for a quest.");
			c.dialogueAction = 542;
			break;
		case 543:
			DialogueHandler.sendPlayerChat1(c, "Who's Saradomin?");
			c.nextChat = 546;
			break;
		case 544:
			DialogueHandler.sendPlayerChat1(c, "Nice place you've got here");
			c.nextChat = 547;
			break;
		case 545:
			DialogueHandler.sendPlayerChat1(c, "I'm looking for a quest");
			c.nextChat = 548;
			break;
		case 546:
			DialogueHandler
					.sendNpcChat3(
							c,
							"Saradomin is the god of pleasantry, order, and wisdom.",
							"His main goal has always been to rid these lands of evil,",
							"especially the likes of Zamorak and his followers.",
							c.talkingNpc, "Father Aereck");
			c.nextChat = 542;
			break;
		case 547:
			DialogueHandler.sendNpcChat3(c, "Thank you!",
					"This is the church of holy Saradomin.",
					"It is more than two hundred and thirty years old",
					c.talkingNpc, "Father Aereck");
			c.nextChat = 542;
			break;
		case 548:
			DialogueHandler.sendNpcChat1(c,
					"That's lucky. I need someone to do a quest for me.",
					c.talkingNpc, "Father Aereck");
			c.nextChat = 549;
			break;
		case 549:
			DialogueHandler.sendPlayerChat1(c, "Ok, let me help then.");
			c.nextChat = 550;
			break;
		case 550:
			DialogueHandler.sendNpcChat2(c,
					"Thank you. The problem is, there is a ghost in the",
					"church graveyards. I would like you to get rid of it.",
					c.talkingNpc, "Father Aereck");
			c.restlessG++; // 1
			c.nextChat = 551;
			c.getPA().loadQuests(); // yellow time oll
			break;
		case 551:
			DialogueHandler.sendNpcChat2(c,
					"If you need any help, my friend Father Urhney is an",
					"expert on ghosts.", c.talkingNpc, "Father Aereck");
			c.nextChat = 552;
			break;
		case 552:
			DialogueHandler.sendNpcChat4(c,
					"I believe he is currently living as a hermit. He has a",
					"little shack somewhere in the swamps south of here. I'm",
					"sure if you told him that I sent you he'd be willing to",
					"help.", c.talkingNpc, "Father Aereck");
			c.nextChat = 553;
			break;
		case 553:
			DialogueHandler.sendNpcChat2(c,
					"My name is Father Aereck by the way. Please to",
					"meet you.", c.talkingNpc, "Father Aereck");
			c.nextChat = 554;
			break;
		case 554:
			DialogueHandler.sendPlayerChat1(c, "Likewise.");
			c.nextChat = 555;
			break;
		case 555:
			DialogueHandler.sendNpcChat2(c,
					"Take care travelling through the swamps, I have heard",
					"they can be quite dangerous.", c.talkingNpc,
					"Father Aereck");
			c.nextChat = 556;
			break;
		case 556:
			DialogueHandler.sendPlayerChat1(c, "I will, thanks.");
			c.nextChat = 0;
			break;
		case 557:
			DialogueHandler.sendNpcChat1(c, "Ohhh oooohh Ooooohhhh oh ohhh",
					c.talkingNpc, "Restless Ghost");
			c.nextChat = 0;
			break;
		case 558:
			DialogueHandler.sendNpcChat1(c, "Go away! I'm meditating!",
					c.talkingNpc, "Father Urhney");
			c.nextChat = 559;
			break;
		case 559:
			DialogueHandler.sendOption3(c, "Well that's friendly.",
					"Father Aereck sent me to talk to you.",
					"I've come to repossess your house.");
			c.dialogueAction = 559;
			break;
		case 560:
			DialogueHandler.sendPlayerChat1(c, "Well that's friendly.");
			c.nextChat = 564;
			break;
		case 561:
			DialogueHandler.sendPlayerChat1(c,
					"Father Aereck sent me to talk to you.");
			c.nextChat = 565;
			break;
		case 562:
			DialogueHandler.sendPlayerChat1(c,
					"I've come to repossess your house.");
			c.nextChat = 563;
			break;
		case 563:
			DialogueHandler.sendNpcChat2(c,
					"I seriously doubt that. I built this house myself.",
					"Now go away.", c.talkingNpc, "Father Urhney");
			c.nextChat = 0;
			break;
		case 564:
			DialogueHandler.sendNpcChat3(c,
					"I have committed to being alone for two years so that",
					"I can dedicate myself to prayer and meditation",
					"Now please go away.", c.talkingNpc, "Father Urhney");
			c.nextChat = 0;
			break;
		case 565:
			DialogueHandler.sendNpcChat2(c,
					"I suppose I'd better talk to you then. What problems",
					"has he got himself into this time?", c.talkingNpc,
					"Father Urhney");
			c.nextChat = 566;
			break;
		case 566:
			DialogueHandler.sendOption2(c,
					"He's got a ghost haunting his graveyard",
					"You mean he gets himself into lots of problems?");
			c.dialogueAction = 566;
			break;
		case 567:
			DialogueHandler.sendPlayerChat1(c,
					"He's got a ghost haunting his graveyard.");
			c.nextChat = 570;
			break;
		case 568:
			DialogueHandler.sendPlayerChat1(c,
					"You mean he gets himself into lots of problems?");
			c.nextChat = 569;
			break;
		case 569:
			DialogueHandler.sendNpcChat4(c,
					"Oh yes, he's always been one for trouble",
					"every once in a while some adventurer will come",
					"and try to repossess my house because of some",
					"mess Father Aereck has gotten into", c.talkingNpc,
					"Father Urhney");
			c.nextChat = 566;
			break;
		case 570:
			DialogueHandler.sendNpcChat1(c, "Oh the silly fool.", c.talkingNpc,
					"Father Urhney");
			c.nextChat = 571;
			break;
		case 571:
			DialogueHandler.sendNpcChat2(c,
					"I leave town for just five months, and ALREADY he",
					"can't manage", c.talkingNpc, "Father Urhney");
			c.nextChat = 572;
			break;
		case 572:
			DialogueHandler.sendNpcChat1(c, "(sigh)", c.talkingNpc,
					"Father Urhney");
			c.nextChat = 573;
			break;
		case 573:
			DialogueHandler.sendNpcChat3(c,
					"Well I can't go back and exorcise it. I vowed not to",
					"leave this place until I had done a full two years of",
					"prayer and meditation.", c.talkingNpc, "Father Urhney");
			c.nextChat = 574;
			break;
		case 574:
			DialogueHandler.sendNpcChat1(c,
					"Tell you what I can do though; take this amulet.",
					c.talkingNpc, "Father Urhney");
			c.nextChat = 575;
			break;
		case 575:
			DialogueHandler.sendStatement(c,
					"Father Urhney hands you an amulet");
			c.restlessG++; // 2
			c.getItems().addItem(552, 1);
			c.nextChat = 576;
			c.getPA().loadQuests();
			break;
		case 576:
			DialogueHandler.sendNpcChat1(c, "It is an Amulet of Ghostspeak",
					c.talkingNpc, "Father Urhney");
			c.nextChat = 577;
			break;
		case 577:
			DialogueHandler.sendNpcChat3(c,
					"So called because when you wear it you can speak to",
					"ghosts. A lot of ghosts are doomed to be ghosts because",
					"they have left some unimportant task uncompleted.",
					c.talkingNpc, "Father Urhney");
			c.nextChat = 578;
			break;
		case 578:
			DialogueHandler.sendNpcChat3(c,
					"Maybe if you know what this task is you can get rid of",
					"the ghost. I'm not making any guarantees mind you,",
					"but it is the best I can do right now.", c.talkingNpc,
					"Father Urhney");
			c.nextChat = 579;
			break;
		case 579:
			DialogueHandler
					.sendPlayerChat1(c, "Thank you. I'll give it a try!");
			c.nextChat = 0;
			break;
		case 580:
			DialogueHandler.sendNpcChat1(c, "Go away! I'm meditating!",
					c.talkingNpc, "Father Urhney");
			c.nextChat = 0;
			break;

		case 581:
			DialogueHandler.sendPlayerChat1(c, "Hello ghost, how are you?");
			c.nextChat = 582;
			break;
		case 582:
			DialogueHandler.sendNpcChat1(c, "Not very good actually.",
					c.talkingNpc, "Restless Ghost");
			c.nextChat = 583;
			break;
		case 583:
			DialogueHandler.sendPlayerChat1(c, "What's the problem then?");
			c.nextChat = 584;
			break;
		case 584:
			DialogueHandler.sendNpcChat1(c,
					"Did you just understand what I said???", c.talkingNpc,
					"Restless Ghost");
			c.nextChat = 585;
			break;
		case 585:
			DialogueHandler.sendOption3(c,
					"Yep, now tell me what the problem is.",
					"No, you sound like you're speaking nonsense to me.",
					"Wow, this amulet works!");
			c.dialogueAction = 585;
			break;
		case 586:
			DialogueHandler.sendNpcChat2(c,
					"Well that's too bad. For a moment I",
					"really thought you could understand me.", c.talkingNpc,
					"Restless Ghost");
			c.nextChat = 587;
			break;
		case 587:
			DialogueHandler.sendPlayerChat1(c, "Yeah that is too bad!");
			c.nextChat = 588;
			break;
		case 588:
			DialogueHandler.sendNpcChat1(c, "....", c.talkingNpc,
					"Restless Ghost");
			c.nextChat = 589;
			break;
		case 589:
			DialogueHandler.sendNpcChat1(c,
					"Are you sure you can't understand me?", c.talkingNpc,
					"Restless Ghost");
			c.nextChat = 590;
			break;
		case 590:
			DialogueHandler.sendPlayerChat1(c, "Yep I'm sure!");
			c.nextChat = 591;
			break;
		case 591:
			DialogueHandler.sendNpcChat2(c, ".....", "Ok then..", c.talkingNpc,
					"Restless Ghost");
			c.nextChat = 0;
			break;
		case 592:
			DialogueHandler.sendPlayerChat1(c,
					"Yep, now tell me what the problem is.");
			c.nextChat = 595;
			break;
		case 593:
			DialogueHandler.sendPlayerChat1(c,
					"No, you sound like you're speaking nonsense to me");
			c.nextChat = 586;
			break;
		case 594:
			DialogueHandler.sendPlayerChat1(c, "Wow this amulet works!");
			c.nextChat = 595;
			break;
		case 595:
			DialogueHandler.sendNpcChat2(c,
					"WOW! This is INCREDIBLE! I didn't expect anyone",
					"to ever understand me again!", c.talkingNpc,
					"Restless Ghost");
			c.nextChat = 596;
			break;
		case 596:
			DialogueHandler.sendPlayerChat1(c, "Ok, Ok, I can understand you!");
			c.nextChat = 597;
			break;
		case 597:
			DialogueHandler
					.sendPlayerChat2(c,
							"But have you any idea WHY you're doomed to be a",
							"ghost?");
			c.nextChat = 598;
			break;
		case 598:
			DialogueHandler.sendNpcChat1(c,
					"Well to be honest... I'm not sure.", c.talkingNpc,
					"Restless Ghost");
			c.nextChat = 599;
			break;
		case 599:
			DialogueHandler.sendPlayerChat2(c,
					"I've been told a certain task may need to be completed",
					"so you can rest in peace.");
			c.nextChat = 600;
			break;
		case 600:
			DialogueHandler.sendNpcChat3(c,
					"I should think it is probably because a warlock has come",
					"along and stolen my skull. If you look inside my coffin",
					"there, you'll find my corpse without a head on it.",
					c.talkingNpc, "Restless Ghost");
			c.nextChat = 601;
			break;
		case 601:
			DialogueHandler.sendPlayerChat1(c,
					"Do you know where this warlock might be now?");
			c.nextChat = 602;
			break;
		case 602:
			DialogueHandler.sendNpcChat2(c,
					"I think it was one of the warlocks who lives in the big",
					"tower by the sea south-west from here.", c.talkingNpc,
					"Restless Ghost");
			c.nextChat = 603;
			break;
		case 603:
			DialogueHandler.sendPlayerChat2(c,
					"Ok, I will try and get the skull back for you, then you",
					"can rest in peace.");
			c.restlessG++; // 3
			c.nextChat = 604;
			c.getPA().loadQuests();
			break;
		case 604:
			DialogueHandler.sendNpcChat1(c,
					"Ooh, thank you. That would be such a great relief!",
					c.talkingNpc, "Restless Ghost");
			c.nextChat = 605;
			break;
		case 605:
			DialogueHandler.sendNpcChat1(c, "It is so dull being a ghost...",
					c.talkingNpc, "Restless Ghost");
			c.nextChat = 0;
			break;
		case 606:
			DialogueHandler.sendNpcChat1(c, "Release! Thank you stranger...",
					c.talkingNpc, "Restless Ghost");
			c.nextChat = 607;
			break;
		case 607:
			c.restlessG++; // 4
			c.getPA().loadQuests(); // green
			RestlessGhost.ghostReward(c);
			break;
		}

	}
}
