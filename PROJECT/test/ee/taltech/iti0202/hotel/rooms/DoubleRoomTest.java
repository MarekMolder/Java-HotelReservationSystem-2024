package ee.taltech.iti0202.hotel.rooms;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleRoomTest {
    Room room1 = new DoubleRoom();
    Room room2 = new DoubleRoom();
    Room room3 = new DoubleRoom();
    Hotel hotel1 = new Hotel();
    Hotel hotel2 = new Hotel();
    Client client1 = new Client("Mati", 200);
    @Test
    public void testStandardRoomGetMethods() {
        hotel1.addRoomToHotel(room1);

        Assertions.assertEquals(1, room2.getNumber(), "First get room is wrong");
        Assertions.assertEquals(200, room2.getPrice(), "Second get room is wrong");
    }

    @Test
    public void testStandardRoomSetHotel() {
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);

        assertTrue(room1.setHotel(hotel1));
        assertFalse(room1.setHotel(hotel2));
    }
}