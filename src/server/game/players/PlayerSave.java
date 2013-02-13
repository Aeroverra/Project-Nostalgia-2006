package server.game.players;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import core.util.Misc;

public class PlayerSave {

	/**
	 *Loading
	 **/

	public static int loadGame(Client p, String playerName, String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		boolean File1 = false;

		try {
			characterfile = new BufferedReader(new FileReader("./Data/characters/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}

		if (File1) {
		} else {
			Misc.println(playerName+": unexisting user.");
			p.newPlayer = false;
			return 0;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			Misc.println(playerName+": error loading file.");
			return 3;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-password")) {
						if (playerPass.equalsIgnoreCase(token2) || Misc.basicEncrypt(playerPass).equals(token2)) {
							playerPass = token2;
						} else {
							return 3;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						p.heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						p.teleportToX = (Integer.parseInt(token2) <= 0 ? 3094 : Integer.parseInt(token2));
					} else if (token.equals("character-posy")) {
						p.teleportToY = (Integer.parseInt(token2) <= 0 ? 3107 : Integer.parseInt(token2));
					} else if (token.equals("character-rights")) {
						p.playerRights = Integer.parseInt(token2);
					} else if (token.equals("flour-amount")) {
						p.flourAmount = Integer.parseInt(token2);
					} else if (token.equals("exp-gained")) {
						p.expGained = Integer.parseInt(token2);
					} else if (token.equals("whirl-time")) {
						p.whirlTime = Integer.parseInt(token2);
					  } else if (token.equals("whirlpool")) {
						p.whirlpool =  Boolean.parseBoolean(token2);
					  } else if (token.equals("aubury")) {
						p.aubury =  Boolean.parseBoolean(token2);
					  } else if (token.equals("sedridor")) {
						p.sedridor =  Boolean.parseBoolean(token2);
					} else if (token.equals("quest-points")) {
						p.questPoints = Integer.parseInt(token2);
					} else if (token.equals("cooks-assistant")) {
						p.cooksA = Integer.parseInt(token2);
					} else if (token.equals("restless-ghost")) {
						p.restlessG = Integer.parseInt(token2);
					} else if (token.equals("dorics-quest")) {
						p.doricsQ = Integer.parseInt(token2);
					} else if (token.equals("bankPin")) {
						p.bankPin = token2;
					} else if (token.equals("setPin")) {
						p.setPin = Boolean.parseBoolean(token2);
					} else if (token.equals("knight-sword")) {
						p.knightS = Integer.parseInt(token2);
					} else if (token.equals("rune-mysteries")) {
						p.runeM= Integer.parseInt(token2);
					} else if (token.equals("imp-catcher")) {
						p.impC = Integer.parseInt(token2);
					} else if (token.equals("sheep-shearer")) {
						p.sheepS = Integer.parseInt(token2);
					} else if (token.equals("witchs-potion")) {
						p.witchspot = Integer.parseInt(token2);
					} else if (token.equals("vampire-slayer")) {
						p.vampireslay = Integer.parseInt(token2);
					} else if (token.equals("isRunning")) {
						p.isRunning2 = Boolean.parseBoolean(token2);
					  } else if (token.equals("splitChat")) {
	                        p.splitChat = Boolean.parseBoolean(token2);
					  } else if (token.equals("brightness")) {
							p.brightness = Integer.parseInt(token2);
					  } else if (token.equals("soundEnabled")) {
							p.soundEnabled =  Boolean.parseBoolean(token2);
					  } else if (token.equals("mimeStage")) {
						p.mimeevent =  Integer.parseInt(token2);
					  } else if (token.equals("mimeTimer")) {
						p.mimeTimer =  Integer.parseInt(token2);
					  } else if (token.equals("mimeFailure")) {
						p.failure =  Integer.parseInt(token2);
					} else if (token.equals("lastLoginDate")) {
						p.lastLoginDate = Integer.parseInt(token2);
					} else if (token.equals("lastX")) {
						p.lastX = Integer.parseInt(token2);
					} else if (token.equals("lastY")) {
						p.lastY = Integer.parseInt(token2);
					} else if (token.equals("pLastX")) {
						p.pLastX = Integer.parseInt(token2);
					} else if (token.equals("pLastY")) {
						p.pLastY = Integer.parseInt(token2);
					} else if (token.equals("pLastH")) {
						p.pLastH = Integer.parseInt(token2);
					} else if (token.equals("character-energy")) {
						p.playerEnergy = Integer.parseInt(token2);
					} else if (token.equals("tutorial-progress")) {
						p.tutorialprog = Integer.parseInt(token2);
					} else if (token.equals("ratdied2")) {
						p.ratdied2 = Boolean.parseBoolean(token2);
					} else if (token.equals("crystal-bow-shots")) {
						p.crystalBowArrowCount = Integer.parseInt(token2);
					} else if (token.equals("skull-timer")) {
						p.skullTimer = Integer.parseInt(token2);
					} else if (token.equals("play-time")) {
						p.pTime = Integer.parseInt(token2);
					} else if (token.equals("magic-book")) {
						p.playerMagicBook = Integer.parseInt(token2);

					} else if (token.equals("special-amount")) {
						p.specAmount = Double.parseDouble(token2);

					} else if (token.equals("teleblock-length")) {
						p.teleBlockDelay = System.currentTimeMillis();
						p.teleBlockLength = Integer.parseInt(token2);
						/*} else if (token.equals("pc-points")) {
						p.pcPoints = Integer.parseInt(token2);	*/
					} else if (token.equals("taskAmount")) {
						p.taskAmount = Integer.parseInt(token2);
					}  else if (token.equals("music")) {
						for (int j = 0; j < token3.length; j++) {
							p.getPlayList().unlocked[j] = Boolean.parseBoolean(token3[j]);
						}
					} else if (token.equals("autoRet")) {
						p.autoRet = Integer.parseInt(token2);

					} else if (token.equals("flagged")) {
						p.accountFlagged = Boolean.parseBoolean(token2);
					} else if (token.equals("fightMode")) {
						p.fightMode = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-equip")) {
						p.playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						p.playerAppearance[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						p.playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						p.playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						p.bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						p.friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				case 9:
					/* if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					} */
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 13;
	}




	/**
	 *Saving
	 **/
	public static boolean saveGame(Client p) {
		if(!p.saveFile || p.newPlayer || !p.saveCharacter) {
			return false;
		}
		if(p.playerName == null || PlayerHandler.players[p.playerId] == null) {
			return false;
		}
		p.playerName = p.playerName2;
		int tbTime = (int)(p.teleBlockDelay - System.currentTimeMillis() + p.teleBlockLength);
		if(tbTime > 300000 || tbTime < 0){
			tbTime = 0;
		}

		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./Data/characters/"+p.playerName+".txt"));

			/*ACCOUNT*/
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(p.playerName, 0, p.playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(Misc.basicEncrypt(p.playerPass), 0, Misc.basicEncrypt(p.playerPass).length());
			characterfile.newLine();
			characterfile.newLine();

			/*CHARACTER*/
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(p.heightLevel), 0, Integer.toString(p.heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(p.absX), 0, Integer.toString(p.absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(p.absY), 0, Integer.toString(p.absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(p.playerRights), 0, Integer.toString(p.playerRights).length());
			characterfile.newLine();
			characterfile.write("exp-gained = ", 0, 13);
			characterfile.write(Integer.toString(p.expGained), 0, Integer.toString(p.expGained).length());
			characterfile.newLine();
			characterfile.write("whirl-time = ", 0, 13);
			characterfile.write(Integer.toString(p.whirlTime), 0, Integer.toString(p.whirlTime).length());
			characterfile.newLine();
			characterfile.write("whirl-pool = ", 0, 13);
			characterfile.write(Boolean.toString(p.whirlpool), 0, Boolean.toString(p.whirlpool).length());
			characterfile.newLine();
			characterfile.write("flour-amount = ", 0, 15);
			characterfile.write(Integer.toString(p.flourAmount), 0, Integer.toString(p.flourAmount).length());
			characterfile.newLine();
			characterfile.write("brightness = ", 0, 13);
			characterfile.write(Integer.toString(p.brightness), 0, Integer.toString(p.brightness).length());
			characterfile.newLine();
			characterfile.write("isRunning = ", 0, 12);
			characterfile.write(Boolean.toString(p.isRunning2), 0, Boolean.toString(p.isRunning2).length());
			characterfile.newLine();
			characterfile.write("soundEnabled = ", 0, 15);
			characterfile.write(Boolean.toString(p.soundEnabled), 0, Boolean.toString(p.soundEnabled).length());
			characterfile.newLine();
			characterfile.write("bankPin = ", 0, 10);
			characterfile.write(p.bankPin, 0, p.bankPin.length());
			characterfile.newLine();
			characterfile.write("setPin = ", 0, 9);
			characterfile.write(Boolean.toString(p.setPin), 0, Boolean.toString(p.setPin).length());
			characterfile.newLine();
			characterfile.write("splitChat = ", 0, 12);
			characterfile.write(Boolean.toString(p.splitChat), 0, Boolean
					.toString(p.splitChat).length());
			characterfile.newLine();
			characterfile.write("RatDied2 = ", 0, 11);
			characterfile.write(Boolean.toString(p.ratdied2), 0, Boolean.toString(p.ratdied2).length());
			characterfile.newLine();
			characterfile.write("sedridor = ", 0, 11);
			characterfile.write(Boolean.toString(p.sedridor), 0, Boolean.toString(p.sedridor).length());
			characterfile.newLine();
			characterfile.write("aubury = ", 0, 9);
			characterfile.write(Boolean.toString(p.aubury), 0, Boolean.toString(p.aubury).length());
			characterfile.newLine();
			characterfile.write("tutorial-progress = ", 0, 20);
			characterfile.write(Integer.toString(p.tutorialprog), 0, Integer.toString(p.tutorialprog).length());
			characterfile.newLine();
			characterfile.write("lastLoginDate = ", 0, 16);
			characterfile.write(Integer.toString(p.lastLoginDate), 0, Integer.toString(p.lastLoginDate).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString(p.playerEnergy), 0, Integer.toString(p.playerEnergy).length());
			characterfile.newLine();
			characterfile.write("last-X = ", 0, 9);
			characterfile.write(Integer.toString(p.lastX), 0, Integer.toString(p.lastX).length());
			characterfile.newLine();
			characterfile.write("last-Y = ", 0, 9);
			characterfile.write(Integer.toString(p.lastY), 0, Integer.toString(p.lastY).length());
			characterfile.newLine();
			characterfile.write("pLastX = ", 0, 9);
			characterfile.write(Integer.toString(p.pLastX), 0, Integer.toString(p.pLastX).length());
			characterfile.newLine();
			characterfile.write("pLastY = ", 0, 9);
			characterfile.write(Integer.toString(p.pLastY), 0, Integer.toString(p.pLastY).length());
			characterfile.newLine();
			characterfile.write("pLastH = ", 0, 9);
			characterfile.write(Integer.toString(p.pLastH), 0, Integer.toString(p.pLastH).length());
			characterfile.newLine();
			//start quests
			characterfile.write("quest-points = ", 0, 15);
			characterfile.write(Integer.toString(p.questPoints), 0, Integer.toString(p.questPoints).length());
			characterfile.newLine();
			characterfile.write("cooks-assistant = ", 0, 18);
			characterfile.write(Integer.toString(p.cooksA), 0, Integer.toString(p.cooksA).length());
			characterfile.newLine();
			characterfile.write("dorics-quest = ", 0, 15);
			characterfile.write(Integer.toString(p.doricsQ), 0, Integer.toString(p.doricsQ).length());
			characterfile.newLine();
			characterfile.write("knight-sword = ", 0, 15);
			characterfile.write(Integer.toString(p.knightS), 0, Integer.toString(p.knightS).length());
			characterfile.newLine();
			characterfile.write("rune-mysteries = ", 0, 17);
			characterfile.write(Integer.toString(p.runeM), 0, Integer.toString(p.runeM).length());
			characterfile.newLine();
			characterfile.write("restless-ghost = ", 0, 17);
			characterfile.write(Integer.toString(p.restlessG), 0, Integer.toString(p.restlessG).length());
			characterfile.newLine();
			characterfile.write("sheep-shearer = ", 0, 16);
			characterfile.write(Integer.toString(p.sheepS), 0, Integer.toString(p.sheepS).length());
			characterfile.newLine();
			characterfile.write("imp-catcher = ", 0, 14);
			characterfile.write(Integer.toString(p.impC), 0, Integer.toString(p.impC).length());
			characterfile.newLine();
			characterfile.write("witchs-potion = ", 0, 16);
			characterfile.write(Integer.toString(p.witchspot), 0, Integer.toString(p.witchspot).length());
			characterfile.newLine();
			characterfile.write("vampire-slayer = ", 0, 17);
			characterfile.write(Integer.toString(p.vampireslay), 0, Integer.toString(p.vampireslay).length());
			characterfile.newLine();
			characterfile.write("dorics-quest = ", 0, 15);
			characterfile.write(Integer.toString(p.doricsQ), 0, Integer.toString(p.doricsQ).length());
			characterfile.newLine();
			characterfile.write("crystal-bow-shots = ", 0, 20);
			characterfile.write(Integer.toString(p.crystalBowArrowCount), 0, Integer.toString(p.crystalBowArrowCount).length());
			characterfile.newLine();
			characterfile.write("skull-timer = ", 0, 14);
			characterfile.write(Integer.toString(p.skullTimer), 0, Integer.toString(p.skullTimer).length());
			characterfile.newLine();
			characterfile.write("play-time = ", 0, 12);
			characterfile.write(Integer.toString(p.pTime), 0, Integer.toString(p.pTime).length());
			characterfile.newLine();
			characterfile.write("magic-book = ", 0, 13);
			characterfile.write(Integer.toString(p.playerMagicBook), 0, Integer.toString(p.playerMagicBook).length());
			characterfile.newLine();

			characterfile.write("special-amount = ", 0, 17);
			characterfile.write(Double.toString(p.specAmount), 0, Double.toString(p.specAmount).length());
			characterfile.newLine();
			characterfile.write("music = ", 0, 8);
			String penis = "";
			for(int i = 0; i < p.getPlayList().unlocked.length; i++)
				penis += p.getPlayList().unlocked[i] + "\t";
			characterfile.write(penis);
			characterfile.newLine();

			characterfile.write("teleblock-length = ", 0, 19);
			characterfile.write(Integer.toString(tbTime), 0, Integer.toString(tbTime).length());
			characterfile.newLine();
			characterfile.write("pc-points = ", 0, 12);
			characterfile.write(Integer.toString(p.pcPoints), 0, Integer.toString(p.pcPoints).length());
			characterfile.newLine();
			characterfile.write("taskAmount = ", 0, 13);
			characterfile.write(Integer.toString(p.taskAmount), 0, Integer.toString(p.taskAmount).length());
			characterfile.newLine();
			characterfile.write("autoRet = ", 0, 10);
			characterfile.write(Integer.toString(p.autoRet), 0, Integer.toString(p.autoRet).length());
			characterfile.newLine();
			characterfile.write("flagged = ", 0, 10);
			characterfile.write(Boolean.toString(p.accountFlagged), 0, Boolean.toString(p.accountFlagged).length());
			characterfile.newLine();
			characterfile.write("fightMode = ", 0, 12);
			characterfile.write(Integer.toString(p.fightMode), 0, Integer.toString(p.fightMode).length());
			characterfile.newLine();
			characterfile.write("void = ", 0, 7);
			String toWrite = p.voidStatus[0] + "\t" + p.voidStatus[1] + "\t" + p.voidStatus[2] + "\t" + p.voidStatus[3] + "\t" + p.voidStatus[4];
			characterfile.write(toWrite);
			characterfile.newLine();
			characterfile.newLine();

			/*EQUIPMENT*/
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < p.playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerEquipment[i]), 0, Integer.toString(p.playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerEquipmentN[i]), 0, Integer.toString(p.playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();

			/*LOOK*/
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.playerAppearance.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerAppearance[i]), 0, Integer.toString(p.playerAppearance[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			/*SKILLS*/
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < p.playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerLevel[i]), 0, Integer.toString(p.playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerXP[i]), 0, Integer.toString(p.playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			/*ITEMS*/
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < p.playerItems.length; i++) {
				if (p.playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerItems[i]), 0, Integer.toString(p.playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerItemsN[i]), 0, Integer.toString(p.playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			/*BANK*/
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.bankItems.length; i++) {
				if (p.bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems[i]), 0, Integer.toString(p.bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItemsN[i]), 0, Integer.toString(p.bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			/*FRIENDS*/
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < p.friends.length; i++) {
				if (p.friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write("" + p.friends[i]);
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			/*IGNORES*/
			/*characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();*/
			/*EOF*/
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			Misc.println(p.playerName+": error writing file.");
			return false;
		}
		return true;
	}


}