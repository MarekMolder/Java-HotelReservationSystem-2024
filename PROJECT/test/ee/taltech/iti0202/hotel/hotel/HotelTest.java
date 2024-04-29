package ee.taltech.iti0202.hotel.hotel;


import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.reservationSystem.ReservationSystem;
import ee.taltech.iti0202.hotel.rooms.DoubleRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    private DoubleRoom room5;
    private DoubleRoom room6;
    private DoubleRoom room7;
    private DoubleRoom room8;
    private DoubleRoom room9;

    private Hotel hotel1;
    private Hotel hotel2;

    private ReservationSystem reservationSystem;
    private Client client1;
    private Client client2;
    private Client client3;
    private Client client4;

    @BeforeEach
    void setUp() {
        room1 = new Room();
        room2 = new Room();
        room3 = new Room();
        room4 = new Room();
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

        reservationSystem = new ReservationSystem();
        client1 = new Client("Mati", 10000);
        client2 = new Client("Kalle", 10000);
        client3 = new Client("Kati", 10000);
        client4 = new Client("Sass", 10000);
    }
    @Test
    @DisplayName("Should add room to hotel.")
    void addRoomToHotel() {
        assertTrue(hotel1.addRoomToHotel(room1));
        assertTrue(hotel1.addRoomToHotel(room2));
    }

    @Test
    @DisplayName("Should not add room to hotel when room is in another hotel.")
    void addRoom_roomInOtherHotel_roomNotAddedInHotel() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);

        // what to expect?
        assertFalse(hotel2.addRoomToHotel(room1));
        assertFalse(hotel2.addRoomToHotel(room2));
    }

    @Test
    void isRoomAvailableLocalDate() {
        // setup
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);
        reservationSystem.addHotelToSystem(hotel1);

        // what to expect?
        assertTrue(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room5));
        assertTrue(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room6));

        // new setup
        reservationSystem.bookRoomInHotel(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);

        // what to expect?
        assertFalse(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room5));
        assertTrue(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room6));
    }

    @Test
    void isRoomAvailableDayOfWeek() {
        // setup
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);
        reservationSystem.addHotelToSystem(hotel1);

        // what to expect?
        assertTrue(hotel1.isRoomAvailable(DayOfWeek.FRIDAY, room5));
        assertTrue(hotel1.isRoomAvailable(DayOfWeek.MONDAY, room6));

        // new setup
        reservationSystem.bookRoomInHotel(room5, LocalDate.of(2024, 4, 26), LocalDate.of(2024, 4, 26), hotel1, client1);

        // what to expect?
        assertFalse(hotel1.isRoomAvailable(DayOfWeek.FRIDAY, room5));
        assertTrue(hotel1.isRoomAvailable(DayOfWeek.MONDAY, room6));
    }

    @Test
    void sortClients() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);
        hotel1.addRoomToHotel(room7);
        hotel1.addRoomToHotel(room8);
        hotel1.addRoomToHotel(room9);
        reservationSystem.addHotelToSystem(hotel1);

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        reservationSystem.bookRoomInHotel(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client2);
        reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        reservationSystem.bookRoomInHotel(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client2);
        reservationSystem.bookRoomInHotel(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client4);
        reservationSystem.bookRoomInHotel(room6, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        reservationSystem.bookRoomInHotel(room7, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        reservationSystem.bookRoomInHotel(room8, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        reservationSystem.bookRoomInHotel(room9, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        client1.writeReview("Lahe hotell", 3, hotel1);
        client2.writeReview("Lahe hotell", 5, hotel1);
        client3.writeReview("Lahe hotell", 1, hotel1);
        client4.writeReview("Lahe hotell", 4, hotel1);

        // what to expect?
        List<Client> sortedClients = new LinkedList<>(Arrays.asList(client3, client2, client1, client4));
        //client3 is first because she has most bookings
        //client2 is second because he gave better score than client1
        //client1 is third because he has more bookings than client4
        List<Client> sort = hotel1.sortClients();
        assertEquals(sortedClients, sort);
    }

    @Test
    void sortClients_AndThirdSort() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);
        hotel1.addRoomToHotel(room7);
        hotel1.addRoomToHotel(room8);
        hotel1.addRoomToHotel(room9);
        hotel1.addService(EServices.DINNER);
        hotel1.addService(EServices.SPA);
        hotel1.addService(EServices.BREAKFAST);
        reservationSystem.addHotelToSystem(hotel1);

        Optional<Booking> booking1 = reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        Optional<Booking> booking2 = reservationSystem.bookRoomInHotel(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client2);
        reservationSystem.bookRoomInHotel(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        Optional<Booking> booking3 = reservationSystem.bookRoomInHotel(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client2);
        reservationSystem.bookRoomInHotel(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client4);
        reservationSystem.bookRoomInHotel(room6, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        Optional<Booking> booking4 = reservationSystem.bookRoomInHotel(room7, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        reservationSystem.bookRoomInHotel(room8, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);
        reservationSystem.bookRoomInHotel(room9, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client3);

        client1.writeReview("Lahe hotell", 5, hotel1);
        client2.writeReview("Lahe hotell", 5, hotel1);
        client3.writeReview("Lahe hotell", 1, hotel1);
        client4.writeReview("Lahe hotell", 4, hotel1);

        reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.BREAKFAST);
        reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER);
        reservationSystem.bookServices(booking4.get(), hotel1, client1, EServices.BREAKFAST);
        reservationSystem.bookServices(booking4.get(), hotel1, client1, EServices.DINNER);
        reservationSystem.bookServices(booking3.get(), hotel1, client2, EServices.SPA);
        reservationSystem.bookServices(booking2.get(), hotel1, client2, EServices.SPA);
        reservationSystem.bookServices(booking2.get(), hotel1, client2, EServices.DINNER);

        // what to expect?
        List<Client> sortedClients = new LinkedList<>(Arrays.asList(client3, client2, client1, client4));
        //client3 is first because she has most bookings
        //client2 is second because he has services 50 + 50 + 30 / 3 = 43.3 > client1 services 20 + 30 + 20 + 30 / 4 = 25
        //client1 is third because he has more bookings than client4

        List<Client> sort = hotel1.sortClients();
        assertEquals(sortedClients, sort); //KAHTLANE!!!
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
        assertEquals(List.of(booking1.get()), hotel1.getHotelBookings());

        // what to test?
        Optional<Booking> booking2 = reservationSystem.bookServices(booking1.get(), hotel1, client1, EServices.DINNER);

        // what to expect
        assertEquals(List.of(booking2.get()), hotel1.getHotelBookings());
    }
}
