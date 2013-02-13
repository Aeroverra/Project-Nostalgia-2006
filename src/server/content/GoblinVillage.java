package server.content;

import server.Config;
import server.game.npcs.NPCHandler;
import core.util.Misc;

/**
 * NPC VS NPC
 * 
 * @author somedude
 * 
 */
public class GoblinVillage extends NPCHandler {
	public static final int RED_GOBLIN = 299;
	public static final int GREEN_GOBLIN = 298;
	public static final String[] GREEN_TEAM_MESSAGES = { "Green!",
			"Stupid reddie!", "Green not red!", "Red armour stupid!",
			"Green armour best!" };
	public static final String[] RED_TEAM_MESSAGES = { "Red!",
			"Stupid greenie!", "Red not green!", "Green armour stupid!",
			"Red armour best!" };

	public static void updateGoblinFights() {
		followNpc(Misc.random(1, 8), Misc.random(8, 15));
		attackNpc(Misc.random(1, 8), Misc.random(8, 15));
	}

	public static void attackNpc(int a, int b) {

		if (npcs[a] != null && npcs[b] != null) {
			if (npcs[a].isDead || npcs[b].isDead) {
				return;
			}
			if (npcs[a].heightLevel != npcs[b].heightLevel) {
				return;
			}
			npcs[a].turnNpc(npcs[b].absX, npcs[b].absY);
			if (goodDistance1(npcs[a].getX(), npcs[a].getY(), npcs[b].getX(),
					npcs[b].getY(), distanceRequired1(a))) {
				npcs[a].attackTimer = getNpcDelay(a);
				npcs[a].hitDelayTimer = getHitDelay(a);
				npcs[a].attackType = 0;
				npcs[b].underAttackByNpc = true;
				npcs[b].attackingNpc = a;
				npcs[a].randomWalk = false;
				npcs[b].randomWalk = false;
				startAnimation(getAttackEmote(a), a);
			}
		}
	}

	public static void applyNpcDamage(int npc) {
		int damage = Misc.random(npcs[npc].maxHit);
		NPCHandler.npcs[npcs[npc].attackingNpc].hitDiff = damage;
		NPCHandler.npcs[npcs[npc].attackingNpc].HP -= damage;
		NPCHandler.npcs[npcs[npc].attackingNpc].hitUpdateRequired = true;
		NPCHandler.npcs[npcs[npc].attackingNpc].updateRequired = true;
	}

	/**
	 * NPC VS NPC
	 * 
	 * @param a
	 * @param b
	 */
	public static void followNpc(int a, int b) {
		if (npcs[b] == null || npcs[b].isDead) {
			return;
		}
		int npcX = npcs[b].absX;
		int npcY = npcs[b].absY;
		npcs[a].randomWalk = false;
		if (goodDistance1(npcs[a].getX(), npcs[a].getY(), npcX, npcY,
				distanceRequired1(a)))
			return;
		if ((npcs[a].spawnedBy > 0)
				|| ((npcs[a].absX < npcs[a].makeX + Config.NPC_FOLLOW_DISTANCE)
						&& (npcs[a].absX > npcs[a].makeX
								- Config.NPC_FOLLOW_DISTANCE)
						&& (npcs[a].absY < npcs[a].makeY
								+ Config.NPC_FOLLOW_DISTANCE) && (npcs[a].absY > npcs[a].makeY
						- Config.NPC_FOLLOW_DISTANCE))) {
			if (npcs[a].heightLevel == npcs[b].heightLevel) {
				if (npcs[a] != null && npcs[b] != null) {
					if (npcY < npcs[a].absY) {
						npcs[a].moveX = GetMove1(npcs[a].absX, npcX);
						npcs[a].moveY = GetMove1(npcs[a].absY, npcY);
					} else if (npcY > npcs[a].absY) {
						npcs[a].moveX = GetMove1(npcs[a].absX, npcX);
						npcs[a].moveY = GetMove1(npcs[a].absY, npcY);
					} else if (npcX < npcs[a].absX) {
						npcs[a].moveX = GetMove1(npcs[a].absX, npcX);
						npcs[a].moveY = GetMove1(npcs[a].absY, npcY);
					} else if (npcX > npcs[a].absX) {
						npcs[a].moveX = GetMove1(npcs[a].absX, npcX);
						npcs[a].moveY = GetMove1(npcs[a].absY, npcY);
					} else if (npcX == npcs[a].absX || npcY == npcs[a].absY) {
						int o = Misc.random(3);
						switch (o) {
						case 0:
							npcs[a].moveX = GetMove1(npcs[a].absX, npcX);
							npcs[a].moveY = GetMove1(npcs[a].absY, npcY + 1);
							break;

						case 1:
							npcs[a].moveX = GetMove1(npcs[a].absX, npcX);
							npcs[a].moveY = GetMove1(npcs[a].absY, npcY - 1);
							break;

						case 2:
							npcs[a].moveX = GetMove1(npcs[a].absX, npcX + 1);
							npcs[a].moveY = GetMove1(npcs[a].absY, npcY);
							break;

						case 3:
							npcs[a].moveX = GetMove1(npcs[a].absX, npcX - 1);
							npcs[a].moveY = GetMove1(npcs[a].absY, npcY);
							break;
						}
					}
					npcs[a].turnNpc(npcs[b].absX, npcs[b].absY);
					handleClipping(b);
					npcs[a].getNextNPCMovement(b);
					npcs[a].updateRequired = true;
				}
			}
		} else {
			npcs[a].randomWalk = true;
			npcs[a].underAttack = false;
		}

	}

	public static boolean goodDistance1(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		return Math.sqrt(Math.pow(objectX - playerX, 2)
				+ Math.pow(objectY - playerY, 2)) <= distance;
	}

	/**
	 * Npc Follow Player
	 **/

	public static int GetMove1(int Place1, int Place2) {
		if ((Place1 - Place2) == 0) {
			return 0;
		} else if ((Place1 - Place2) < 0) {
			return 1;
		} else if ((Place1 - Place2) > 0) {
			return -1;
		}
		return 0;
	}

	/**
	 * Distanced required to attack
	 **/
	public static int distanceRequired1(int i) {
		switch (npcs[i].npcType) {
		case 2025:
		case 2028:
			return 6;
		case 50:
		case 2562:
			return 2;
		case 172:
		case 174:
			return 4;
		case 2881:// dag kings
		case 2882:
		case 3200:// chaos ele
		case 2743:
		case 2631:
		case 2745:
			return 8;
		case 2883:// rex
			return 1;
		case 2552:
		case 2553:
		case 2556:
		case 2557:
		case 2558:
		case 2559:
		case 2560:
		case 2564:
		case 2565:
			return 9;
			// things around dags
		case 2892:
		case 2894:
			return 10;
		default:
			return 1;
		}
	}

	// NPC VS NPC END

}
