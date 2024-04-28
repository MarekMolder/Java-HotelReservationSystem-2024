package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.hotel.Hotel;

import java.time.LocalDate;

public class MonthDiscountStrategy implements DiscountStrategy {
    @Override
    public double getDiscount(Hotel hotel, LocalDate since, LocalDate until) {
        if (hotel.getMonthlyBookings().containsKey(since.getMonth())) {
            if (hotel.getMonthlyBookings().get(since.getMonth()) == 10) {
                return 0.1;
            } else if (hotel.getMonthlyBookings().get(since.getMonth()) == 9) {
                return 0.11;
            } else if (hotel.getMonthlyBookings().get(since.getMonth()) == 8) {
                return 0.12;
            } else if (hotel.getMonthlyBookings().get(since.getMonth()) == 7) {
                return 0.13;
            } else if (hotel.getMonthlyBookings().get(since.getMonth()) == 6) {
                return 0.14;
            } else if (hotel.getMonthlyBookings().get(since.getMonth()) == 5) {
                return 0.15;
            } else if (hotel.getMonthlyBookings().get(since.getMonth()) == 4) {
                return 0.16;
            } else if (hotel.getMonthlyBookings().get(since.getMonth()) == 3) {
                return 0.17;
            } else if (hotel.getMonthlyBookings().get(since.getMonth()) == 2) {
                return 0.18;
            } else if (hotel.getMonthlyBookings().get(since.getMonth()) == 1) {
                return 0.19;
            } else {
                return 0.0;
            }
        }
        return 0.20;
    }
}
