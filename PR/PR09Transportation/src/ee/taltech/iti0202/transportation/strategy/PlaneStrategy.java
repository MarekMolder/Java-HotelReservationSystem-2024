package ee.taltech.iti0202.transportation.strategy;

import ee.taltech.iti0202.transportation.person.Person;
import ee.taltech.iti0202.transportation.ticket.Ticket;

import java.time.LocalDate;

public class PlaneStrategy implements BookingStrategy {
    @Override
    public Ticket bookTicket(Person person, LocalDate date, double price) {
        if (person.getAge() <= 75) {
            return new Ticket(Math.round(price - (price * person.getAge() / 100)));
        } else {
            return new Ticket(Math.round(price - (price * 75 / 100)));
        }
    }
}
