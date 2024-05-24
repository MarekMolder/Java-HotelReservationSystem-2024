package ee.taltech.iti0202.computerbuilder;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Computer;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Customer {
    private String name;
    private BigDecimal balance;
    private final List<Component> components = new ArrayList<>();
    private final List<Computer> computers;

    public Customer(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
        this.computers = new ArrayList<>();
    }
    public List<Computer> getComputer() {
        return computers;
    }

    public void addComputer(Computer computer) {
        computers.add(computer);
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
