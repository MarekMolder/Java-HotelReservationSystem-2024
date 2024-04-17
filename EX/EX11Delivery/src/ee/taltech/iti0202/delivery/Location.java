package ee.taltech.iti0202.delivery;

import java.util.*;

public class Location {
    private final String name;
    private Map<String, Integer> distances = new HashMap<>();
    private final Map<Packet, String> packets;


    public Location(String name) {
        this.name = name;
        this.packets = new HashMap<>();
    }

    public String getName() {
        return name;
    }
    public void addPacket(Packet packet) {
        packets.put(packet, packet.getName());
    }

    public Optional<Packet> getPacket(String name) {
        for (Packet packet : packets.keySet()) {
            if (packet.getName().equals(name)) {
                packets.remove(packet);
                return Optional.of(packet);
                }
            }

        return Optional.empty();
    }

    public Map<Packet, String> getPackets() {
        return packets;
    }

    public Integer getDistanceTo(String name) {
        if (!distances.containsKey(name)) {
            return Integer.MAX_VALUE;
        }
        return distances.get(name);
    }

    public void addDistance(String location, int distance) {
        distances.put(location, distance);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", packets=" + packets +
                '}';
    }
}
