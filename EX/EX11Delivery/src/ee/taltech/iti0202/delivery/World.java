package ee.taltech.iti0202.delivery;

import java.util.*;

public class World {

    private Map<String, Location> locationMap = new HashMap<>();
    private Set<String> locationSet = new HashSet<>();
    private Map<String, Courier> couriers = new HashMap<>();

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (locationMap.containsKey(name) || !locationSet.containsAll(otherLocations)) {
            return Optional.empty();
        }
        Location location = new Location(name);
        locationMap.put(name, location);
        locationSet.add(name);
        return Optional.of(location);
    }

    public Optional<Courier> addCourier(String name, String to) {
        if (couriers.containsKey(name) || !locationMap.containsKey(to)) {
            return Optional.empty();
        }

        Courier courier = new Courier(name);
        couriers.put(name, courier);

        return Optional.of(courier);
    }

    boolean giveStrategy(String name, Strategy strategy) {
        if (!couriers.containsKey(name)) {
            return false;
        }
        couriers.get(name).setStrategy(strategy);
        return true;
    }

    void tick() {
        for (Courier courier : couriers.values()) {
            Optional<Location> currentLocation = courier.getLocation();
            if (currentLocation.isPresent()) {
                // courier at location
                Action action = courier.getStrategy().getAction();
                List<String> depositPackets = action.getDeposit();
                List<String> takePackets = action.getTake();
                Location goTo = action.getGoTo();

                // deposit
                for (String packetName : depositPackets) {
                    Optional<Packet> packetOptional = currentLocation.get().getPacket(packetName);
                    packetOptional.ifPresent(packet -> courier.addPacket(packet));
                }
                // take
                for (String packetName : takePackets) {
                    Optional<Packet> packetOptional = courier.getPacket(packetName);
                    packetOptional.ifPresent(packet -> currentLocation.get().addPacket(packet));
                }
                // setTarget
                courier.setTarget(action.getGoTo());
            } else {
                courier.move();
            }
        }
    }
}
