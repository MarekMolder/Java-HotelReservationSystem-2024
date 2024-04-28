package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class PriceCalculator {

    public static BigDecimal calculateRoomPrice(Hotel hotel, Room room, LocalDate since, LocalDate until, Client client) {
        BigDecimal price = room.getPrice().multiply(BigDecimal.valueOf(hotel.getDatesInRange(since, until).size()));
        return price.multiply(BigDecimal.valueOf(hotel.getDiscount(client)));
    }

}
