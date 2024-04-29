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


    public BookingBuilder(Booking existingBooking) {
        this.room = existingBooking.getRoom();
        this.since = existingBooking.getSince();
        this.until = existingBooking.getUntil();
        this.client = existingBooking.getClient();
        this.services = new HashSet<>(existingBooking.getService());
    }

    public BookingBuilder setRoom(Room room) {
        this.room = room;
        return this;
    }

    public BookingBuilder setSince(LocalDate since) {
        this.since = since;
        return this;
    }

    public BookingBuilder setUntil(LocalDate until) {
        this.until = until;
        return this;
    }

    public BookingBuilder setClient(Client client) {
        this.client = client;
        return this;
    }

    public BookingBuilder addService(EServices service) {
        this.services.add(service);
        return this;
    }

    public Booking createBooking() {
        return new Booking(room, since, until, client);
    }
}