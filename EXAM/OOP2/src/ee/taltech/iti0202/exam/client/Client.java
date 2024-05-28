package ee.taltech.iti0202.exam.client;

import ee.taltech.iti0202.exam.company.Company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    private final String name;
    private final int age;
    private double balance;
    private final List<Ticket> tickets;
    private final Map<Company, Integer> companies;

    /**
     * Constructs a new client.
     * @param name
     * @param age
     * @param balance
     */
    public Client(String name, int age, double balance) {
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.tickets = new ArrayList<>();
        this.companies = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Adds a ticket to client's list.
     */
    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public Map<Company, Integer> getCompanies() {
        return companies;
    }

    /**
     * Everytime client buys a ticket from a company, it increases a company number.
     */
    public void increaseCompany(Company company) {
        this.companies.merge(company, 1, Integer::sum);
    }

    /**
     * Subtracts price from client's balance.
     * @param price
     */
    public void setBalance(double price) {
        this.balance -= price;
    }
}
