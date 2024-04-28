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
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MonthDiscountStrategyTest {

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
    void BookRoom_MonthDiscountStrategy_zeroAndOneBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.giveStrategy(new MonthDiscountStrategy());
        hotel1.getMonthlyBookings().put(Month.FEBRUARY, 5);

        assertEquals(BigDecimal.valueOf(1000.0), client1.getBalance());
        assertNull(hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 2), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 20%
        // total = 80 * (1 - 0.2) = 64
        // 1000 - 64 = 936
        assertEquals(BigDecimal.valueOf(936.0), client1.getBalance());
        assertEquals(1, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 3), LocalDate.of(2022, 4, 4), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 19%
        // total = 80 * (1 - 0.19) = 64,8
        // 936 - 64,8 = 871.2
        assertEquals(BigDecimal.valueOf(871.20), client1.getBalance());
    }

    @Test
    @DisplayName("Should book a room.")
    void BookRoom_MonthDiscountStrategy_TwoAndThreeBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.giveStrategy(new MonthDiscountStrategy());
        hotel1.getMonthlyBookings().put(Month.FEBRUARY, 5);
        hotel1.getMonthlyBookings().put(Month.APRIL, 2);

        assertEquals(BigDecimal.valueOf(1000.0), client1.getBalance());
        assertEquals(2, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 2), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 18%
        // total = 80 * (1 - 0.18) = 65.6
        // 1000 - 65.6 = 934.4
        assertEquals(BigDecimal.valueOf(934.4), client1.getBalance());
        assertEquals(3, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 3), LocalDate.of(2022, 4, 4), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 17%
        // total = 80 * (1 - 0.17) = 66,4
        // 934.4 - 66,4 = 868
        assertEquals(BigDecimal.valueOf(868.0), client1.getBalance());
        assertEquals(4, hotel1.getMonthlyBookings().get(Month.APRIL));
    }

    @Test
    @DisplayName("Should book a room.")
    void BookRoom_MonthDiscountStrategy_FourAndFiveBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.giveStrategy(new MonthDiscountStrategy());
        hotel1.getMonthlyBookings().put(Month.FEBRUARY, 5);
        hotel1.getMonthlyBookings().put(Month.APRIL, 4);

        assertEquals(BigDecimal.valueOf(1000.0), client1.getBalance());
        assertEquals(4, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 2), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 16%
        // total = 80 * (1 - 0.16) = 67.2
        // 1000 - 67.2 = 932.8
        assertEquals(BigDecimal.valueOf(932.8), client1.getBalance());
        assertEquals(5, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 3), LocalDate.of(2022, 4, 4), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 15%
        // total = 80 * (1 - 0.15) = 68
        // 932.8 - 68 = 864.8
        assertEquals(BigDecimal.valueOf(864.8), client1.getBalance());
        assertEquals(6, hotel1.getMonthlyBookings().get(Month.APRIL));
    }

    @Test
    @DisplayName("Should book a room.")
    void BookRoom_MonthDiscountStrategy_6And7Booking() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.giveStrategy(new MonthDiscountStrategy());
        hotel1.getMonthlyBookings().put(Month.FEBRUARY, 5);
        hotel1.getMonthlyBookings().put(Month.APRIL, 6);

        assertEquals(BigDecimal.valueOf(1000.0), client1.getBalance());
        assertEquals(6, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 2), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 14%
        // total = 80 * (1 - 0.14) = 68.8
        // 1000 - 68.8 = 931.2
        assertEquals(BigDecimal.valueOf(931.2), client1.getBalance());
        assertEquals(7, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 3), LocalDate.of(2022, 4, 4), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 13%
        // total = 80 * (1 - 0.13) = 69.6
        // 931.2 - 69.6 = 863.2
        assertEquals(BigDecimal.valueOf(861.6), client1.getBalance());
        assertEquals(8, hotel1.getMonthlyBookings().get(Month.APRIL));
    }

    @Test
    @DisplayName("Should book a room.")
    void BookRoom_MonthDiscountStrategy_8And9Booking() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.giveStrategy(new MonthDiscountStrategy());
        hotel1.getMonthlyBookings().put(Month.FEBRUARY, 5);
        hotel1.getMonthlyBookings().put(Month.APRIL, 8);

        assertEquals(BigDecimal.valueOf(1000.0), client1.getBalance());
        assertEquals(8, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 2), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 12%
        // total = 80 * (1 - 0.12) = 70.4
        // 1000 - 70.4 = 929.6
        assertEquals(BigDecimal.valueOf(929.6), client1.getBalance());
        assertEquals(9, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 3), LocalDate.of(2022, 4, 4), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 11%
        // total = 80 * (1 - 0.11) = 71.2
        // 929.6 - 71.2 = 858.4
        assertEquals(BigDecimal.valueOf(858.4), client1.getBalance());
        assertEquals(10, hotel1.getMonthlyBookings().get(Month.APRIL));
    }

    @Test
    @DisplayName("Should book a room.")
    void BookRoom_MonthDiscountStrategy_10plusBooking() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.giveStrategy(new MonthDiscountStrategy());
        hotel1.getMonthlyBookings().put(Month.FEBRUARY, 5);
        hotel1.getMonthlyBookings().put(Month.APRIL, 10);

        assertEquals(BigDecimal.valueOf(1000.0), client1.getBalance());
        assertEquals(10, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 2), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 10%
        // total = 80 * (1 - 0.10) = 72
        // 1000 - 72 = 928
        assertEquals(BigDecimal.valueOf(928.0), client1.getBalance());
        assertEquals(11, hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 3), LocalDate.of(2022, 4, 4), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 0%
        // total = 80 * (1 - 0) = 80
        // 928 - 80 = 858.4
        assertEquals(BigDecimal.valueOf(848.0), client1.getBalance());
        assertEquals(12, hotel1.getMonthlyBookings().get(Month.APRIL));
    }

    @Test
    @DisplayName("Should book a room.")
    void BookRoom_MonthDiscountStrategy_clientDiscountAlso() {
        // setup
        hotel1.addRoomToHotel(room1);

        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.giveStrategy(new MonthDiscountStrategy());

        hotel1.getMonthlyBookings().put(Month.FEBRUARY, 5);

        hotel1.getHotelClients().add(client1);
        hotel1.getHotelClients().add(client2);
        hotel1.getHotelClients().add(client3);

        hotel1.getHotelClientBookings().put(client1, 5);
        hotel1.getHotelClientBookings().put(client2, 3);
        hotel1.getHotelClientBookings().put(client3, 1);

        assertEquals(BigDecimal.valueOf(1000.0), client1.getBalance());
        assertNull(hotel1.getMonthlyBookings().get(Month.APRIL));

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 2), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 20% + 15%
        // total = 80 * (1 - 0.20 + 0.15) = 52
        // 1000 - 52 = 940
        assertEquals(BigDecimal.valueOf(948.0), client1.getBalance());
        assertEquals(1, hotel1.getMonthlyBookings().get(Month.APRIL));
    }
}
