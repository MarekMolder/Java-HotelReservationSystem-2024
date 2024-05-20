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

    public Computer orderComputer(EUseCase useCase, EComputerType type) {
        return Factory.assembleComputer(Optional.empty(), Optional.of(useCase), type, this);
    }

    public Computer orderComputer(BigDecimal budget, EComputerType type) {
        return Factory.assembleComputer(Optional.of(budget),Optional.empty(), type, this);
    }

    public Computer orderComputer(EComputerType type) {
        return Factory.assembleComputer(Optional.empty(), Optional.empty(), type, this);
    }

    public Computer orderComputer(BigDecimal budget, EUseCase useCase, EComputerType type) {
        return Factory.assembleComputer(Optional.of(budget), Optional.of(useCase), type, this);
    }

    public List<Component> getAvailableComponents() {
        return database.getComponents().values().stream()
                .filter(c -> c.getAmount() > 0)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
