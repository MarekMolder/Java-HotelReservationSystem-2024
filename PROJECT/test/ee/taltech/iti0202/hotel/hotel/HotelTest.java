package ee.taltech.iti0202.hotel.hotel;


import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.rooms.DeluxeRoom;
import ee.taltech.iti0202.hotel.rooms.DoubleRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {
Room room1 = new Room();
    Room room2 = new Room();
    Room room3 = new Room();
    Room room4 = new Room();
    Room room5 = new DoubleRoom();
    Room room6 = new DoubleRoom();
    Room room7 = new DoubleRoom();
    Room room8 = new DoubleRoom();
    Room room9 = new DeluxeRoom();
    Room room10 = new DeluxeRoom();
    Room room11 = new DeluxeRoom();
    Room room12 = new DeluxeRoom();
    Hotel hotel1 = new Hotel();
    Hotel hotel2 = new Hotel();
    Client client1 = new Client("Mati", 1000000);
    Client client2 = new Client("Kati", 1000000);
    Client client3 = new Client("Jüri", 1000000);
    Client client4 = new Client("Tõnu", 1000000);
    Client client5 = new Client("Laura", 1000000);
    Client client6 = new Client("Kalle", 10000000);

    @Test
    public void testHotelAddRoom() {
        assertTrue(hotel1.addRoomToHotel(room1));
        assertTrue(hotel1.addRoomToHotel(room2));
        assertTrue(hotel1.addRoomToHotel(room3));
        assertTrue(hotel1.addRoomToHotel(room4));
    }

    @Test
    public void testHotelAddRoomWhichIsInOtherHotel() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);

        assertFalse(hotel2.addRoomToHotel(room1));
        assertFalse(hotel2.addRoomToHotel(room2));
        assertFalse(hotel2.addRoomToHotel(room3));
        assertFalse(hotel2.addRoomToHotel(room4));
    }

    @Test
    public void testHotelGetRooms() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);

        Set<Room> rooms = new HashSet<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);

        assertEquals(rooms, hotel1.getRooms());
    }

    @Test
    public void testHotelGetClients() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client4.bookRoom(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        Set<Client> clients = new HashSet<>();
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);

        assertEquals(clients, hotel1.getClients());
    }

    @Test
    public void testHotelGetHotelReviews() {
        hotel1.addRoomToHotel(room1);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        client1.writeReview("Lahe hotell", 5, hotel1);

        Map<Client, List<Object>> Hotelexpected = new HashMap<>();
        Hotelexpected.put(client1, new ArrayList<>(Arrays.asList("Lahe hotell", 5)));

        assertEquals(Hotelexpected, hotel1.getHotelReviews());
    }

    @Test
    public void testHotelGetReviewsScore() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client4.bookRoom(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        client1.writeReview("Lahe hotell", 5, hotel1);
        client2.writeReview("Cool tuba", 4, hotel1);
        client3.writeReview("hea söök", 3, hotel1);
        client4.writeReview("mõnus voodi", 2, hotel1);

        assertEquals(3.5 , hotel1.getReviewsArithmeticScore());
    }

    @Test
    public void testHotelGetReviewsScoreWhenNoReviews() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client4.bookRoom(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        assertEquals(0.0 , hotel1.getReviewsArithmeticScore());
    }

    @Test
    public void testHotelGetReviewsScoreWhenOneWrongReview() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        client4.bookRoom(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        client1.writeReview("Lahe hotell", 5, hotel1);
        client2.writeReview("Cool tuba", 4, hotel1);
        client3.writeReview("hea söök", 3, hotel1);
        client4.writeReview("mõnus voodi", 6, hotel1);

        assertEquals(4 , hotel1.getReviewsArithmeticScore());
    }

    @Test
    public void testHotelGetBookings() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking2 = client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking3 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking4 = client4.bookRoom(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);;

        Set<Booking> bookings = new HashSet<>();
        bookings.add(booking1.get());
        bookings.add(booking2.get());
        bookings.add(booking3.get());
        bookings.add(booking4.get());

        assertEquals(bookings , hotel1.getBooking());
    }

    @Test
    public void testHotelLookUpFreeRoomsType() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);
        hotel1.addRoomToHotel(room7);
        hotel1.addRoomToHotel(room8);

        Set<Room> actual = hotel1.LookUpFreeRoomsType(DoubleRoom.class, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17));
        Set<Room> expected = new LinkedHashSet<>();
        expected.add(room5);
        expected.add(room6);
        expected.add(room7);
        expected.add(room8);

        assertEquals(expected, actual);

        Optional<Booking> booking1 = client1.bookRoom(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking2 = client2.bookRoom(room6, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        Set<Room> actual1 = hotel1.LookUpFreeRoomsType(DoubleRoom.class, LocalDate.of(2022, 4, 15), LocalDate.of(2022, 4, 16));
        Set<Room> expected1 = new LinkedHashSet<>();
        expected1.add(room7);
        expected1.add(room8);

        assertEquals(expected1, actual1);
    }

    @Test
    public void testHotelLookUpFreeRoomDate() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);
        hotel1.addRoomToHotel(room7);
        hotel1.addRoomToHotel(room8);

        Set<Room> actual = hotel1.LookUpFreeRoomDate(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17));
        Set<Room> expected = new LinkedHashSet<>();
        expected.add(room1);
        expected.add(room2);
        expected.add(room5);
        expected.add(room6);
        expected.add(room7);
        expected.add(room8);

        assertEquals(expected, actual);

        Optional<Booking> booking1 = client1.bookRoom(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking2 = client2.bookRoom(room6, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        Set<Room> actual1 = hotel1.LookUpFreeRoomsType(DoubleRoom.class, LocalDate.of(2022, 4, 15), LocalDate.of(2022, 4, 16));
        Set<Room> expected1 = new LinkedHashSet<>();
        expected.add(room1);
        expected.add(room2);
        expected1.add(room7);
        expected1.add(room8);

        assertEquals(expected1, actual1);
    }

    @Test
    public void testHotelIsRoomAvailable() {
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);

        assertTrue(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room5));
        assertTrue(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room6));

        Optional<Booking> booking1 = client1.bookRoom(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        assertFalse(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room5));
        assertTrue(hotel1.isRoomAvailable(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), room6));
    }

    @Test
    public void testHotelGetDatesInRange() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking2 = client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 13), hotel1);
        Optional<Booking> booking3 = client3.bookRoom(room3, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        Optional<Booking> booking4 = client1.bookRoom(room1, LocalDate.of(2024, 3, 29), LocalDate.of(2024, 4, 2), hotel1);

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


        assertEquals(book1, booking1.get().getDatesInRange(booking1.get().getSince(), booking1.get().getUntil()));
        assertEquals(book2, booking2.get().getDatesInRange(booking2.get().getSince(), booking2.get().getUntil()));
        assertEquals(book3, booking3.get().getDatesInRange(booking3.get().getSince(), booking3.get().getUntil()));
        assertEquals(book4, booking4.get().getDatesInRange(booking4.get().getSince(), booking4.get().getUntil()));
    }

    @Test
    public void testHotelSortClients() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);
        hotel1.addRoomToHotel(room7);
        hotel1.addRoomToHotel(room8);
        hotel1.addRoomToHotel(room9);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking2 = client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 13), hotel1);
        Optional<Booking> booking3 = client3.bookRoom(room3, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        Optional<Booking> booking4 = client2.bookRoom(room4, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking5 = client5.bookRoom(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 13), hotel1);
        Optional<Booking> booking6 = client3.bookRoom(room6, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        Optional<Booking> booking7 = client1.bookRoom(room7, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        Optional<Booking> booking8 = client3.bookRoom(room8, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        Optional<Booking> booking9 = client3.bookRoom(room9, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);

        client1.writeReview("Lahe hotell", 5, hotel1);
        client2.writeReview("Lahe hotell", 3, hotel1);
        client3.writeReview("Lahe hotell", 1, hotel1);
        client5.writeReview("Lahe hotell", 4, hotel1);

        List<Client> sortedClients = new LinkedList<>(Arrays.asList(client3, client1, client2, client5));
        List<Client> sort = hotel1.sortClients();

        assertEquals(sortedClients, sort);

    }


}

