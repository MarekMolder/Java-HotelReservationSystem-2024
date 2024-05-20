package ee.taltech.iti0202.computerbuilder.database;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public final class Database {
    private final Map<Integer, Component> components = new HashMap<>();
    private static Database instance;

    private Database() {

    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
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
                if (currentAmount >= amount) {
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
        Component.resetIdCounter();
    }
}
