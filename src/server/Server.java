package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.DecimalFormat;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;
import org.omicron.jagex.runescape.CollisionMap;

import server.clip.region.ObjectDef;
import server.clip.region.Region;
import server.event.CycleEventHandler;
import server.event.EventManager;
import server.event.Task;
import server.event.TaskScheduler;
import server.game.items.GlobalDropsHandler;
import server.game.items.ItemDefinitions;
import server.game.npcs.NPCHandler;
import server.game.objects.doors.Doors;
import server.game.objects.doors.DoubleDoors;
import server.game.players.Client;
import server.game.players.HighscoresConfig;
import server.game.players.Player;
import server.game.players.PlayerHandler;
import server.game.players.PlayerSave;
import server.game.players.PlayerSaving;
import server.world.ItemHandler;
import server.world.ObjectHandler;
import server.world.ObjectManager;
import server.world.PlayerManager;
import server.world.ShopHandler;
import server.world.StillGraphicsManager;
import server.world.WalkingCheck;
import core.util.ControlPanel;
import core.util.SimpleTimer;
import core.util.log.Logger;

/**
 * Server.java
 *
 * @author Sanity
 * @author Graham
 * @author Blake
 * @author Ryan Lmctruck30
 * @author Izumi
 *
 */

public class Server {

	public static PlayerManager playerManager = null;
	private static StillGraphicsManager stillGraphicsManager = null;

	public static boolean sleeping;
	public static final int cycleRate;
	public static boolean UpdateServer = false;
	public static long lastMassSave = System.currentTimeMillis();
	private static SimpleTimer engineTimer, debugTimer;
	private static long cycleTime, cycles, totalCycleTime, sleepTime;
	private static DecimalFormat debugPercentFormat;
	public static boolean shutdownServer = false;
	public static boolean shutdownClientHandler;
	public static int serverlistenerPort;
	public static ItemHandler itemHandler = new ItemHandler();
	public static PlayerHandler playerHandler = new PlayerHandler();
	public static NPCHandler npcHandler = new NPCHandler();
	public static ShopHandler shopHandler = new ShopHandler();
	public static ObjectHandler objectHandler = new ObjectHandler();
	public static ObjectManager objectManager = new ObjectManager();
//	public static ControlPanel panel = new ControlPanel(false); // false if you want it off

	static {
		if(!Config.SERVER_DEBUG) {
			serverlistenerPort = 43594;
		} else {
			serverlistenerPort = 43594;
		}
		cycleRate = 600;
		shutdownServer = false;
		engineTimer = new SimpleTimer();
		debugTimer = new SimpleTimer();
		sleepTime = 0;
		debugPercentFormat = new DecimalFormat("0.0#%");
	}
	/**
	 * The task scheduler.
	 */
	private static final TaskScheduler scheduler = new TaskScheduler();

	/**
	 * Gets the task scheduler.
	 * @return The task scheduler.
	 */
	public static TaskScheduler getTaskScheduler() {
		return scheduler;
	}
	
	public static void main(java.lang.String args[]) throws NullPointerException, IOException {
		/**
		 * Starting Up Server
		 */
		ObjectDef.loadConfig();
		Region.load();
		ItemDefinitions.read();
		WalkingCheck.load();
		System.setOut(new Logger(System.out));
		System.setErr(new Logger(System.err));
		System.out.println("[Stage 1] NPC drops have been loaded...");
		System.out.println("[Stage 2] NPC spawns have been loaded...");
		System.out.println("[Stage 3] Shops have been loaded...");
		System.out.println("[Stage 4] Object spawns have been loaded...");
		System.out.println("[Stage 5] Connections are now being accepted...");

		/**
		 * Accepting Connections
		 */

		playerManager = PlayerManager.getSingleton();
		playerManager.setupRegionPlayers();
		stillGraphicsManager = new StillGraphicsManager();
		
		bind();
		
		/**
		 * Mysql stuff
		 */
		//Hiscores.connect();
		
		/**
		 * Initialise Handlers
		 */
		//CollisionMap.load("Data\\data\\collisiondata.dat");
		EventManager.initialize();
		Doors.getSingleton().load();
		DoubleDoors.getSingleton().load();
		Connection.initialize();
		//HighscoresConfig.loadHighscores();
		GlobalDropsHandler.initialize("globaldrops.txt");
		PlayerSaving.initialize();
		/**
		 * Server Successfully Loaded
		 */
		System.out.println("[Final Stage] F2p has been launched on localhost:" + serverlistenerPort + "...");
		
		/**
		 * Main Server Tick
		 */
		scheduler.schedule(new Task() { // just implemented task
			@Override
                    protected void execute() {
				try {
					while (!Server.shutdownServer) {
						if (sleepTime >= 0)
							Thread.sleep(sleepTime);
						else
							Thread.sleep(600);
						engineTimer.reset();
						itemHandler.process();
						playerHandler.process();
						npcHandler.process();
						shopHandler.process();
						CycleEventHandler.getSingleton().process();
						objectManager.process();
						cycleTime = engineTimer.elapsed();
						sleepTime = cycleRate - cycleTime;
						totalCycleTime += cycleTime;
						cycles++;
						debug();
						
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("A fatal exception has been thrown!");
					for(Player p : PlayerHandler.players) {
						if(p == null)
							continue;
						PlayerSave.saveGame((Client)p);
					}
				}
				System.exit(0);
	        }
        });
		
	}

	public static void processAllPackets() {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				while(PlayerHandler.players[j].processQueuedPackets());
			}
		}
	}

	public static boolean playerExecuted = false;
	private static void debug() {
		if (debugTimer.elapsed() > 360*1000 || playerExecuted) {
			long averageCycleTime = totalCycleTime / cycles;
			double engineLoad = ((double) averageCycleTime / (double) cycleRate);
			System.out.println("Currently online: " + PlayerHandler.getPlayerCount()+ ", engine load: "+ debugPercentFormat.format(engineLoad));
			totalCycleTime = 0;
			cycles = 0;
			//System.gc();
		//	System.runFinalization();
			debugTimer.reset();
			playerExecuted = false;
		}
	}
	
	 private static void bind() {
         ServerBootstrap serverBootstrap = new ServerBootstrap (new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
         serverBootstrap.setPipelineFactory (new PipelineFactory(new HashedWheelTimer()));
         serverBootstrap.bind (new InetSocketAddress(serverlistenerPort));	
     }
	public static long getSleepTimer() {
		return sleepTime;
	}

	public static StillGraphicsManager getStillGraphicsManager() {
		return stillGraphicsManager;
	}

	public static PlayerManager getPlayerManager() {
		return playerManager;
	}

	public static ObjectManager getObjectManager() {
		return objectManager;
	}

}
