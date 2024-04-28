package ee.taltech.iti0202.hotel.rooms;

import ee.taltech.iti0202.hotel.hotel.ECountryAndCitys;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.hotel.HotelBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private Room room1;
    private Room room2;
    private Hotel hotel1;
    private Hotel hotel2;

    @BeforeEach
    void setUp() {
        room1 = new Room();
        room2 = new Room();

        hotel1 = new HotelBuilder()
                .setCountry(ECountryAndCitys.ESTONIA)
                .setCity("Tallinn").createHotel();

        hotel2 = new HotelBuilder()
                .setCountry(ECountryAndCitys.ESTONIA)
                .setCity("Tallinn").createHotel();
    }

    @Test
    @DisplayName("Should add room to hotel.")
    void addHotel_roomNotInHotel_roomAddedToHotel() {
        assertTrue(room1.addHotel(hotel1));
        assertTrue(room2.addHotel(hotel2));

        assertEquals(hotel1, room1.getHotel());
        assertEquals(hotel2, room2.getHotel());
    }

    @Test
    @DisplayName("Should not add room to hotel when this room is already in another hotel.")
    void addHotel_roomInOtherHotel_roomNotAddedToHotel() {
        assertTrue(room1.addHotel(hotel1));

        assertFalse(room1.addHotel(hotel1));
        assertFalse(room1.addHotel(hotel2));

        //check if room1 is in hotel1
        assertEquals(hotel1, room1.getHotel());
    }

    @Test
    void getHotel() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel2.addRoomToHotel(room2);

        // what to expect?
        assertEquals(hotel1, room1.getHotel());
        assertEquals(hotel2, room2.getHotel());
    }

    @Test
    void getPrice() {
        // setup
        hotel1.addRoomToHotel(room1);

        // what to expect?
        assertEquals(BigDecimal.valueOf(40), room1.getPrice());
    }

    @Test
    @DisplayName("Every Room should return their unique number.")
    void getNumber() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel2.addRoomToHotel(room2);

        // what to expect
        assertNotEquals(room1.getNumber(), room2.getNumber());
    }

    @Test
    @DisplayName("Should set new price for the room.")
    public void setPrice() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel2.addRoomToHotel(room2);

        room1.setPrice(BigDecimal.valueOf(200));
        room2.setPrice(BigDecimal.valueOf(300));

        // what toe expect?
        assertEquals(BigDecimal.valueOf(200), room1.getPrice());
        assertEquals(BigDecimal.valueOf(300), room2.getPrice());
    }
}
