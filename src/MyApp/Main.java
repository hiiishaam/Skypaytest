package MyApp;

import java.security.Provider.Service;
import java.text.SimpleDateFormat;

import Enums.RoomType;
import Services.ReservationService;

public class Main {
    public static void main(String[] args) throws Exception {

        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        ReservationService s = new ReservationService() ;

        // Rooms
        s.setRoom(1, RoomType.STANDARD, 1000);
        s.setRoom(2, RoomType.JUNIOR, 2000);
        s.setRoom(3, RoomType.MASTER, 3000);

        // Users
        s.setUser(1, 5000);
        s.setUser(2, 10000);

        // TESTS
        try { s.bookRoom(1, 2, f.parse("30/06/2026"), f.parse("07/07/2026")); } catch (Exception e) { System.out.println(e.getMessage()); }
        try { s.bookRoom(1, 2, f.parse("07/07/2026"), f.parse("30/06/2026")); } catch (Exception e) { System.out.println(e.getMessage()); }
        try { s.bookRoom(1, 1, f.parse("07/07/2026"), f.parse("08/07/2026")); } catch (Exception e) { System.out.println(e.getMessage()); }
        try { s.bookRoom(2, 1, f.parse("07/07/2026"), f.parse("09/07/2026")); } catch (Exception e) { System.out.println(e.getMessage()); }
        try { s.bookRoom(2, 3, f.parse("07/07/2026"), f.parse("08/07/2026")); } catch (Exception e) { System.out.println(e.getMessage()); }

        s.setRoom(1, RoomType.MASTER, 10000);

        s.printAll();
        s.printAllUsers();
    }
}
