import ee.taltech.iti0202.delivery.Courier;
import ee.taltech.iti0202.delivery.Location;
import ee.taltech.iti0202.delivery.Packet;
import ee.taltech.iti0202.delivery.TakePackageFromOnePlaceStrategy;
import ee.taltech.iti0202.delivery.World;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

class TakePackageFromOnePlaceTest {
    public static void main(String[] args) {
        // setup
        World world = new World();

        Location tallinn = world.addLocation("Tallinn", new ArrayList<>(), new ArrayList<>()).get();
        Location tartu = world.addLocation("Tartu", List.of("Tallinn"), List.of(3)).get();
        Location parnu = world.addLocation("Parnu", List.of("Tallinn", "Parnu"), List.of(3, 5)).get();

        Packet packetTallinn1 = new Packet("tal1", tartu);
        Packet packetTallinn2 = new Packet("tal2", tartu);
        Packet packetTallinn3 = new Packet("tal3", parnu);
        Packet packetTartu1 = new Packet("tartu1", tallinn);
        Packet packetTartu2 = new Packet("tartu2", tallinn);
        tallinn.addPacket(packetTallinn1);
        tallinn.addPacket(packetTallinn2);
        tallinn.addPacket(packetTallinn3);
        tartu.addPacket(packetTartu1);
        tartu.addPacket(packetTartu2);

        Courier courier1 = world.addCourier("Mati", "Tallinn").get();
        TakePackageFromOnePlaceStrategy strategy = new TakePackageFromOnePlaceStrategy();
        world.giveStrategy("Mati", strategy);
        strategy.setCourier(courier1);


        // Progress

        assertEquals(3, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertEquals(tallinn, courier1.getLocation().get()); // starts at Tallinn
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();

        assertEquals(2, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to parnu (2 steps)
        assertEquals(List.of(packetTallinn3), courier1.getPacketList());

        world.tick();

        assertEquals(2, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to parnu (1 step)
        assertEquals(List.of(packetTallinn3), courier1.getPacketList());

        world.tick();

        assertEquals(2, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertEquals(parnu, courier1.getLocation().get()); // at parnu
        assertEquals(List.of(packetTallinn3), courier1.getPacketList());

        world.tick();

        assertEquals(2, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to tallinn (2 steps)
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();
        assertEquals(2, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to tallinn (1 step)
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();
        assertEquals(2, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertEquals(tallinn, courier1.getLocation().get()); // at tallinn
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to tartu (2 steps)
        assertEquals(List.of(packetTallinn1, packetTallinn2), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to tartu (1 step)
        assertEquals(List.of(packetTallinn1, packetTallinn2), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertEquals(tartu, courier1.getLocation().get()); // at tartu
        assertEquals(List.of(packetTallinn1, packetTallinn2), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(4, tartu.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to tallinn (2 steps)
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(4, tartu.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to tallinn (1 step)
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(4, tartu.getPackets().size());
        assertEquals(tallinn, courier1.getLocation().get()); // at tallinn
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(4, tartu.getPackets().size());
        assertEquals(tallinn, courier1.getLocation().get()); // stays at tallinn and waits for more packages
        assertEquals(List.of(), courier1.getPacketList());
    }
}
