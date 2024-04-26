package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class World {

    private final Map<String, Location> locationMap = new HashMap<>();
    private final Map<String, Courier> couriers = new HashMap<>();
    Logger logger = Logger.getLogger(World.class.getName());

    /**
     * This method is used to add location in to the world.
     * @param name The name of the location
     * @param otherLocations Other locations
     * @param distances New location and other location distances.
     * @return Optional.of(Location) if conditions are met, otherwise Optional.empty()
     */
    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (locationMap.containsKey(name) || otherLocations.size() != distances.size()
                || locationMap.size() > otherLocations.size()) {
            return Optional.empty();
        }

        Location location = new Location(name);
        locationMap.put(name, location);

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

    /**
     * This method is used to add Courier in to the world.
     * @param name The name of the courier.
     * @param to The location where courier starts.
     * @return Opional.of(courier) if conditions are met, otherwise optional.empty()
     */
    public Optional<Courier> addCourier(String name, String to) {
        if (couriers.containsKey(name) || !locationMap.containsKey(to)) {
            return Optional.empty();
        }

        Courier courier = new Courier(name, locationMap.get(to));
        couriers.put(name, courier);

        return Optional.of(courier);
    }

    /**
     * This method is used to give strategy to courier
     * @param name The name of the courier
     * @param strategy The strategy to be added.
     * @return true, if conditions are met, false otherwise
     */
    public boolean giveStrategy(String name, Strategy strategy) {
        if (!couriers.containsKey(name)) {
            return false;
        }
        couriers.get(name).setStrategy(strategy);
        return true;
    }

    /**
     * This method is used to implement new day.
     */
    public void tick() {
        for (Courier courier : couriers.values()) {
            Optional<Location> currentLocation = courier.getLocation();
            if (currentLocation.isPresent()) {
                // courier at location
                Action action = courier.getStrategy().getAction();
                List<String> depositPackets = action.getDeposit();
                List<String> takePackets = action.getTake();
                Location goTo = action.getGoTo();
                Integer steps = currentLocation.get().getDistanceTo(action.getGoTo().getName());
                logger.log(Level.INFO, "Courier destination: " + action.getGoTo());
                logger.log(Level.INFO, "how many steps to destination: " + steps);

                // deposit
                for (String packetName : depositPackets) {
                    Optional<Packet> packetOptional = courier.getPacket(packetName);
                    packetOptional.ifPresent(packet -> currentLocation.get().addPacket(packet));
                    logger.log(Level.INFO, "Courier deposits package");
                }
                // take
                for (String packetName : takePackets) {
                    Optional<Packet> packetOptional = currentLocation.get().getPacket(packetName);
                    packetOptional.ifPresent(courier::addPacket);
                    logger.log(Level.INFO, "Courier takes package");
                }
                // setTarget
                courier.setTarget(goTo);
                courier.move();
                steps--;
                logger.log(Level.INFO, "Courier starts moving");
                logger.log(Level.INFO, "Steps left to reach next destination: " + steps);
            } else {
                courier.move();
                logger.log(Level.INFO, "Courier moves");
            }
        }
    }
}
