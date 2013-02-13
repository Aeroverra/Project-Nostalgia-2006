package server.content;

import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * @author somedude
 */
public class HairDresser {	
	
	/**
	 * Hairdresser dialogue
	 * @param c
	 * @param dialogue
	 * @param npcId
	 */
	public static void dialogue(Client c,int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch(dialogue) {
		case 1300:
			DialogueHandler.sendNpcChat2(c,"Good afternoon, sir. In need of a haircut or shave, are", "we?", 598, "Hairdresser");
			c.nextChat = 1301;
			break;

		case 1301:		
			DialogueHandler.sendOption3(c,"A haircut, please.", "A shave, please.", "No, thank you.");
           c.dialogueAction = 1301;
			break;
		
		case 1302:// first option
			DialogueHandler.sendPlayerChat1(c, "A haircut, please.");
			c.nextChat = 1303;
			break;

		case 1303:
			DialogueHandler.sendNpcChat1(c,"Certainly, sir. The fee will be 2,000 coins.", 598, "Hairdresser");
			c.nextChat = 1304;
			break;

		case 1304:
			if (c.getItems().playerHasItem(995, 2000)) {
				DialogueHandler.sendNpcChat2
				(c,"Please select a hairstyle you would", "like from this brochure."
						, 598, "Hairdresser");				
				c.nextChat = 1305;
			} else {
				DialogueHandler.sendNpcChat2
				(c,"It looks like you don't have 2,000 coins,", "please revisit when you do."
						, 598, "Hairdresser");				
				c.nextChat = 0;
			}
			break;

		case 1305:
			c.getPA().showInterface(2653); // hairstyle interface
			break;
       // end of hairstyle cut.
			
		case 1306: // dialogue option 3
			DialogueHandler.sendPlayerChat1(c,"No, thank you.");
			c.nextChat = 1307;
			break;

		case 1307:
			DialogueHandler.sendNpcChat1(c,"Very well. Come back if you change your mind.", 598, "Hairdresser");
			c.nextChat = 0;
			break;
			// END
		case 1308: // start of shaving
			DialogueHandler.sendPlayerChat1(c,"A shave, please.");
			c.nextChat = 1309;
			break;
			
		case 1309:
			DialogueHandler.sendNpcChat1(c,"Certainly, sir. The fee will be 2,000 coins.", 598, "Hairdresser");
			c.nextChat = 1310;
			break;

		case 1310:
			if (c.getItems().playerHasItem(995, 2000)) {
				DialogueHandler.sendNpcChat2(c,"Please select a beard and color you would", "like from this brochure.", 598, "Hairdresser");
				c.nextChat = 1311;
			} else {
				DialogueHandler.sendNpcChat2(c,"It looks like you don't have 2,000 coins,", "please revisit when you do.", 598, "Hairdresser");
				c.nextChat = 0;
			}
			break;
			
		case 1311:
			c.getPA().showInterface(2007); // hair/beard interface
			c.nextChat = 0;
			break;
			
		}
}
	/**
	 * Handles buttons of hair/shave interface and for dialogue
	 * @param c
	 * @param actionButtonId
	 */
	public static void buttons(Client c, int actionButtonId) {
		switch (actionButtonId) { // new dialogue action handler
		case 9167:
			switch (c.dialogueAction) { // new dialogue action handler
			case 1301: // first option haircut.
				dialogue(c, 1302, 598);
				break;

			}
			break;
			
		case 9169: // third option. NO THANKS BITCH
			switch (c.dialogueAction) {
			case 1301:
				dialogue(c, 1306, 598);
			}
			break;
           
		case 9168: // second option SHAVING
			switch (c.dialogueAction) {
			case 1301:
				dialogue(c, 1308, 598);
			}
			break;
			
/** Hairdresser buttons */
		case 8100:
			c.playerAppearance[7] = 11; // beard 11: long
			c.getPA().requestUpdates();
			break;

		case 8101:
			c.playerAppearance[7] = 10; // beard 10: goatee
			c.getPA().requestUpdates();
			break;

		case 8102:
			c.playerAppearance[7] = 13; // beard 13: mustache
			c.getPA().requestUpdates();
			break;

		case 8103:
			c.playerAppearance[7] = 15; // beard 15: Chin strap
			c.getPA().requestUpdates();
			break;

		case 8104:
			c.playerAppearance[7] = 17; // beard 17: Barbarian beard?
			c.getPA().requestUpdates();
			break;

		case 8105:
			c.playerAppearance[7] = 12; // beard 12: Egyptian beard?
			c.getPA().requestUpdates();
			break;

		case 8106:
			c.playerAppearance[7] = 14; // beard 14: Clean shaven 
			c.getPA().requestUpdates();
			break;

		case 8107:
			c.playerAppearance[7] = 16; // beard 16: Goatee + Chin strap
			c.getPA().requestUpdates();
			break;

		case 8088:
			c.playerAppearance[8] = 0; // hair/beard color: Dark-brown
			c.getPA().requestUpdates();
			break;

		case 8089:
			c.playerAppearance[8] = 1; // hair/beard color: White
			c.getPA().requestUpdates();
			break;

		case 8090:
			c.playerAppearance[8] = 2; // hair/beard color: Gray
			c.getPA().requestUpdates();
			break;

		case 8091:
			c.playerAppearance[8] = 3; // hair/beard color: Black
			c.getPA().requestUpdates();
			break;

		case 8092:
			c.playerAppearance[8] = 4; // hair/beard color: Orange
			c.getPA().requestUpdates();
			break;

		case 8093:
			c.playerAppearance[8] = 5; // hair/beard color: Blonde
			c.getPA().requestUpdates();
			break;

		case 8094:
			c.playerAppearance[8] = 6; // hair/beard color: Light-brown
			c.getPA().requestUpdates();
			break;

		case 8095:
			c.playerAppearance[8] = 7; // hair/beard color: Brown
			c.getPA().requestUpdates();
			break;

		case 8096:
			c.playerAppearance[8] = 8; // hair/beard color: Cyan
			c.getPA().requestUpdates();
			break;

		case 8097:
			c.playerAppearance[8] = 9; // hair/beard color: Green
			c.getPA().requestUpdates();
			break;

		case 8098:
			c.playerAppearance[8] = 10; // hair/beard color: Red
			c.getPA().requestUpdates();
			break;

		case 8099:
			c.playerAppearance[8] = 11; // hair/beard color: Pink
			c.getPA().requestUpdates();
			break;
			
		case 10229:
			c.playerAppearance[1] = 0; // 0: Bald
			c.getPA().requestUpdates();
			break;
			
		case 10230:
			c.playerAppearance[1] = 1; // 1: Dreadlocks
			c.getPA().requestUpdates();
			break;
			
		case 10231:
			c.playerAppearance[1] = 2; // 2: Long hair
			c.getPA().requestUpdates();
			break;
			
		case 10232:
			c.playerAppearance[1] = 3; // 3: Medium hair
			c.getPA().requestUpdates();
			break;
			
		case 10233:
			c.playerAppearance[1] = 4; // 4: Monk
			c.getPA().requestUpdates();
			break;
			
		case 10234:
			c.playerAppearance[1] = 5; // 5: Comb-over
			c.getPA().requestUpdates();
			break;
			
		case 10235:
			c.playerAppearance[1] = 6; // 6: Close-cropped
			c.getPA().requestUpdates();
			break;
			
		case 10236:
			c.playerAppearance[1] = 7; // Wild spikes
			c.getPA().requestUpdates();
			break;
			
		case 10237:
			c.playerAppearance[1] = 8; // Spikes
			c.getPA().requestUpdates();
			break;
			
		case 10217:
			c.playerAppearance[8] = 0; // hair/beard color: Dark-brown
			c.getPA().requestUpdates();
			break;

		case 10218:
			c.playerAppearance[8] = 1; // hair/beard color: White
			c.getPA().requestUpdates();
			break;

		case 10219:
			c.playerAppearance[8] = 2; // hair/beard color: Gray
			c.getPA().requestUpdates();
			break;

		case 10220:
			c.playerAppearance[8] = 3; // hair/beard color: Black
			c.getPA().requestUpdates();
			break;

		case 10221:
			c.playerAppearance[8] = 4; // hair/beard color: Orange
			c.getPA().requestUpdates();
			break;

		case 10222:
			c.playerAppearance[8] = 5; // hair/beard color: Blonde
			c.getPA().requestUpdates();
			break;

		case 10223:
			c.playerAppearance[8] = 6; // hair/beard color: Light-brown
			c.getPA().requestUpdates();
			break;

		case 10224:
			c.playerAppearance[8] = 7; // hair/beard color: Brown
			c.getPA().requestUpdates();
			break;

		case 10225:
			c.playerAppearance[8] = 8; // hair/beard color: Cyan
			c.getPA().requestUpdates();
			break;

		case 10226:
			c.playerAppearance[8] = 9; // hair/beard color: Green
			c.getPA().requestUpdates();
			break;

		case 10227:
			c.playerAppearance[8] = 10; // hair/beard color: Red
			c.getPA().requestUpdates();
			break;

		case 10228:
			c.playerAppearance[8] = 11; // hair/beard color: Pink
			c.getPA().requestUpdates();
			break;
			
		case 10193:
			c.getItems().deleteItem(995, 2000);
			c.getPA().removeAllWindows();
			break;

		case 8065:
			c.getItems().deleteItem(995, 2000);
			c.getPA().removeAllWindows();
			break;
			/** End of Hairdresser buttons */
	}
	
	}
}
