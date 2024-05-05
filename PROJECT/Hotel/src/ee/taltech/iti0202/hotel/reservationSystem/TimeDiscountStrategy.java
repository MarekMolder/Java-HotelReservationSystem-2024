package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.hotel.Hotel;

import java.time.LocalDate;
import java.util.List;

public class TimeDiscountStrategy implements DiscountStrategy {

    public static final int RESERVATIONS = 7;
    public static final double FIFTEEN = 0.15;
    public static final double FIVE = 0.005;
    public static final double THIRTEEN = 0.30;

    @Override
    public double getDiscount(Hotel hotel, LocalDate since, LocalDate until) {
        List<LocalDate> dates = hotel.getDatesInRange(since, until);
        double discount = 0.0;

        if (dates.size() >= RESERVATIONS) {
            discount = FIFTEEN;
            if (dates.size() > RESERVATIONS) {
                long extraDays = dates.size() - RESERVATIONS;
                discount += extraDays * FIVE;
            }
        }
        return Math.min(discount, THIRTEEN);
    }
}
