package server.content.quests.dialogue;

import server.content.quests.DemonSlayer;
import server.game.players.Client;
import server.game.players.DialogueHandler;
import core.util.Misc;



/**
 * Made by someotherdude
 */
public class DemonSlayerD {

	/**
	 * Demon Slayer dialogue
	 * 
	 * @param c
	 * @param dialogue
	 * @param npcId
	 */
	public static void dialogue(Client c, int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		
	    case 1325:
            DialogueHandler.sendNpcChat1(c,"Hello young one.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1326;
        break;
		
	    case 1326:
            DialogueHandler.sendNpcChat2(c,"Cross my palm with silver and the fortune will be", "revealed to you.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1327;
        break;
		
		case 1327:
			DialogueHandler.sendOption3(c,"Okay, here you go.", "No I don't believe in that stuff.", "With silver?");
			c.DSOption = true;
		break;
		
	    case 1328:
            DialogueHandler.sendPlayerChat1(c,"Who are you calling 'young one'?");
            c.nextChat = 1329;
        break;
		
	    case 1329:
            DialogueHandler.sendNpcChat1(c,"I do not have time with the likes of you questioning my ways.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 0;
        break;
		
	    case 1330:
            DialogueHandler.sendPlayerChat1(c,"No, I don't believe in that stuff.");
            c.nextChat = 0;
        break;
		
	    case 1331:
            DialogueHandler.sendPlayerChat1(c,"With silver?");
            c.nextChat = 1332;
        break;
		
	    case 1332:
            DialogueHandler.sendNpcChat1(c,"With money from ones pocket.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1333;
        break;
		
		case 1333:
			DialogueHandler.sendOption2(c,"Ok, here you go.", "No I don't believe in that stuff.");
			c.DSOption2 = true;
		break;
		
	    case 1334:
            DialogueHandler.sendNpcChat2(c,"I sence you do not have enough! Return to me", "when you are able to pay...", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 0;
        break;
		
	    case 1335:
            DialogueHandler.sendPlayerChat1(c,"Ok, here you go.");
            c.nextChat = 1336;
			c.getItems().deleteItem(995, 1);
        break;
		
	    case 1336:
            DialogueHandler.sendNpcChat3(c,"Come closer and listen carefully to what the future", "holds, as I peer into the swirling mists of the crystal", "ball.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1337;
        break;
		
	    case 1337:
            DialogueHandler.sendNpcChat1(c,"I can see images forming. I can see you.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1338;
        break;
		
	    case 1338:
            DialogueHandler.sendNpcChat2(c,"You are holding a very impressing looking sword. I'm", "sure I recognize it...", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1339;
        break;
		
	    case 1339:
            DialogueHandler.sendNpcChat1(c,"These is a big dark shadow appearing now.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1340;
        break;
		
	    case 1340:
            DialogueHandler.sendNpcChat1(c,"Aaargh!", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1341;
        break;
		
	    case 1341:
            DialogueHandler.sendPlayerChat1(c,"Are you all right?");
            c.nextChat = 1342;
        break;
		
	    case 1342:
            DialogueHandler.sendNpcChat1(c,"It's Delrith! Delrith is coming!", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1343;
        break;
		
	    case 1343:
            DialogueHandler.sendPlayerChat1(c,"Who's Delrith?");
            c.nextChat = 1344;
        break;
		
	    case 1344:
            DialogueHandler.sendNpcChat1(c,"Delrith...", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1345;
        break;
		
	    case 1345:
            DialogueHandler.sendNpcChat1(c,"Delrith is a powerful demon.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1346;
        break;
		
	    case 1346:
            DialogueHandler.sendNpcChat2(c,"Oh! I really hope he didn't see me looking at him", "through my crystal ball!", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1347;
        break;
		
	    case 1347:
            DialogueHandler.sendNpcChat2(c,"He tried to destroy this city 150 years ago. He was", "stopped just in time by the great hero Wally.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1348;
        break;
		
	    case 1348:
            DialogueHandler.sendNpcChat3(c,"Using his magic sword Silverlight. Wally managed to", "trap this demon ni the stone circle just south", "of this city.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1349;
        break;
		
	    case 1349:
            DialogueHandler.sendNpcChat3(c,"Ye gods! Silverlight was the sword you were holding in", "my vision! You are the one destined to stop the demon", "this time.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1350;
        break;
		
		case 1350:
			DialogueHandler.sendOption3(c,"How am I meant to fight a demon who can destroy cities?", "Okay, where is he? I'll kill him for you.", "Wally doesn't sound like a very heroic name.");
			c.DSOption3 = true;
		break;
		
	    case 1351:
            DialogueHandler.sendPlayerChat2(c,"How am I menat to fight a demon who can destroy", "cities?");
            c.nextChat = 1352;
			c.demonS = 5;
        break;
		
	    case 1352:
            DialogueHandler.sendNpcChat3(c,"If you face Delrith while he is still weak from being", "summoned and use the correct weapon, you will not", "find the task too hard.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1353;
        break;
		
	    case 1353:
            DialogueHandler.sendNpcChat2(c,"Do not fear. If you follow the path of the great hero", "Wally, then you are sure to defeat the demon.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1354;
        break;
		
		case 1354:
			DialogueHandler.sendOption3(c,"Okay, where is he? I'll kill him for you.", "Wally doesn't sound like a very heroic name.", "So how did wally kill Delright?");
			c.DSOption4 = true;
		break;
		
	    case 1355:
            DialogueHandler.sendPlayerChat1(c,"So, how did Wally kill Delrith?");
            c.nextChat = 1356;
			c.demonS = 6;
        break;
		
	    case 1356:
            DialogueHandler.sendNpcChat2(c,"Wally managed to arrive at the stone circle just as", "Delrith was summoned by a cult of chaos druids.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 357;
        break;
		
		case 1357:
			DialogueHandler.sendStatement(c,"You look into the crystal ball.");
			c.nextChat = 1358;
		break;
		
	    case 1358:
            DialogueHandler.sendNpcChat1(c,"Die, foul demon!", c.talkingNpc, "Wally");
            c.nextChat = 1359;
        break;
		
	    case 1359:
            DialogueHandler.sendNpcChat1(c,"Now, what was that incantation again?", c.talkingNpc, "Wally");
            c.nextChat = 1360;
        break;
		
		case 1360:
		int i = Misc.random(3);
		if(i == 0) {
			DialogueHandler.sendNpcChat1(c,"Carlem... Gabindo... Purchai... Zaree... Camerinthum!", c.talkingNpc, "Wally");
			c.nextChat = 361;
			c.Incantation = 1;
			c.demonS = 7;
		} else if(i == 1) {
			DialogueHandler.sendNpcChat1(c,"Purchai... Zaree... Gabindo... Cariem... Camerinthum!", c.talkingNpc, "Wally");
			c.nextChat = 361;
			c.Incantation = 2;
			c.demonS = 7;
		} else if(i == 2) {
			DialogueHandler.sendNpcChat1(c,"Purchai... Camerinthum... Aber... Gabindo... Carlem!", c.talkingNpc, "Wally");
			c.nextChat = 361;
			c.Incantation = 3;
			c.demonS = 7;
		} else if(i == 3) {
			DialogueHandler.sendNpcChat1(c,"Carlem... Aber... Camerinthum... Purchai... Gabindo!", c.talkingNpc, "Wally");
			c.nextChat = 361;
			c.Incantation = 4;
			c.demonS = 7;
		}
		break;
		
		case 1361:
			DialogueHandler.sendStatement(c,"You watch as the demon is sucked into the stone circle.");
			c.nextChat = 1362;
		break;
		
	    case 1362:
            DialogueHandler.sendNpcChat1(c,"I am the greatest demon slayer EVER!", c.talkingNpc, "Wally");
            c.nextChat = 1363;
        break;
		
	    case 1363:
            DialogueHandler.sendNpcChat4(c,"By reciting the correct magical incantation, and", "thrusting Silverlight into Delrith while he was newly", "summoned, Wally was able to imprision Delrith in the", "stone block in the centre of the circle.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1364;
        break;
		
		case 1364:
			DialogueHandler.sendStatement(c,"You stop looking into the crystal ball.");
			c.nextChat = 1365;
			c.demonS = 8;
		break;
		
	    case 1365:
            DialogueHandler.sendPlayerChat1(c,"Delrith will come forth from the stone circle again..");
            c.nextChat = 1366;
        break;
		
	    case 1366:
            DialogueHandler.sendPlayerChat2(c,"I would imagine an evil sorcerer is already beginning", "the rituals to summon Delrith as we speak.");
            c.nextChat = 1367;
        break;
		
		case 1367:
			DialogueHandler.sendOption3(c,"Okay where is he? I'll kill him for you.", "What is the magical incantation?", "Where can I find silverlight?");
			c.DSOption5 = true;
		break;
		
	    case 1368:
            DialogueHandler.sendPlayerChat1(c,"Wally doesn't sound like a very heroic name.");
            c.nextChat = 1369;
        break;
		
	    case 1369:
            DialogueHandler.sendNpcChat1(c,"Oh but he was! He was the greatest hero this town has had!", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1354;
        break;
		
		case 1370:
            DialogueHandler.sendPlayerChat1(c,"Wally doesn't sound like a very heroic name.");
            c.nextChat = 1371;
        break;
		
	    case 1371:
            DialogueHandler.sendNpcChat1(c,"Oh but he was! He was the greatest hero this town has had!", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1350;
        break;
		
	    case 1372:
            DialogueHandler.sendPlayerChat1(c,"Okay, where is he? I'll kill him for you.");
            c.nextChat = 1373;
        break;
		
	    case 1373:
            DialogueHandler.sendNpcChat2(c,"You are foolish to try to take on Delrith with out knowing", "even a bit of knowledge about him!", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1350;
        break;
		
		case 1374:
            DialogueHandler.sendPlayerChat1(c,"Okay, where is he? I'll kill him for you.");
            c.nextChat = 1375;
        break;
		
	    case 1375:
            DialogueHandler.sendNpcChat2(c,"You are foolish to try to take on Delrith with out knowing", "even a bit of knowledge about him!", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1354;
        break;

	    case 1376:
            DialogueHandler.sendPlayerChat1(c,"Okay, where is he? I'll kill him for you.");
            c.nextChat = 1377;
        break;
		
	    case 1377:
            DialogueHandler.sendNpcChat2(c,"You have amazing courage to take on a task like this,", "but without Silverlight, you are doomed.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1367;
        break;
		
	    case 1378:
            DialogueHandler.sendPlayerChat1(c,"What is the magical incantation?");
            c.nextChat = 1379;
        break;
		
		case 1379:
		if(c.Incantation == 1) {
			DialogueHandler.sendNpcChat1(c,"Carlem... Gabindo... Purchai... Zaree... Camerinthum!", c.talkingNpc, "Wally");
			c.nextChat = 1367;
		} else if(c.Incantation == 2) {
			DialogueHandler.sendNpcChat1(c,"Purchai... Zaree... Gabindo... Cariem... Camerinthum!", c.talkingNpc, "Wally");
			c.nextChat = 1367;
		} else if(c.Incantation == 3) {
			DialogueHandler.sendNpcChat1(c,"Purchai... Camerinthum... Aber... Gabindo... Carlem!", c.talkingNpc, "Wally");
			c.nextChat = 1367;
		} else if(c.Incantation == 4) {
			DialogueHandler.sendNpcChat1(c,"Carlem... Aber... Camerinthum... Purchai... Gabindo!", c.talkingNpc, "Wally");
			c.nextChat = 1367;
		}
		break;
		
	    case 1380:
            DialogueHandler.sendPlayerChat1(c,"Where can I find Silverlight?");
            c.nextChat = 1381;
        break;
		
	    case 1381:
            DialogueHandler.sendPlayerChat3(c,"Silverlight has been passed down by Wally's", "dessendants, I believe it is currently in the case of one", "of the king's knights called Sir Prysin.");
            c.nextChat = 1382;
        break;
		
	    case 1382:
            DialogueHandler.sendNpcChat2(c,"He shouldn't be to hard to find. He lives in the royal", "palace in the city. Tell him Gypsy Aris sent you.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 1383;
        break;
		
	    case 1383:
            DialogueHandler.sendPlayerChat1(c,"Okay, thanks. I'll do my best to stop the demon.");
            c.nextChat = 1384;
        break;
		
	    case 1384:
            DialogueHandler.sendNpcChat1(c,"Good luck, and may Guthix be with you!", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 0;
			c.demonS = 10;
        break;
		
		case 1385:
			DialogueHandler.sendOption3(c,"What is the incantation again?", "Where do I find Sir Prysin?", "Nevermind.");
			c.DSOption6 = true;
		break;
		
	    case 1386:
            DialogueHandler.sendPlayerChat1(c,"What is the incantation again?");
            c.nextChat = 1387;
        break;
		
		case 1387:
			if(c.Incantation == 1) {
				DialogueHandler.sendNpcChat1(c,"Carlem... Gabindo... Purchai... Zaree... Camerinthum!", c.talkingNpc, "Wally");
				c.nextChat = 0;
			} else if(c.Incantation == 2) {
				DialogueHandler.sendNpcChat1(c,"Purchai... Zaree... Gabindo... Cariem... Camerinthum!", c.talkingNpc, "Wally");
				c.nextChat = 0;
			} else if(c.Incantation == 3) {
				DialogueHandler.sendNpcChat1(c,"Purchai... Camerinthum... Aber... Gabindo... Carlem!", c.talkingNpc, "Wally");
				c.nextChat = 0;
			} else if(c.Incantation == 4) {
				DialogueHandler.sendNpcChat1(c,"Carlem... Aber... Camerinthum... Purchai... Gabindo!", c.talkingNpc, "Wally");
				c.nextChat = 0;
		}
		break;
		
	    case 1388:
            DialogueHandler.sendPlayerChat1(c,"Where do I find Sir Prysin?");
            c.nextChat = 1389;
        break;
		
	    case 1389:
            DialogueHandler.sendNpcChat2(c,"Sir Pysin can be found in the kings royal palace", "just north of here.", c.talkingNpc, "Gypsy Aris");
            c.nextChat = 0;
        break;
		
	    case 1390:
            DialogueHandler.sendPlayerChat1(c,"Nevermind.");
            c.nextChat = 0;
        break;
		
	    case 1391:
            DialogueHandler.sendNpcChat1(c,"Hello, who are you?", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1392;
        break;
		
		case 1392:
			DialogueHandler.sendOption3(c,"I am a mighty adventurer. Who are you?", "I'm not sure. I was hoping you could tell me.", "Gypsy Aris said I should come and talk to you.");
			c.DSOption7 = true;
		break;
		
	    case 1393:
            DialogueHandler.sendPlayerChat1(c,"I am a mighty adventurer. Who are you?");
            c.nextChat = 394;
        break;
		
	    case 1394:
            DialogueHandler.sendNpcChat2(c,"I am Sir Prysin, one of the royal guards of the king.", "Now I ask you again, who are you?", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1392;
        break;
		
	    case 1395:
            DialogueHandler.sendPlayerChat1(c,"I'm not sure, I was hoping you could tell me.");
            c.nextChat = 1396;
        break;
		
	    case 1396:
            DialogueHandler.sendNpcChat1(c,"I'm sorry sir, but I do not know who you are.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1392;
        break;
		
	    case 1397:
            DialogueHandler.sendPlayerChat1(c,"Gypsy Aris said I should come and talk to you.");
            c.nextChat = 1398;
        break;
		
	    case 1398:
            DialogueHandler.sendNpcChat3(c,"Gypsy Aris? Is she still alive? I remember her from", "when I was pretty young. Well, what do you need to", "talk to me about?", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1399;
        break;
		
	    case 1399:
            DialogueHandler.sendPlayerChat1(c,"I need to find Silverlight.");
            c.nextChat = 1400;
        break;
		
	    case 1400:
            DialogueHandler.sendNpcChat1(c,"What do you need to find that for?", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1401;
        break;
		
	    case 1401:
            DialogueHandler.sendPlayerChat1(c,"I need to fight Delrith.");
            c.nextChat = 1402;
        break;
		
	    case 1402:
            DialogueHandler.sendNpcChat2(c,"Delrith? I thought the world was rid of him, thanks to ", "my great-grandfather.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1403;
        break;
		
	    case 1403:
            DialogueHandler.sendPlayerChat1(c,"Well the gypsy's crystal ball seems to think otherwise.");
            c.nextChat = 1404;
        break;
		
	    case 1404:
            DialogueHandler.sendNpcChat1(c,"Well, if the ball says so, I'd better help you.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1405;
        break;
		
	    case 1405:
            DialogueHandler.sendNpcChat1(c,"The problem is getting Silverlight.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1406;
        break;
		
	    case 1406:
            DialogueHandler.sendPlayerChat1(c,"You mean you don't have it?");
            c.nextChat = 1407;
        break;
		
	    case 1407:
            DialogueHandler.sendNpcChat4(c,"Oh, I do have it, but it is so powerful that the king", "made me put it in a special box requiring three", "different keys to open it. That way it wouldn't fall into", "the wrong hands.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1408;
        break;
		
		case 1408:
			DialogueHandler.sendOption2(c,"So give me the keys!", "And why is this a problem?");
			c.DSOption8 = true;
		break;
		
	    case 1409:
            DialogueHandler.sendPlayerChat1(c,"So give me the keys!");
            c.nextChat = 1410;
        break;
		
	    case 1410:
            DialogueHandler.sendNpcChat1(c,"Uhm well, it's not so easy.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1412;
        break;
		
		case 1411:
			DialogueHandler.sendPlayerChat1(c,"And why is this a problem?");
			c.nextChat = 1412;
		break;
		
	    case 1412:
            DialogueHandler.sendNpcChat2(c,"I kept one of the keys, I gave the other two to others", "for safekeeping.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1413;
        break;
		
	    case 1413:
            DialogueHandler.sendNpcChat1(c,"One I gave to Rovin the captain of the palace guard.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1414;
        break;
		
	    case 1414:
            DialogueHandler.sendNpcChat1(c,"The other I gave to the wizard Trailborn.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1415;
        break;
		
	    case 1415:
            DialogueHandler.sendPlayerChat1(c,"Can you give me your key?");
            c.nextChat = 1416;
        break;
		
	    case 1416:
            DialogueHandler.sendNpcChat1(c,"Um....ah....", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1417;
        break;
		
	    case 1417:
            DialogueHandler.sendNpcChat1(c,"Well, there's a problem there as well.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1418;
        break;
		
	    case 1418:
            DialogueHandler.sendNpcChat3(c,"I managed to drop the key in the drain just outside the", "palace kitchen. I can see it in there, but I can't reach", "it.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1419;
        break;
		
	    case 1419:
            DialogueHandler.sendPlayerChat1(c,"So where does the drain lead?");
            c.nextChat = 1420;
        break;
		
	    case 1420:
            DialogueHandler.sendNpcChat2(c,"It is the drain for the drainpipes running from the sink", "in the kitchen down to the palace sweres.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1421;
        break;
		
	    case 1421:
            DialogueHandler.sendPlayerChat1(c,"Where can I find Captain Rovin?");
            c.nextChat = 1422;
        break;
		
	    case 1422:
            DialogueHandler.sendNpcChat2(c,"Captain Rovin lives at the top of the guards' questers in", "the north-west wing of this palace.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1423;
        break;
		
	    case 1423:
            DialogueHandler.sendPlayerChat1(c,"Where does the wizard live?");
            c.nextChat = 1424;
        break;
		
	    case 1424:
            DialogueHandler.sendNpcChat1(c,"Wizard Trailborn?", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1425;
        break;
		
	    case 1425:
            DialogueHandler.sendNpcChat3(c,"He is one of the wizards in the tower, on the little", "island just off the south cost. I believe his quarters are", "on the second-floor of the tower.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1426;
        break;
		
	    case 1426:
            DialogueHandler.sendPlayerChat1(c,"Well, I'd better go key hunting.");
            c.nextChat = 1427;
        break;
		
	    case 1427:
            DialogueHandler.sendNpcChat1(c,"Okay, goodbye.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 0;
			c.demonS = 20;
        break;
		
	    case 1428:
            DialogueHandler.sendNpcChat2(c,"What are you doing up here? Only palace guards", "are allowed up here.", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1429;
        break;
		
	    case 1429:
            DialogueHandler.sendPlayerChat1(c,"Yea, I know, but this is important.");
            c.nextChat = 1430;
        break;
		
	    case 1430:
            DialogueHandler.sendNpcChat1(c,"Ok, I'm listenting. Tell me what's so important.", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1431;
        break;
		
	    case 1431:
            DialogueHandler.sendPlayerChat1(c,"There's a demon who wants to invade the city.");
            c.nextChat = 1432;
        break;
		
	    case 1432:
            DialogueHandler.sendNpcChat1(c,"Is it a powerful demon?", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1433;
        break;
		
	    case 1433:
            DialogueHandler.sendPlayerChat1(c,"Yes, very.");
            c.nextChat = 1434;
        break;
		
	    case 1434:
            DialogueHandler.sendNpcChat2(c,"As good as the palace guards are, I don't know if", "they're up to taking on a very powerful demon.", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1435;
        break;
		
	    case 1435:
            DialogueHandler.sendPlayerChat1(c,"It's not them who are going to fight the demon, it's me.");
            c.nextChat = 1436;
        break;
		
	    case 1436:
            DialogueHandler.sendNpcChat1(c,"What, all by yourself? How are you going to do that?", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1437;
        break;
		
	    case 1437:
            DialogueHandler.sendPlayerChat2(c,"I'm going to use the powerful sword Silverlight, which I", "believe you have one of the keys for?");
            c.nextChat = 1438;
        break;
		
	    case 1438:
            DialogueHandler.sendNpcChat1(c,"Yes, I do. But why should I give it to you?", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1439;
        break;
		
	    case 1439:
            DialogueHandler.sendPlayerChat1(c,"Sir Prysin said you would give me the key.");
            c.nextChat = 1440;
        break;
		
	    case 1440:
            DialogueHandler.sendNpcChat2(c,"Oh, he did, did he? Well I don't report to Sir Prysin, I", "report directly to the king!", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1441;
        break;
		
	    case 1441:
            DialogueHandler.sendNpcChat4(c,"I didn't work my way up through the ranks of the", "palace guards so I could take orders from an ill-bred", "moron who only has a job because his great-", "grandfather was a hero with a silly name!", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1442;
        break;
		
	    case 1442:
            DialogueHandler.sendPlayerChat1(c,"Why did he give you one of the keys then?");
            c.nextChat = 1443;
        break;
		
	    case 1443:
            DialogueHandler.sendNpcChat4(c,"Only because the king ordered him to! The king", "couldn't get Sir Prysin to part with his precious", "ancestral sword, but he made him lock it up so he", "couldn't lose it.", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1444;
        break;
		
	    case 1444:
            DialogueHandler.sendNpcChat2(c,"I got one key and I think some wizard got another.", "Now what happend to the third one?", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1445;
        break;
		
	    case 1445:
            DialogueHandler.sendPlayerChat1(c,"Sir Prysin dropped it down a drain!");
            c.nextChat = 1446;
        break;
		
	    case 1446:
            DialogueHandler.sendNpcChat1(c,"Ha ha ha! The idiot!", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1447;
        break;
		
	    case 1447:
            DialogueHandler.sendNpcChat2(c,"Okay, I'll give you the key, just so that it's you that", "kills the demon and not Sir Prysin!", c.talkingNpc, "Captain Rovin");
            c.nextChat = 1448;
        break;
		
		case 1448:
			DialogueHandler.sendItemChat1(c," ", "Captain Roving hands you a key.", 2400, 100);
			c.nextChat = 0;
			c.getItems().addItem(2400, 1);
			c.captainRovin = true;
		break;
		
	    case 1449:
            DialogueHandler.sendNpcChat1(c,"Have a good day, and be safe.", c.talkingNpc, "Captain Rovin");
            c.nextChat = 0;
        break;
		
	    case 1450:
            DialogueHandler.sendPlayerChat1(c,"That must be the key Sir Prysin dropped.");
            c.nextChat = 1451;
        break;
		
	    case 1451:
            DialogueHandler.sendPlayerChat3(c,"I don't seem to be able to reach it. I wonder if I can", "dislodge it somehow. That way it may go down into the", "sewers.");
            c.nextChat = 0;
        break;
		
	    case 1452:
            DialogueHandler.sendPlayerChat2(c,"OK, I think I've washed the key down into the sewer.", "I'd better go down and get it!");
            c.nextChat = 0;
        break;
		
	    case 1453:
            DialogueHandler.sendNpcChat1(c,"Ello young thingummywut.", c.talkingNpc, "Trailborn");
            c.nextChat = 1454;
        break;
		
	    case 1454:
            DialogueHandler.sendPlayerChat1(c,"I need to get a key given to you by Sir Prysin.");
            c.nextChat = 1455;
        break;
		
	    case 1455:
            DialogueHandler.sendNpcChat2(c,"Sir Prysin? Who's that? What would I want his key", "for?", c.talkingNpc, "Trailborn");
            c.nextChat = 1456;
        break;
		
	    case 1456:
            DialogueHandler.sendPlayerChat1(c,"Well, have you got any keys knocking around?");
            c.nextChat = 1457;
        break;
		
	    case 1457:
            DialogueHandler.sendNpcChat3(c,"Now you come to mention it, yes I do have a kay. It's", "in my special closet of valuable stuff. Now how do I get", "into that?", c.talkingNpc, "Trailborn");
            c.nextChat = 1458;
        break;
		
	    case 1458:
            DialogueHandler.sendNpcChat2(c,"I sealed it using one of my magic rituals. So it would", "make sense that another ritual would open it again.", c.talkingNpc, "Trailborn");
            c.nextChat = 1459;
        break;
		
	    case 1459:
            DialogueHandler.sendPlayerChat1(c,"So do you know what ritual to use?");
            c.nextChat = 1460;
        break;
		
	    case 1460:
            DialogueHandler.sendNpcChat1(c,"Let me think a second.", c.talkingNpc, "Trailborn");
            c.nextChat = 1461;
        break;
		
	    case 1461:
            DialogueHandler.sendNpcChat4(c,"Yes a simple drazier style ritual should suffice. Hmm,", "main problem with that is I'll need 25 sets of bones.", "Now where am I going to get hold of something like", "that?", c.talkingNpc, "Trailborn");
            c.nextChat = 1462;
        break;
		
	    case 1462:
            DialogueHandler.sendPlayerChat1(c,"I'll get you the bones.");
            c.nextChat = 1463;
        break;
		
	    case 1463:
            DialogueHandler.sendNpcChat1(c,"Ooh that would be very good of you.", c.talkingNpc, "Trailborn");
            c.nextChat = 1464;
        break;
		
	    case 1464:
            DialogueHandler.sendPlayerChat1(c,"Ok, I'll speak to you when I've got the bones.");
            c.nextChat = 0;
			c.boneSearch = true;
        break;
		
	    case 1465:
            DialogueHandler.sendNpcChat1(c,"How are you doing finding bones?", c.talkingNpc, "Trailborn");
			if(!c.getItems().playerHasItem(526, 25)) {
            c.nextChat = 1466;
			} else if(c.getItems().playerHasItem(526, 25)) {
			c.nextChat = 1467;
		}
        break;
		
	    case 1466:
            DialogueHandler.sendPlayerChat1(c,"I don't have all 25 yet, I'll be back.");
            c.nextChat = 0;
        break;
		
	    case 1467:
            DialogueHandler.sendPlayerChat1(c,"I have the bones right here.");
            c.nextChat = 1468;
        break;
		
	    case 1468:
            DialogueHandler.sendNpcChat1(c,"Give 'em here then.", c.talkingNpc, "Trailborn");
            c.nextChat = 1469;
        break;
		
		case 1469:
			DialogueHandler.sendStatement(c,"You give Trailborn 25 sets of bones.");
			c.nextChat = 1470;
		break;
		
	    case 1470:
            DialogueHandler.sendNpcChat1(c,"Hurrah! That's all 25 sets of bones.", c.talkingNpc, "Trailborn");
            c.nextChat = 1471;
        break;
		
	    case 1471:
            DialogueHandler.sendNpcChat4(c,"Wings of dark and colour too,", "Spreading in the morning dew;", "Locked away I have a key;", "Return it now, please, unto me.", c.talkingNpc, "Trailborn");
            c.nextChat = 1472;
        break;
		
		case 1472:
			DialogueHandler.sendItemChat1(c," ", "Trailborn hands you a key.", 2399, 100);
			c.nextChat = 1473;
			c.getItems().addItem(2399, 1);
			c.trailborn = true;
		break;
		
	    case 1473:
            DialogueHandler.sendPlayerChat1(c,"Thank you very much.");
        break;
		
	    case 1474:
            DialogueHandler.sendNpcChat1(c,"So how are you doing with getting the keys?", c.talkingNpc, "Sir Prysin");
			if(!c.captainRovin || !c.trailborn || !c.prysin) {
            c.nextChat = 475;
			} else if(c.captainRovin && c.trailborn && c.prysin) {
			c.nextChat = 1477;
		}
        break;
		
	    case 1475:
            DialogueHandler.sendPlayerChat1(c,"I don't have them all yet.");
            c.nextChat = 1476;
        break;
		
	    case 1476:
            DialogueHandler.sendNpcChat1(c,"Well hurry up, I doubt we have much time!", c.talkingNpc, "Sir Prysin");
            c.nextChat = 0;
        break;
		
	    case 1477:
            DialogueHandler.sendPlayerChat1(c,"I've got them right here!");
            c.nextChat = 1478;
        break;
		
	    case 1478:
            DialogueHandler.sendNpcChat1(c,"Excellent! Now I can give you Silverlight.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1479;
        break;
		
		case 1479:
			DialogueHandler.sendItemChat1(c," ", "Sir Prysin hands you Silverlight!", 2402, 100);
			c.nextChat = 1480;
			c.getItems().addItem(2402, 1);
			c.demonS = 30;
		break;
		
	    case 1480:
            DialogueHandler.sendNpcChat1(c,"Now, go kill that demon!", c.talkingNpc, "Sir Prysin");
            c.nextChat = 0;
        break;
		
	    case 1481:
            DialogueHandler.sendNpcChat1(c,"Have you slayed the demon yet?", c.talkingNpc, "Sir Prysin");
            c.nextChat = 482;
        break;
		
	    case 1482:
            DialogueHandler.sendPlayerChat1(c,"No not yet.");
            c.nextChat = 0;
        break;
		
		case 1483:
            DialogueHandler.sendNpcChat1(c,"Have you slayed the demon yet?", c.talkingNpc, "Sir Prysin");
            c.nextChat = 1484;
        break;
		
	    case 1484:
            DialogueHandler.sendPlayerChat1(c,"Not yet. And I, um, lost Silverlight.");
            c.nextChat = 1485;
        break;
		
	    case 1485:
            DialogueHandler.sendNpcChat2(c,"Yes, I know, someone returned it to me. Take better", "care of it this time.", c.talkingNpc, "Sir Prysin");
            c.nextChat = 0;
			c.getItems().addItem(2402, 1);
        break;
		
	    case 1486:
            DialogueHandler.sendPlayerChat1(c,"Now, what was the magical incantation again...");
            c.nextChat = 1487;
        break;
		
		case 1487:
				DialogueHandler.sendOption4(c,"Carlem... Gabindo... Purchai... Zaree... Camerinthum!", "Purchai... Zaree... Gabindo... Cariem... Camerinthum!", "Purchai... Camerinthum... Aber... Gabindo... Carlem!", "Carlem... Aber... Camerinthum... Purchai... Gabindo!");
				c.incantationOption = true;
		break;
		
		case 1488:
			DialogueHandler.sendStatement(c,"Delrith is sucked into the vortex...");
			c.nextChat = 1489;
		break;
		
		case 1489:
			DialogueHandler.sendStatement(c,"...back into the dark dimension from which he came.");
			c.nextChat = 0;
			DemonSlayer.questReward(c);
		break;

		}
	}
}
	
