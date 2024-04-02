package ee.taltech.iti0202.transportation.travelagency;

import ee.taltech.iti0202.transportation.booking.Booking;
import ee.taltech.iti0202.transportation.person.Person;
import ee.taltech.iti0202.transportation.strategy.BookingStrategy;
import ee.taltech.iti0202.transportation.strategy.PlaneStrategy;
import ee.taltech.iti0202.transportation.ticket.Ticket;

import java.time.LocalDate;

public class TravelAgency {
    private double defaultPrice;

    public TravelAgency(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public void setPrice(double price) {
        this.defaultPrice = price;
    }

    public Ticket bookTicket(Booking booking) {
        BookingStrategy strategy = booking.getStrategy();
        double price = defaultPrice;
        if (strategy != null) {
            Ticket prices = strategy.bookTicket(booking.getPerson(), booking.getDate(), defaultPrice);
            price = prices.getPrice();
        }
        return new Ticket(price);
    }

    public static void main(String[] args) {
        Person person = new Person("Mati", 24);
        TravelAgency agency = new TravelAgency(100);
        Booking booking = new Booking(person, LocalDate.of(2024, 5, 3));
        PlaneStrategy planeStrategy = new PlaneStrategy();
        booking.setStrategy(planeStrategy);
        Ticket ticket = agency.bookTicket(booking);
        System.out.println(ticket.getPrice());   // -> 76.0 (24 years old means 24% off)
    }
}
