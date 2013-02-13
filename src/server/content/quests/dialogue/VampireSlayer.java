package server.content.quests.dialogue;

import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * somedude
 * 
 * @author Administrator
 * 
 */
public class VampireSlayer {
	Client c;

	public VampireSlayer(Client c) {
		this.c = c;
	}

	public boolean spawned;

	public void sendDialogue(int dialogue, int npcid) {
		c.talkingNpc = npcid;
		switch (dialogue) {
		case 722:// start
			DialogueHandler.sendNpcChat1(c, "Please help us, bold adventurer!",
					c.talkingNpc, "Morgan");
			c.nextChat = 723;
			break;
		case 723:
			DialogueHandler.sendPlayerChat1(c, "What's the problem?");
			c.nextChat = 724;
			break;
		case 724:
			DialogueHandler.sendNpcChat4(c,
					"Our little village has been dreafully ravaged by an evil",
					"vampire! He lives in the basement of the manor to the",
					"north, we need someone to get rid of him once and for",
					"all!", c.talkingNpc, "Morgan");
			c.nextChat = 725;
			break;
		case 725: // options
			DialogueHandler.sendOption3(c, "No, vampires are scary!",
					"Ok. I'm up for an adventure.",
					"Have you got any tips on killing the vampire?");
			c.dialogueAction = 725;
			break;
		case 726:
			DialogueHandler.sendPlayerChat1(c, "Ok. I'm up for an adventure.");
			c.nextChat = 727;
			break;
		case 727:
			DialogueHandler.sendNpcChat4(c,
					"I think first you should seek help. I have a friend who",
					"is a retired vampire hunter, his name is Dr. Harlow. He",
					"may be able to give you some tips. He can normally be",
					"found in the Blue Moon Inn in Varrock, he's a bit of",
					c.talkingNpc, "Morgan");
			c.nextChat = 728;
			break;
		case 728:
			DialogueHandler.sendNpcChat2(c,
					"an old soak these days. Mention his old friend Morgan,",
					"I'm sure he wouldn't want me killed by a vampire.",
					c.talkingNpc, "Morgan");
			c.nextChat = 729;
			break;
		case 729:
			DialogueHandler.sendPlayerChat1(c, "I'll look him up then.");
			c.vampireslay = 1; // 1
			c.getPA().loadQuests();
			c.nextChat = 0;
			break;
		case 7298:
			DialogueHandler.sendNpcChat1(c, "I know you can do it!",
					c.talkingNpc, "Morgan");
			c.nextChat = 0;
			break;

		// Dr harlow dialogue
		case 730: // first
			DialogueHandler.sendNpcChat1(c, "Buy me a drrink pleassh...",
					c.talkingNpc, "Dr Harlow");
			c.nextChat = 731;
			break;
		case 731:// second options
			DialogueHandler.sendOption2(c, "No, you've had enough.",
					"Morgan needs your help!");
			c.dialogueAction = 726;

			break;
		case 732:
			DialogueHandler.sendPlayerChat1(c, "Morgan needs your help!");
			c.nextChat = 733;
			break;
		case 733:
			DialogueHandler.sendNpcChat1(c, "Morgan you shhay...?",
					c.talkingNpc, "Dr Harlow");
			c.nextChat = 734;
			break;

		case 734:
			DialogueHandler.sendPlayerChat2(c,
					"His village is being terrorised by a vampire! He told me",
					"to ask you about how I can stop it.");
			c.nextChat = 735;
			break;
		case 735:
			DialogueHandler.sendNpcChat2(c,
					"Buy me a beer... then I'll teash you what you need to",
					"know...", c.talkingNpc, "Dr Harlow");
			c.nextChat = 736;
			break;
		case 736:
			DialogueHandler.sendPlayerChat1(c,
					"But this is your friend Morgan!");
			c.nextChat = 737;
			break;
		case 737:
			DialogueHandler.sendNpcChat1(c, "Buy ush a beer anyway...",
					c.talkingNpc, "Dr Harlow");
			c.vampireslay = 2; // 2
			c.getPA().loadQuests();
			c.nextChat = 0;
			break;

		// bartender stuff

		case 738:
			DialogueHandler.sendNpcChat1(c, "2 coins for a fine glass of ale.",
					c.talkingNpc, "Bartender");
			c.nextChat = 739;
			break;
		case 739:
			if (c.getItems().playerHasItem(995, 2)) {
				c.sendMessage("You buy a pint of beer.");
				c.getItems().addItem(1917, 1); // Beer
				c.getItems().deleteItem(995, 2);
			} else {
				c.sendMessage("You don't have enough coins.");
			}
			c.nextChat = 0;
			c.getPA().closeAllWindows();
			break;

		// doctor halor 2
		case 740: // first
			DialogueHandler.sendNpcChat1(c, "Buy me a drrink pleassh...",
					c.talkingNpc, "Dr Harlow");
			c.nextChat = 741;
			break;
		case 741:
			if (c.getItems().playerHasItem(1917)) {
				DialogueHandler.sendItemChat1(c, "",
						"You give a beer to Dr Harlow.", 1917, 150);
				c.getItems().deleteItem(1917, 1);
				c.nextChat = 742;
				c.vampireslay = 3; // 3
				c.getPA().loadQuests();
			} else {
				DialogueHandler.sendPlayerChat1(c,
						"Wait, let me buy one for you.");
				c.nextChat = 0;
			}
			break;
		case 742:
			DialogueHandler.sendPlayerChat1(c,
					"So, tell me how to kill vampires then.");
			c.nextChat = 743;
			break;
		case 743:
			DialogueHandler.sendNpcChat2(c,
					"Yesh Yesh vampires. I was very good at",
					"killing em once...", c.talkingNpc, "Dr Harlow");
			c.nextChat = 744;
			break;
		case 744:
			DialogueHandler.sendStatement(c,
					"Dr Harlow appears to sober up slightly.");
			c.nextChat = 745;
			break;
		case 745:
			DialogueHandler
					.sendNpcChat3(
							c,
							"Well you're gonna to need a stake, otherwise he'll just",
							"regenerate. Yes, you must have a stake to finish it off...",
							"I just happen to have one with me.", c.talkingNpc,
							"Dr Harlow");
			c.nextChat = 746;
			break;
		case 746:
			DialogueHandler.sendItemChat1(c, "",
					"Dr Harlow hands you a stake.", 1549, 250);
			c.getItems().addItem(1549, 1);
			c.nextChat = 747;
			break;
		case 747:
			DialogueHandler.sendNpcChat3(c,
					"You also need a hammer to drive it in properly.",
					"It's wise to carry garlic. They get weak to the smell.",
					"You should find some in Morgan's cupboard.", c.talkingNpc,
					"Dr Harlow");
			c.nextChat = 748;
			break;
		case 748:
			DialogueHandler.sendPlayerChat1(c, "Thank you very much!");
			c.vampireslay = 4;// 4
			c.getPA().loadQuests();
			c.nextChat = 0;
			break;
		}
	}

	public void sendButtons(int actionbutton) {
		switch (actionbutton) {
		case 9158:
			switch (c.dialogueAction) {

			case 726: // dialogue option 2
				sendDialogue(732, 756);
				break;

			}
			break;

		case 9168:
			switch (c.dialogueAction) {
			case 725:
				sendDialogue(726, 755); // sends ok i'm up
				break;
			}
			break;
		}
	}

	public void showInformation() {
		for (int i = 8144; i < 8195; i++) {
			c.getPA().sendFrame126("", i);
		}
		c.getPA().sendFrame126("@dre@Vampire Slayer", 8144);
		c.getPA().sendFrame126("", 8145);
		if (c.vampireslay == 0) {
			c.getPA().sendFrame126("Vampire Slayer", 8144);
			c.getPA().sendFrame126(
					"I can start this quest by speaking to Morgan in", 8147);
			c.getPA().sendFrame126("Draynor Village.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("The minimum requirements is", 8150);
			c.getPA().sendFrame126(
					"@blu@The ability to defeat a level 37 enemy.", 8151);
		} else if (c.vampireslay == 1) {
			c.getPA().sendFrame126("Vampire Slayer", 8144);
			c.getPA().sendFrame126(
					"@str@I can start this quest by speaking to Morgan in",
					8147);
			c.getPA().sendFrame126("@str@Draynor Village.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126(
					"I have spoken to Morgan and he has explained to me", 8150);
			c.getPA().sendFrame126(
					"about this vampire terrorising his village ", 8151);
			c.getPA().sendFrame126(
					"He told me to go visit Dr Harlow in the Blue Moon Inn ",
					8152);
		} else if (c.vampireslay == 2 || c.vampireslay == 3) {
			c.getPA().sendFrame126("Vampire Slayer", 8144);
			c.getPA().sendFrame126(
					"@str@I can start this quest by speaking to Morgan in",
					8147);
			c.getPA().sendFrame126("@str@Draynor Village.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126(
					"@str@I have spoken to Morgan and he has explained to me",
					8150);
			c.getPA().sendFrame126(
					"@str@about this vampire terrorising his village ", 8151);
			c.getPA()
					.sendFrame126(
							"@str@He told me to go visit Dr Harlow in the Blue Moon Inn ",
							8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA().sendFrame126(
					"I spoke with Dr Harlow and he asked me to buy him a beer",
					8154);
			c.getPA().sendFrame126(
					"before he could tell me how to slay the vampire.", 8155);
		} else if (c.vampireslay == 4) {
			c.getPA().sendFrame126("Vampire Slayer", 8144);
			c.getPA().sendFrame126(
					"@str@I can start this quest by speaking to Morgan in",
					8147);
			c.getPA().sendFrame126("@str@Draynor Village.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126(
					"@str@I have spoken to Morgan and he has explained to me",
					8150);
			c.getPA().sendFrame126(
					"@str@about this vampire terrorising his village ", 8151);
			c.getPA()
					.sendFrame126(
							"@str@He told me to go visit Dr Harlow in the Blue Moon Inn ",
							8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA()
					.sendFrame126(
							"@str@I spoke with Dr Harlow and he asked me to buy him a beer",
							8154);
			c.getPA().sendFrame126(
					"@str@before he could tell me how to slay the vampire.",
					8155);
			c.getPA().sendFrame126("", 8156);
			c.getPA().sendFrame126("He seemed to sober up and told me that",
					8157);
			c.getPA()
					.sendFrame126(
							"inorder to kill the vampire, I needed a stake and a hammer.",
							8158);
			c.getPA().sendFrame126(
					"He also told me vampires hate the smell of garlic.", 8159);
		} else if (c.vampireslay == 5) {
			c.getPA().sendFrame126("Vampire Slayer", 8144);
			c.getPA().sendFrame126(
					"@str@I can start this quest by speaking to Morgan in",
					8147);
			c.getPA().sendFrame126("@str@Draynor Village.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126(
					"@str@I have spoken to Morgan and he has explained to me",
					8150);
			c.getPA().sendFrame126(
					"@str@about this vampire terrorising his village ", 8151);
			c.getPA()
					.sendFrame126(
							"@str@He told me to go visit Dr Harlow in the Blue Moon Inn ",
							8152);
			c.getPA().sendFrame126("", 8153);
			c.getPA()
					.sendFrame126(
							"@str@I spoke with Dr Harlow and he asked me to buy him a beer",
							8154);
			c.getPA().sendFrame126(
					"@str@before he could tell me how to slay the vampire.",
					8155);
			c.getPA().sendFrame126("", 8156);
			c.getPA().sendFrame126(
					"@str@He seemed to sober up and told me that", 8157);
			c.getPA()
					.sendFrame126(
							"@str@inorder to kill the vampire, I needed a stake and a hammer.",
							8158);
			c.getPA().sendFrame126(
					"@str@He also told me vampires hate the smell of garlic.",
					8159);
			c.getPA().sendFrame126(
					"Congratulations! You have completed this quest!", 8160);
		}
		c.getPA().showInterface(8134);
	}

}
