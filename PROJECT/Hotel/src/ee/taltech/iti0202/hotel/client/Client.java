package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.hotel.EServices;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.review.Review;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Client who can book rooms, write reviews and remove bookings.
 */
public class Client {
    private final String name; // The name of the client
    private BigDecimal balance; // The balance of the client
    private List<Review> clientReviews; // a set of client reviews
    private List<Booking> clientBookings; // a List of client bookings
    private List<EServices> clientServices; // a List of client services

    /**
     * Constructs a new Client object.
     * @param name The name of the client
     * @param balance The balance of the client
     */
    public Client(String name, int balance) {
        this.name = name;
        this.balance = BigDecimal.valueOf(balance);
        this.clientReviews = new ArrayList<>();
        this.clientBookings = new ArrayList<>();
        this.clientServices = new ArrayList<>();
    }

    public List<EServices> getClientServices() {
        return clientServices;
    }

    public int getClientServiceArithmetic() {
        int sum = 0;

        if (!clientServices.isEmpty()) {
            for (EServices service : clientServices) {
                sum += service.getPrice();
            }
            return sum / clientServices.size();
        }
        return 0;
    }

    /**
     * This method is used to get client written reviews.
     * @return The map containing client reviews.
     */
    public List<Review> getReviews() {
        return this.clientReviews;
    }

    /**
     * This method is used to get client bookings.
     * @return The list containing client bookings.
     */
    public List<Booking> getBookings() {
        return this.clientBookings;
    }

    /**
     * The method is used to get client balance.
     *
     * @return The client balance.
     */
    public BigDecimal getBalance() {
        return balance.setScale(1, RoundingMode.HALF_UP);
    }


    public void subtractBalance(BigDecimal price) {
        balance = balance.subtract(price);
    }

    public void addBalance(BigDecimal price) {
        balance = balance.add(price);
    }

    /**
     * This method is used to write review for a hotel.
     * @param review The review text to be written.
     * @param score The score given to the hotel in review (1-5).
     * @param hotel The hotel for which the review is being written.
     * @return True if the review was successfully written, false otherwise.
     */
    public boolean writeReview(String review, int score, Hotel hotel) {
        if (hotel.getHotelClients().contains(this)
                && !hotel.getHotelReviews().containsKey(this)
                && score >= 1 && score <= 5
                && !review.isEmpty()) {
            Review review1 = new Review(review, score, hotel);
            this.clientReviews.add(review1);
            hotel.getHotelReviews().put(this, review1);
            return true;
            }
        return false;
    }

    public boolean updateBooking(Booking oldBooking, Booking newBooking) {
        if (clientBookings.contains(oldBooking)) {
            for (Booking booking : clientBookings) {
                if (booking.equals(oldBooking)) {
                    booking = newBooking;
                    return true;
                }
            }
        }
        return false;
    }
}
