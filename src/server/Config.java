package server;

import core.util.Misc;


public class Config {

	public static final boolean SERVER_DEBUG = false;

	public static final String SERVER_NAME = "Project 2006";
	public static final int ITEM_LIMIT = 12750;
	public static final int TRADE_LIMIT = 28;
	public static final int MAXITEM_AMOUNT = Integer.MAX_VALUE;
	public static final int BANK_SIZE = 68;// 352 for donators
	public static final int MAX_PLAYERS = 1024;

	
	public static final int IPS_ALLOWED = 2000;

	public static final boolean WORLD_LIST_FIX = true;
	
	

	public static final int[] ITEM_SELLABLE 		=	{3842,3844,3840,8844,8845,8846,8847,8848,8849,8850,10551,6570,7462,7461,7460,7459,7458,7457,7456,7455,7454,8839,8840,8842,11663,11664,11665,10499,
		9748,9754,9751,9769,9757,9760,9763,9802,9808,9784,9799,9805,9781,9796,9793,9775,9772,9778,9787,9811,9766,
		9749,9755,9752,9770,9758,9761,9764,9803,9809,9785,9800,9806,9782,9797,9794,9776,9773,9779,9788,9812,9767,
		9747,9753,9750,9768,9756,9759,9762,9801,9807,9783,9798,9804,9780,9795,9792,9774,9771,9777,9786,9810,9765,995};
	public static final int[] ITEM_TRADEABLE 		= 	{3842,3844,3840,8844,8845,8846,8847,8848,8849,8850,10551,6570,7462,7461,7460,7459,7458,7457,7456,7455,7454,8839,8840,8842,11663,11664,11665,10499,
		9748,9754,9751,9769,9757,9760,9763,9802,9808,9784,9799,9805,9781,9796,9793,9775,9772,9778,9787,9811,9766,
		9749,9755,9752,9770,9758,9761,9764,9803,9809,9785,9800,9806,9782,9797,9794,9776,9773,9779,9788,9812,9767,
		9747,9753,9750,9768,9756,9759,9762,9801,9807,9783,9798,9804,9780,9795,9792,9774,9771,9777,9786,9810,9765};
	public static final int[] UNDROPPABLE_ITEMS 	= 	{};

	public static final int[] FUN_WEAPONS	=	{2460,2461,2462,2463,2464,2465,2466,2467,2468,2469,2470,2471,2471,2473,2474,2475,2476,2477};

	public static final boolean ADMIN_CAN_TRADE = true;
	public static final boolean ADMIN_CAN_SELL_ITEMS = false;
	public static final boolean ADMIN_DROP_ITEMS = false;

	
	public static final int START_LOCATION_X = 3094;
	public static final int START_LOCATION_Y = 3107;
	public static final int RESPAWN_X = 3222 + Misc.random(1);
	public static final int RESPAWN_Y = 3218 + Misc.random(1);
	public static final int DUELING_RESPAWN_X = 3362;
	public static final int DUELING_RESPAWN_Y = 3263;
	public static final int RANDOM_DUELING_RESPAWN = 5;

	public static final int NO_TELEPORT_WILD_LEVEL = 20;
	public static final int SKULL_TIMER = 1200;
	
	public static final boolean SINGLE_AND_MULTI_ZONES = true;
	public static final boolean COMBAT_LEVEL_DIFFERENCE = true;

	public static final boolean itemRequirements = true;

	public static final int MELEE_EXP_RATE = 4; //6
	public static final int RANGE_EXP_RATE = 4;
	public static final int MAGIC_EXP_RATE = 4;
	public static final int SERVER_EXP_BONUS = 5; // 3x or 4x

	public static final int INCREASE_SPECIAL_AMOUNT = 14500;
	public static final boolean PRAYER_POINTS_REQUIRED = true;
	public static final boolean PRAYER_LEVEL_REQUIRED = true;
	public static final boolean MAGIC_LEVEL_REQUIRED = true;
	public static final int GOD_SPELL_CHARGE = 300000;
	public static final boolean RUNES_REQUIRED = true;
	public static final boolean CORRECT_ARROWS = true;
	public static final boolean CRYSTAL_BOW_DEGRADES = true;

	public static final int SAVE_TIMER = 10;
	public static final int NPC_RANDOM_WALK_DISTANCE = 4;
	public static final int NPC_FOLLOW_DISTANCE = 10;
	public static final int[] UNDEAD_NPCS = {90,91,92,93,94,103,104,73,74,75,76,77};

	/**
	 * Glory
	 */
	public static final int AL_KHARID_X = 3293;
	public static final int AL_KHARID_Y = 3174;

	/**
	 * Teleport Spells
	 **/
	// modern
	public static final int VARROCK_X = 3212;
	public static final int VARROCK_Y = 3424;
	public static final int LUMBY_X = 3222;
	public static final int LUMBY_Y = 3218;
	public static final int FALADOR_X = 2964;
	public static final int FALADOR_Y = 3378;
	public static final int BUFFER_SIZE = 10000;


	public static boolean SERVER_DEV = true;

	public static boolean RANDOMS_ENABLED = true;
	
	/*
	 * Skill enablers
	 */
	public static boolean COOKING_ENABLED = true;
	public static boolean CRAFTING_ENABLED = true;
	public static boolean FIREMAKING_ENABLED = true;
	public static boolean FISHING_ENABLED = true;
	public static boolean MINING_ENABLED = true;
	public static boolean PRAYER_ENABLED = true;
	public static boolean RUNECRAFTING_ENABLED = true;
	public static boolean SMITHING_ENABLED = true;
	public static boolean WOODCUTTING_ENABLED = true;
}
