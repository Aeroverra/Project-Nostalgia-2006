package server.content.skills.crafting;

import server.content.skills.misc.SkillHandler;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;

/**
 * @author somedude F2P Only(WOOL ONLY) Sheering.
 */
public class SpinningWheel extends SkillHandler {
	private static int WOOL = 1737, BALL_OF_WOOL = 1759,

	INTERFACE = 4429, FRAME = 1746;
	
	/**
	 * Handles Spinning data. index , lvl required, XP, item required, Final
	 * item
	 */
	public static int[][] data = { { 1, 1, 2, WOOL, BALL_OF_WOOL }, };

	/**
	 * Sends the spinning wheel interface
	 * 
	 * @param c
	 * @param Object
	 */
	public static void sendInterface(Client c, int Object) {
		for (int i = 0; i < data.length; i++) {
			if (data[i][3] > 0) {
				if (!c.getItems().playerHasItem(data[i][3])) {
					c.sendMessage("You need some wool to use this spinning wheel.");
					return;
				}
			}
		}
		c.getPA().itemOnInterface(FRAME, 150, BALL_OF_WOOL);
		c.getPA().sendFrame164(INTERFACE);
		c.getPA().sendFrame126("Ball of Wool", 2799);
	}

	/**
	 * Main Method
	 * 
	 * @param c
	 * @param datatype
	 */
	public static void spinItem(final Client c, int dataType) {
		if(c.hardbodies)
			return;
		for (int i = 0; i < data.length; i++) {
			if (dataType == data[i][0]) {
				// WOOL ONLY no requirements
				if (data[i][3] > 0) {
					if (!c.getItems().playerHasItem(data[i][3])) {
						c.sendMessage("You need some wool.");
						return;
					}
				}
				if (c.playerSkilling[12]) {
					return;
				}

				c.playerSkilling[12] = true;
				c.stopPlayerSkill = true;

				c.playerSkillProp[12][0] = data[i][0];// index
				c.playerSkillProp[12][1] = data[i][1];// Level required
				c.playerSkillProp[12][2] = data[i][2];// XP
				c.playerSkillProp[12][3] = data[i][3];// item required
				c.playerSkillProp[12][4] = data[i][4];// Final Item

				c.getPA().removeAllWindows();
				c.startAnimation(894);
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						deleteTime(c);
						c.getItems().deleteItem(c.playerSkillProp[12][3], 1);
						c.sendMessage("You get a " // Message
								+ c.getItems()
										.getItemName(c.playerSkillProp[12][4])
										.toLowerCase() + ".");

						c.getPA().addSkillXP(c.playerSkillProp[12][2] // XP
								, 12);
						c.getItems().addItem(c.playerSkillProp[12][4], 1);// item

						// ///////////////////////////////CHECKING//////////////////////
						if (!c.getItems().playerHasItem(
								c.playerSkillProp[12][3], 1)) {
							c.sendMessage("You ran out of wool.");
							resetSpin(c);
							container.stop();
						}
						if (c.doAmount <= 0) {
							resetSpin(c);
							container.stop();
						}
						if (!c.playerSkilling[12]) {
							resetSpin(c);
							container.stop();
						}
						if (!c.stopPlayerSkill) {
							resetSpin(c);
							container.stop();
						}

					}

					@Override
					public void stop() {

					}
				}, (int) 5.9);

				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {// animation
							@Override
							public void execute(CycleEventContainer container) {
								if (!c.playerSkilling[12]) {
									resetSpin(c);
									container.stop();
									return;
								}
								c.startAnimation(894);
								if (!c.stopPlayerSkill) {
									container.stop();
								}
							}

							@Override
							public void stop() {

							}
						}, (int) 5.8);

			}
		}
	}

	/**
	 * Sets the amount of wool that can be spun(EG. 5,10,28 times)
	 * 
	 * @param c
	 * @param amount
	 */
	public static void setAmount(Client c, int amount, int dataType) {
		c.doAmount = amount;
		spinItem(c, dataType);
		c.getPA().removeAllWindows();
	}

	/**
	 * Does the amount. Buttonid
	 * 
	 * @param c
	 * @param dataType
	 */
	public static void doAmount(Client c, int dataType) {
		switch (dataType) {
		case 10239: // 1
			setAmount(c, 1, 1);
			break;
		case 10238:// 5
			setAmount(c, 5, 1);
			break;
		case 6212:// X
		case 6211:// ALL
			setAmount(c, 28, 1);
			break;
		}
	}

	/**
	 * Resets spinning
	 * 
	 * @param c
	 */
	public static void resetSpin(Client c) {
		c.playerSkilling[12] = false;
		c.stopPlayerSkill = false;
		c.playerisSmelting = false;
		for (int i = 0; i < 7; i++) {
			c.playerSkillProp[12][i] = -1;
		}

	}
	
	
/*******************************Sheering****************************************/
	
	public static void sheerNPC(Client c) {
		c.startAnimation(893);
		c.getItems().addItem(WOOL, 1);
		c.sendMessage("You shear the sheep for its wool.");
		
		}
}
