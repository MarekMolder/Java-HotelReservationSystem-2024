package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Courier {
    private List<Packet> packetList;
    private final String name;
    private Location location; // where now?
    private Strategy strategy;
    private Location target; // where to?

    private int distanceToTarget;

    public Courier(String name) {
        this.strategy = null;
        this.name = name;
        this.packetList = new ArrayList<>();
        this.location = null;
        this.target = null;
        this.distanceToTarget = 0;
    }

    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    Strategy getStrategy() {
        return strategy;
    }

    public String getName() {
        return name;
    }

    public void addPacket(Packet packet) {
        packetList.add(packet);
    }

    public List<Packet> getPacketList() {
        return packetList;
    }


    public Optional<Packet> getPacket(String name) {
        for (Packet packet : packetList) {
            if (packet.getName().equals(name)) {
                packetList.remove(packet);
                return Optional.of(packet);
            }
        }
        return Optional.empty();
    }

    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    public void setTarget(Location location) {
        this.target = location;
        distanceToTarget = location.getDistanceTo(location.getName());
    }

    public void move() {
        distanceToTarget--;

        if (distanceToTarget == 0) {
            this.location = target;
            this.target = null;
        }
    }

    @Override
    public String toString() {
        return "Courier{" +
                "packets=" + packetList +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", target=" + target +
                ", distanceToTarget=" + distanceToTarget +
                '}';
    }

}
