package server.game.players;

import server.Config;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import core.util.Misc;

public class DialogueHandler {

	/**
	 * Handles all talking
	 * @param dialogue The dialogue you want to use
	 * @param npcId The npc id that the chat will focus on during the chat
	 */
	public static void sendDialogues(Client c, int dialogue, int npcId) {

		c.talkingNpc = npcId;
		switch(dialogue) {
		/**
		 * Guilds
		 */
		case 2793: // cooking
			sendNpcChat1(c, "Sorry, but only experienced chefs may enter.", c.talkingNpc, "Head chef");
			c.sendMessage("You need a cooking level of 32 to enter this guild.");
			c.nextChat = 0;
			break;
		case 2794:// cooking without chef hat
			sendNpcChat2(c, 
			"You can't come in here unless you're wearing a chef's",
			"hat, or something like that.", c.talkingNpc, "Head chef");
			c.nextChat = 0;
			break;
			
		case 1355:
			sendOption2(c, "Climb Up", "Climb Down");
			c.dialogueAction = 1355;
			break;

		case 1356:
			sendOption2(c, "Climb Up", "Climb Down");
			c.dialogueAction = 1356;
			break;

			// ladders up
		case 9001:
			sendOption2(c, "Teleport me to a random place!", "Leave me here.");
			c.dialogueAction = 9001;
			break;
		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 1:
			sendStatement(c, "You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 2:
			sendOption2(c, "Yea! I'm fearless!",  "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendNpcChat1(c, "'Ello, and what are you after then?", c.talkingNpc, "Chaeldar");
			c.nextChat = 4;
			break;
		case 5:
			sendNpcChat4(c, "Hello adventurer...", "My name is Kolodion, the master of this mage bank.", "Would you like to play a minigame in order ",
					"to earn points towards receiving magic related prizes?", c.talkingNpc, "Kolodion");
			c.nextChat = 6;
			break;
		case 6:
			sendNpcChat4(c, "The way the game works is as follows...", "You will be teleported to the wilderness,",
					"You must kill mages to recieve points,","redeem points with the chamber guardian.", c.talkingNpc, "Kolodion");
			c.nextChat = 15;
			break;
		case 11:
			sendNpcChat1(c, "'Ello, and what are you after then?", c.talkingNpc, "Chaeldar");
			c.nextChat = 12;
			break;
		case 12:
			sendOption2(c, "I need an assignment.", "Err... nothing.");
			c.dialogueAction = 5;
			break;
		case 13:
			sendNpcChat1(c, "'Ello, and what are you after then?", c.talkingNpc, "Chaeldar");
			c.nextChat = 14;
			break;
		case 14:
			sendOption2(c, "I need an easier assignment.", "Err... nothing.");
			c.dialogueAction = 6;
			break;
		case 15:
			sendOption2(c, "Yes I would like to play", "No, sounds too dangerous for me.");
			c.dialogueAction = 7;
			break;

		case 17:
			sendOption5(c, "Air altar", "Mind altar", "Water altar", "Earth altar", "More");
			c.dialogueAction = 10;
			c.dialogueId = 17;
			c.teleAction = -1;
			break;
		case 18:
			sendOption5(c, "Fire altar", "Body altar", "Cosmic altar", "Astral altar", "More");
			c.dialogueAction = 11;
			c.dialogueId = 18;
			c.teleAction = -1;
			break;
		case 19:
			sendOption5(c, "Nature altar", "Law altar", "Death altar", "Blood altar", "More");
			c.dialogueAction = 12;
			c.dialogueId = 19;
			c.teleAction = -1;
			break;

		case 57:
			c.getPA().sendFrame126("Teleport to shops?", 2460);
			c.getPA().sendFrame126("Yes.", 2461);
			c.getPA().sendFrame126("No.", 2462);
			c.getPA().sendFrame164(2459);
			c.dialogueAction = 27;
			break;
		case 58:
			sendNpcChat1(c, "Where would you like me to take you?", c.talkingNpc, "Sailor");
			c.nextChat = 59;
			break;
		case 59:
			sendOption3(c, "Port Phasmatys", "Karamja", "Brimhaven");
			c.dialogueAction = 13;
			c.dialogueId = 59;
			c.teleAction = -1;
			break;
		case 60:
			sendNpcChat1(c, "Welcome to the church of Entrana, my brother.", c.talkingNpc, "Brother Jered");
			c.nextChat = 61;
			break;
		case 61:
			sendPlayerChat1(c, "Thanks, brother Jered.");
			c.nextChat = 0;
			break;
		case 62:
			c.sendMessage("This is your first time playing, welcome to F2P server 06");
			c. getPA().showInterface(3559);
			c.canChangeAppearance = true;
			c.nextChat = 0;
			break;
		case 63:
			sendNpcChat1(c, "Would you like to change your appearance?", c.talkingNpc, "Make-over mage");
			c.nextChat = 64;
			break;
		case 64:
			sendOption2(c, "Yes please.", "No thanks.");
			c.dialogueAction = 14;
			c.dialogueId = 64;
			break;
		case 65:
			sendPlayerChat1(c, "No thanks.");
			c.nextChat = 0;
			break;
		case 66:
			sendNpcChat1(c, "Do you want to take a look at my fishing supplies?", c.talkingNpc, "Master fisher");
			c.nextChat = 67;
			break;
		case 67:
			sendOption2(c, "Yes.", "No.");
			c.dialogueAction = 15;
			c.dialogueId = 67;
			break;
		case 68:
			sendOption3(c, "Duel Arena", "Barrows", "The TzHaar City");
			c.dialogueAction = 16;
			c.dialogueId = 68;
			break;
		case 69:
			sendNpcChat2(c, "You even defeated TzTok-Jad, I am most impressed!", "Please accept this gift as a reward.", c.talkingNpc, "Tzhaar-Mej-Tal");
			c.nextChat = -1;
			break;
		case 70:
			sendNpcChat2(c, "Hey, do you want me to bring you into the icy cavern?", "I can't help you in there though, it's too dangerous.", c.talkingNpc, "Wizard Mizgog");
			c.nextChat = 71;
			break;
		case 71:
			sendStatement4(c, "@red@WARNING!", "This cavern contains very dangerous monsters.", "You can only escape by teleporting.", "Do you really want to enter?");
			c.nextChat = 72;
			break;
		case 72:
			sendOption2(c, "Yes, follow Mizgog into the cavern.", "No.");
			c.dialogueAction = 17;
			c.dialogueId = 69;
			break;
		case 73:
			sendNpcChat1(c, "Hello " + Misc.capitalize(c.playerName) + ", take a look at my herblore supplies!", c.talkingNpc, "Kaqemeex");
			c.nextChat = 74;
			break;
		case 74:
			c.getShops().openShop(12);
			break;
		case 75:
			sendNpcChat2(c, "Hello sir, I am specialized in tribal weaponry. Do you", "want to take a look at my shop?", c.talkingNpc, "Tribal Weapon Salesman");
			c.nextChat = 76;
			break;
		case 76:
			sendOption2(c, "Sure, why not?", "Not right now.");
			c.dialogueAction = 18;
			c.dialogueId = 76;
			break;
		case 77:
			sendNpcChat1(c, "Hello, do you want to take a look at my farming shop?", c.talkingNpc, "Farmer Brumty");
			c.nextChat = 78;
			break;
		case 78:
			sendOption2(c, "Yes, I'm in need of farming supplies.", "No thanks.");
			c.dialogueAction = 19;
			c.dialogueId = 78;
			break;
		case 79:
			sendStatement2(c, "The ship will take you to the river troll's island.", "Are you sure you want to go?");
			c.nextChat = 80;
			break;
		case 80:
			sendOption2(c, "Yes!", "No.");
			c.dialogueAction = 20;
			c.dialogueId = 80;
			break;
		case 81:
			sendNpcChat1(c, "Do you want me to teleport you to the slayer tower?", c.talkingNpc, "Old Man");
			c.nextChat = 82;
			break;
		case 82:
			sendOption2(c, "Yes.", "No.");
			c.dialogueAction = 21;
			c.dialogueId = 82;
			break;
		case 83:
			sendOption2(c, "Teleport to rock crabs.", "Nevermind.");
			c.dialogueAction = 22;
			c.dialogueId = 83;
			break;
		case 84:
			sendNpcChat2(c, "Hey there! I've gained a lot of gear lately. Do you", "want to take a look?", c.talkingNpc, "Bandit shopkeeper");
			c.nextChat = 85;
			break;
		case 85:
			sendOption2(c, "Sure.", "No thanks.");
			c.dialogueAction = 23;
			c.dialogueId = 85;
			break;
		case 86:
			sendNpcChat1(c, "Do you want to take a look at my pickaxes?", c.talkingNpc, "Nulodion");
			c.nextChat = 87;
			break;
		case 87:
			sendOption2(c, "Yes.", "No thanks.");
			c.dialogueAction = 24;
			c.dialogueId = 87;
			break;
		case 88:
			sendNpcChat1(c, "Do you want to go to the gnome agility course?", c.talkingNpc, "Gnome");
			c.nextChat = 89;
			break;
		case 89:
			sendOption2(c, "Yes.", "No thanks.");
			c.dialogueAction = 25;
			c.dialogueId = 89;
			break;

			/*
			 * Al- kharid gates
			 */
		case 500:
			sendPlayerChat1(c, "Can I come through this gate?");
			c.nextChat = 501;
			break;
		case 501:
			sendNpcChat1(c, "You must pay a toll of 10 gold coins to pass.", c.talkingNpc, "Border Guard");
			c.nextChat = 502;
			break;
		case 502:
			sendOption3(c, "Okay, I'll pay.", "Who does my money go to?", "No thanks, I'll walk around.");
			c.dialogueAction = 502;
			break;
		case 503:
			sendPlayerChat1(c, "Okay, I'll pay.");
			c.nextChat = 499;
			break;
		case 499:
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(PlayerHandler.players[i] != null) {
					Client c2 = (Client)PlayerHandler.players[i];
					c2.getPA().object(2882, 3268, 3227, 3, 0);
					c2.getPA().object(2883, 3268, 3228, 1, 0);
				}
			}
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			if (c.objectX == 3268) {
				if (c.absX < c.objectX) {
					c.getPA().walkTo(1,0);
				} else {
					c.getPA().walkTo(-1,0);
				}
				c.getItems().deleteItem(995, 10);
			}
			c.sendMessage("You pay the toll guard 10 coins and pass through the gate.");
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(PlayerHandler.players[i] != null) {
							Client c2 = (Client)PlayerHandler.players[i];
							c2.getPA().object(2882, 3268, 3227, 0, 0);
							c2.getPA().object(2883, 3268, 3228, 0, 0);
						}
					}

					container.stop();
				}
				@Override
				public void stop() {

				}
			}, 2);
			break;
		case 505:
			sendPlayerChat1(c, "Who does my money go to?");
			c.nextChat = 506;
			break;
		case 506:
			sendNpcChat1(c, "The money goes to the city of Al-Kharid.", c.talkingNpc, "Border Guard");
			c.nextChat = 0;
			break;
		case 507:
			sendOption3(c, "Okay, I'll pay.", "Where does my money go to?", "No thanks, I'll walk around.");
			c.dialogueAction = 508;
			break;
		case 508:
			sendPlayerChat1(c, "No thanks, I'll walk around.");
			c.nextChat = 509;
			break;
		case 509:
			sendNpcChat1(c, "As you wish. Don't go too close to the scorpions", c.talkingNpc, "Border Guard");
			c.nextChat = 0;
			break;
		case 510:
			sendPlayerChat1(c, "Can I come through this gate?");
			c.nextChat = 511;
			break;
		case 511:
			sendNpcChat1(c, "You must pay a toll of 10 gold coins to pass.", c.talkingNpc, "Border Guard");
			c.nextChat = 512;
			break;
		case 512:
			sendOption2(c, "Who does my money go to?", "I haven't got that much.");
			c.dialogueAction = 512;
			break;

		case 513:
			sendNpcChat1(c,"The money goes to the city of Al-Kharid.", c.talkingNpc, "Border Guard");
			c.nextChat = 0;
			break;

		case 514:
			sendPlayerChat1(c, "I haven't got that much.");
			c.nextChat = 0;
			break;

		case 515:
			sendNpcChat1(c,"You must pay a toll of 10 gold coins to pass.", c.talkingNpc, "Border Guard");
			c.nextChat = 0;
			break;
			//end


			
			/*
			 * Banker dialogues
			 */
		case 1000:
			sendNpcChat1(c, "Hello, how may I help you?", c.talkingNpc, "Banker");
			c.nextChat = 1001;
			break;
		case 1001:
			sendPlayerChat1(c, "I would like to access my bank account.");
			c.nextChat = 1002;
			break;
		case 1002:
			sendNpcChat1(c, "Of course.", c.talkingNpc, "Banker");
			c.nextChat = 1003;
			break;
		case 1003:
			c.sendMessage("The banker opens up your bank account.");
			c.getPA().openUpBank();
			c.nextChat = 0;
			break;

			/*
			 * Zaff dialogues
			 */
		case 1004:
			sendNpcChat1(c, "Hello "+Misc.capitalize(c.playerName)+"!", c.talkingNpc, "Zaff");
			c.nextChat = 1005;
			break;
		case 1005:
			sendPlayerChat1(c, "Hello Zaff.");
			c.nextChat = 1006;
			break;
		case 1006:
			sendNpcChat1(c, "So how are you today?", c.talkingNpc, "Zaff");
			c.nextChat = 1007;
			break;
		case 1007:
			sendPlayerChat1(c, "I'm fine!");
			c.nextChat = 0;
			break;

			/*
			 * Thessalia dialogues
			 */
		case 1008:
			sendNpcChat1(c, "Hi...", c.talkingNpc, "Thessalia");
			c.nextChat = 1009;
			break;
		case 1009:
			sendPlayerChat1(c, "Hello Thessalia, how are you?");
			c.nextChat = 1010;
			break;
		case 1010:
			sendNpcChat1(c, "I guess not so good...", c.talkingNpc, "Thessalia");
			c.nextChat = 1011;
			break;
		case 1011:
			sendPlayerChat1(c, "What's wrong?");
			c.nextChat = 1012;
			break;
		case 1012:
			sendNpcChat2(c, "None of your business! Do you want to buy any", "clothes or not?!", c.talkingNpc, "Thessalia");
			c.nextChat = 1013;
			break;
		case 1013:
			sendPlayerChat1(c, "Err... Maybe later...");
			//c.sendMessage("Thessalia starts crying... What was that all about?");
			c.nextChat = 0;
			break;
			
			/*
			 * Bananas
			 */
		case 4000:// luthas
			sendNpcChat1(c, "Hi, I'm Luthas, I run the banana plantation here.", c.talkingNpc, "Luthas");
			c.nextChat = 4001;
			break;
		case 4001:// luthas
			sendOption2(c,"Could you offer me employment on your plantation?", "The custom's officer can be annoying.");
			c.dialogueAction = 4001;
			break;
		case 4002://luthas
			sendPlayerChat1(c, "Could you offer me employment on your plantation?");
			c.nextChat = 4003;
			break;
		case 4003:
			sendNpcChat2(c, "Yes, I can sort something out. There's a crate ready to",
					"be loaded onto the ship.", 
					c.talkingNpc, "Luthas");
			c.nextChat = 4004;
		break;
		case 4004:
			sendNpcChat2(c, "If you fill it up with bananas, I'll pay you 30",
					"gold.", 
					c.talkingNpc, "Luthas");
			c.nextChat = 0;
			c.luthas = 1;
		break;
		case 4005:
			sendPlayerChat1(c, "I filled a crate with bananas.");
			c.nextChat = 4006;
			break;
		case 4006:
			sendNpcChat1(c, "Well done, here's your payment.", c.talkingNpc, "Luthas");
			c.nextChat = 4007;
			break;
		case 4007:
			c.sendMessage("Luthas hands you 30 coins.");
			c.getItems().addItem(995, 30);
			c.nextChat = 0;
			c.getPA().removeAllWindows();
			c.bananacrate = 0;
			c.luthas = 0;
			break;
			
			/*
			 * Random Lumbridge dialogues
			 */
			
		
			

			/*
			 * Random.
			 */
		case 996:
			sendNpcChat1(c, "I don't want to talk to people unexperienced with magic!", c.talkingNpc, "Aubury");
			c.nextChat = 0;
			break;
		case 997:
			sendNpcChat1(c, "Take a look in the chest over there.", c.talkingNpc, "Tramp");
			c.nextChat = 0;
			break;
		case 999:
			sendNpcChat1(c, "Hello and welcome to the general store!", c.talkingNpc, "Shop Keeper");
			c.nextChat = 0;
			break;
		case 998:
			sendNpcChat1(c, "You have no business here!", c.talkingNpc, "Guard");
			c.nextChat = 0;
			break;
		case 2000:
			sendNpcChat1(c, "Did you know F2P server 06 is in Alpha stage?", c.talkingNpc, "");
			c.nextChat = 2001;
			break;
		case 2001:
			sendPlayerChat1(c, "I know... you fag!");
			c.nextChat = 0;
			break;
		}
	}
	
	
	

	public static void sendStartInfo(Client c,String text, String text1, String text2, String text3, String title) {
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

	public static void sendItemChat1(Client c,String header, String one, int item, int zoom) {
		c.getPA().sendFrame246(4883, zoom, item);
		c.getPA().sendFrame126(header, 4884);
		c.getPA().sendFrame126(one, 4885);
		c.getPA().sendFrame164(4882);
	}

	public static void sendItemChat2(Client c,String header, String one, String two, int item, int zoom) {
		c.getPA().sendFrame246(4888, zoom, item);
		c.getPA().sendFrame126(header, 4889);
		c.getPA().sendFrame126(one, 4890);
		c.getPA().sendFrame126(two, 4891);
		c.getPA().sendFrame164(4887);
	}

	public void sendItemChat3(Client c,String header, String one, String two, String three, int item, int zoom) {
		c.getPA().sendFrame246(4894, zoom, item);
		c.getPA().sendFrame126(header, 4895);
		c.getPA().sendFrame126(one, 4896);
		c.getPA().sendFrame126(two, 4897);
		c.getPA().sendFrame126(three, 4898);
		c.getPA().sendFrame164(4893);
	}

	public void sendItemChat4(Client c,String header, String one, String two, String three, String four, int item, int zoom) {
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

	public static void sendOption(Client c,String s) {
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

	public static void sendOption3(Client c,String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2470);
		c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126(s2, 2473);
		c.getPA().sendFrame164(2469);
	}

	public static void sendOption4(Client c,String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}

	public static void sendOption5(Client c,String s, String s1, String s2, String s3, String s4) {
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

	public static void sendStatement(Client c,String s) {
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}

	public static void sendStatement2(Client c,String s, String s1) {
		c.getPA().sendFrame126(s, 360);
		c.getPA().sendFrame126(s1, 361);
		c.getPA().sendFrame126("Click here to continue", 362);
		c.getPA().sendFrame164(359);
	}
	public static void sendStatement3(Client c,String s, String s1, String s2) {
		c.getPA().sendFrame126(s, 364);
		c.getPA().sendFrame126(s1, 365);
		c.getPA().sendFrame126(s1, 366);
		c.getPA().sendFrame126("Click here to continue", 367);
		c.getPA().sendFrame164(363);
	}

	public static void sendStatement4(Client c,String s, String s1, String s2, String s3) {
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

	public static void sendNpcChat1(Client c,String s, int ChatNpc, String name) {
		c.getPA().sendFrame200(4883, 591);
		c.getPA().sendFrame126(name, 4884);
		c.getPA().sendFrame126(s, 4885);
		c.getPA().sendFrame75(ChatNpc, 4883);
		c.getPA().sendFrame164(4882);
	}

	public static void sendNpcChat2(Client c,String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 591);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}

	public static void sendNpcChat3(Client c,String s, String s1, String s2, int ChatNpc, String name) {
		c.getPA().sendFrame200(4894, 591);
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}
	public static void sendNpcChat4(Client c,String s, String s1, String s2, String s3, int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 591);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}

	/*
	 * Player Chating Back
	 */

	public static void sendPlayerChat1(Client c,String s) {
		c.getPA().sendFrame200(969, 591);
		c.getPA().sendFrame126(Misc.capitalize(c.playerName), 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	public static void sendPlayerChat2(Client c,String s, String s1) {
		c.getPA().sendFrame200(974, 591);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	

	}
	 public static void sendPlayerChat3(Client c,String s, String s1, String s2) {
	        c.getPA().sendFrame200(980, 591);
	        c.getPA().sendFrame126(c.playerName, 981);
	        c.getPA().sendFrame126(s, 982);
	        c.getPA().sendFrame126(s1, 983);
	        c.getPA().sendFrame126(s2, 984);
	        c.getPA().sendFrame185(980);
	        c.getPA().sendFrame164(979);
	    }

	    public static void sendPlayerChat4(Client c,String s, String s1, String s2, String s3) {
	        c.getPA().sendFrame200(987, 591);//591 == animation.
	        c.getPA().sendFrame126(c.playerName, 988);
	        c.getPA().sendFrame126(s, 989);
	        c.getPA().sendFrame126(s1, 990);
	        c.getPA().sendFrame126(s2, 991);
	        c.getPA().sendFrame126(s3, 992);
	        c.getPA().sendFrame185(987);
	        c.getPA().sendFrame164(986);
	    }

}
