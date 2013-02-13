package server.content.music;

import core.util.Misc;
import server.Server;
import server.game.npcs.NPCHandler;
import server.game.npcs.NPCList;

/**
 * Eclipse IDE User: somedude 
 * Date: Aug 6, 2012 
 * Time: 5:07:09 PM 
 * Purpose: Handles all sound effects being used.
 * 
 */
public class Sound {
    /*
     * Singular sound effects
     */
    public final static int SHOOT_ARROW = 370;

    /**
     * Gets the NPC sound to be played based on the type.
     * 
     * @param npcID
     * @param type
     *            - Attack, Block and Death sounds
     */
    public static int getNPCSound(int npcID, String type) {
	String npc = getNPCId(npcID).toLowerCase();
	switch (type) {
	case "Attack":// Handles the attack sounds
	    switch(npc) {   
	    case "Man":
		return 418;
	
	    default:
		return 477;
	    }
	    
	    
	case "Block":// Handles the Block sounds
	    switch(npc) {   
	    case "Man":
		return 72;
	
	    default:
		return 477;
	    }
	    
	    
	case "Death":// Handles the Death sounds
	  if(npc.equalsIgnoreCase("man")||npc.contains("guard")||npc.contains("warrior")||
		  npc.equalsIgnoreCase("barbarian")) 
	      return 70;
	  if(npc.contains("woman"))
	      return 71;
	}
	return 477;//Nothing.
    }

    
    /**
     * Gets the NPC's ID based on its name.
     * 
     * @param NpcID
     *            - NPC Index.
     * @return
     */
    public static String getNPCId(int NpcID) {
	return getNpcListName(NpcID);
    }
    
    /**
	 * handles npc name in class now
	 * @param npcId
	 * @return
	 */
	public static String getNpcListName(int npcId) {
		for (NPCList i : NPCHandler.NpcList) {
			if (i != null) {
				if (i.npcId == npcId) {
					return i.npcName;
				}
			}
		}
		return "nothing";
	}
}
