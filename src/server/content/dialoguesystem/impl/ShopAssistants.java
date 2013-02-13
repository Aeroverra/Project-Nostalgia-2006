package server.content.dialoguesystem.impl;

import server.content.dialoguesystem.DialogueSystem;
import server.game.players.Client;

/**
 * @author somedude. An example of class dialogues
 *
 */								//always extends dialoguesystem	
public class ShopAssistants extends DialogueSystem {
	/**
	 * Handles the dialogue.
	 * @param c
	 * @param dialogueId
	 */
	public static void handle(Client c, int dialogueId) {
		switch (dialogueId) {
		case 1:
			sendNpcChat2(c,
					"Hello and welcome to the general store.",
					"How may I help you?",
					HAPPY);
			setNextDialogue(c, 2);
			break;
		case 2:
			resetChatDialogue(c);
			break;
		}
	}
}
