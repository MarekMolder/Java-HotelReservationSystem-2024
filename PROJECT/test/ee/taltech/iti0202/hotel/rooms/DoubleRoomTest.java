package ee.taltech.iti0202.hotel.rooms;

import ee.taltech.iti0202.hotel.hotel.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleRoomTest {
    private Room room1;
    private DoubleRoom room2;
    private Hotel hotel1;

    @BeforeEach
    void setUp() {
        room1 = new Room();
        room2 = new DoubleRoom();
        hotel1 = new Hotel();
    }

    @Test
    void getDoubleRoomPrice() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);

        assertEquals(40, room1.getPrice()); // Standard room
        assertEquals(80, room2.getPrice()); // Double room
    }
}