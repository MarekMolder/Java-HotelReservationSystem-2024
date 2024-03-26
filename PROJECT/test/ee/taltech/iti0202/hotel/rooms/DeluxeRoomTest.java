package ee.taltech.iti0202.hotel.rooms;

import ee.taltech.iti0202.hotel.hotel.Hotel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeluxeRoomTest {
    Room room1 = new Room();
    Room room2 = new DeluxeRoom();
    Hotel hotel1 = new Hotel();

    @Test
    public void testDeluxeRoomGetPrice() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);

        assertEquals(40, room1.getPrice());
        assertEquals(150, room2.getPrice());
    }
}