package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.DeluxeRoom;
import ee.taltech.iti0202.hotel.rooms.DoubleRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    Room room1 = new Room();
    Room room2 = new DoubleRoom();
    Room room3 = new DeluxeRoom();
    Hotel hotel = new Hotel();
    Client client1 = new Client("Mati", 200);
    Client client2 = new Client("Mati", 0);
    Client client3 = new Client("Mati", 1000);

    Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
    Optional<Booking> booking2 = client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 13), hotel);
    Optional<Booking> booking3 = client3.bookRoom(room3, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel);
    Optional<Booking> booking4 = client1.bookRoom(room1, LocalDate.of(2024, 3, 29), LocalDate.of(2024, 4, 2), hotel);


    @Test
    public void testClientWriteReview() {
        hotel.addRoomToHotel(room1);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        client1.writeReview("Lahe hotell", 5, hotel);

        Map<String, Integer> Clientexpected = new HashMap<>();
        Clientexpected.put("Lahe hotell", 5);

        Map<Client, List<Object>> Hotelexpected = new HashMap<>();
        Hotelexpected.put(client1, new ArrayList<>(Arrays.asList("Lahe hotell", 5)));

        assertEquals(Clientexpected, client1.getReviews());
        assertEquals(Hotelexpected, hotel.getHotelReviews());
    }

    @Test
    public void testClientWriteDoubleReview() {
        hotel.addRoomToHotel(room1);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        assertTrue(client1.writeReview("Lahe hotell", 5, hotel));

        Map<String, Integer> Clientexpected1 = new HashMap<>();
        Clientexpected1.put("Lahe hotell", 5);
        Map<Client, List<Object>> Hotelexpected1 = new HashMap<>();
        Hotelexpected1.put(client1, new ArrayList<>(Arrays.asList("Lahe hotell", 5)));

        assertEquals(Clientexpected1, client1.getReviews());
        assertEquals(Hotelexpected1, hotel.getHotelReviews());

        assertTrue(client1.writeReview("Cool bassein", 3, hotel));

        Map<String, Integer> Clientexpected2 = new HashMap<>();
        Clientexpected2.put("Lahe hotell", 5);
        Clientexpected2.put("Cool bassein", 3);

        Map<Client, List<Object>> Hotelexpected2 = new HashMap<>();
        Hotelexpected2.put(client1, new ArrayList<>(Arrays.asList("Lahe hotell", 5)));
        Hotelexpected2.put(client1, new ArrayList<>(Arrays.asList("Cool bassein", 3)));

        assertEquals(Clientexpected2, client1.getReviews());
        assertEquals(Hotelexpected2, hotel.getHotelReviews());
    }
    @Test
    public void testClientWriteReviewWithScore6() {
        hotel.addRoomToHotel(room1);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        assertFalse(client1.writeReview("lahe Hotell", 6, hotel));
    }

    @Test
    public void testClientWriteReviewWhenNotInBookingList() {
        hotel.addRoomToHotel(room1);

        assertFalse(client1.writeReview("lahe Hotell", 3, hotel));
    }

    @Test
    public void testClientBookRoom() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room3);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        assertTrue(booking1.isPresent());
        assertTrue(booking2.isPresent());

        assertEquals(2, hotel.hotelBookings.size());

        assertTrue(hotel.hotelBookings.contains(booking1.get()));
        assertTrue(hotel.hotelBookings.contains(booking2.get()));

        assertTrue(client1.getBookings().contains(booking1.get()));
        assertTrue(client3.getBookings().contains(booking2.get()));

        assertTrue(hotel.hotelClients.contains(client1));
        assertTrue(hotel.hotelClients.contains(client3));
    }

    @Test
    public void testClientBookRoomBookingNumberInHotelListIncreases() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room3);

        Optional<Booking> booking1 = client3.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        assertTrue(booking1.isPresent());
        assertTrue(booking2.isPresent());

        Map<Client, Integer> clientBooking = new HashMap<>();
        clientBooking.put(client3, 2);

        assertEquals(clientBooking, hotel.hotelClientBooking);
    }

    @Test
    public void testClientBookRoomCheckIfHotelTakesMoney() {
        hotel.addRoomToHotel(room1);

        assertEquals(200, client1.getMoney());

        Optional<Booking> booking = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        assertEquals(100, client1.getMoney());
    }

    @Test
    public void testClientBookRoomButRoomNotAvailable() {
        hotel.addRoomToHotel(room1);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        assertTrue(booking1.isPresent());

        Optional<Booking> booking2 = client3.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        assertFalse(booking2.isPresent());

        Optional<Booking> booking3 = client3.bookRoom(room1, LocalDate.of(2022, 4, 15), LocalDate.of(2022, 4, 16), hotel);
        assertFalse(booking3.isPresent());
    }

    @Test
    public void testClientBookRoomNotEnoughMoney() {
        hotel.addRoomToHotel(room1);

        assertEquals(0, client2.getMoney());

        Optional<Booking> booking = client2.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        assertFalse(booking.isPresent());
    }

    @Test
    public void testClientBookButRoomDoesntExistInThisHotel() {
        Optional<Booking> booking = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        assertFalse(booking.isPresent());
    }

    @Test
    public void testClientRemoveBooking() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room3);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        assertTrue(booking1.isPresent());
        assertTrue(booking2.isPresent());

        assertEquals(2, hotel.hotelBookings.size());

        assertTrue(hotel.hotelBookings.contains(booking1.get()));
        assertTrue(hotel.hotelBookings.contains(booking2.get()));

        assertTrue(client1.getBookings().contains(booking1.get()));
        assertTrue(client3.getBookings().contains(booking2.get()));

        assertTrue(hotel.hotelClients.contains(client1));
        assertTrue(hotel.hotelClients.contains(client3));

        client1.removeBooking(booking1.get(), hotel);
        client3.removeBooking(booking2.get(), hotel);

        assertEquals(0, hotel.hotelBookings.size());
        assertEquals(0, client1.getBookings().size());
        assertEquals(0, client2.getBookings().size());

        assertFalse(hotel.hotelClients.contains(client1));
        assertFalse(hotel.hotelClients.contains(client3));
    }

    @Test
    public void testClientGetMoneyBackAfterRemovingBooking() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room3);

        assertEquals(200, client1.getMoney());
        assertEquals(1000, client3.getMoney());

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        assertTrue(booking1.isPresent());
        assertTrue(booking2.isPresent());

        assertEquals(100, client1.getMoney());
        assertEquals(700, client3.getMoney());

        client1.removeBooking(booking1.get(), hotel);
        client3.removeBooking(booking2.get(), hotel);

        assertEquals(200, client1.getMoney());
        assertEquals(1000, client3.getMoney());
    }

    @Test
    public void testClientRemoveBookingNumberInHotelListDecreases() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room3);

        Optional<Booking> booking1 = client3.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        assertTrue(booking1.isPresent());
        assertTrue(booking2.isPresent());

        Map<Client, Integer> clientBooking1 = new HashMap<>();
        clientBooking1.put(client3, 2);
        assertEquals(clientBooking1, hotel.hotelClientBooking);

        client3.removeBooking(booking1.get(), hotel);

        Map<Client, Integer> clientBooking2 = new HashMap<>();
        clientBooking2.put(client3, 1);
        assertEquals(clientBooking2, hotel.hotelClientBooking);

        client3.removeBooking(booking2.get(), hotel);
        assertTrue(hotel.hotelClientBooking.isEmpty());
    }

    @Test
    public void testClientRemoveBookingButBookingDoesntExist() {
        Hotel hotel2 = new Hotel();
        hotel2.addRoomToHotel(room1);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel2);

        assertFalse(client1.removeBooking(booking1.get(), hotel));
    }

    @Test
    public void testClientGetReviews() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room3);

        client3.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        client3.writeReview("Lahe hotell", 5, hotel);
        client3.writeReview("Cool tuba oli", 5, hotel);

        Map<String, Integer> Clientexpected = new HashMap<>();
        Clientexpected.put("Lahe hotell", 5);
        Clientexpected.put("Cool tuba oli", 5);

        assertEquals(Clientexpected, client3.getReviews());
    }

    @Test
    public void testClientGetBookings() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room3);

        Optional<Booking> booking1 = client3.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client3.bookRoom(room3, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);

        List<Booking> clientexpected = new ArrayList<>();
        clientexpected.add(booking1.get());
        clientexpected.add(booking2.get());

        assertEquals(clientexpected, client3.getBookings());
    }

    @Test
    public void testClientGetMoney() {
        assertEquals(200, client1.getMoney());
        assertEquals(0, client2.getMoney());
        assertEquals(1000, client3.getMoney());
    }
}
