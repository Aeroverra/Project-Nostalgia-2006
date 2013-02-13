package server.game.players;

import server.Config;
import server.Server;
import server.clip.region.ObjectDef;
import server.content.FlourMill;
import server.content.HairDresser;
import server.content.Pickable;
import server.content.Sailing;
import server.content.dialoguesystem.DialogueSystem;
import server.content.quests.dialogue.BlackKnightsD;
import server.content.quests.dialogue.CooksAssistantD;
import server.content.quests.dialogue.DoricsQuestD;
import server.content.quests.dialogue.ImpCatcherD;
import server.content.quests.dialogue.KnightSwordD;
import server.content.quests.dialogue.RestlessGhostD;
import server.content.quests.dialogue.RuneMysteriesD;
import server.content.quests.dialogue.SheepShearerD;
import server.content.quests.dialogue.WitchsPotion;
import server.content.quests.misc.Tutorialisland;
import server.content.randoms.Whirlpool;
import server.content.skillguilds.SkillGuilds;
import server.content.skills.Fishing;
import server.content.skills.Mining;
import server.content.skills.Runecrafting;
import server.content.skills.Smithing;
import server.content.skills.Woodcutting;
import server.content.skills.crafting.Pottery;
import server.content.skills.crafting.SpinningWheel;
import server.content.skills.crafting.Tanning;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.items.ItemAssistant;
import server.game.objects.Object;
import server.game.objects.ladders.LadderConfigurations;
import server.game.objects.ladders.LadderHandler;
import core.util.Misc;
import core.util.ScriptManager;

public class ActionHandler {

    private Client c;

    public ActionHandler(Client Client) {
	this.c = Client;
    }

    public void firstClickObject(int objectType, final int obX, final int obY) {
	c.clickObjectType = 0;

	if (c.stopPlayerPacket || c.spinningPlate
		|| System.currentTimeMillis() - c.lastObjectClick < 900) {
	    return;
	}
	c.lastObjectClick = System.currentTimeMillis();
	if (LadderConfigurations.handlingLadder(c, objectType, obX, obY))
	    return;
	final String name1 = ObjectDef.getObjectDef(objectType).name.toLowerCase();
	if (name1.contains("hay")) {
	    int damage = 1;
	    if (Misc.random(10) == 1) {
		c.forcedChat("Ouch!");
		c.sendMessage("You search the bales... and get poked by a needle!");
		c.dealDamage(damage);
		c.handleHitMask(damage);
		c.startAnimation(832);
		c.getItems().addItem(1733, 1);
	    } else {
		c.sendMessage("You search the bales... and find nothing.");
	    }
	    return;
	}
	if (Woodcutting.playerTrees(c, objectType)) {
	    Woodcutting.attemptData(c, objectType, obX, obY);
	}
	if (Mining.miningRocks(c, objectType)) {
	    Mining.attemptData(c, objectType, obX, obY);
	    return;
	}
	if(SkillGuilds.handleGuilds(c, objectType, obX, obY)) 
	    return;
	switch (objectType) {
	case 8689:
	    if(c.getItems().playerHasItem(1925,1)) {
		c.startAnimation(2305);
		c.getItems().deleteItem(1925, 1);
		c.getItems().addItem(1927,1);
		c.sendMessage("You milked the cow to get a fresh bucket of milk.");
	} else {
		c.sendMessage("You need a bucket to milk this cow!");
	}
	    break;
	case 11601: //pottery.
	    Pottery.sendPotteryInterface(c, -1, "fired");
	    break;
	case 5581://axe in log
	    c.getItems().addItem(1351, 1);
	    c.startAnimation(832);
	    c.sendMessage("You take the axe from the log.");
	    new Object(5582, obX, obY, 0, 2, 10, 5581, 30);
	    break;
	/*/case 1551:
	    if (c.objectX == 3253 && c.objectY == 3266
	    || c.objectX == 3241 && c.objectY == 3301) { //a
		Server.objectHandler.createAnObject(-1, obX, obY +1, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +3, obX, obY, 3, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX +1, obY, 3, 0);
	    }
	    if (c.objectX == 3236 && c.objectY == 3285
		    || c.objectX == 3236 && c.objectY == 3296) { //b
		Server.objectHandler.createAnObject(-1, obX, obY -1, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +3, obX, obY, 1, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX -1, obY, 1, 0);
	    }
	    if (c.objectX == 3261 && c.objectY == 3321) { //c
		Server.objectHandler.createAnObject(-1, obX +1, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX, obY -1, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +3, obX, obY, 0, 0);
	    }
	    if (c.objectX == 3198 && c.objectY == 3282) { //this one is just real weird
		Server.objectHandler.createAnObject(-1, obX -1, obY, 0, 0);
		Server.objectHandler.createAnObject(-1, obX, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +3, obX, obY -2, 2, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX, obY -1, 2, 0);
	    }
	    
	    break;
	   
	    
	case 1552:
	    if (c.objectX == 3253 && c.objectY == 3267
		    || c.objectX == 3241 && c.objectY == 3302) { //a higher
		Server.objectHandler.createAnObject(-1, obX +1, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX, obY -1, 0, 0);
	    }
	    if (c.objectX == 3254 && c.objectY == 3266
		    || c.objectX == 3242 && c.objectY == 3301) { //a lower
		Server.objectHandler.createAnObject(-1, obX, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX -1, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX -1, obY +1, 0, 0);
	    }
	    if (c.objectX == 3236 && c.objectY == 3284
		|| c.objectX == 3236 && c.objectY == 3295) { //b lower
		Server.objectHandler.createAnObject(-1, obX -1, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX, obY, 2, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX, obY +1, 2, 0);
	    }
	    if (c.objectX == 3235 && c.objectY == 3285
		    || c.objectX == 3235 && c.objectY == 3296) { //b higher
		Server.objectHandler.createAnObject(-1, obX, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX +1, obY, 2, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX +1, obY -1, 2, 0);
	    }
	    if (c.objectX == 3261 && c.objectY == 3320) { //c west
		Server.objectHandler.createAnObject(-1, obX, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX, obY +1, 1, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX +1, obY +1, 1, 0);
	    }
	    if (c.objectX == 3262 && c.objectY == 3321) { //c east
		Server.objectHandler.createAnObject(-1, obX, obY -1, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX, obY, 1, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX -1, obY, 1, 0);
	    }
	    if (c.objectX == 3198 && c.objectY == 3281) { //this one is just real weird
		Server.objectHandler.createAnObject(-1, obX, obY -1, 0, 0);
		Server.objectHandler.createAnObject(-1, obX, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX, obY +1, 3, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX -1, obY +1, 3, 0);
	    }
	    if (c.objectX == 3197 && c.objectY == 3280) { //this one is just real weird
		Server.objectHandler.createAnObject(-1, obX, obY +1, 0, 0);
		Server.objectHandler.createAnObject(-1, obX, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX +1, obY +2, 3, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX, obY +2, 3, 0);
	    }
	    break;
	case 1553:
	    if (c.objectX == 3253 && c.objectY == 3267
		    || c.objectX == 3241 && c.objectY == 3302) { //a
		Server.objectHandler.createAnObject(-1, obX, obY -1, 0, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX, obY, 1, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX +1, obY, 1, 0);
	    }
	    if (c.objectX == 3236 && c.objectY == 3284
		    || c.objectX == 3236 && c.objectY == 3295) { //b
		Server.objectHandler.createAnObject(-1, obX, obY +1, 0, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX, obY, 3, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX -1, obY, 3, 0);
	    }
	    if (c.objectX == 3262 && c.objectY == 3321) { //c
		Server.objectHandler.createAnObject(-1, obX -1, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX, obY -1, 2, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX, obY, 2, 0);
	    }
	    if (c.objectX == 3197 && c.objectY == 3282) { //weird one
		Server.objectHandler.createAnObject(-1, obX +1, obY, 0, 0);
		Server.objectHandler.createAnObject(-1, obX, obY, 0, 0);
		Server.objectHandler.createAnObject(c.objectId -1, obX, obY -2, 0, 0);
		Server.objectHandler.createAnObject(c.objectId +1, obX, obY -1, 0, 0);
	    }
	    break;
	case 12986:
	    if (c.objectX == 3213 && c.objectY == 3261) {
        	Server.objectHandler.createAnObject(-1, obX, obY +1, 0, 0);
        	Server.objectHandler.createAnObject(c.objectId +3, obX, obY, 3, 0);
        	Server.objectHandler.createAnObject(c.objectId +2, obX +1, obY, 3, 0);
	    }
	    break;
	case 12987:
	    if (c.objectX == 3213 && c.objectY == 3262) {
        	Server.objectHandler.createAnObject(-1, obX, obY -1, 0, 0);
        	Server.objectHandler.createAnObject(c.objectId +1, obX, obY, 1, 0);
        	Server.objectHandler.createAnObject(c.objectId +2, obX +1, obY, 1, 0);
	    }
	    break;
	case 12988:
	    if (c.objectX == 3213 && c.objectY == 3262) {
        	Server.objectHandler.createAnObject(-1, obX +1, obY, 0, 0);
        	Server.objectHandler.createAnObject(c.objectId -1, obX, obY, 0, 0);
        	Server.objectHandler.createAnObject(c.objectId -2, obX, obY -1, 0, 0);
	    }
	    if (c.objectX == 3214 && c.objectY == 3261) {
        	Server.objectHandler.createAnObject(-1, obX, obY, 0, 0);
        	Server.objectHandler.createAnObject(c.objectId -2, obX -1, obY, 0, 0);
        	Server.objectHandler.createAnObject(c.objectId -1, obX -1, obY +1, 0, 0);
	    }
	    break;
	case 12989:
	    if (c.objectX == 3214 && c.objectY == 3262) {
        	Server.objectHandler.createAnObject(-1, obX, obY, 0, 0);
        	Server.objectHandler.createAnObject(c.objectId -2, obX -1, obY, 0, 0);
        	Server.objectHandler.createAnObject(c.objectId -3, obX -1, obY -1, 0, 0);
	    }
	    if (c.objectX == 3213 && c.objectY == 3261) {
        	Server.objectHandler.createAnObject(-1, obX +1, obY, 0, 0);
        	Server.objectHandler.createAnObject(c.objectId -3, obX, obY, 0, 0);
        	Server.objectHandler.createAnObject(c.objectId -2, obX, obY +1, 0, 0);
	    }
	    break;
	    /*/
	    
	case 2271:
	    c.getPA().object(2272, 2984, 3336, 1, 10);
	    c.sendMessage("You open the cupboard.");
	    break;
	case 2272:
	    if (c.knightS == 5) {
		c.sendMessage("You search the cupboard...");
		c.getItems().addItem(666, 1);
		KnightSwordD.dialogue(c, 653, 1);
		c.knightS += 1;
	    } else {
		c.sendMessage("You search the cupboard...");
		c.sendMessage("and don't find anything interesting.");
	    }
	    break;
	case 398: // coffin
	    Server.objectHandler.createAnObject(c, 399, obX, obY);
	    break;
	case 399: // coffin
	    Server.objectHandler.createAnObject(c, 398, obX, obY);
	    break;
	case 2145: // coffin
	    Server.objectHandler.createAnObject(c, 2146, obX, obY);
	    break;
	case 2146: // coffin
	    Server.objectHandler.createAnObject(c, 2145, obX, obY);
	    break;
	case 1804:
	    if (c.getItems().playerHasItem(983)) {
		c.sendMessage("You unlock the door.");
		return;
	    }
	    break;
	case 2072:// banana crate
	    if (c.bananacrate == 10) {
		c.luthas = 2;
		DialogueHandler.sendStatement(c,
			"The crate is now full of bananas.");
		c.nextChat = 0;
		break;
	    }
	    if (c.bananacrate > 10)
		c.bananacrate = 10;
	    if (c.getItems().playerHasItem(1963, 1)) {
		c.startAnimation(832);
		c.getItems().deleteItem(1963, 1);
		c.bananacrate++;
	    }
	    break;
	/*
	 * Banana
	 */
	case 2078:
	    c.sendMessage("The bananas have already been picked from this tree");
	    break;
	case 2073:
	    if (c.getItems().freeSlots() <= 0) {
		c.sendMessage(ItemAssistant.noSpace());
		return;
	    }
	    if (c.bananalimit == 5) {
		Server.objectHandler.createAnObject(c, 2078, obX, obY);
		c.bananalimit = 0;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {// animation
			    @Override
			    public void execute(CycleEventContainer container) {
				Server.objectHandler.createAnObject(c, 2073,
					obX, obY);
				container.stop();
			    }

			    @Override
			    public void stop() {

			    }
			}, 25 + Misc.random(20));
		return;
	    }
	    if (c.bananalimit <= 5) {
		c.sendMessage("You pick a banana.");
		c.getItems().addItem(1963, 1);
		c.bananalimit++;
	    }
	    if (c.bananalimit > 5) {
		c.bananalimit = 5;
		return;
	    }

	    break;
	// /BANANNA END////

	case 1764: // karamja lessers rope
	    if (c.objectX == 2856 && c.objectY == 9569)
		c.getPA().movePlayer(2857, 3167, 0);
	    break;
	case 492:
	    c.getPA().movePlayer(2856, 9569 - 1, 0);
	    break;
	case 2452:
	case 2453:
	case 2454:
	case 2455:
	case 2456:
	case 2457:
	case 2458:
	case 2459:
	case 2460:
	case 2461:
	case 2462:
	    Runecrafting.enterAltar(c, objectType, 0);
	    break;
	case 2469:// fire
	case 2465:// air
	case 2466:// Mind
	case 2467:// Water
	case 2470:// body
	case 2468:// earth
	    if (c.lastX > 0 && c.lastY > 0)
		Runecrafting.exitAltar(c);
	    break;
	case 14879: // lumbridge to cellar
	    if (c.objectX == 3209 && c.objectY == 3216) {
		c.startAnimation(828);
		c.getPA().movePlayer(3208, 9616, 0);
	    }
	    break;
	case 1782:// full flour bin
	    FlourMill.emptyFlourBin(c);
	    break;
	case 2718: // Hopper
	case 2719:
	    FlourMill.hopperControl(c);
	    break;
	/*
	 * skills
	 */
	case 11993: // Wizard tower doors
	    if (c.objectX == 3107 && c.objectY == 3162) {
		server.Server.objectHandler.createAnObject(c, -1, obX, obY);
	    }
	    if (c.objectX == 3109 && c.objectY == 3167) {
		server.Server.objectHandler.createAnObject(c, -1, obX, obY);
	    }
	    break;

	case 2147: // Ladder to underground wizard tower
	    if (c.objectX == 3104 && c.objectY == 3162) {
		c.startAnimation(828);
		c.getPA().movePlayer(3104, 9576, 0);
	    }
	    break;
	case 2148:
	    c.startAnimation(828);
	    c.getPA().movePlayer(3105, 3162, 0);
	    break;

	case 6434:// Trapdoor to underground draynor
	    server.Server.objectHandler.createAnObject(c, 6435, obX, obY);
	    break;
	case 6435:// opened trapdoor to underground draynor
	    if (c.objectX == 3118 && c.objectY == 3244) {
		c.startAnimation(828);
		c.getPA().movePlayer(3118, 9644, 0);
	    }
	    if (c.objectX == 3084 && c.objectY == 3272) {
		c.startAnimation(828);
		c.getPA().movePlayer(3084, 9672, 0);
	    }
	    break;
	case 6436: // ladder to above draynor:
	    c.startAnimation(828);
	    c.getPA().movePlayer(3117, 3244, 0);
	    break;

	case 9472: // Trapdoor to underground to blurite ore
	    c.startAnimation(828);
	    c.getPA().movePlayer(3009, 9550, 0);
	    break;

	case 881: // manhole in varrock
	    server.Server.objectHandler.createAnObject(c, 882, obX, obY);
	    break;
	case 882:
	    c.startAnimation(828);
	    c.getPA().movePlayer(3237, 9859, 0);
	    break;

	case 1755:
	    if (c.objectX == 2884 && c.objectY == 9797) {
		c.startAnimation(828);
		c.getPA().movePlayer(2844, 3516, 0);
	    }
	    if (c.objectX == 3116 && c.objectY == 9852) {
		c.startAnimation(828);
		c.getPA().movePlayer(3117, 3452, 0);
	    }
	    if (c.objectX == 3097 && c.objectY == 9867) {
		c.startAnimation(828);
		c.getPA().movePlayer(3096, 3468, 0);
	    }
	    if (c.objectX == 3237 && c.objectY == 9858) {
		c.startAnimation(828);
		c.getPA().movePlayer(3238, 3458, 0);
	    }
	    if (c.objectX == 3008 && c.objectY == 9550) {
		c.startAnimation(828);
		c.getPA().movePlayer(3009, 3150, 0);
	    }
	    if (c.objectX == 3084 && c.objectY == 9672) {
		c.startAnimation(828);
		c.getPA().movePlayer(3085, 3272, 0);
	    }
	    if (c.objectX == 3209 && c.objectY == 9616) {
		c.startAnimation(828);
		c.getPA().movePlayer(3208, 3216, 0);
	    }
	    // mining guild
	    if (c.objectX == 3018 && c.objectY == 9739 || c.objectX == 3019
		    && c.objectY == 9740 || c.objectX == 3020
		    && c.objectY == 9739 || c.objectX == 3019
		    && c.objectY == 9738) {
		if (c.playerLevel[14] >= 60) {
		    c.startAnimation(828);
		    c.getPA().movePlayer(c.absX, c.absY - 6400, 0);
		} else {
		    c.sendMessage("You need a mining level of 60 to use this");
		}
	    }
	    break;
	case 733:
	    server.Server.objectHandler.createAnObject(c, 734, obX, obY);
	    break;

	/*
	 * Start tutorial island objects
	 */
	case 3025:
	    if (c.tutorialprog == 28) {
		c.getPA().walkTo(1, 0);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			    if (PlayerHandler.players[i] != null) {
				Client c2 = (Client) PlayerHandler.players[i];
				c2.getPA().object(3025, 3130, 3124, 0, 0);
				// others
				c2.getPA().object(-1, 3129, 3124, 0, 0);

				container.stop();
			    }
			}
		    }

		    @Override
		    public void stop() {

		    }
		}, 3);
		Tutorialisland.TII(c, 70, 15);
		Tutorialisland.chatbox(c, 6180);
		Tutorialisland
			.chatboxText(
				c,
				"Follow the path to the chapel and enter it.",
				"Once inside talk to the monk. He'll tell you all about the skill.",
				"", "", "Prayer");
		Tutorialisland.chatbox(c, 6179);
		c.getPA().drawHeadicon(1, 8, 0, 0); // sends to prayer dude
	    }

	    break;
	case 3026:
	    if (c.tutorialprog == 32) {
		Tutorialisland.TII(c, 80, 17);
		c.getPA().drawHeadicon(1, 9, 0, 0); // sends to prayer dude
		c.getPA().walkTo(0, -1);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			    if (PlayerHandler.players[i] != null) {
				Client c2 = (Client) PlayerHandler.players[i];
				c2.getPA().object(3026, 3122, 3102, 1, 0);
				// others
				c2.getPA().object(-1, 3122, 3103, 0, 0);

				container.stop();
			    }
			}
		    }

		    @Override
		    public void stop() {

		    }
		}, 3);

	    }
	    break;
	case 3024:
	    if (c.tutorialprog == 27) {
		c.getPA().walkTo(1, 0);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			    if (PlayerHandler.players[i] != null) {
				Client c2 = (Client) PlayerHandler.players[i];
				c2.getPA().object(3024, 3125, 3124, 0, 0);
				// others
				c2.getPA().object(-1, 3124, 3124, 0, 0);

				container.stop();
			    }
			}
		    }

		    @Override
		    public void stop() {

		    }
		}, 3);
		Tutorialisland.TII(c, 65, 14);
		Tutorialisland.chatbox(c, 6180);
		Tutorialisland
			.chatboxText(
				c,
				"The guide here will tell you all about making cash. Just click on",
				"him to hear what he's got to say.", "", "",
				"Financial advice");
		Tutorialisland.chatbox(c, 6179);
		c.getPA().drawHeadicon(1, 7, 0, 0);
	    }

	    break;
	case 3045: // bank booth
	    if (c.tutorialprog == 26) {
		c.getPA().openUpBank();
		Tutorialisland.TII(c, 60, 13);
		c.getPA().createArrow(3125, 3124, c.getHeightLevel(), 2);
		Tutorialisland.chatbox(c, 6180);
		Tutorialisland
			.chatboxText(
				c,
				"You can store stuff here for safekeeping. If you die anything",
				"in your bank will be saved. To deposit something, rich click it",
				"and select 'store'. Once you've had a good look, close the",
				"window and move on through the door indicated.",
				"This is your bank box");
		Tutorialisland.chatbox(c, 6179);
		c.tutorialprog = 27;
	    }

	    break;
	case 3039:// tutorial island
	    if (c.getItems().playerHasItem(2307) && (c.tutorialprog == 8)) {
		c.startAnimation(896);
		c.getPA().requestUpdates();
		c.getItems().deleteItem(2307, 1);
		c.getItems().addItem(2309, 1);
		Tutorialisland.sendDialogue(c, 3037, 0);
	    }
	case 3019:
	    if (c.tutorialprog == 11) {
		Tutorialisland.sendDialogue(c, 3042, 0);
	    }
	    break;

	/*
	 * tutorial isalnd
	 */
	case 3014: // tutorial island shit
	    if (c.tutorialprog == 2) {
		c.getPA().walkTo(1, 0);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			    if (PlayerHandler.players[i] != null) {
				Client c2 = (Client) PlayerHandler.players[i];
				c2.getPA().object(3014, 3098, 3107, 0, 0);

				c2.getPA().object(-1, 3097, 3107, 0, 0);

				container.stop();
			    }
			}
		    }

		    @Override
		    public void stop() {

		    }
		}, 3);
		Tutorialisland.sendDialogue(c, 3011, -1);
	    }
	    break;
	case 3020:
	case 3021:
	   
	    if (c.tutorialprog >= 21 && c.getX() < 3095 && (c.getY() == 9503 || c.getY() == 9502)) {

		Tutorialisland.chatbox(c, 6180);
		Tutorialisland
			.chatboxText(
				c,
				"In this area you will find out about combat with swords and",
				"bows. Speak to the guide and he will tell you all about it.",
				"", "", "Combat");
		Tutorialisland.chatbox(c, 6179);
		// c2.getPA().object(3020, 3094, 9503, 4, 0);
		// c2.getPA().object(3021, 3094, 9502, 7, 0);
		c.getPA().object(-1, 3094, 9502, 0, 0);
		c.getPA().object(3021, 3095, 9502, 7, 0);

		c.getPA().object(-1, 3094, 9503, 0, 0);
		c.getPA().object(3020, 3095, 9503, 1, 0);

		c.getPA().walkTo(1, 0);
		c.tutorialprog = 22;
		// c.sendMessage("You pay the toll guard 10 coins and pass through the gate.");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			c.getPA().object(3020, 3094, 9503, 2, 0);
			c.getPA().object(3021, 3094, 9502, 2, 0);
			// others
			c.getPA().object(-1, 3095, 9502, 0, 0);
			c.getPA().object(-1, 3095, 9503, 0, 0);
			c.getPA().drawHeadicon(1, 6, 0, 0); // draws headicon to
							    // combat ude

			container.stop();
		    }

		    @Override
		    public void stop() {

		    }
		}, 2);
	    }
	    break;
	case 3023:
	case 3022:
	    if (c.tutorialprog == 24 && (c.getY() == 9519 || c.getY() == 9518)) {
		Tutorialisland.chatbox(c, 6180);
		Tutorialisland
			.chatboxText(
				c,
				"",
				"To attack the rat, right click it and select the attack option. you",
				"will then walk over to it and start hitting it.",
				"", "Attacking");
		Tutorialisland.chatbox(c, 6179);
		c.getPA().object(-1, 3111, 9518, 0, 0);
		c.getPA().object(3022, 3110, 9518, 7, 0);

		c.getPA().object(-1, 3111, 9519, 0, 0);
		c.getPA().object(3023, 3110, 9519, 1, 0);

		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {

			c.getPA().object(3022, 3111, 9518, 0, 0);
			c.getPA().object(3023, 3111, 9519, 0, 0);
			// others
			c.getPA().object(-1, 3110, 9518, 7, 0);
			c.getPA().object(-1, 3110, 9519, 1, 0);
			c.getPA().drawHeadicon(1, 13, 0, 0); // draws headicon
							     // to combat ude

			container.stop();
		    }

		    @Override
		    public void stop() {

		    }
		}, 4);
	    } else if (c.tutorialprog == 25
		    && (c.getY() == 9519 || c.getY() == 9518)) {
		c.getPA().object(-1, 3111, 9518, 0, 0);
		c.getPA().object(3022, 3110, 9518, 7, 0);

		c.getPA().object(-1, 3111, 9519, 0, 0);
		c.getPA().object(3023, 3110, 9519, 1, 0);

		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {

			c.getPA().object(3022, 3111, 9518, 0, 0);
			c.getPA().object(3023, 3111, 9519, 0, 0);
			// others
			c.getPA().object(-1, 3110, 9518, 7, 0);
			c.getPA().object(-1, 3110, 9519, 1, 0);

			container.stop();
		    }

		    @Override
		    public void stop() {

		    }
		}, 4);
	    }

	    break;

	// tutorial stuff end

	case 2491:
	    Mining.mineEss(c, objectType);
	    break;
	case 2024: // WP quest
	    if (WitchsPotion.finalstage) {
		DialogueHandler
			.sendStatement2(
				c,
				"You drink from the cauldron, it tastes horrible! You feel yourself",
				"imbued with power.");
		c.nextChat = 719;
	    }
	    break;
	case 3938:
	    if (c.objectX == 2266) {
		if (c.absX < c.objectX) {
		    c.stopMovement();
		    c.freezeTimer = 3;
		    c.playerWalkIndex = 839;
		    c.updateRequired = true;
		    c.appearanceUpdateRequired = true;
		    c.getPA().walkTo(3, 0);
		} else if (c.getX() == 2268 && c.getY() == 3192) {

		    c.stopMovement();
		    c.freezeTimer = 3;
		    c.playerWalkIndex = 839;
		    c.updateRequired = true;
		    c.appearanceUpdateRequired = true;
		    c.getPA().walkTo(-3, 0);
		}
	    }
	    break;
	case 2612: // cupboard lol
	    c.getPA().object(2613, 3096, 3269, 0, 10);
	    break;
	case 2613: // cupboard lol
	    CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		@Override
		public void execute(CycleEventContainer container) {
		    c.getItems().addItem(1550, 1);
		    container.stop();
		}

		@Override
		public void stop() {

		}
	    }, 2);
	    c.sendMessage("You find some garlic in the cupboard.");
	    break;
	case 8742:
	    if (c.objectX == 2305) {
		c.getPA().walkTo(-3, 0);
		break;
	    }
	case 2614:
	    if (!c.getVS().spawned && c.vampireslay == 4) {
		c.getVS().spawned = true;
		c.getPA().object(2615, 3077, 9775, 0, 10);
		Server.npcHandler.spawnNpc(c, 757, c.absX, c.absY, 0, 0, 35, 4,
			20, 10, true, false);
	    } else
		c.sendMessage("He's already dead.");
	    break;
	case 2616:
	    LadderHandler.climbStairs(c, 3077, 9771, 0, 0);
	    break;
	case 2617:
	    if (c.vampireslay == 5) {
		c.getVS().spawned = true;
		LadderHandler.climbStairs(c, 3116, 3356, 0, 0);
	    } else
		c.getVS().spawned = false;
	    LadderHandler.climbStairs(c, 3116, 3356, 0, 0);
	    break;
	case 12536: // Stairs wizard tower up
	    LadderHandler.climbStairs(c, 3104, 3161, 0, 1);
	    break;
	case 12537: // DIALOUGE OPTIONS
	    DialogueHandler.sendDialogues(c, 1355, 0);
	    break;
	case 12538: // go down.
	    LadderHandler.climbStairs(c, 3104, 3161, 2, 1);
	    break;

	case 1722:
	    if (c.objectX == 3099 && c.objectY == 3266)
		LadderHandler.climbStairs(c, 3102, 3267, 0, 1);
	    else if (c.objectX == 3175 && c.objectY == 3420)
		LadderHandler.climbStairs(c, c.objectX, c.objectY - 1, 0, 1);
	    else if (c.objectX == 3188 && c.objectY == 3355)
		LadderHandler.climbStairs(c, 3189, 3354, 0, 1);
	    break;
	case 1723:
	    if (c.objectX == 3100 && c.objectY == 3266)
		LadderHandler.climbStairs(c, 3098, 3267, 1, 0);
	    else
		LadderHandler.climbStairs(c, c.objectX, c.objectY + 3, 1, 0);
	    break;

	// end shortcuts
	case 9294:
	    if (c.objectX == 2879 && c.objectY == 9813) {
	    }
	    break;
	case 9293:
	    if (c.objectX == 2887 && c.objectY == 9799) {
		c.getPA().movePlayer(2892, 9799, 0);
	    }
	    if (c.objectX == 2890 && c.objectY == 9799) {
		c.getPA().movePlayer(2886, 9799, 0);
	    }
	    break;

	case 1568:
	    if (c.objectX == 3097 && c.objectY == 3468) {
		c.getPA().movePlayer(3096, 9867, 0);
	    }
	    break;
	case 2178:
	    if (c.objectX == 2675 && c.objectY == 3170) {
		DialogueHandler.sendDialogues(c, 79, 0);
	    }
	    break;
	case 2:
	    c.getPA().movePlayer(3029, 9582, 0);
	    break;

	/* Bank */
	case 2213:
	    c.npcType = 494;
	    DialogueSystem.sendDialogue(c,1, c.npcType);
	    break;

	case 2492:
	    if (c.objectX == 2889 && c.objectY == 4813) {
		if (c.aubury) {
		    c.getPA().startTeleport(3252, 3401, 0, "modern");
		} else if (c.sedridor) {
		    c.getPA().startTeleport(3104, 9570, 0, "modern");
		}
		c.aubury = false;
		c.sedridor = false;
	    }
	    break;

	case 2557:
	    if (c.getItems().playerHasItem(1523, 1) && c.absX == 3190
		    && c.absY == 3957) {
		c.getPA().movePlayer(3190, 3958, 0);
	    } else if (c.getItems().playerHasItem(1523, 1) && c.absX == 3190
		    && c.absY == 3958) {
		c.getPA().movePlayer(3190, 3957, 0);
	    }
	    break;

	case 2995:
	    c.getPA().startTeleport2(2717, 9801, 0);
	    c.sendMessage("Welcome to the dragon lair, be aware. It's very dangerous.");
	    break;

	case 1816:
	    c.getPA().startTeleport2(2271, 4680, 0);
	    break;
	case 1817:
	    c.getPA().startTeleport(3067, 10253, 0, "modern");
	    break;
	case 1814:
	    // ardy lever
	    c.getPA().startTeleport(3153, 3923, 0, "modern");
	    break;
	case 134:
	case 135:
	    if (c.getX() == 3109 && c.getY() == 3353) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
		    if (PlayerHandler.players[i] != null) {
			Client c2 = (Client) PlayerHandler.players[i];
			c2.getPA().object(135, 3109, 3353, 2, 0);
			c2.getPA().object(134, 3108, 3353, 0, 0);
		    }
		}

	    }

	    CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		@Override
		public void execute(CycleEventContainer container) {
		    for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
			    Client c2 = (Client) PlayerHandler.players[i];
			    c2.getPA().object(135, 3109, 3353, 5, 0);
			    c2.getPA().object(134, 3108, 3353, 5, 0);

			}
		    }

		    container.stop();
		}

		@Override
		public void stop() {

		}
	    }, 2);
	    break;
	case 2882: // al-kharid gate
	    if (obX == 3268 && obY == 3227
		    && c.getItems().playerHasItem(995, 10)) {
		DialogueHandler.sendDialogues(c, 500, 925);
	    } else if (obX == 3268 && obY == 3227) {
		DialogueHandler.sendDialogues(c, 510, 925);
	    }
	    break;
	case 2883: // al-kharid gate
	    if (obX == 3268 && obY == 3228
		    && c.getItems().playerHasItem(995, 10)) {
		DialogueHandler.sendDialogues(c, 500, 925);
	    } else if (obX == 3268 && obY == 3228) {
		DialogueHandler.sendDialogues(c, 510, 925);
	    }
	    break;

	case 1765:
	    c.getPA().movePlayer(3067, 10256, 0);
	    break;
	case 272:
	    c.getPA().movePlayer(c.absX, c.absY, 1);
	    break;

	case 273:
	    c.getPA().movePlayer(c.absX, c.absY, 0);
	    break;

	case 245:
	    c.getPA().movePlayer(c.absX, c.absY + 2, 2);
	    break;

	case 246:
	    c.getPA().movePlayer(c.absX, c.absY - 2, 1);
	    break;

	case 1766:
	    c.getPA().movePlayer(3016, 3849, 0);
	    break;

	case 6552:
	    if (c.playerMagicBook == 0) {
		c.playerMagicBook = 1;
		c.setSidebarInterface(6, 12855);
		c.autocasting = false;
		c.sendMessage("An ancient wisdomin fills your mind.");
		c.getPA().resetAutocast();
	    } else {
		c.setSidebarInterface(6, 1151); // modern
		c.playerMagicBook = 0;
		c.autocasting = false;
		c.sendMessage("You feel a drain on your memory.");
		c.autocastId = -1;
		c.getPA().resetAutocast();
	    }
	    break;

	case 1733:
	    if(obX == 3058 && obY == 3376)
	    c.getPA().movePlayer(3058, 9776, 0);
	    break;

	case 1734:
	    c.getPA().movePlayer(3061, 3376, 0);
	    break;

	case 8959:
	    if (c.getX() == 2490 && (c.getY() == 10146 || c.getY() == 10148)) {
		if (c.getPA().checkForPlayer(2490,
			c.getY() == 10146 ? 10148 : 10146)) {
		    new Object(6951, c.objectX, c.objectY, c.heightLevel, 1,
			    10, 8959, 15);
		}
	    }
	    break;

	case 10177:
	    c.getPA().movePlayer(1890, 4407, 0);
	    break;
	case 10230:
	    c.getPA().movePlayer(2900, 4449, 0);
	    break;
	case 10229:
	    c.getPA().movePlayer(1912, 4367, 0);
	    break;

	case 2623:
	    if (c.absX >= c.objectX)
		c.getPA().walkTo(-1, 0);
	    else
		c.getPA().walkTo(-1, 0);
	    break;
	// pc boat
	case 14315:
	    c.getPA().movePlayer(2661, 2639, 0);
	    break;
	case 14314:
	    c.getPA().movePlayer(2657, 2639, 0);
	    break;

	case 4031:
	case 1596:
	case 1597:
	case 3198:
	case 3197:
	case 9295:
	case 12163:
	case 8391:
	case 1599:
	case 1598:
	case 12165:
	case 10558:
	case 9299:
	case 5947:
	    c.membersonly();
	    break;

	/**
	 * Guilds
	 */
	// prayer
	case 2640: // prayer alter prayer guild
	    c.startAnimation(645);
	    c.sendMessage("You recharge your prayer points.");
	    break;

	case 2712: // Cooking Guild
	    if (!(c.playerLevel[c.playerCooking] >= 32)) {
		DialogueHandler.sendDialogues(c, 2793, 847);
		return;
	    } else if (c.playerLevel[c.playerCooking] >= 32
		    && !(c.playerEquipment[c.playerHat] == 1949)
		    && !c.guildDoor) {
		DialogueHandler.sendDialogues(c, 2794, 847);
		return;
	    }
	    if (c.getX() == 3143 && c.getY() == 3444) {
		Server.objectHandler.createAnObject(2712, obX, obY, 2, 0);
		c.guildDoor = false;
		c.getPA().walkTo(0, -1);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {// animation
			    @Override
			    public void execute(CycleEventContainer container) {
				Server.objectHandler.createAnObject(2712, obX,
					obY, -1, 0);
				// 3023
				container.stop();
			    }

			    @Override
			    public void stop() {
			    }
			}, 2);
		return;
	    }
	    if (!c.guildDoor) {
		Server.objectHandler.createAnObject(2712, obX, obY, 2, 0);
		c.getPA().walkTo(0, 1);
		c.sendMessage("You enter the guild of Cooks.");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {// animation
			    @Override
			    public void execute(CycleEventContainer container) {
				Server.objectHandler.createAnObject(2712, obX,
					obY, -1, 0);
				// 3023
				c.guildDoor = true;
				container.stop();
			    }

			    @Override
			    public void stop() {
			    }
			}, 2);
	    }
	    break;

	/**
	 * guilds end
	 */

	case 14235:
	case 14233:
	    if (c.objectX == 2670)
		if (c.absX <= 2670)
		    c.absX = 2671;
		else
		    c.absX = 2670;
	    if (c.objectX == 2643)
		if (c.absX >= 2643)
		    c.absX = 2642;
		else
		    c.absX = 2643;
	    if (c.absX <= 2585)
		c.absY += 1;
	    else
		c.absY -= 1;
	    c.getPA().movePlayer(c.absX, c.absY, 0);
	    break;

	case 14829:
	case 14830:
	case 14827:
	case 14828:
	case 14826:
	case 14831:
	    // Server.objectHandler.startObelisk(objectType);
	    Server.objectManager.startObelisk(objectType);
	    break;

	case 9369:
	    if (c.getY() > 5175)
		c.getPA().movePlayer(2399, 5175, 0);
	    else
		c.getPA().movePlayer(2399, 5177, 0);
	    break;

	// doors
	case 6749:
	    if (obX == 3562 && obY == 9678) {
		c.getPA().object(3562, 9678, 6749, -3, 0);
		c.getPA().object(3562, 9677, 6730, -1, 0);
	    } else if (obX == 3558 && obY == 9677) {
		c.getPA().object(3558, 9677, 6749, -1, 0);
		c.getPA().object(3558, 9678, 6730, -3, 0);
	    }
	    break;
	case 6730:
	    if (obX == 3558 && obY == 9677) {
		c.getPA().object(3562, 9678, 6749, -3, 0);
		c.getPA().object(3562, 9677, 6730, -1, 0);
	    } else if (obX == 3558 && obY == 9678) {
		c.getPA().object(3558, 9677, 6749, -1, 0);
		c.getPA().object(3558, 9678, 6730, -3, 0);
	    }
	    break;
	case 6727:
	    if (obX == 3551 && obY == 9684) {
		c.sendMessage("You can't open this door...");
	    }
	    break;
	case 6746:
	    if (obX == 3552 && obY == 9684) {
		c.sendMessage("You can't open this door...");
	    }
	    break;
	case 6748:
	    if (obX == 3545 && obY == 9678) {
		c.getPA().object(3545, 9678, 6748, -3, 0);
		c.getPA().object(3545, 9677, 6729, -1, 0);
	    } else if (obX == 3541 && obY == 9677) {
		c.getPA().object(3541, 9677, 6748, -1, 0);
		c.getPA().object(3541, 9678, 6729, -3, 0);
	    }
	    break;
	case 6729:
	    if (obX == 3545 && obY == 9677) {
		c.getPA().object(3545, 9678, 6748, -3, 0);
		c.getPA().object(3545, 9677, 6729, -1, 0);
	    } else if (obX == 3541 && obY == 9678) {
		c.getPA().object(3541, 9677, 6748, -1, 0);
		c.getPA().object(3541, 9678, 6729, -3, 0);
	    }
	    break;
	case 6726:
	    if (obX == 3534 && obY == 9684) {
		c.getPA().object(3534, 9684, 6726, -4, 0);
		c.getPA().object(3535, 9684, 6745, -2, 0);
	    } else if (obX == 3535 && obY == 9688) {
		c.getPA().object(3535, 9688, 6726, -2, 0);
		c.getPA().object(3534, 9688, 6745, -4, 0);
	    }
	    break;
	case 6745:
	    if (obX == 3535 && obY == 9684) {
		c.getPA().object(3534, 9684, 6726, -4, 0);
		c.getPA().object(3535, 9684, 6745, -2, 0);
	    } else if (obX == 3534 && obY == 9688) {
		c.getPA().object(3535, 9688, 6726, -2, 0);
		c.getPA().object(3534, 9688, 6745, -4, 0);
	    }
	    break;
	case 6743:
	    if (obX == 3545 && obY == 9695) {
		c.getPA().object(3545, 9694, 6724, -1, 0);
		c.getPA().object(3545, 9695, 6743, -3, 0);
	    } else if (obX == 3541 && obY == 9694) {
		c.getPA().object(3541, 9694, 6724, -1, 0);
		c.getPA().object(3541, 9695, 6743, -3, 0);
	    }
	    break;
	case 6724:
	    if (obX == 3545 && obY == 9694) {
		c.getPA().object(3545, 9694, 6724, -1, 0);
		c.getPA().object(3545, 9695, 6743, -3, 0);
	    } else if (obX == 3541 && obY == 9695) {
		c.getPA().object(3541, 9694, 6724, -1, 0);
		c.getPA().object(3541, 9695, 6743, -3, 0);
	    }
	    break;

	// DOORS
	case 1516:
	case 1519:
	    if (c.objectY == 9698) {
		if (c.absY >= c.objectY)
		    c.getPA().walkTo(0, -1);
		else
		    c.getPA().walkTo(0, 1);
		break;
	    }
	    /**
	     * tutorial island objects
	     */
	case 3015:
	case 3016:
	    if (c.tutorialprog == 7) {
		c.getPA().walkTo(-1, 0);
		c.getPA().object(3015, 3089, 3092, 1, 0);
		c.getPA().object(3016, 3088, 3092, 1, 0);

		c.getPA().object(-1, 3089, 3091, 0, 0); // removes orig

		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			c.getPA().object(3015, 3090, 3092, 0, 0);// creates orgi
			c.getPA().object(3016, 3090, 3091, 0, 0);

			// others
			c.getPA().object(-1, 3089, 3092, 0, 0);
			c.getPA().object(-1, 3088, 3092, 0, 0);

			container.stop();
		    }

		    @Override
		    public void stop() {

		    }
		}, 5);
		Tutorialisland.sendDialogue(c, 3020, 0);
	    }
	    break;
	case 3018: // door again
	    if (c.tutorialprog == 10) {
		c.getPA().walkTo(-1, 0);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			    if (PlayerHandler.players[i] != null) {
				Client c2 = (Client) PlayerHandler.players[i];
				c2.getPA().object(3018, 3072, 3090, 2, 0);
				// others
				c2.getPA().object(-1, 3073, 3090, 0, 0);

				container.stop();

			    }
			}
		    }

		    @Override
		    public void stop() {

		    }
		}, 3);

		Tutorialisland.sendDialogue(c, 3038, 0);
	    }
	    break;
	case 3017:// door tutorial isalnd
	    if (c.tutorialprog == 7) {
		c.getPA().walkTo(-1, 0);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		    @Override
		    public void execute(CycleEventContainer container) {
			c.getPA().object(3017, 3079, 3084, 4, 0);
			// others
			c.getPA().object(-1, 3078, 3084, 0, 0);

			container.stop();
		    }

		    @Override
		    public void stop() {

		    }
		}, 3);
		c.getPA().drawHeadicon(1, 3, 0, 0); // sends to chef leo
	    }
	    break;
	case 1530:
	case 1531:
	case 1533:
	case 1534:
	case 11712:
	case 11711:
	case 11707:
	case 11708:
	case 6725:

	    Server.objectHandler.doorHandling(objectType, c.objectX, c.objectY,
		    0);
	    break;

	case 9319:
	    if (c.heightLevel == 0)
		c.getPA().movePlayer(c.absX, c.absY, 1);
	    else if (c.heightLevel == 1)
		c.getPA().movePlayer(c.absX, c.absY, 2);
	    break;

	case 9320:
	    if (c.heightLevel == 1)
		c.getPA().movePlayer(c.absX, c.absY, 0);
	    else if (c.heightLevel == 2)
		c.getPA().movePlayer(c.absX, c.absY, 1);
	    break;

	case 4496:
	case 4494:
	    if (c.heightLevel == 2) {
		c.getPA().movePlayer(c.absX - 5, c.absY, 1);
	    } else if (c.heightLevel == 1) {
		c.getPA().movePlayer(c.absX + 5, c.absY, 0);
	    }
	    break;

	case 4493:
	    if (c.heightLevel == 0) {
		c.getPA().movePlayer(c.absX - 5, c.absY, 1);
	    } else if (c.heightLevel == 1) {
		c.getPA().movePlayer(c.absX + 5, c.absY, 2);
	    }
	    break;

	case 4495:
	    if (c.heightLevel == 1) {
		c.getPA().movePlayer(c.absX + 5, c.absY, 2);
	    }
	    break;

	case 5126:
	    if (c.absY == 3554)
		c.getPA().walkTo(0, 1);
	    else
		c.getPA().walkTo(0, -1);
	    break;

	case 1759:
	    if (c.objectX == 2884 && c.objectY == 3397) {
		c.getPA().movePlayer(c.absX, c.absY + 6400, 0);
	    } else if (c.objectX == 2845 && c.objectY == 3516) {
		c.getPA().movePlayer(2884, 9798, 0);
	    } else if (c.objectX == 2848 && c.objectY == 3513) {
		c.getPA().movePlayer(2884, 9798, 0);
	    } else if (c.objectX == 2848 && c.objectY == 3519) {
		c.getPA().movePlayer(2884, 9798, 0);
	    }
	    break;
	case 1558:
	    if (c.absX == 3041 && c.absY == 10308) {
		c.getPA().movePlayer(3040, 10308, 0);
	    } else if (c.absX == 3040 && c.absY == 10308) {
		c.getPA().movePlayer(3041, 10308, 0);
	    } else if (c.absX == 3040 && c.absY == 10307) {
		c.getPA().movePlayer(3041, 10307, 0);
	    } else if (c.absX == 3041 && c.absY == 10307) {
		c.getPA().movePlayer(3040, 10307, 0);
	    } else if (c.absX == 3044 && c.absY == 10341) {
		c.getPA().movePlayer(3045, 10341, 0);
	    } else if (c.absX == 3045 && c.absY == 10341) {
		c.getPA().movePlayer(3044, 10341, 0);
	    } else if (c.absX == 3044 && c.absY == 10342) {
		c.getPA().movePlayer(3045, 10342, 0);
	    } else if (c.absX == 3045 && c.absY == 10342) {
		c.getPA().movePlayer(3044, 10343, 0);
	    } else if (c.absX == 3103) {
		c.getPA().movePlayer(3104, c.absY, 0);
	    } else if (c.absX == 3104) {
		c.getPA().movePlayer(3103, c.absY, 0);
	    }
	    break;
	case 1569:

	    break;
	case 1557:
	    if (c.absX == 3023 && c.absY == 10312) {
		c.getPA().movePlayer(3022, 10312, 0);
	    } else if (c.absX == 3022 && c.absY == 10312) {
		c.getPA().movePlayer(3023, 10312, 0);
	    } else if (c.absX == 3023 && c.absY == 10311) {
		c.getPA().movePlayer(3022, 10311, 0);
	    } else if (c.absX == 3022 && c.absY == 10311) {
		c.getPA().movePlayer(3023, 10311, 0);
	    } else if (c.objectX == 3145 && c.objectY == 9871) {
		Server.objectManager.placeObject(new Object(1557, obX, obY,
			c.heightLevel, -1, 0, 1596, 0));
	    } else if (c.absX == 3103) {
		c.getPA().movePlayer(3104, c.absY, 0);
	    } else if (c.absX == 3104) {
		c.getPA().movePlayer(3103, c.absY, 0);
	    }
	    break;
	case 1754:
	    c.startAnimation(828);
	    c.getPA().movePlayer(3117, 9852, 0);
	    break;
	case 409:
	case 4859:
	    if (c.playerLevel[5] < c.getPA().getLevelForXP(c.playerXP[5])) {
		c.startAnimation(645);
		c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
		c.sendMessage("You recharge your prayer points.");
		c.getPA().sound(442);
		c.getPA().refreshSkill(5);

	    } else {
		c.sendMessage("You already have full prayer points.");
	    }
	    break;
	case 3044:
	    Smithing.startSmelting(c, objectType);
	    break;

	default:
	    final String name = ObjectDef.getObjectDef(objectType).name;
	    if (name.equalsIgnoreCase("gate")) {
		Server.objectHandler.createAnObject(-1, obX, obY, 0, 0);
		return;
	    }
	    if(name.equalsIgnoreCase("bank booth")) {
		 c.npcType = 494;
		    DialogueSystem.sendDialogue(c,1, c.npcType);
		return;
	    }
	    if(name.contains("Deposit Box")){
			c.getPA().sendFrame126("The Bank of Tyden - Deposit Box", 7421);
			c.getPA().sendFrame248(4465, 197);
			c.getItems().resetItems(7423);
		return;
	    }
	    if (name1.equalsIgnoreCase("furnace")) {
		    Smithing.startSmelting(c, objectType);
		    return;
		}
	    ScriptManager.callFunc("objectClick1_" + objectType, c, objectType,
		    obX, obY);
	    break;

	}
    }

    public void secondClickObject(int objectType, int obX, int obY) {
	if (c.stopPlayerPacket || c.spinningPlate
		|| System.currentTimeMillis() - c.lastObjectClick < 900) {
	    return;
	}
	c.lastObjectClick = System.currentTimeMillis();
	c.clickObjectType = 0;
	final String name = ObjectDef.getObjectDef(objectType).name;
	if (name.equalsIgnoreCase("furnace")) {
	    Smithing.startSmelting(c, objectType);
	    return;
	}
	switch (objectType) {
	case 823: // dummies
	    if (c.playerEquipment[c.playerWeapon] == -1) {
		if (c.fightMode == 0) {
		    if (c.playerLevel[0] <= 8) {
			c.startAnimation(422);
			c.getPA().addSkillXP(8, 0);
		    } else {
			c.sendMessage("You have learned everything you can from this.");
		    }
		}
		if (c.fightMode == 2) {
		    if (c.playerLevel[2] <= 8) {
			c.startAnimation(423);
			c.getPA().addSkillXP(8, 2);
		    } else {
			c.sendMessage("You have learned everything you can from this.");
		    }
		}
		if (c.fightMode == 1) {
		    if (c.playerLevel[1] <= 8) {
			c.startAnimation(422);
			c.getPA().addSkillXP(8, 1);
		    } else {
			c.sendMessage("You have learned everything you can from this.");
		    }
		}
	    } else {
		c.sendMessage("You cannot attack a dummy while wielding a weapon.");
	    }
	    break;
	case 2145:
	    c.sendMessage("I should open the coffin first.");
	    break;
	case 2146:
	    if (c.objectX == 3249 && c.objectY == 3192) {
		if (c.restlessG <= 3) {
		    if (c.ghostSpawned == false) {
			Server.npcHandler.spawnNpc(c, 457, 3250, 3195, 0, 1, 0,
				0, 0, 0, false, false);
			c.ghostSpawned = true;
		    }
		    c.sendMessage("There's a skeleton with no skull.");
		} else {
		    c.sendMessage("There's a skeleton.");
		}
	    } else {
		c.sendMessage("You don't find anything interesting.");
	    }
	    break;
	case 2644:
	    SpinningWheel.sendInterface(c, objectType);
	    break;
	case 8391:
	    c.membersonly();
	    break;

	case 1161:
	case 313:
	case 5585:
	case 5584:
	case 312:
	case 3366:
	    Pickable.pickObject(c, c.objectId, c.objectX, c.objectY);
	    break;

	case 2781:
	    Smithing.startSmelting(c, objectType);
	    break;

	case 2090:
	case 2091:
	case 3042:
	    Mining.prospectRock(c, "copper ore");
	    break;
	case 2094:
	case 2095:
	case 3043:
	    Mining.prospectRock(c, "tin ore");
	    break;
	case 2110:
	    Mining.prospectRock(c, "blurite ore");
	    break;
	case 2092:
	case 2093:
	    Mining.prospectRock(c, "iron ore");
	    break;
	case 2100:
	case 2101:
	    Mining.prospectRock(c, "silver ore");
	    break;
	case 2098:
	case 2099:
	    Mining.prospectRock(c, "gold ore");
	    break;
	case 2096:
	case 2097:
	    Mining.prospectRock(c, "coal");
	    break;
	case 2102:
	case 2103:
	    Mining.prospectRock(c, "mithril ore");
	    break;
	case 2104:
	case 2105:
	    Mining.prospectRock(c, "adamantite ore");
	    break;
	case 14860:
	case 14859:
	case 2106:
	case 2107:
	    Mining.prospectRock(c, "runite ore");
	    break;
	case 2491:
	    Mining.prospectRock(c, "rune essence");
	    break;
	case 9713:
	case 9711:
	case 2109:
	case 2108:
	    Mining.prospectRock(c, "clay");
	    break;
	case 450:
	case 451:
	    Mining.prospectNothing(c);
	    break;
	case 2213:
	    c.getPA().openUpBank();
	    break;
	case 2558:
	    if (System.currentTimeMillis() - c.lastLockPick < 3000
		    || c.freezeTimer > 0)
		break;
	    if (c.getItems().playerHasItem(1523, 1)) {
		c.lastLockPick = System.currentTimeMillis();
		if (Misc.random(10) <= 3) {
		    c.sendMessage("You fail to pick the lock.");
		    break;
		}
		if (c.objectX == 3044 && c.objectY == 3956) {
		    if (c.absX == 3045) {
			c.getPA().walkTo2(-1, 0);
		    } else if (c.absX == 3044) {
			c.getPA().walkTo2(1, 0);
		    }

		} else if (c.objectX == 3038 && c.objectY == 3956) {
		    if (c.absX == 3037) {
			c.getPA().walkTo2(1, 0);
		    } else if (c.absX == 3038) {
			c.getPA().walkTo2(-1, 0);
		    }
		} else if (c.objectX == 3041 && c.objectY == 3959) {
		    if (c.absY == 3960) {
			c.getPA().walkTo2(0, -1);
		    } else if (c.absY == 3959) {
			c.getPA().walkTo2(0, 1);
		    }
		}
	    } else {
		c.sendMessage("I need a lockpick to pick this lock.");
	    }
	    break;
	default:
	    if(name.equalsIgnoreCase("bank booth")) {
		c.getPA().openUpBank();
		return;
	    }
	    ScriptManager.callFunc("objectClick2_" + objectType, c, objectType,
		    obX, obY);
	    break;
	}
    }

    public void thirdClickObject(int objectType, int obX, int obY) {
	c.clickObjectType = 0;
	c.sendMessage("Object type: " + objectType);
	switch (objectType) {
	default:

	    ScriptManager.callFunc("objectClick3_" + objectType, c, objectType,
		    obX, obY);
	    break;
	}
    }

    public void firstClickNpc(int npcType) {
	c.clickNpcType = 0;
	//c.npcClickIndex = 0;
	if (Fishing.fishingNPC(c, npcType)) {
	    Fishing.fishingNPC(c, 1, npcType);
	    if(!c.whirlpool) {
	   c.fishNPC = npcType;
	    }
	    return;
	} else if(Whirlpool.isWhirlPoolNpc(c, npcType)) {
	    Fishing.fishingNPC(c, 1, npcType);
	    return;
	}
	switch (npcType) {
	// black knight's fortress
	case 608:
	    if (c.blackK == 0) {
		BlackKnightsD.dialogue(c, 4010, 608);
	    }
	    break;
	case 606:
	    if (c.knightS == 0) {
		KnightSwordD.dialogue(c, 610, 606);
	    }
	    if (c.knightS == 4) {
		KnightSwordD.dialogue(c, 648, 606);
	    }
	    if (c.knightS == 8) {
		KnightSwordD.dialogue(c, 669, 606);
	    }
	    break;
	case 2660:
	    if (c.knightS == 1) {
		KnightSwordD.dialogue(c, 626, 2660);
	    }
	    break;
	case 604:
	    if (c.knightS == 2) {
		KnightSwordD.dialogue(c, 636, 604);
	    }
	    if (c.knightS == 3) {
		KnightSwordD.dialogue(c, 642, 604);
	    }
	    if (c.knightS == 6) {
		KnightSwordD.dialogue(c, 654, 604);
	    }
	    if (c.knightS == 7) {
		KnightSwordD.dialogue(c, 662, 604);
	    }
	    break;
	case 553:
	    if (c.runeM == 3) {
		RuneMysteriesD.dialogue(c, 360, 553);
	    } else if (c.runeM == 4) {
		RuneMysteriesD.dialogue(c, 367, 553);
	    } else if (c.runeM == 5) {
		RuneMysteriesD.dialogue(c, 368, 553);
	    }
	    break;
	case 300:
	    if (c.runeM == 1) {
		RuneMysteriesD.dialogue(c, 164, 300);
	    } else if (c.runeM == 2) {
		RuneMysteriesD.dialogue(c, 173, 300);
	    } else if (c.runeM == 6) {
		RuneMysteriesD.dialogue(c, 371, 300);
	    }
	    break;
	case 741:
	    if (c.runeM == 0) {
		RuneMysteriesD.dialogue(c, 153, npcType);
	    }
	    break;
	case 284:
	    if (c.doricsQ == 0) {
		DoricsQuestD.dialogue(c, 300, 284);
	    } else if (c.doricsQ == 1) {
		DoricsQuestD.dialogue(c, 308, 284);
	    } else if (c.doricsQ == 3) {
		DoricsQuestD.dialogue(c, 312, 284);
	    }
	    break;
	case 758:
	    if (c.sheepS == 0) {
		SheepShearerD.dialogue(c, 515, 758);
	    }
	    if (c.sheepS == 1) {
		SheepShearerD.dialogue(c, 533, 758);
	    }
	    if (c.sheepS == 2) {
		SheepShearerD.dialogue(c, 540, 748);
	    }
	    break;
	case 379:// luthas
	    if (c.luthas == 0)
		DialogueHandler.sendDialogues(c, 4000, 379);
	    if (c.luthas == 1)
		DialogueHandler.sendDialogues(c, 4004, 379);
	    if (c.luthas == 2) {
		DialogueHandler.sendDialogues(c, 4005, 379);
	    }
	    break;
	case 409:
	    c.talkedto = true;
	    break;
	case 804:
	case 2824:
	    Tanning.sendTanningInterface(c);
	    break;
	case 925: // al-kharid gate
	case 926:
	    if (c.getItems().playerHasItem(995, 10)) {
		DialogueHandler.sendDialogues(c, 500, 925);
	    } else {
		DialogueHandler.sendDialogues(c, 510, 925);
	    }
	    break;

	case 484:
	    DialogueHandler.sendDialogues(c, 88, 484);
	    break;
	/* Quests */
	// restless ghost
	case 456: // father araeck
	    if (c.restlessG == 0) {
		RestlessGhostD.dialogue(c, 541, 456);
	    }
	    break;
	case 457:
	    if (c.restlessG == 0 || c.restlessG == 1) {
		RestlessGhostD.dialogue(c, 557, 457);
	    }
	    if (c.restlessG == 2 && c.playerEquipment[c.playerAmulet] == 552) {
		RestlessGhostD.dialogue(c, 581, 457);
	    } else {
		RestlessGhostD.dialogue(c, 557, 457);
	    }
	    break;
	case 458:
	    if (c.restlessG == 0) {
		RestlessGhostD.dialogue(c, 580, 458);
	    }
	    if (c.restlessG == 1) {
		RestlessGhostD.dialogue(c, 558, 458);
	    }
	    if (c.restlessG >= 2) {
		RestlessGhostD.dialogue(c, 580, 458);
	    }
	    break;
	case 307: // betty
	    if (c.witchspot == 0) {
		WitchsPotion.handledialogue(c, 700, 307);
	    } else if (c.witchspot == 1) {
		WitchsPotion.handledialogue(c, 712, 307);
	    } else if (c.witchspot == 2) {
		WitchsPotion.handledialogue(c, 720, 307);
	    }
	    break;
	case 733:
	    c.getVS().sendDialogue(738, 731);
	    break;
	case 756: // dr fag
	    if (c.vampireslay == 1)
		c.getVS().sendDialogue(730, 756);
	    if (c.vampireslay == 2)
		c.getVS().sendDialogue(740, 756);
	    if (c.vampireslay == 3)
		c.getVS().sendDialogue(742, 756);
	    break;
	case 755:// morgan
	    if (c.vampireslay == 0)
		c.getVS().sendDialogue(722, 755);
	    if (c.vampireslay > 0)
		c.getVS().sendDialogue(7298, 755);
	    break;
	case 278:
	    if (c.cooksA == 0) {
		CooksAssistantD.dialogue(c, 100, 278);
	    } else if (c.cooksA == 1) {
		CooksAssistantD.dialogue(c, 108, 278);
	    } else if (c.cooksA == 2) {
		CooksAssistantD.dialogue(c, 110, 278);
	    } else if (c.cooksA == 3) {
		CooksAssistantD.dialogue(c, 112, 278);
	    }
	    break;
	/*
	 * tutorial island
	 */
	case 945:
	    if (c.tutorialprog == 0)
		Tutorialisland.sendDialogue(c, 3001, 945);
	    if (c.tutorialprog == 1)
		Tutorialisland.sendDialogue(c, 3008, 945);
	    if (c.tutorialprog == 2)
		Tutorialisland.sendDialogue(c, 0, 945);

	    break;
	case 943:// survival
	    if (c.tutorialprog == 2)
		Tutorialisland.sendDialogue(c, 3012, 943);
	    if (c.tutorialprog == 5) {
		Tutorialisland.sendDialogue(c, 3017, 943);
		Tutorialisland.TII(c, 10, 3);
	    }
	    break;
	case 942: // master chef
	    if (c.tutorialprog == 7)
		Tutorialisland.sendDialogue(c, 3021, 942);
	    break;
	case 949: // quest guide
	    if (c.tutorialprog == 12)
		Tutorialisland.sendDialogue(c, 3043, 949);
	    if (c.tutorialprog == 13)
		Tutorialisland.sendDialogue(c, 3045, 949);
	    break;
	case 948: // mining tutor
	    if (c.tutorialprog == 14)
		Tutorialisland.sendDialogue(c, 3052, 948);
	    if (c.tutorialprog == 16)
		Tutorialisland.sendDialogue(c, 3056, 948);
	    if (c.tutorialprog == 20)
		Tutorialisland.sendDialogue(c, 3063, 948);
	    break;
	case 944: // Combat deud
	    if (c.tutorialprog == 21||c.tutorialprog == 22)
		Tutorialisland.sendDialogue(c, 3067, 944);
	    if (c.tutorialprog == 23)
		Tutorialisland.sendDialogue(c, 3072, 944);
	    if (c.tutorialprog == 25)
		Tutorialisland.sendDialogue(c, 3074, 944);

	    break;

	case 947: // fiancial dude
	    if (c.tutorialprog == 27)
		Tutorialisland.sendDialogue(c, 3079, 947);
	    break;

	case 954: // prayer dude
	    if (c.tutorialprog == 28)
		Tutorialisland.sendDialogue(c, 3089, 222);
	    if (c.tutorialprog == 29)
		Tutorialisland.sendDialogue(c, 3092, 222);
	    if (c.tutorialprog == 31)
		Tutorialisland.sendDialogue(c, 3097, 222);
	    break;
	case 946:// mage
	    if (c.tutorialprog == 32)
		Tutorialisland.sendDialogue(c, 3105, 946);
	    if (c.tutorialprog == 33)
		Tutorialisland.sendDialogue(c, 3108, 946);
	    if (c.tutorialprog == 34)
		Tutorialisland.sendDialogue(c, 3110, 946);
	    if (c.tutorialprog == 35)
		Tutorialisland.sendDialogue(c, 3112, 946);
	    break;
	case 598:
	    HairDresser.dialogue(c, 1300, 598);
	    break;
	case 376:
	    Sailing.sailingDia(c, 902, 376);// captain tobias
	    break;
	case 378:
	    Sailing.sailingDia(c, 900, 378);// Seaman
	    break;
	case 377:
	    Sailing.sailingDia(c, 901, 377);
	    break;
	case 380:
	    Sailing.sailingDia(c, 907, 380); // customs officer
	    break;

	case 209:
	    DialogueHandler.sendDialogues(c, 86, 209);
	    break;
	case 1917:
	    DialogueHandler.sendDialogues(c, 84, 1917);
	    break;
	case 2201:
	    DialogueHandler.sendDialogues(c, 83, 2201);
	    break;
	case 2262:
	    DialogueHandler.sendDialogues(c, 17, 462);
	    break;
	case 1696:
	    DialogueHandler.sendDialogues(c, 81, 1696);
	    break;
	case 559:
	    DialogueHandler.sendDialogues(c, 999, 559);
	    break;
	case 291:
	    DialogueHandler.sendDialogues(c, 77, 291);
	    break;
	case 545:
	    DialogueHandler.sendDialogues(c, 999, 545);
	    break;
	case 1658:
	    DialogueHandler.sendDialogues(c, 999, 1658);
	    break;
	case 455:
	    DialogueHandler.sendDialogues(c, 73, 455);
	    break;
	case 692:
	    DialogueHandler.sendDialogues(c, 75, 692);
	    break;
	case 706:
	    if (c.impC == 0) {
		ImpCatcherD.dialogue(c, 328, 706);
	    } else if (c.impC == 1) {
		ImpCatcherD.dialogue(c, 346, 706);
	    } else if (c.impC == 2) {
		ImpCatcherD.dialogue(c, 352, 706);
	    }
	    break;
	case 308:
	    DialogueHandler.sendDialogues(c, 66, 308);
	    break;
	case 599:
	    DialogueHandler.sendDialogues(c, 63, 599);
	    break;
	case 802:
	    DialogueHandler.sendDialogues(c, 60, 802);
	    break;
	case 1304:
	    DialogueHandler.sendDialogues(c, 58, 1304);
	    break;
	case 201:
	    DialogueHandler.sendDialogues(c, 9001, 201);
	    break;
	case 1598:
	    if (c.slayerTask <= 0) {
		DialogueHandler.sendDialogues(c, 11, npcType);
	    } else {
		DialogueHandler.sendDialogues(c, 13, npcType);
	    }
	    break;

	case 1526:
	    DialogueHandler.sendDialogues(c, 55, npcType);
	    break;

	// case 212:
	case 589:
	    DialogueHandler.sendDialogues(c, 56, npcType);
	    break;

	case 1152:
	    DialogueHandler.sendDialogues(c, 16, npcType);
	    break;

	case 905:
	    DialogueHandler.sendDialogues(c, 5, npcType);
	    break;
	case 460:
	    DialogueHandler.sendDialogues(c, 3, npcType);
	    break;

	case 904:
	    c.sendMessage("You have " + c.magePoints + " points.");
	    break;

	case 812:
	    DialogueHandler.sendDialogues(c, 998, npcType);
	    break;

	case 546:
	    DialogueHandler.sendDialogues(c, 1004, npcType);
	    break;
	case 548:
	    DialogueHandler.sendDialogues(c, 1008, npcType);
	    break;
	case 641:
	    DialogueHandler.sendDialogues(c, 997, npcType);
	    break;

	/* Dialogue system. Used only when dialogue is in Misc. */
	case 0:
	    DialogueSystem.sendDialogue(c, 7, npcType);
	    break;
	case 2244:
	    DialogueSystem.sendDialogue(c, 12, npcType);
	    break;
	// NEW DIALOUGE SYSTEM END
	default:
	    DialogueSystem.sendDialogue(c, 1, npcType);
	    ScriptManager.callFunc("npcClick1_" + npcType, c, npcType);
	    if (c.playerRights == 3)
		Misc.println("First Click Npc : " + npcType);
	    break;
	}
    }

    public void secondClickNpc(int npcType) {
	c.clickNpcType = 0;
	//c.npcClickIndex = 0;

	if (Fishing.fishingNPC(c, npcType)) {
	    Fishing.fishingNPC(c, 2, npcType);
	    if(!c.whirlpool) {
		   c.fishNPC = npcType;
		    }
		    return;
		} else if(Whirlpool.isWhirlPoolNpc(c, npcType)) {
		    Fishing.fishingNPC(c, 2, npcType);
		    return;
	}

	switch (npcType) {
	case 2824:
	    Tanning.sendTanningInterface(c);
	    break;
	case 300: // sevidor
	    if (c.runeM == 7) {
		c.getPA().startTeleport2(2898, 4819, 0);
		c.sedridor = true;
	    } else {
		c.sendMessage("You must complete Rune Mysteries first.");
	    }
	    break;
	case 538:// Peska helmet
	    c.getShops().openShop(99);
	    break;
	case 209:
	    c.getShops().openShop(22);
	    break;
	case 1917:
	    c.getShops().openShop(21);
	    break;
	case 2620:
	    c.getShops().openShop(20);
	    break;
	case 1658:
	    c.getShops().openShop(14);
	    break;
	// /SHOPS - By Ambient
	case 2721:
	    c.getShops().openShop(27);
	    break;
	case 1860: // rimington brian's archery
	    c.getShops().openShop(100);
	    break;

	case 585: // Rominik crafting
	    c.getShops().openShop(21);
	    break;

	case 524:
	case 525:
	    c.getShops().openShop(101); // Rimmington General Store
	    break;
	case 531:
	case 530:
	    c.getShops().openShop(105); // Rimmington General Store
	    break;
	case 521:
	case 520: //
	    c.getShops().openShop(1); // Lumberidge General Store
	    break;
	case 550: // LOWE
	    c.getShops().openShop(3); // Lowe's Archery Emporium
	    break;
	case 549: // HORVIK
	    c.getShops().openShop(4); // Horvik's Armour Shop
	    break;
	case 546: // ZAFF
	    c.getShops().openShop(5); // Zaff's Superior Staves
	    break;
	case 548: // THESSALIA
	    c.getShops().openShop(6); // Thessalia's Rare's
	    break;

	case 552:
	case 551: // SWORD SUPLIES// VAROCK
	    c.getShops().openShop(7);
	    break;
	case 553: // AUBURY
	    c.getShops().openShop(8); // Aubury's Rune Shop
	    break;
	case 526: //
	case 527:
	    c.getShops().openShop(9); // Falador General Store
	    break;
	case 580: // FLYNN
	    c.getShops().openShop(11); // Flynn's Mace Shop
	    break;
	case 577: // CASSIE
	    c.getShops().openShop(12); // Cassie's shield shop
	    break;
	case 584: // HERQUIN
	    c.getShops().openShop(13); // Herquin's Gems
	    break;
	case 581: // WAYNE
	    c.getShops().openShop(14); // Wayne's Chains
	    break;
	case 736: //
	    c.getShops().openShop(15); // Pub
	    break;
	case 519: // BOB
	    c.getShops().openShop(16); // Bob's Brilliant Axes
	    break;
	case 533:
	case 532: //
	    c.getShops().openShop(103); // Edgeville General Store
	    break;
	case 523:
	case 522: //
	    if(c.getPA().inRegion(51, 49))
	    c.getShops().openShop(91); // Alk General Store
	   else
	   c.getShops().openShop(90); // Varrock General Store
	    break;
	case 541: //
	    c.getShops().openShop(18); // Zeke's Superior Scimitars
	    break;
	case 540: //
	    c.getShops().openShop(20); // Gem trader suplies
	    break;
	case 545: // DOMMIK
	    c.getShops().openShop(109); // Dommik's Crafting Shop
	    break;
	case 542: // LOUIE
	    c.getShops().openShop(23); // Louie's Armoured Legs Bazaar
	    break;
	case 544: // RANAEL
	    c.getShops().openShop(24); // Ranael's Super Skirt Store
	    break;
	case 836: //
	    c.membersonly();
	    // c.getShops().openShop(25); // Shantay Pass Shop
	    break;
	case 970: // DIANGO
	    c.getShops().openShop(26); // Diango's Toy Store
	    break;
	case 557: // WYDIN
	    c.getShops().openShop(27); // Wydin's Food store
	    break;
	case 558: // GERRANT
	    c.getShops().openShop(28); // Gerrant's Fishy Business
	    break;
	case 556: // GRUM
	    c.getShops().openShop(29); // Grum's Gold Exchange
	    break;
	case 583: // BETTY
	    c.getShops().openShop(30); // Betty's Magic Emporium
	    break;
	case 559: // BRIAN
	    c.getShops().openShop(31); // Brian's Battleaxe Bazaar
	    break;
	case 568: // ZAMBO
	    c.getShops().openShop(33); // Zambo's Pub
	    break;
	case 588: // DAVON
	    c.getShops().openShop(34); // Davon Jewellery suplies
	    break;
	case 1039: // BARKER
	    c.getShops().openShop(36); // Barker's Haberdashery
	    break;
	case 1038: // RUFUS
	    c.getShops().openShop(37); // Rufus's Meat Emperium
	    break;
	case 576: // HARRY
	    c.getShops().openShop(38); // Harry's Fishing Shop
	    break;
	case 575: // HICKTON
	    c.getShops().openShop(39); // Hickton's Archery Emperium
	    break;
	case 587: // JATIX
	    c.getShops().openShop(41); // Jetix Herblore Shop
	    break;
	case 586: // GAIUS
	    c.getShops().openShop(42); // Gaius' Two Handed Shop
	    break;
	case 589: // ZENESHA
	    c.getShops().openShop(43); // Zenesha's Plate Mail Body Shop
	    break;
	case 590: // AEMAD
	    c.getShops().openShop(44); // Aemad's Adventuring Suplies
	    break;
	case 1436: // IFABA
	    c.getShops().openShop(45); // Ifaba's General Store
	    break;
	case 1434: // DAGA
	    c.getShops().openShop(46); // Daga's Scimitar Smithy
	    break;
	case 1435: // TUTAB
	    c.getShops().openShop(47); // Tutab's Magical Market
	    break;
	case 1433: // SOLIHIB
	    c.getShops().openShop(48); // Solihib's Food Stall
	    break;
	case 1437: // HAMAB
	    c.getShops().openShop(49); // Hamab's Crafting Emporium
	    break;
	case 2622: // TZHAAR-HUR-LEK
	    c.getShops().openShop(57); // TzHaar-Hur-Lek's Ore Store
	    break;
	/**
	 * case ##: // c.getShops().openShop(60); // Warrior Guild Food Shop
	 * 
	 * case ##: // c.getShops().openShop(61); // Warrior Guild Potion Shop
	 * 
	 * case ##: // c.getShops().openShop(62); // Warrior Guild 2handed sword
	 * Shop
	 * 
	 * case ##: // c.getShops().openShop(63); // Warrior Guild Chain and
	 * Platebody Shop
	 * 
	 * case ##: // c.getShops().openShop(64); // Warrior Guild Sword Shop
	 * 
	 * case ##: // c.getShops().openShop(65); // Warrior Guild Helmet shop
	 * 
	 * case ##: // c.getShops().openShop(66); // Warrior Guild General Shop
	 * 
	 * case ##: // c.getShops().openShop(67); // Warrior Guild Armoured Legs
	 * Shop
	 * 
	 * case ##: // c.getShops().openShop(68); // Blurberry Bar
	 **/

	case 603: // HECKLE FUNCH
	    c.getShops().openShop(69); // Funch's Fine Groceries
	    break;
	case 602: // GULLUCK
	    c.getShops().openShop(71); // Gulluck and Sons
	    break;
	case 1781: // DARREN
	    c.getShops().openShop(72); // Darren's Wilderness Shop
	    break;
	case 1785: // EDMOND
	    c.getShops().openShop(73); // Edmond's Wilderness Shop
	    break;
	case 1779: // IAN
	    c.getShops().openShop(74); // Ian's Wilderness Shop
	    break;
	case 1782: // EDWARD
	    c.getShops().openShop(75); // Edward's Wilderness Shop
	    break;
	case 1783: // RICHARD
	    c.getShops().openShop(76); // Richard's Wilderness Shop
	    break;
	case 1787: // SAM
	    c.getShops().openShop(77); // Sam's Wilderness Shop
	    break;
	case 1784: // NEIL
	    c.getShops().openShop(78); // Neil's Wilderness Shop
	    break;
	case 1786: // SIMON
	    c.getShops().openShop(79); // Simon's Wilderness Shop
	    break;
	case 1778: // WILLIAM
	    c.getShops().openShop(80); // William's Wilderness Shop
	    break;
	/**
	 * case ##: // c.getShops().openShop(81); // Magic Guild Store
	 * 
	 * case ##: // c.getShops().openShop(82); // Magic Guild Store
	 * 
	 * case ##: // c.getShops().openShop(83); // Slayer Suplies
	 **/

	case 1336: // LARRISSA
	    c.getShops().openShop(84); // Larrissa's Suplies

	    /**
	     * case ##: // c.getShops().openShop(85); // Magic Guild Store
	     **/
	    break;
	case 579: // DROGO DWARF
	    c.getShops().openShop(86); // Drogo's Mining Emporium
	    break;
	case 536: // VALAINES
	    c.getShops().openShop(87); // Valaines Shop of Champions
	    break;
	case 537: // SCAVVO
	    c.getShops().openShop(88); // Scavvo's Rune Store
	    break;
	case 747: // OZIACH
	    if (c.dragSlayer == 10) {
		c.getShops().openShop(89); // Oziach Platebody Suplies
	    } else {
		c.sendMessage("You must complete the quest 'Dragon Slayer' first.");
	    }
	    break;
	case 594: // nurmof
	    c.getShops().openShop(107); // nurmoff
	    break;
	case 582: // dwarven shopping store
	    c.getShops().openShop(108); // dwarven shopping store
	    break;

	case 692:
	    c.getShops().openShop(11);
	    break;
	/* Thieving */
	case 1:
	case 2:
	case 3:
	case 4:
	case 5:
	case 6:
	case 7:
	case 9:
	case 18:
	case 20:
	case 26:
	case 21:
	    c.membersonly();
	    break;
	/* - - Shops - - */
	/* General Store / Assistant Varrock */

	case 3788:
	    // c.getShops().openVoid();
	    break;
	case 494:
	case 495:
	case 496:
	case 497:
	case 498:
	case 499:
	    c.getPA().openUpBank();
	    break;
	default:
	    ScriptManager.callFunc("npcClick2_" + npcType, c, npcType);
	    if (c.playerRights == 3)
		Misc.println("Second Click Npc : " + npcType);
	    break;

	}					    
    }

    public void thirdClickNpc(int npcType) {
	c.clickNpcType = 0;
	c.npcClickIndex = 0;
	switch (npcType) {
	case 553:
	    if (c.runeM == 7) {
		c.getPA().startTeleport2(2898, 4819, 0);
		c.aubury = true;
	    } else {
		c.sendMessage("You must complete Rune Mysteries first.");
	    }
	    break;
	default:
	    // DialogueHandler.sendDialogues(144, npcType);
	    ScriptManager.callFunc("npcClick3_" + npcType, c, npcType);
	    if (c.playerRights == 3)
		Misc.println("Third Click NPC : " + npcType);
	    break;

	}
    }

}