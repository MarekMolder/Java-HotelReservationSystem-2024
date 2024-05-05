package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.hotel.Hotel;

import java.time.LocalDate;

public interface DiscountStrategy {

    /**
     * Calculates the discount amount to be applied to a hotel booking.
     */
    double getDiscount(Hotel hotel, LocalDate since, LocalDate until);
}
