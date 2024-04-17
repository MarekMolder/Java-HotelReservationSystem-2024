package ee.taltech.iti0202.delivery;

import java.util.*;

public class Location {
    private final String name;
    private Map<String, Integer> distances = new HashMap<>();
    private final List<Packet> packets;


    public Location(String name) {
        this.name = name;
        this.packets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void addPacket(Packet packet) {
        packets.add(packet);
    }

    public Optional<Packet> getPacket(String name) {
        if (packets.contains(name)) {
            for (Packet packet : packets) {
                if (packet.getName().equals(name)) {
                    packets.remove(packet);
                    return Optional.of(packet);
                }
            }
        }

        return Optional.empty();
    }

    public List<Packet> getPackets() {
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
                ", distances=" + distances +
                ", packets=" + packets +
                '}';
    }
}
