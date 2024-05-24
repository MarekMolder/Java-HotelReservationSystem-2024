package ee.taltech.iti0202.computerbuilder.components;

import java.math.BigDecimal;

public class Component {
    private static int nextId = 1;
    private int id;
    private String name;
    private Type type;
    private BigDecimal price;
    private int amount = 1;
    private String manufacturer;
    private int performancePoints;
    private int powerConsumption;

    public enum Type {
        CPU, GPU, RAM, MOTHERBOARD, HDD, SSD, PSU, KEYBOARD, TOUCHPAD, SCREEN, BATTERY, CASE
    }

    /**
     * Constructs a new component.
     * @param name
     * @param type
     * @param price
     * @param manufacturer
     * @param performancePoints
     * @param powerConsumption
     */
    public Component(String name, Type type, BigDecimal price, String manufacturer,
                     int performancePoints, int powerConsumption) {
        this.id = getNextId();
        this.name = name;
        this.type = type;
        this.price = price;
        this.manufacturer = manufacturer;
        this.performancePoints = performancePoints;
        this.powerConsumption = powerConsumption;
    }

    /**
     * Gives a new id.
     */
    public static int getNextId() {
        return nextId++;
    }

    /**
     * Resets the component id.
     */
    public static void resetIdCounter() {
        nextId = 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPerformancePoints() {
        return performancePoints;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

}
