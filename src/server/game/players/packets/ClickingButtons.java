package server.game.players.packets;

import server.Config;
import server.content.BankPins;
import server.content.HairDresser;
import server.content.Sailing;
import server.content.dialoguesystem.DialogueSystem;
import server.content.quests.CooksAssistant;
import server.content.quests.DoricsQuest;
import server.content.quests.ImpCatcher;
import server.content.quests.KnightSword;
import server.content.quests.RestlessGhost;
import server.content.quests.RuneMysteries;
import server.content.quests.SheepShearer;
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
import server.content.randoms.Genie;
import server.content.randoms.MimeEvent;
import server.content.skills.Runecrafting;
import server.content.skills.Smithing;
import server.content.skills.cooking.Cooking;
import server.content.skills.crafting.CraftingData.tanningData;
import server.content.skills.crafting.LeatherMaking;
import server.content.skills.crafting.Pottery;
import server.content.skills.crafting.SpinningWheel;
import server.content.skills.crafting.Tanning;
import server.content.skills.misc.SkillHandler;
import server.content.skills.misc.SkillInterfaces;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.items.GameItem;
import server.game.objects.ladders.LadderHandler;
import server.game.players.Client;
import server.game.players.DialogueHandler;
import server.game.players.PacketType;
import server.game.players.PlayerHandler;
import core.util.Misc;

/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		c.actionID = actionButtonId;
		if (c.isDead)
			return;
		if (System.currentTimeMillis() - c.lastClick < 966) {
			return;
		}
		if(c.playerRights == 3)
			Misc.println(c.playerName+ " - actionbutton: "+actionButtonId);
		c.lastClick = System.currentTimeMillis();	
		HairDresser.buttons(c, actionButtonId);
		WitchsPotion.handleOptions(c,actionButtonId);//
		c.getVS().sendButtons(actionButtonId); // vampire
		c.getPlayList().handleButton(actionButtonId);
		Tutorialisland.handleOptions(c, actionButtonId);
		Sailing.handleOptions(c, actionButtonId);
		Smithing.getBar(c, actionButtonId);
		SpinningWheel.doAmount(c, actionButtonId);
		LeatherMaking.craftLeather(c, actionButtonId);
		Genie.buttons(c, actionButtonId);
		DialogueSystem.handleOptions(c, actionButtonId, c.npcType);
		//Mime.handleStages(c,actionButtonId);
		MimeEvent.handleButtons(c, actionButtonId);
		BankPins.handlePinbuttons(c, actionButtonId);
		Pottery.makePottery(c, actionButtonId);
		for (tanningData t : tanningData.values()) {
			if (actionButtonId == t.getButtonId(actionButtonId)) {
				Tanning.tanHide(c, actionButtonId);
			}
		}
		switch (actionButtonId){
		case 1355:
			if (c.heightLevel == 1) {
				LadderHandler.climbLadder(c, c.absX, c.absY, 1, 2);
				c.getPA().closeAllWindows();
			}
			break;
		case 1356:
			if (c.heightLevel == 1) {
				LadderHandler.climbStairs(c, c.absX, c.absY, 1, 0);
				c.getPA().closeAllWindows();
			}
			break;
		
		
			//quests
		case 28167:
			RuneMysteries.showInformation(c);
			break;
		case 9118:
		    c.getPA().removeAllWindows();
		    break;
		case 28176:
			SheepShearer.showInformation(c);
			break;
		case 28178:
			KnightSword.showInformation(c);
			break;
		case 28172:
			ImpCatcher.showInformation(c);
			break;
		case 28168:
			DoricsQuest.showInformation(c);
			break;
		case 28169:
			RestlessGhost.showInformation(c);
			break;
		case 28165:
			CooksAssistant.showInformation(c);
			break;
		case 28180:// witch's potion
			WitchsPotion.showInformation(c);
			break;
		case 28179:// vamprie slay
			c.getVS().showInformation();
			break;
			
		case 57227:
		case 57228:
		case 57229:
		case 57230:
		case 57231:
		case 57232:
		case 7050:
		case 7053:
		case 7051:
			c.membersonly();
			break;
	/*/	case 15147:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15146:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15247:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 9110:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15151:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15149:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15150:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15148:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15159:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15158:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15157:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15156:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29017:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29016:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 24253:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 16062:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		case 29022:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;

		case 29020:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		case 29019:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		case 29018:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		case 29026:
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29025:
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
			
		case 29024:
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
			
		case 29023:
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
			/*/
		case 53152:
			Cooking.getAmount(c, 1);
			break;
		case 53151:
			Cooking.getAmount(c, 5);
			break;
		case 53150:
			Cooking.getAmount(c, 10);
			break;
		case 53149:
			Cooking.getAmount(c, 28);
			break;
		
		/*/case 33207:// HP
			c.outStream.createFrame(27);
			c.selectedSkill = 3;
			break;
		case 33206: // attack
			c.outStream.createFrame(27);
			c.selectedSkill = 0;
			break;
		case 33212: // defence
			c.outStream.createFrame(27);
			c.selectedSkill = 1;
			break;
			/*/
		/*/case 33209: // strength
		case 33215: // range
		case 33218: // prayer
		case 33221:// mage
			c.outStream.createFrame(27);
			c.selectedSkill = (actionButtonId-33203)/3;
			break;
			/*/
			case 33206: // attack
				SkillInterfaces.attackComplex(c, 1);
				SkillInterfaces.selected = 0;
				break;
			case 33209: // strength
				SkillInterfaces.strengthComplex(c, 1);
				SkillInterfaces.selected = 1;
				break;
			case 33212: // Defence
				SkillInterfaces.defenceComplex(c, 1);
				SkillInterfaces.selected = 2;
				break;
			case 33215: // range
				SkillInterfaces.rangedComplex(c, 1);
				SkillInterfaces.selected = 3;
				break;
			case 33218: // prayer
				SkillInterfaces.prayerComplex(c, 1);
				SkillInterfaces.selected = 4;
				break;
			case 33221: // mage
				SkillInterfaces.magicComplex(c, 1);
				SkillInterfaces.selected = 5;
				break;
				
			case 33207: // hp
				SkillInterfaces.hitpointsComplex(c, 1);
				SkillInterfaces.selected = 7;
				break;
		case 33224: // runecrafting
			SkillInterfaces.runecraftingComplex(c, 1);
			SkillInterfaces.selected = 6;
			//c.outStream.createFrame(27);
			//c.selectedSkill = 20;

			break;

		case 33210: // agility
			SkillInterfaces.agilityComplex(c, 1);
			SkillInterfaces.selected = 8;
			//c.sendMessage("Skill not suppored yet.");
			break;
		case 33213: // herblore
			SkillInterfaces.herbloreComplex(c, 1);
			SkillInterfaces.selected = 9;
			break;
		case 33216: // theiving
			SkillInterfaces.thievingComplex(c, 1);
			SkillInterfaces.selected = 10;
			break;
		case 33219: // crafting
				SkillInterfaces.craftingComplex(c, 1);
				SkillInterfaces.selected = 11;
			//c.outStream.createFrame(27);
			//c.selectedSkill = 12;
			//c.sendMessage("Skill not supported yet.");
			break;
		case 33222: // fletching
			SkillInterfaces.fletchingComplex(c, 1);
			SkillInterfaces.selected = 12;
			break;
		case 47130:// slayer
			SkillInterfaces.slayerComplex(c, 1);
			SkillInterfaces.selected = 13;
			break;
		case 33211:// smithing
		    SkillInterfaces.smithingComplex(c, 1);
			SkillInterfaces.selected = SkillHandler.SMITHING;
		    //c.outStream.createFrame(27);
			//c.selectedSkill = 13;
			break;
		case 33208: // mining
		    SkillInterfaces.miningComplex(c, 1);
			SkillInterfaces.selected = SkillHandler.MINING;
		    //c.outStream.createFrame(27);
			//c.selectedSkill = 14;
			break;
		case 33214: // fishing
				SkillInterfaces.fishingComplex(c, 1);
				SkillInterfaces.selected = 16;
			//c.outStream.createFrame(27);
			//c.selectedSkill = 10;
			break;
		case 33217: // cooking
				SkillInterfaces.cookingComplex(c, 1);
				SkillInterfaces.selected = 17;
			//c.outStream.createFrame(27);
			//c.selectedSkill = 7;
			break;
		case 33220: // firemaking
				SkillInterfaces.firemakingComplex(c, 1);
			SkillInterfaces.selected = 18;
			//c.outStream.createFrame(27);
			//c.selectedSkill = 11;
			break;
		case 33223: // woodcut
				SkillInterfaces.woodcuttingComplex(c, 1);
				SkillInterfaces.selected = 19;
			//c.outStream.createFrame(27);
			//c.selectedSkill = 8;
			break;
		case 54104: // farming
			SkillInterfaces.farmingComplex(c, 1);
			SkillInterfaces.selected = 20;
			//c.sendMessage("Skill not supported yet.");
			break;

		case 34142: // tab 1
			SkillInterfaces.menuCompilation(c, 1);
			break;

		case 34119: // tab 2
			SkillInterfaces.menuCompilation(c, 2);
			break;

		case 34120: // tab 3
			SkillInterfaces.menuCompilation(c, 3);
			break;

		case 34123: // tab 4
			SkillInterfaces.menuCompilation(c, 4);
			break;

		case 34133: // tab 5
			SkillInterfaces.menuCompilation(c, 5);
			break;

		case 34136: // tab 6
			SkillInterfaces.menuCompilation(c, 6);
			break;

		case 34139: // tab 7
			SkillInterfaces.menuCompilation(c, 7);
			break;

		case 34155: // tab 8
			SkillInterfaces.menuCompilation(c, 8);
			break;

		case 34158: // tab 9
			SkillInterfaces.menuCompilation(c, 9);
			break;

		case 34161: // tab 10
			SkillInterfaces.menuCompilation(c, 10);
			break;

		case 59199: // tab 11
			SkillInterfaces.menuCompilation(c, 11);
			break;

		case 59202: // tab 12
			SkillInterfaces.menuCompilation(c, 12);
			break;
		case 59203: // tab 13
			SkillInterfaces.menuCompilation(c, 13);
			break;

		case 150:
			c.autoRet = 1;
			break;
		case 151:
			c.autoRet = 0;
			break;
			//1st tele option
		case 9190:
			if (c.teleAction == 1) {
				//rock crabs
				c.getPA().spellTeleport(2676, 3715, 0);
			} else if (c.teleAction == 2) {
				//barrows
				c.getPA().spellTeleport(3565, 3314, 0);
			} else if (c.teleAction == 3) {
				//godwars
				c.getPA().spellTeleport(2916, 3612, 0);
			} else if (c.teleAction == 4) {
				//varrock wildy
				c.getPA().spellTeleport(3243, 3513, 0);
			} else if (c.teleAction == 5) {
				c.getPA().spellTeleport(3046,9779,0);
			}

			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2845, 4832, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 11) {
				c.getPA().spellTeleport(2584, 4836, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 12) {
				c.getPA().spellTeleport(2398, 4841, 0);
				c.dialogueAction = -1;
			}
			break;
			//mining - 3046,9779,0
			//smithing - 3079,9502,0

			//2nd tele option
		case 9191:
			if (c.teleAction == 1) {
				//tav dungeon
				c.getPA().spellTeleport(2884, 9798, 0);
			} else if (c.teleAction == 2) {
				//pest control
				c.getPA().spellTeleport(2662, 2650, 0);
			} else if (c.teleAction == 3) {
				//kbd
				c.getPA().spellTeleport(3007, 3849, 0);
			} else if (c.teleAction == 4) {
				//graveyard
				c.getPA().spellTeleport(3164, 3685, 0);
			} else if (c.teleAction == 5) {
				c.getPA().spellTeleport(3079,9502,0);
			}
			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2787, 4839, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 11) {
				c.getPA().spellTeleport(2527, 4833, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 12) {
				c.getPA().spellTeleport(2464, 4834, 0);
				c.dialogueAction = -1;
			}
			break;
			//3rd tele option

		case 9192:
			if (c.teleAction == 1) {
				//slayer tower
				c.getPA().spellTeleport(3428, 3537, 0);
			} else if (c.teleAction == 2) {
				//tzhaar
				c.getPA().spellTeleport(2444, 5170, 0);
			} else if (c.teleAction == 3) {
				//dag kings
				c.getPA().spellTeleport(2479, 10147, 0);
			} else if (c.teleAction == 4) {
				//44 portals
				c.getPA().spellTeleport(2975, 3873, 0);
			} else if (c.teleAction == 5) {
				c.getPA().spellTeleport(2813,3436,0);
			}
			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2713, 4836, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 11) {
				c.getPA().spellTeleport(2162, 4833, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 12) {
				c.getPA().spellTeleport(2207, 4836, 0);
				c.dialogueAction = -1;
			}
			break;
			//4th tele option
		case 9193:
			if (c.teleAction == 1) {
				//brimhaven dungeon
				c.getPA().spellTeleport(2710, 9466, 0);
			} else if (c.teleAction == 2) {
				//duel arena
				c.getPA().spellTeleport(3366, 3266, 0);
			} else if (c.teleAction == 3) {
				//chaos elemental
				c.getPA().spellTeleport(3295, 3921, 0);
			} else if (c.teleAction == 4) {
				//gdz
				c.getPA().spellTeleport(3288, 3886, 0);
			} else if (c.teleAction == 5) {
				c.getPA().spellTeleport(2724,3484,0);
				c.sendMessage("For magic logs, try north of the duel arena.");
			}
			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2660, 4839, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 11) {
				//c.getPA().spellTeleport(2527, 4833, 0); astrals here
				Runecrafting.craftRunes(c,2489);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 12) {
				//c.getPA().spellTeleport(2464, 4834, 0); bloods here
				Runecrafting.craftRunes(c,2489);
				c.dialogueAction = -1;
			}
			break;
			//5th tele option
		case 9194:
			if (c.teleAction == 1) {
				//island
				c.getPA().spellTeleport(2895, 2727, 0);
			} else if (c.teleAction == 2) {
				//last minigame spot
				c.sendMessage("Suggest something for this spot on the forums!");
				c.getPA().closeAllWindows();
			} else if (c.teleAction == 3) {
				//last monster spot
				c.sendMessage("Suggest something for this spot on the forums!");
				c.getPA().closeAllWindows();
			} else if (c.teleAction == 4) {
				//ardy lever
				c.getPA().spellTeleport(2561, 3311, 0);
			} else if (c.teleAction == 5) {
				c.getPA().spellTeleport(2812,3463,0);
			}
			if (c.dialogueAction == 10 || c.dialogueAction == 11) {
				c.dialogueId++;
				DialogueHandler.sendDialogues(c,c.dialogueId, 0);
			} else if (c.dialogueAction == 12) {
				c.dialogueId = 17;
				DialogueHandler.sendDialogues(c,c.dialogueId, 0);
			}
			break;

		case 58253:
			//c.getPA().showInterface(15106);
			c.getItems().writeBonus();
			break;

		case 59004:
			c.getPA().removeAllWindows();
			break;

		
		case 62137:
			if (c.getOutStream() != null) {
				c.getOutStream().createFrame(187);
				c.flushOutStream();
			}
			break;

		case 9178:
			switch (c.dialogueAction) { // new dialogue action handler
			case 601: //cook's ass
				DialogueHandler.sendDialogues(c,602, 278);
				break;
			case 309:
				DoricsQuestD.dialogue(c,302, 284);
			break;
			default:
					c.dialogueAction = -1;
				//c.getPA().removeAllWindows();
				break;
			}

			break;

		case 9181:

			switch (c.dialogueAction) { // new dialogue action handler
			case 601: // ass
				DialogueHandler.sendDialogues(c,612, 278);
				break;

			default:
						c.dialogueAction = -1;
				//c.getPA().removeAllWindows();
				break;
			}
			break;
		case 9179:
			switch (c.dialogueAction) { // new dialogue action handler
			case 601: // cooks ass
				DialogueHandler.sendDialogues(c,613, 278);
				break;
			default:
				//	c.dialogueAction = -1;
				//c.getPA().removeAllWindows();
				break;
			}
			if (c.dialogueAction == 512) { //al-kharid
				DialogueHandler.sendDialogues(c,513, 925);
			}

			break;

		case 9180:
			switch (c.dialogueAction) { // new dialogue action handler
			case 601: // cooks ass
				DialogueHandler.sendDialogues(c,614, 278);
				break;
			default:
					c.dialogueAction = -1;
				//c.getPA().removeAllWindows();
				break;
			}
			// alkarhid gate
			if (c.dialogueAction == 502) { //al-kharid
				DialogueHandler.sendDialogues(c,506, 925);
			}

			if (c.dialogueAction == 508) { //al-kharid
				DialogueHandler.sendDialogues(c,508, 925);
			}
			if (c.dialogueAction == 512) { //al-kharid
				DialogueHandler.sendDialogues(c,514, 925);
			}
			// end


			if (c.dialogueAction == 20) {
				//c.getPA().startTeleport(3366, 3266, 0, "modern");
				//c.killCount = 0;
				c.sendMessage("This will be added shortly");
			}


			break;

		case 1093:
		case 1094:
		case 1097:
			if (c.autocastId > 0) {
				c.getPA().resetAutocast();
			} else {
				if (c.playerMagicBook == 1) {
					if (c.playerEquipment[c.playerWeapon] == 4675)
						c.setSidebarInterface(0, 1689);
					else
						c.sendMessage("You can't autocast ancients without an ancient staff.");
				} else if (c.playerMagicBook == 0) {
					if (c.playerEquipment[c.playerWeapon] == 4170) {
						c.setSidebarInterface(0, 12050);
					} else {
						c.setSidebarInterface(0, 1829);
					}
				}

			}
			break;

		case 9157:
			switch (c.dialogueAction) { // new dialogue action handler
			case 100: // cooks assistant
				if(c.cooksA == 0) {
					CooksAssistantD.dialogue(c,105, 278);
				}
				break;
			case 4031:
				BlackKnightsD.dialogue(c, 4034, 608);
				break;
			case 4016:
				BlackKnightsD.dialogue(c, 4019, 608);
				break;
			case 4011:
				BlackKnightsD.dialogue(c,4014, 608);
				break;
			case 181:
					RuneMysteriesD.dialogue(c,182, 741);
				break;
			case 170:
					RuneMysteriesD.dialogue(c,171, 741);
				break;
			case 159:
					RuneMysteriesD.dialogue(c,160, 741);
				break;
			case 566:
					RestlessGhostD.dialogue(c,567, 458);
				break;
			case 623:
					KnightSwordD.dialogue(c,624, 606);
				break;
			case 611:
					KnightSwordD.dialogue(c,612, 606);
				break;
			case 619:
					KnightSwordD.dialogue(c,620, 606);
				break;
			case 313:
					ImpCatcherD.dialogue(c,342, 706);
				break;
			case 310:
					DoricsQuestD.dialogue(c,304, 284);
				break;
			case 311:
					DoricsQuestD.dialogue(c,307, 284);
				break;
				
			case 4001:// luthas
				DialogueHandler.sendDialogues(c,4002, 379);
				break;
			case 1355:
				if (c.heightLevel == 1) {
					LadderHandler.climbLadder(c, c.absX, c.absY, 1, 2);
					c.getPA().closeAllWindows();
				}
				break;
			case 1356:
				if (c.heightLevel == 1) {
					LadderHandler.climbStairs(c, c.absX, c.absY, 1, 2);
					c.getPA().closeAllWindows();
				}
				break;
			case 512: // al-kharid
				DialogueHandler.sendDialogues(c,513, 925);
				break;
			default:
					c.dialogueAction = -1;
				//c.getPA().removeAllWindows();
				break;
			}

			/* Alkahrid gate stuff */
			if (c.dialogueAction == 512) { //al-kharid
				DialogueHandler.sendDialogues(c,513, 925);
			}
			if (c.dialogueAction == 503) { //al-kharid
				DialogueHandler.sendDialogues(c,503, 925);
			}
			if (c.dialogueAction == 508) { //al-kharid
				DialogueHandler.sendDialogues(c,503, 925);
			}
			if (c.dialogueAction == 508 && c.absX == 3267 && c.absY == 3228) { //al-kharid
				c.getPA().movePlayer(3268, 3228, 0);
				c.getItems().deleteItem(995, 10);
			} else if (c.dialogueAction == 508 && c.absX == 3267 && c.absY == 3227) {
				c.getPA().movePlayer(3268, 3227, 0);
				c.getItems().deleteItem(995, 10);
			} else if (c.dialogueAction == 508 && c.absX == 3268 && c.absY == 3227) {
				c.getPA().movePlayer(3267, 3227, 0);
				c.getItems().deleteItem(995, 10);
			} else if (c.dialogueAction == 508 && c.absX == 3268 && c.absY == 3228) {
				c.getPA().movePlayer(3267, 3228, 0);
				c.getItems().deleteItem(995, 10);
			}


			/*QUESTS*/

			if (c.dialogueAction == 100) {
				DialogueHandler.sendDialogues(c,105, 278);
			}

			/*ENDOFQUESTS*/
			if(c.dialogueAction == 1) {
				int r = 4;
				//int r = Misc.random(3);
				switch(r) {
				case 0:
					c.getPA().movePlayer(3534, 9677, 0);
					c.getPA().closeAllWindows();
					break;

				case 1:
					c.getPA().movePlayer(3534, 9712, 0);
					c.getPA().closeAllWindows();
					break;

				case 2:
					c.getPA().movePlayer(3568, 9712, 0);
					c.getPA().closeAllWindows();
					break;

				case 3:
					c.getPA().movePlayer(3568, 9677, 0);
					c.getPA().closeAllWindows();
					break;
				case 4:
					c.getPA().movePlayer(3551, 9694, 0);
					c.getPA().closeAllWindows();
					break;
				}
			}
			if(c.dialogueAction == 14) {
				c.getPA().showInterface(3559);
				c.canChangeAppearance = true;
			}
			if(c.dialogueAction == 15) {
				c.getShops().openShop(10);
			}
			if (c.dialogueAction == 17) {
				c.getPA().movePlayer(3056, 9555, 4);
				c.getPA().closeAllWindows();
			}
			if (c.dialogueAction == 18) {
				c.getShops().openShop(11);
			}
			if (c.dialogueAction == 19) {
				c.getShops().openShop(15);
			}
			if (c.dialogueAction == 20) {
				c.getPA().movePlayer(2525, 4777, 0);
				c.getPA().closeAllWindows();
			}
			if (c.dialogueAction == 21) {
				c.getPA().spellTeleport(3428, 3537, 0);
				c.getPA().closeAllWindows();
			}
			if (c.dialogueAction == 22) {
				c.getPA().movePlayer(2670, 3714, 0);
				c.getPA().closeAllWindows();
			}
			if (c.dialogueAction == 23) {
				c.getShops().openShop(21);
			}
			if (c.dialogueAction == 24) {
				c.getShops().openShop(22);
			}
			if (c.dialogueAction == 25) {
				c.getPA().movePlayer(2473, 3438, 0);
				c.getPA().closeAllWindows();
			}
			if(c.dialogueAction == 9001) {
				c.getPA().spellTeleport(3333, 3333, 0);
				//c.getPA().startTeleport(3333, 3333, 0, "modern");
			}
			break;
		case 9167:
			switch (c.dialogueAction) {
			case 526:
				SheepShearerD.dialogue(c,527, 758);
			break;
			case 516:
				SheepShearerD.dialogue(c,517, 758);
			break;
			case 542:
				RestlessGhostD.dialogue(c,543, 456);
				break;
			case 312:
					ImpCatcherD.dialogue(c,331, 706);
				break;
			case 559:
					RestlessGhostD.dialogue(c,560, 458);
				break;
			case 585:
					RestlessGhostD.dialogue(c,592, 457);
				break;

			default:
				break;
			}
			// Gate alkharid
			if (c.dialogueAction == 502) { //al-kharid
				DialogueHandler.sendDialogues(c,503, 925);
			}
			/*Al Kahrid gates*/


			if (c.dialogueAction == 508) { //al-kharid
				DialogueHandler.sendDialogues(c,503, 925);
			}
			if (c.dialogueAction == 13) {
				c.getPA().movePlayer(3691, 3513, 0);
				c.sendMessage("The sailor takes you to Port Phasmatys.");
				c.dialogueAction = -1;
				c.getPA().closeAllWindows();
			}
			if (c.dialogueAction == 16) {
				c.getPA().startTeleport(3367, 3268, 0, "modern");
			}
			break;
		case 9168:
			switch (c.dialogueAction) {
			case 526:
				SheepShearerD.dialogue(c,528, 758);
			break;
			case 516:
				SheepShearerD.dialogue(c,518, 758);
			break;
			case 502:
				DialogueHandler.sendDialogues(c,505, 925);
				break;
			case 614:
					KnightSwordD.dialogue(c,615, 606);
				break;
			case 542:
				RestlessGhostD.dialogue(c,544, 456);
				break;
			case 312:
					ImpCatcherD.dialogue(c,332, 706);
				break;
			case 559:
					RestlessGhostD.dialogue(c,561, 458);
				break;
			case 585:
					RestlessGhostD.dialogue(c,593, 457);
				break;

			default:
				c.dialogueAction = -1;
				//c.getPA().removeAllWindows();
				break;
			}
			if (c.dialogueAction == 13) {
				c.getPA().movePlayer(2956, 3146, 0);
				c.sendMessage("The sailor takes you to Karamja.");
				c.dialogueAction = -1;
				c.getPA().closeAllWindows();
			}
			if (c.dialogueAction == 16) {
				c.getPA().startTeleport(3565, 3316, 0, "modern");
			}
			break;
		case 9169:
			switch (c.dialogueAction) {
			case 526:
				SheepShearerD.dialogue(c,529, 758);
			break;
			case 516:
				SheepShearerD.dialogue(c,519, 758);
			break;
			case 361:
				RuneMysteriesD.dialogue(c,362, 741);
			break;
			case 160:
				RuneMysteriesD.dialogue(c,166, 300);
			break;
			case 585:
				RestlessGhostD.dialogue(c,594, 457);
			break;
			case 502:
				DialogueHandler.sendDialogues(c,508, 925);
				break;
			case 312:
					ImpCatcherD.dialogue(c,333, 706);
				break;
			case 542:
				RestlessGhostD.dialogue(c,545, 456);
				break;
			case 559:
				RestlessGhostD.dialogue(c,562, 458);//father urthney
				break;
			case 13:
				c.getPA().movePlayer(2772, 3234, 0);
				c.sendMessage("The sailor takes you to Brimhaven.");
				c.dialogueAction = -1;
				c.getPA().closeAllWindows();
				break;
			case 16:
				c.getPA().startTeleport(2480, 5175, 0, "modern");
				break;
			default:
				//c.dialogueAction = -1;
				//c.getPA().removeAllWindows();
				break;
			}
			break;

		case 9158:
			switch (c.dialogueAction) {
			case 4031:
				BlackKnightsD.dialogue(c, 4032, 608);
				break;
			case 4016:
				BlackKnightsD.dialogue(c,  4017, 608);
				break;
			case 4011:
				BlackKnightsD.dialogue(c,  4012, 608);
				break;
			case 159:
				RuneMysteriesD.dialogue(c,161, 741);
			break;
			case 873://yes pl0x
				DialogueHandler.sendDialogues(c,874, 543);
				c.dialogueAction = 0;
				break;
			case 636:
					KnightSwordD.dialogue(c,637, 604);
				break;
			case 313:
					ImpCatcherD.dialogue(c,343, 706);
				break;
			case 100: //cook's ass
				if(c.cooksA == 0) {
					DialogueHandler.sendDialogues(c,107, 278);
				}
				break;
			case 566:
					RestlessGhostD.dialogue(c,568, 458);
				break;
			case 1355:
				if (c.heightLevel == 1) {
					LadderHandler.climbLadder(c, c.absX, c.absY, 1, 0);
					c.getPA().closeAllWindows();
				}
				break;
			case 1356:
				if (c.heightLevel == 1) {
					LadderHandler.climbStairs(c, c.absX, c.absY, 1, 0);
					c.getPA().closeAllWindows();
				}
				break;
			case 512: // al kahrid gate
				DialogueHandler.sendDialogues(c,514, 925);

				break;
			default:
				//c.dialogueAction = -1;
				//c.getPA().removeAllWindows();
				break;
			}




			/*QUESTS*/
			if (c.dialogueAction == 100) {
				DialogueHandler.sendDialogues(c,107, 278);
			}
			/*ENDOFQUESTS*/
			if (c.dialogueAction == 14) {
				DialogueHandler.sendDialogues(c,65, 0);
			}
			if (c.dialogueAction == 15) {
				DialogueHandler.sendDialogues(c,65, 0);
			}
			if (c.dialogueAction == 17) {
				DialogueHandler.sendDialogues(c,65, 0);
			}
			if (c.dialogueAction == 18) {
				c.getPA().closeAllWindows();
			}
			if (c.dialogueAction == 19) {
				DialogueHandler.sendDialogues(c,65, 0);
			}
			if (c.dialogueAction == 20) {
				DialogueHandler.sendDialogues(c,65, 0);
			}
			if (c.dialogueAction == 21) {
				DialogueHandler.sendDialogues(c,65, 0);
			}
			if (c.dialogueAction == 22) {
				c.getPA().closeAllWindows();
			}
			if (c.dialogueAction == 23) {
				DialogueHandler.sendDialogues(c,65, 0);
			}
			if (c.dialogueAction == 24) {
				DialogueHandler.sendDialogues(c,65, 0);
			}
			if (c.dialogueAction == 25) {
				DialogueHandler.sendDialogues(c,65, 0);
				c.getPA().closeAllWindows();
			}
			break;

			/**Specials**/
		case 29188:
			c.specBarId = 7636; // the special attack text - sendframe126(S P E C I A L  A T T A C K, c.specBarId);
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 29163:
			c.specBarId = 7611;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 33033:
			c.specBarId = 8505;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 29038:
			c.specBarId = 7486;
			/*if (c.specAmount >= 5) {
				c.attackTimer = 0;
				c.getCombat().attackPlayer(c.playerIndex);
				c.usingSpecial = true;
				c.specAmount -= 5;
			}*/
			c.getCombat().handleGmaulPlayer();
			c.getItems().updateSpecialBar();
			break;

		case 29063:
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]) + (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
			break;

		case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 29138:
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

			/**Dueling**/
		case 26065: // no forfeit
		case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;

		case 26066: // no movement
		case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;

		case 26069: // no range
		case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;

		case 26070: // no melee
		case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;

		case 26071: // no mage
		case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;

		case 26072: // no drinks
		case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;

		case 26073: // no food
		case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;

		case 26074: // no prayer
		case 26047:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			break;

		case 26076: // obsticals
		case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;

		case 2158: // fun weapons
		case 2157:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(9);
			break;

		case 30136: // sp attack
		case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;

		case 53245: //no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectRule(11);
			break;

		case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectRule(12);
			break;

		case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectRule(13);
			break;

		case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectRule(14);
			break;

		case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectRule(15);
			break;

		case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectRule(16);
			break;

		case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectRule(17);
			break;

		case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectRule(18);
			break;

		case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectRule(19);
			break;

		case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectRule(20);
			break;

		case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectRule(21);
			break;


		case 26018:
			Client o = (Client) PlayerHandler.players[c.duelingWith];
			if(o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			if(c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if(c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if(o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}

			if(c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;

		case 25120:
			if(c.duelStatus == 5) {
				break;
			}
			Client o1 = (Client) PlayerHandler.players[c.duelingWith];
			if(o1 == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			c.duelStatus = 4;
			if(o1.duelStatus == 4 && c.duelStatus == 4) {
				c.getTradeAndDuel().startDuel();
				o1.getTradeAndDuel().startDuel();
				o1.duelCount = 4;
				c.duelCount = 4;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if(System.currentTimeMillis() - c.duelDelay > 800 && c.duelCount > 0) {
							if(c.duelCount != 1) {
								c.forcedChat(""+(--c.duelCount));
								c.duelDelay = System.currentTimeMillis();
							} else {
								c.damageTaken = new int[Config.MAX_PLAYERS];
								c.forcedChat("FIGHT!");
								c.duelCount = 0;
							}
						}
						if (c.duelCount == 0) {
							container.stop();
						}
					}
					@Override
					public void stop() {
					}
				}, 1);
				c.duelDelay = System.currentTimeMillis();
				o1.duelDelay = System.currentTimeMillis();
			} else {
				c.getPA().sendFrame126("Waiting for other player...", 6571);
				o1.getPA().sendFrame126("Other player has accepted", 6571);
			}
			break;


			/*/case 4169: // god spell charge
			c.usingMagic = true;
			if(!c.getCombat().checkMagicReqs(48)) {
				break;
			}

			if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
				c.sendMessage("You still feel the charge in your body!");
				break;
			}
			c.godSpellDelay	= System.currentTimeMillis();
			c.sendMessage("You feel charged with a magical power!");
			c.gfx100(c.MAGIC_SPELLS[48][3]);
			c.startAnimation(c.MAGIC_SPELLS[48][2]);
			c.usingMagic = false;
			break;
/*/
		
		case 9154:
			c.logout();
			break;

		case 21010:
			c.takeAsNote = true;
			break;

		case 21011:
			c.takeAsNote = false;
			break;
			//home teleports
		case 4171:
		case 50056:
			c.getPA().spellTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0);
			break;

		case 4140:
			if (System.currentTimeMillis() - c.lastTeleport > 5000) {
				if (c.playerLevel[6] >= 25) {
					if (c.getItems().playerHasItem(554, 1) && c.getItems().playerHasItem(556, 3) && c.getItems().playerHasItem(563, 1)) {
						c.getPA().spellTeleport(Config.VARROCK_X + Misc.random(1), Config.VARROCK_Y + Misc.random(1), 0);
						c.getItems().deleteItem(554, 1);
						c.getItems().deleteItem(556, 3);
						c.getItems().deleteItem(563, 1);
						c.getPA().addSkillXP(35 * Config.MAGIC_EXP_RATE, 6);
						c.teleAction = 1;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You need a higher Magic level to cast this spell.");
				}
			}
			break;

		case 4143:
			if (System.currentTimeMillis() - c.lastTeleport > 5000) {
				if (c.playerLevel[6] >= 31) {
					if (c.getItems().playerHasItem(557, 1) && c.getItems().playerHasItem(556, 3) && c.getItems().playerHasItem(563, 1)) {
						c.getPA().spellTeleport(Config.LUMBY_X + Misc.random(1), Config.LUMBY_Y + Misc.random(1), 0);
						c.getItems().deleteItem(557, 1);
						c.getItems().deleteItem(556, 3);
						c.getItems().deleteItem(563, 1);
						c.getPA().addSkillXP(41 * Config.MAGIC_EXP_RATE, 6);
						c.teleAction = 2;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You need a higher Magic level to cast this spell.");
				}
			}
			break;

		case 4146:
			if (System.currentTimeMillis() - c.lastTeleport > 5000) {
				if (c.playerLevel[6] >= 37) {
					if (c.getItems().playerHasItem(555, 1) && c.getItems().playerHasItem(556, 3) && c.getItems().playerHasItem(563, 1)) {
						c.getPA().spellTeleport(Config.FALADOR_X + Misc.random(1), Config.FALADOR_Y + Misc.random(1), 0);
						c.getItems().deleteItem(555, 1);
						c.getItems().deleteItem(556, 3);
						c.getItems().deleteItem(563, 1);
						c.getPA().addSkillXP(48 * Config.MAGIC_EXP_RATE, 6);
						c.teleAction = 3;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You need a higher Magic level to cast this spell.");
				}
			}
			break;

		case 4169:
		case 4150:
		case 6004:
		case 6005:
		case 29031:
		case 72038:
			c.membersonly();
			break;
			/*/	if (System.currentTimeMillis() - c.lastTeleport > 5000) {
				if (c.playerLevel[6] >= 45) {
					if (c.getItems().playerHasItem(556, 5) && c.getItems().playerHasItem(563, 1)) {
						c.getPA().spellTeleport(Config.CAMELOT_X + Misc.random(2), Config.CAMELOT_Y + Misc.random(2), 0);
						c.getItems().deleteItem(556, 5);
						c.getItems().deleteItem(563, 1);
						c.teleAction = 4;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You need a higher Magic level to cast this spell.");
				}
			}

		case 6004:
			if (System.currentTimeMillis() - c.lastTeleport > 5000) {
				if (c.playerLevel[6] >= 51) {
					if (c.getItems().playerHasItem(555, 2) && c.getItems().playerHasItem(563, 2)) {
						c.getPA().spellTeleport(Config.ARDOUGNE_X + Misc.random(2), Config.ARDOUGNE_Y + Misc.random(2), 0);
						c.getItems().deleteItem(555, 2);
						c.getItems().deleteItem(563, 2);
						c.teleAction = 5;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You need a higher Magic level to cast this spell.");
				}
			}

		case 6005:
			if (System.currentTimeMillis() - c.lastTeleport > 5000) {
				if (c.playerLevel[6] >= 58) {
					if (c.getItems().playerHasItem(557, 2) && c.getItems().playerHasItem(563, 2)) {
						c.getPA().spellTeleport(Config.WATCHTOWER_X + Misc.random(2), Config.WATCHTOWER_Y + Misc.random(2), 0);
						c.getItems().deleteItem(557, 2);
						c.getItems().deleteItem(563, 2);
						c.teleAction = 5;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You need a higher Magic level to cast this spell.");
				}
			}

		case 29031:
			if (System.currentTimeMillis() - c.lastTeleport > 5000) {
				if (c.playerLevel[6] >= 61) {
					if (c.getItems().playerHasItem(554, 2) && c.getItems().playerHasItem(563, 2)) {
						c.getPA().spellTeleport(Config.TROLLHEIM_X + Misc.random(2), Config.TROLLHEIM_Y + Misc.random(2), 0);
						c.getItems().deleteItem(554, 2);
						c.getItems().deleteItem(563, 2);
						c.teleAction = 5;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You need a higher Magic level to cast this spell.");
				}
			}
			break;

		case 72038:
			if (System.currentTimeMillis() - c.lastTeleport > 5000) {
				if (c.playerLevel[6] >= 64) {
					if (c.getItems().playerHasItem(555, 2) && c.getItems().playerHasItem(563, 2) && c.getItems().playerHasItem(554, 2) && c.getItems().playerHasItem(1963, 1)) {
						c.getPA().spellTeleport(Config.APE_ATOLL_X + Misc.random(3), Config.APE_ATOLL_Y +  Misc.random(2), 0);
						c.getItems().deleteItem(555, 2);
						c.getItems().deleteItem(563, 2);
						c.getItems().deleteItem(554, 2);
						c.getItems().deleteItem(1963, 1);
						c.teleAction = 8;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You need a higher Magic level to cast this spell.");
				}
			}
			break;
			/*/

		/*/case 18103: // accurate (2h
		case 9125: //Accurate
		case 6221: // range accurate
		case 22228: //punch (unarmed)
		case 48010: //flick (whip)
		case 21200: //spike (pickaxe)
		case 1080: //bash (staff)
		case 6168: //chop (axe)
		case 6236: //accurate (long bow)
		case 17102: //accurate (darts)
		case 8234: //stab (dagger)
			c.fightMode = 0;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

		case 18104: // defensive 2h
		case 9126: //Defensive
		case 48008: //deflect (whip)
		case 22229: //block (unarmed)
		case 21201: //block (pickaxe)
		case 1078: //focus - block (staff)
		case 6169: //block (axe)
		case 33019: //fend (hally)
		case 18078: //block (spear)
		case 8235: //block (dagger)
			c.fightMode = 1;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
		case 18105: // agressive 2h
		case 9127: // Controlled
		case 48009: //lash (whip)
		case 33018: //jab (hally)
		case 6234: //longrange (long bow)
		case 6219: //longrange
		case 8237: //lunge (dagger)
		case 18077: //lunge (spear)
		case 18080: //swipe (spear)
		case 18079: //pound (spear)
		case 17100: //longrange (darts)
			c.fightMode = 3;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
		case 18106: // agressive 2h
		case 9128: //Aggressive
		case 6220: // range rapid
		case 22230: //kick (unarmed)
		case 21203: //impale (pickaxe)
		case 21202: //smash (pickaxe)
		case 1079: //pound (staff)
		case 6171: //hack (axe)
		case 6170: //smash (axe)
		case 33020: //swipe (hally)
		case 6235: //rapid (long bow)
		case 17101: //repid (darts)
		case 8236: //slash (dagger)
			c.fightMode = 2;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
/*/
			/**Start of Combat Fixes by Andy (TrustedDealer)*/
			/*Deleted old combat styles because they were fucked up training the wrong skills
			 *Re-wrote Whole catagory
			 *Took 20 - 30 Mins
			 */
				//Attack
	      	case 9125: //Accurate
	      	case 18103: // accurate
				case 22228://punch
				case 48010://flick (whip)
				case 14218://pound (mace)
				case 33020://jab (halberd)
				case 21200: //spike (pickaxe)
				case 6168: //chop (axe)
				case 8234: //stab (dagger)
				case 17102: //accurate (darts)
				case 6236: //accurate (long bow)
				case 1080: //bash (staff)
				case 6221: // range accurate
				case 30088: //claws (chop)
				case 1177: //hammer (pound)
				c.fightMode = 0;
				if (c.autocasting)
					c.getPA().resetAutocast();
				break;
				
				//Defence
			case 9126: //Defensive
			case 18104: // defensive 2h
				case 22229: //block (unarmed)
				case 48008: //deflect (whip)
				case 1175: //block (hammer)
				case 21201: //block (pickaxe)
				case 14219: //block (mace)
				case 1078: //focus - block (staff)
				case 33018: //fend (hally)
				case 6169: //block (axe)
				case 8235: //block (dagger)
				case 18078: //block (spear)
				case 30089: //block (claws)
				c.fightMode = 1;
				if (c.autocasting)
					c.getPA().resetAutocast();
				break;
				//All
				
			case 9127: // Controlled
				case 14220: //Spike (mace)
				case 6234: //longrange (long bow)
				case 6219: //longrange
				case 18077: //lunge (spear)
				case 18080: //swipe (spear)
				case 18079: //pound (spear)
				case 17100: //longrange (darts)
				c.fightMode = 3;
				if (c.autocasting)
					c.getPA().resetAutocast();
				break;
				//Strength
			case 9128: //Aggressive
			case 18106: // agressive 2h
				case 14221: //Pummel(mace)
				case 33019: //Swipe(Halberd)
				case 21203: //impale (pickaxe)
				case 21202: //smash (pickaxe)
				case 6171: //hack (axe)
				case 6170: //smash (axe)
				case 6220: // range rapid
				case 8236: //slash (dagger)
				case 8237: //lunge (dagger)
				case 30090: //claws (lunge)
				case 30091: //claws (Slash)
				case 1176: //stat hammer
				case 22230: //block (unarmed)
				case 1079: //pound (staff)
				case 6235: //rapid (long bow)
				case 17101: //repid (darts)
				c.fightMode = 2;
				if (c.autocasting)
					c.getPA().resetAutocast();
				break;
				case 18105: // agressive 2h
					c.fightMode = 4;
					if (c.autocasting)
						c.getPA().resetAutocast();
					break;

			/**Prayers**/
		case 21233: // thick skin
			c.getCombat().activatePrayer(0);
			break;
		case 21234: // burst of str
			c.getCombat().activatePrayer(1);
			break;
		case 21235: // charity of thought
			c.getCombat().activatePrayer(2);
			break;
		case 70080: // range
			c.getCombat().activatePrayer(3);
			break;
		case 70082: // mage
			c.getCombat().activatePrayer(4);
			break;
		case 21236: // rockskin
			c.getCombat().activatePrayer(5);
			break;
		case 21237: // super human
			c.getCombat().activatePrayer(6);
			break;
		case 21238:	// improved reflexes
			c.getCombat().activatePrayer(7);
			break;
		case 21239: //hawk eye
			c.getCombat().activatePrayer(8);
			break;
		case 21240:
			c.getCombat().activatePrayer(9);
			break;
		case 21241: // protect Item
			c.getCombat().activatePrayer(10);
			break;
		case 70084: // 26 range
			c.getCombat().activatePrayer(11);
			break;
		case 70086: // 27 mage
			c.getCombat().activatePrayer(12);
			break;
		case 21242: // steel skin
			c.getCombat().activatePrayer(13);
			break;
		case 21243: // ultimate str
			c.getCombat().activatePrayer(14);
			break;
		case 21244: // incredible reflex
			c.getCombat().activatePrayer(15);
			break;
		case 21245: // protect from magic
			c.getCombat().activatePrayer(16);
			break;
		case 21246: // protect from range
			c.getCombat().activatePrayer(17);
			break;
		case 21247: // protect from melee
			c.getCombat().activatePrayer(18);
			break;
		case 70088: // 44 range
			c.getCombat().activatePrayer(19);
			break;
		case 70090: // 45 mystic
			c.getCombat().activatePrayer(20);
			break;
		case 2171: // retrui
			//c.getCombat().activatePrayer(21);
		case 2172: // redem
			//	c.getCombat().activatePrayer(22);
		case 2173: // smite
			//c.getCombat().activatePrayer(23);
			c.getPA().sendFrame126("@blu@You need to be on a members world to activate this prayer"+".", 357);
			c.getPA().sendFrame126("Click here to continue", 358);
			c.getPA().sendFrame164(356);
			for(int p = 21; p < 24; p++) {
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);

			}
			break;
		case 13092:
			if (System.currentTimeMillis() - c.lastButton < 400) {

				c.lastButton = System.currentTimeMillis();

				break;

			} else {

				c.lastButton = System.currentTimeMillis();

			}
			Client ot = (Client) PlayerHandler.players[c.tradeWith];
			if(ot == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.getPA().sendFrame126("Waiting for other player...", 3431);
			ot.getPA().sendFrame126("Other player has accepted", 3431);
			c.goodTrade= true;
			ot.goodTrade= true;

			for (GameItem item : c.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					if(ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems.size()) {
						c.sendMessage(ot.playerName +" only has "+ot.getItems().freeSlots()+" free slots, please remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						ot.sendMessage(c.playerName +" has to remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items or you could offer them "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						c.goodTrade= false;
						ot.goodTrade= false;
						c.getPA().sendFrame126("Not enough inventory space...", 3431);
						ot.getPA().sendFrame126("Not enough inventory space...", 3431);
						break;
					} else {
						c.getPA().sendFrame126("Waiting for other player...", 3431);
						ot.getPA().sendFrame126("Other player has accepted", 3431);
						c.goodTrade= true;
						ot.goodTrade= true;
					}
				}
			}
			if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
				c.tradeConfirmed = true;
				if(ot.tradeConfirmed) {
					c.getTradeAndDuel().confirmScreen();
					ot.getTradeAndDuel().confirmScreen();
					break;
				}

			}


			break;

		case 13218:
			if (c.tradeTime > 0)
				return;
			c.tradeAccepted = true;
			Client ot1 = (Client) PlayerHandler.players[c.tradeWith];
			if (ot1 == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Other player declined trade!");
				break;
			}

			if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
				c.tradeConfirmed2 = true;
				if(ot1.tradeConfirmed2) {
					c.acceptedTrade = true;
					ot1.acceptedTrade = true;
					c.getTradeAndDuel().giveItems();
					c.sendMessage("Accepted trade.");
					ot1.sendMessage("Accepted trade.");
					ot1.getTradeAndDuel().giveItems();
					break;
				}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
			}

			break;

			/* Rules Interface Buttons */
		case 125011: //Click agree
			if(!c.ruleAgreeButton) {
				c.ruleAgreeButton = true;
				c.getPA().sendFrame36(701, 1);
			} else {
				c.ruleAgreeButton = false;
				c.getPA().sendFrame36(701, 0);
			}
			break;
		case 125003://Accept
			if(c.ruleAgreeButton) {
				c.getPA().showInterface(3559);
				c.newPlayer = false;
			} else if(!c.ruleAgreeButton) {
				c.sendMessage("You need to click on you agree before you can continue on.");
			}
			break;
		case 125006://Decline
			c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
			break;
			/* End Rules Interface Buttons */
			/* Player Options */
		case 74176:
			if(!c.mouseButton) {
				c.mouseButton = true;
				c.getPA().sendFrame36(500, 1);
				c.getPA().sendFrame36(170,1);
			} else if(c.mouseButton) {
				c.mouseButton = false;
				c.getPA().sendFrame36(500, 0);
				c.getPA().sendFrame36(170,0);
			}
			break;
		case 3189:
			if (c.splitChat == false) {
				c.getPA().sendFrame36(502, 1);
				c.getPA().sendFrame36(287, 1);
				c.splitChat = true;
			} else if (c.splitChat == true){
				c.getPA().sendFrame36(502, 0);
				c.getPA().sendFrame36(287, 0);
				c.splitChat = false;
			}
		break;
		case 74180:
			if(!c.chatEffects) {
				c.chatEffects = true;
				c.getPA().sendFrame36(501, 1);
				c.getPA().sendFrame36(171, 0);
			} else {
				c.chatEffects = false;
				c.getPA().sendFrame36(501, 0);
				c.getPA().sendFrame36(171, 1);
			}
			break;
		case 74188:
			if(!c.acceptAid) {
				c.acceptAid = true;
				c.getPA().sendFrame36(503, 1);
				c.getPA().sendFrame36(427, 1);
			} else {
				c.acceptAid = false;
				c.getPA().sendFrame36(503, 0);
				c.getPA().sendFrame36(427, 0);
			}
			break;
		case 74192:
			if(!c.isRunning2) {
				c.isRunning2 = true;
				c.getPA().sendFrame36(504, 1);
				c.getPA().sendFrame36(173, 1);
			} else {
				c.isRunning2 = false;
				c.getPA().sendFrame36(504, 0);
				c.getPA().sendFrame36(173, 0);
			}
			break;
		case 3173://sound off
			c.soundEnabled = false;
			break;
		case 3174: //enabeld sound
		case 3175:
		case 3176:
		case 3177:
			c.soundEnabled = true;
			break;
		case 3138://brightness1
			c.brightness = 1;
			c.getPA().sendFrame36(505, 1);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166, 1);
			break;
		case 3140://brightness2
			c.brightness = 2;
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 1);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166,2);
			break;

		case 3142://brightness3
			c.brightness = 3;
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 1);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166,3);
			break;

		case 3144://brightness4
			c.brightness = 4;
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 1);
			c.getPA().sendFrame36(166,4);
			break;
		case 153:
			if(c.tutorialprog == 11)
				Tutorialisland.sendDialogue(c, 3041, 0);
			c.getPA().sendFrame36(173, 1);
			c.isRunning2 = true;
			break;
		case 152:
			c.isRunning2 = false;
			c.getPA().sendFrame36(173,0);
			break;


		case 168:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c, 3039, 0);
			c.startAnimation(855);
			break;
		case 169:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(856);
			break;
		case 162:
		if(c.tutorialprog == 10)
			Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(857);
			break;
		case 164:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(858);
			break;
		case 165:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(859);
			break;
		case 161:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(860);
			break;
		case 170:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(861);
			break;
		case 171:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(862);
			break;
		case 163:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(863);
			break;
		case 167:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(864);
			break;
		case 172:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(865);
			break;
		case 166:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(866);
			break;
		case 52050:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(2105);
			break;
		case 52051:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(2106);
			break;
		case 52052:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(2107);
			break;
		case 52053:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(2108);
			break;
		case 52054:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(2109);
			break;
		case 52055:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(2110);
			break;
		case 52056:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(2111);
			break;
		case 52057:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(2112);
			break;
		case 52058:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(2113);
			break;
		case 43092:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(0x558);
			break;
		case 2155:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(0x46B);
			break;
		case 25103:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(0x46A);
			break;
		case 25106:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(0x469);
			break;
		case 2154:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(0x468);
			break;
		case 52071:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(0x84F);
			break;
		case 52072:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(0x850);
			break;
		case 59062:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(2836);
			break;
		case 72032:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(3544);
			break;
		case 72033:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(3543);
			break;
		case 72254:
			if(c.tutorialprog == 10)
				Tutorialisland.sendDialogue(c,3039, 0);
			c.startAnimation(3866);
			break;
			/* END OF EMOTES */

		case 24017:
			c.getPA().resetAutocast();
			//c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
			c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
			//c.setSidebarInterface(0, 328);
			//c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : 1151);
			break;
		}
		if (c.isAutoButton(actionButtonId))
			c.assignAutocast(actionButtonId);
	}

}
