package ee.taltech.iti0202.computerstore;

import ee.taltech.iti0202.computerstore.components.Component;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Customer {
    private String name;
    private BigDecimal balance;
    private final List<Component> components = new ArrayList<>();

    public Customer(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Component> getComponents() {
        return components;
    }

}