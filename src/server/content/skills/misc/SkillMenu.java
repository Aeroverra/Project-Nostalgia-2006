package server.content.skills.misc;

import server.game.players.Client;

/*
 * Eclipse IDE
 * User: somedude
 * Date: Aug 15, 2012
 * Time: 6:16:53 PM
 */
public class SkillMenu {
    
    public static void sendInterface(Client c, String type) {
	switch(type.toLowerCase()) {
	case "unfired":
	    display5Item(c, 1787, 1789, 1791, 5352, 4438, "Pot", "Pie Dish", "Bowl", "Plant pot", "Pot lid");
	    break;
	case "fired":
	    display5Item(c, 1931, 2313, 1923, 5350, 4440, "Pot", "Pie Dish", "Bowl", "Plant pot", "Pot lid");
	    break;
	}
    }
    
    public static void display5Item(Client player, int i1, int i2, int i3,
	    int i4, int i5, String s1, String s2, String s3, String s4,
	    String s5) {
	player.getPA().itemOnInterface(8941, 120, i1);
	player.getPA().itemOnInterface(8942, 150, i2);
	player.getPA().itemOnInterface(8943, 150, i3);
	player.getPA().itemOnInterface(8944, 120, i4);
	player.getPA().itemOnInterface(8945, 150, i5);
	player.getPA().sendFrame126(s1, 8944);
	player.getPA().sendFrame126(s2, 8953);
	player.getPA().sendFrame126(s3, 8957);
	player.getPA().sendFrame126(s4, 8961);
	player.getPA().sendFrame126(s5, 8965);
	player.getPA().sendFrame164(8938);
    }
}
