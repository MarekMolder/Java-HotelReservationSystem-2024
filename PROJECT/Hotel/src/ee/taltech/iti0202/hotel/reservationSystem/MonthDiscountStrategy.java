package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.hotel.Hotel;

import java.time.LocalDate;

public class MonthDiscountStrategy implements DiscountStrategy {

    @Override
    public double getDiscount(Hotel hotel, LocalDate since, LocalDate until) {
        Integer bookings = hotel.getMonthlyBookings().get(since.getMonth());
        if (bookings == null) {
            // If there's no record for that month, return a default discount
            return 0.20;
        }

        // DISCOUNTS 20% - 10%
        return switch (bookings) {
            case 1 -> 0.19;
            case 2 -> 0.18;
            case 3 -> 0.17;
            case 4 -> 0.16;
            case 5 -> 0.15;
            case 6 -> 0.14;
            case 7 -> 0.13;
            case 8 -> 0.12;
            case 9 -> 0.11;
            case 10 -> 0.10;
            default -> 0.0;
        };
    }
}
