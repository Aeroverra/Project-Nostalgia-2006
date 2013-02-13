package server.game.objects;

import server.Server;


public class Objects {

	public long delay, oDelay;
	public int xp, item, owner, target, times;
	public boolean bait;

	public  int objectId;
	public int objectX;
	public  int objectY;
	public int objectHeight;
	public int objectFace;
	public int objectType;
	public int objectTicks;

	public int getObjectId() {
		return this.objectId;
	}

	public int getObjectX() {
		return objectX;
	}

	public  int getObjectY() {
		return objectY;
	}


	public Objects(int id, int x, int y, int height, int face, int type, int ticks) {
		this.objectId = id;
		this.objectX = x;
		this.objectY = y;
		this.objectHeight = height;
		this.objectFace = face;
		this.objectType = type;
		this.objectTicks = ticks;
	}




	public int getObjectHeight() {
		return this.objectHeight;
	}

	public int getObjectFace() {
		return this.objectFace;
	}

	public int getObjectType() {
		return this.objectType;
	}


}