package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.DeluxeRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookRoomTest {
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
        client2 = new Client("Mati", 210);
        client3 = new Client("Mati", 10000);
    }

    @Test
    @DisplayName("Should book a room.")
    void BookRoom() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);

        // What to test 1?
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // What to expect 1?
        assertTrue(booking1.isPresent());
        assertEquals(1, hotel1.getHotelBookings().size());
        assertTrue(hotel1.getHotelBookings().contains(booking1.get()));
        assertEquals(1, client1.getBookings().size());
        assertTrue(client1.getBookings().contains(booking1.get()));;
        assertEquals(1, hotel1.getHotelClients().size());
        assertTrue(hotel1.getHotelClients().contains(client1));
        assertEquals(Map.of(client1, 1), hotel1.getHotelClientBookings());

        // what to test 2?
        Optional<Booking> booking2 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to expect?
        assertTrue(booking2.isPresent());
        assertEquals(2, hotel1.getHotelBookings().size());
        assertEquals(Set.of(booking1.get(), booking2.get()), hotel1.getHotelBookings());
        assertEquals(1, client1.getBookings().size());
        assertTrue(client1.getBookings().contains(booking1.get()));
        assertEquals(1, client3.getBookings().size());
        assertTrue(client3.getBookings().contains(booking2.get()));
        assertEquals(2, hotel1.getHotelClients().size());
        assertEquals(Set.of(client1, client3), hotel1.getHotelClients());
        assertEquals(Map.of(client1, 1, client3, 1), hotel1.getHotelClientBookings());
    }

    @Test
    @DisplayName("Client balance should decrease as much as room cost.")
    void BookRoom_clientBalanceDecreases() {
        // setup
        hotel1.addRoomToHotel(room1);

        // what to test?
        assertEquals(10000, client1.getBalance());
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        // date range (6 days) * StandardRoom price (40) = 240

        // what to expect?
        assertEquals(9760, client1.getBalance());
    }

    @Test
    @DisplayName("Should not book room when room is not available.")
    void BookRoom_roomNotAvailable_roomNotBooked() {
        // setup
        hotel1.addRoomToHotel(room1);
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        assertTrue(booking1.isPresent());

        // what to test?
        Optional<Booking> booking2 = client3.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        // same dates as previous booking

        // what to expect?
        assertFalse(booking2.isPresent());
        assertEquals(1, hotel1.getHotelBookings().size());
        assertTrue(hotel1.getHotelBookings().contains(booking1.get()));
        assertTrue(client3.getBookings().isEmpty());

        // what to test?
        Optional<Booking> booking3 = client3.bookRoom(room1, LocalDate.of(2022, 4, 15), LocalDate.of(2022, 4, 21), hotel1);
        // not all dates are same

        // what to expect?
        assertFalse(booking3.isPresent());
    }

    @Test
    @DisplayName("Should not book room when room is not in a hotel")
    void BookRoom_roomNotInHotel_roomNotBooked() {
        // what to test?
        Optional<Booking> booking = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to expect?
        assertFalse(booking.isPresent());
    }

    @Test
    @DisplayName("Should not book room when not enough money.")
    void BookRoom_notEnoughMoney_roomNotBooked() {
        // setup
        hotel1.addRoomToHotel(room1);
        assertEquals(210, client2.getBalance());

        // what to test?
        Optional<Booking> booking = client2.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        // date range (6 days) * StandardRoom price (40) = 240

        // what to expect?
        assertFalse(booking.isPresent());
        assertEquals(210, client2.getBalance());
    }

}
