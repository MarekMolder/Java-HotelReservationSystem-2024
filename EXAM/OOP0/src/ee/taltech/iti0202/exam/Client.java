package ee.taltech.iti0202.exam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Client {
    private String name;
    private int age;
    private BigDecimal balance;
    private Map<Cinema, Integer> visits;

    public Client(String name, int age, BigDecimal balance) {
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.visits = new HashMap<Cinema, Integer>();
    }

    public void addVisit(Cinema cinema) {
        if (!visits.containsKey(cinema)) {
            visits.put(cinema, 1);
        } else {
            visits.merge(cinema, 1, Integer::sum);
        }
    }

    public Map<Cinema, Integer> getVisits() {
        return visits;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        if (balance.compareTo(this.balance) < 0) {
            this.balance = this.balance.subtract(balance);
        } else {
            throw new IllegalArgumentException("New balance must be less than the current balance.");
        }
    }
}
