package server.game.players;

import server.content.quests.misc.Tutorialisland;


public class ClickTab implements PacketType {

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		switch(packetSize) {
		case 1:// first part.
			if(c.tutorialprog == 0) { // wrentch
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "On the side panel you can now see a variety of options from",
						"changing your graphic settings and audio and music volume",
						"to selecting wether your player should accept help from",
						"other players. Don't worry about these too much for now.",
						"@blu@Player controls");
				Tutorialisland.chatbox(c, 6179);
				c.tutorialprog = 1;
			} else if(c.tutorialprog == 3) { // backpack
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "You can click on the backpack icon at any time to view the",
						"items that you currently have in your inventory. You will see",
						"that you now have an axe in your inventory. Use this to get",
						"some logs by clicking on one of the trees in the area.",
						"Cut down a tree");
				c.getPA().createArrow(3099, 3095, c.getHeightLevel(), 2);
				Tutorialisland.chatbox(c, 6179);
				
			} else if(c.tutorialprog == 4) { // Skills tab
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "Here you will see how good your skills are. As you move your",
						"mouse over any of the icons in this panel, the small yellow",
						"popup box will show you the exact amount of experience you",
						"have and how much is needed to get to the next level.",
						"Your skill stats");
				c.tutorialprog = 5;
				Tutorialisland.chatbox(c, 6179);
			} else if(c.tutorialprog == 9) { //Music tab
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "From this interface you can control the music that is played.",
						"As you explore the world, more of the tunes will become",
						"unlocked. Once you've examined this menu use the next door",
						"to continue. If you need a recap, talk to the Master Chef",
						"The music player");
				c.getPA().createArrow(3073, 3090, c.getHeightLevel(), 2);
				c.tutorialprog = 10;
				Tutorialisland.chatbox(c, 6179);
			} else if(c.tutorialprog == 10) { // Emotes aNd running
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "For those situations where words don't quite describe how you",
						"feel, try an emote. Go ahead, try one out! You might notice",
						"that some of the emotes are grey and cannot be used now.",
						"As you progress further into the game you'll gain more.",
						"Emotes");
				Tutorialisland.chatbox(c, 6179);
			} else if(c.tutorialprog == 12) { // Quest Tab
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "",
						"This is your Quest Journal, a list of all the quests in the game.",
						"Talk to the Quest Guide again for an explaination.",
						"",
						"Your Quest Journal");
				c.tutorialprog = 13;
				Tutorialisland.chatbox(c, 6179);
			} else if(c.tutorialprog == 21 || c.tutorialprog == 22) { // Worn inventory
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "You can see what items you are wearing in the worn inventory",
						"to the left of the screen with their combined statistics on the",
						"right. Let's add something. Left click your dagger to 'wield' it.",
						"",
						"Worn interface");
				Tutorialisland.chatbox(c, 6179);
				c.tutorialprog = 22;
			} else if(c.tutorialprog == 23) { // Attack syle tabs
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "From this interface you can select the type of attack your",
						"character will use. Different monsters have different",
						"weaknesses. If you hover your mouse over the buttons, you",
						"will see the type of XP you will receive when using each type of attack.",
						"This is your combat interface");
				c.tutorialprog = 24;
				Tutorialisland.chatbox(c, 6179);
				c.getPA().createArrow(3111, 9518, c.getHeightLevel(), 2);
			} else if(c.tutorialprog == 29) { // Prayer
				Tutorialisland.sendDialogue(c, 3092, 222);
			} else if(c.tutorialprog == 30) { // friends tab
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "This will be explaing by Brother Brace shortly, but first click",
						"on the other flashing face to the right of your screen.",
						"",
						"",
						"This is your friends list");
				c.setSidebarInterface(9, 5715);
				Tutorialisland.flashSideBarIcon(c, -9);
				c.tutorialprog = 31;
				Tutorialisland.chatbox(c, 6179);
				
			} else if(c.tutorialprog == 31) { // ignores tab
				Tutorialisland.chatbox(c, 6180);
				Tutorialisland.chatboxText
				(c, "The two lists - friends and ignore - can be very helpful for",
						"keeping track of when your friends are online or for blocking",
						"messages from people you simply don't like. Speak with",
						"Brother Brace and he will tell you more.",
						"This is your ignore list");
				Tutorialisland.chatbox(c, 6179);
				
			} else if(c.tutorialprog == 32) { // Final magic tab
				c.tutorialprog = 33;
				Tutorialisland.sendDialogue(c, 3108, 946);
			}
			
			break;

		}

	}

}
