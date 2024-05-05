package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.hotel.Hotel;

import java.time.LocalDate;

public class BothDiscountStrategy implements DiscountStrategy {

    @Override
    public double getDiscount(Hotel hotel, LocalDate since, LocalDate until) {
        DiscountStrategy monthStrategy = new MonthDiscountStrategy();
        DiscountStrategy timeStrategy = new TimeDiscountStrategy();
        double finalDiscount = 0.0;

        double discount = monthStrategy.getDiscount(hotel, since, until);
        double discount2 = timeStrategy.getDiscount(hotel, since, until);
        finalDiscount += discount + discount2;

        return Math.min(finalDiscount, 0.4);
    }
}
