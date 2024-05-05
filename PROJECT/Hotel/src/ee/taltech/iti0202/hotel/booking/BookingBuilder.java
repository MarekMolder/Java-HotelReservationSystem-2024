package ee.taltech.iti0202.hotel.booking;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.EServices;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.time.LocalDate;
import java.util.HashSet;

public class BookingBuilder {
    private Room room;
    private LocalDate since;
    private LocalDate until;
    private Client client;
    private HashSet<EServices> services = new HashSet<>();

    /**
     * Creates a new BookingBuilder based on an existing Booking.
     * This constructor initializes the builder with the properties of an existing booking,
     * allowing for modification of any booking details.
     * @param existingBooking the existing booking from which to copy initial values
     */
    public BookingBuilder(Booking existingBooking) {
        this.room = existingBooking.getRoom();
        this.since = existingBooking.getSince();
        this.until = existingBooking.getUntil();
        this.client = existingBooking.getClient();
        this.services = new HashSet<>(existingBooking.getService());
    }

    /**
     * Sets the room for this booking.
     * @param room the room to be set
     * @return this builder instance for chaining method calls
     */
    public BookingBuilder setRoom(Room room) {
        this.room = room;
        return this;
    }

    /**
     * Sets the start date of the booking.
     * @param since the start date to be set
     * @return this builder instance for chaining method calls
     */
    public BookingBuilder setSince(LocalDate since) {
        this.since = since;
        return this;
    }

    /**
     * Sets the end date of the booking.
     * @param until the end date to be set
     * @return this builder instance for chaining method calls
     */
    public BookingBuilder setUntil(LocalDate until) {
        this.until = until;
        return this;
    }

    /**
     * Sets the client for this booking.
     * @param client the client to be set
     * @return this builder instance for chaining method calls
     */
    public BookingBuilder setClient(Client client) {
        this.client = client;
        return this;
    }

    /**
     * Adds a service to the booking.
     * @param service the service to add to the booking
     * @return this builder instance for chaining method calls
     */
    public BookingBuilder addService(EServices service) {
        this.services.add(service);
        return this;
    }

    /**
     * Creates and returns a new Booking instance with the current state of the builder.
     * @return the newly created Booking instance
     */
    public Booking createBooking() {
        return new Booking(room, since, until, client);
    }
}
