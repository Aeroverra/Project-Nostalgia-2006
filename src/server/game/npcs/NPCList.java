package server.game.npcs;
public class NPCList {
	public int npcId;
	public String npcName;
	public int npcCombat;
	public int npcHealth;
	public int attackSound;
	public int blockSound;
	public int dieSound;

	public NPCList(int _npcId) {
		npcId = _npcId;
	}
}
