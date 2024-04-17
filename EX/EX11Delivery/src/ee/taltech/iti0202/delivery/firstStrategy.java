package ee.taltech.iti0202.delivery;

public class firstStrategy implements Strategy{

    private final World world;
    private Courier courier;

    public firstStrategy(World world) {
        this.world = world;
    }

    @Override
    public Action getAction() {
        Location location = courier.getLocation().get();
        Location target = null;
        Action action = null;
        if (location.getPackets().size() > 0) {
            target = location.getPackets().getFirst().getTarget();
            action = new Action(target);
            for (Packet packet : courier.getPacketList()) {
                if (packet.getTarget().equals(courier.getLocation())) {
                    action.addDeposit(packet.getName());
                }
            }
            for (Packet packet : location.getPackets()) {
                if (packet.getTarget().equals(target)) {
                    action.addTake(packet.getName());
                }
            }
        } else {
           action = new Action(courier.getLocation().get());
        }
        return action;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
}
