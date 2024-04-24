package ee.taltech.iti0202.delivery;

public class TakePackageFromOnePlaceStrategy implements Strategy {

    /**
     * Sets the courier for which this strategy is to be applied.
     * This courier will be used to determine actions based on its current location and packet list.
     * @param courier The courier whose route and package handling strategy will be managed.
     */
    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    private Courier courier;

    /**
     * Determines and returns the next action for the courier based on the current scenario and location.
     * The action depends significantly on whether the courier is at the first place or not.
     * Detailed Steps:
     * 1. If the courier is already at the first place:
     *    a. If there are packets at the first place, the courier will take the first available packet
     *       and all other packets that have the same target destination.
     *    b. If no packets are available at the first place, the courier will perform an action
     *       to stay at the first place.
     * 2. If the courier is not at the first place:
     *    a. The action will be to move the courier back to the first place.
     *    b. Along the way, if the courier currently carries any packets whose target matches the
     *       current location of the courier, those packets will be deposited.
     * 3. If none of the above conditions are met, the default action is to stay at the current location.
     * @return An {@link Action} object representing the next step the courier should take,
     *         which could involve moving to a location, taking or depositing packets.
     */
    @Override
    public Action getAction() {
        Location location = courier.getFirstPlace();
        Location target = null;
        Action action = null;

        if (courier.getLocation().get().equals(location)) {
            if (!location.getPackets().isEmpty()) {
                target = location.getPackets().entrySet().iterator().next().getValue().getTarget();
                action = new Action(target);
                for (Packet packet : location.getPackets().values()) {
                    if (packet.getTarget().equals(target)) {
                        action.addTake(packet.getName());
                    }
                }
            } else {
                action = new Action(location);
            }
        } else if (!courier.getLocation().get().equals(location)) {
            action = new Action(location);
            for (Packet packet : courier.getPacketList()) {
                if (packet.getTarget().equals(courier.getLocation().get())) {
                    action.addDeposit(packet.getName());
                }
            }
        } else {
            action = new Action(location);
        }
        return action;
    }
}
