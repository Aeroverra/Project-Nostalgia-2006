package server.game.objects.ladders;

import server.content.quests.misc.Tutorialisland;
import server.game.players.Client;
import server.game.players.DialogueHandler;

/**
 * @author Genesis
 */
public class LadderConfigurations {

	public static boolean handlingLadder(Client c, int objectType, int obX, int obY) {
		switch (objectType) {
		case 3029:
			if(c.tutorialprog == 14) {
				Tutorialisland.sendDialogue(c, 3051, 0);
				LadderHandler.climbLadder(c, 3088, 9520, 0, 0);
			}
			break;
		case 3030:
			if(c.tutorialprog == 26) {
				Tutorialisland.TII(c, 55, 12);
				Tutorialisland.sendDialogue(c, 3078, 0);
				LadderHandler.climbLadder(c, 3111, 3125, 0, 0);
			}
			
			
			break;
		case 12964:
		case 4415:
		case 4417:
		case 4418:
		case 4419:
		case 4420:
		case 4469:
		case 4470:
		case 4911:
		case 4912:
		case 1748:
		case 1747:
		case 1757:
		case 4437:
		case 6281:
		case 6280:
		case 4472:
		case 4471:
		case 4406:
		case 4407:
		case 4458:
		case 4902:
		case 4903:
		case 4900:
		case 4901:
		case 4461:
		case 4463:
		case 4464:
		case 4377:
		case 2269:
		case 3205:
		case 1750:
		case 2268:
		case 4378:
		case 11739:
		case 12966:
			if (c.heightLevel == 0) {
				LadderHandler.climbLadder(c, c.absX, c.absY, 0, 1);
			} else if (c.heightLevel == 1 && !c.cantClimbLadder) {
				DialogueHandler.sendDialogues(c, 1355, -1);
			} else if (c.heightLevel == 2) {
				LadderHandler.climbLadder(c, c.absX, c.absY, 2, 1);


			}
			return true;
		case 11741:
		case 1746:

			if (c.heightLevel > 0) {
				LadderHandler.climbLadder(c, c.absX, c.absY, c.heightLevel, c.heightLevel - 1);

			}
			return true;
		case 1738:
		case 1739:
		case 11732:
		case 12965:

			if (c.heightLevel == 0) {
				LadderHandler.climbStairs(c, c.absX, c.absY, 0, 1);
			} else if (c.heightLevel == 1 && !c.cantClimbLadder) {
				DialogueHandler.sendDialogues(c, 1356, -1);
			} else if (c.heightLevel == 2) {
				LadderHandler.climbStairs(c, c.absX, c.absY, 2, 1);

			}
			return true;
		case 1740:
			if (c.heightLevel > 0) {
				LadderHandler.climbStairs(c, c.absX, c.absY, c.heightLevel, c.heightLevel - 1);
			}

			return true;
		}
		return false;
	}


}