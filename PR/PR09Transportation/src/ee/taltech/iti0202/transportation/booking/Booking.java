package ee.taltech.iti0202.transportation.booking;

import ee.taltech.iti0202.transportation.person.Person;
import ee.taltech.iti0202.transportation.strategy.BookingStrategy;

import java.time.LocalDate;

public class Booking {
    private final Person person;
    private final LocalDate date;
    private BookingStrategy strategy;

    public Booking(Person person, LocalDate date) {
        this.person = person;
        this.date = date;
        this.strategy = null;
    }

    public Person getPerson() {
        return this.person;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setStrategy (BookingStrategy strategy) {
        this.strategy = strategy;
    }

    public BookingStrategy getStrategy() {
        return this.strategy;
    }
}
