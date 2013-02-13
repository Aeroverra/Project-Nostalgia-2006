package server.game.items.food;

import java.util.HashMap;

import core.util.Misc;

import server.game.players.Client;

/**
 * Food.java
 *
 * @author Izumi
 *
 */

public class Food {

	public static enum FoodToEat {
		MANTA(391,22,"Manta Ray"),
		SHARK(385,20,"Shark"),
		LOBSTER(379,12,"Lobster"),
		TROUT(333,7,"Trout"),
		SALMON(329,9,"Salmon"),
		SWORDFISH(373,14,"Swordfish"),
		TUNA(361,10,"Tuna"),
		MONKFISH(7946,16,"Monkfish"),
		SEA_TURTLE(397,22,"Sea Turtle"),
		TUNA_POTATO(7060,22,"Tuna Potato"),
		SARDINE(325,4,"Sardine"),
		HERRING(347,5,"Herring"),
		BASS(365,13,"Bass"),
		SHRIMPS(315,3,"Shrimps"),
		ANCHOVIES(319, 1, "Anchovies"),
		BREAD(2309,5,"Bread"),
		CABBAGE(1965,1,"Cabbage"),
		POTATO(1942,1,"Potato"),
		BANANA(1963,2,"Banana"),
		MEAT(2142,3,"Meat"),
		CHICKEN(2140, 3, "Chicken");


		private int id; private int heal; private String name;

		private FoodToEat(int id, int heal, String name) {
			this.id = id;
			this.heal = heal;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public int getHeal() {
			return heal;
		}

		public String getName() {
			return name;
		}
		public static HashMap <Integer,FoodToEat> food = new HashMap<Integer,FoodToEat>();

		public static FoodToEat forId(int id) {
			return food.get(id);
		}

		static {
			for (FoodToEat f : FoodToEat.values())
				food.put(f.getId(), f);
		}
	}

	public static void eat(Client c, int id, int slot) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(id,slot,1);
			FoodToEat f = FoodToEat.food.get(id);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += f.getHeal();
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
			}
			c.getPA().sound(317);
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			
			if (id == 1965) {
				c.sendMessage("You eat the cabbage. Yuck!");
			} else {
				c.sendMessage("You eat the " + f.getName().toLowerCase() + ".");
			}
		}
	}


	public static boolean isFood(int id) {
		return FoodToEat.food.containsKey(id);
	}
	
	
/**************************KEBABS****************************************/
	public static int Kebab = 1971;
	float chances = 0.0f;
	
	/**
	 * Chances(Percents)
     */
	public static float chances(String effect) {
		float chances = 0.0f;// PERCENT
		switch(effect) {
		case "effect1": // Nothing
			chances = 9.0f;
			break;
		case "effect2": //Normal 10% heal
			chances = 61.2f;
			break;
		case "effect3": // 10-20+ heal
			chances = 21.1f;
			break;
		case "effect4": // 0-30+ HP + other effects
			chances = 3.6f;
			break;
		case "effect5": // Bad kebab
			chances = 6.0f;
			break;
		default:
			chances = Float.parseFloat(effect);
			break;
		}
		return chances; //Equals 100.9%
}

	/**
	 * Different effects(Healing,lowering,damaging)
	 */
	public static void effects(Client c) {
		float eff1 = chances("effect1");
		float eff2 = chances("effect2");
		float eff3 = chances("effect3");
		float eff4 = chances("effect4");
		float eff5 = chances("effect5");
		 
		if (Misc.random(100.0f) <= eff1) { // 8.71%
			c.sendMessage("That kebab didn't seem to do a lot.");
			
		} else if (Misc.random(100.0f) <= eff2) { // 61.24% heals 10% of HP
			c.sendMessage("It restores some life points.");
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += (c.getLevelForXP(c.playerXP[3]) * 0.10);
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
				
			}
			
		} else if (Misc.random(100.0f) <= eff3) { // 21.12% + 10-20 HP
			c.sendMessage("That was a good kebab. You feel a lot better. ");
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += Misc.random(20);
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
			}
			
		} else if (Misc.random(100.0f) <= eff4) {// 3.65% + attk,str,def + 2-3 + heal 0-300
			c.sendMessage("Wow, that was an amazing kebab! You feel really invigorated.");
			c.playerLevel[1] += (2 +Misc.random(1)); // def
			c.playerLevel[2] += (2 +Misc.random(1)); // str
			c.playerLevel[0] += (2 +Misc.random(1)); // atk
			c.getPA().refreshSkill(1); c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += Misc.random(30);	
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
			}
			
		} else if (Misc.random(100.0f) <= eff5) {// 6.3%. lower STAT
			c.sendMessage("That tasted very dodgy. You feel very ill.");
			c.sendMessage("Eating the kebab has done damage to some of your stats.");
			for (int j = 0; j < 2; j++) {
			c.playerLevel[j] -= 2; // Fix this l0l
			c.getPA().refreshSkill(j);
			}
		}

	}
	
	/**
	 * Eatting the kebab
	 */
	public static void eatKebab(Client c, int slot) {
	if (System.currentTimeMillis() - c.foodDelay >= 1500) {
	if(c.playerLevel[3] == c.getLevelForXP(c.playerXP[3])) { // If full health, does nothing but eat.
			c.getCombat().resetPlayerAttack();
			c.sendMessage("You eat the kebab.");
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(Kebab, slot, 1);
			c.getPA().sound(317);
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			return;
		}
		c.getCombat().resetPlayerAttack();
		c.sendMessage("You eat the kebab.");
		effects(c);
		c.attackTimer += 2;
		c.startAnimation(829);
		c.getItems().deleteItem(Kebab, slot, 1);
		c.getPA().sound(317);
		c.foodDelay = System.currentTimeMillis();
		c.getPA().refreshSkill(3);
		}
	}

}