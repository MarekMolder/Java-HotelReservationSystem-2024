package ee.taltech.iti0202.delivery;

import java.util.List;
import java.util.Objects;

public class TravelingSalesman implements Strategy {

    /**
     * Sets the courier to which this strategy applies.
     * This allows the strategy to access the courier's current location and packet list.
     * @param courier The courier whose actions are to be determined by this strategy.
     */
    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    private Courier courier;

    /**
     * Determines the next action for the courier based on a set of rules
     * designed to optimize travel and delivery efficiency.
     * The action is chosen based on the courier's current location,
     * the destination of packets, and the distances involved.
     * Steps:
     * 1. If the courier is at the first place and there are packets to pick up,
     * it creates an action to take the packet with the nearest target.
     * 2. If the courier is not at the first place, it checks whether any packets at the current location
     * need to be delivered nearby; if so, it updates the target accordingly.
     * 3. If there are no packets at the courier's location, or all nearby delivery opportunities are exhausted,
     * the courier plans to move towards the location with the highest priority packet or back to the first place.
     * 4. Finally, if the courier is at the location with no pending actions,
     * it stays put by creating an action that targets the current location.
     * @return An Action object indicating the next location to which the
     * courier should move and any packets to take or deposit.
     */
    @Override
    public Action getAction() {
        Location location = courier.getFirstPlace();
        Location target = null;
        Action action = null;

        if (!location.getPackets().isEmpty()) {
            List<Packet> sortedPackets = location.getPacketsSortedByDistance();

            if (courier.getLocation().get().equals(location)) {
                if (!location.getPackets().isEmpty()) {
                    target = sortedPackets.getFirst().getTarget();
                    action = new Action(target);
                    for (Packet packet : location.getPackets().values()) {
                            action.addTake(packet.getName());
                        }
                    }
                } else {
                    action = new Action(location);
                }
            } else if (!courier.getLocation().get().equals(location)) {
                for (Packet packet : courier.getPacketList()) {
                    if (packet.getTarget().equals(courier.getLocation().get())) {
                        continue;
                    } else if (target != null) {
                        if (target.equals(location)) {
                            target = packet.getTarget();
                        } else if (!packet.getTarget().equals(location) && courier.getLocation().get().getDistanceTo(packet.getTarget().getName())
                                < courier.getLocation().get().getDistanceTo(target.getName())) {
                            target = packet.getTarget();
                        }
                    } else {
                        target = packet.getTarget();
                    }
                }
                action = new Action(Objects.requireNonNullElse(target, location));
                for (Packet pack : courier.getPacketList()) {
                        if (pack.getTarget().equals(courier.getLocation().get())) {
                            action.addDeposit(pack.getName());
                    }
                }
            } else {
                action = new Action(location);
            }
        return action;
    }
}
