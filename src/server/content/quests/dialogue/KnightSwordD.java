package server.content.quests.dialogue;

import server.content.quests.KnightSword;
import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * Made by someotherdude
 */
public class KnightSwordD {

	/**
	 * Knight's Sword dialogue
	 * 
	 * @param c
	 * @param dialogue
	 * @param npcId
	 */
	public static void dialogue(Client c, int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		case 610:
			DialogueHandler.sendNpcChat1(c,
					"Hello. I am the squire to Sir Vyvin.", c.talkingNpc,
					"Squire");
			c.nextChat = 611;
			break;
		case 611:
			DialogueHandler.sendOption2(c, "And how is life as a squire?",
					"Wouldn't you prefer to be a squire for me?");
			c.dialogueAction = 611;
			break;
		case 612:
			DialogueHandler.sendPlayerChat1(c, "And how is life as a squire?");
			c.nextChat = 613;
			break;
		case 613:
			DialogueHandler.sendNpcChat3(c,
					"Well, sir Vyvin is a good guy to work for",
					"however, I'm in a spot of trouble today.",
					"I've gone and lost Sir Vyvin's sword!", c.talkingNpc,
					"Squire");
			c.nextChat = 614;
			break;
		case 614:
			DialogueHandler.sendOption3(c, "Do you know where you lost it?",
					"I can make a new sword if you like...", "Is he angry?");
			c.dialogueAction = 614;
			break;
		case 615:
			DialogueHandler.sendPlayerChat1(c,
					"I can make a new sword if you like...");
			c.nextChat = 616;
			break;
		case 616:
			DialogueHandler
					.sendNpcChat1(
							c,
							"Thanks for the offer. I'd be surprised if you could though",
							c.talkingNpc, "Squire");
			c.nextChat = 617;
			break;
		case 617:
			DialogueHandler.sendNpcChat4(c,
					"The thing is, this sword is a family heirloom. It has",
					"been passed down through Vyvin's family for",
					"five generations! It was originally made by",
					"the Imacando dwarves, who were", c.talkingNpc, "Squire");
			c.nextChat = 618;
			break;
		case 618:
			DialogueHandler.sendNpcChat2(c,
					"a particularly skilled tribe of dwarven smiths.",
					"I doubt anyone could make it in the style they do.",
					c.talkingNpc, "Squire");
			c.nextChat = 619;
			break;
		case 619:
			DialogueHandler.sendOption2(c,
					"So would these dwarves make another one?",
					"Well I hope you find it soon.");
			c.dialogueAction = 619;
			break;
		case 620:
			DialogueHandler.sendPlayerChat1(c,
					"So would these dwarves make another one?");
			c.nextChat = 621;
			break;
		case 621:
			DialogueHandler
					.sendNpcChat4(
							c,
							"I'm not a hundred percent sure the Imacando tribe",
							"exists anymore. I should think Reldo, the palace librarian",
							"will know; he has done a lot of research",
							"on the races of Runescape.", c.talkingNpc,
							"Squire");
			c.nextChat = 622;
			break;
		case 622:
			DialogueHandler.sendNpcChat2(c,
					"I don't suppose you could try and track down the",
					"Imacando dwarves for me? I've got so much work to do...",
					c.talkingNpc, "Squire");
			c.nextChat = 623;
			break;
		case 623:
			DialogueHandler.sendOption2(c, "Ok, I'll give it a go.",
					"No, I've got lots of mining work to do.");
			c.dialogueAction = 623;
			break;
		case 624:
			DialogueHandler.sendPlayerChat1(c, "Ok, I'll give it a go.");
			c.knightS += 1;
			c.nextChat = 625;
			break;
		case 625:
			DialogueHandler.sendNpcChat2(c,
					"Thank you very much! As I say, the best place",
					"to start should be with Reldo...", c.talkingNpc, "Squire");
			c.nextChat = 0;
			break;

		// reldo starts here
		case 626:
			DialogueHandler.sendNpcChat1(c, "Hello stranger.", c.talkingNpc,
					"Reldo");
			c.nextChat = 627;
			break;
		case 627:
			if (c.knightS == 1) {
				DialogueHandler.sendPlayerChat1(c,
						"What do you know about the Imcando dwarves?");
				c.nextChat = 628;
			}
			break;
		case 628:
			DialogueHandler.sendNpcChat1(c, "The imcando dwarves, you say?",
					c.talkingNpc, "Reldo");
			c.nextChat = 629;
			break;
		case 629:
			DialogueHandler.sendNpcChat3(c,
					"Ah yes... for many hundreds of years they were the",
					"world's most skilled smiths. They used secret smithing",
					"knowledge passed down from generation to generation",
					c.talkingNpc, "Reldo");
			c.nextChat = 630;
			break;
		case 630:
			DialogueHandler
					.sendNpcChat2(
							c,
							"Unfortunately, a century ago, the once thriving race",
							"was wiped out during the barbarian invasions of that time.",
							c.talkingNpc, "Reldo");
			c.nextChat = 631;
			break;
		case 631:
			DialogueHandler
					.sendPlayerChat1(c, "So are there any Imcando left?");
			c.nextChat = 632;
			break;
		case 632:
			DialogueHandler.sendNpcChat3(c,
					"I believe a few of them survived, but with the bulk of",
					"their population destroyed their numbers have",
					"dwindled even further.", c.talkingNpc, "Reldo");
			c.nextChat = 633;
			break;
		case 633:
			DialogueHandler.sendNpcChat3(c,
					"I believe I remember a couple living in Asgarnia near",
					"the cliffs on the Asgarnian southern peninsula but",
					"they DO tend to keep to themselves.", c.talkingNpc,
					"Reldo");
			c.nextChat = 634;
			break;
		case 634:
			DialogueHandler
					.sendNpcChat4(
							c,
							"They tend not to tell people they're the descendents of",
							"the Imcando, which is why people think that the tribe has",
							"died out totally, but you may well have more luck talking",
							" to them if you bring them some", c.talkingNpc,
							"Reldo");
			c.nextChat = 635;
			break;
		case 635:
			DialogueHandler.sendNpcChat1(c,
					"redberry pie. They REALLY like redberry pie.",
					c.talkingNpc, "Reldo");
			c.knightS += 1;
			c.nextChat = 0;
			break;

		// start thurgo

		case 636:
			if (c.knightS == 2) {
				if (c.getItems().playerHasItem(2325, 1)) {
					DialogueHandler.sendOption2(c,
							"Hello. Are you an Imcando dwarf?",
							"Would you like some redberry pie?");
					c.dialogueAction = 636;
				} else {
					DialogueHandler.sendPlayerChat1(c,
							"Hello. Are you an Imcando dwarf?");
					c.nextChat = 0;
				}
			}
			break;
		case 637:
			DialogueHandler.sendPlayerChat1(c,
					"Would you like some redberry pie?");
			c.nextChat = 638;
			break;
		case 638:
			DialogueHandler.sendStatement(c, "You see Thurgo's eyes light up.");
			c.nextChat = 639;
			break;

		case 639:
			DialogueHandler.sendNpcChat1(c,
					"I'd never say no to a redberry pie! They're GREAT stuff!",
					c.talkingNpc, "Thurgo");
			c.nextChat = 640;
			break;
		case 640:
			DialogueHandler
					.sendStatement(c,
							"You hand over the pie. Thurgo eats the pie. Thurgo pats his stomach.");
			c.getItems().deleteItem(2325, 1);
			c.nextChat = 641;
			break;
		case 641:
			DialogueHandler.sendNpcChat2(c, "By Guthix! THAT was a good pie!",
					"Anyone who makes pie like THAT has got to be alright!",
					c.talkingNpc, "Thurgo");
			c.knightS += 1;
			c.nextChat = 0;
			break;
		case 642:
			DialogueHandler.sendPlayerChat1(c, "Can you make a special sword?");
			c.nextChat = 643;
			break;
		case 643:
			DialogueHandler.sendNpcChat2(c,
					"Well, after bringing me my favorite food I guess I",
					"should give it a go. What sort of sword is it?",
					c.talkingNpc, "Thurgo");
			c.nextChat = 644;
			break;
		case 644:
			DialogueHandler.sendPlayerChat4(c,
					"I need you to make a sword for one of Falador's",
					"knights. He had one which was passed down through five",
					" generations, but his squire lost it.",
					" So we need an identical one to replace it.");
			c.nextChat = 645;
			break;
		case 645:
			DialogueHandler.sendNpcChat2(c,
					"A knight's sword eh? Well I'd need to know exactly",
					"how it looked before I could make a new one.",
					c.talkingNpc, "Thurgo");
			c.nextChat = 646;
			break;
		case 646:
			DialogueHandler.sendNpcChat3(c,
					"All the Faladorian knights used to have sword with",
					" unique designs according to their position.",
					"Could you bring me a picture or something?", c.talkingNpc,
					"Thurgo");
			c.nextChat = 647;
			break;
		case 647:
			DialogueHandler.sendPlayerChat1(c,
					"I'll go ask his squire and see if I can find one.");
			c.knightS += 1;
			c.nextChat = 0;
			break;
		// back to squire
		case 648:
			DialogueHandler.sendNpcChat1(c,
					"So how are you doing getting the sword?", c.talkingNpc,
					"Squire");
			c.nextChat = 649;
			break;
		case 649:
			DialogueHandler.sendPlayerChat2(c,
					"I've found an Imcando dwarf but",
					"he needs a picture of the sword before he can make it.");
			c.nextChat = 650;
			break;
		case 650:
			DialogueHandler.sendNpcChat3(c,
					"A picture ehh? Hmmm... The only one I can think of is",
					"in a small portrait of Sir Vyvin's father...",
					"Sir Vyvin keeps it in a cupboard in his room I think.",
					c.talkingNpc, "Squire");
			c.nextChat = 651;
			break;
		case 651:
			DialogueHandler.sendPlayerChat1(c,
					"Ok, I'll try and get that then.");
			c.nextChat = 652;
			break;
		case 652:
			DialogueHandler.sendNpcChat2(c, "Please don't let him catch you!",
					"He MUSTN'T know what happened!", c.talkingNpc, "Squire");
			c.knightS += 1;
			c.nextChat = 0;
			break;

		// getting the picture
		case 653:
			DialogueHandler.sendStatement(c,
					"You find a small portrait in here which you take.");
			c.nextChat = 0;
			break;

		// back to thurgo
		case 654:
			DialogueHandler.sendPlayerChat2(c,
					"I have found a picture of the sword",
					"I would like you to make.");
			c.nextChat = 655;
			break;
		case 655:
			DialogueHandler
					.sendStatement(c,
							"You give the portrait to Thurgo. Thurgo studies the portrait.");
			c.getItems().deleteItem(666, 1);
			c.nextChat = 656;
			break;
		case 656:
			DialogueHandler.sendNpcChat2(c,
					"Ok. You'll need to get me some stuff",
					"in order for me to make this.", c.talkingNpc, "Thurgo");
			c.nextChat = 657;
			break;
		case 657:
			DialogueHandler.sendNpcChat4(c,
					"I'll need two iron bars to make the sword to start with.",
					"I'll also need an ore called blurite.",
					"It's useless for making actual weapons for fighting,",
					"but I'll need some as decoration for the hilt.",
					c.talkingNpc, "Thurgo");
			c.nextChat = 658;
			break;
		case 658:
			DialogueHandler.sendNpcChat2(c,
					"The only place I know where to get it is",
					"under the cliff here...", c.talkingNpc, "Thurgo");
			c.nextChat = 659;
			break;
		case 659:
			DialogueHandler.sendNpcChat1(c,
					"But it is guarded by a very powerful ice giant.",
					c.talkingNpc, "Thurgo");
			c.nextChat = 660;
			break;
		case 660:
			DialogueHandler.sendNpcChat3(c,
					"Most of the rocks in that cliff are pretty",
					"useless, and don't contain much of anything,",
					"but there's DEFINITELY some blurite in there.",
					c.talkingNpc, "Thurgo");
			c.nextChat = 661;
			break;
		case 661:
			DialogueHandler.sendNpcChat2(c,
					"You'll need a little bit of mining experience",
					"to be able to find it.", c.talkingNpc, "Thurgo");
			c.knightS += 1;
			c.nextChat = 0;
			break;

		// after getting the materials
		case 662:
			DialogueHandler.sendNpcChat1(c,
					"How are you doing finding the sword materials?",
					c.talkingNpc, "Thurgo");
			c.nextChat = 663;
			break;
		case 663:
			if (c.getItems().playerHasItem(2351, 2)
					&& c.getItems().playerHasItem(668, 1)) {
				DialogueHandler.sendPlayerChat1(c, "I have them right here");
				c.nextChat = 664;
			} else {
				DialogueHandler.sendPlayerChat1(c, "I'm still working on it.");
				c.nextChat = 0;
			}
			break;
		case 664:
			DialogueHandler.sendStatement(c,
					"You give the bluerite ore and two bars to Thurgo");
			c.getItems().deleteItem(2351, 1);
			c.getItems().deleteItem(2351, 1);
			c.getItems().deleteItem(668, 1);
			c.nextChat = 665;
			break;
		case 665:
			DialogueHandler.sendStatement(c, "Thurgo starts to make the sword");
			c.nextChat = 666;
			break;
		case 666:
			DialogueHandler.sendStatement(c, "Thurgo hands you the sword");
			c.knightS += 1;
			c.getItems().addItem(667, 1);
			c.nextChat = 667;
			break;
		case 667:
			DialogueHandler.sendPlayerChat1(c, "Thank you very much!");
			c.nextChat = 668;
			break;
		case 668:
			DialogueHandler.sendNpcChat1(c,
					"Just remember to call in with more pie some time!",
					c.talkingNpc, "Thurgo");
			c.nextChat = 0;
			break;

		// back to squire
		case 669:
			DialogueHandler.sendPlayerChat1(c,
					"I have retrieved your sword for you.");
			c.nextChat = 670;
			break;
		case 670:
			DialogueHandler.sendNpcChat2(c,
					"Thank you, thank you, thank you! I was",
					"seriously worried I would have to own up to Sir Vyvin!",
					c.talkingNpc, "Squire");
			c.nextChat = 671;
			break;
		case 671:
			DialogueHandler.sendStatement(c,
					"you give the sword to the squire.");
			c.getItems().deleteItem(667, 1);
			c.knightS += 1;
			c.nextChat = 672;
			break;
		case 672:
			KnightSword.questReward(c);
			break;
		}

	}
}
