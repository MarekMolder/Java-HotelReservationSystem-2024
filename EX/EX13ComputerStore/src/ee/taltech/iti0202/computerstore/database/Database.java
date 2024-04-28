package ee.taltech.iti0202.computerstore.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.io.*;
import java.lang.module.Configuration;
import java.lang.reflect.Type;
import java.net.spi.InetAddressResolverProvider;
import java.util.*;

public class Database {
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

    public void saveToFile(String location) {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(location)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    public void loadFromFile(String location) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(location)) {
            instance = gson.fromJson(reader, Database.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}