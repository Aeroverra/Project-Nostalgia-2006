package server.content;

import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;
import server.game.players.Client;
import server.game.players.DialogueHandler;

public class Sailing {

	private static final int[][] TRAVEL_DATA = { 
		{}, // 0 - Null
		{2834, 3335, 8750}, // 1 - From Port Sarim to Entrana
		{3048, 3234, 8750}, // 2 - From Entrana to Port Sarim
		{2853, 3237, 7000}, // 3 - From Port Sarim to Crandor
		{2834, 3335, 8000}, // 4 - From Crandor to Port Sarim
		{2956, 3146, 4500}, // 5 - From Port Sarim to Karajama
		{3029, 3217, 4500}, // 6 - From Karajama to Port Sarim
		{2772, 3234, 2000}, // 7 - From Ardougne to Brimhaven
		{3029, 3217, 2000}, // 8 - From Brimhaven to Ardougne
		{}, // 9 - Null
		{}, // 10 - Null
		{2998, 3043, 14000}, // 11 - From Port Khazard to Ship Yard
		{2676, 3170, 14000}, // 12 - From Ship Yard to Port Khazard
		{2998, 3043, 10000}, // 13 - From Cairn Island to Ship Yard
		{2659, 2676, 7000}, // 14 - From Port Sarim to Pest Control
		{3041, 3202, 7000}, // 15 - From Pest Control to Port Sarim
		{2763, 2956, 6000}, // 16 - To Cairn Isle from Feldip Hills
	};

	public static void startTravel(final Client player, final int i) {
		player.getPA().showInterface(3281);
		player.getPA().sendFrame36(75, i);
		player.getPA().movePlayer(1, 1, 0);
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer e) {
				player.getPA().movePlayer(getX(i), getY(i), 0);
				e.stop();
			}
		}, getTime(i) - 400);
		
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer e) {
				player.getPA().sendFrame36(75, -1);
				player.getPA().closeAllWindows();
				e.stop();
			}
		}, getTime(i));
	}

	public static int getX(int i) {
		return TRAVEL_DATA[i][0];
	}

	public static int getY(int i) {
		return TRAVEL_DATA[i][1];
	}
	
	public static int getTime(int i) {
		return TRAVEL_DATA[i][2];
	}
	
	public static void sailingDia(Client c, int dialogue, int npcid) {
		c.talkingNpc = npcid;
		switch(dialogue) {
		/*
		 * Travel to Karamja
		 */
				case 900:
				DialogueHandler.sendNpcChat1(c, "The trip to Karamja will cost you 30 coins.", c.talkingNpc, "Seaman Thresnor");
					c.nextChat = 903;
					break;
				case 901:
					DialogueHandler.sendNpcChat1(c, "The trip to Karamja will cost you 30 coins.", c.talkingNpc, "Seaman Lorris");
					c.nextChat = 903;
					break;
				case 902:
					DialogueHandler.sendNpcChat1(c, "The trip to Karamja will cost you 30 coins.", c.talkingNpc, "Captain Tobias");
					c.nextChat = 903;
					break;
					
				case 903:
					DialogueHandler.sendOption2(c, "Yes please.","No, thank you.");
					c.dialogueAction = 903;
					break;
					
				case 904:
					DialogueHandler.sendPlayerChat1(c, "Yes please.");
					c.nextChat = 905;
						//c.nextChat = 906;
					break;
				case 906:
					c.nextChat = 0;
					DialogueHandler.sendNpcChat1(c, "You don't have enough coins...", c.talkingNpc, "");
					break;
					
				case 905:
					c.getItems().deleteItem(995, 30);
					startTravel(c, 5);// travel to kramaja			
					break;
					/*
					 * Travel to port shrim
					 */
				case 907:
					DialogueHandler.sendNpcChat1(c, "The trip back will cost you 30 coins.", c.talkingNpc, "Customs Officer");
					c.nextChat = 908;
					break;
				case 908: // customoffrcer. Back to Port shrim
					DialogueHandler.sendOption2(c, "Yes please.","No, thank you.");
					c.dialogueAction = 908;
					break;
					
				case 909: // customoffrcer. Back to Port shrim
					DialogueHandler.sendPlayerChat1(c, "Yes please.");
					c.nextChat = 910;
					break;
					
				case 910:
					c.getItems().deleteItem(995, 30);
					startTravel(c, 6);// travel to kramaja		
					break;
		}
	}

	public static void handleOptions(Client c, int actionbuttonId) {
		switch (actionbuttonId) { // new dialogue action handler
		case 9157:
			switch (c.dialogueAction) {
			case 903: // to karamaja
				if(c.getItems().playerHasItem(995, 30))
				sailingDia(c,904,c.talkingNpc);
				else
					sailingDia(c,906,c.talkingNpc);
				break;
		
			case 908: // to port shirm
				if(c.getItems().playerHasItem(995, 30))
					sailingDia(c,909,c.talkingNpc);
				else
					sailingDia(c,906,c.talkingNpc);
					
				break;
			}
			break;

		
		}
	}

}