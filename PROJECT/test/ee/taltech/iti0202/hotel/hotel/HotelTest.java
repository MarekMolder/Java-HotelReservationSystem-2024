package ee.taltech.iti0202.hotel.hotel;


import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.rooms.DoubleRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        hotel1 = new Hotel();
        hotel2 = new Hotel();
        client1 = new Client("Mati", 10000);
        client2 = new Client("Kalle", 10000);
        client3 = new Client("Kati", 10000);
        client4 = new Client("Sass", 10000);
    }

    @Test
    void getHotelRooms() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        Set<Room> expected = new HashSet<>();
        expected.add(room1);
        expected.add(room2);

        // what to test and expect?
        assertEquals(expected, hotel1.getHotelRooms());
    }

    @Test
    void getHotelClients() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Set<Client> expected = new HashSet<>();
        expected.add(client1);
        expected.add(client2);

        // what to test and expect?
        assertEquals(expected, hotel1.getHotelClients());
    }

    @Test
    void getHotelClientBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client1.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client1.bookRoom(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Map<Client, Integer> expected = new HashMap<>();
        expected.put(client1, 3);
        expected.put(client2, 1);

        // what to test and expect?
        assertEquals(expected, hotel1.getHotelClientBookings());
    }

    @Test
    void getHotelReviews() {
        // setup
        hotel1.addRoomToHotel(room1);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to test?
        client1.writeReview("Lahe hotell", 5, hotel1);

        // what to expect?
        assertEquals(1, hotel1.getHotelReviews().size());
        assertEquals("Lahe hotell", hotel1.getHotelReviews().get(client1).getReview());
        assertEquals(5, hotel1.getHotelReviews().get(client1).getScore());
    }

    @Test
    void getHotelBookings() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking2 = client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Set<Booking> expected = new HashSet<>();
        expected.add(booking1.get());
        expected.add(booking2.get());

        // what to expect?
        assertEquals(expected, hotel1.getHotelBookings());
    }

    @Test
    @DisplayName("Should add room to hotel.")
    void addRoom() {
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
    void getReviewsArithmeticScore() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client4.bookRoom(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to test?
        client1.writeReview("Lahe hotell", 5, hotel1);
        client2.writeReview("Cool tuba", 5, hotel1);
        client3.writeReview("hea söök", 4, hotel1);
        client4.writeReview("mõnus voodi", 6, hotel1);
        // 5 + 5 + 4 = 14 / 3 = 4.666666666666667 (6 not included because false score)

        // what to expect?
        assertEquals(4.666666666666667 , hotel1.getReviewsArithmeticScore());
    }

    @Test
    void getReviewsArithmeticScore_thereIsNoReviews_ArithmeticScoreIs0() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to expect
        assertEquals(0.0 , hotel1.getReviewsArithmeticScore());
    }

    @Test
    void isRoomAvailable() {
        // setup
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);

        // what to expect?
        assertTrue(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room5));
        assertTrue(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room6));

        // new setup
        client1.bookRoom(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to expect?
        assertFalse(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room5));
        assertTrue(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room6));
    }

    @Test
    void getDatesInRange() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);

        List<LocalDate> book1 = new ArrayList<>();
        book1.add(LocalDate.of(2022, 4, 12));
        book1.add(LocalDate.of(2022, 4, 13));
        book1.add(LocalDate.of(2022, 4, 14));
        book1.add(LocalDate.of(2022, 4, 15));
        book1.add(LocalDate.of(2022, 4, 16));
        book1.add(LocalDate.of(2022, 4, 17));

        List<LocalDate> book2 = new ArrayList<>();
        book2.add(LocalDate.of(2022, 4, 12));
        book2.add(LocalDate.of(2022, 4, 13));

        List<LocalDate> book3 = new ArrayList<>();
        book3.add(LocalDate.of(2023, 4, 12));
        book3.add(LocalDate.of(2023, 4, 13));
        book3.add(LocalDate.of(2023, 4, 14));

        List<LocalDate> book4 = new ArrayList<>();
        book4.add(LocalDate.of(2024, 3, 29));
        book4.add(LocalDate.of(2024, 3, 30));
        book4.add(LocalDate.of(2024, 3, 31));
        book4.add(LocalDate.of(2024, 4, 1));
        book4.add(LocalDate.of(2024, 4, 2));


        // what to test?
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking2 = client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 13), hotel1);
        Optional<Booking> booking3 = client3.bookRoom(room3, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        Optional<Booking> booking4 = client1.bookRoom(room1, LocalDate.of(2024, 3, 29), LocalDate.of(2024, 4, 2), hotel1);

        // what to expect?
        assertEquals(book1, booking1.get().getDatesInRange(booking1.get().getSince(), booking1.get().getUntil()));
        assertEquals(book2, booking2.get().getDatesInRange(booking2.get().getSince(), booking2.get().getUntil()));
        assertEquals(book3, booking3.get().getDatesInRange(booking3.get().getSince(), booking3.get().getUntil()));
        assertEquals(book4, booking4.get().getDatesInRange(booking4.get().getSince(), booking4.get().getUntil()));
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
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 13), hotel1);
        client3.bookRoom(room3, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        client2.bookRoom(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client4.bookRoom(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 13), hotel1);
        client3.bookRoom(room6, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        client1.bookRoom(room7, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        client3.bookRoom(room8, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        client3.bookRoom(room9, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        client1.writeReview("Lahe hotell", 3, hotel1);
        client2.writeReview("Lahe hotell", 5, hotel1);
        client3.writeReview("Lahe hotell", 1, hotel1);
        client4.writeReview("Lahe hotell", 4, hotel1);

        // what to expect?
        List<Client> sortedClients = new LinkedList<>(Arrays.asList(client3, client2, client1, client4));
        //client3 is first because most bookings
        //client2 is second because better score than client1
        //client 1 is third because have more bookings than client4
        List<Client> sort = hotel1.sortClients();
        assertEquals(sortedClients, sort);
    }
}
