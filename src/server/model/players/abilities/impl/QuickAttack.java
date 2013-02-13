package server.model.players.abilities.impl;

import server.game.players.Client;
import server.model.players.abilities.Ability;

/**
 * Quick Attack Ability. First ability to be unlocked and made.(basic)
 * Description: Reduces your attack delay in half.
 * 
 * @author
 * 
 */
public class QuickAttack extends Ability {

	@Override
	public int reqSkillLevel() {
		return 1;
	}

	@Override
	public String abilityType() {
		return "Melee";
	}

	@Override
	public void destroy() {
		//nothing
	}

	@Override
	public void execute(Client c) {
		c.attackTimer /= 2;
	}

}
