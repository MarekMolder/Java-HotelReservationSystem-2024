package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Location {
    private final String name;
    private final Map<String, Integer> distances = new HashMap<>();
    private final Map<String, Packet> packets;

    /**
     * Constructs a new Location object.
     * @param name The name of the Location.
     */
    public Location(String name) {
        this.name = name;
        this.packets = new HashMap<>();
    }

    public Map<String, Packet> getPackets() {
        return packets;
    }

    /**
     * This method is used to get a name of the Location.
     * @return The name of the Location.
     */
    public String getName() {
        return name;
    }

    /**
     * This method is used to add packet to the location.
     * @param packet The packet that will be added in Location.
     */
    public void addPacket(Packet packet) {
        packets.put(packet.getName(), packet);
    }

    /**
     * This method is used to get specific packet and remove it from the location.
     * @param name The name of the packet
     * @return Optional.of(packet) if conditions are met, otherwise Optional.empty()
     */
    public Optional<Packet> getPacket(String name) {
        Packet packet = packets.get(name);
        if (packet != null) {
            packets.remove(name);
            return Optional.of(packet);
        }
        return Optional.empty();
    }



    /**
     * This method is used to get distance from location to target.
     * @param name The name of the target.
     * @return distance from one place to another.
     */
    public Integer getDistanceTo(String name) {
        if (!distances.containsKey(name)) {
            return Integer.MAX_VALUE;
        }
        return distances.get(name);
    }

    /**
     * This method is used to add Location and distance.
     * @param location The location.
     * @param distance The distance.
     */
    public void addDistance(String location, int distance) {
        distances.put(location, distance);
    }

    @Override
    public String toString() {
        return "Location{"
                + "name='" + name + '\''
                + ", packets=" + packets
                + '}';
    }
}
