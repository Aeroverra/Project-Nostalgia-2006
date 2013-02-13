package server.content.dialoguesystem;

import server.Server;
import server.content.dialoguesystem.impl.Banker;
import server.content.dialoguesystem.impl.DefaultDialogue;
import server.content.dialoguesystem.impl.MiscDialogue;
import server.content.dialoguesystem.impl.ShopAssistants;
import server.game.players.Client;
import core.util.Misc;

/**
 * @author somedude. New Dialogue system
 * Don't use the MiscDialouge as much. Create new classes for dialogue.
 * 
 */
public class DialogueSystem {

	public static final int DEFAULT = 591, EVIL = 592, SAD = 596, SLEEPY = 603,
			LAUGHING = 605, MOURNING = 610, MAD = 614, DISINTERESTED = 602,
			SADISH = 612, NEARLY_CRYING = 613, LAUGH2 = 606, LAUGH3 = 607,
			LAUGH4 = 608, EVIL_LAUGH = 609, EVIL2 = 604, ANGRY = 614,
			ANGRY2 = 615, ANGRY3 = 616, ANGRY4 = 617, DRUNK1 = 600,
			DRUNK2 = 601, HAPPY = 588, CALM = 590, EVILCONT = 593,
			HAPPYEVIL = 594, ANNOYED = 595, DISTRESSED = 596, BOWHEAD = 598,
			MORESAD = 611;
	
	// Actionbutton ids on options interface. In order.
	public static int[] options2 = { -1, 9157, 9158 };
	public static int[] options3 = { -1, 9167, 9168, 9169 };
	public static int[] options4 = { -1, 9178, 9179, 9180, 9181 };

	/**
	 * Sends the dialogue based on npc
	 * 
	 * @param dialogueId
	 *            - dialogue id
	 * @param npcId
	 *            - Npc ID
	 */
	public static void sendDialogue(Client c, int dialogueId, int npcId) {
	    String npc = GetNpcName(npcId).toLowerCase();
	    switch (npcId) {
		case 0://hans
		case 543://karim
		case 2244://sage
			MiscDialogue.handle(c, dialogueId);
			break;
		case 551://shop keeprs
		case 520:
		case 521:
		case 550:
		case 595:
		case 561:
		case 531:
		case 530:
			ShopAssistants.handle(c, dialogueId);
			break;
		default:
		    if(npc.equalsIgnoreCase("banker")) {
			Banker.handle(c, dialogueId);
			return;
		    }
			DefaultDialogue.handle(c, dialogueId);
			break;
		}
	}

	/**
	 * Handles the options in the dialogue
	 * 
	 * @param c
	 * @param buttons
	 *            - Option button ids
	 * @param npcId
	 *            - Npc Id of the action
	 */
	public static void handleOptions(Client c, int buttons, int npcId) {
	    String npc = GetNpcName(npcId).toLowerCase();
	    if(npc.equalsIgnoreCase("banker")) {
		switch (getDialogueAction(c)) {
		case 1:
			if (buttons == options3[1])
				Banker.handle(c, 3);
			else if(buttons == options3[2])
				Banker.handle(c, 5);
			else if(buttons == options3[3])
				Banker.handle(c, 32);
			break;
		case 2://options for changing bank pin
		    if (buttons == options2[1])
			Banker.handle(c, 8);
		else if(buttons == options2[2])
			Banker.handle(c, 32);
		    break;
		case 3://options for 3rd action
		    if (buttons == options2[1])
			Banker.handle(c, 10);
		else if(buttons == options2[2])
			Banker.handle(c, 12);
		}
		return;
	    }
	    switch (npcId) {
		case 2244://lumby sage
			switch (getDialogueAction(c)) {
			case 1:
				if (buttons == options3[1])
					MiscDialogue.handle(c, 14);
				else if(buttons == options3[2])
					MiscDialogue.handle(c, 16);
				else if(buttons == options3[3])
					MiscDialogue.handle(c, 20);
				break;
			}
			break;
		case 0://Hans
			switch (getDialogueAction(c)) {
			case 1:
				if (buttons == options3[1]) //optin 1
					MiscDialogue.handle(c, 9);
				else if(buttons == options3[2])//option 2
					MiscDialogue.handle(c, 10);
				else if(buttons == options3[3])//option 3
					MiscDialogue.handle(c, 11);
				break;
			}
			break;
		case 543:// Karim
			switch (getDialogueAction(c)) {
			case 1:
				if (buttons == options2[2])//option 2
					MiscDialogue.handle(c, 3);
				else
					MiscDialogue.handle(c, 6);//option 1
				break;
			}
			break;
		
		default: // Default(LAYOUT)
			switch (getDialogueAction(c)) {
			case 1:// dialogue action
				if (buttons == options2[1]) // option1
					DefaultDialogue.handle(c, 10);
				else
					DefaultDialogue.handle(c, 7);//option 2
				break;
			}
			break;
		}
	}

	public static int getDialogueAction(Client c) {
		return c.chatDialogueAction;
	}

	public static void setDialogueAction(Client c, int action) {
		c.chatDialogueAction = action;
	}

	public static void setNextDialogue(Client c,int dialogue) {
		c.chatDialogue = dialogue;
	}

	public static int getDialogueId(Client c) {
		return c.chatDialogue;
	}

	public static void resetChatDialogue(Client c) {
		setNextDialogue(c, -1);
		setDialogueAction(c, -1);
		c.getPA().removeAllWindows();
    		c.dialogueAction = -1;
    		c.nextChat = 0;
	}

	public void sendStartInfo(Client c, String text, String text1,
			String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame218(6179);
		c.getPA().sendFrame164(6179);
	}

	/*
	 * Item chat
	 */

	public static void sendItemChat1(Client c, String header, String one,
			int item, int zoom) {
		c.getPA().sendFrame246(4883, zoom, item);
		c.getPA().sendFrame126(header, 4884);
		c.getPA().sendFrame126(one, 4885);
		c.getPA().sendFrame164(4882);
	}

	public static void sendItemChat2(Client c, String header, String one,
			String two, int item, int zoom) {
		c.getPA().sendFrame246(4888, zoom, item);
		c.getPA().sendFrame126(header, 4889);
		c.getPA().sendFrame126(one, 4890);
		c.getPA().sendFrame126(two, 4891);
		c.getPA().sendFrame164(4887);
	}

	public void sendItemChat3(Client c, String header, String one, String two,
			String three, int item, int zoom) {
		c.getPA().sendFrame246(4894, zoom, item);
		c.getPA().sendFrame126(header, 4895);
		c.getPA().sendFrame126(one, 4896);
		c.getPA().sendFrame126(two, 4897);
		c.getPA().sendFrame126(three, 4898);
		c.getPA().sendFrame164(4893);
	}

	public void sendItemChat4(Client c, String header, String one, String two,
			String three, String four, int item, int zoom) {
		c.getPA().sendFrame246(4901, zoom, item);
		c.getPA().sendFrame126(header, 4902);
		c.getPA().sendFrame126(one, 4903);
		c.getPA().sendFrame126(two, 4904);
		c.getPA().sendFrame126(three, 4905);
		c.getPA().sendFrame126(four, 4906);
		c.getPA().sendFrame164(4900);
	}

	/*
	 * Options
	 */

	public static void sendOption(Client c, String s) {
		c.getPA().sendFrame126("Select an Option", 2470);
		c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126("Click here to continue", 2473);
		c.getPA().sendFrame164(13758);
	}

	public static void sendOption2(Client c, String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}

	public static void sendOption3(Client c, String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2470);
		c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126(s2, 2473);
		c.getPA().sendFrame164(2469);
	}

	public static void sendOption4(Client c, String s, String s1, String s2,
			String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}

	public static void sendOption5(Client c, String s, String s1, String s2,
			String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */

	public static void sendStatement(Client c, String s) {
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}

	public static void sendStatement2(Client c, String s, String s1) {
		c.getPA().sendFrame126(s, 360);
		c.getPA().sendFrame126(s1, 361);
		c.getPA().sendFrame126("Click here to continue", 362);
		c.getPA().sendFrame164(359);
	}

	public static void sendStatement3(Client c, String s, String s1, String s2) {
		c.getPA().sendFrame126(s, 364);
		c.getPA().sendFrame126(s1, 365);
		c.getPA().sendFrame126(s1, 366);
		c.getPA().sendFrame126("Click here to continue", 367);
		c.getPA().sendFrame164(363);
	}

	public static void sendStatement4(Client c, String s, String s1, String s2,
			String s3) {
		c.getPA().sendFrame126(s, 369);
		c.getPA().sendFrame126(s1, 370);
		c.getPA().sendFrame126(s2, 371);
		c.getPA().sendFrame126(s3, 372);
		c.getPA().sendFrame126("Click here to continue", 373);
		c.getPA().sendFrame164(368);
	}

	/*
	 * Npc Chatting
	 */
	public static void sendNpcChat1(Client c, String s, int anim) {
		String name = GetNpcName(c.npcType);
		c.getPA().sendFrame200(4883, anim);
		c.getPA().sendFrame126(name, 4884);
		c.getPA().sendFrame126(s, 4885);
		c.getPA().sendFrame75(c.npcType, 4883);
		c.getPA().sendFrame164(4882);
	}

	public static void sendNpcChat2(Client c, String s, String s1, int anim) {
		String name = GetNpcName(c.npcType);
		c.getPA().sendFrame200(4888, anim);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(c.npcType, 4888);
		c.getPA().sendFrame164(4887);
	}

	public static void sendNpcChat3(Client c, String s, String s1, String s2,
			int anim) {
		String name = GetNpcName(c.npcType);
		c.getPA().sendFrame200(4894, anim);
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(c.npcType, 4894);
		c.getPA().sendFrame164(4893);
	}

	public static void sendNpcChat4(Client c, String s, String s1, String s2,
			String s3, int anim) {
		String name = GetNpcName(c.npcType);
		c.getPA().sendFrame200(4901, anim);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(c.npcType, 4901);
		c.getPA().sendFrame164(4900);
	}

	/*
	 * Player Chating Back
	 */

	public static void sendPlayerChat1(Client c, String s, int anim) {
		c.getPA().sendFrame200(969, anim);
		c.getPA().sendFrame126(Misc.capitalize(c.playerName), 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}

	public static void sendPlayerChat2(Client c, String s, String s1, int anim) {
		c.getPA().sendFrame200(974, anim);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);

	}

	public static void sendPlayerChat3(Client c, String s, String s1,
			String s2, int anim) {
		c.getPA().sendFrame200(980, anim);
		c.getPA().sendFrame126(c.playerName, 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}

	public static void sendPlayerChat4(Client c, String s, String s1,
			String s2, String s3, int anim) {
		c.getPA().sendFrame200(987, anim);// 591 == animation.
		c.getPA().sendFrame126(c.playerName, 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}

	public static String GetNpcName(int NpcID) {
		return Server.npcHandler.getNpcListName(NpcID).replace("_", " ");
	}

}
