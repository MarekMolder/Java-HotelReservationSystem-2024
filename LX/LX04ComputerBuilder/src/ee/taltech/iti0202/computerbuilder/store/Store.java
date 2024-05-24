package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.Customer;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.factory.Factory;

import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

public class Store {
    private BigDecimal profitMargin;
    private String name;
    private BigDecimal balance;
    private final Database database;

    public Store(String name, BigDecimal balance, BigDecimal profitMargin) {
        this.name = name;
        this.balance = balance;
        if (profitMargin.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException();
        } else {
            this.profitMargin = profitMargin;
        }
        this.database = Database.getInstance();
    }

    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        Component component = database.getComponents().get(id);
        if (component == null) {
            throw new ProductNotFoundException();
        }
        if (component.getAmount() < 1) {
            throw new OutOfStockException();
        }

        if (customer.getBalance().compareTo(component.getPrice().multiply(profitMargin)) < 0) {
            throw new NotEnoughMoneyException();
        } else {
            database.decreaseComponentStock(id, 1);
            customer.setBalance(customer.getBalance().subtract(component.getPrice().multiply(profitMargin)));
            this.setBalance(balance.add(component.getPrice().multiply(profitMargin)));
            customer.getComponents().add(component);
        }
        return component;
    }

    public Boolean orderComputer(EUseCase useCase, EComputerType type, Customer customer) throws OutOfStockException, ProductNotFoundException {
        Computer computer = Factory.assembleComputer(Optional.empty(), Optional.of(useCase), type, this);
        BigDecimal computerPrice = computer.calculateTotalPrice().multiply(profitMargin);

        if (customer.getBalance().compareTo(computerPrice) >= 0) {
            customer.setBalance((customer.getBalance().subtract(computerPrice)));
            customer.addComputer(computer);

            for (Component component : computer.getComponents()) {
                database.decreaseComponentStock(component.getId(), 1);
            }
            this.setBalance(balance.add(computerPrice));

        } else {
            throw new IllegalArgumentException("Customer does not have enough balance to buy this computer.");
        }
        return true;
    }

    public boolean orderComputer(BigDecimal budget, EComputerType type, Customer customer) throws OutOfStockException, ProductNotFoundException {
        Computer computer = Factory.assembleComputer(Optional.of(budget),Optional.empty(), type, this);
        BigDecimal computerPrice = computer.calculateTotalPrice().multiply(profitMargin);

        if (customer.getBalance().compareTo(computerPrice) >= 0) {
            customer.setBalance((customer.getBalance().subtract(computerPrice)));
            customer.addComputer(computer);

            for (Component component : computer.getComponents()) {
                database.decreaseComponentStock(component.getId(), 1);
            }
            this.setBalance(balance.add(computerPrice));

        } else {
            throw new IllegalArgumentException("Customer does not have enough balance to buy this computer.");
        }
        return true;
    }

    public boolean orderComputer(EComputerType type, Customer customer) throws OutOfStockException, ProductNotFoundException {
        Computer computer = Factory.assembleComputer(Optional.empty(), Optional.empty(), type, this);
        BigDecimal computerPrice = computer.calculateTotalPrice().multiply(profitMargin);

        if (customer.getBalance().compareTo(computerPrice) >= 0) {
            customer.setBalance((customer.getBalance().subtract(computerPrice)));
            customer.addComputer(computer);

            for (Component component : computer.getComponents()) {
                database.decreaseComponentStock(component.getId(), 1);
            }
            this.setBalance(balance.add(computerPrice));

        } else {
            throw new IllegalArgumentException("Customer does not have enough balance to buy this computer.");
        }
        return true;
    }

    public boolean orderComputer(BigDecimal budget, EUseCase useCase, EComputerType type, Customer customer) throws OutOfStockException, ProductNotFoundException {
        Computer computer = Factory.assembleComputer(Optional.of(budget), Optional.of(useCase), type, this);
        BigDecimal computerPrice = computer.calculateTotalPrice().multiply(profitMargin);

        if (customer.getBalance().compareTo(computerPrice) >= 0) {
            customer.setBalance((customer.getBalance().subtract(computerPrice)));
            customer.addComputer(computer);

            for (Component component : computer.getComponents()) {
                database.decreaseComponentStock(component.getId(), 1);
            }
            this.setBalance(balance.add(computerPrice));

        } else {
            throw new IllegalArgumentException("Customer does not have enough balance to buy this computer.");
        }
        return true;
    }

    public List<Component> getAvailableComponents() {
        return database.getComponents().values().stream()
                .filter(c -> c.getAmount() > 0)
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByAmount() {
        return database.getComponents().values().stream()
                .sorted(Comparator.comparingInt(Component::getAmount))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByName() {
        return database.getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getName))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByPrice() {
        return database.getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getPrice))
                .collect(Collectors.toList());
    }

    public List<Component> filterByType(Component.Type type) {
        return database.getComponents().values().stream()
                .filter(c -> c.getType() == type)
                .collect(Collectors.toList());
    }

    public BigDecimal getInventoryValue() {
        return database.getComponents().values().stream()
                .map(c -> c.getPrice().multiply(new BigDecimal(c.getAmount())).multiply(profitMargin))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(BigDecimal profitMargin) {
        if (profitMargin.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException();
        }
        this.profitMargin = profitMargin;
    }
}
