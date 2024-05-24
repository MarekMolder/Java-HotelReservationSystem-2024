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

    /**
     * Constructs a Store with the specified name, balance, and profit margin.
     * @param name the name of the store
     * @param balance the initial balance of the store
     * @param profitMargin the profit margin for sales
     */
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

    /**
     * Purchases a component for a customer.
     * @param id the ID of the component
     * @param customer the customer purchasing the component
     * @return the purchased component
     * @throws OutOfStockException if the component is out of stock
     * @throws ProductNotFoundException if the component is not found
     * @throws NotEnoughMoneyException if the customer does not have enough money
     */
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

    /**
     * Orders a computer for a customer based on the specified use case and type.
     * @param useCase the use case of the computer
     * @param type the type of the computer
     * @param customer the customer ordering the computer
     * @return true if the order is successful
     * @throws OutOfStockException if any component is out of stock
     * @throws ProductNotFoundException if any component is not found
     */
    public Boolean orderComputer(EUseCase useCase, EComputerType type, Customer customer)
            throws OutOfStockException, ProductNotFoundException {
        Computer computer = Factory.assembleComputer(Optional.empty(),
                Optional.of(useCase), type, this);
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

    /**
     * Orders a computer for a customer based on the specified budget and type.
     * @param budget the budget for the computer
     * @param type the type of the computer
     * @param customer the customer ordering the computer
     * @return true if the order is successful
     * @throws OutOfStockException if any component is out of stock
     * @throws ProductNotFoundException if any component is not found
     */
    public boolean orderComputer(BigDecimal budget, EComputerType type, Customer customer)
            throws OutOfStockException, ProductNotFoundException {
        Computer computer = Factory.assembleComputer(Optional.of(budget),
                Optional.empty(), type, this);
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

    /**
     * Orders a computer for a customer based on the specified type.
     * @param type the type of the computer
     * @param customer the customer ordering the computer
     * @return true if the order is successful
     * @throws OutOfStockException if any component is out of stock
     * @throws ProductNotFoundException if any component is not found
     */
    public boolean orderComputer(EComputerType type, Customer customer)
            throws OutOfStockException, ProductNotFoundException {
        Computer computer = Factory.assembleComputer(Optional.empty(),
                Optional.empty(), type, this);
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

    /**
     * Orders a computer for a customer based on the specified budget, use case, and type.
     * @param budget the budget for the computer
     * @param useCase the use case of the computer
     * @param type the type of the computer
     * @param customer the customer ordering the computer
     * @return true if the order is successful
     * @throws OutOfStockException if any component is out of stock
     * @throws ProductNotFoundException if any component is not found
     */
    public boolean orderComputer(BigDecimal budget, EUseCase useCase,
                                 EComputerType type, Customer customer)
            throws OutOfStockException, ProductNotFoundException {
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

    /**
     * Gets the list of available components in the store.
     * @return the list of available components
     */
    public List<Component> getAvailableComponents() {
        return database.getComponents().values().stream()
                .filter(c -> c.getAmount() > 0)
                .collect(Collectors.toList());
    }

    /**
     * Gets the list of components sorted by amount.
     * @return the list of components sorted by amount
     */
    public List<Component> getComponentsSortedByAmount() {
        return database.getComponents().values().stream()
                .sorted(Comparator.comparingInt(Component::getAmount))
                .collect(Collectors.toList());
    }

    /**
     * Gets the list of components sorted by name.
     * @return the list of components sorted by name
     */
    public List<Component> getComponentsSortedByName() {
        return database.getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getName))
                .collect(Collectors.toList());
    }

    /**
     * Gets the list of components sorted by price.
     * @return the list of components sorted by price
     */
    public List<Component> getComponentsSortedByPrice() {
        return database.getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getPrice))
                .collect(Collectors.toList());
    }

    /**
     * Filters the components by the specified type.
     * @param type the type of the components
     * @return the list of components of the specified type
     */
    public List<Component> filterByType(Component.Type type) {
        return database.getComponents().values().stream()
                .filter(c -> c.getType() == type)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the total inventory value of the store.
     * @return the total inventory value
     */
    public BigDecimal getInventoryValue() {
        return database.getComponents().values().stream()
                .map(c -> c.getPrice().multiply(new BigDecimal(c.getAmount())).multiply(profitMargin))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Gets the name of the store.
     * @return the name of the store
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the store.
     * @param name the new name of the store
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the balance of the store.
     * @return the balance of the store
     */
    public BigDecimal getBalance() {
        return this.balance;
    }

    /**
     * Sets the balance of the store.
     * @param balance the new balance of the store
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets the profit margin of the store.
     * @return the profit margin
     */
    public BigDecimal getProfitMargin() {
        return profitMargin;
    }

    /**
     * Sets the profit margin of the store.
     * @param profitMargin the new profit margin
     * @throws IllegalArgumentException if the profit margin is less than 1
     */
    public void setProfitMargin(BigDecimal profitMargin) {
        if (profitMargin.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException();
        }
        this.profitMargin = profitMargin;
    }
}
