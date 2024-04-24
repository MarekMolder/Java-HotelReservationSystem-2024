package ee.taltech.iti0202.delivery;

import java.util.List;
import java.util.Objects;

public class TravelingSalesmanLongDistance implements Strategy {

    /**
     * Sets the courier for which the strategy should be computed.
     * @param courier the courier to be set
     */
    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    private Courier courier;

    /**
     * Determines the next action for the courier based on the current location,
     * the destination of packets, and their priorities by distance.
     * The method follows these steps:
     * 1. If the courier's current location has packets to be picked up,
     *    it plans the action to take the farthest packet first.
     * @return an {@link Action} object representing the next move the courier should take,
     *         including taking or depositing packets as required.
     */
    @Override
    public Action getAction() {
        Location location = courier.getFirstPlace();
        Location target = null;
        Action action = null;

        if (!location.getPackets().isEmpty()) {
            List<Packet> sortedPackets = location.getPacketsSortedByDistance();
            sortedPackets = sortedPackets.reversed();

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
                    } else if (!packet.getTarget().equals(location)
                            && courier.getLocation().get().getDistanceTo(packet.getTarget().getName())
                            > courier.getLocation().get().getDistanceTo(target.getName())) {
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
