package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.hotel.EServices;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.review.Review;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    /**
     * This method is used to get client balance.
     * Rounded to one decimal place using the half-up rounding mode.
     * @return The client balance.
     */
    public BigDecimal getBalance() {
        return balance.setScale(1, RoundingMode.HALF_UP);
    }

    /**
     * This method is used to subtract specific amount from the client's balance.
     * @param amount value to be subtracted from the balance.
     */
    public void subtractBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    /**
     * This method is used to add specific amount to the clientś balance.
     * @param amount value to be added to the balance.
     */
    public void addBalance(BigDecimal amount) {
        balance = balance.add(amount);
    }

    /**
     * This method is used to get client written reviews.
     * @return List containing client reviews.
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
     * This method is used to get client services.
     * @return The list containing client services.
     */
    public List<EServices> getClientServices() {
        return clientServices;
    }

    /**
     * This method is used to get arithmetic of how much has client spend on services.
     * @return The arithmetic of how much has client spend on services.
     */
    public double getClientServiceArithmetic() {
        double sum = 0.0;

        if (!clientServices.isEmpty()) {
            for (EServices service : clientServices) {
                sum += service.getPrice();
            }
            return Math.round((sum / clientServices.size()) * 10) / 10.0;
        }
        return 0.0;
    }

    /**
     * This method is used to update client's booking.
     * @param oldBooking the old booking which will be updated.
     * @param newBooking the new booking which will be added.
     * @return true if booking update was successful, false otherwise.
     */
    public boolean updateBooking(Booking oldBooking, Booking newBooking) {
        int index = clientBookings.indexOf(oldBooking);
        if (index != -1) {
            clientBookings.set(index, newBooking);
            return true;
        }
        return false;
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

}
