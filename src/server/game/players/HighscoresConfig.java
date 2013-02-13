package server.game.players;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import server.content.randoms.MimeEvent;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;

import core.util.Misc;

public class HighscoresConfig {
	public static Rank rank[] = new Rank[51];

	public static void updateHighscores(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
		@Override
		public void execute(CycleEventContainer container) {
		    if(c.inMime()) { // only called upon login
		    MimeEvent.handleEvent(c);	
		    container.stop();
		}
		}

		@Override
		public void stop() {
		}
		}, 4);
	    for(int i = 0; i < 50; i ++) {
			if(rank[i] == null) {
				addRank(i, c);
				break;
			}
			if(rank[i].totalLevel <= c.totalLevel && rank[i].xpTotal < c.xpTotal) {
				addRank(i, c);
				break;
			}
			if(rank[i].playerName.equals(c.playerName))
				break;
		}
	}

	public static void updateHighscores1(final Client c) {
		for(int i = 0; i < 50; i ++) {
			if(rank[i] == null) {
				addRank(i, c);
				break;
			}
			if(rank[i].totalLevel <= c.totalLevel && rank[i].xpTotal < c.xpTotal) {
				addRank(i, c);
				break;
			}
			if(rank[i].playerName.equals(c.playerName))
				break;
		}
	}

	public static void addRank(int ranknum, Client c) {
		for(int i = 49; i >= ranknum; i --) {
			if(rank[i] != null) {
				if(rank[i].playerName.equals(c.playerName))
					rank[i] = null;
			}
			rank[i + 1] = rank[i];
		}
		if (c.playerRights <= 1) {
			Rank newRank = new Rank(ranknum, c.playerName, c.totalLevel, c.xpTotal);
			rank[ranknum] = newRank;
			saveHighscores();
		}
	}
	public static void addRank(int ranknum, String playerName, int totalLevel, int xpTotal) {
		for(int i = 49; i >= ranknum; i --) {
			if(rank[i] != null) {
				if(rank[i].playerName.equals(playerName))
					rank[i] = null;
			}
			rank[i + 1] = rank[i];
		}
		Rank newRank = new Rank(ranknum, playerName, totalLevel, xpTotal);
		rank[ranknum] = newRank;
		saveHighscores();
	}
	public static void saveHighscores() {
		BufferedWriter highscoresfile = null;
		try {
			highscoresfile = new BufferedWriter(new FileWriter("./data/highscores.txt"));
			highscoresfile.write("//Rank# PlayerName TotalLevel XPTotal");
			highscoresfile.newLine();
			for(int i = 0; i < 50; i ++) {
				if(rank[i] != null) {
					highscoresfile.write(i+"	"+rank[i].playerName+"	"+rank[i].totalLevel+"	"+rank[i].xpTotal);
					highscoresfile.newLine();
				}
			}
			highscoresfile.write(".");
			highscoresfile.close();
		} catch(IOException ioexception) {}
	}
	public static void loadHighscores() {
		String line = "";
		boolean EndOfFile = false;
		//int ReadMode = 0;
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader("./data/highscores.txt"));
		} catch(FileNotFoundException fileex) {
			return;
		}
		try {
			line = file.readLine();
		} catch(IOException ioexception) {
			return;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			try {
				line = file.readLine();
				if(line.equals(".")) {
					file.close();
					return;
				}
				String[] split = line.split("	");
				if(!(line.startsWith("//")) || !line.startsWith(".")) {
					loadHighscoreRank(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
				}
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try {
			file.close();
		} catch(IOException ioexception) {
		}
	}
	public static void loadHighscoreRank(int ranknum, String playerName, int totalLevel, int xpTotal) {
		for(int i = 0; i < 50; i ++) {
			if(rank[i] == null) {
				addRank(i, playerName, totalLevel, xpTotal);
				break;
			}
			if(rank[i].totalLevel <= totalLevel && rank[i].xpTotal < xpTotal) {
				addRank(i, playerName, totalLevel, xpTotal);
				break;
			}
			if(rank[i].playerName.equals(playerName))
				break;
		}
	}

}
class Rank {
	public int rank, totalLevel, xpTotal;
	public String playerName;
	public Rank(int rank, String playerName, int totalLevel, int xpTotal) {
		this.rank = rank;
		this.playerName = Misc.capitalize(playerName);
		this.totalLevel = totalLevel;
		this.xpTotal = xpTotal;
	}
}