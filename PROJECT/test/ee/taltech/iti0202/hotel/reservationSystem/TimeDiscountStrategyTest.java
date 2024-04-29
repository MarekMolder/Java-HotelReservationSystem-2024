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

import static org.junit.jupiter.api.Assertions.*;

public class TimeDiscountStrategyTest {

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
        client1 = new Client("Mati", 10000);
        client2 = new Client("Kalle", 10000);
        client3 = new Client("Kati", 10000);
    }

    @Test
    void BookRoom_TimeDiscountStrategy() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.giveStrategy(new TimeDiscountStrategy());

        assertEquals(BigDecimal.valueOf(10000.0), client1.getBalance());

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 2), hotel1, client1);
        // room price 40 * 2 = 80
        // discount = 0%
        // total = 80 * (1 - 0.0) = 80
        // 10000 - 80 = 9920
        assertEquals(BigDecimal.valueOf(9920.0), client1.getBalance());

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 5, 1), LocalDate.of(2022, 5, 7), hotel1, client1);
        // room price 40 * 7 = 280
        // discount = 15%
        // total = 280 * (1 - 0.15) = 238
        // 9920 - 238 = 852
        assertEquals(BigDecimal.valueOf(9682.0), client1.getBalance());

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 12), hotel1, client1);
        // room price 40 * 12 = 480
        // discount = 17.5%
        // total = 480 * (1 - 0.175) = 396
        // 9682 - 396 = 9286
        assertEquals(BigDecimal.valueOf(9286.0), client1.getBalance());

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 7, 1), LocalDate.of(2022, 8, 30), hotel1, client1);
        // room price 40 * 61 = 2440
        // discount = 30%
        // total = 2440 * (1 - 0.3) = 1708
        // 9286 - 1708 = 7578
        assertEquals(BigDecimal.valueOf(7578.0), client1.getBalance());
    }

    @Test
    void BookRoom_TimeDiscountStrategy_PlusClientGetHotelDiscount() {
        // setup
        hotel1.addRoomToHotel(room1);
        reservationSystem.addHotelToSystem(hotel1);
        reservationSystem.giveStrategy(new TimeDiscountStrategy());

        hotel1.getHotelClients().add(client1);
        hotel1.getHotelClients().add(client2);
        hotel1.getHotelClients().add(client3);

        hotel1.getHotelClientBookings().put(client1, 5);
        hotel1.getHotelClientBookings().put(client2, 3);
        hotel1.getHotelClientBookings().put(client3, 1);

        assertEquals(BigDecimal.valueOf(10000.0), client1.getBalance());

        reservationSystem.bookRoomInHotel(room1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 7), hotel1, client1);
        // room price 40 * 7 = 280
        // discount = 15% + 15%
        // total = 280 * (1 - 0.3) = 196
        // 10000 - 196 = 9804
        assertEquals(BigDecimal.valueOf(9804.0), client1.getBalance());
    }
}