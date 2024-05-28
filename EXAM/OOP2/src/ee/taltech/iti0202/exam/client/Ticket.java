package ee.taltech.iti0202.exam.client;

import ee.taltech.iti0202.exam.bus.Bus;
import ee.taltech.iti0202.exam.company.Company;

public class Ticket {
    private final Company company;
    private final Bus bus;
    private final double price;
    private final Client client;
    private final String startPoint;
    private final String endPoint;

    /**
     * Constructs a new ticket.
     */
    public Ticket(Company company, Bus bus, double price, Client client, String startPoint, String endPoint) {
        this.company = company;
        this.bus = bus;
        this.price = price;
        this.client = client;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Company getCompany() {
        return company;
    }

    public Bus getBus() {
        return bus;
    }

    public double getPrice() {
        return price;
    }

    public Client getClient() {
        return client;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
