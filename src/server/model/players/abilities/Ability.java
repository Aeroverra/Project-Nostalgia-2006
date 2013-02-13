package server.model.players.abilities;

import server.game.players.Client;

/**
 * Represents an ability.
 * 
 * @author bob. Creative idea, inspired by eoc
 * 
 */
public abstract class Ability {

	public abstract int reqSkillLevel();

	public abstract String abilityType();

	public final static String combatMessage = "You need to be in combat to use this ability.";

	public int getReqLvl() {
		return reqSkillLevel();
	}

	public String getAbilityType() {
		return abilityType();
	}

	/**
	 * Executes the ability's functions
	 */
	public abstract void execute(Client c);

	/**
	 * Executes actions on destruction of the ability
	 */
	public abstract void destroy();

	public static void activateAbility(Client c, Ability ability) {
		if (ability.getAbilityType().equals("Melee")) {
			if (c.playerLevel[0] < ability.getReqLvl()) {
				c.sendMessage("You need an attack level of "
						+ ability.getReqLvl() + " to activate this ability.");
				return;
			}
			if (c.attackTimer <= 0) {
				c.sendMessage(combatMessage);
				return;
			}
			ability.execute(c);
			
		}
		c.sendMessage("Ability Executed.");
	}
}
