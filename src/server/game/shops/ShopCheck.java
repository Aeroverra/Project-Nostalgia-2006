package server.game.shops;

import java.util.ArrayList;
import java.util.List;

/**
 * It's basically an antidupe check for shops. Can be adapted for trading, I
 * just cbf atm.
 * @author Daniel
 */
public class ShopCheck {

    private List<ShopItem> shopStock = new ArrayList<ShopItem>();

    /**
     * Config for the items held in the shop.
     */
    public class ShopItem {
        private int id, currentAmount;

        private ShopItem(int itemId, int amount) {
            id = itemId;
            currentAmount = amount;
        }

    }

    /**
     * Whether or not the item you're buying or selling is already in the shop.
     * @param itemId The id of the item to check.
     * @return Whether or not it's already there.
     */
    private boolean shopHasItem(int itemId) {
	    for (ShopItem aShopStock : shopStock) {
		    if (aShopStock.id == itemId) {
			    return true;
		    }
	    }
        return false;
    }

    /**
     * Adds the specific amount of items to the shop.
     * @param itemId The id to add.
     * @param amount The ammount of that id to add.
     */
    public void addToShop(int itemId, int amount) {
        if(shopHasItem(itemId))
            for(int i = 0; i < shopStock.size(); ++i)
                if(shopStock.get(i).id == itemId) {
                    shopStock.set(i, new ShopItem(itemId, shopStock.get(i).currentAmount + amount));
                    break;
                }
        else
            shopStock.add(new ShopItem(itemId, amount));
    }

    /**
     * Attempts to buy the item from the shop, this is just extra protection
     * against dupers.
     * @param itemId The item to attempt to buy.
     * @param amount The amount to attempt to but.
     * @return The amount actually bought.
     */
    public int tryToBuy(int itemId, int amount) {
        if(shopHasItem(itemId)) {
            for(int i = 0; i < shopStock.size();)
                if(shopStock.get(i).id == itemId) {
                    if(shopStock.get(i).currentAmount > amount) {
                        int temp = shopStock.get(i).currentAmount;
                        shopStock.remove(i);
                        return temp;
                    } else {
                        shopStock.set(i, new ShopItem(itemId, shopStock.get(i).currentAmount - amount));
                        return amount;
                    }
                } else {
                    return 0;
                }
        }
        return 0;
    }

}