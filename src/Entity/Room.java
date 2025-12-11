package Entity;

import Enums.RoomType;

public class Room {
	 private int roomNumber;
	    private RoomType type;
	    private int pricePerNight;

	    public Room(int roomNumber, RoomType type, int pricePerNight) {
	        this.roomNumber = roomNumber;
	        this.type = type;
	        this.pricePerNight = pricePerNight;
	    }

	    public int getRoomNumber() { return roomNumber; }
	    public RoomType getType() { return type; }
	    public int getPricePerNight() { return pricePerNight; }

	    public void update(int pricePerNight, RoomType type) {
	        this.pricePerNight = pricePerNight;
	        this.type = type;
	    }

	    @Override
	    public String toString() {
	        return "Room " + roomNumber + " (" + type + ") - Price/night: " + pricePerNight;
	    }

}
