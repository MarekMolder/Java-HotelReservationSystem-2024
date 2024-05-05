package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.hotel.Hotel;

import java.time.LocalDate;
import java.util.List;

public class TimeDiscountStrategy implements DiscountStrategy {

    public static final int RESERVATIONS = 7;

    @Override
    public double getDiscount(Hotel hotel, LocalDate since, LocalDate until) {
        List<LocalDate> dates = hotel.getDatesInRange(since, until);
        double discount = 0.0;

        if (dates.size() >= RESERVATIONS) {
            discount = 0.15;
            if (dates.size() > RESERVATIONS) {
                long extraDays = dates.size() - RESERVATIONS;
                discount += extraDays * 0.005;
            }
        }
        return Math.min(discount, 0.30);
    }
}
