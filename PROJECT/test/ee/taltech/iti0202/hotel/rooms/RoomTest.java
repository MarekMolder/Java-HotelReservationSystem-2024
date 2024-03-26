package ee.taltech.iti0202.hotel.rooms;

import ee.taltech.iti0202.hotel.hotel.Hotel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    Room room1 = new Room();
    Room room2 = new Room();
    Room room3 = new Room();
    Hotel hotel1 = new Hotel();
    Hotel hotel2 = new Hotel();
    @Test
    public void testRoomGetNumber() {
        hotel1.addRoomToHotel(room1);
        hotel2.addRoomToHotel(room2);
        hotel2.addRoomToHotel(room3);

        assertNotEquals(room2.getNumber(), room1.getNumber());
        assertNotEquals(room2.getNumber(), room3.getNumber());
        assertNotEquals(room1.getNumber(), room3.getNumber());
    }

    @Test
    public void testRoomGetPrice() {
        hotel1.addRoomToHotel(room1);
        hotel2.addRoomToHotel(room2);

        assertEquals(40, room1.getPrice());
        assertEquals(40, room2.getPrice());
    }

    @Test
    public void testRoomGetHotel() {
        hotel1.addRoomToHotel(room1);
        hotel2.addRoomToHotel(room2);

        assertEquals(hotel1, room1.getHotel());
        assertEquals(hotel2, room2.getHotel());
    }

    @Test
    public void testRoomSetHotel() {
        assertTrue(room1.setHotel(hotel1));
        assertTrue(room2.setHotel(hotel2));

        assertEquals(hotel1, room1.getHotel());
        assertEquals(hotel2, room2.getHotel());
    }

    @Test
    public void testRoomSetHotelWhenItIsAlreadyAddedInHotel() {
        assertTrue(room1.setHotel(hotel1));
        assertTrue(room2.setHotel(hotel2));
        assertFalse(room1.setHotel(hotel1));
        assertFalse(room2.setHotel(hotel2));
        assertFalse(room1.setHotel(hotel2));
        assertFalse(room2.setHotel(hotel1));

        assertEquals(hotel1, room1.getHotel());
        assertEquals(hotel2, room2.getHotel());
    }

    @Test
    public void testRoomSetPrice() {
        hotel1.addRoomToHotel(room1);
        hotel2.addRoomToHotel(room2);

        room1.setPrice(200);
        room2.setPrice(300);

        assertEquals(200, room1.getPrice());
        assertEquals(300, room2.getPrice());
    }
}
