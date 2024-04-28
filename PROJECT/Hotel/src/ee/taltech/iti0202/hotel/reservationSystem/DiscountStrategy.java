package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.hotel.Hotel;

import java.time.LocalDate;

public interface DiscountStrategy {
    double getDiscount(Hotel hotel, LocalDate since, LocalDate until);
}
