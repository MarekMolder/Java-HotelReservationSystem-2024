package ee.taltech.iti0202.exam;

import java.math.BigDecimal;

public class Car {
    private String type;
    private String model;
    private int maxGasCapacity;
    private int gasBalance;
    private int gasItTakesPerMinute;
    private BigDecimal price;

    public Car(String type, String model, int maxGasCapacity, int gasBalance, int gasItTakesPerMinute, BigDecimal price) {
        this.type = type;
        this.model = model;
        this.maxGasCapacity = maxGasCapacity;
        this.gasBalance = gasBalance;
        this.gasItTakesPerMinute = gasItTakesPerMinute;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public int getMaxGasCapacity() {
        return maxGasCapacity;
    }

    public int getGasBalance() {
        return gasBalance;
    }

    public int getGasItTakesPerMinute() {
        return gasItTakesPerMinute;
    }

    public void setGasBalance(int gasBalance) {
        this.gasBalance -= gasBalance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void giveGas() {
        this.gasBalance = maxGasCapacity;
    }
}
