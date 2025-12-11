package Entity;

import java.util.Date;

public class Booking {
	    public User userSnapshot;
	    public Room roomSnapshot;
	    public Date checkIn;
	    public Date checkOut;
	    public int totalPrice;

	    public Booking(User user, Room room, Date checkIn, Date checkOut, int totalPrice) {
	        this.userSnapshot = new User(user.getUserId(), user.getBalance());
	        this.roomSnapshot = new Room(room.getRoomNumber(), room.getType(), room.getPricePerNight());

	        this.checkIn = checkIn;
	        this.checkOut = checkOut;
	        this.totalPrice = totalPrice;
	    }
	    
	    @Override
	    public String toString() {
	        return "Booking => User " + userSnapshot.getUserId() +
	                ", Room " + roomSnapshot.getRoomNumber() +
	                ", Type: " + roomSnapshot.getType() +
	                ", Price/night: " + roomSnapshot.getPricePerNight() +
	                ", From: " + checkIn +
	                ", To: " + checkOut +
	                ", Total: " + totalPrice;
	    }

}
