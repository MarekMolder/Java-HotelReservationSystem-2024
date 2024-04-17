package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private Location location;
    private List<String> deposit = new ArrayList<>();
    private List<String> take = new ArrayList<>();
    private Location goTo;

    public Action(Location location) {
        this.location = location;
    }

    public List<String> getDeposit() {
        return deposit;
    }

    public List<String> getTake() {
        return take;
    }

    public Location getGoTo() {
        return goTo;
    }

    void addDeposit(String packetName) {
        deposit.add(packetName);
    }

    void addTake(String packetName) {
        take.add(packetName);
    }
}
