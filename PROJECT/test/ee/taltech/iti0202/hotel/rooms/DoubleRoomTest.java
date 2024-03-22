package ee.taltech.iti0202.hotel.rooms;

import ee.taltech.iti0202.hotel.hotel.Hotel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleRoomTest {
    Room room1 = new Room();
    Room room2 = new DoubleRoom();
    Hotel hotel1 = new Hotel();

    @Test
    public void testStandardRoomGetPrice() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);

        assertEquals(100, room1.getPrice());
        assertEquals(200, room2.getPrice());
    }
}