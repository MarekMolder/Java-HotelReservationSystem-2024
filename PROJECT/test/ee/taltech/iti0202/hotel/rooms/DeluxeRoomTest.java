package ee.taltech.iti0202.hotel.rooms;

import ee.taltech.iti0202.hotel.hotel.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeluxeRoomTest {
    private Room room1;
    private DeluxeRoom room2;
    private Hotel hotel1;

    @BeforeEach
    void setUp() {
        room1 = new Room();
        room2 = new DeluxeRoom();
        hotel1 = new Hotel();
    }

    @Test
    void getDeluxeRoomPrice() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);

        assertEquals(40, room1.getPrice()); // Standard room
        assertEquals(150, room2.getPrice()); // Deluxe room
    }
}