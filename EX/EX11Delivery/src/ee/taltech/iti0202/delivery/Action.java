package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private Location location;
    private final List<String> deposit = new ArrayList<>();
    private final List<String> take = new ArrayList<>();
    private final Location goTo;

    /**
     * Constructs a new Action with target.
     * @param goTo The target where courier must go to.
     */
    public Action(Location goTo) {
        this.goTo = goTo;
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
