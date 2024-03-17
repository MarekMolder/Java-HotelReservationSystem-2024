package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ClientTest {
    Room room1 = new Room();
    Room room2 = new Room();
    Room room3 = new Room();
    Hotel hotel = new Hotel();
    Client client1 = new Client("Mati", 200);
    Client client2 = new Client("Mati", 0);
    @Test
    public void testClientRemoveBooking() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room2);

        Optional<Booking> firstBooking = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), hotel);
        Optional<Booking> secondBooking = client1.bookRoom(room2, LocalDate.of(2022, 4, 13), hotel);

        Assertions.assertTrue(firstBooking.isPresent(), "First booking is not present.");
        Assertions.assertTrue(client1.removeBooking(firstBooking.get(), hotel), "First remove booking is wrong.");

        Assertions.assertFalse(client1.removeBooking(firstBooking.get(), hotel), "Can't remove booking which doesn't exist.");
    }

    @Test
    public void testClientBookRoomNotEnoughMoney() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room2);

        client2.bookRoom(room2, LocalDate.of(2022, 4, 13), hotel);
        Assertions.assertEquals(new ArrayList<>(), client2.getBookings(), "Can't add a booking when client doesnt have enough money");

        client1.bookRoom(room1, LocalDate.of(2022, 4, 13), hotel);
        Assertions.assertEquals(LocalDate.of(2022, 4, 13), client1.getBookings().getFirst().getDate(), "Can't add a booking when client doesnt have enough money");

        client1.bookRoom(room3, LocalDate.of(2022, 5, 13), hotel);
        Assertions.assertEquals(1, client1.getBookings().size(), "Can't add a booking when client doesnt have enough money");
    }

    @Test
    public void testClientBookRoomThatDoesntExist() {
        hotel.addRoomToHotel(room1);

        client1.bookRoom(room2, LocalDate.of(2022, 4, 13), hotel);
        Assertions.assertEquals(new ArrayList<>(), client1.getBookings(), "Cant add a booking when this room doesnt exist.");
    }

    @Test
    public void testClientBookRoomButItIsAlreadyBooked() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room2);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 13), hotel);
        client2.bookRoom(room1, LocalDate.of(2022, 4, 13), hotel);
        Assertions.assertEquals(new ArrayList<>(), client2.getBookings(), "Cant add a booking when this room is already booked.");

    }

    @Test
    public void testClientWriteReview() {
        hotel.addRoomToHotel(room1);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 13), hotel);
        client1.writeReview("Lahe hotell", 5, hotel);

        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("Lahe hotell", 5);
        Assertions.assertEquals(expected, client1.getReviews(), "Something is wrong with reviews");
    }

    @Test
    public void testClientWriteReviewWithScore6() {
        hotel.addRoomToHotel(room1);

        client1.bookRoom(room1, LocalDate.of(2022, 4, 13), hotel);
        assertFalse(client1.writeReview("lahe Hotell", 6, hotel));
    }

    @Test
    public void testClientWriteReviewWhenNotInBookingList() {
        hotel.addRoomToHotel(room1);

        assertFalse(client1.writeReview("lahe Hotell", 3, hotel));
    }
}