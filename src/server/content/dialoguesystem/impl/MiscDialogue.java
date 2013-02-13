package server.content.dialoguesystem.impl;

import server.content.dialoguesystem.DialogueSystem;
import server.game.items.ItemAssistant;
import server.game.players.Client;

/**
 * @author somedude This class is like another DialogueHandler class.
 *         Handles every dialoge that doesn't need to be in a seperate class. Do
 *         not use this class too much. It's way too messy if you stuff
 *         everything in one class.
 */
public class MiscDialogue extends DialogueSystem {
	
	/**
	 * Handles the dialogue. Put only very short dialogue here.
	 * @param c
	 * @param dialogueId
	 */
	public static void handle(Client c, int dialogueId) {
		switch (dialogueId) {
		// kebabs
		case 1:
			sendNpcChat1(c,
					"Would you like to buy a nice kebab? Only one gold.",
					DEFAULT);
			setNextDialogue(c,2);
			break;
		case 2:
			sendOption2(c, "I think I'll give it a miss.", "Yes please.");
			setDialogueAction(c, 1);
			break;
		case 3:// Yes please
			sendPlayerChat1(c, "Yes please.", HAPPY);
			if (c.getItems().playerHasItem(995, 1))
				setNextDialogue(c,4);
			else
				setNextDialogue(c,5);
			break;
		case 6:// no thanks
			sendPlayerChat1(c, "I think I'll give it a miss.", DISINTERESTED);
			setNextDialogue(c,1000);
			break;
		case 4:
			if (c.getItems().freeSlots() <= 0) {
				c.sendMessage(ItemAssistant.noSpace());
				c.getPA().closeAllWindows();
				resetChatDialogue(c);
				break;
			}
			c.getItems().addItem(1971, 1);
			c.getItems().deleteItem(995, 1);
			c.sendMessage("You buy a kebab.");
			c.getPA().closeAllWindows();
			resetChatDialogue(c);
			break;
		case 5:// no money
			sendNpcChat1(c, "You don't have enough coins.", DISTRESSED);
			setNextDialogue(c,1000);
			break;
		// ///////////KEBABS END/////////////

		case 7:// Hans dialogue
			sendNpcChat1(c, "Hello. What are you doing here?", DEFAULT);
			setNextDialogue(c,8);
			break;
		case 8:
			sendOption3(c,
					"I'm looking for whoever is in charge. of this place.",
					"I have come to kill everyone in this castle!",
					"I don't know. I'm lost. Where am I?");
			setDialogueAction(c, 1);
			break;
		case 9:// option 1
			sendNpcChat1(c,
					"Who, the duke? He's in his study, on the first floor",
					CALM);
			setNextDialogue(c,8);
			break;
		case 10:// option 2
			sendNpcChat1(c, "Help! Help!", NEARLY_CRYING);
			setNextDialogue(c,1000);
			break;
		case 11:// option 3
			sendNpcChat1(c, "You are in Lumbridge Castle.", DEFAULT);
			setNextDialogue(c,8);
			break;
		// hans end

		case 12: // lumbridge sage
			sendNpcChat1(c, "Greetings, adventurer. How may I help you?", HAPPY);
			setNextDialogue(c,13);
			break;
		case 13:
			sendOption3(c, "Who are you?",
					"Tell me about the town of Lumbridge.",
					"I'm fine for now, thanks.");
			setDialogueAction(c, 1);
			break;
		case 14:// option 1
			sendPlayerChat1(c, "Who are you?", DISINTERESTED);
			setNextDialogue(c,15);
			break;
		case 15:// option 1 cont.
			sendNpcChat4(c,
					"I am Phileas, the Lumbridge Guide. In times past, people",
					"came from all around to ask me for advice. My renown",
					"seems to have diminished somewhat in recent years",
					"though. Can I help you with anything", CALM);
			setNextDialogue(c,13);
			break;
		case 16:// option 2
			sendPlayerChat1(c, "Tell me about the town of Lumbridge",
					DISINTERESTED);
			setNextDialogue(c,17);
			break;
		case 17:// option 2 cont
			sendNpcChat4(
					c,
					"Lumbridge is one of the older towns in the human-",
					"controlled kingdoms. It was founded over two hundred",
					"years ago toward the end of the fourth age. It's called",
					"Lumbridge because of this bridge built over the River Lum.",
					HAPPY);
			setNextDialogue(c,18);
			break;
		case 18:// option 2 cont
			sendNpcChat2(c,
					"The town is governed by Duke Horacio, who is a good",
					"friend of our monarch, King Roald of Misthalin.", DEFAULT);
			setNextDialogue(c,19);
			break;
		case 19:
			sendPlayerChat1(c, "Thank you, goodbye.", ANNOYED);
			setNextDialogue(c,1000);
			break;
		case 20:// option 3
			sendPlayerChat1(c, "I'm fine for now, thanks.", DEFAULT);
			setNextDialogue(c,1000);
			break;

		case 1000:
			resetChatDialogue(c);
			break;
		}
	}

}
