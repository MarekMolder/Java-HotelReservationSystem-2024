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
    Client client3 = new Client("Mati", 0);

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

        assertFalse(client1.writeReview("Cool bassein", 3, hotel));

        Map<String, Integer> Clientexpected2 = new HashMap<>();
        Clientexpected2.put("Lahe hotell", 5);
        Map<Client, List<Object>> Hotelexpected2 = new HashMap<>();
        Hotelexpected2.put(client1, new ArrayList<>(Arrays.asList("Lahe hotell", 5)));

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

}
