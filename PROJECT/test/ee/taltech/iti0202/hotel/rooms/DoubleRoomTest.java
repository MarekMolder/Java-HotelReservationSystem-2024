package ee.taltech.iti0202.hotel.rooms;

import ee.taltech.iti0202.hotel.hotel.ECountryAndCitys;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.hotel.HotelBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleRoomTest {
    private Room room1;
    private DoubleRoom room2;
    private Hotel hotel1;

    @BeforeEach
    void setUp() {
        room1 = new Room();
        room2 = new DoubleRoom();

        hotel1 = new HotelBuilder()
                .setCountry(ECountryAndCitys.ESTONIA)
                .setCity("Tallinn").createHotel();
    }

    @Test
    void getDoubleRoomPrice() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);

        // what to expect?
        assertEquals(BigDecimal.valueOf(40), room1.getPrice()); // Standard room
        assertEquals(BigDecimal.valueOf(80), room2.getPrice()); // Double room
    }
}
