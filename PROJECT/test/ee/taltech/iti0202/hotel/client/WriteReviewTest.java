package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.DeluxeRoom;
import ee.taltech.iti0202.hotel.rooms.DoubleRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WriteReviewTest {
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
        client2 = new Client("Mati", 0);
        client3 = new Client("Mati", 10000);
    }

    @Test
    @DisplayName("Should write a new review.")
    void WriteReview() {
        // setup
        hotel1.addRoomToHotel(room1);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

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
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

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
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

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
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to expect
        assertFalse(client1.writeReview("", 3, hotel1));
        assertFalse(client1.writeReview("", 4, hotel1));
        assertEquals(new ArrayList<>(), client1.getReviews());
        assertEquals(new HashMap<>(), hotel1.getHotelReviews());
    }
}
