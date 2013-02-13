package server.content.music;

import server.game.players.Client;
import core.util.Misc;
/**
 * Class to handle music stuff
 */
public class Music {

	public static int[][] MUSIC_DATA = {     //all the songs added
		{50, 51, 2},
		{49, 51, 2}, // Flour mill place
		{48, 50, 3},
		{46, 56, 8},
		{51, 60, 9},
		{46, 50, 12},
		{31, 74, 16},//mime event
		//{46, 50, 49},
		{52, 57, 14},
		{47, 52, 15},
		{45, 53, 18},
		{45, 54, 18},
		{47, 50, 35},
		{50, 150, 139},//harmony 2
		{46, 54, 23},// goblin village
		//{47, 50, 36},
		{51, 50, 36},
		{47, 51, 49},
		{51, 49, 50},
		{44, 75, 52},
		{46, 53, 54},
		{49, 54, 111},
		{45, 75, 57},
		{47, 47, 62},//Newbie Melody, Tutorial Island corrupted
		{47, 48, 62},//Newbie Melody, Tutorial Island
		{48, 47, 62},//Newbie Melody, Tutorial Island
		{48, 48, 62},//Newbie Melody, Tutorial Island
		{48, 148, 144},//underground, underground
		{49, 48, 62},//Newbie Melody, Tutorial Island
		{50, 49, 64},
		{49, 49, 64},
		{43, 75, 52},//Miracle Dance, Mind Altar
		//{51, 48, 69}, // this song is currently corrupt! Do not play!
		//{46, 52, 107}, // falador different
		{46, 52, 72}, // falador
		{50, 50, 76},
	        {48, 49, 358}, //Vision - wizard's tower
		{46, 49, 92},// karamja
		{45, 49, 92},// karamja diff region
		{51, 54, 93}, //Edgeville
		{46, 55, 96}, //this song is currently corrupt! Do not play!
		{48, 54, 98},
		{47, 49, 105},
		{50, 52, 106},
		{44, 53, 119},
		{51, 51, 123},
		{50, 53, 125}, //Varrock But Laggyyyy
		// {50, 53, 157}, //Varrock
		{46, 51, 127},
		{48, 53, 141},
		{41, 75, 52},//Down to Earth, Earth Altar
		{42, 75, 52},//Zealot, Water Altar
		{48, 51, 151},
		{51, 52, 157},
		{51, 53, 157},
		{52, 51, 122}, //Duel Arena Hospital - Shine
		{41, 40, 588}, //Pest Control - In-game
		{41, 41, 587}, //Pest Control - Lobby
		{40, 75, 52},//Quest, Fire Altar
		{55, 51, 380},//Barrows
		{50, 55, 169},
		{49, 154, 144}, // varrock sewers repeated
		{50, 154, 144}, //Varrock Sewers
		{48, 154, 144}, //EdgeVille dungeon
		{48, 153, 144}, //EdgeVilledungeon
		{48, 150, 144},// draynor sewers
		{48, 151, 144},// draynor sewers
		{48, 55, 169}, //Wilderness North of Edge
		{38, 80, 469}, //Tzhaar cave
		{49, 53, 175},
		{50, 54, 177},
	//	{46, 49, 180},
		//{45, 52, 186}, ARRIVAL CORRUPTED
		{45, 52, 72},
		{39, 75, 52},//Heart and Mind, Body Altar
		{47, 153, 325},//Cave Background, Dwarven Mines
		{46, 153, 325},//Cave Background, Dwarven Mines
		{47, 152, 325},//Cave Background, Dwarven Mines
		{49, 50, 327},
		{47, 53, 49}, //Wander
		{48, 52, 333},
		{50, 56, 337},
		{40, 74, 419},
		{47, 56, 121},
		{38, 48, 80}, //Soundscape, Castle Wars
		{37, 148, 28}, //Attack 5 - Castle Wars, Sara Portal
		{43, 54, 60}, //Lightwalk - Camelot
		{40, 53, 328}, //March
		{29, 81, 537},//Dogs of War - Stronghold of Security - Vault of War
		{31, 81, 558},//Food for Thought - Stronghold of Security - Catacomb of Famine
		{33, 82, 559},//Malady - Stronghold of Security - Pit of Pestilence
		{36, 81, 560},//Dance of Death - Stronghold of Security - Sepulchre of Death
		{41, 58, 141}, //Relleka Rock Crabs
		{41, 53, 140} //Ranging Guild
	};

	/**
	 * Checks how many areas we have loaded.
	 * @param c Client.
	 */
	public static void checkMusic(Client c) {
		if (c.playerRights == 3 || c.playerRights == 5) {
			for (int[] aMUSIC_DATA : MUSIC_DATA) {
				if (c.getX() / 64 == aMUSIC_DATA[0] && c.getY() / 64 == aMUSIC_DATA[1]/* && player.getPlayList().auto*/) {
					c.sendMessage("@blu@Region " +aMUSIC_DATA[0]+" : " + aMUSIC_DATA[1] + "   Music " + ": " + aMUSIC_DATA[2]);
					//	}
				}
			}

		}
	}

	/**
	 * Checks which song is played in which region.
	 * @param player the player.
	 */
	public static void playMusic(Client player) {
		for (int[] aMUSIC_DATA : MUSIC_DATA) {
			if (player.getX() / 64 == aMUSIC_DATA[0] && player.getY() / 64 == aMUSIC_DATA[1]/* && player.getPlayList().auto*/) {
				if (player.getTemporary("CURRENT_SONG") == null || (Integer) player.getTemporary("CURRENT_SONG") != aMUSIC_DATA[2]) {
					player.getPlayList();
					if (player.getPlayList().auto) {
						player.addTemporary("CURRENT_SONG", aMUSIC_DATA[2]);
					playMusic(player,aMUSIC_DATA[2]);
					}
					int[] edgeVilleSongs = {98, 111, 127, 157, 106};
					int toPlay = Misc.random(4);
					if (aMUSIC_DATA[0] == 48 && aMUSIC_DATA[1] == 54) {
						aMUSIC_DATA[2] = edgeVilleSongs[toPlay];
					}
					player.getPlayList().playSong(aMUSIC_DATA[2]);
				}
			}
		}
	}

	/**
	 * 
	 * song packets
	 * @param song
	 */
	public static void playMusic(Client c,int song) {
		c.outStream.createFrame(74);
		c.outStream.writeWordBigEndian(song);

	}

	public static void sendQuickSong(Client c,int id, int songDelay) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(121);
			c.getOutStream().writeWordBigEndian(id);
			c.getOutStream().writeWordBigEndian(songDelay);
			c.flushOutStream();
		}
	}
}
