package ee.taltech.iti0202.transportation.ticket;

public class Ticket {
    private final double price;

    public Ticket (double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}
