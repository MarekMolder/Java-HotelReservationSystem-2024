package ee.taltech.iti0202.computerstore.database;

import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.util.*;

public class Database {
    private final Map<Integer, Component> components = new HashMap<>();

    public static Database getInstance() {
        return null;
    }

    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (!components.containsKey(component.getId())) {
            components.put(component.getId(), component);
        } else {
            throw new ProductAlreadyExistsException();
        }
    }

    public void deleteComponent(int id) throws ProductNotFoundException {
        if (components.containsKey(id)) {
            components.remove(id);
        } else {
            throw new ProductNotFoundException();
        }
    }

    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        if (components.containsKey(id)) {
            if (amount > 0) {
                Component comp = components.get(id);
                int currentAmount = comp.getAmount();
                comp.setAmount(currentAmount + amount);
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new ProductNotFoundException();
        }
    }

    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        if (components.containsKey(id)) {
            if (amount > 0) {
                Component comp = components.get(id);
                int currentAmount = comp.getAmount();
                if (currentAmount <= amount) {
                    comp.setAmount(currentAmount - amount);
                } else {
                    throw new OutOfStockException();
                }
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new ProductNotFoundException();
        }
    }

    public Map<Integer, Component> getComponents() {
      return components;
    }

    public void resetEntireDatabase() {
        components.clear();
        // reset components
    }

    public void saveToFile(String location) {
    }

    public void loadFromFile(String location) {
    }
}