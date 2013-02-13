package server.game.players;



/**
 * @author Omicron
 */
public abstract class Mobile {
	public int absX;
	public int absY;
	private int lastX, lastY;
	public int heightLevel;
    private Location location;
    private Sprite sprite;
    public transient Object distanceEvent;

    public Sprite getSprites() {
        return sprite;
    }

    public int getAbsX() {
		return absX;
	}

	public int getAbsY() {
		return absY;
	}

	public int getLastX() {
		return lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public void setAbsX(int absX) {
		this.lastX = this.absX;
		this.absX = absX;
	}

	public void setAbsY(int absY) {
		this.lastY = this.absY;
		this.absY = absY;
	}

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getTheLocation() {
        return this.location;
    }

    public void setDistanceEvent(Object event) {
        this.distanceEvent = event;
    }

}
