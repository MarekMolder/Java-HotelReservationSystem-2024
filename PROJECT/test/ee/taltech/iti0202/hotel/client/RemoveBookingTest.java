package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.DeluxeRoom;
import ee.taltech.iti0202.hotel.rooms.DoubleRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RemoveBookingTest {
    private Room room1;
    private DoubleRoom room2;
    private DeluxeRoom room3;
    private Hotel hotel1;
    private Client client1;
    private Client client2;
    private Client client3;

    @BeforeEach
    void setUp() {
        room1 = new Room();
        room2 = new DoubleRoom();
        room3 = new DeluxeRoom();
        hotel1 = new Hotel();
        client1 = new Client("Mati", 10000);
        client2 = new Client("Mati", 210);
        client3 = new Client("Mati", 10000);
    }

    @Test
    @DisplayName("Should remove a booking.")
    void removeBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        assertTrue(booking1.isPresent());
        assertEquals(1, hotel1.getHotelBookings().size());
        assertTrue(hotel1.getHotelBookings().contains(booking1.get()));
        assertTrue(client1.getBookings().contains(booking1.get()));
        assertTrue(hotel1.getHotelClients().contains(client1));

        // what to test?
        client1.removeBooking(booking1.get(), hotel1);

        // what to expect?
        assertEquals(0, hotel1.getHotelBookings().size());
        assertEquals(0, client1.getBookings().size());
        assertEquals(0, client2.getBookings().size());
        assertFalse(hotel1.getHotelClients().contains(client1));
        assertFalse(hotel1.getHotelClients().contains(client3));
    }

    @Test
    @DisplayName("Should not remove a booking when booking does not exist in hotel list.")
    void removeBooking_bookingDoesNotExistInHotelList_bookingNotRemoved() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        Optional<Booking> booking1 = client2.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        assertEquals(0, hotel1.getHotelBookings().size());

        // what to test and expect?
        assertThrows(NoSuchElementException.class, () -> client2.removeBooking(booking1.get(), hotel1));
    }

    @Test
    @DisplayName("Should not remove a booking when booking does not exist in client list.")
    void removeBooking_bookingDoesNotExistInClientList_bookingNotRemoved() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        Optional<Booking> booking1 = client1.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        assertEquals(1, hotel1.getHotelBookings().size());

        // what to test and expect?
        assertFalse(client2.removeBooking(booking1.get(), hotel1));
    }

    @Test
    @DisplayName("Should get money back after removing booking.")
    void removeBooking_afterRemoveBooking_getMoneyBack() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        assertEquals(10000, client1.getBalance());
        assertEquals(10000, client3.getBalance());
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        // date range (6 days) * StandardRoom price (40) = 240

        Optional<Booking> booking2 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        // date range (6 days) * StandardRoom price (150) = 900

        assertTrue(booking1.isPresent());
        assertTrue(booking2.isPresent());
        assertEquals(9760, client1.getBalance());
        assertEquals(9100, client3.getBalance());

        // what to test?
        client1.removeBooking(booking1.get(), hotel1);
        client3.removeBooking(booking2.get(), hotel1);

        // what to expect
        assertEquals(10000, client1.getBalance());
        assertEquals(10000, client3.getBalance());
    }

    @Test
    @DisplayName("Should decrease client booking number in hotelClientBookings map when booking is removed.")
    void removeBooking_clientHaveMoreThan1Booking_decreasesHerBookingBy1() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        Optional<Booking> booking1 = client3.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking2 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Map<Client, Integer> expected1 = new HashMap<>();
        expected1.put(client3, 2);
        Map<Client, Integer> expeceted2 = new HashMap<>();
        expeceted2.put(client3, 1);

        assertTrue(booking1.isPresent());
        assertTrue(booking2.isPresent());
        assertEquals(expected1, hotel1.getHotelClientBookings());

        // what to test?
        client3.removeBooking(booking1.get(), hotel1);

        // what to expect?
        assertEquals(expeceted2, hotel1.getHotelClientBookings());

        client3.removeBooking(booking2.get(), hotel1);
        assertTrue(hotel1.getHotelClientBookings().isEmpty());
    }

    @Test
    @DisplayName("Should remove client with 1 booking in hotelClientBookings map when booking is removed.")
    void removeBooking_clientHave1Booking_removeClientFromHotelClientBookingMap() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        Optional<Booking> booking1 = client3.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Map<Client, Integer> expeceted1 = new HashMap<>();
        expeceted1.put(client3, 1);

        assertTrue(booking1.isPresent());
        assertEquals(expeceted1, hotel1.getHotelClientBookings());

        // what to test?
        client3.removeBooking(booking1.get(), hotel1);

        // what to expect?
        assertTrue(hotel1.getHotelClientBookings().isEmpty());
    }
}
