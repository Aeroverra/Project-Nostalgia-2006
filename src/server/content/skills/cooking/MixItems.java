package server.content.skills.cooking;

import server.Config;
import server.content.dialoguesystem.DialogueSystem;
import server.game.players.Client;


/*
 * Eclipse IDE
 * User: somedude
 * Date: Aug 16, 2012
 * Time: 11:15:25 AM
 */
public class MixItems {
  
    public static enum MixItemData {
	PIE_SHELL(1953, 0, 2313, 2315, 1, 0, true, 0), 
	REDBERRY_PIE(1951, 0, 2315, 2321, 10, 78, true, 0),
	APPLE_PIE(1955, 0, 2315, 2317, 30, 130, true, 0), 
	MEAT_PIE(2142, 0, 2315, 2319, 20, 110, true, 0), 
	CURRY(2007, 0, 1921, 2009, 60, 280, true, 7496), 
	CURRY_LEAF(5970, 3, 1921, 2009, 60, 280, true, 0),
	MEAT_PIZZA(2142, 0, 2289, 2293, 45, 169, true, 0), 
	ANCHOVIE_PIZZA(319, 0, 2289, 2297, 45, 169, true, 0), 
	PINEAPPLE_PIZZA(2116, 0, 2289, 2301, 65, 195, true, 0), 
	PINEAPPLE_PIZZA2(2118, 0, 2289, 2301, 65, 195, true, 0),
	CHOCOLATE_CAKE(1973, 0, 1891, 1897, 50, 210, true, 0), 
	CHOCOLATE_CAKE2(1975, 0, 1891, 1897, 50, 210, true, 0), 
	CHOCOLATE_MILK(1975, 0, 1927, 1977, 4, 0, true, 0), 
	CAKE1(2516, 0, 1887, 1889, 40, 0, true, 0), 
	CAKE2(1927, 0, 1887, 1889, 40, 0, true, 0), 
	CAKE3(1944, 0, 1887, 1889, 40, 0, true, 0),
	WINE(1987, 0, 1937, 1993, 68, 309.5, false, 0) ;

	private int primaryIngredient;
	private int amount;
	private int recipient;
	private int result;
	private int level;
	private double experience;
	private boolean hasRecipient;
	private int emptyOne;

	public static MixItemData forIdItem(int itemUsed, int withItem) {
		for (MixItemData mixItemData : MixItemData.values()) {
			if ((mixItemData.primaryIngredient == itemUsed && mixItemData.recipient == withItem) || (mixItemData.primaryIngredient == withItem && mixItemData.recipient == itemUsed))
				return mixItemData;

		}
		return null;
	}

	private MixItemData(int primaryIngredient, int amount, int recipient, int result, int level, double experience, boolean hasRecipient, int emptyOne) {
		this.primaryIngredient = primaryIngredient;
		this.amount = amount;
		this.recipient = recipient;
		this.result = result;
		this.level = level;
		this.experience = experience;
		this.hasRecipient = hasRecipient;
		this.emptyOne = emptyOne;
	}

	public int getPrimaryIngredient() {
		return primaryIngredient;
	}

	public int getAmount() {
		return amount;
	}

	public int getRecipient() {
		return recipient;
	}

	public int getResult() {
		return result;
	}

	public int getLevel() {
		return level;
	}

	public double getExperience() {
		return experience;
	}

	public boolean hasRecipient() {
		return hasRecipient;
	}

	public int getEmptyOne() {
		return emptyOne;
	}

}
    
    
    public static boolean mixItems(Client c, int itemUsed, int withItem,
	    int itemUsedSlot, int withItemSlot) {
	MixItemData rightData = MixItemData.forIdItem(itemUsed, withItem);
	if (rightData == null)
	    return false;

	c.getPA().removeAllWindows();

	if (!Config.COOKING_ENABLED) {
	    c.sendMessage(c.disabled());
	    return true;
	}
	if (c.playerLevel[c.playerCooking] < rightData.getLevel()) {
	    DialogueSystem.sendStatement(c, "You need a cooking level of "
		    + rightData.getLevel() + " to do this.");
	    return true;
	}
	int amount = rightData.getAmount() == 0 ? 1 : rightData.getAmount();
	if (c.getItems().getItemAmount(rightData.getPrimaryIngredient()) < amount) {
	    DialogueSystem.sendStatement(c, "You need "
		    + amount
		    + " "
		    + c.getItems()
			    .getItemName(rightData.getPrimaryIngredient())
			    .toLowerCase() + " to do this");
	    return true;
	}
	if ((rightData.getResult() == 1871 || rightData.getResult() == 7080 || rightData
		.getResult() == 7074) && !c.getItems().playerHasItem(946)) {
	    c.sendMessage("You need a knife for that.");
	    return true;
	}
	if (rightData.getResult() == 1889)// making cake
	    if (!c.getItems().playerHasItem(2516)
		    || !c.getItems().playerHasItem(1927)
		    || !c.getItems().playerHasItem(1944))
		return true;
	    else {
		handleCake(c);
		return true;
	    }
	if (rightData.getResult() == 7066)
	    c.getItems().addItem(1923, 1);
	
	if (rightData.hasRecipient())
	    c.sendMessage("You put the "
		    + c.getItems()
		    .getItemName(rightData.getPrimaryIngredient())
			    .toLowerCase()
		    + " into the "
		    + c.getItems()
		    .getItemName(rightData.getRecipient())
			    .toLowerCase()
		    + " and make a "
		    + c.getItems()
		    .getItemName(rightData.getResult())
			    .toLowerCase() + ".");
	else
	    c.sendMessage("You mix the "
		    + c.getItems()
		    .getItemName(rightData.getPrimaryIngredient())
			    .toLowerCase()
		    + " with the "
		    + c.getItems()
		    .getItemName(rightData.getRecipient())
			    .toLowerCase()
		    + " and make a "
		    + c.getItems()
		    .getItemName(rightData.getResult())
			    .toLowerCase() + ".");
	if (rightData.getResult() != 1889)
	    c.getItems().deleteItem2(rightData.getPrimaryIngredient(), amount);
	c.getItems().deleteItem(rightData.getRecipient(), 1);
	c.getItems().addItem(rightData.getResult(), 1); // new slot id
	c.getPA().addSkillXP((int) rightData.getExperience(), c.playerCooking);
	
	if (rightData.getEmptyOne() != 0)
	    //empty.
	    c.getItems().addItem(rightData.getEmptyOne(), 1);
	return true;

    }
    /**
     * Handles cake making.
     * @param player
     */
    public static void handleCake(Client player) {
	player.getItems().addItem(2516, 1);
	player.getItems().addItem(1927, 1);
	player.getItems().addItem(1944, 1);
	player.getItems().addItem(1887, 1);
	player.getItems().addItem(3727, 1);
	player.getItems().addItem(1931, 1);
	player.getItems().addItem(1889, 1);
	player.sendMessage("You mix the ingredients together and make a cake.");
    }

}
