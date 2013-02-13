package server.content.randoms;

import server.content.skills.misc.SkillHandler;
import server.game.players.Client;
import core.util.Misc;


public class EventHandler {
    
    private final static int SPAWN_EVENTS = 3;
    @SuppressWarnings("unused")
    private static int[][] randomLocations = {
            { 2 },
        };

    public static void getRandomEvent(Client c) {
        SkillHandler.resetallSkills(c);

        if (c.playerSkilling[8] && (Misc.random(1) == 0)) {
            SpiritTree.spawnSpiritTree(c);
        } else if (c.playerSkilling[10] && (Misc.random(0) == 1)) {
            RiverTroll.spawnTroll(c);
        } else if (c.playerSkilling[c.playerMining] && (Misc.random(1) == 0)) {
            RockGolem.spawnGolem(c);
        } else {
            executeSpawnEvent(c);
        }
    }

    private static void executeSpawnEvent(Client c) {
        int event = Misc.random(SPAWN_EVENTS);

        switch (event) {
        case 0:
            EvilChicken.spawnChicken(c);
            break;

        case 1:
            Genie.spawnGenie(c);
            break;

        case 2:
            Swarm.spawnSwarm(c);
            break;
        case 3:
            if(!c.inMime()) {
        c.pLastX = c.getX();
    	c.pLastY = c.getY();
    	c.pLastH = c.heightLevel;
    }
            MimeEvent.handleEvent(c);
            break;
        }
    }

    public static void handleFailure(Client c) {
    }

    public static void changeToSidebar(Client c, int bar, int i2nterface) {
        for (int i = 0; i < 14; i++) {
            c.setSidebarInterface(i, -1);
        }

        c.setSidebarInterface(10, 2449); // logout interface
        c.setSidebarInterface(bar, i2nterface);
        c.getPA().changeToSidebar(bar);
    }

    public static void changeBackSidebars(Client c) {
        c.getPA().setSidebarInterfaces(c);
    }
    
    
    
}
