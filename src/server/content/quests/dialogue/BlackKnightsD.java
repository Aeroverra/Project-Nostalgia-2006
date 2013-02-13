package server.content.quests.dialogue;

import server.game.players.Client;
import server.game.players.DialogueHandler;


/**
 * Made by someotherdude
 */
public class BlackKnightsD {

	/**
	 * Black Knight's fortress dialogue
	 * 
	 * @param c
	 * @param dialogue
	 * @param npcId
	 */
	public static void dialogue(Client c, int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		case 4010:
			DialogueHandler.sendNpcChat2(c, "I am the leader of the White Knights of Falador. Why" , "do you seek my audience?" , c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4011;
			break;
		case 4011:
			DialogueHandler.sendOption2(c, "I seek a quest!" , "I don't, I'm just looking around." );
			c.dialogueAction = 4011;
			break;
		case 4012: 
			DialogueHandler.sendPlayerChat1(c, "I don't, I'm just looking around.");
			c.nextChat = 4013;
			break;
		case 4013: 
			DialogueHandler.sendNpcChat1(c, "Well if you need anything I'll be right here.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 0;
			break;
		case 4014: 
			DialogueHandler.sendPlayerChat1(c, "I seek a quest");
			c.nextChat = 4015;
			break;
		case 4015:
			DialogueHandler.sendNpcChat3(c, "Well, I need some spy work doing but it's quite" , "dangerous. It will involve going into the Black Knights'" , "fortress." , c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4016;
			break;
		case 4016:
			DialogueHandler.sendOption2(c, "I laugh in the face of danger!" , "I run and cower at the first sight of danger!" );
			c.dialogueAction = 4016;
			break;
		case 4017: 
			DialogueHandler.sendPlayerChat1(c, "I run and cower at the first sight of danger!");
			c.nextChat = 4018;
			break;
		case 4018: 
			DialogueHandler.sendNpcChat2(c, "Then this task is surely not suited for you." , "Come back when you are ready for danger.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 0;
			break;
		case 4019: 
			DialogueHandler.sendNpcChat1(c, "Well that's good. Don't get too overconfident though.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4020;
			break;
		case 4020: 
			DialogueHandler.sendNpcChat2(c, "You've come along at just the right time actually. All of" , "my knights are already known to the Black Knights.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4021;
			break;

			case 4021: 
			DialogueHandler.sendNpcChat1(c, "Subtlety isn't exactly our strong point.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4022;
			break;

			case 4022: 
			DialogueHandler.sendPlayerChat2(c, "Why don't you just take your White Knights' armor off?" , "They wouldn't recognize you then!");
			c.nextChat = 4023;
			break;

			case 4023: 
			DialogueHandler.sendNpcChat2(c, "I'm afraid our charter prevents us using espionage in" , "any form, that is the domain of the Temple Knights.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4024;
			break;

			case 4024: 
			DialogueHandler.sendPlayerChat1(c, "Temple Knights? Who are they?");
			c.nextChat = 4025;
			break;

			case 4025: 
			DialogueHandler.sendNpcChat2(c, "That information is classified. I am forbidden to share it" , "with outsiders.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4026;
			break;

			case 4026: 
			DialogueHandler.sendPlayerChat1(c, "So, what do you need doing?");
			c.nextChat = 4027;
			break;

			case 4027: 
			DialogueHandler.sendNpcChat4(c, "Well, the Black Knights have started making strange" , "threats to us; demanding large amounts of money and" , "land, and threatening to invade Falador if we don't pay" , "them.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4028;
			break;

			case 4028: 
			DialogueHandler.sendNpcChat1(c, "Now, NORMALLY this wouldn't be a problem...", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4029;
			break;

			case 4029: 
			DialogueHandler.sendNpcChat1(c, "But they claim to have a powerful new secret weapon.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4030;
			break;

			case 4030: 
			DialogueHandler.sendNpcChat3(c, "Your mission, should you decide to accept it, is to" , "infiltrate their fortress, find out what their secret" , "weapon is, and then sabotage it.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 4031;
			break;

			case 4031:
			DialogueHandler.sendOption2(c, "Ok, I'll do my best." , "No, I'm not ready to do that.");
			c.dialogueAction = 4031;
			break;

			case 4032:
			DialogueHandler.sendPlayerChat1(c, "No, I'm not ready to do that.");
			c.nextChat = 4033;
			break;

			case 4033:
			DialogueHandler.sendNpcChat1(c, "Very well then, come back when you are ready", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 0;
			break;

			case 4034:
			DialogueHandler.sendPlayerChat1(c, "Ok, I'll do my best.");
			c.blackK++; //1
			c.getPA().loadQuests();
			c.nextChat = 4035;
			break;

			case 4035: 
			DialogueHandler.sendNpcChat2(c, "Good luck! Let me know how you get on. Here's the" , "dossier for the case, I've already given you the details.", c.talkingNpc, "Sir Amik Varze");
			c.nextChat = 0;
			break;

			case 4036:
			DialogueHandler.sendNpcChat1(c, "So� how's the secret weapon coming along?" , c.talkingNpc, "Black Knight Captain");
			c.nextChat = 4037;
			break;

			case 4037:
			DialogueHandler.sendNpcChat1(c, "The invincibility potion is almost ready" , c.talkingNpc, "Witch");
			c.nextChat = 4038;
			break;

			case 4038:
			DialogueHandler.sendNpcChat1(c, "It's taken me FIVE YEARS, but it's almost ready." , c.talkingNpc, "Witch");
			c.nextChat = 4039;
			break;

			case 4039:
			DialogueHandler.sendNpcChat2(c, "Greldo, the goblin here, is just going to fetch the last" , "ingredient for me." , c.talkingNpc, "Witch");
			c.nextChat = 4040;
			break;

			case 4040:
			DialogueHandler.sendNpcChat2(c, "It's a special cabbage grown by my cousin Helda, who" , "lives in Draynor Manor." , c.talkingNpc, "Witch");
			c.nextChat = 4041;
			break;

			case 4041:
			DialogueHandler.sendNpcChat2(c, "The soil there is slightly magical and it gives the" , "cabbages slight magical properties..." , c.talkingNpc, "Witch");
			c.nextChat = 4042;
			break;

			case 4042:
			DialogueHandler.sendNpcChat1(c, "�not to mention the trees!" , c.talkingNpc, "Witch");
			c.nextChat = 4043;
			break;

			case 4043:
			DialogueHandler.sendNpcChat3(c, "Now, remember, Greldo, only a Draynor Manor" , "cabbage will do! Don't get lazy and bring any old" , "cabbage! THAT would ENTIRELY wreck the potion!" , c.talkingNpc, "Witch");
			c.nextChat = 4044;
			break;

			case 4044:
			DialogueHandler.sendNpcChat1(c, "Yeth, mithtreth." , c.talkingNpc, "Greldo");
			c.nextChat = 0;
			break;

			case 4045:
			DialogueHandler.sendNpcChat3(c, "I wouldn't go in there if I were you. Those Black" , "Knights are in an important meeting. They said they'd" , "kill anyone who went in there!" , c.talkingNpc, "Fortress Guard");
			c.nextChat = 4046;
			break;

			case 4046:
			DialogueHandler.sendOption2(c, "Okay, I won't." , "I don't care. I'm going in anyway.");
			c.dialogueAction = 4046;
			break;

			case 4047:
			DialogueHandler.sendPlayerChat1(c, "Okay, I won't.");
			c.nextChat = 0;
			break;

		}
	}
}
	
