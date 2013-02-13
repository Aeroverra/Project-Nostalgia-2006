package server.content.musicsys;
import server.Server;

public class Sound {

	/**
	 *Singular sound variables.
	 */

	public final int LEVELUP =  67;
	public final int DUELWON = 77;
	public final int DUELLOST = 76;
	public final int FOODEAT = 317;
	public final int DROPITEM = 376;
	public final int COOKITEM = 357;
	public final static int SHOOT_ARROW = 370;
	public final int TELEPORT = 202;
	public final int BONE_BURY = 380;
	public final int DRINK_POTION = 334;
	public final int EQUIP = 230;

	public static int getNpcAttackSound(int NPCID){
		
		return 1;
	
	}
	public static int getNpcBlockSound(int NPCID)
	{
		String npc = GetNpcName(NPCID).toLowerCase();
		if (npc.contains("bat")) {
			return 7;
		}
		else if (npc.contains("cow")) {
			return 5;
		}
		else	if (npc.contains("imp"))
		{
			return 11;
		}
		else	if (npc.contains("rat")) {
			return 16;
		}

		else	if (npc.contains("duck"))
		{
			return 24;
		}
		else	if (npc.contains("wolf") || npc.contains("bear"))
		{
			return 34;
		}
		else if (npc.contains("dragon"))
		{
			return 45;
		}
		else	if (npc.contains("ghost"))
		{
			return 53;
		}
		else	if (npc.contains("goblin"))
		{
			return 87;
		}
		else if (npc.contains("skeleton") || npc.contains("demon") || npc.contains("ogre") || npc.contains("giant") || npc.contains("tz-") || npc.contains("jad"))
		{
			return 1154;
		}
		else if (npc.contains("zombie"))
		{
			return 1151;
		}
		else	if (npc.contains("man") && !npc.contains("woman"))
		{
			return 816;
		}
		else	if (npc.contains("monk"))
		{
			return 816;
		}

		else if (!npc.contains("man") && npc.contains("woman"))
		{
			return 818;
		}
		return 791;

	}

	public static int getNpcDeathSounds(int NPCID)
	{
		String npc = GetNpcName(NPCID).toLowerCase();
		if (npc.contains("bat")) {
			return 7;
		}
		if (npc.contains("cow")) {
			return 3;
		}
		if (npc.contains("imp")){
			return 9;
		}
		if (npc.contains("rat")){
			return 15;
		}
		if (npc.contains("duck"))	{
			return 25;
		}
		if (npc.contains("wolf") || npc.contains("bear")){
			return 35;
		}
		if (npc.contains("dragon"))
		{
			return 44;
		}
		if (npc.contains("ghost"))
		{
			return 60;
		}
		if (npc.contains("goblin"))
		{
			return 125;
		}
		if (npc.contains("skeleton") || npc.contains("demon") || npc.contains("ogre") || npc.contains("giant") || npc.contains("tz-") || npc.contains("jad"))
		{
			return 70;
		}
		if (npc.contains("zombie"))
		{
			return 1140;
		}
		return 70;

	}

	public static String GetNpcName(int NpcID) {
		return Server.npcHandler.getNpcListName(NpcID);
	}

	public static String getItemName(int ItemID) {
		return Server.itemHandler.ItemList[ItemID].itemName;
	}


}