package server.content.dialoguesystem.impl;

import server.content.dialoguesystem.DialogueSystem;
import server.game.players.Client;

/**
 * @author somedude Default dialogue for every npc
 * 
 */
public class DefaultDialogue extends DialogueSystem {

	public static void handle(Client c, int dialogueId) {
		switch (dialogueId) {
		case 1:
			sendNpcChat1(c, "sdsdsd",  DEFAULT);
			setNextDialogue(c, 2);
			break;
		case 2:
			sendNpcChat1(c, "sfsfsf", LAUGHING);
			setNextDialogue(c, 3);
			break;
		case 3:
			sendNpcChat1(c, "dt", EVIL);
			setNextDialogue(c, 4);
			break;
		case 4:
			sendNpcChat1(c, "d", MOURNING);
			setNextDialogue(c, 5);
			break;
		case 5:
			sendPlayerChat2(c, "a", "as well",
					SLEEPY);
			setNextDialogue(c, 6);
			break;
		case 6:
			sendOption2(c, "Faggy", "poop");
			setDialogueAction(c, 1);
			break;
		case 7:
			sendPlayerChat1(c, "faggy", LAUGHING);	
			setNextDialogue(c, 8);
			break;
		case 8: // options2 = 1
			sendNpcChat1(c, "faggy", DISINTERESTED);
			setNextDialogue(c, 9);
			break;
		case 10: // options2 = 2
			sendNpcChat1(c, "poop.", LAUGH2);
			setNextDialogue(c, 9);
			break;
		case 9:
			resetChatDialogue(c);
			break;	
		}
	}
}
