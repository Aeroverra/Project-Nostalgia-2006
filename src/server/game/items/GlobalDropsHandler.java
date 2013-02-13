package server.game.items;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.util.Misc;

import server.Config;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;
import server.game.players.PlayerHandler;

/**
 * Handles global drops which respawn after set amount of time when taken
 * 
 * @author Stuart <RogueX>- edited by somedude
 * 
 */
public class GlobalDropsHandler {

	/**
	 * time in seconds it takes for the item to respawn
	 */
	private static final int TIME_TO_RESPAWN = 60;

	/**
	 * holds all the objects
	 */
	private static List<GlobalDrop> globalDrops = new ArrayList<GlobalDrop>();

	/**
	 * loads the items
	 */
		public static boolean initialize(String FileName) {
		    String line = "";
		    String token = "";
		    boolean EndOfFile = false;
		    BufferedReader characterfile = null;
		    try {
		            characterfile = new BufferedReader(new FileReader("./Data/data/"
		                            + FileName));
		    } catch (FileNotFoundException fileex) {
		    	Misc.println("File not loaded");
		            return false;
		    }
		    try {
		            line = characterfile.readLine();
		    } catch (IOException ioexception) {
		    	Misc.println("File not loaded");
		            return false;
		    }
		    while ((EndOfFile == false) && (line != null)) {
		            line = line.trim();
		            int spot = line.indexOf("=");
		            if (spot > -1) {
		            	String[] cmd = line.split("=");
		            	token = cmd[0].trim();
		            	String[] numbers = cmd[1].split("\\s+");
		                    if (token.equals("drop")) {
		                            int id = Integer.parseInt(numbers[1]);
		                            int x = Integer.parseInt(numbers[2]);
		                            int y = Integer.parseInt(numbers[3]);
		                            int amount = Integer.parseInt(numbers[4]);
		                          //  int height = Integer.parseInt(numbers[5]);
		                            globalDrops.add(new GlobalDrop(id,amount,x,y));	
		                    
		                    }
		            } else {
		                    if (line.equals("[ENDOFDROPLIST]")) {
		                            try {
		                                    characterfile.close();
		                            } catch (IOException ioexception) {
		                            	Misc.println("File not loaded");
		                            }
		                            Misc.println("Loaded " + globalDrops.size() + " global drops.");		   
		                            return true;
		                    }
		            }
		            try {
		                    line = characterfile.readLine();
		            } catch (IOException ioexception1) {
		            	Misc.println("File not loaded");
		                    EndOfFile = true;
		            }
		    }
		    try {
		            characterfile.close();
		    } catch (IOException ioexception) {
		    	Misc.println("File not loaded");
		    }
		    return false;
		}

	

	/**
	 * See if a drop exists at the given place
	 * 
	 * @param a
	 *            item id
	 * @param b
	 *            x cord
	 * @param c
	 *            y cord
	 * @return
	 */
	private static GlobalDrop itemExists(int a, int b, int c) {
		for (GlobalDrop drop : globalDrops) {
			if (drop.getId() == a && drop.getX() == b && drop.getY() == c) {
				return drop;
			}
		}
		return null;
	}

	/**
	 * Pick up an item at the given location
	 * 
	 * @param a
	 *            item id
	 * @param b
	 *            cord x
	 * @param c
	 *            cord y
	 */
	public static void pickup(Client client, int a, int b, int c) {
		GlobalDrop drop = itemExists(a, b, c);
		if (drop == null) {
			return;
		}
		if (drop.isTaken()) {
			return;
		}
		if (client.distanceToPoint(drop.getX(), drop.getY()) <= 1) {
			drop.setTakenAt(System.currentTimeMillis());
		drop.setTaken(true);
		if (client.getItems().freeSlots() > 0) {
			client.getItems().addItem(drop.getId(), drop.getAmount());
		}
		// TODO use the region manager for this...
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			Client cl = (Client) PlayerHandler.players[i];
			if (cl != null) {
				if (cl.distanceToPoint(drop.getX(), drop.getY()) <= 60) {
					cl.getItems().removeGroundItem(drop.getId(), drop.getX(),
							drop.getY(), drop.getAmount());
					client.getPA().sound(356);
				}
			}
		}
		CycleEventHandler.getSingleton().addEvent(null, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				for (GlobalDrop drop : globalDrops) {
					if (drop.isTaken()) {
						if ((System.currentTimeMillis() - drop.getTakenAt()) >= (TIME_TO_RESPAWN * 1000)) {
							drop.setTaken(false);
							for (int i = 0; i < Config.MAX_PLAYERS; i++) {
								Client client = (Client) PlayerHandler.players[i];
								if (client != null) {
									if (client.distanceToPoint(drop.getX(),
											drop.getY()) <= 60) {
										client.getItems().createGroundItem(
												drop.getId(), drop.getX(),
												drop.getY(), drop.getAmount());
										container.stop();
									}
								}
							}
						}
					}
				}
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}

		}, 1);
	}
	}

	/**
	 * Loads all the items when a player changes region
	 */
	public static void load(Client client) {
		for (GlobalDrop drop : globalDrops) {
			if (!drop.isTaken()) {
				if (client.distanceToPoint(drop.getX(), drop.getY()) <= 60) {
					client.getItems().createGroundItem(drop.getId(),
							drop.getX(), drop.getY(), drop.getAmount());
				}
			}
		}
	}

	/**
	 * Holds each drops data
	 * 
	 * @author Stuart
	 * 
	 */
	static class GlobalDrop {
		/**
		 * cord x
		 */
		int x;
		/**
		 * cord y
		 */
		int y;
		/**
		 * item id
		 */
		int id;
		/**
		 * item amount
		 */
		int amount;
		/**
		 * has the item been taken
		 */
		boolean taken = false;
		/**
		 * Time it was taken at
		 */
		long takenAt;

		/**
		 * Sets the drop arguments
		 * 
		 * @param a
		 *            item id
		 * @param b
		 *            item amount
		 * @param c
		 *            cord x
		 * @param d
		 *            cord y
		 */
		public GlobalDrop(int a, int b, int c, int d) {
			this.id = a;
			this.amount = b;
			this.x = c;
			this.y = d;
		}

		/**
		 * get cord x
		 * 
		 * @return
		 */
		public int getX() {
			return this.x;
		}

		/**
		 * get cord x
		 * 
		 * @return
		 */
		public int getY() {
			return this.y;
		}

		/**
		 * get the item id
		 * 
		 * @return
		 */
		public int getId() {
			return this.id;
		}

		/**
		 * get the item amount
		 * 
		 * @return
		 */
		public int getAmount() {
			return this.amount;
		}

		/**
		 * has the drop already been taken?
		 * 
		 * @return
		 */
		public boolean isTaken() {
			return this.taken;
		}

		/**
		 * set if or not the drop has been taken
		 * 
		 * @param a
		 *            true yes false no
		 */
		public void setTaken(boolean a) {
			this.taken = a;
		}

		/**
		 * set the time it was picked up
		 * 
		 * @param a
		 */
		public void setTakenAt(long a) {
			this.takenAt = a;
		}

		/**
		 * get the time it was taken at
		 * 
		 * @return
		 */
		public long getTakenAt() {
			return this.takenAt;
		}

	}

}
