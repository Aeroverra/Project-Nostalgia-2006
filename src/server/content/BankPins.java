package server.content;

import server.game.players.Client;
import core.util.Misc;

public class BankPins {

	public enum State {
		ONE,
		TWO,
		THREE,
		FOUR,
	}

	

	public static void reset(Client client) {
		client.bankPin = "";
		client.attempts = 0;
		client.enteredPin = "";
		client.fullPin = "";
		client.setPin = false;
		for(int i = 0; i < 3; i++)
		    client.getPA().sendFrame126("?", 14913+ i);
	}

	public static void close(Client client) {
		client.enteredPin = "";
		client.getPA().removeAllWindows();
		client.state = State.ONE;
		for(int i = 0; i < 3; i++)
		    client.getPA().sendFrame126("?", 14913+ i);
	}

	public static void open(Client client) {
		if (!(client.fullPin.equalsIgnoreCase(""))) {
			client.getPA().openUpBank();
			return;
		}
		for(int i = 0; i < 3; i++)
		    client.getPA().sendFrame126("?", 14913+ i);
		client.getPA().showInterface(7424);
		resend(client);
		client.state = State.ONE;
	}

	private static void resend(Client client) {
		if (!(client.fullPin.equalsIgnoreCase(""))) {
			client.getPA().openUpBank();
			return;
		}
		mixNumbers(client);
		switch(client.state) {
		case ONE:
			client.getPA().sendFrame126("First click the FIRST digit", 15313);
			break;
		case TWO:
			client.getPA().sendFrame126("Then click the SECOND digit", 15313);
			client.getPA().sendFrame126("*", 14913);
			break;
		case THREE:
			client.getPA().sendFrame126("Then click the THIRD digit", 15313);
			client.getPA().sendFrame126("*", 14914);
			break;
		case FOUR:
			client.getPA().sendFrame126("And lastly click the FOURTH digit", 15313);
			client.getPA().sendFrame126("*", 14915);
			break;
		}
		sendPins(client);
	}

	public static void pinEnter(Client c, int button) {
		switch(c.state) {
		case ONE:
		case TWO:
		case THREE:
		case FOUR:
			enterPin(c, button, c.state);
			break;
		}
	}

	private static void enterPin(Client client, int button, State which) {
		for(int i = 0; i < getActionButtons().length; i++) {
			if(getActionButtons()[i] == button) {
				client.enteredPin += getBankPins()[i]+"";
			}
		}
		switch(which) {
		case ONE:
			client.state = State.TWO;
			resend(client);
			break;
		case TWO:
			client.state = State.THREE;
			resend(client);
			break;
		case THREE:
			client.state = State.FOUR;
			resend(client);
			break;
		case FOUR:
			if(!client.setPin) {
				client.sendMessage("You have successfully set your bank pin.");
				client.bankPin = client.enteredPin.trim();
				client.fullPin = client.enteredPin.trim();
				client.setPin = true;
				resend(client);
			} else {
				if(client.bankPin.equalsIgnoreCase(client.enteredPin.trim())) {
					client.sendMessage("You have successfully entered your bank pin.");
					client.fullPin = client.enteredPin.trim();
					resend(client);
				} else {
					client.sendMessage("The pin you have entered is incorrect.");
					close(client);
				}
			}
			client.state = State.ONE;
			break;
		}
	}

	private static void sendPins(Client client) {
		if (!(client.fullPin.equalsIgnoreCase(""))) {
			client.getPA().openUpBank();
			return;
		}
		for(int i = 0; i < getBankPins().length; i++) {
			client.getPA().sendFrame126(""+getBankPins()[i], 14883+i);
		}
	}

	private static void mixNumbers(Client c) {
		for(int i = 0; i < bankPins.length; i++) {
			bankPins[i] = -1;
		}
		for(int i = 0; i < bankPins.length; i++) {
			for(int i2 = 0; i2 < 9999; i2++) {
				boolean can = true;
				int random = Misc.random(9);
				for(int i3 = 0; i3 < bankPins.length; i3++) {
					if(random == bankPins[i3]) {
						can = false;
						random = Misc.random(9);
					}
				}
				if(!can) {
					continue;
				} else {
					bankPins[i] = random;
					break;
				}
			}
		}
		sendPins(c);
	}

	private static int[] getBankPins() {
		return bankPins;
	}

	private static int[] getActionButtons() {
		return actionButtons;
	}


	private static int bankPins[] = {
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1
	};

	private static int actionButtons[] = { 
		58025, 58026, 58027, 58028, 
		58029, 58030, 58031, 58032, 
		58033, 58034
	};

	public static String getFullPin(Client client) {
		return client.fullPin;
	}
	
	public static void handlePinbuttons(Client c, int b) {
	switch(b) {
	case 58074:
		close(c);
		break;

	case 58025:
	case 58026:
	case 58027:
	case 58028:
	case 58029:
	case 58030:
	case 58031:
	case 58032:
	case 58033:
	case 58034:
		pinEnter(c,b);
		break;
	}
	
	}
}