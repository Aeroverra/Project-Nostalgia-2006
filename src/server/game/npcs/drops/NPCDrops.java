/**
 * Contains npc drops.
 */
package server.game.npcs.drops;



/**
 * @author somedude 100%
 * {ItemID,Amount,Chance}
 */
public class NPCDrops extends NPCDropsHandler {
	/** TODO Dwarves,greater demon(when hosting), Moss Giants.
	 * 
	 */
	
	//*************DEFAULT DROP TABLE************//
	public static final int[][] DEFAULT = {
	{i("bones"),1,ALWAYS}, {995, r(10),COINSRATE},	
    };
	//*************DROPS************//
	public static final int[][] man = {
	{526,1,ALWAYS},
	{995,1 + r(39),COINSRATE},
	{i("Water rune"),7,UNCOMMON}, {i("Earth rune"),4,UNCOMMON}, 
	{i("Fire rune"),6,UNCOMMON}, {i("Mind rune"),9,UNCOMMON},
	{i("bronze arrow"),7, UNCOMMON}, {i("iron dagger"),1, UNCOMMON},
	{i("Bronze full helm"),1,UNCOMMON}, {i("Air talisman"),1,UNCOMMON},
	{i("earth talisman"),1,UNCOMMON}, {i("fishing bait"),1,UNCOMMON},
	{i("copper ore"),1,UNCOMMON}, {i("tin ore"),1,UNCOMMON},
	{i("Chaos rune"),2,RARE}, {i("Body rune"),7,RARE}, {i("staff of air"),1, RARE + r(5)}	
	};
	
	public static final int[][] goblin = {
	{526,1,ALWAYS},
	{995,1 + r(23),COINSRATE},{i("iron dagger"),1,COMMON},	
	{i("bronze arrow"),4 + r(16), COMMON}, {i("bronze med helm"),1, COMMON},
	{i("bronze sq shield"),1,COMMON}, {i("air rune"),2 + r(4), COMMON},
	{i("body rune"),2 + r(7), COMMON}, {i("earth rune"),4, COMMON}, 
	{i("water rune"),6, COMMON}, {i("Air talisman"),1,COMMON}, {i("chef's hat"),1,COMMON},
	{i("goblin mail"),1, COMMON}, 
	{i("bronze full helm"),1, UNCOMMON}, {i("bronze sword"),1, UNCOMMON},
	{i("bronze longsword"),1, UNCOMMON}, {i("iron arrow"),3 + r(3), UNCOMMON},
	{i("bronze chainbody"),1,UNCOMMON}, {i("staff of air"),1,RARE},
	{i("bronze kiteshield"),1,UNCOMMON}, {i("raw chicken"),1,UNCOMMON}, 
	{i("fire talisman"),1,UNCOMMON}, 
	{i("mind rune"),2 + r(17), UNCOMMON}, {i("earth talisman"),1, UNCOMMON},
	{i("bronze axe"),1,RARE}, {i("bronze scimitar"),1,RARE}, {i("iron full helm"),1,RARE},	
	{i("leather body"),1,RARE}, {i("cape"),1,RARE}, {i("chaos rune"),1,RARE},
	{i("nature rune"),1,RARE}, {i("mind talisman"),1,RARE}, {946,1,RARE},  
    {i("tin ore"),1,RARE}  	
	};
	
	public static final int[][] lesserdemon = {	
	{i("ashes"),1,ALWAYS}, 	
	{995,10 + r(640),COINSRATE},{i("steel axe"),1,COMMON}, {i("steel scimitar"),1,COMMON},
	{i("steel full helm"),1,COMMON}, {i("black 2h sword"),1,COMMON},
	{i("mithril sq shield"),1,UNCOMMON}, {i("mithril chainbody"),1,UNCOMMON},
	{i("fire rune"),6 + r(114),UNCOMMON}, {i("chaos rune"),7 + r(17),UNCOMMON},
	{445,2 + r(2),UNCOMMON}, {i("jug of wine"),1,UNCOMMON},
	{i("death rune"),3 + r(3),RARE}, {i("black kiteshield"),1,RARE}, 
	{i("rune med helm"),1,VERY_RARE}
	};
	
	public static final int[][] guard = {	
	{i("bones"),1,ALWAYS}, 	
	{995,1 + r(24),COINSRATE}, {i("grapes"),1,COMMON},
	{i("air rune"),6,COMMON}, {i("earth rune"),3 + r(3),COMMON},
	{i("fire rune"),2,COMMON}, {i("body talisman"),1,COMMON},
	{i("iron dagger"),1,COMMON}, {i("iron arrow"),1+ r(2),COMMON},	
	{i("law rune"),1 + r(9),COMMON},
	{i("bronze arrow"),1 + r(4),UNCOMMON}, {i("steel arrow"),1+r(10),UNCOMMON},
	{i("nature rune"),1,RARE}, {i("chaos rune"),1 + r(1),RARE},
	{i("steel warhammer"),1,RARE}, {i("wheat"),1 + r(3),RARE},
	{i("iron ore"),1,RARE}, {i("iron platebody"),1,RARE}, 
	{i("steel bar"),1,RARE}	
	};
	
	public static final int[][] icewarrior = {	
	{i("bones"),1,ALWAYS}, 	
	{995,1 + r(14),COINSRATE}, {i("iron battleaxe"),1,COMMON}, 
	{i("mithril arrow"),3,COMMON}, {i("cosmic rune"),2,COMMON},
	{i("chaos rune"),3,COMMON}, {i("nature rune"),4,COMMON},
	{i("law rune"),2,COMMON},
	{i("mithril mace"),1,UNCOMMON}, {i("adamant arrow"),2,UNCOMMON},
	{i("death rune"),2,UNCOMMON},
	{i("mind rune"),24,RARE}, {i("mithril ore"),1,RARE},
	{i("iron 2h sword"),1,VERY_RARE}, {i("steel sword"),1,VERY_RARE},	
	{i("black kiteshield"),1,VERY_RARE}, {i("mithril sq shield"),1,VERY_RARE},
	{i("rune longsword"),1,VERY_RARE}	
	};
	
	public static final int[][] icegiant = {	
	{i("big bones"),1,ALWAYS}, 	
	{i("iron battleaxe"),1,COMMON}, {i("iron platelegs"),1,COMMON},
	{i("iron 2h sword"),1,COMMON}, {i("steel axe"),1,COMMON},
	{i("steel sword"),1,COMMON}, {i("black kiteshield"),1,COMMON},
	{i("water rune"),12,COMMON}, {i("mind rune"),24,COMMON},
	{i("body rune"),37,COMMON}, {i("cosmic rune"),2 + r(2),COMMON},
	{995,8 + r(446),COINSRATE},
	{i("adamant arrow"),5,UNCOMMON}, {i("law rune"),3,UNCOMMON},
	{i("nature rune"),6,UNCOMMON}, {i("banana"),1,UNCOMMON},
	{i("jug of wine"),1,UNCOMMON}, {i("mithril ore"),1,UNCOMMON},
	{i("mithril sq shield"),1,RARE}, {i("mithril mace"),1,RARE}, 
	{i("death rune"),2+ r(2),RARE}
	};
	
	public static final int[][] hobgoblin = {	
	{i("bones"),1,ALWAYS}, 	
	{995, 1 + r(70), COINSRATE},
	{i("iron sword"),1,COMMON}, {i("iron longsword"),1,UNCOMMON},
	{i("steel longsword"),1,UNCOMMON}, {i("steel dagger"),1,UNCOMMON},
	{i("limpwurt root"),1,COMMON},
	{i("water rune"),2,UNCOMMON}, {i("body rune"),6,UNCOMMON},
	{i("fire rune"),7,UNCOMMON}, {i("cosmic rune"),2,UNCOMMON},
	{i("chaos rune"),3,UNCOMMON}, {i("nature rune"),4,UNCOMMON},
	{i("law rune"),2,UNCOMMON}
	};
	
	public static final int[][] pirate = {	
	{i("bones"),1,ALWAYS}, 	
	{i("air rune"),10,COMMON}, {i("earth rune"),9,COMMON}, 
	{i("fire rune"),5,COMMON}, {i("chaos rune"),2,COMMON},
	{i("nature rune"),2,COMMON}, {i("bronze scimitar"),1,COMMON},
	{i("iron dagger"),1,COMMON}, {i("bronze arrow"),9 + r(3),COMMON},
	{i("staff of air"),1,UNCOMMON},  {995,4+r(51),COINSRATE}, 
	{i("law rune"),2,RARE}, {i("iron bar"),1,RARE}, {i("chef's hat"),1,RARE},
	{i("limpwurt root"),1,RARE}, {i("tinderbox"),1,RARE},
	{i("iron sword"),1,VERY_RARE}	
	};
	
	public static final int[][] zombie = {
	{i("bones"),1,ALWAYS},
	{995,1+r(25),COINSRATE},
	{i("fishing bait"),7,COMMON},
	{i("bronze axe"),1,UNCOMMON}, {i("iron axe"),1,UNCOMMON},
	{i("bronze med helm"),1,UNCOMMON}, {i("iron mace"),1,UNCOMMON},
	{i("bronze kiteshield"),1,UNCOMMON}, {i("iron arrow"),5,UNCOMMON},
	{i("iron dagger"),1,UNCOMMON}, {i("steel arrow"),5 + r(27),UNCOMMON},
	{i("mithril arrow"),2,UNCOMMON}, {i("chaos rune"),4,UNCOMMON},	
	{i("air rune"),3,UNCOMMON}, {i("fire rune"),7,UNCOMMON},
	{i("mind rune"),5,UNCOMMON}, {i("body rune"),3 + r(5),UNCOMMON},
	{i("nature rune"),5,UNCOMMON}, {i("law rune"),3,UNCOMMON},
	{i("cosmic rune"),2,UNCOMMON}, {i("tinderbox"),1,UNCOMMON},
	{i("copper ore"),1,UNCOMMON}, {i("iron ore"),1,UNCOMMON},
	{i("tin ore"),1,UNCOMMON}, {i("eye of newt"),1,UNCOMMON}, 
	{i("ashes"),1,UNCOMMON},
	{i("half a meat pie"),1,VERY_RARE}
	};
	
	public static final int[][] skeleton = {
	{i("bones"),1,ALWAYS},
	{i("iron dagger"),1,COMMON}, {i("iron med helm"),1,COMMON},
	{i("air rune"),12,COMMON}, {i("water rune"),5,COMMON}, 
	{i("earth rune"),3,COMMON}, {i("fire rune"),2,COMMON}, 
	{i("mind rune"),1 + r(24),COMMON}, {995,10,COINSRATE},
	{i("bronze arrow"),1 + r(10),COMMON}, {i("iron mace"),1,COMMON},
	{i("iron axe"),1,UNCOMMON}, {i("cosmic rune"),2,UNCOMMON},
	{i("iron arrow"),5,UNCOMMON}, {i("iron sword"),1,UNCOMMON},
	{i("chaos rune"),4,UNCOMMON}, {i("nature rune"),1 + r(2),UNCOMMON},
	{i("iron sword"),1,UNCOMMON}, {i("law rune"),2,UNCOMMON},
	{i("bucket"),1,UNCOMMON}, {i("wheat"),1,UNCOMMON},
	{i("iron ore"),1,UNCOMMON}, {i("bronze bar"),1,UNCOMMON},	
	{i("iron scimitar"),1,RARE}, {i("steel arrow"),1,RARE},
	{i("fire talisman"),1,RARE},
	{i("adamant kiteshield"),1,VERY_RARE},
	};
	
	public static final int[][] hillgiant = {
	{i("big bones"),1,ALWAYS},
	{i("limpwurt root"),1,COMMON},{i("beer"),1,COMMON},
	{i("iron arrow"),3,COMMON}, {i("iron full helm"),1,COMMON},
	{i("water rune"),7,COMMON}, {995,8 + r(15),COINSRATE},
	{i("body talisman"),1,UNCOMMON}, {i("fire rune"),15,UNCOMMON},
	{i("law rune"),1 + r(2),UNCOMMON}, {i("nature rune"),6,UNCOMMON},
	{i("mind rune"),3,UNCOMMON}, {i("steel longsword"),1,UNCOMMON},
	{i("steel arrow"),10 + r(5),UNCOMMON}, {i("iron kiteshield"),1,UNCOMMON},
	{i("iron 2h sword"),1,RARE}, {i("mithril arrow"),1,RARE},
	{i("steel dagger"),1,RARE}, {i("death rune"),2,RARE},
	{i("chaos rune"),2,RARE},
	{i("steel platebody"),1,VERY_RARE}, {i("adamant arrow"),1 + r(4),VERY_RARE},
	};
	
	public static final int[][] deadlyredspider = {
    {i("red spiders' eggs"),1, UNCOMMON}	
	};
	
	public static final int[][] rat = {
	{i("rat's tail"),1, COMMON}, {i("bones"),1, ALWAYS},
    };
	
	public static final int[][] cow = {
	{i("bones"),1, ALWAYS}, {i("cowhide"),1, ALWAYS},
	{i("raw beef"),1, ALWAYS},	
	};
	
	public static final int[][] chicken = {
    {i("bones"),1, ALWAYS}, {i("feather"), 5 + r(10), ALWAYS},
    {i("raw chicken"),1, ALWAYS},
	{i("egg"),1, UNCOMMON},
	};
	
	public static final int[][] imp = {
	{i("ashes"),1, ALWAYS},
	{i("red bead"),1, UNCOMMON}, {i("yellow bead"),1, UNCOMMON},
	{i("black bead"),1, UNCOMMON}, {i("white bead"),1, UNCOMMON},
    {i("tinderbox"),1, UNCOMMON}
	};
	public static final int[][] darkwizard = {
	{i("bones"),1, ALWAYS},
	{i("water rune"),1, UNCOMMON}, {i("fire rune"),1, UNCOMMON},
	{i("air rune"),1, UNCOMMON}, {i("chaos rune"),1, UNCOMMON},
	{i("earth rune"),1, UNCOMMON}
	};
}