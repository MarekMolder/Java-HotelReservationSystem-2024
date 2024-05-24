package ee.taltech.iti0202.computerbuilder.database;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

public final class Database {
    private final Map<Integer, Component> components = new HashMap<>();
    private static Database instance;

    private Database() {

    }

    /**
     * Returns the single instance of the database.
     * @return the singleton instance of the Database
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Saves a component to the database.
     * @param component the component to save
     * @throws ProductAlreadyExistsException if a component with the same ID already exists
     */
    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (!components.containsKey(component.getId())) {
            components.put(component.getId(), component);
        } else {
            throw new ProductAlreadyExistsException();
        }
    }

    /**
     * Deletes a component from the database.
     * @param id the ID of the component to delete
     * @throws ProductNotFoundException if no component with the given ID exists
     */
    public void deleteComponent(int id) throws ProductNotFoundException {
        if (components.containsKey(id)) {
            components.remove(id);
        } else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Increases the stock of a component.
     * @param id the ID of the component
     * @param amount the amount to increase
     * @throws ProductNotFoundException if no component with the given ID exists
     * @throws IllegalArgumentException if the amount is non-positive
     */
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

    /**
     * Decreases the stock of a component.
     * @param id the ID of the component
     * @param amount the amount to decrease
     * @throws OutOfStockException if there is not enough stock to decrease
     * @throws ProductNotFoundException if no component with the given ID exists
     * @throws IllegalArgumentException if the amount is non-positive
     */
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

    /**
     * Returns all components in the database.
     * @return a map of component IDs to components
     */
    public Map<Integer, Component> getComponents() {
        return components;
    }

    /**
     * Resets the entire database, clearing all components and resetting the ID counter.
     */
    public void resetEntireDatabase() {
        components.clear();
        Component.resetIdCounter();
    }
}
