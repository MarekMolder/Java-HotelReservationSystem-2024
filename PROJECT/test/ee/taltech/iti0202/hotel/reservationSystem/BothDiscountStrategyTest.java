package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.ECountryAndCitys;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.hotel.HotelBuilder;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BothDiscountStrategyTest {
    private Room room1;

    private Hotel hotel1;

    private ReservationSystem reservationSystem;
    private Client client1;
    private Client client2;
    private Client client3;

    @BeforeEach
    void setUp() {
        room1 = new Room();

        hotel1 = new HotelBuilder()
                .setCountry(ECountryAndCitys.ESTONIA)
                .setCity("Tallinn").createHotel();

        reservationSystem = new ReservationSystem();
        client1 = new Client("Mati", 1000);
        client2 = new Client("Kalle", 10000);
        client3 = new Client("Kati", 10000);
    }

    @Test
    @DisplayName("Should book a room.")
    void BookRoom_MonthDiscountStrategy_clientDiscountAlso() {
        // setup
        hotel1.addRoomToHotel(room1);

        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.giveStrategy(new BothDiscountStrategy());

        hotel1.getHotelClients().add(client1);
        hotel1.getHotelClients().add(client2);
        hotel1.getHotelClients().add(client3);

        hotel1.getHotelClientBookings().put(client1, 5);
        hotel1.getHotelClientBookings().put(client2, 3);
        hotel1.getHotelClientBookings().put(client3, 1);

        assertEquals(BigDecimal.valueOf(1000.0), client1.getBalance());
        assertNull(hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 7), hotel1, client1);
        // room price 40 * 7 = 280
        // discount = 20% + 15% + 15%
        // total = 280 * (1 - 0.5) = 140
        // 1000 - 140 = 860
        assertEquals(BigDecimal.valueOf(860.0), client1.getBalance());
        assertEquals(1, hotel1.getMonthlyBookings().get(Month.APRIL));
    }
}
