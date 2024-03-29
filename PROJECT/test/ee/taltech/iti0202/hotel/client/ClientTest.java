package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.DeluxeRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {
    private Room room1;
    private DeluxeRoom room3;
    private Hotel hotel1;
    private Client client1;
    private Client client2;
    private Client client3;

    @BeforeEach
    void setUp() {
        room1 = new Room();
        room3 = new DeluxeRoom();
        hotel1 = new Hotel();
        client1 = new Client("Mati", 10000);
        client2 = new Client("Mati", 0);
        client3 = new Client("Mati", 10000);
    }

    @Test
    void clientGetReviews() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        client3.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("Lahe hotell", 5);

        // what to test?
        client3.writeReview("Lahe hotell", 5, hotel1);

        // what to expect?
        assertEquals(expected, client3.getReviews());
    }

    @Test
    void clientGetBookings() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);

        // what to test?
        Optional<Booking> booking1 = client3.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking2 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        List<Booking> expected = new ArrayList<>();
        expected.add(booking1.get());
        expected.add(booking2.get());

        // what to expect?
        assertEquals(expected, client3.getBookings());
    }

    @Test
    void clientGetMoney() {
        assertEquals(10000, client1.getBalance());
        assertEquals(0, client2.getBalance());
    }
}
