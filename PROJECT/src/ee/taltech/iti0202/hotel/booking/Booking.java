package ee.taltech.iti0202.hotel.booking;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.time.LocalDate;

public class Booking {
    private final Client client;
    private final Integer number;
    private Room room;
    private final LocalDate date;

    public Booking (Room room, LocalDate date, Client client) {
        this.room = room;
        this.date = date;
        this.client = client;
        this.number = room.getNumber();
    }

    public Room getRoom() {
        return this.room;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Client getClient() {
        return this.client;
    }
}
