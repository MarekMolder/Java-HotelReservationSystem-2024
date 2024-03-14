package ee.taltech.iti0202.hotel.client;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.time.LocalDate;
import java.util.*;


public class Client {
    private final String name;
    private Integer money;
    private Map<String, Integer> clientReviews;
    private List<Booking> clientBookings;

    public Client(String name, int money) {
        this.name = name;
        this.money = money;
        this.clientReviews = new HashMap<>();
        this.clientBookings = new ArrayList<>();
    }

    public boolean writeReview(String review, Integer score, Hotel hotel) {
        for (Booking booking : hotel.hotelBooked) {
            if (booking.getClient() == this) {
                if (score >= 1 && score <= 5 && !review.isEmpty()) {
                    this.clientReviews.put(review, score);
                    hotel.hotelReviewsAnonymus.put(review, score);
                    hotel.hotelReviews.put(this, score);
                    hotel.bestClient.put(this, score);
                    return true;
                }
            }
        }
        return false;
    }

    public Optional<Booking> bookRoom(Room room, LocalDate date, Hotel hotel) {
        for (Booking hotelBooking : hotel.hotelBooked) {
            if (hotelBooking.getDate().equals(date) && hotelBooking.getRoom().equals(room)) {
                return Optional.empty();
            }
        }

        if (hotel.hotelRooms.contains(room)) {
            if (this.money >= room.getPrice()) {
                Booking booking = new Booking(room, date, this);
                hotel.hotelBooked.add(booking);
                clientBookings.add(booking);
                hotel.hotelClients.add(this);
                this.money -= room.getPrice();
                if (hotel.hotelClientsWithScore.containsKey(this)) {
                    hotel.hotelClientsWithScore.merge(this, 1, Integer::sum);
                    return Optional.of(booking);
                } else {
                    hotel.hotelClientsWithScore.put(this, 1);
                    return Optional.of(booking);
                }
            }
        }
        return Optional.empty();
    }

    /*
    parameeter booking

    kontrollida kas booking on selle hotelli oma
    hotelClients te set-iks + remove client siis kui ta hotelclientwithscore value on 1, kui on rohkem kui 1 siis öra removi
     */
    public Boolean removeBooking(Room room, LocalDate date, Hotel hotel) {
        for (Booking booking : clientBookings) {
            if (booking.getRoom().equals(room) && booking.getDate().equals(date)) {
                clientBookings.remove(booking);
                hotel.hotelBooked.remove(booking);
                hotel.hotelClients.remove(this);
                this.money += room.getPrice();
                if (hotel.hotelClientsWithScore.containsKey(this)) {
                    hotel.hotelClientsWithScore.merge(this, 1, Integer::min);
                }
                return true;
            }
        }
        return false;
    }

    public Map<String, Integer> getReviews() {
        return this.clientReviews;
    }

    public List<Booking> getBookings() {
        return this.clientBookings;
    }

}
