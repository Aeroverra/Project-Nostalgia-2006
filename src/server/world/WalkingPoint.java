package server.world;

/**
 * @author Omicron
 */
public class WalkingPoint {
	private int x;
	private int y;
	private boolean clippable;

	public WalkingPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public WalkingPoint(int x, int y, boolean clippable) {
		this(x, y);
		this.clippable = clippable;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isClippable() {
		return clippable;
	}

	public void incrementX(int amount) {
		x += amount;
	}

	public void incrementY(int amount) {
		y += amount;
	}
}
