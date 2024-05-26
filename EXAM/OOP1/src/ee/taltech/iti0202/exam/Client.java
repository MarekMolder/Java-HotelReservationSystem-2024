package ee.taltech.iti0202.exam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private BigDecimal balance;
    private int timesDrived;
    private List<Order> orders;

    public Client(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
        this.timesDrived = 0;
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
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
        this.balance= this.balance.subtract(balance);
    }

    public int getTimesDrived() {
        return timesDrived;
    }

    public void setTimesDrived() {
        this.timesDrived++;
    }
}
