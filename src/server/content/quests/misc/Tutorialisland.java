package server.content.quests.misc;

import server.game.players.Client;
import server.game.players.DialogueHandler;
import core.util.Misc;
/**
 * 100% made by
 * @author somedude
 *Booleans included: ratdied2
 *
 */

public class Tutorialisland {
	
/**
 * This is shit L0l plz don't be means
 * @param c
 * @param dialogue
 * @param npcid
 */
	public static void sendDialogue(final Client c,int dialogue, int npcid) {
		c.talkingNpc = npcid;
		switch(dialogue) {

		case 3000: // first part. Entering the tutorial island
			c.xpLock = true;
			chatbox(c, 6180); // displays chatbox
			removesideicons(c); // removes side icons
			c.getPA().showInterface(3559); // character design
			c.canChangeAppearance = true;
			TII(c,0, 0); // progress bar + tutorial int
			c.getPA().drawHeadicon(1, 1, 0, 0); // sends to rsguide fagf
			chatboxText(c, "To start the tutorial use your left mouse button to click on the",
					"RuneScape Guide in this room. He is indicated by a flashing",
					"yellow arrow above his head. If you can't see him, use your",
					"keyboard's arrow keys to rotate the view.",
					"@blu@Getting started");
			chatbox(c, 6179); // displays chatbox
			c.tutorialprog = 0;
			c.nextChat = 0;
			break;
			/*
			 * RS guide section
			 */
		case 3001: // RS GUIDE
			DialogueHandler.sendNpcChat2
			(c, "Greetings! I see you are a new arrival to the land. My",
					"job is to welcome all new visitors. So welcome!", c.talkingNpc, "Runescape Guide");
			c.nextChat = 3002;
			break;
		case 3002: // 2
			DialogueHandler.sendNpcChat2
			(c, "You have already learned the first thing needed to",
					"succeed in this world: talking to other people!", c.talkingNpc, "Runescape Guide");
			c.nextChat = 3003;
			break;

		case 3003: //3
			DialogueHandler.sendNpcChat3
			(c, "You will find many inhabitants of this world have useful",
					"things to say to you. By clicking on them with your",
					"mouse you can talk to them.",
					c.talkingNpc, "Runescape Guide");
			c.nextChat = 3004;
			break;
		case 3004:
			DialogueHandler.sendNpcChat4
			(c, "I would also suggest reading through some of the",
					"supporting information on the website. There you can",
					"find the Knowledge Base, which contains all the",
					"additional information you're ever likely to need. It also",
					c.talkingNpc, "Runescape Guide");
			c.nextChat = 3005;
			break;
		case 3005:
			DialogueHandler.sendNpcChat2
			(c, "contains maps and helpful tips to help you on your",
					"journey.", c.talkingNpc, "Runescape Guide");
			c.nextChat = 3006;
			break;

		case 3006:// show tab wrentch
			clearchatboxText(c); // call this every time there is new chatboxtext coming up
			DialogueHandler.sendNpcChat2
			(c, "You will notice a flashing icon of a wrench, please click",
					"on this to continue the tutorial.", c.talkingNpc, "Runescape Guide");
			c.setSidebarInterface(11, 904); // wrench tab
			flashSideBarIcon(c, -11);
			c.nextChat = 3007;
			break;

		case 3007: //Player controls
			c.getPA().removeAllWindows();
			chatboxText(c, "Please click on the flashing wrench icon found at the bottom",
					"right of your screen. This will display your player controls.",
					"",
					"",
					"Player controls");
			c.nextChat = 0;
			break;

		case 3008: // Glad your making progress
			DialogueHandler.sendNpcChat1
			(c, "I'm glad you're making progress!", c.talkingNpc, "Runescape Guide");
			c.nextChat = 3009;
			break;

		case 3009:
			DialogueHandler.sendNpcChat2
			(c, "To continue the tutorial go through that door over",
					"there and speak to your first instructor!"	, c.talkingNpc, "Runescape Guide");
			c.nextChat = 3010;
			break;
		case 3010:
			c.tutorialprog = 2;
			 chatbox(c, 6180);
			c.getPA().removeAllWindows();
			chatboxText(c, "You can interact with many items of scenery by simply clicking",
					"on them. Right clicking will also give more options. Feel free to",
					"try it with the things in this room, then click on the door",
					"indicated with the yellow arrow to go through to the next instructor." ,
					"Interacting with scenery");
			 chatbox(c, 6179);
			c.getPA().createArrow(3098, 3107, c.getHeightLevel(), 2);
			c.nextChat = 0;
			break;

		case 3011: // door handling
			c.getPA().removeAllWindows();
			chatboxText(c, "Follow the path to find the next instructor. Clicking on the",
					"ground will walk you to that point. Talk to the Survival Expert by",
					"the pond to the continue the tutorial. Remember you can rotate",
					"the view by pressing the arrow keys." ,
					"Moving around");
			c.getPA().drawHeadicon(1, 2, 0, 0); // sends to Survival Expert
			TII(c,5,2); // progress bar + tutorial int
			c.nextChat = 0;
			break;
			// end end end end end

			/*
			 * Survival Expert. Second part.
			 */
		case 3012: // Survival Expert
			DialogueHandler.sendNpcChat4
			(c, "Hello there, newcomer. My name is Brynna. My job is",
					"to teach you a few survival tips and tricks. First off",
					"we're going to start with the most basic survival skill of",
					"all: making a fire.",
					c.talkingNpc, "Survival Expert");
			c.nextChat = 3013;
			break;

		case 3013: // giving bronze and tinder
		    if(c.getItems().getItemAmount(590) == 0 || c.getItems().getItemAmount(590) == 0){
			DialogueHandler.sendItemChat2(c, "",
					"The Survival Guide gives you a @blu@tinderbox @bla@and a","@blu@bronze axe!", 590, 150);
			c.getItems().addItem(590, 1);
			c.getItems().addItem(1351, 1);}
			c.nextChat = 0;
			chatboxText(c, "Click on the flashing backpack icons to the right hand side of",
					"the main window to view your inventory. Your inventory is a list",
					"of everything you have on your backpack.",
					"" ,
					"Viewing the items that you were given");
			c.setSidebarInterface(3, 3213);// sends interface
			flashSideBarIcon(c, -3); // flashes inventory
			c.tutorialprog = 3;
			break;
		case 3014: // finished cutting tree
			DialogueHandler.sendItemChat1(c, "", "You got some logs", 1511, 150);
			c.nextChat = 3015;
			break;
		case 3015: // firemaking time
			c.getPA().removeAllWindows();
			chatboxText
			(c, "Well done! You managed to cut some logs from the tree! Next,",
					"use the tinderbox in your inventory to light the logs.",
					"First click on the tinderbox to use it.",
					"Then click on the logs in your inventory to light them.",
					"Making a fire");
			c.getPA().drawHeadicon(0, 0, 0, 0); // sends to Survival Expert
			c.tutorialprog = 4;
			break;
			
		case 3016: // firemaking done skill tab flashing now.
			//c.getPA().removeAllWindows();
			chatboxText
			(c, "Click on the flashing bar graph icon near the inventory button",
					"to see your skill stats.",
					"",
					"",
					"You gained some experience.");
			flashSideBarIcon(c, -1); // flashes skill
			c.setSidebarInterface(1, 3917); // sets the skill tab
			c.nextChat = 3017;
			break;
			
		case 3017: //survival expert part 2
			DialogueHandler.sendNpcChat3
			(c, "Well done! Next we need to get some food in our",
				"bellies. We'll need something to cook. There are shrimp"	,
					"in the pond there so let's catch and cook some.",
					 c.talkingNpc, "Runescape Guide");
			c.nextChat = 3018;
			
			break;
			
		case 3018: 
			DialogueHandler.sendItemChat1(c, "",
					"The Survival Guide gives you a @blu@net!", 303, 150);
			c.getItems().addItem(303, 1);
			c.nextChat = 0;
			chatboxText(c, "Click on the sparkling fishing spot indicated by the flashing",
					"arrow. Remember, you can check your inventory by clicking the",
					"backpack icon.",
					"" ,
					"Catch some Shrimp");
			c.getPA().createArrow(3101, 3092, c.getHeightLevel(), 2);
			c.tutorialprog = 6;
			break;
	
		case 3019: // Cooking the shrimp
			chatbox(c, 6180);
			chatboxText
			(c, "Now you have caught some shrimp let's cook it. First light a",
					"fire, chop down a tree and then use the tinderbox on the logs.",
					"If you've lost your axe or tinderbox, Brynna will give you",
					"another.",
					"Cooking your shrimp.");
			chatbox(c, 6179);	
			break;
			//END
			
			/*
			 * 
			 * Finding next tutor FAT BITCH
			 */
		case 3020: // tutorial PROGRESS = 7
          TII(c,15,4); // 15 percent
          chatbox(c, 6180);
			chatboxText
			(c, "Talk to the chef indicated. He will teach you the more advanced",
			"aspects of Cooking such as combining ingredients. He will also",
		"teach you about your music player menu as well.",
		"",
					"Find your next instructor");
			chatbox(c, 6179);
			c.getPA().createArrow(3078, 3084, c.getHeightLevel(), 2);
			break;
			
		case 3021: // start of dialogue.
			DialogueHandler.sendNpcChat3
			(c, "Ah! Welcome newcomer. I am the Master Chef Leo. It",
				"is here I will teach you how to cook food truly fit for a"	,
					"king.",
					 c.talkingNpc, "Master Chef");
			c.nextChat = 3022;			
			break;
			
		case 3022:
			DialogueHandler.sendPlayerChat2
			(c, "I already know how to cook. Brynna taught me just"
					, "now.");
			c.nextChat = 3023;		
break;
		case 3023:
			DialogueHandler.sendNpcChat3
			(c, "Hahahahahaha! You call THAT cooking? Some shrimp",
				"on an open log fire? Oh no, no, no. I am going to",
				"teach you the fine art of cooking bread.",
					 c.talkingNpc, "Master Chef");
			c.nextChat = 3024;			
			break;
		case 3024: 
			DialogueHandler.sendNpcChat2
			(c, "And no fine meal is complete without good music, so",
				"we'll cover that while you're here too.",
					 c.talkingNpc, "Master Chef");
			c.nextChat = 3025;			
			break;
			
		case 3025: // he gives u bucket of water etc TTUOTRIAL PROG 8
			DialogueHandler.sendItemChat2(c, "",
					"The Cooking Guide gives you a @blu@bucket of water@bla@ and a",
					"@blu@pot of flour!", 1933, 150);
			c.getItems().addItem(1933, 1);
			c.getItems().addItem(1929, 1);
			c.nextChat = 0;
			chatboxText(c, "This is the base for many of the meals. To make dough we must",
					"mix flour and water. First right click the bucket of water and",
					"select use, then left click on the pot of flour.",
					"" ,
					"Making dough");
			rmHint(c);
			c.tutorialprog = 8;
			
			break;
		case 3026: // cooking dough
	          chatbox(c, 6180);
				chatboxText
				(c, "Now you have made dough, you can cook it. To cook the dough",
				"use it with the range shown by the arrow. If you lose your",
			"dough, talk to Leo - he will give you more ingredients.",
			"",
						"Cooking dough");
				chatbox(c, 6179);
				c.getPA().createArrow(3075, 3081, c.getHeightLevel(), 2);
			
			c.nextChat = 0;			
			break;
			
		case 3037: // new tutorial prog
			 chatbox(c, 6180);
				chatboxText
				(c, "Well done! Your first loaf of bread. As you gain experience in",
				"Cooking you will be able to make other things like pies, cakes",
			"and even kebabs. Now you've got the hang of cooking, let's",
			"move on. Click on the flashing icon in the bottom right.",
						"Cooking dough");
				chatbox(c, 6179);
				rmHint(c);
			TII(c,20, 5);
			c.setSidebarInterface(13, 962); // sets music
			flashSideBarIcon(c, -13);
			c.tutorialprog = 9;
			c.nextChat = 0;			
			break;
			
		case 3038: // Emotes
			 chatbox(c, 6180);
				chatboxText
				(c, "",
				"Now, how about showing some feelings? You will see a flashing",
			"icon in the shape of a person. Click on that to access your",
			"emotes.",
						"Emotes");
				chatbox(c, 6179);
				rmHint(c);
			TII(c,25, 6); // 25 percent now
			c.setSidebarInterface(12, 147); // run tab
			flashSideBarIcon(c, -12);
			c.nextChat = 0;						
			break;
		case 3039: // running
			c.tutorialprog = 11;
			 chatbox(c, 6180);
				chatboxText
				(c, "It's only a short distance to the next guide.",
				"Why not try running there? Start by opening the player",
			"settings, that's the flashing icon of a wrench.",
			"",
						"Running");
			chatbox(c, 6179);
			flashSideBarIcon(c, -12);
			c.getPA().createArrow(3086, 3126, c.getHeightLevel(), 2);
			c.nextChat = 0;					
			break;
		case 3040:
			 chatbox(c, 6180);
				chatboxText
				(c, "In this menu you will see many options. At the bottom in the",
				"middle is a button with the symbol of a running shoe. You can",
			"turn this button on or off to select run or walk. Give it a go,",
			"click on the run button now.",
						"Running");
			chatbox(c, 6179);
			c.nextChat = 0;					
			break;
		case 3041: // clicked on run
			 chatbox(c, 6180);
				chatboxText
				(c, "Now that you have the run botton turned on, follow the path",
				"until you come to the end. You may notice that the numbers on",
			"the button goes down. This is your run energy. If your run",
			"energy reaches zero, you'll stop running.",
						"Run to the next guide");
			chatbox(c, 6179);
			c.getPA().createArrow(3086, 3125, c.getHeightLevel(), 2);
			
			c.nextChat = 0;					
			break;
			//ENDDDDDDDDDDDDDDDDDDDDDDDD
			
			/**
			 * Quest Guide
			 */			
		case 3042: // entering the quest GUIDE
			c.getPA().drawHeadicon(1, 4, 0, 0); // sends to quest guide
			TII(c,35, 8); // 30 percent now
			chatbox(c, 6180);
				chatboxText
				(c, "Talk with the Quest Guide.",
				"",
			"He will tell you all about quests.",
			"",
						"");
			chatbox(c, 6179);
			c.tutorialprog = 12;
			c.nextChat = 0;
			break;
			
		case 3043: // quest guide dialogue START
			DialogueHandler.sendNpcChat2
			(c, "Ah. Welcome, adventurer. I'm here to tell you all about",
				"quests. Let's start by opening the quest side panel."	,
					 c.talkingNpc, "Quest Guide");
			c.nextChat = 3044;					
			break;
			
		case 3044: // Send quest tab
			c.getPA().removeAllWindows();
		chatbox(c, 6180);
				chatboxText
				(c, "Open the Quest Journal.",
				"",
			"Click on the flashing icon next to your inventory.",
			"",
						"");
				chatbox(c, 6179);
				c.setSidebarInterface(2, 638); // quest
			flashSideBarIcon(c, -2);
			c.nextChat = 0;					
			break;
			
		case 3045: // quest guide 2 prog 13
			DialogueHandler.sendNpcChat3
			(c, "Now you have the journal open. I'll tell you a bit about",
				"it. At the moment all the quests shown in red which",
				"means you have not started them yet.",
					 c.talkingNpc, "Quest Guide");
			c.nextChat = 3046;			
			
			break;
			
		case 3046:
			DialogueHandler.sendNpcChat4
			(c, "When you start a quest it will change colour to yellow",
				"and to green when you've finished. This is so you can",
				"easily see what's complete, what's started, and what's left",
				"to begin.",
					 c.talkingNpc, "Quest Guide");
			c.nextChat = 3047;		
			break;
			
		case 3047:
			DialogueHandler.sendNpcChat3
			(c, "The start of quests are easy to find. Look out for the",
				"star icons on the minimap, just like the one you should",
				"see marking my house.",
					 c.talkingNpc, "Quest Guide");
			c.nextChat = 3048;		
			break;
			
		case 3048:
			DialogueHandler.sendNpcChat4
			(c, "The quests themselves can vary greatly from collecting",
				"beads to hunting down dragons. Generally quests are",
				"started by talking to a non-player character like me,",
				"and will involve a series of tasks.",
					 c.talkingNpc, "Quest Guide");
			c.nextChat = 3049;		
			break;
			
		case 3049: // last
			DialogueHandler.sendNpcChat4
			(c, "There's not a lot more I can tell you about questing.",
				"You have to experience the thrill of it yourself to fully",
				"understand. You may find some adventure in the caves",
				"under my house.",
					 c.talkingNpc, "Quest Guide");
			c.nextChat = 3050;		
			break;
			
		case 3050: // moving on. Cave time biaches
			c.getPA().removeAllWindows();
			chatbox(c, 6180);
					chatboxText
					(c, "",
					"It's time to enter some caves. Click on the ladder to go down to",
				"the next area.",
				"",
							"Moving on");
					chatbox(c, 6179);
					c.getPA().createArrow(3088, 3119, c.getHeightLevel(), 2);
				c.nextChat = 0;		
				c.tutorialprog = 14;
			break;
			//end
			
			/*
			 * Start of Mining/Smithing
			 */
		case 3051:
			c.getPA().removeAllWindows();
			chatbox(c, 6180);
					chatboxText
					(c, "Next let's get you a weapon or more to the point, you can",
					"make your first weapon yourself. Don't panic, the Mining",
				     "Instructor will help you. Talk to him and he'll tell you all about it.",
				    "",
					"Mining and Smithing");
					chatbox(c, 6179);
					TII(c,40,9);
					//c.getPA().drawHeadicon(1, 5, 0, 0); // sends to quest guide
				c.getPA().createArrow(1, 5);
					c.nextChat = 0;		
			break;
			
		case 3052:// mining tutor start
			DialogueHandler.sendNpcChat4
			(c, "Hi there. You must be new around here. So what do I",
				"call you? Newcomer seems so impersonal and if we're",
				"going to be working together, I'd rather call you by",
				"name.",
					 c.talkingNpc, "Mining Instructor");
			c.nextChat = 3053;		
			break;
	
		case 3053:// mining tutor start
			DialogueHandler.sendPlayerChat1(c, "You can call me "+ Misc.capitalize(c.playerName) + ".");
			c.nextChat = 3054;		
			break;
			
		case 3054:// mining tutor start
			DialogueHandler.sendNpcChat2
			(c, "Ok then, " + Misc.capitalize(c.playerName)+"." +" My name is Dezzick and I'm a",
				"miner by trade. Let's prospect some of those rocks.",
					 c.talkingNpc, "Mining Instructor");
			c.nextChat = 3055;		
			break;
			
		case 3055: // prospecting		
			c.getPA().removeAllWindows();
			chatbox(c, 6180);
					chatboxText
					(c, "To prospect a mineable rock, just right click it and select the",
					"'prospect rock' option. This will tell you the type of ore you can",
				     "mine from it. Try it now on one of the rocks indicated.",
				    "",
					"Prospecting");
					chatbox(c, 6179);
					TII(c,40,9);
					c.getPA().createArrow(3076, 9504, c.getHeightLevel(), 2);
					c.nextChat = 0;		
					c.tutorialprog = 15;
					break;
		case 3056: // done prospecting
			DialogueHandler.sendPlayerChat2(c, "I prospected both types of rocks! One set contains tin",
					"and the other has copper ore inside.");
			c.nextChat = 3057;
			break;
			
		case 3057:			
			DialogueHandler.sendNpcChat2
			(c, "Absolutely right, " + Misc.capitalize(c.playerName) + "." + " These two ore types",
				"can be smelted together to make bronze.",
					 c.talkingNpc, "Mining Instructor");
			c.nextChat = 3058;	
			break;
			
		case 3058:			
			DialogueHandler.sendNpcChat3
			(c, "So now you know what ore is in the rocks over there,",
				"why don't you have a go at mining some tin and",
				"copper? here, you'll need this to start with.",
					 c.talkingNpc, "Mining Instructor");
			c.nextChat = 3060;	
			break;
		case 3060:			
			DialogueHandler.sendItemChat1(c, "",
					"Dezzick gives you a @blu@bronze pickaxe!", 1265, 300);
			c.getItems().addItem(1265, 1);
			c.nextChat = 0;
			chatboxText(c, "It's quite simple really. All you need to do is right click on the",
					"rock and select 'mine'. You can only mine when you have a",
					"pickaxe. So give a try: first mine one tin ore.",
					"" ,
					"Mining");
			c.getPA().createArrow(3076, 9504, c.getHeightLevel(), 2); // sends hint to ore
			c.tutorialprog = 17;
			break;
			
		case 3061: // furnace time
			c.tutorialprog = 19;
			c.nextChat = 0;
			chatboxText(c, "You should now have both some copper and tin ore. So let's",
					"smelt them to make a bronze bar. To do this, right click on",
					"either tin or copper ore and select use, then left click on the",
					"furnace. Try it now." ,
					"Smelting");
			break;
		case 3062: // smelting
			c.tutorialprog = 20;
			c.nextChat = 0;
			chatbox(c, 6180);
			chatboxText(c, "",
					"Speak to the Mining Instructor and he'll show you how to make",
					"it into a weapon.",
					"" ,
					"You've made a bronze bar!");
			chatbox(c, 6179);
			break;
			
		case 3063:
			c.nextChat = 3064;
			DialogueHandler.sendPlayerChat1(c, "How do I make a weapon out of this?");
			break;
			
		case 3064:
			DialogueHandler.sendNpcChat2
			(c, "Okay, I'll show you how to make a dagger out of it.",
				"You'll be needing this...",
					 c.talkingNpc, "Mining Instructor");
			c.nextChat = 3065;	
			break;
			
		case 3065: // giving you the hammer
			DialogueHandler.sendItemChat1(c, "",
					"Dezzick gives you a @blu@hammer!", 2347, 300);
			c.getItems().addItem(2347, 1);
			c.nextChat = 0;
			chatboxText(c, "To smith you'll need a hammer - like the one you were given by",
					"Dezzick - access to an anvil like the one with the arrow over it",
					"and enough metal bars to make what you are trying to smith.",
					"" ,
					"Smithing a dagger");
			c.getPA().createArrow(3082, 9499, c.getHeightLevel(), 2); //send hint to furnace
			break;
			
			//end of mining/smithing
			
			/*
			 * start of melee
			 */
		case 3066:
		    c.tutorialprog = 21;
		    chatboxText(c, "So let's move on. Go through the gate shown by the arrow.",
				"Remember, you may need to move the camera to see your",
				"surroundings. Speak to the guide for a recap at any time.",
				"",
				"@blu@You're finished in this area.");
		    c.getPA().createArrow(3094, 9503, c.getHeightLevel(), 2); // sends hint to the gate
		    
		    break;
		case 3067:// Melee instructor c.tutorialprog = 22
			DialogueHandler.sendPlayerChat1(c, "Hi! My name is " + Misc.capitalize(c.playerName) + ".");
			c.nextChat = 3068;
			break;
			
		case 3068:
			DialogueHandler.sendNpcChat2(c, "Do I look like I care? To me you're just another",
					"newcomer who thinks they're ready to fight.", 
					c.talkingNpc, "Combat Instructor");
			c.nextChat = 3069;
			break;
		
		case 3069:
			DialogueHandler.sendNpcChat1(c, "I am Vannaka, the greatest swordsman alive.",
					c.talkingNpc, "Combat Instructor");
			c.nextChat = 3070;
			break;
						
		case 3070:
			DialogueHandler.sendNpcChat1(c, "Let's get started by teaching you to wield a weapon",
					c.talkingNpc, "Combat Instructor");
			c.nextChat = 3071;
			break;
			
		case 3071: // send wear interface						
			c.getPA().removeAllWindows();
		chatbox(c, 6180);
				chatboxText
				(c, "",
				"You now have access to a new interface. Click on the flashing",
			"icon of a man the one to the right of your backpack icon.",
			"",
						"Wielding weapons");
				chatbox(c, 6179);
				c.setSidebarInterface(4, 1644);// worn
			flashSideBarIcon(c, -4);
			c.nextChat = 0;				
			break;
			
		case 3072: 
			DialogueHandler.sendNpcChat2(c, "Very good, but that little butter knife isn't going to",
					"protect you much. Here, take these.", 
					c.talkingNpc, "Combat Instructor");
			c.nextChat = 3073;
		break;
		
		case 3073:
			DialogueHandler.sendItemChat2(c, "",// Gives me sword and shield
					"The Combat Guide gives you a @blu@bronze sword@bla@ and a",
					"@blu@wooden shield!",1171, 300);
			c.getItems().addItem(1171, 1);
			c.getItems().addItem(1277, 1);
			c.nextChat = 0;
			chatboxText(c, "In your worn inventory panel, right click on the dagger and",
					"select the remove option from the drop down list. After you've",
					"unequipped the dagger, wield the sword and shield. As you",
					"pass the mouse over an item you will see its name." ,
					"Unequipping items");
			rmHint(c);
			break;
			
		case 3074:
			DialogueHandler.sendPlayerChat1(c, "I did it! I killed a giant rat!");
			c.nextChat = 3075;				
			break;
			
		case 3075:
			DialogueHandler.sendNpcChat3(c, "I saw, " + Misc.capitalize(c.playerName) + "." + " You seem better at this than I",
					"thought. Now that you have grasped basic swordplay",
					"let's move on.",
					c.talkingNpc, "Combat Instructor");
			
			c.nextChat = 3076;
			break;
			
		case 3076:
			DialogueHandler.sendNpcChat4(c, "Let's try some ranged attacking, with this you can kill",
					"foes from a distance. Also, foes unable to reach you are",
					"as good as dead. You'll be able to attack the rats",
					"without entering the pit.",
					c.talkingNpc, "Combat Instructor");
			
			c.nextChat = 3077;
			break;
			
		case 3077: // gives me bow and arrow
			DialogueHandler.sendItemChat2(c, "",
					"The Combat Guide gives you some @blu@bronze arrows@bla@ and",
					"a @blu@shortbow!",841, 300);
			c.getItems().addItem(841, 1);
			c.getItems().addItem(882, 50);
			c.nextChat = 0;
			chatboxText(c, "Now you have a bow and some arrows. Before you can use",
					"them you'll need to equip them. Remember: to attack, right",
					"click on the monster and select attack.",
					"" ,
					"Rat ranging");
			c.ratdied2 = true;
			c.getPA().drawHeadicon(1, 13, 0, 0); // draws headicon to rat			
			break;
			/*
			 * FINISH . Last parts, Finacial,prayer,magic
			 */
			
		case 3078: // fresh
			chatbox(c, 6180);
			chatboxText
			(c, "Follow the path and you will come to the front of the building.",
			"This is the Bank of Tyden, where you can store all your",
		     "most valued items. To open your bank box just right click on an",
		    "open booth indicated and select 'use'.",
			"Banking");
			chatbox(c, 6179);
			c.getPA().createArrow(3122, 3124, c.getHeightLevel(), 2);
			break;
			
		case 3079: //fiancial dude start
			DialogueHandler.sendPlayerChat1(c, "Hello. Who are you?");
			c.nextChat = 3080;		
			
			break;
			
		case 3080: 
			DialogueHandler.sendNpcChat2(c, "I'm the Financial Advisor. I'm here to tell people how to",
					"make money.", 
					c.talkingNpc, "Financial Advisor");
			c.nextChat = 3081;					
			break;
			
		case 3081:
			DialogueHandler.sendPlayerChat1(c, "Okay. How can I make money then?");
			c.nextChat = 3082;				
			break;
			
		case 3082:
			DialogueHandler.sendNpcChat1
			(c, "How you can make money? Quite.",
					c.talkingNpc, "Financial Advisor");
			c.nextChat = 3083;
			break;
			
		case 3083:
			DialogueHandler.sendNpcChat3
			(c, "Well there are three basic ways of making money here:",
					"combat, quests, and trading. I will talk you through each",
					"of them very quickly.",
					c.talkingNpc, "Financial Advisor");
			c.nextChat = 3084;
			break;
			
		case 3084:
			DialogueHandler.sendNpcChat3
			(c, "Let's start with combat as it is probably still fresh in",
					"your mind. Many enemies, both human and monster,",
					"will drop items when they die.",
					c.talkingNpc, "Financial Advisor");
			c.nextChat = 3085;			
			break;
			
		case 3085:
			DialogueHandler.sendNpcChat3
			(c, "Now, the next way to earn money quickly is by quests.",
					"Many people on Tyden 06 have things they need",
					"doing, which they will reward you for.",
					c.talkingNpc, "Financial Advisor");
			c.nextChat = 3086;			
			break;
			
		case 3086:
			DialogueHandler.sendNpcChat3
			(c, "By getting a high level in skills such as Cooking, Mining,",
					"Smithing or Fishing, you can create or catch your own",
					"items and sell them for pure profit.",
					c.talkingNpc, "Financial Advisor");
			c.nextChat = 3087;			
			break;
			
		case 3087:
			DialogueHandler.sendNpcChat2
			(c, "Well that about covers it. Come back if you'd like to go",
					"over this again.",
					c.talkingNpc, "Financial Advisor");
			c.nextChat = 3088;			
			break;
			
		case 3088: // end
			c.getPA().removeAllWindows();
			c.tutorialprog = 28;
			chatbox(c, 6180);
			chatboxText
			(c, "",
			"Continue through the next door.",
		     "",
		    "",
			"");
			chatbox(c, 6179);
			c.getPA().createArrow(3129, 3124, c.getHeightLevel(), 2);
			c.nextChat = 0;
			break;
			//end of FINANCIAL
			
		  /*
		   * Section Prayer
		   */
			
		case 3089: // start of dialogue
			DialogueHandler.sendPlayerChat1(c, "Good day, brother, my name's " +Misc.capitalize(c.playerName) + ".");
			c.nextChat = 3090;
			break;
			
		case 3090: 
			DialogueHandler.sendNpcChat2
			(c, "Hello, "+Misc.capitalize(c.playerName) + "." + " I'm Brother Brace. I'm here to",
					"tell you all about Prayer.",
					c.talkingNpc, "Brother Brace");
			c.nextChat = 3091;			
			
			break;
		case 3091:
			c.getPA().removeAllWindows();
			chatbox(c, 6180);
					chatboxText
					(c, "",
					"Click on the flashing icon to open the Prayer menu.",
				"",
				"",
							"Your Prayer menu");
					chatbox(c, 6179);
					c.setSidebarInterface(5, 5608);
				flashSideBarIcon(c, -5);
				c.tutorialprog = 29;
				c.nextChat = 0;			
			break;
			
		case 3092:
			DialogueHandler.sendNpcChat3
			(c, "This is your Prayer list. Prayers can help a lot in",
					"combat. Click on the prayer you wish to use to activate",
					"it and click it again to deactivate it.",
					c.talkingNpc, "Brother Brace");
			c.nextChat = 3093;
			
			break;
		case 3093:
			DialogueHandler.sendNpcChat3
			(c, "Active prayers will drain your Prayer Points which",
					"you can recharge by finding an altar or other holy spot",
					"and praying there.",
					c.talkingNpc, "Brother Brace");
			c.nextChat = 3094;			
			break;
			
		case 3094:
			DialogueHandler.sendNpcChat3
			(c, "As you noticed, most enemies will drop bones when",
					"defeated. Burying bones by clicking them in your",
					"inventory will gain you Prayer experience.",
					c.talkingNpc, "Brother Brace");
			c.nextChat = 3095;			
			break;
			
		case 3095:
			DialogueHandler.sendNpcChat2
			(c, "I'm also the community officer 'round here, so it's my",
					"job to tell you about your friends and ignore list.",
					c.talkingNpc, "Brother Brace");
			c.nextChat = 3096;			
			break;
			
		case 3096:
			c.getPA().removeAllWindows();
			chatbox(c, 6180);
					chatboxText
					(c, "You should now see another new icon. Click on the flashing",
					"icon to open your friends list.",
				"",
				"",
							"Friends list");
					chatbox(c, 6179);
					c.setSidebarInterface(8, 5065);
				flashSideBarIcon(c, -8);
				c.tutorialprog = 30;
				c.nextChat = 0;		
				break;
				
		case 3097: // long dialogue START
			Tutorialisland.TII(c, 75, 16);
			DialogueHandler.sendNpcChat4
			(c, "Good. Now you have both menus open, I'll tell you a",
					"little about each. You can add people to either list by",
					"clicking the add button then typing their name into the",
					"box that appears.",
					c.talkingNpc, "Brother Brace");
			c.nextChat = 3098;					
			break;
			
		case 3098: 
			DialogueHandler.sendNpcChat4
			(c, "You remove people from the lists in the same way. If",
					"you add someone to your ignore list they will not be",
					"able to talk to you or send any form of message to",
					"you.",
					c.talkingNpc, "Brother Brace");
			c.nextChat = 3099;		
			
			break;
		case 3099: 
			DialogueHandler.sendNpcChat4
			(c, "Your friends list shows the online status of your",
					"friends. Friends in the red are offline, friends in green are",
					"online and on the same server and friends in yellow",
					"are online but on a different server.",
					c.talkingNpc, "Brother Brace");
			c.nextChat = 3100;					
			break;
			
		case 3100:
DialogueHandler.sendPlayerChat1(c, "Are there rules on in-game behaviour?");
			c.nextChat = 3101;
			break;
		case 3101:
			DialogueHandler.sendNpcChat3
			(c, "Yes, you should read the rules of conduct on the",
					"website to make sure you do nothing to get yourself",
					"banned.",
					c.talkingNpc, "Brother Brace");
						c.nextChat = 3102;
						break;
		case 3102:
			DialogueHandler.sendNpcChat3
			(c, "But in general, always try to be courteous to other",
					"players - remember the people in the game are real",
					"people with real feelings.",
					c.talkingNpc, "Brother Brace");
						c.nextChat = 3103;
						break;
		case 3103:
			DialogueHandler.sendNpcChat2
			(c, "If you go 'round being abusive or causing trouble your",
					"character could end up being the one in trouble.",
					c.talkingNpc, "Brother Brace");
						c.nextChat = 3104;
						break;
		case 3104: // last one
			DialogueHandler.sendPlayerChat1(c, "Okay thanks. I'll bear that in mind.");
			chatbox(c, 6180);
			chatboxText
			(c, "You're almost finished on tutorial island. Pass through the",
			"door to find the path leading to your final instructor.",
		"",
		"",
					"Your final instructor!");
			rmHint(c);
			c.tutorialprog = 32;
			chatbox(c, 6179);
						c.nextChat = 0;
						break;
						//END BROTHER BRACE PRAYER
						
						
						
						/*
						 * Start magic instructor
						 */
						
		case 3105:
			DialogueHandler.sendPlayerChat1(c, "Hello.");
			c.nextChat = 3106;			
			break;
			
		case 3106:
			DialogueHandler.sendNpcChat3
			(c, "Good day, newcomer. My name is Terrova. I'm here",
					"to tell you about Magic. Let's start by opening your",
					"spell list.",
					c.talkingNpc, "Magic Instructor");
			c.nextChat = 3107;
			break;
			
		case 3107:
		DialogueHandler.sendItemChat1(c, "", "", 0, 50);
		c.getPA().removeAllWindows();
			chatbox(c, 6180);
			chatboxText
			(c, "",
			"Open up the Magic menu by clicking on the flashing icon next",
		"to the Prayer button you just learned about.",
		"",
					"Open up your final menu");
			chatbox(c, 6179);
						c.nextChat = 0;
						c.setSidebarInterface(6, 1151); //modern
						flashSideBarIcon(c, -6);
			
			break;
			
		case 3108:
			DialogueHandler.sendNpcChat3
			(c, "Good. This is a list of your spells. Currently you can",
					"only cast one offensive spell called Wind Strike. Let's",
					"try it out on one of those chickens.",
					c.talkingNpc, "Magic Instructor");
			c.nextChat = 3109;			
			break;
			
		case 3109:
			DialogueHandler.sendItemChat1(c, "",
					"Terrova gives you five @blu@air runes@bla@ and @blu@five mind runes!",556, 300);
			c.getItems().addItem(558, 5);
			c.getItems().addItem(556, 5);
			c.nextChat = 0;
			chatboxText(c, "Now you have runes you should see the Wind Strike icon at the",
					"top left corner of the Magic interface - second in from the",
					"left. Walk over to the caged chickens, click the Wind Strike icon",
					"and then select one of the chicken to cast it on." ,
					"Cast Wind Strke at a chicken");
			c.getPA().drawHeadicon(1, 24, 0, 0); // draws headicon to chicken			
			
			break;
		case 3110:
			DialogueHandler.sendNpcChat2
			(c, "Well you're all finished here now. I'll give you a",
					"reasonable number of runes when you leave.",
					c.talkingNpc, "Magic Instructor");
			c.nextChat = 3111;			
			break;
			
		case 3111:
			DialogueHandler.sendOption2(c, "Mainland", "Stay here");
			c.dialogueAction = 3111;
			c.nextChat = 0;
			break;
			
		case 3112:// Mainland
			c.tutorialprog = 35;
			DialogueHandler.sendNpcChat4
			(c, "When you get to the mainland you will find yourself in",
					"the town of Lumbridge. If you want some ideas on",
					"where to go next talk to my friend the Lumbridge",
				    "Guide. You can't miss him; he's holding a big staff with"
					,c.talkingNpc, "Magic Instructor");
			c.nextChat = 3113;			
			break;
		case 3113:
			DialogueHandler.sendNpcChat4
			(c, "a question mark on the end. He also has a white beard",
					"and carries a rucksack full of scrolls. There are also",
					"many tutors willing to teach you about the many skills",
				    "you could learn."
					,c.talkingNpc, "Magic Instructor");
			c.nextChat = 3114;						
			break;
			
		case 3114:
			DialogueHandler.sendNpcChat3
			(c, "If all else fails, visit the Tyden website for a whole",
					"chestload of information on quests, skills, and minigames",
					"as well as a very good starter's guide.",
				c.talkingNpc, "Magic Instructor");
			c.nextChat = 3115;						
			break;
	
			
///////////*******************************************************************///////////////
		case 3115://FINAL FINAL FINAL FINAL FINAL!!!!
		//TODO WARNING REMOVE ALL ITEMS FROM BANK DUPE BUG!!!
			c.getPA().movePlayer(3233, 3229, 0);
			c.getItems().deleteAllItems();
			c.xpLock = false;
			c.getPA().	walkableInterface(-1);
			Tutorialisland.rmHint(c);
			chatbox(c, -1);
			c.getPA().addStarter();
			DialogueHandler.sendStatement4(c, "Welcome to Lumbridge! To get more help, simply click on the", 
					"Lumbridge Guide or one of the Tutors - these can be found by", 
					"looking for the question mark icon on your mini-map. If you find",
					"you are lost at any time, look for a signpost.");					
			c.tutorialprog = 36;
		//	 c.getPA().showInterface(3559); // character design
		//		c.canChangeAppearance = true;
			c.nextChat = 0;//change later if tutorial island is added!
			break;
/////////////////***********************************************************************************/////////////			
		
		
		}

	}
	
	public static void handleOptions(Client c, int actionbuttonId) {
		switch (actionbuttonId) {
		case 9157:
			switch (c.dialogueAction) { 
			case 3111:
				sendDialogue(c,3112,946);
				break;

			}
			break;
		}


	}
	
	/*
	 * Methods
	 */
public static void rmHint(Client c) {
	c.getPA().drawHeadicon(0, 0, 0, 0);
}

	public static void removesideicons(Client c) {
		c.setSidebarInterface(1, -1);
		c.setSidebarInterface(2, -1);
		c.setSidebarInterface(3, -1);
		c.	setSidebarInterface(4, -1);
		c.	setSidebarInterface(5, -1);

		c.setSidebarInterface(6, -1); // ancient
		c.setSidebarInterface(7, -1);
		c.setSidebarInterface(8, -1);
		c.	setSidebarInterface(9, -1);
		c.	setSidebarInterface(11, -1); // wrench tab
		c.	setSidebarInterface(12, -1); // run tab
		c.	setSidebarInterface(13, -1);
		c.	setSidebarInterface(0, -1);
	}

	public static void TII(Client c, int p, int a) {
		c.getPA().sendFrame20(406, a);
		c.getPA().sendFrame171(1, 12224);
		c.getPA().	sendFrame171(1, 12225);
		c.getPA().	sendFrame171(1, 12226);
		c.getPA().	sendFrame171(1, 12227);
		c.getPA().	sendFrame126(""+ (p)+"%", 12224);
		c.getPA().	walkableInterface(8680);
	}

	public static void chatboxText(Client c,String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		//  c.getPA().sendFrame164(6179);
	}
	public static void clearchatboxText(Client c) {
		c.getPA().sendFrame126("", 6180);
		c.getPA().sendFrame126("", 6181);
		c.getPA().sendFrame126("", 6182);
		c.getPA().sendFrame126("", 6183);
		c.getPA().sendFrame126("", 6184);
	}


	public static void chatbox(Client c,int i1) {
		if(c.getOutStream() != null && c != null) {
			c.outStream.createFrame(218);
			c.outStream.writeWordBigEndianA(i1);
			c. updateRequired = true;
			c. appearanceUpdateRequired = true;
		}

	}
	public static void flashSideBarIcon(Client c,int i1) {
		// Makes the sidebar Icons flash
		// Usage: i1 = 0 through -12 inorder to work
		c. outStream.createFrame(24);
		c.  outStream.writeByteA(i1);
	}

}