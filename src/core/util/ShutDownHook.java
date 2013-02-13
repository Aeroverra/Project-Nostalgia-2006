package core.util;

import server.game.players.Client;
import server.game.players.PlayerHandler;

public class ShutDownHook extends Thread {

	@Override
	public void run() {
		System.out.println("Shutdown thread run.");
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client)PlayerHandler.players[j];
				server.game.players.PlayerSave.saveGame(c);
			}
		}
		System.out.println("Shutting down...");
	}

}