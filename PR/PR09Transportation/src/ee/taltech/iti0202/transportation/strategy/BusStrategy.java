package ee.taltech.iti0202.transportation.strategy;

import ee.taltech.iti0202.transportation.person.Person;
import ee.taltech.iti0202.transportation.ticket.Ticket;

import java.time.LocalDate;

public class BusStrategy implements BookingStrategy {
    @Override
    public Ticket bookTicket(Person person, LocalDate date, double price) {
        if (date.getDayOfMonth() % 2 == 0) {
            return new Ticket(price - (price * 0.5));
        } else {
            return new Ticket(price - (price * person.getName().length() / 100));
        }
    }
}

