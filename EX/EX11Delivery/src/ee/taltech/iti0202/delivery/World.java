package ee.taltech.iti0202.delivery;

import java.util.*;

public class World {

    private Map<String, Location> locationMap = new HashMap<>();
    private Set<String> locationSet = new HashSet<>();
    private Map<String, Courier> couriers = new HashMap<>();

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (locationMap.containsKey(name) || otherLocations.size() != distances.size()) {
            return Optional.empty();
        }

        for (String otherLocation : otherLocations) {
            if (locationSet.contains(otherLocation)) {}
        }

        Location location = new Location(name);
        locationMap.put(name, location);
        locationSet.add(name);

        for (int i = 0; i < otherLocations.size(); i++) {
            String otherLocationName = otherLocations.get(i);
            if (locationMap.containsKey(otherLocationName)) {
                int distance = distances.get(i);
                location.addDistance(otherLocationName, distance);
                locationMap.get(otherLocationName).addDistance(name, distance);
            } else {
                locationMap.remove(name);
                return Optional.empty();
            }
        }

        return Optional.of(location);

    }

    public Optional<Courier> addCourier(String name, String to) {
        if (couriers.containsKey(name) || !locationMap.containsKey(to)) {
            return Optional.empty();
        }

        Courier courier = new Courier(name, locationMap.get(to));
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
                    Optional<Packet> packetOptional = courier.getPacket(packetName);
                    packetOptional.ifPresent(packet -> currentLocation.get().addPacket(packet));
                }
                // take
                for (String packetName : takePackets) {
                    Optional<Packet> packetOptional = currentLocation.get().getPacket(packetName);
                    packetOptional.ifPresent(packet -> courier.addPacket(packet));
                }
                // setTarget
                courier.setTarget(goTo);
                courier.move();
            } else {
                courier.move();
            }
        }
    }

}
