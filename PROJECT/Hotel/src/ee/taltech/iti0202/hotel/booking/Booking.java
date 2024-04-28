package ee.taltech.iti0202.hotel.booking;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.EServices;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Represents a booking made by a client for a room in a hotel.
 */
public class Booking {
    private final Client client; // The client who made the booking
    private final BigDecimal price; // The booking price
    private final HashSet<EServices> services;
    private Room room; // The room booked for the reservation
    private final LocalDate since; // The start date of the booking
    private final LocalDate until; // The end date of the booking

    /**
     * Constructs a new Booking object.
     * @param room The room booked for the reservation.
     * @param since The start date of the booking.
     * @param until The end date of the booking.
     * @param client The client who made the booking.
     */
    public Booking(Room room, LocalDate since, LocalDate until, Client client) {
        this.room = room;
        this.since = since;
        this.until = until;
        this.client = client;
        this.price = room.getPrice().multiply(BigDecimal.valueOf(getDatesInRange(since, until).size()));
        this.services = new HashSet<>();
    }

    public void addService(EServices service) {
        services.add(service);
    }

    public void removeService(EServices service) {
        services.remove(service);
    }

    public HashSet<EServices> getService() {
        return this.services;
    }

    public double getBalanceOfService() {
        double balance = 0;
        for (EServices service : services) {
            balance += service.getPrice();
        }
        return balance;
    }

    /**
     * This method is used to get the room of the specific booking.
     * @return The room booked for this booking.
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * This method is used to get the start date of the specific booking.
     * @return The start date of the booking.
     */
    public LocalDate getSince() {
        return this.since;
    }

    /**
     * The method is used to get the end date of the specific booking.
     * @return The end date of the booking.
     */
    public LocalDate getUntil() {
        return this.until;
    }

    /**
     * This method is used to get the client who made the specific booking.
     * @return The client who made the booking.
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * This method is used to get the price of the booking.
     *
     * @return The price of the booking.
     */
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * This method is used to retrieve a list of dates within the specific range.
     * @param since The start date of the range.
     * @param until The end date of the range.
     * @return A list of dates within the specific range.
     */
    public List<LocalDate> getDatesInRange(LocalDate since, LocalDate until) {
        List<LocalDate> datesInRange = new ArrayList<>();
        while (!since.isAfter(until)) {
            datesInRange.add(since);
            since = since.plusDays(1);
        }
        return datesInRange;
    }
}
