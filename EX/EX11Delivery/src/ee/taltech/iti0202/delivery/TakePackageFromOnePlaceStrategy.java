package ee.taltech.iti0202.delivery;

public class TakePackageFromOnePlaceStrategy implements Strategy{

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    private Courier courier;

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
                    action.addTake(packet.getName());
                }
            } else {
                action = new Action(location);
            }
        } else if (!courier.getLocation().get().equals(location)) {
            action = new Action(location);
            for (Packet packet : location.getPackets().values()) {
                if (packet.getTarget().equals(courier.getLocation().get())) {
                    action.addTake(packet.getName());
                }
            }
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
