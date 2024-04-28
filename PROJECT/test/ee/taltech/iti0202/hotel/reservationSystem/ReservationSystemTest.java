package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.ECountryAndCitys;
import ee.taltech.iti0202.hotel.hotel.EServices;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.hotel.HotelBuilder;
import ee.taltech.iti0202.hotel.rooms.DeluxeRoom;
import ee.taltech.iti0202.hotel.rooms.DoubleRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ReservationSystemTest {

    private Room room1;
    private Room room2;
    private DeluxeRoom room3;
    private DeluxeRoom room4;
    private DoubleRoom room5;
    private DoubleRoom room6;
    private DoubleRoom room7;
    private DoubleRoom room8;
    private DoubleRoom room9;

    private Hotel hotel1;
    private Hotel hotel2;
    private Hotel hotel3;
    private Hotel hotel4;
    private Hotel hotel5;

    private ReservationSystem reservationSystem;
    private Client client1;
    private Client client2;
    private Client client3;
    private Client client4;
    private Client client5;
    private Client client6;

    @BeforeEach
    void setUp() {
        room1 = new Room();
        room2 = new Room();
        room3 = new DeluxeRoom();
        room4 = new DeluxeRoom();
        room5 = new DoubleRoom();
        room6 = new DoubleRoom();
        room7 = new DoubleRoom();
        room8 = new DoubleRoom();
        room9 = new DoubleRoom();

        hotel1 = new HotelBuilder()
                .setCountry(ECountryAndCitys.ESTONIA)
                .setCity("Tallinn").createHotel();

        hotel2 = new HotelBuilder()
                .setCountry(ECountryAndCitys.ITALY)
                .setCity("Rome").createHotel();

        hotel3 = new HotelBuilder()
                .setCountry(ECountryAndCitys.ESTONIA)
                .setCity("Pärnu").createHotel();

        hotel4 = new HotelBuilder()
                .setCountry(ECountryAndCitys.FRANCE)
                .setCity("Paris").createHotel();

        hotel5 = new HotelBuilder()
                .setCountry(ECountryAndCitys.ESTONIA)
                .setCity("Tallinn").createHotel();

        reservationSystem = new ReservationSystem();
        client1 = new Client("Mati", 10000);
        client2 = new Client("Kalle", 10000);
        client3 = new Client("Kati", 10000);
        client4 = new Client("Sass", 10000);
        client5 = new Client("Sass", 210);
        client6 = new Client("Sass", 250);
    }

    @Test
    @DisplayName("Should add hotel to reservationSystem.")
    void addHotelToSystem() {
        assertTrue(reservationSystem.addHotelToSystem(hotel1));
        assertTrue(reservationSystem.addHotelToSystem(hotel2));
    }

    @Test
    @DisplayName("Should not add hotel to ReservationSystem when hotel is already added.")
    void addHotelToSystem_HotelAlreadyInSystem_HotelNotAddedInReservationSystem() {
        // setup
        assertTrue(reservationSystem.addHotelToSystem(hotel1));
        assertFalse(reservationSystem.addHotelToSystem(hotel1));
    }

    @Test
    @DisplayName("Should not add hotel to ReservationSystem when hotel is null.")
    void addHotelToSystem_HotelIsNull_HotelNotAddedInReservationSystem() {
        // setup
        assertFalse(reservationSystem.addHotelToSystem(null));
    }

    @Test
    void getHotels() {
        assertTrue(reservationSystem.addHotelToSystem(hotel1));
        assertTrue(reservationSystem.addHotelToSystem(hotel2));

        assertEquals(Set.of(hotel1, hotel2),reservationSystem.getHotels());
    }

    @Test
    void lookUpHotel_ParametersAreCountryAndCity() {
        // setup
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.addHotelToSystem(hotel2);
        reservationSystem.addHotelToSystem(hotel3);
        reservationSystem.addHotelToSystem(hotel4);
        reservationSystem.addHotelToSystem(hotel5);

        // what to expect?
        assertEquals(Set.of(hotel1, hotel5),reservationSystem.lookUpHotel(ECountryAndCitys.ESTONIA, "Tallinn"));
        assertEquals(Set.of(hotel2),reservationSystem.lookUpHotel(ECountryAndCitys.ITALY, "Rome"));
    }

    @Test
    void lookUpHotel_ParameterIsCity() {
        // setup
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.addHotelToSystem(hotel2);
        reservationSystem.addHotelToSystem(hotel3);
        reservationSystem.addHotelToSystem(hotel4);
        reservationSystem.addHotelToSystem(hotel5);

        // what to expect?
        assertEquals(Set.of(hotel1, hotel5),reservationSystem.lookUpHotel("Tallinn"));
    }

    @Test
    void lookUpHotel_ParameterIsCountry() {
        // setup
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.addHotelToSystem(hotel2);
        reservationSystem.addHotelToSystem(hotel3);
        reservationSystem.addHotelToSystem(hotel4);
        reservationSystem.addHotelToSystem(hotel5);

        // what to expect?
        assertEquals(Set.of(hotel1, hotel3, hotel5),reservationSystem.lookUpHotel(ECountryAndCitys.ESTONIA));
    }

    @Test
    void lookUpHotel_ParameterIsScore() {
        // setup
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.addHotelToSystem(hotel2);
        reservationSystem.addHotelToSystem(hotel3);
        reservationSystem.addHotelToSystem(hotel4);
        reservationSystem.addHotelToSystem(hotel5);

        hotel1.addRoomToHotel(room1);
        hotel3.addRoomToHotel(room2);
        hotel3.addRoomToHotel(room3);
        hotel5.addRoomToHotel(room4);

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        reservationSystem.bookRoomInHotel(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel3, client2);
        reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel3, client3);
        reservationSystem.bookRoomInHotel(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel5, client4);

        client1.writeReview("Lahe hotell", 3, hotel1);
        client2.writeReview("Lahe hotell", 5, hotel3);
        client3.writeReview("Lahe hotell", 2, hotel3);
        client4.writeReview("Lahe hotell", 5, hotel5);

        // Hotels from best to worst (hotel5 (score 5) -> hotel3 (score 3.5) -> hotel1 (score 3) -> hotel2 (score null) and hotel4 (score null))

        // what to expect?
        assertEquals(Set.of(hotel5),reservationSystem.lookUpHotel(5));
        assertEquals(Set.of(hotel5),reservationSystem.lookUpHotel(4));
        assertEquals(Set.of(hotel5, hotel3, hotel1),reservationSystem.lookUpHotel(3));
    }

    @Test
    void lookUpHotel_ParameterAreCountryCityAndScore() {
        // setup
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.addHotelToSystem(hotel2);
        reservationSystem.addHotelToSystem(hotel3);
        reservationSystem.addHotelToSystem(hotel4);
        reservationSystem.addHotelToSystem(hotel5);

        hotel1.addRoomToHotel(room1);
        hotel3.addRoomToHotel(room2);
        hotel3.addRoomToHotel(room3);
        hotel5.addRoomToHotel(room4);

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        reservationSystem.bookRoomInHotel(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel3, client2);
        reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel3, client3);
        reservationSystem.bookRoomInHotel(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel5, client4);

        client1.writeReview("Lahe hotell", 3, hotel1); //Estonia Tallinn
        client2.writeReview("Lahe hotell", 5, hotel2); // Italy Rome
        client3.writeReview("Lahe hotell", 2, hotel3); // Estonia Parnu
        client4.writeReview("Lahe hotell", 5, hotel5); // Estonia Tallinn

        // Hotels from best to worst (hotel5 (score 5) -> hotel2 (score 5) -> hotel3 (score 2) -> hotel1 (score 3) ->  and hotel4 (score null))

        // what to expect?
        assertEquals(Set.of(hotel5),reservationSystem.lookUpHotel(ECountryAndCitys.ESTONIA, "Tallinn", 5));
        assertEquals(Set.of(),reservationSystem.lookUpHotel(ECountryAndCitys.FRANCE, "Paris", 3));
    }

    @Test
    void lookUpHotelRooms() {
        // setup
        reservationSystem.addHotelToSystem(hotel1);

        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);

        assertEquals(Set.of(room1, room2),reservationSystem.lookUpHotelRooms(hotel1, BigDecimal.valueOf(50), client1));
        assertEquals(Set.of(room1, room2, room3, room4, room5, room6), reservationSystem.lookUpHotelRooms(hotel1, BigDecimal.valueOf(200), client1));
    }

    @Test
    void lookUpHotelRooms_ClientGetDiscount() {
        // setup
        reservationSystem.addHotelToSystem(hotel1);

        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);

        assertEquals(Set.of(),reservationSystem.lookUpHotelRooms(hotel1, BigDecimal.valueOf(34), client2));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        reservationSystem.bookRoomInHotel(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client2);
        reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);

        client2.writeReview("Lahe hotell", 5, hotel1);

        assertEquals(Set.of(room1, room2),reservationSystem.lookUpHotelRooms(hotel1, BigDecimal.valueOf(34), client2));
    }

    @Test
    @DisplayName("Should book a room.")
    void BookRoom() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        reservationSystem.addHotelToSystem(hotel1);

        // What to test 1?
        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

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
        Optional<Booking> booking2 = reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);

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
    @DisplayName("Should not book room when room is not in a hotel")
    void BookRoom_roomNotInHotel_roomNotBooked() {
        reservationSystem.addHotelToSystem(hotel1);
        // what to test?
        Optional<Booking> booking = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        // what to expect?
        assertFalse(booking.isPresent());
    }

    @Test
    @DisplayName("Should not book room when hotel is not in a reservation System")
    void BookRoom_hotelNotInReservationSystem_roomNotBooked() {
        hotel1.addRoomToHotel(room1);
        // what to test?
        Optional<Booking> booking = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        // what to expect?
        assertFalse(booking.isPresent());
    }

    @Test
    @DisplayName("Should not book room when room is not available.")
    void BookRoom_roomNotAvailable_roomNotBooked() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        assertTrue(booking1.isPresent());

        // what to test?
        Optional<Booking> booking2 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        // same dates as previous booking

        // what to expect?
        assertFalse(booking2.isPresent());
        assertEquals(1, hotel1.getHotelBookings().size());
        assertTrue(hotel1.getHotelBookings().contains(booking1.get()));
        assertTrue(client3.getBookings().isEmpty());

        // what to test?
        Optional<Booking> booking3 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 15), LocalDate.of(2022, 4, 21), hotel1, client3);
        // not all dates are same

        // what to expect?
        assertFalse(booking3.isPresent());
    }

    @Test
    @DisplayName("Client balance should decrease as much as room cost.")
    void BookRoom_clientBalanceDecreases() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);

        // what to test?
        assertEquals(BigDecimal.valueOf(10000), client1.getBalance());
        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        // date range (6 days) * StandardRoom price (40) = 240

        // what to expect?
        assertEquals(BigDecimal.valueOf(9760.0), client1.getBalance());
    }

    @Test
    @DisplayName("Should not book room when not enough money.")
    void BookRoom_notEnoughMoney_roomNotBooked() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);

        assertEquals(BigDecimal.valueOf(210), client5.getBalance());

        // what to test?
        Optional<Booking> booking = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client5);
        // date range (6 days) * StandardRoom price (40) = 240

        // what to expect?
        assertFalse(booking.isPresent());
        assertEquals(BigDecimal.valueOf(210), client5.getBalance());
    }

    @Test
    @DisplayName("Should book room and Client should get Discount.")
    void BookRoom_ClientIsTop_roomIsBookedAndClientGetsDiscount() {
        // setup
        reservationSystem.addHotelToSystem(hotel1);

        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);

        assertEquals(BigDecimal.valueOf(10000), client1.getBalance());

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        reservationSystem.bookRoomInHotel(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client2);
        reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);

        assertEquals(BigDecimal.valueOf(9760.0), client1.getBalance());
        // room 1 cost 40 * 6 days = 240 -> (10000 - 240 = 9760.0)

        client1.writeReview("Lahe hotell", 5, hotel1);

        reservationSystem.bookRoomInHotel(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        BigDecimal expectedBalance = BigDecimal.valueOf(8995.00);
        assertEquals(0, client1.getBalance().compareTo(expectedBalance));
        // room 4 cost 150 * 6 days * 0.85(discount) = 765 -> (9760.0 - 765 = 8995.0)
    }

    @Test
    @DisplayName("Should remove a booking.")
    void removeBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        assertTrue(booking1.isPresent());
        assertEquals(1, hotel1.getHotelBookings().size());
        assertTrue(hotel1.getHotelBookings().contains(booking1.get()));
        assertTrue(client1.getBookings().contains(booking1.get()));
        assertTrue(hotel1.getHotelClients().contains(client1));

        // what to test?
        reservationSystem.removeBookingInHotel(booking1.get(), hotel1, client1);

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
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client5);
        assertEquals(0, hotel1.getHotelBookings().size());

        // what to test and expect?
        assertThrows(NoSuchElementException.class, () -> reservationSystem.removeBookingInHotel(booking1.get(), hotel1, client2));
    }

    @Test
    @DisplayName("Should not remove a booking when booking does not exist in client list.")
    void removeBooking_bookingDoesNotExistInClientList_bookingNotRemoved() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        assertEquals(1, hotel1.getHotelBookings().size());

        // what to test and expect?
        assertFalse(reservationSystem.removeBookingInHotel(booking1.get(), hotel1, client2));
    }

    @Test
    @DisplayName("Should get money back after removing booking.")
    void removeBooking_afterRemoveBooking_getMoneyBack() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        reservationSystem.addHotelToSystem(hotel1);

        assertEquals(BigDecimal.valueOf(10000), client1.getBalance());
        assertEquals(BigDecimal.valueOf(10000), client3.getBalance());
        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        // date range (6 days) * StandardRoom price (40) = 240

        Optional<Booking> booking2 = reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        // date range (6 days) * StandardRoom price (150) = 900

        assertTrue(booking1.isPresent());
        assertTrue(booking2.isPresent());
        assertEquals(BigDecimal.valueOf(9760.0), client1.getBalance());
        assertEquals(BigDecimal.valueOf(9100.0), client3.getBalance());

        // what to test?
        reservationSystem.removeBookingInHotel(booking1.get(), hotel1, client1);
        reservationSystem.removeBookingInHotel(booking2.get(), hotel1, client3);

        // what to expect
        assertEquals(BigDecimal.valueOf(9880.0), client1.getBalance());
        assertEquals(BigDecimal.valueOf(9550.0), client3.getBalance());
    }

    @Test
    @DisplayName("Should decrease client booking number in hotelClientBookings map when booking is removed.")
    void removeBooking_clientHaveMoreThan1Booking_decreasesHerBookingBy1() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        Optional<Booking> booking2 = reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        Map<Client, Integer> expected1 = new HashMap<>();
        expected1.put(client3, 2);
        Map<Client, Integer> expeceted2 = new HashMap<>();
        expeceted2.put(client3, 1);

        assertTrue(booking1.isPresent());
        assertTrue(booking2.isPresent());
        assertEquals(expected1, hotel1.getHotelClientBookings());

        // what to test?
        reservationSystem.removeBookingInHotel(booking1.get(), hotel1, client3);

        // what to expect?
        assertEquals(expeceted2, hotel1.getHotelClientBookings());

        reservationSystem.removeBookingInHotel(booking2.get(), hotel1, client3);
        assertTrue(hotel1.getHotelClientBookings().isEmpty());
    }

    @Test
    @DisplayName("Should remove client with 1 booking in hotelClientBookings map when booking is removed.")
    void removeBooking_clientHave1Booking_removeClientFromHotelClientBookingMap() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room3);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        Map<Client, Integer> expeceted1 = new HashMap<>();
        expeceted1.put(client3, 1);

        assertTrue(booking1.isPresent());
        assertEquals(expeceted1, hotel1.getHotelClientBookings());

        // what to test?
        reservationSystem.removeBookingInHotel(booking1.get(), hotel1, client3);

        // what to expect?
        assertTrue(hotel1.getHotelClientBookings().isEmpty());
    }

    @Test
    void bookServices() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        assertTrue(reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER));
    }

    @Test
    void bookServices_HotelDoesNotHaveBooking() {
        // setup
        hotel2.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.addHotelToSystem(hotel2);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel2, client1);

        assertFalse(reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER));
    }

    @Test
    void bookServices_ClientDoesNotHaveBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        assertFalse(reservationSystem.bookServices(booking1.get(), hotel1, client2, EServices.DINNER));
    }

    @Test
    void bookServices_ServiceDoesNotExist() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        assertFalse(reservationSystem.bookServices(booking1.get(), hotel1, client2, null));
    }

    @Test
    void bookServices_ServiceClientDoesNotHaveEnoughMoney() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client6);

        assertFalse(reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.SPA));
    }

    @Test
    void removeServices() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        assertTrue(reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER));

        assertTrue(reservationSystem.removeService(booking1.get(), hotel1, client1, EServices.DINNER));
    }

    @Test
    void removeServices_HotelDoesNotHaveBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.addHotelToSystem(hotel2);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        assertTrue(reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER));

        assertFalse(reservationSystem.removeService(booking1.get(), hotel2, client1, EServices.DINNER));
    }

    @Test
    void removeServices_ClientDoesNotHaveBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        assertTrue(reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER));

        assertFalse(reservationSystem.removeService(booking1.get(), hotel2, client2, EServices.DINNER));
    }

    @Test
    void removeServices_ServiceDoesNotExist() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addService(EServices.DINNER);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        assertTrue(reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER));

        assertFalse(reservationSystem.removeService(booking1.get(), hotel2, client1, null));
    }
}