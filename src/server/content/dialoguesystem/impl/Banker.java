package server.content.dialoguesystem.impl;

import server.content.BankPins;
import server.content.dialoguesystem.DialogueSystem;
import server.game.players.Client;

/**
 * This is the dialogue for bankers
 * @author somedude
 *
 */
public class Banker extends DialogueSystem {
    
    public static void handle(Client c, int dialogue) {
	switch(dialogue) {
	case 1:
	    sendNpcChat1(c, "What can I do for you?", DEFAULT);
	    setNextDialogue(c, 2);
	    break;
	case 2:
	    sendOption3(c, "I would like to access my bank account.",
		    "I would like to edit my Bank Pin settings.",
		    "Nevermind.");
	    setDialogueAction(c, 1);
	    break;
	
	case 3: // Option 1.
	    sendPlayerChat1(c,"I would like to access my bank account.", CALM);
	    setNextDialogue(c, 4);
	    break;
	case 4:// open bank
	    c.getPA().openUpBank();
	    setNextDialogue(c, 1000);
	    break;
	    
	case 5: // option 2
	    sendPlayerChat1(c,"I would like to edit my Bank Pin settings.", DEFAULT);
	    setNextDialogue(c, 6);
	    break;
	case 6:
	    sendNpcChat1(c, "What would you like to do?", DEFAULT);
	    setNextDialogue(c, 7);
	    break;
	case 7:
	    if(c.bankPin.length() <= 0 && !c.setPin) { // if doesn't ahve bank pin
	    sendOption2(c, "I would like to set a bank pin.", "Nevermind.");
	    setDialogueAction(c, 2);
	    } else {
	    sendOption2(c, "I would like to change my bank pin.", "I would like to delete my bank pin.");
	    setDialogueAction(c, 3);
	   }
	    break;    
	case 8: //action 2, option 1
	    sendPlayerChat1(c,"I would like to set a bank pin.", DEFAULT);
	    setNextDialogue(c, 9);
	    break;
	case 9:
	    BankPins.open(c);
	    setNextDialogue(c, 1000);
	    break;
	    
	case 10:// action 3, option 1
	    sendPlayerChat1(c, "I would like to change my bank pin.", DEFAULT);
	    setNextDialogue(c, 11);
	    break;   
	case 11:
	    BankPins.reset(c);
	    BankPins.open(c);
	    setNextDialogue(c, 1000);
	    break;
	    
	case 12: //action 3 option 2
	    sendPlayerChat1(c, "I would like to delete my pin.", ANNOYED);
	    setNextDialogue(c, 13);
	    break; 
	case 13:
	    BankPins.reset(c);
	    sendNpcChat1(c, "Your pin has been reseted. Anything else?", HAPPY);
	    setNextDialogue(c, 7);
	    break;
	    
	case 32:// first option 3 && option 2 for neverminds
	    //action 2 and 1
	    sendPlayerChat1(c,"Nevermind.", CALM);
	    setNextDialogue(c, 1000);
	    break;
	    
	
	case 1000:
	    resetChatDialogue(c);
	    break;
	}
    }

}
