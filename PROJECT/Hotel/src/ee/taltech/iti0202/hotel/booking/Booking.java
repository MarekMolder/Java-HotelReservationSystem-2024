package ee.taltech.iti0202.hotel.booking;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private final Client client;
    private final Integer number;
    private Room room;
    private final LocalDate since;
    private final LocalDate until;

    public Booking (Room room, LocalDate since, LocalDate until, Client client) {
        this.room = room;
        this.since = since;
        this.until = until;
        this.client = client;
        this.number = room.getNumber();
    }

    public Room getRoom() {
        return this.room;
    }

    public LocalDate getSince() {
        return this.since;
    }

    public LocalDate getUntil() {
        return this.until;
    }

    public List<LocalDate> getDatesInRange(LocalDate since, LocalDate until) {
        List<LocalDate> datesInRange = new ArrayList<>();
        while (!since.isAfter(until)) {
            datesInRange.add(since);
            since = since.plusDays(1);
        }
        return datesInRange;
    }

    public Client getClient() {
        return this.client;
    }
}
