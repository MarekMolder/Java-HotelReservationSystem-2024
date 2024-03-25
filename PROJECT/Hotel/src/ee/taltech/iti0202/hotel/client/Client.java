package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a Client who can book rooms, write reviews and remove bookings.
 */
public class Client {
    private final String name; // The name of the client
    private Integer balance; // The balance of the client
    private Map<String, Integer> clientReviews; // a map of client reviews
    private List<Booking> clientBookings; // a List of client bookings

    /**
     * Constructs a new Client object.
     * @param name The name of the client
     * @param balance The balance of the client
     */
    public Client(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.clientReviews = new HashMap<>();
        this.clientBookings = new ArrayList<>();
    }

    /**
     * This method is used to write review for a hotel.
     * @param review The review text to be written.
     * @param score The score given to the hotel in review (1-5).
     * @param hotel The hotel for which the review is being written.
     * @return True if the review was successfully written, false otherwise.
     */
    public boolean writeReview(String review, int score, Hotel hotel) {
        List<Object> clientReview = new ArrayList<>();
        if (hotel.hotelClients.contains(this)) {
            if (score >= 1 && score <= 5 && !review.isEmpty()) {
                clientReview.add(review);
                clientReview.add(score);
                this.clientReviews.put(review, score);
                hotel.hotelReviews.put(this, clientReview);
                hotel.hotelReviewsScores.put(this, score);
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to book a room in a hotel for a specific duration.
     * @param room The room to be booked.
     * @param since The start date of the booking.
     * @param until The end date of the booking.
     * @param hotel The hotel where the room is to be booked.
     * @return Optional containing the booking object if conditions are met, otherwise Optional empty.
     */
    public Optional<Booking> bookRoom(Room room, LocalDate since, LocalDate until, Hotel hotel) {
        if (!hotel.isRoomAvailable(since, until, room)) {
                return Optional.empty();
            }

        if (hotel.hotelRooms.contains(room)) {
            if (this.balance >= room.getPrice()) {
                Booking booking = new Booking(room, since, until, this);
                hotel.hotelBookings.add(booking);
                clientBookings.add(booking);
                hotel.hotelClients.add(this);
                this.balance -= room.getPrice();
                hotel.hotelClientBooking.merge(this, 1, Integer::sum);
                return Optional.of(booking);
                }
            }
        return Optional.empty();
    }

    /**
     * This method is used to remove booking.
     * @param booking The booking object to be removed.
     * @param hotel The hotel where the booking is made.
     * @return True if the removal was successful, false otherwise.
     */
    public Boolean removeBooking(Booking booking, Hotel hotel) {
        if (hotel.hotelBookings.contains(booking) && clientBookings.contains(booking)) {
            clientBookings.remove(booking);
            hotel.hotelBookings.remove(booking);
            this.balance += booking.getRoom().getPrice();
            if (hotel.hotelClientBooking.containsKey(this) && hotel.hotelClientBooking.get(this) > 1) {
                hotel.hotelClientBooking.merge(this, 1, Integer::min);
                return true;
            } else {
                hotel.hotelClients.remove(this);
                hotel.hotelClientBooking.remove(this);
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to get client written reviews.
     * @return The map containing client reviews.
     */
    public Map<String, Integer> getReviews() {
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
     * @return The client balance.
     */
    public Integer getBalance() {
        return balance;
    }

}
