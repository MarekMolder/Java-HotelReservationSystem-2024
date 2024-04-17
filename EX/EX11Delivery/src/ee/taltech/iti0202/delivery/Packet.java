package ee.taltech.iti0202.delivery;

public class Packet {
    private final String name;
    private final Location target;

    Packet(String name, Location target) {
        this.name = name;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public Location getTarget() {
        return target;
    }
}
