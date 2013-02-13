package server.content.skills;

import server.Config;
import server.content.quests.misc.Tutorialisland;
import server.content.skills.misc.SkillHandler;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;
import core.util.Misc;

	/**
	 * Class Cooking
	 * Handles Cooking
	 * @author 2012
	 * START: 20:13 25/10/2010
	 * FINISH: 20:21 25/10/2010
	 */
 
public class Cooking extends SkillHandler {

	public static void cookThisFood(Client p, int i, int object) {
		switch(i) {
			case 317:	cookFish(p, i, 30, 1, 323, 315, object); break;
			case 321:	cookFish(p, i, 30, 1, 323, 319, object); break;
			case 327: 	cookFish(p, i, 40, 1, 367, 325, object); break;
			case 345: 	cookFish(p, i, 50, 5, 357, 347, object); break;
			case 353: 	cookFish(p, i, 60, 10, 357, 355, object); break;
			case 335: 	cookFish(p, i, 70, 15, 343, 333, object); break;
			case 341: 	cookFish(p, i, 75, 18, 343, 339, object); break;
			case 349: 	cookFish(p, i, 80, 20, 343, 351, object); break;
			case 331: 	cookFish(p, i, 90, 25, 343, 329, object); break;
			case 359: 	cookFish(p, i, 100, 30, 367, 361, object); break;
			case 361: 	cookFish(p, i, 100, 30, 367, 365, object); break;
			case 377: 	cookFish(p, i, 120, 40, i + 4, i + 2, object); break;
			case 371: 	cookFish(p, i, 140, 45, i + 4, i + 2, object); break;
			case 383: 	cookFish(p, i, 210, 80, i + 4, i + 2, object); break;
			case 395: 	cookFish(p, i, 212, 82, i + 4, i + 2, object); break;
			case 389: 	cookFish(p, i, 216, 91, i + 4, i + 2, object); break;
			case 7944: 	cookFish(p, i, 150, 62, i + 4, i + 2, object); break;
			default: 	p.sendMessage("Nothing interesting happens."); break;
		}
	}

	private static int fishStopsBurning(int i) {
		switch (i) {
			case 317: return 34;
			case 321: return 34;
			case 327: return 38;
			case 345: return 37;
			case 353: return 45;
			case 335: return 50;
			case 341: return 39;
			case 349: return 52;
			case 331: return 58;
			case 359: return 63;
			case 377: return 74;
			case 363: return 80;
			case 371: return 86;
			case 7944: return 90;
			case 383: return 94;
			default: return 99;
		}
	}

	private static void cookFish(Client c, int itemID, int xpRecieved, int levelRequired, int burntFish, int cookedFish, int object) {
		if(!Config.COOKING_ENABLED) {
			c.sendMessage("Cooking is currently disabled.");
			return;
		}
		if(!hasRequiredLevel(c, 7, levelRequired, "cooking", "cook this")){
			return;
		}
		int chance = c.playerLevel[7];
		if(c.playerEquipment[c.playerHands] == 775) {
			chance = c.playerLevel[7] + 8; 
		}
		if(chance <= 0) {
			chance = Misc.random(5);
		}
    		c.playerSkillProp[7][0] = itemID;
		c.playerSkillProp[7][1] = xpRecieved;
		c.playerSkillProp[7][2] = levelRequired;
		c.playerSkillProp[7][3] = burntFish;
		c.playerSkillProp[7][4] = cookedFish;
		c.playerSkillProp[7][5] = object;
		c.playerSkillProp[7][6] = chance;
		c.stopPlayerSkill = false;
		int item = c.getItems().getItemAmount(c.playerSkillProp[7][0]);
		if(item == 1) {
			c.doAmount = 1;
			cookTheFish(c);
			return;
		}
		viewCookInterface(c, itemID);
	}

	public static void getAmount(Client c, int amount) {
		int item = c.getItems().getItemAmount(c.playerSkillProp[7][0]);
		if(amount > item) {
			amount = item;
		}
		c.doAmount = amount;
		cookTheFish(c);
	}

	public static void resetCooking(Client c) {
		c.playerSkilling[7] = false;
		c.stopPlayerSkill = false;
		for(int i = 0; i < 6; i++) {
			c.playerSkillProp[7][i] = -1;
		}
	}

	private static void viewCookInterface(Client c, int item) {
        	c.getPA().sendFrame164(1743);
         	c.getPA().sendFrame246(13716, 190, item);
     		c.getPA().sendFrame126(""+c.getItems().getItemName(item)+"", 13717);
	}

	private static void cookTheFish(final Client c) {
		if(c.playerSkilling[7]) {
			return;	
		}
		if(c.tutorialprog == 6) {
			c.playerSkilling[7] = true;
			c.stopPlayerSkill = true;
			c.getPA().removeAllWindows();
			if(c.playerSkillProp[7][5] > 0) {
				//c.startAnimation(c.playerSkillProp[7][5] == 2732 ? 897 : 896);
				c.startAnimation(c.playerSkillProp[7][5] == 2732 ? 897 :
						c.playerSkillProp[7][5] == 12269 ? 897
						: 896);
		
			}
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					c.getItems().deleteItem(c.playerSkillProp[7][0], c.getItems().getItemSlot(c.playerSkillProp[7][0]), 1);
					if(c.Cookstage1 == 1) {
						Tutorialisland.chatbox(c, 6180);
						Tutorialisland.chatboxText
						(c, "You have just burned your first shrimp. This is normal. As you",
								"get more experience in Cooking, you will burn stuff less often.",
								"Let's try cooking without burning it this time. First catch some",
								"more shrimp then use them on a fire.",
								"Burning your shrimp.");
						Tutorialisland.chatbox(c, 6179);
						c.Cookstage1 = 0;
						c.getItems().addItem(c.playerSkillProp[7][3], 1);					
					} else {
						Tutorialisland.chatbox(c, 6180);
						Tutorialisland.chatboxText
						(c, "If you'd like a recap on anything you've learnt so far, speak to",
								"the Survival Expert. You can now move on to the next",
								"instructor. Click on the gate shown and follow the path.",
								"Remember, you can move the camera with the arrow keys.",
								"Well done, you've just cooked your first RuneScape meal");
						Tutorialisland.chatbox(c, 6179);
						c.getPA().createArrow(3089, 3092, c.getHeightLevel(), 2);
						c.getPA().addSkillXP(c.playerSkillProp[7][1], 7);
						c.getItems().addItem(c.playerSkillProp[7][4], 1);	
						c.tutorialprog = 7;
					}
					deleteTime(c);
					if(!c.getItems().playerHasItem(c.playerSkillProp[7][0], 1) || c.doAmount <= 0) {
						container.stop();
					}
					if(!c.stopPlayerSkill) {
						container.stop();
					}
				}
				@Override
				public void stop() {
					resetCooking(c);
				}
			}, 4);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if(c.playerSkillProp[7][5] > 0) {
						c.getPA().sendSound(357, 100, 1); // cook sound
						c.startAnimation(c.playerSkillProp[7][5] == 2732 ? 897 : 896);
					}
					if(!c.stopPlayerSkill) {
						container.stop();
					}
				}
				@Override
				public void stop() {

				}
			}, 4);
			return;	
		}
		
		
		/// REAL COOKING
		c.getPA().sendSound(357, 100, 1); // cook sound
		c.playerSkilling[7] = true;
		c.stopPlayerSkill = true;
		c.getPA().removeAllWindows();
		if(c.playerSkillProp[7][5] > 0) {
			c.startAnimation(c.playerSkillProp[7][5] == 2732 ? 897 :
				c.playerSkillProp[7][5] == 5499 ? 897
				: 896);
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if(c.playerIsFiremaking || c.playerSkilling[8] || c.playerSkilling[10]) { // woodcut and firemake and fish
					Cooking.resetCooking(c);
					}
				c.getItems().deleteItem(c.playerSkillProp[7][0], c.getItems().getItemSlot(c.playerSkillProp[7][0]), 1);
				if(c.playerLevel[7] >= fishStopsBurning(c.playerSkillProp[7][0]) || Misc.random(c.playerSkillProp[7][6]) > Misc.random(c.playerSkillProp[7][2])) {
					c.sendMessage("You successfully cook the "+ c.getItems().getItemName(c.playerSkillProp[7][0]).toLowerCase() +".");
					c.getPA().addSkillXP(c.playerSkillProp[7][1], 7);
					c.getItems().addItem(c.playerSkillProp[7][4], 1);
				} else {
					c.sendMessage("You accidentally burnt the "+ c.getItems().getItemName(c.playerSkillProp[7][0]).toLowerCase() +"!");
					c.getItems().addItem(c.playerSkillProp[7][3], 1);
				}
				deleteTime(c);
				if(!c.getItems().playerHasItem(c.playerSkillProp[7][0], 1) || c.doAmount <= 0) {
					container.stop();
				}
				if(!c.stopPlayerSkill) {
					container.stop();
				}
			}
			@Override
			public void stop() {
				resetCooking(c);
			}
		}, (int) 4.3);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if(c.playerSkillProp[7][5] > 0) {
					c.getPA().sendSound(357, 100, 1); // cook sound
					c.startAnimation(c.playerSkillProp[7][5] == 2732 ? 897 :
						c.playerSkillProp[7][5] == 5499 ? 897
						: 896);
				}
				if(!c.stopPlayerSkill) {
					container.stop();
				}
			}
			@Override
			public void stop() {

			}
		}, 4);
	}
}