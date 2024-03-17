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

    public boolean writeReview(String review, int score, Hotel hotel) {
        List<Object> clientReview = new ArrayList<>();
        if (hotel.hotelClients.contains(this) && !hotel.hotelReviews.containsKey(this)) {
            if (score >= 1 && score <= 5 && !review.isEmpty()) {
                clientReview.add(review);
                clientReview.add(score);
                this.clientReviews.put(review, score);
                hotel.hotelReviews.put(this, clientReview);
                return true;
            }
        }
        return false;
    }

    /*
    Saab mitmeks päevaks bronnida
     */
    public Optional<Booking> bookRoom(Room room, LocalDate date, Hotel hotel) {
        for (Booking hotelBooking : hotel.hotelBookings) {
            if (hotelBooking.getDate().equals(date) && hotelBooking.getRoom().equals(room)) {
                return Optional.empty();
            }
        }

        if (hotel.hotelRooms.contains(room)) {
            if (this.money >= room.getPrice()) {
                Booking booking = new Booking(room, date, this);
                hotel.hotelBookings.add(booking);
                clientBookings.add(booking);
                hotel.hotelClients.add(this);
                this.money -= room.getPrice();
                if (hotel.hotelClientBooking.containsKey(this)) {
                    hotel.hotelClientBooking.merge(this, 1, Integer::sum);
                    return Optional.of(booking);
                } else {
                    hotel.hotelClientBooking.put(this, 1);
                    return Optional.of(booking);
                }
            }
        }
        return Optional.empty();
    }

    public Boolean removeBooking(Booking booking, Hotel hotel) {
        if (hotel.hotelBookings.contains(booking) && clientBookings.contains(booking)) {
            clientBookings.remove(booking);
            hotel.hotelBookings.remove(booking);
            this.money += booking.getRoom().getPrice();
            if (hotel.hotelClientBooking.containsKey(this) && hotel.hotelClientBooking.get(this) > 1) {
                hotel.hotelClientBooking.merge(this, 1, Integer::min);
            } else {
                hotel.hotelClients.remove(this);
            }
            return true;
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
