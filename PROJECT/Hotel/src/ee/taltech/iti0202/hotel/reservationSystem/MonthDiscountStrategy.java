package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.hotel.Hotel;

import java.time.LocalDate;

public class MonthDiscountStrategy implements DiscountStrategy {

    public static final double DEFAULT_VALUE = 0.20;
    public static final double NINETEEN = 0.19;
    public static final double EIGHTEEN = 0.18;
    public static final double SEVENTEEN = 0.17;
    public static final double SIXTEEN = 0.16;
    public static final double FIFTEEN = 0.15;
    public static final double FOURTEEN = 0.14;
    public static final double THIRTEEN = 0.13;
    public static final double TWELVE = 0.12;
    public static final double ELEVEN = 0.11;
    public static final double TEN = 0.10;
    public static final double ZERO = 0.0;

    @Override
    public double getDiscount(Hotel hotel, LocalDate since, LocalDate until) {
        Integer bookings = hotel.getMonthlyBookings().get(since.getMonth());
        if (bookings == null) {
            // If there's no record for that month, return a default discount
            return DEFAULT_VALUE;
        }

        return switch (bookings) {
            case 1 -> NINETEEN;
            case 2 -> EIGHTEEN;
            case 3 -> SEVENTEEN;
            case 4 -> SIXTEEN;
            case 5 -> FIFTEEN;
            case 6 -> FOURTEEN;
            case 7 -> THIRTEEN;
            case 8 -> TWELVE;
            case 9 -> ELEVEN;
            case 10 -> TEN;
            default -> ZERO;
        };
    }
}
