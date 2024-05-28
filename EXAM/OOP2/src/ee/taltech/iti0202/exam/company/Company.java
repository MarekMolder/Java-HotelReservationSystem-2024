package ee.taltech.iti0202.exam.company;

import ee.taltech.iti0202.exam.bus.Bus;
import ee.taltech.iti0202.exam.client.Client;
import ee.taltech.iti0202.exam.client.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Company {
    private List<Client> clients;
    private Map<Bus, Integer> buses; //Map of bus and bus popularity number
    private List<Ticket> history;
    private DiscountStrategy strategy;

    public Company() {
        this.clients = new ArrayList<>();
        this.buses = new HashMap<>();
        this.history = new ArrayList<>();
        this.strategy = new Discount();
    }

    public void addClients(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }

    public void addBus(Bus bus) {
        if (!buses.containsKey(bus)) {
            buses.put(bus, 0);
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public Map<Bus, Integer> getBuses() {
        return buses;
    }

    public List<Ticket> getHistory() {
        return history;
    }

    public DiscountStrategy getStrategy() {
        return strategy;
    }

    public void increaseBusPopularity(Bus bus) {
        buses.merge(bus, 1, Integer::sum);
    }

    public Map<Bus, Double> findBusesForStartPointAndDestination(String startPoint, String destination) {
        Map<Bus, Double> result = new HashMap<>();
        for (Bus bus : buses.keySet()) {
            if (bus.getStartPoint().equals(startPoint) && bus.getDestination().equals(destination)) {
                result.put(bus, bus.getPrice());
            }
        }
        return result;
    }

    public List<Bus> sortPopularBuses() {
        return buses.entrySet().stream()
                .sorted(Map.Entry.<Bus, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public boolean sellTicket(String startPoint, String destination, Client client) {
        Map<Bus, Double> suitableBuses = findBusesForStartPointAndDestination(startPoint, destination);

        if (!suitableBuses.isEmpty()) {
            Bus cheapestBus = suitableBuses.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
            double discount = strategy.getDiscount(this, client);

            if (discount == 0 && cheapestBus.getSeatsTaken() < cheapestBus.getMaxSeats()) {
                Ticket ticket = new Ticket(this, cheapestBus, 0.0,
                        client, startPoint, destination);

                this.history.add(ticket);
                this.increaseBusPopularity(cheapestBus);
                this.addClients(client);

                client.increaseCompany(this);
                client.addTicket(ticket);
                cheapestBus.takeSeat();
                return true;

            } else if (client.getBalance() >= cheapestBus.getPrice() * discount
                    && cheapestBus.getSeatsTaken() < cheapestBus.getMaxSeats()) {
                Ticket ticket = new Ticket(this, cheapestBus, cheapestBus.getPrice() * discount,
                        client, startPoint, destination);

                this.history.add(ticket);
                this.increaseBusPopularity(cheapestBus);
                this.addClients(client);

                client.setBalance(cheapestBus.getPrice() * discount);
                client.increaseCompany(this);
                client.addTicket(ticket);
                cheapestBus.takeSeat();
                return true;

            } else {
                return false;
            }
        }
        return false;
    }

}
