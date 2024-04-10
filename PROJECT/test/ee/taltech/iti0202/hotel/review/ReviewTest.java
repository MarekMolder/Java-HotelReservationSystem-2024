package ee.taltech.iti0202.hotel.review;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {
    private Room room1;
    private Hotel hotel1;
    private Client client1;
    @BeforeEach
    void setUp() {
        room1 = new Room();
        hotel1 = new Hotel();
        client1 = new Client("Mati", 10000);
    }

    @Test
    void getReview() {
        // setup
        hotel1.addRoomToHotel(room1);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to test?
        assertTrue(client1.writeReview("Lahe hotell", 5, hotel1));

        // what to expect
        assertEquals("Lahe hotell", client1.getReviews().getFirst().getReview());
    }

    @Test
    void getScore() {
        // setup
        hotel1.addRoomToHotel(room1);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to test?
        assertTrue(client1.writeReview("Lahe hotell", 5, hotel1));

        // what to expect
        assertEquals(5, client1.getReviews().getFirst().getScore());
    }

    @Test
    void reviewScoreIsWrong() {
        // setup
        hotel1.addRoomToHotel(room1);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to test?


        // what to expect
        assertThrows(IllegalArgumentException.class, () -> new Review("hei", 6, hotel1));
    }

    @Test
    void getHotel() {
        // setup
        hotel1.addRoomToHotel(room1);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to test?
        assertTrue(client1.writeReview("Lahe hotell", 5, hotel1));

        // what to expect
        assertEquals(hotel1, client1.getReviews().getFirst().getHotel());
    }
}