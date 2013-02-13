package server.game.players;

public class Highscores {

	private Client c;
	public Highscores(Client c) {
		this.c = c;
	}
	public void openHighscores() {
		sendHighscoreText();
		c.getPA().showInterface(6308);
	}
	public int[] text = {6402, 6403, 6404, 6405, 6406, 6407, 6408, 6409, 6410, 6411, 8578, 8579, 8580, 8581, 8582, 8583, 8584, 8585, 8586, 8587, 8588, 8589, 8590, 8591, 8592, 8593, 8594, 8595, 8596, 8597, 8598, 8599, 8600, 8601, 8602, 8603, 8604, 8605, 8606, 8607, 8608, 8609, 8610, 8611, 8612, 8613, 8614, 8615, 8616, 8617};
	public void sendHighscoreText() {
		c.getPA().sendFrame126("Project 06 - Top 50 Overall Highscores", 6399);
		for(int i = 0; i < 50; i ++) {
			if(HighscoresConfig.rank[i] != null) {
				c.getPA().sendFrame126((i + 1) +": "+HighscoresConfig.rank[i].playerName.substring(0,1) + HighscoresConfig.rank[i].playerName.substring(1)+" - "+HighscoresConfig.rank[i].totalLevel +" - "+HighscoresConfig.rank[i].xpTotal, text[i]);
			} else {
				c.getPA().sendFrame126("Rank "+(i + 1)+" open", text[i]);
			}
		}
	}
}