package server.game.npcs;


/**
 * @author somedude f2p.
 */
public class NPCAggressive {
	/**
	 * Aggressive formula = RS. if (npccombat*2) > player.combat
	 * 
	 * @param i
	 * @return
	 */

	public static boolean isAggressive(int i) {
		String npc = GetNpcName(i).toLowerCase();
		if (npc.equals("lesser_demon") || npc.equals("Black_Heather")
				|| npc.equals("black_knight") || npc.equals("dark_wizard")
				|| npc.equals("deadly_red_spider") || npc.equals("ghost")
				|| npc.equals("giant_rat") || npc.equals("greater_demon")
				|| npc.equals("hill_giant") || npc.equals("hobgoblin")
				|| npc.equals("ice_giant") || npc.equals("ice_warrior")
				|| npc.equals("jail_guard") || npc.equals("pirate")
				|| npc.equals("scorpion") || npc.equals("skeleton")
				|| npc.equals("highwayman") || npc.equals("mugger") ||
				npc.equals("giant_spider") || npc.equals("monk_of_zamorak")) {
			return true;
		}
		switch (i) {
		case 112: // mossys
		case 195:// bandits lolol
		case 196:// bandits l0l0l
		case 74:// zombie
		case 75:// zombie
		case 76:// zombies
			return true;
		}
		return false;
	}

	/**
	 * Gets the npc NAME
	 * @param NpcID
	 * @return
	 */
	public static String GetNpcName(int NpcID) {
		return getNpcListName(NpcID);
	}
	/**
	 * handles npc name in class now
	 * @param npcId
	 * @return
	 */
	public static String getNpcListName(int npcId) {
		for (NPCList i : NPCHandler.NpcList) {
			if (i != null) {
				if (i.npcId == npcId) {
					return i.npcName;
				}
			}
		}
		return "nothing";
	}
}
