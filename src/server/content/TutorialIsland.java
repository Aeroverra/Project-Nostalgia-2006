package server.content;

import server.game.players.Client;
import server.game.players.DialogueHandler;

/*
 * Eclipse IDE
 * User: somedude
 * Date: 12-feb.-2013
 * Time: 01:51:19
 * TODO:
 */
public class TutorialIsland {

    
    
    public static void sendCharacterCreation(Client c){
	c.getPA().showInterface(3559);
	c.canChangeAppearance = true;
	DialogueHandler.sendStartInfo(c, "To start the tutorial use your left mouse button to click on the", 
		"Runescape Guide in this room. You can't miss him! Use ", "your keyboard arrows to rotate the camera!", "", "Getting Started");	
    }
    
}
