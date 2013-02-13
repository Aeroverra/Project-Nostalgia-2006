package server.game.items;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ItemDefinitions {

	/**
	 * Creates the default item definition.
	 */
	public ItemDefinitions() {
		name = null;
		description = null;
		price = 0;
		low = 0;
		high = 0;
		stackable = false;
		noteable = false;
		weight = 0;
		bonuses = null;
		stand = -1;
		walk = -1;
		run = -1;
		turn90left = -1;
		turn90right = -1;
		turn180 = -1;
		attack = -1;
		block = -1;
	}

	/**
	 * Reads the definitions from the file.
	 */
	public static void read() {
		System.out.println("Item Defs has been loaded");
		try {
			DataInputStream in = new DataInputStream(new FileInputStream("./Data/data/itemdef.gsu"));
			total = in.readShort();
			if(items == null)
				items = new ItemDefinitions[total];
			for(int j = 0; j < total; j++) {
				if(items[j] == null) {
					items[j] = new ItemDefinitions();
				}
				items[j].getValues(in);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the stream values.
	 * @param in
	 */
	private void getValues(DataInputStream in) {
		try {
			do {
				int opcode = in.readByte();
				if(opcode == 0)
					return;
				if(opcode == 1) {
					name = in.readUTF();
				} else if(opcode == 2) {
					description = in.readUTF();
				} else if(opcode == 3) {
					price = in.readInt();
				} else if(opcode == 4) {
					low = in.readInt();
				} else if(opcode == 5) {
					high = in.readInt();
				} else if(opcode == 6) {
					stackable = true;
				} else if(opcode == 7) {
					noteable = true;
				} else if(opcode == 8) {
					weight = in.readDouble();
				} else if(opcode == 9) {
					int length = in.readShort();
					bonuses = new double[length];
					for (int index = 0; index < length; index++) {
						bonuses[index] = in.readDouble();
					}
				} else if(opcode == 10) {
					stand = in.readShort();
				} else if(opcode == 11) {
					walk = in.readShort();
				} else if(opcode == 12) {
					run = in.readShort();
				} else if(opcode == 13) {
					turn90left = in.readShort();
				} else if(opcode == 14) {
					turn90right = in.readShort();
				} else if(opcode == 15) {
					turn180 = in.readShort();
				} else if(opcode == 16) {
					attack = in.readShort();
				} else if(opcode == 17) {
					block = in.readShort();
				} else {
					System.out.println("Unrecognized opcode: " + opcode);
				}
			} while(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The item definition cache.
	 */
	public static ItemDefinitions items[];

	/**
	 * The total items read from the definitions.
	 */
	public static int total;

	/**
	 * Returns the total item number.
	 */
	public static int getTotal() {
		return total;
	}

	/**
	 * The item name.
	 */
	public String name;

	/**
	 * Returns the name of the item.
	 * @return
	 */
	public static String getName(int id) {
		return items[id].name;
	}

	/**
	 * The item description.
	 */
	public String description;

	/**
	 * Returns the description of an item.
	 */
	public static String getDescription(int id) {
		return items[id].description;
	}

	/**
	 * The item price.
	 */
	public int price;

	/**
	 * Returns the price of an item.
	 */
	public static int getPrice(int id) {
		return items[id].price;
	}

	/**
	 * The low alch value.
	 */
	public int low;

	/**
	 * Returns the low alch value of an item.
	 */
	public static int getLow(int id) {
		return items[id].low;
	}

	/**
	 * The high alch value.
	 */
	public int high;

	/**
	 * Returns the high alch value of an item.
	 */
	public static int getHigh(int id) {
		return items[id].high;
	}

	/**
	 * Can the item be stacked?
	 */
	public boolean stackable;

	/**
	 * Returns whether or not the item can be stacked.
	 */
	public static boolean canStack(int id) {
		return items[id].stackable;
	}

	/**
	 * Can the item be noted?
	 */
	public boolean noteable;
	
	/**
	 * Returns whether or not the item can be noted.
	 */
	public static boolean canNote(int id) {
		return items[id].noteable;
	}

	/**
	 * The weight of an item.
	 */
	public double weight;

	/**
	 * Returns the weight of an item.
	 */
	public static double getWeight(int id) {
		return items[id].weight;
	}

	/**
	 * The bonuses of an item.
	 */
	public double[] bonuses;

	/**
	 * Returns the array of bonuses for an item.
	 */
	public double[] getBonuses(int id) {
		return items[id].bonuses;
	}

	/**
	 * The stand animation of an item.
	 */
	public int stand;

	/**
	 * Returns the stand animation of an item.
	 */
	public static int getStand(int id) {
		return items[id].stand;
	}

	/**
	 * The walk animation of an item.
	 */
	public int walk;

	/**
	 * Returns the walk animation of an item.
	 */
	public static int getWalk(int id) {
		return items[id].walk;
	}

	/**
	 * The run animation of an item.
	 */
	public int run;

	/**
	 * Returns the run animation of an item.
	 */
	public static int getRun(int id) {
		return items[id].run;
	}

	/**
	 * The 90-degree left turn animation of an item.
	 */
	public int turn90left;

	/**
	 * Returns the 90-degree left turn animation of an item.
	 */
	public static int get90left(int id) {
		return items[id].turn90left;
	}

	/**
	 * The 90-degree right turn animation of an item.
	 */
	public int turn90right;

	/**
	 * Returns the 90-degree right turn animation of an item.
	 */
	public static int get90right(int id) {
		return items[id].turn90right;
	}

	/**
	 * The 180-degree turn animation of an item.
	 */
	public int turn180;

	/**
	 * Returns the 180-degree turn animation of an item.
	 */
	public static int get180(int id) {
		return items[id].turn180;
	}

	/**
	 * The attack animation of an item.
	 */
	public int attack;

	/**
	 * Returns the attack animation of an item
	 */
	public static int getAttack(int id) {
		return items[id].attack;
	}

	/**
	 * The block animation of an item.
	 */
	public int block;

	/**
	 * Returns the block animation of an item
	 */
	public static int getBlock(int id) {
		return items[id].block;
	}
}