package server.content.dialoguesystem.impl;

import server.content.dialoguesystem.DialogueSystem;
import server.game.players.Client;

/**
 * @author somedude-
 * Dragonslayer dialogue.
 */
public class DragonSlayer extends DialogueSystem {
	public static void handle(Client c, int dialogueId) {
		switch (dialogueId) {
		case 1:
			sendNpcChat1(c, "???",  DEFAULT);
			setNextDialogue(c, 2);
			break;
		case 1000: // resets dialogue.
			resetChatDialogue(c);
			break;	
		}
	}
}
