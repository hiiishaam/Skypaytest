package Services;

import java.util.ArrayList;
import java.util.Date;

import Entity.Booking;
import Entity.Room;
import Entity.User;
import Enums.RoomType;

public class ReservationService {
	 ArrayList<Room> rooms = new ArrayList<>();
	    ArrayList<User> users = new ArrayList<>();
	    ArrayList<Booking> bookings = new ArrayList<>();

	    public void setUser(int userId, int balance) {
	        for (User u : users) {
	            if (u.getUserId() == userId) return; 
	        }
	        users.add(0, new User(userId, balance)); 
	    }

	    public void setRoom(int roomNumber, RoomType roomType, int pricePerNight) {
	        for (Room r : rooms) {
	            if (r.getRoomNumber() == roomNumber) {
	                r.update(pricePerNight, roomType);
	                return;
	            }
	        }
	        rooms.add(0, new Room(roomNumber, roomType, pricePerNight));
	    }

	    // ---------------------------- BOOK ----------------------------
	    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {

	        if (checkOut.before(checkIn))
	            throw new IllegalArgumentException("checkOut date invalid.");

	        User u = findUser(userId);
	        Room r = findRoom(roomNumber);

	        int nights = daysBetween(checkIn, checkOut);
	        int total = nights * r.getPricePerNight();

	        if (u.getBalance() < total)
	            throw new IllegalArgumentException("Not enough balance.");

	        if (!isRoomAvailable(roomNumber, checkIn, checkOut))
	            throw new IllegalArgumentException("Room not available.");

	        u.debit(total);

	        bookings.add(0, new Booking(u, r, checkIn, checkOut, total));
	    }

	    private boolean isRoomAvailable(int roomNum, Date in, Date out) {
	        for (Booking b : bookings) {
	            if (b.toString().contains("Room " + roomNum)) {
	                if (!(out.before(b.checkIn) || in.after(b.checkOut))) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }

	    private User findUser(int id) {
	        return users.stream()
	                .filter(u -> u.getUserId() == id)
	                .findFirst()
	                .orElseThrow(() -> new IllegalArgumentException("User not found."));
	    }

	    private Room findRoom(int num) {
	        return rooms.stream()
	                .filter(r -> r.getRoomNumber() == num)
	                .findFirst()
	                .orElseThrow(() -> new IllegalArgumentException("Room not found."));
	    }

	    private int daysBetween(Date a, Date b) {
	        long diff = b.getTime() - a.getTime();
	        return (int)(diff / (1000*60*60*24));
	    }

	    
	    public void printAll() {
	        System.out.println("=== ROOMS ===");
	        rooms.forEach(System.out::println);

	        System.out.println("\n=== BOOKINGS ===");
	        bookings.forEach(System.out::println);
	    }

	    public void printAllUsers() {
	        System.out.println("=== USERS ===");
	        users.forEach(System.out::println);
	    }

}
