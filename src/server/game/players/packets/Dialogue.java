package server.game.players.packets;

import server.content.HairDresser;
import server.content.Sailing;
import server.content.dialoguesystem.DialogueSystem;
import server.content.quests.dialogue.BlackKnightsD;
import server.content.quests.dialogue.CooksAssistantD;
import server.content.quests.dialogue.DoricsQuestD;
import server.content.quests.dialogue.ImpCatcherD;
import server.content.quests.dialogue.KnightSwordD;
import server.content.quests.dialogue.RestlessGhostD;
import server.content.quests.dialogue.RuneMysteriesD;
import server.content.quests.dialogue.SheepShearerD;
import server.content.quests.dialogue.WitchsPotion;
import server.content.quests.misc.Tutorialisland;
import server.game.players.Client;
import server.game.players.DialogueHandler;
import server.game.players.PacketType;


/**
 * Dialogue
 **/
public class Dialogue implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		if(c.chatDialogue > 0) {
		DialogueSystem.sendDialogue(c, c.chatDialogue, c.npcType);
		return;
		}
		if(c.nextChat > 0) {
			DialogueHandler.sendDialogues(c, c.nextChat, c.talkingNpc);
			WitchsPotion.handledialogue(c,c.nextChat, c.talkingNpc);
			c.getVS().sendDialogue(c.nextChat, c.talkingNpc);
			Tutorialisland.sendDialogue(c, c.nextChat, c.talkingNpc);
			Sailing.sailingDia(c, c.nextChat, c.talkingNpc);
			HairDresser.dialogue(c, c.nextChat, c.talkingNpc);
			CooksAssistantD.dialogue(c, c.nextChat, c.talkingNpc);
			BlackKnightsD.dialogue(c, c.nextChat, c.talkingNpc);
			RestlessGhostD.dialogue(c, c.nextChat, c.talkingNpc);
			SheepShearerD.dialogue(c, c.nextChat, c.talkingNpc);
			DoricsQuestD.dialogue(c, c.nextChat, c.talkingNpc);
			ImpCatcherD.dialogue(c, c.nextChat, c.talkingNpc);
			KnightSwordD.dialogue(c, c.nextChat, c.talkingNpc);
			RuneMysteriesD.dialogue(c, c.nextChat, c.talkingNpc);
		} else {
			DialogueHandler.sendDialogues(c, 0, -1);
		}

	}

}
