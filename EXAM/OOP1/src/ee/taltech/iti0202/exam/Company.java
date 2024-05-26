package ee.taltech.iti0202.exam;

import java.math.BigDecimal;
import java.util.*;

public class Company {
    private List<Client> clients;
    private Map<Car, Integer> cars;
    private List<Order> orders;
    private CarStrategy strategy;

    public Company () {
        this.clients = new ArrayList<>();
        this.cars = new HashMap<>();
        this.orders = new ArrayList<>();
        this.strategy = new GetBestCar();
    }

    public void addClient (Client client) {
        this.clients.add(client);
    }

    public void addCar (Car car) {
        this.cars.put(car, 0);
    }

    public void addOrder (Order order) {
        this.orders.add(order);
    }

    public List<Client> getClients() {
        return clients;
    }

    public Map<Car, Integer> getCars() {
        return cars;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public boolean orderCar(Client client, int minutes) {
        Car car = strategy.getCar(this, client, minutes);

        BigDecimal price = car.getPrice().multiply(BigDecimal.valueOf(minutes)).multiply(getDiscount(client));
        int gasItTakes = car.getGasItTakesPerMinute() * minutes;
        if (this.cars.containsKey(car) && gasItTakes >= car.getGasBalance() && price.compareTo(client.getBalance()) <= 0) {
            Order order = new Order(car, minutes, client);
            client.setTimesDrived();
            this.addOrder(order);
            this.cars.merge(car, 1, Integer::sum);
            client.setBalance(price);
            car.setGasBalance(gasItTakes);
            client.addOrder(order);
            return true;
        }
        return false;
    }

    public List<Car> findCarsThatAreEmpty () {
        List<Car> cars = new ArrayList<>();
        for (Car car : this.cars.keySet()) {
            if (car.getGasBalance() == 0) {
                cars.add(car);
            }
        }
        return cars;
    }

    public void fillCars (List<Car> cars) {
        for (Car car : cars) {
            car.giveGas();
        }
    }

    public Car findPopularCar() {
        return cars.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    public BigDecimal getDiscount(Client client) {
        if (client.getTimesDrived() % 3 == 0) {
            return BigDecimal.valueOf(0.95);
        }
        return BigDecimal.ONE;
    }
}
