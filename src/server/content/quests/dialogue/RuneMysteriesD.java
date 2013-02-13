package server.content.quests.dialogue;

import server.content.quests.RuneMysteries;
import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * Made by someotherdude
 */
public class RuneMysteriesD {

	/**
	 * Rune Mysteries
	 * 
	 * @param c
	 * @param dialogue
	 * @param npcId
	 */
	public static void dialogue(Client c, int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		case 152:
			DialogueHandler.sendNpcChat1(c,
					"I thought you went to speak with the Wizard.",
					c.talkingNpc, "Duke Horacio");
			c.nextChat = 0;
			break;
		case 153:
			DialogueHandler.sendNpcChat1(c, "Greetings. Welcome to my castle.",
					c.talkingNpc, "Duke Horacio");
			c.nextChat = 154;
			break;
		case 154:
			DialogueHandler
					.sendPlayerChat1(c, "Do you have any quests for me?");
			c.nextChat = 155;
			break;
		case 155:
			DialogueHandler.sendNpcChat2(c,
					"Well, it's not really a quest but I recenty discovered",
					"This strange talsiman.", c.talkingNpc, "Duke Horacio");
			c.nextChat = 156;
			break;
		case 156:
			DialogueHandler.sendNpcChat2(c,
					"It seems to be mystical and I have never seen anything",
					"like it before. Would you take it to the head wizard at",
					c.talkingNpc, "Duke Horacio");
			c.nextChat = 157;
			break;
		case 157:
			DialogueHandler.sendNpcChat3(c,
					"The Wizards' Tower for me? It's just south-west of here",
					"and should not take you very long at all. I would be",
					"awfully grateful.", c.talkingNpc, "Duke Horacio");
			c.nextChat = 158;
			break;
		case 158:
			DialogueHandler.sendOption2(c, "Sure, No problem.",
					"Not right now.");
			c.dialogueAction = 159;
			break;
		case 160:
			DialogueHandler.sendPlayerChat1(c, "Sure, no problem.");
			c.nextChat = 162;
			break;
		case 161:
			DialogueHandler.sendPlayerChat1(c, "Not right now.");
			c.nextChat = 0;
			break;
		case 162:
			DialogueHandler.sendNpcChat2(c,
					"Thank you very much, stranger. I am sure the head",
					"Wizard will reward you for such an interesting find",
					c.talkingNpc, "Duke Horacio");
			c.nextChat = 163;
			break;
		case 163:
			DialogueHandler.sendStatement(c,
					"The Duke hands you an air talisman");
			c.runeM = 1;
			c.getItems().addItem(1438, 1);
			c.nextChat = 0;
			c.getPA().loadQuests();
			break;
		case 164:
			DialogueHandler.sendNpcChat2(c,
					"Welcome adventurer, to the world renowned",
					"Wizards' Tower. How many I help you?", c.talkingNpc,
					"Wizard Sedridor");
			c.nextChat = 165;
			break;
		case 165:
			DialogueHandler.sendOption3(c,
					"Nothing thanks, I'm just looking around.",
					"What are you doing down here?",
					"I'm looking for the head wizard.");
			c.dialogueAction = 160;
			break;
		case 166:
			DialogueHandler.sendPlayerChat1(c,
					"I'm looking for the head wizard.");
			c.nextChat = 167;
			break;
		case 167:
			DialogueHandler.sendNpcChat2(c, "Oh, you are, are you?",
					"And just why would you be doing that?", c.talkingNpc,
					"Sedridor");
			c.nextChat = 168;
			break;
		case 168:
			DialogueHandler.sendPlayerChat3(c,
					"The Duke of Lumbridge sent me to find him. I have",
					"this weird talisman he found. He said the head wizard",
					"would be very interested in it");
			c.nextChat = 169;
			break;
		case 169:
			DialogueHandler.sendNpcChat4(c, "Did he now? HmmmMMMMMmmmmm.",
					"Well that IS interesting. Hand it over then adventurer",
					"let me see what all the hubbub about it is.",
					"Just some amulet I'll wager.", c.talkingNpc, "Sedridor");
			c.nextChat = 170;
			break;
		case 170:
			DialogueHandler.sendOption2(c, "Ok, here you are.",
					"No, I'll only give it to the head wizard");
			c.dialogueAction = 170;
			break;
		case 171:
			DialogueHandler.sendPlayerChat1(c, "Ok, here you are.");
			c.nextChat = 172;
			break;
		case 172:
			DialogueHandler.sendStatement(c,
					"You hand the talisman to the wizard.");
			c.runeM = 2;
			c.getItems().deleteItem(1438, 1);
			c.nextChat = 173;
			c.getPA().loadQuests();
			break;
		case 173:
			DialogueHandler.sendNpcChat1(c, "Wow! This is... incredible!",
					c.talkingNpc, "Sedridor");
			c.nextChat = 174;
			break;
		case 174:
			DialogueHandler.sendNpcChat3(c,
					"Th-this talisman you brought me...! it is the last piece",
					"of the puzzle. I think! Finally! The legacy of our",
					"Ancestors... it will return to us once more!",
					c.talkingNpc, "Sedridor");
			c.nextChat = 175;
			break;
		case 175:
			DialogueHandler.sendNpcChat3(c, "I need time to study this, "
					+ c.playerName + ". Can you please ",
					"do me this task while I study the talisman you have",
					"given me? In the mighty town of Varrock, which",
					c.talkingNpc, "Sedridor");
			c.nextChat = 176;
			break;
		case 176:
			DialogueHandler
					.sendNpcChat3(
							c,
							"is located North East of here, there is a certain shop",
							"that sells magical runes. I have in this package all of the",
							"research I have done relating ro the Rune Stones, and",
							c.talkingNpc, "Sedridor");
			c.nextChat = 177;
			break;
		case 177:
			DialogueHandler.sendNpcChat3(c,
					"require somebody to take them to the shopkeeper so that",
					"he may share my research and offer me his insights.",
					"Do this thing for me, and bring back what he gives you,",
					c.talkingNpc, "Sedridor");
			c.nextChat = 178;
			break;
		case 178:
			DialogueHandler
					.sendNpcChat3(
							c,
							"and if my suspicions are correct, I will let you into the",
							"knowledge of one of the greatest secrest this world has",
							"ever known! A secret so powerful that it destroyed the",
							c.talkingNpc, "Sedridor");
			c.nextChat = 179;
			break;
		case 179:
			DialogueHandler.sendNpcChat4(c,
					"original Wizards' Tower all of those centuries",
					"ago! My research, combined with this mysterious",
					"talisman... I cannot believe the answer to",
					"the mysteries is so close now!", c.talkingNpc, "Sedridor");
			c.nextChat = 180;
			break;
		case 180:
			DialogueHandler.sendNpcChat2(c, "Do this thing for me "
					+ c.playerName + ". Be rewarded in a",
					"way you can never imagine", c.talkingNpc, "Sedridor");
			c.nextChat = 181;
			break;
		case 181:
			DialogueHandler.sendOption2(c, "Yes, certainly.", "No, I'm busy.");
			c.dialogueAction = 181;
			break;
		case 182:
			DialogueHandler.sendPlayerChat1(c, "Yes, certainly.");
			c.nextChat = 183;
			break;
		case 183:
			DialogueHandler.sendNpcChat4(c,
					"Take this package, and head directly North",
					"from here, through Draynor village, until you reach",
					"the Barbarian Village. Then head East from there",
					"until you reach Varrock", c.talkingNpc, "Sedridor");
			c.nextChat = 184;
			break;
		case 184:
			DialogueHandler.sendNpcChat2(c,
					"Once in Varrock, take this package to the owner of the",
					"rune shop. His name is Aubury.", c.talkingNpc, "Sedridor");
			c.nextChat = 185;
			break;
		case 185:
			DialogueHandler.sendStatement(c,
					"The head wizard gives you a package.");
			c.getItems().addItem(290, 1);
			c.runeM = 3;
			c.nextChat = 186;
			c.getPA().loadQuests();
			break;
		case 186:
			DialogueHandler.sendNpcChat1(c, "Best of luck with your quest, "
					+ c.playerName + "!", c.talkingNpc, "Sedridor");
			c.nextChat = 0;
			break;

		case 360:
			DialogueHandler.sendNpcChat1(c, "Do you want to buy some runes?",
					c.talkingNpc, "Aubury");
			c.nextChat = 361;
			break;
		case 361:
			if (c.runeM == 3) {
				DialogueHandler.sendOption3(c, "Yes please!",
						"Oh, it's a rune shop. No thank you, then",
						"I have been sent here with a package for you.");
				c.dialogueAction = 361;
			} else {
				DialogueHandler.sendOption2(c, "Yes please!",
						"Oh, it's a rune shop. No thank you, then.");
				c.dialogueAction = 361;
			}
			break;
		case 362:
			DialogueHandler.sendPlayerChat2(c,
					"I have been sent here with a package for you. It's from",
					"the head wizard at the Wizards' Tower.");
			c.nextChat = 365;
			break;
		case 363:
			DialogueHandler.sendPlayerChat1(c, "Yes please!");
			c.nextChat = 0;
			break;
		case 364:
			DialogueHandler.sendPlayerChat1(c,
					"Oh, it's a rune shop. No thank you, then.");
			c.nextChat = 0;
			break;
		case 365:
			DialogueHandler.sendNpcChat3(c,
					"Really? But... surely he can't have...? Please, let me",
					"have it, it must be extremely important for him to have",
					"sent a stranger.", c.talkingNpc, "Aubury");
			c.nextChat = 366;
			break;
		case 366:
			DialogueHandler.sendStatement(c,
					"You hand Aubury the research package.");
			c.runeM = 4;
			c.getItems().deleteItem(290, 1);
			c.nextChat = 367;
			c.getPA().loadQuests();
			break;
		case 367:
			DialogueHandler
					.sendNpcChat2(
							c,
							"This... this is incredible. Please, give me a few moments",
							"to quickly look over this, and then talk to me again.",
							c.talkingNpc, "Aubury");
			c.runeM = 5;
			c.nextChat = 0;
			break;
		case 368:
			DialogueHandler.sendNpcChat2(c,
					"This is truly incredible! Please take my notes to",
					"the head wizard.", c.talkingNpc, "Aubury");
			c.nextChat = 369;
			break;
		case 369:
			DialogueHandler.sendStatement(c, "Aubury hands you his notes.");
			c.getItems().addItem(291, 1);
			c.runeM = 6;
			c.nextChat = 0;
			c.getPA().loadQuests();
			break;
		case 371:
			DialogueHandler.sendNpcChat2(c, "Ah, " + c.playerName
					+ ". How goes your quest? Have you",
					"delivered the research notes to my friend Aubury yet?",
					c.talkingNpc, "Sedridor");
			c.nextChat = 372;
			break;
		case 372:
			DialogueHandler.sendPlayerChat2(c,
					"Yes, I have. He gave me some research notes",
					"to pass on to you.");
			c.nextChat = 373;
			break;
		case 373:
			DialogueHandler.sendNpcChat1(c, "May I have his notes then?",
					c.talkingNpc, "Sedridor");
			c.nextChat = 374;
			break;
		case 374:
			DialogueHandler.sendPlayerChat1(c, "Sure. I have them here.");
			c.nextChat = 375;
			break;
		case 375:
			DialogueHandler.sendNpcChat4(c,
					"Well, before you hand them over to me, as you",
					"have been nothing but trustful with me to this point",
					"and I admire that in an adventurer. I will let you",
					"into the secret of our research.", c.talkingNpc,
					"Sedridor");
			c.nextChat = 376;
			break;
		case 376:
			DialogueHandler.sendNpcChat4(c,
					"Now as you may or may not know, many",
					"centuries ago, the wizards at this tower",
					"learnt the secret of creating Rune Stones, which",
					"allowed us to cast Magic very easily.", c.talkingNpc,
					"Sedridor");
			c.nextChat = 377;
			break;
		case 377:
			DialogueHandler.sendNpcChat4(c,
					"When this tower was burnt down the secret of",
					"creating runes was lost to us for all time.. except it",
					"wasn't. Some months ago, while searching these ruins",
					"for information from the old days.", c.talkingNpc,
					"Sedridor");
			c.nextChat = 378;
			break;
		case 378:
			DialogueHandler.sendNpcChat3(c,
					"I came upon a scroll, almost destroyed, that detailed a",
					"magical rock deep in the icefields of the North, closed",
					"off from access by anything other than magical means",
					c.talkingNpc, "Sedridor");
			c.nextChat = 379;
			break;
		case 379:
			DialogueHandler.sendNpcChat4(c,
					"This rock was called the 'Rune Essence' by the",
					"magicians who studied its powers. Apparently, by simply",
					"breaking a chunk from it, a Rune Stone could be",
					"fashiong very quickly and easily at certain",
					c.talkingNpc, "Sedridor");
			c.nextChat = 380;
			break;
		case 380:
			DialogueHandler.sendNpcChat4(c,
					"elemental altars that were scattered across the land",
					"back then. Now this is an interesting little piece of",
					"history, but not much to use as modern wizards",
					"without access to the Rune Essence.", c.talkingNpc,
					"Sedridor");
			c.nextChat = 381;
			break;
		case 381:
			DialogueHandler.sendNpcChat4(c,
					"or these elemental altars. This is where you and",
					"Aubury come into this story. A few weeks back,",
					"Aubury discovered in a standard delivery of runes",
					"to his store, a parchment detailing a", c.talkingNpc,
					"Sedridor");
			c.nextChat = 382;
			break;
		case 382:
			DialogueHandler.sendNpcChat4(c,
					"teleportation spell that he had never come across",
					"before. To his shock, when cast it took him to a",
					"strange rock he had never encountered before...",
					"yet that felt strangely familiar...", c.talkingNpc,
					"Sedridor");
			c.nextChat = 383;
			break;
		case 383:
			DialogueHandler
					.sendNpcChat3(
							c,
							"As I'm sure you have now guessed, he had discovered a",
							"portal leading to the mythical Rune Essence. As soon as",
							"he told me of this spell, I saw the important of his find.",
							c.talkingNpc, "Sedridor");
			c.nextChat = 384;
			break;
		case 384:
			DialogueHandler.sendNpcChat4(c,
					"for if we could but find the elemental alters spoken",
					"of in the ancient texts, we would once more be able",
					"to create runes as our ancestors had done! It would",
					"be the savior of the wizards' art!", c.talkingNpc,
					"Sedridor");
			c.nextChat = 385;
			break;
		case 385:
			DialogueHandler.sendPlayerChat2(c,
					"I'm still not sure how I fit into",
					"this little story of yours...");
			c.nextChat = 386;
			break;
		case 386:
			DialogueHandler.sendNpcChat3(c,
					"You haven't guessed?? This talisman you brought me..",
					"it is the key to the elemental alter of air! When",
					"you hold it next, it will direct you towards",
					c.talkingNpc, "Sedridor");
			c.nextChat = 387;
			break;
		case 387:
			DialogueHandler.sendNpcChat3(c,
					"the entrance to the long forgotten Air Altar! By",
					"bring pieces of the Rune Essence to the Air Temple",
					"you will be able to fashion your own Air Runes!",
					c.talkingNpc, "Sedridor");
			c.nextChat = 388;
			break;
		case 388:
			DialogueHandler.sendNpcChat4(c, "And this is not all!",
					"By finding other talismans similar",
					"to this one, you will eventually be able to craft every",
					"rune that is available on this world! just", c.talkingNpc,
					"Sedridor");
			c.nextChat = 389;
			break;
		case 389:
			DialogueHandler.sendNpcChat4(c, "as our ancestors did!",
					"I cannot stress enough what a",
					"find this is! Now, due to the risks involved of letting",
					"this mighty power fall into the wrong hands",
					c.talkingNpc, "Sedridor");
			c.nextChat = 390;
			break;
		case 390:
			DialogueHandler
					.sendNpcChat4(
							c,
							"I will keep the teleport skill to the Rune Essence",
							"a closely guarded secret, s hared only by myself",
							"and those magic users around the world",
							"whom I trust enough to keep it.", c.talkingNpc,
							"Sedridor");
			c.nextChat = 391;
			break;
		case 391:
			DialogueHandler
					.sendNpcChat4(
							c,
							"This means that if any evil power should discover",
							"the talismans required to enter the elemental",
							"temples, we will be able to prevent their access",
							"to the Rune Essence and prevent", c.talkingNpc,
							"Sedridor");
			c.nextChat = 392;
			break;
		case 392:
			DialogueHandler.sendNpcChat3(c,
					"tragedy befalling this world. I know not where the",
					"temples are located, nor do I know where the talismans",
					"have been scattered to in this land, but I now",
					c.talkingNpc, "Sedridor");
			c.nextChat = 393;
			break;
		case 393:
			DialogueHandler.sendNpcChat3(c,
					"return your Air Talisman to you. Find the Air",
					"Temple, and you will be able to charge your Rune",
					"Essences to become Air Runes at will. Any time",
					c.talkingNpc, "Sedridor");
			c.nextChat = 394;
			break;
		case 394:
			DialogueHandler.sendNpcChat3(c,
					"you wish to visit the Rune Essence, speak to me",
					"or Aubury and we will open a portal to that",
					"Mystical place for you to visit.", c.talkingNpc,
					"Sedridor");
			c.nextChat = 395;
			break;
		case 395:
			DialogueHandler.sendPlayerChat2(c,
					"So only you and Aubury know the teleport",
					"spell to the Rune Essence?");
			c.nextChat = 396;
			break;
		case 396:
			DialogueHandler.sendNpcChat4(c,
					"No... there are others, whom I will tell of your",
					"authorization to visit that place. When you speak",
					"to them, they will know and grant you",
					"access to that place when asked.", c.talkingNpc,
					"Sedridor");
			c.nextChat = 397;
			break;
		case 397:
			DialogueHandler.sendNpcChat4(c,
					"Use the Air Talisman to locate the Air Temple",
					"and use any further talismans you find to locate",
					"the other missing elemental temples.",
					"Now... My research notes please?", c.talkingNpc,
					"Sedridor");
			c.nextChat = 398;
			break;
		case 398:
			DialogueHandler.sendStatement2(c,
					"You hand the head wizard the research notes.",
					"He hands you back the Air Talisman");
			c.getItems().deleteItem(291, 1);
			c.getItems().addItem(1438, 1);
			c.nextChat = 399;
			break;
		case 399:
			c.runeM = 7;
			c.getPA().loadQuests();
			c.nextChat = 0;
			RuneMysteries.mysteriesReward(c);
			break;

		}

	}
}
