package ee.taltech.iti0202.delivery;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Courier {
    private final List<Packet> packetList;
    private final String name;
    private final Location firstPlace;
    private Location location; // where now?
    private Strategy strategy;
    private Location target; // where to?
    private int distanceToTarget;

    public Location getFirstPlace() {
        return firstPlace;
    }

    /**
     * Constructs a new Courier with name and location.
     * @param name The name of the courier.
     * @param location The location of the courier.
     */
    public Courier(String name, Location location) {
        this.firstPlace = location;
        this.strategy = null;
        this.name = name;
        this.packetList = new LinkedList<>();
        this.location = location;
        this.target = null;
        this.distanceToTarget = 0;
    }

    /**
     * This method is to set new strategy to a Courier.
     * @param strategy The strategy to be added.
     */
    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    Strategy getStrategy() {
        return strategy;
    }

    public String getName() {
        return name;
    }

    /**
     * This method is used to add packet to courier.
     * @param packet The packet to be added.
     */
    public void addPacket(Packet packet) {
        packetList.add(packet);
    }

    public List<Packet> getPacketList() {
        return packetList;
    }

    /**
     * This method is used to get packet and remove it from the courier packets.
     * @param name The name of the packet.
     * @return Optional.of(packet) if conditions are met, otherwise Optional.empty()
     */
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

    /**
     * This method is used to set target.
     * @param target The new location.
     */
    public void setTarget(Location target) {
        if (!target.equals(location)) {
            this.target = target;
            distanceToTarget = location.getDistanceTo(target.getName());
            this.location = null;
        }
    }

    /**
     * This method is used to implement courier moving
     */
    public void move() {
        distanceToTarget--;

        if (distanceToTarget == 0) {
            this.location = target;
            this.target = null;
        }
    }

    @Override
    public String toString() {
        return "Courier{"
                + "packets=" + packetList
                + ", name='" + name + '\''
                + ", location=" + location
                + ", target=" + target
                + ", distanceToTarget=" + distanceToTarget
                + '}';
    }

}
