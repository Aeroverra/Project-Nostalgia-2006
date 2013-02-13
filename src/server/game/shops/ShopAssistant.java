package server.game.shops;

import java.util.ArrayList;
import java.util.List;

import server.Config;
import server.Server;
import server.game.items.Item;
import server.game.players.Client;
import server.game.players.PlayerHandler;
import server.world.ShopHandler;

public class ShopAssistant {

    private List<ShopItem> shopStock = new ArrayList<ShopItem>();

    private final Client c;

    public ShopAssistant(Client client) {
	this.c = client;
    }

    public class ShopItem {
	private int id, currentAmount;

	private ShopItem(int itemId, int amount) {
	    id = itemId;
	    currentAmount = amount;
	}

    }

    public int tryToBuy(int itemId, int amount) {
	if (shopHasItem(itemId)) {
	    for (int i = 0; i < shopStock.size();) {
		if (shopStock.get(i).id == itemId) {
		    if (shopStock.get(i).currentAmount > amount) {
			int temp = shopStock.get(i).currentAmount;
			shopStock.remove(i);
			return temp;
		    } else {
			shopStock.set(i, new ShopItem(itemId,
				shopStock.get(i).currentAmount - amount));
			return amount;
		    }
		} else {
		    return 0;
		}
	    }
	}
	return 0;
    }

    /**
     * Shops
     */

    public void openShop(int ShopID) {
	c.getPA().sound(1465);
	c.getItems().resetItems(3823);
	resetShop(ShopID);
	c.isShopping = true;
	c.myShopId = ShopID;
	c.getPA().sendFrame248(3824, 3822);
	c.getPA().sendFrame126(ShopHandler.ShopName[ShopID], 3901);
    }

    public void updatePlayerShop() {
	for (int i = 1; i < PlayerHandler.players.length; i++) {
	    if (PlayerHandler.players[i] != null) {
		if (PlayerHandler.players[i].isShopping
			&& PlayerHandler.players[i].myShopId == c.myShopId
			&& i != c.playerId) {
		    PlayerHandler.players[i].updateShop = true;
		}
	    }
	}
    }

    public void resetShop(int ShopID) {
	synchronized (c) {
	    int TotalItems = 0;
	    for (int i = 0; i < ShopHandler.MaxShopItems; i++) {
		if (ShopHandler.ShopItems[ShopID][i] > 0) {
		    TotalItems++;
		}
	    }
	    if (TotalItems > ShopHandler.MaxShopItems) {
		TotalItems = ShopHandler.MaxShopItems;
	    }
	    c.getOutStream().createFrameVarSizeWord(53);
	    c.getOutStream().writeWord(3900);
	    c.getOutStream().writeWord(TotalItems);
	    int TotalCount = 0;
	    for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
		if (ShopHandler.ShopItems[ShopID][i] > 0
			|| i <= ShopHandler.ShopItemsStandard[ShopID]) {
		    if (ShopHandler.ShopItemsN[ShopID][i] > 254) {
			c.getOutStream().writeByte(255);
			 c.getOutStream().writeDWord_v2(ShopHandler.ShopItemsN[ShopID][i]);
		    } else {
			c.getOutStream().writeByte(
				ShopHandler.ShopItemsN[ShopID][i]);
		    }
		    if (ShopHandler.ShopItems[ShopID][i] > Config.ITEM_LIMIT
			    || ShopHandler.ShopItems[ShopID][i] < 0) {
			ShopHandler.ShopItems[ShopID][i] = Config.ITEM_LIMIT;
		    }
		    c.getOutStream().writeWordBigEndianA(
			    ShopHandler.ShopItems[ShopID][i]);
		    TotalCount++;
		}
		if (TotalCount > TotalItems) {
		    break;
		}
	    }
	    c.getOutStream().endFrameVarSizeWord();
	    c.flushOutStream();
	}
    }

    public double getItemShopValue(int ItemID, int Type) {
	double ShopValue = 1;
	double TotPrice;
	for (int i = 0; i < Config.ITEM_LIMIT; i++) {
	    if (Server.itemHandler.ItemList[i] != null) {
		if (Server.itemHandler.ItemList[i].itemId == ItemID) {
		    ShopValue = Server.itemHandler.ItemList[i].ShopValue;
		}
	    }
	}

	TotPrice = ShopValue;

	if (ShopHandler.ShopBModifier[c.myShopId] == 1) {
	    TotPrice *= 1;
	    TotPrice *= 1;
	    if (Type == 1) {
		TotPrice *= 1;
	    }
	} else if (Type == 1) {
	    TotPrice *= 1;
	}
	return TotPrice;
    }

    public int getItemShopValue(int itemId) {
	for (int i = 0; i < Config.ITEM_LIMIT; i++) {
	    if (Server.itemHandler.ItemList[i] != null) {
		if (Server.itemHandler.ItemList[i].itemId == itemId) {
		    return (int) Server.itemHandler.ItemList[i].ShopValue;
		}
	    }
	}
	return 0;
    }

    /**
     * buy item from shop (Shop Price)
     */

    public void buyFromShopPrice(int removeId) {
	int ShopValue = (int) Math.floor(getItemShopValue(removeId, 0));
	ShopValue *= 1.10;
	String ShopAdd = "";
	if (ShopValue >= 1000 && ShopValue < 1000000) {
	    ShopAdd = " (" + (ShopValue / 1000) + "K)";
	} else if (ShopValue >= 1000000) {
	    ShopAdd = " (" + (ShopValue / 1000000) + " million)";
	}
	c.sendMessage(c.getItems().getItemName(removeId) + ": currently costs "
		+ ShopValue + " coins" + ShopAdd);
    }

    public int getTokkulValue(int id) {
	switch (id) {
	case 1961:
	case 1989:
	case 795:
	    return 1;
	case 6722:
	    return 2;
	case 10498:
	    return 900;
	case 10958:
	case 10956:
	case 10954:
	    return 500;
	case 6733:
	    return 8500;
	case 6330:
	case 6328:
	case 6326:
	case 6324:
	case 6322:
	    return 1500;
	case 4219:
	    return 3000;

	    // End Archery Shop
	case 4068:
	case 4069:
	case 4070:
	case 4071:
	case 4072:
	    return 150;
	case 4503:
	case 4504:
	case 4505:
	case 4506:
	case 4507:
	    return 250;
	case 4508:
	case 4509:
	case 4510:
	case 4511:
	case 4512:
	    return 400;
	case 4513:
	case 4514:
	case 4515:
	case 4516:
	    return 100;
	case 6571:
	    return 11000;
	case 11128:
	    return 6500;
	case 6528:
	    return 6000;
	case 6524:
	    return 6000;
	case 6568:
	    return 6000;
	case 6525:
	    return 3000;
	case 6526:
	    return 3000;
	case 6522:
	    return 15;
	case 3488:
	case 3486:
	case 3481:
	case 3483:
	case 3485:
	    return 3000;
	case 9005:
	case 9006:
	    return 2000;
	case 15021:
	    return 8500;
	}
	return 0;
    }

    public int getSpecialItemValue(int id) {
	if (id >= 1038 && id <= 2422)
	    return 950;
	switch (id) {
	case 10939:
	case 10940:
	case 10941:
	case 10933:
	    return 15;
	case 6739:
	    return 30;
	    /** Lighters **/
	case 7329:
	case 7330:
	case 7331:
	case 10326:
	case 10327:
	    return 1;
	    /** Honour Shop **/
	case 11377:
	    return 4;
	case 8676:
	case 8740:
	    return 3;
	case 9629:
	case 2402:
	    return 2;
	case 9731:
	    return 1;
	case 10384:
	case 10386:
	case 10388:
	case 10390:
	case 10376:
	case 10378:
	case 10380:
	case 10382:
	case 10368:
	case 10370:
	case 10372:
	case 10374:
	    return 2;

	    /** Thieving **/
	case 1506:
	    return 10;
	case 4502:
	    return 40;
	case 2631:
	    return 16;
	case 9634:
	case 9636:
	case 9638:
	    return 20;
	case 7918:
	    return 30;
	case 7917:
	    return 4;
	    /** End **/
	case 606:
	    return 1;
	case 1555:
	case 1556:
	case 1557:
	case 1558:
	case 1559:
	case 1560:
	    return 10;
	case 6555:
	    return 20;
	case 7583:
	    return 25;
	case 7582:
	    return 50;
	case 6583:
	    return 200;
	case 1052:
	    return 80;
	case 775:
	case 1833:
	case 1835:
	case 1837:
	    return 5;
	case 10547:
	case 10548:
	case 10549:
	case 10550:
	    return 35;
	case 10553:
	    return 10;
	case 10555:
	    return 60;
	case 10552:
	    return 20;
	case 6137:
	case 6139:
	case 6141:
	case 6147:
	case 6153:
	    return 45;
	case 9084:
	    return 90;
	case 9096:
	case 9097:
	case 9098:
	case 9099:
	    return 150;
	case 9100:
	case 9101:
	case 9102:
	case 9104:
	    return 50;
	case 3385:
	case 3387:
	case 3389:
	case 3391:
	case 3393:
	    return 25;
	case 7400:
	case 7399:
	case 7398:
	    return 35;
	case 2890:
	    return 125;
	case 10400:
	    return 40;
	case 10402:
	    return 40;
	case 10404:
	    return 40;
	case 10406:
	    return 40;
	case 10408:
	    return 40;
	case 10410:
	    return 40;
	case 10412:
	    return 40;
	case 10414:
	    return 40;
	case 10416:
	    return 40;
	case 10418:
	    return 40;
	case 10420:
	    return 40;
	case 10424:
	    return 40;
	case 10426:
	    return 40;
	case 10428:
	    return 40;
	case 10430:
	    return 40;
	case 10432:
	    return 40;
	case 10434:
	    return 40;
	case 10436:
	    return 40;
	case 10438:
	    return 40;
	case 6859:
	    return 50;
	case 7053:
	    return 50;
	case 1419:
	    return 50;
	case 2460:
	    return 10;
	case 2462:
	    return 10;
	case 2464:
	    return 10;
	case 2466:
	    return 10;
	case 2468:
	    return 10;
	case 2470:
	    return 10;
	case 2472:
	    return 10;
	case 2474:
	    return 10;
	case 9044:
	    return 50;
	case 4155:
	    return 10;
	case 4166:
	    return 6;
	case 4170:
	    return 12;
	case 4156:
	    return 17;
	case 4551:
	    return 12;
	case 10952:
	    return 1;
	case 6708:
	    return 6;
	case 4158:
	    return 21;
	case 8901:
	    return 50;
	case 8839:
	    return 125;
	case 2528:
	    return 1;
	case 10422:
	    return 40;
	case 8841:
	    return 40;
	case 8840:
	    return 125;
	case 8842:
	    return 50;
	case 11663:
	    return 75;
	case 11664:
	    return 75;
	case 11665:
	    return 75;
	case 6570:
	    return 30;
	case 11283:
	    return 70;
	case 10499:
	    return 30;
	case 11724:
	    return 100;
	case 11726:
	    return 100;
	case 7462:
	    return 40;
	case 10551:
	    return 75;
	case 8850:
	    return 20;
	case 11732:
	    return 10;
	case 6585:
	    return 40;
	case 6737:
	    return 20;
	case 4151:
	    return 20;
	case 11777:
	    return 160;
	case 1053:
	case 1055:
	case 1057:
	    return 175;
	case 11718:
	case 11720:
	case 11722:
	    return 80;
	case 8013:
	    return 1;
	case 11698:
	case 11696:
	case 11700:
	    return 190;
	case 11730:
	    return 50;
	case 4447:
	    return 1;
	case 2595:
	case 2591:
	case 2593:
	case 2597:
	    return 5;
	case 2581:
	case 2577:
	    return 25;
	case 11694:
	    return 280;
	case 14999:
	case 15002:
	case 15003:
	    return 120;
	case 15001:
	case 15004:
	    return 60;
	case 15000:
	    return 150;
	case 13738:
	case 13744:
	case 13740:
	case 13742:
	    return 50;
	case 1038:
	case 1040:
	case 1042:
	case 1044:
	case 1046:
	case 1048:
	    return 275;
	case 1037:
	    return 150;
	case 4566:
	case 9920:
	case 10507:
	    return 20;
	case 10679:
	case 10294:
	    return 25;
	case 2583:
	case 2585:
	case 2587:
	case 2589:
	    return 15;
	case 2669:
	case 2671:
	case 2673:
	case 2675:
	case 2653:
	case 2655:
	case 2657:
	case 2659:
	case 2661:
	case 2663:
	case 2665:
	case 2667:
	    return 25;
	case 10362:
	    return 5;
	case 10364:
	    return 10;
	    // case 6739:
	    // return 20;
	case 2639:
	case 2641:
	    return 20;
	case 2643:
	    return 30;
	case 981:
	    return 70;
	case 1050:
	    return 90;
	case 962:
	    return 375;
	case 11235:
	    return 30;
	case 6912:
	    return 30;
	case 6914:
	    return 50;
	case 6916:
	    return 60;
	case 6918:
	    return 50;
	case 6920:
	    return 50;
	case 6922:
	    return 45;
	case 6924:
	    return 65;
	case 6889:
	    return 70;
	case 13887:
	    return 150;
	case 13893:
	    return 160;
	case 13899:
	    return 250;
	case 13864:
	    return 110;
	case 13905:
	    return 215;
	case 7321:
	    return 75;
	case 7323:
	    return 100;
	case 13858:
	    return 100;
	case 13861:
	    return 105;
	case 4325:
	case 4345:
	case 4365:
	case 4385:
	case 4405:
	    return 1;
	case 13896:
	    return 155;
	case 13884:
	    return 135;
	case 13890:
	    return 140;
	case 13902:
	    return 210;
	case 13876:
	    return 110;
	case 13870:
	    return 100;
	case 13873:
	    return 105;
	case 3486:
	    return 35;
	case 3481:
	    return 35;
	case 3483:
	    return 35;
	case 3485:
	    return 35;
	case 3488:
	    return 35;
	    /**/
	case 10722:
	    return 20;
	case 12000:
	    return 60;
	case 13263:
	    return 150;
	}
	return 0;
    }

    /**
     * Sell item to shop (Shop Price)
     */
    public void sellToShopPrice(int removeId) {
	for (int i : Config.ITEM_SELLABLE) {
	    if (i == removeId) {
		c.sendMessage("You can't sell "
			+ c.getItems().getItemName(removeId).toLowerCase()
			+ ".");
		return;
	    }
	}
	boolean IsIn = false;
	if (ShopHandler.ShopSModifier[c.myShopId] > 1) {
	    for (int j = 0; j <= ShopHandler.ShopItemsStandard[c.myShopId]; j++) {
		if (removeId == (ShopHandler.ShopItems[c.myShopId][j] - 1)) {
		    IsIn = true;
		    break;
		}
	    }
	} else {
	    IsIn = true;
	}
	if (!IsIn) {
	    c.sendMessage("You can't sell "
		    + c.getItems().getItemName(removeId).toLowerCase()
		    + " to this store.");
	} else {
	    int ShopValue = (int) Math.floor(getItemShopValue(removeId, 1));
	    String ShopAdd = "";
	    if (ShopValue >= 1000 && ShopValue < 1000000) {
		ShopAdd = " (" + (ShopValue / 1000) + "K)";
	    } else if (ShopValue >= 1000000) {
		ShopAdd = " (" + (ShopValue / 1000000) + " million)";
	    }
	    c.sendMessage(c.getItems().getItemName(removeId)
		    + ": shop will buy for " + ShopValue + " coins" + ShopAdd);
	}
    }

    public boolean shopSellsItem(int itemID) {
	for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
	    if (itemID == (ShopHandler.ShopItems[c.myShopId][i] - 1)) {
		return true;
	    }
	}
	return false;
    }

    public boolean sellItem(int itemID, int fromSlot, int amount) {
	if (c.inTrade || c.duelStatus != 0) {
	    c.sendMessage("You cant sell items to the shop while your in trade!");
	    return false;
	}
	for (int i : Config.ITEM_SELLABLE) {
	    if (i == itemID) {
		c.sendMessage("You can't sell "
			+ c.getItems().getItemName(itemID).toLowerCase() + ".");
		return false;
	    }
	}

	if (amount > 0 && itemID == (c.playerItems[fromSlot] - 1)) {
	    if (ShopHandler.ShopSModifier[c.myShopId] > 1) {
		boolean IsIn = false;
		for (int i = 0; i <= ShopHandler.ShopItemsStandard[c.myShopId]; i++) {
		    if (itemID == (ShopHandler.ShopItems[c.myShopId][i] - 1)) {
			IsIn = true;
			break;
		    }
		}
		if (!IsIn) {
		    c.sendMessage("You can't sell "
			    + c.getItems().getItemName(itemID).toLowerCase()
			    + " to this store.");
		    return false;
		}
	    }

	    if (amount > c.playerItemsN[fromSlot]
		    && (Item.itemIsNote[(c.playerItems[fromSlot] - 1)] || Item.itemStackable[(c.playerItems[fromSlot] - 1)])) {
		amount = c.playerItemsN[fromSlot];
	    } else if (amount > c.getItems().getItemAmount(itemID)
		    && !Item.itemIsNote[(c.playerItems[fromSlot] - 1)]
		    && !Item.itemStackable[(c.playerItems[fromSlot] - 1)]) {
		amount = c.getItems().getItemAmount(itemID);
	    }
	    // double ShopValue;
	    // double TotPrice;
	    int TotPrice2;
	    // int Overstock;
	    for (int i = amount; i > 0; i--) {
		TotPrice2 = (int) Math.floor(getItemShopValue(itemID, 1));
		if (c.getItems().freeSlots() > 0
			|| c.getItems().playerHasItem(995)) {
		    if (!Item.itemIsNote[itemID]) {
			c.getItems().deleteItem(itemID,
				c.getItems().getItemSlot(itemID), 1);
		    } else {
			c.getItems().deleteItem(itemID, fromSlot, 1);
		    }
		    c.getItems().addItem(995, TotPrice2);
		    addShopItem(itemID, 1);
		} else {
		    c.sendMessage("You don't have enough space in your inventory.");
		    break;
		}
	    }
	    c.getItems().resetItems(3823);
	    resetShop(c.myShopId);
	    updatePlayerShop();
	    return true;
	}
	return true;
    }

    public boolean addShopItem(int itemID, int amount) {
	boolean Added = false;
	if (amount <= 0) {
	    return false;
	}
	if (Item.itemIsNote[itemID]) {
	    itemID = c.getItems().getUnnotedItem(itemID);
	}
	for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
	    if ((ShopHandler.ShopItems[c.myShopId][i] - 1) == itemID) {
		ShopHandler.ShopItemsN[c.myShopId][i] += amount;
		Added = true;
	    }
	}
	if (!Added) {
	    for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
		if (ShopHandler.ShopItems[c.myShopId][i] == 0) {
		    ShopHandler.ShopItems[c.myShopId][i] = (itemID + 1);
		    ShopHandler.ShopItemsN[c.myShopId][i] = amount;
		    ShopHandler.ShopItemsDelay[c.myShopId][i] = 0;
		    break;
		}
	    }
	}
	return true;
    }

    public boolean shopHasItem(int itemId) {
	for (ShopItem aShopStock : shopStock) {
	    if (aShopStock.id == itemId) {
		return true;
	    }
	}
	return false;
    }

    public boolean buyItem(int itemID, int fromSlot, int amount) {
	if (ShopHandler.ShopItems[c.myShopId][fromSlot] - 1 != itemID
		|| ShopHandler.ShopItems[c.myShopId][fromSlot] < 0) {
	    return false;
	}
	if (!shopSellsItem(itemID)) {
	    return false;
	}
	if (amount <= 0) {
	    return false;
	}
	if (amount > 0) {
	    if (amount > ShopHandler.ShopItemsN[c.myShopId][fromSlot]) {
		amount = ShopHandler.ShopItemsN[c.myShopId][fromSlot];
	    }
	    // double ShopValue;
	    // double TotPrice;
	    int TotPrice2;
	    // int Overstock;
	    int Slot = 0;
	    for (int i = amount; i > 0; i--) {
		TotPrice2 = (int) Math.floor(getItemShopValue(itemID, 0));
		Slot = c.getItems().getItemSlot(995);
		if (Slot == -1) {
		    c.sendMessage("You don't have enough coins.");
		    break;
		}
		if (TotPrice2 <= 1) {
		    TotPrice2 = (int) Math.floor(getItemShopValue(itemID, 0));
		    TotPrice2 *= 1.66;
		}
		if (c.myShopId != 29 && c.myShopId != 31 || c.myShopId != 47
			|| c.myShopId != 21) {
		    if (c.playerItemsN[Slot] >= TotPrice2) {
			if (c.getItems().freeSlots() > 0) {
			    c.getItems().deleteItem(995,
				    c.getItems().getItemSlot(995), TotPrice2);
			    tryToBuy(itemID, 1);
			    c.getItems().addItem(itemID, 1);
			    ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
			    ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
			   
			    //if (ShopHandler.ShopItemsN[c.myShopId][fromSlot] == 0 &&
			//	    ShopHandler.ShopSModifier[c.myShopId] == 1) {
			    // ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
				    
				    
			   //   }
			     
			} else {
			    c.sendMessage("You don't have enough space in your inventory.");
			    break;
			}
		    } else {
			c.sendMessage("You don't have enough coins.");
			break;
		    }
		}

	    }
	    c.getItems().resetItems(3823);
	    resetShop(c.myShopId);
	    updatePlayerShop();
	    return true;
	}
	return false;
    }

    public void openSkillCape() {
	int capes = get99Count();
	if (capes > 1) {
	    capes = 1;
	} else {
	    capes = 0;
	}
	c.myShopId = 14;
	setupSkillCapes(get99Count());
    }

    public int[] skillCapes = { 9747, 9753, 9750, 9768, 9756, 9759, 9762, 9801,
	    9807, 9783, 9798, 9804, 9780, 9795, 9792, 9774, 9771, 9777, 9786,
	    9810, 9765, 1293, 9948 };

    public int get99Count() {
	int count = 0;
	for (int j = 0; j < c.playerLevel.length; j++) {
	    if (c.getLevelForXP(c.playerXP[j]) >= 99) {
		count++;
	    }
	}
	return count;
    }

    public void setupSkillCapes(int capes2) {
	synchronized (c) {
	    c.getItems().resetItems(3823);
	    c.isShopping = true;
	    c.myShopId = 14;
	    c.getPA().sendFrame248(3824, 3822);
	    c.getPA().sendFrame126("Skillcape Shop", 3901);

	    int TotalItems;
	    TotalItems = capes2;
	    if (TotalItems > ShopHandler.MaxShopItems) {
		TotalItems = ShopHandler.MaxShopItems;
	    }
	    c.getOutStream().createFrameVarSizeWord(53);
	    c.getOutStream().writeWord(3900);
	    c.getOutStream().writeWord(TotalItems);
	    for (int i = 0; i <= 22; i++) {
		if (c.getLevelForXP(c.playerXP[i]) < 99) {
		    continue;
		}
		c.getOutStream().writeByte(1);
		c.getOutStream().writeWordBigEndianA(skillCapes[i] + 2);
	    }
	    c.getOutStream().endFrameVarSizeWord();
	    c.flushOutStream();
	}
    }

    public void skillBuy(int item) {
	int nn = get99Count();
	if (nn > 1) {
	    nn = 1;
	} else {
	    nn = 0;
	}
	for (int j = 0; j < skillCapes.length; j++) {
	    if (skillCapes[j] == item || skillCapes[j] + 1 == item) {
		if (c.getItems().freeSlots() > 1) {
		    if (c.getItems().playerHasItem(995, 99000)) {
			if (c.getLevelForXP(c.playerXP[j]) >= 99) {
			    c.getItems().deleteItem(995,
				    c.getItems().getItemSlot(995), 99000);
			    c.getItems().addItem(skillCapes[j] + nn, 1);
			    c.getItems().addItem(skillCapes[j] + 2, 1);
			} else {
			    c.sendMessage("You must have 99 in the skill of the cape you're trying to buy.");
			}
		    } else {
			c.sendMessage("You need 99k to buy this item.");
		    }
		} else {
		    c.sendMessage("You must have at least 1 inventory spaces to buy this item.");
		}
	    }
	    /*
	     * if (skillCapes[j][1 + nn] == item) { if (c.getItems().freeSlots()
	     * >= 1) { if (c.getItems().playerHasItem(995,99000)) { if
	     * (c.getLevelForXP(c.playerXP[j]) >= 99) {
	     * c.getItems().deleteItem(995, c.getItems().getItemSlot(995),
	     * 99000); c.getItems().addItem(skillCapes[j] + nn,1);
	     * c.getItems().addItem(skillCapes[j] + 2,1); } else {
	     * c.sendMessage(
	     * "You must have 99 in the skill of the cape you're trying to buy."
	     * ); } } else { c.sendMessage("You need 99k to buy this item."); }
	     * } else { c.sendMessage(
	     * "You must have at least 1 inventory spaces to buy this item."); }
	     * break; }
	     */
	}
	c.getItems().resetItems(3823);
    }

}
