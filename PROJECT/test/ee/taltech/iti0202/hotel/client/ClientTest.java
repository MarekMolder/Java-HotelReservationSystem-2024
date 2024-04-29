package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.hotel.ECountryAndCitys;
import ee.taltech.iti0202.hotel.hotel.EServices;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.hotel.HotelBuilder;
import ee.taltech.iti0202.hotel.reservationSystem.ReservationSystem;
import ee.taltech.iti0202.hotel.rooms.DeluxeRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Room room1;
    private DeluxeRoom room3;
    private Hotel hotel1;
    private ReservationSystem reservationSystem;
    private Client client1;
    private Client client2;
    private Client client3;

    @BeforeEach
    void setUp() {
        room1 = new Room();
        room3 = new DeluxeRoom();
        hotel1 = new HotelBuilder()
                .setCountry(ECountryAndCitys.ESTONIA)
                .setCity("Tallinn").createHotel();
        reservationSystem = new ReservationSystem();
        client1 = new Client("Mati", 10000);
        client2 = new Client("Mati", 0);
        client3 = new Client("Mati", 10000);
    }

    @Test
    void getBalance() {
        assertEquals(BigDecimal.valueOf(10000.0), client1.getBalance());
        assertEquals(BigDecimal.valueOf(0.0), client2.getBalance());
    }

    @Test
    void subtractBalance() {
        assertEquals(BigDecimal.valueOf(10000.0), client1.getBalance());
        assertEquals(BigDecimal.valueOf(0.0), client2.getBalance());

        client1.subtractBalance(BigDecimal.valueOf(50));
        client2.subtractBalance(BigDecimal.valueOf(50));

        assertEquals(BigDecimal.valueOf(9950.0), client1.getBalance());
        assertEquals(BigDecimal.valueOf(-50.0), client2.getBalance());
    }

    @Test
    void addBalance() {
        assertEquals(BigDecimal.valueOf(10000.0), client1.getBalance());
        assertEquals(BigDecimal.valueOf(0.0), client2.getBalance());

        client1.addBalance(BigDecimal.valueOf(50));
        client2.addBalance(BigDecimal.valueOf(50));

        assertEquals(BigDecimal.valueOf(10050.0), client1.getBalance());
        assertEquals(BigDecimal.valueOf(50.0), client2.getBalance());
    }

    @Test
    void getReviews() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        reservationSystem.addHotelToSystem(hotel1);

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);

        // what to test?
        client3.writeReview("Lahe hotell", 5, hotel1);

        // what to expect?
        assertEquals(1, client3.getReviews().size());
        assertEquals(5, client3.getReviews().getFirst().getScore());
        assertEquals("Lahe hotell", client3.getReviews().getFirst().getReview());
    }

    @Test
    void getBookings() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        reservationSystem.addHotelToSystem(hotel1);

        // what to test?
        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1,client3);
        Optional<Booking> booking2 = reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);

        List<Booking> expected = new ArrayList<>();
        expected.add(booking1.get());
        expected.add(booking2.get());

        // what to expect?
        assertEquals(expected, client3.getBookings());
    }

    @Test
    void getClientServices() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);

        // what to test?
        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER);

        // what to expect
        assertEquals(List.of(EServices.DINNER), client1.getClientServices());
    }

    @Test
    void getClientServiceArithmetic() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        hotel1.addService(EServices.SPA);
        hotel1.addService(EServices.BREAKFAST);
        reservationSystem.addHotelToSystem(hotel1);

        // what to test?
        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        Optional<Booking> booking2 = reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER);
        Optional<Booking> booking3 = reservationSystem.bookServices(booking2.get(), hotel1, client1, EServices.SPA);
        reservationSystem.bookServices(booking3.get(), hotel1, client1, EServices.BREAKFAST);

        // what to expect
        assertEquals(33.3, client1.getClientServiceArithmetic());
        // Arithmetic = 30 + 50 + 20 / 3 = 33.3
    }

    @Test
    void updateBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);

        // what to test?
        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        // what to expect
        assertEquals(List.of(booking1.get()), client1.getBookings());

        // what to test?
        Optional<Booking> booking2 = reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER);

        // what to expect
        assertEquals(List.of(booking2.get()), client1.getBookings());
    }

    @Test
    @DisplayName("Should write a new review.")
    void WriteReview() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        // what to test?
        assertTrue(client1.writeReview("Lahe hotell", 5, hotel1));

        // what to expect
        assertEquals(1, client1.getReviews().size());
        assertEquals(5, client1.getReviews().getFirst().getScore());
        assertEquals("Lahe hotell", client1.getReviews().getFirst().getReview());

        assertEquals(1, hotel1.getHotelReviews().size());
        assertEquals("Lahe hotell", hotel1.getHotelReviews().get(client1).getReview());
        assertEquals(5, hotel1.getHotelReviews().get(client1).getScore());
    }

    @Test
    @DisplayName("Should not write a new review when client is not in hotel booking list.")
    void writeReview_clientNotInHotelList_reviewNotWritten() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);

        // what to test?
        assertFalse(client1.writeReview("lahe Hotell", 3, hotel1));

        // what to expect
        assertEquals(0, client1.getReviews().size());
        assertEquals(0, hotel1.getHotelReviews().size());
    }

    @Test
    @DisplayName("Should not write a new review when client has already wrote a review (to avoid spam).")
    void writeReview_clientAlreadyWroteAReview_reviewNotWritten() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        assertEquals(0, client1.getReviews().size());
        assertEquals(0, hotel1.getHotelReviews().size());

        // First written review should pass
        assertTrue(client1.writeReview("Lahe hotell", 5, hotel1));

        assertEquals(1, client1.getReviews().size());
        assertEquals(1, hotel1.getHotelReviews().size());

        // Second written review should not pass
        assertFalse(client1.writeReview("Cool bassein", 3, hotel1));

        // what to expect
        assertEquals(1, client1.getReviews().size());
        assertEquals(5, client1.getReviews().getFirst().getScore());
        assertEquals("Lahe hotell", client1.getReviews().getFirst().getReview());

        assertEquals(1, hotel1.getHotelReviews().size());
        assertEquals("Lahe hotell", hotel1.getHotelReviews().get(client1).getReview());
        assertEquals(5, hotel1.getHotelReviews().get(client1).getScore());
    }
    @Test
    @DisplayName("Should not write a new review when score is less than 1 or higher than 5.")
    void writeReview_clientGiveFalseScore_reviewNotWritten() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        // what to expect
        assertFalse(client1.writeReview("lahe Hotell", 6, hotel1));
        assertFalse(client1.writeReview("lahe Hotell", -2, hotel1));
        assertEquals(new ArrayList<>(), client1.getReviews());
        assertEquals(new HashMap<>(), hotel1.getHotelReviews());
    }

    @Test
    @DisplayName("Should not write a new review when review is empty.")
    void writeReview_reviewIsEmpty_reviewNotWritten() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        // what to expect
        assertFalse(client1.writeReview("", 3, hotel1));
        assertFalse(client1.writeReview("", 4, hotel1));
        assertEquals(new ArrayList<>(), client1.getReviews());
        assertEquals(new HashMap<>(), hotel1.getHotelReviews());
    }
}
