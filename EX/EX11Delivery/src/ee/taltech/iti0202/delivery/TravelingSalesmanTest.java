package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

class TravelingSalesmanTest {
    public static void main(String[] args) {
        // setup

        World world = new World();

        Location tallinn = world.addLocation("Tallinn", new ArrayList<>(), new ArrayList<>()).get();
        Location tartu = world.addLocation("Tartu", List.of("Tallinn"), List.of(6)).get();
        Location parnu = world.addLocation("Parnu", List.of("Tallinn", "Tartu"), List.of(3, 2)).get();
        Location narva = world.addLocation("Narva", List.of("Tallinn", "Tartu", "Parnu"), List.of(2, 3, 6)).get();

        Packet packetTallinn1 = new Packet("tal1", tartu);
        Packet packetTallinn2 = new Packet("tal2", tartu);
        Packet packetTallinn3 = new Packet("tal3", parnu);
        Packet packetTallinn4 = new Packet("tal4", tartu);
        Packet packetTallinn5 = new Packet("tal5", narva);
        Packet packetTallinn6 = new Packet("tal6", narva);
        Packet packetTartu1 = new Packet("tartu1", tallinn);
        Packet packetTartu2 = new Packet("tartu2", tallinn);

        tallinn.addPacket(packetTallinn1);
        tallinn.addPacket(packetTallinn2);
        tallinn.addPacket(packetTallinn3);
        tallinn.addPacket(packetTallinn4);
        tallinn.addPacket(packetTallinn5);
        tallinn.addPacket(packetTallinn6);

        tartu.addPacket(packetTartu1);
        tartu.addPacket(packetTartu2);

        Courier courier1 = world.addCourier("Mati", "Tallinn").get();
        TravelingSalesman strategy = new TravelingSalesman();
        world.giveStrategy("Mati", strategy);
        strategy.setCourier(courier1);

        // progress

        assertEquals(6, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertEquals(0, parnu.getPackets().size());
        assertEquals(0, narva.getPackets().size());
        assertEquals(tallinn, courier1.getLocation().get()); // starts at Tallinn
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertEquals(0, parnu.getPackets().size());
        assertEquals(0, narva.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to narva (1 step)
        assertEquals(List.of(packetTallinn5, packetTallinn6, packetTallinn3, packetTallinn4,
                packetTallinn1, packetTallinn2), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertEquals(0, parnu.getPackets().size());
        assertEquals(0, narva.getPackets().size());
        assertEquals(narva, courier1.getLocation().get()); // at narva
        assertEquals(List.of(packetTallinn5, packetTallinn6, packetTallinn3, packetTallinn4,
                packetTallinn1, packetTallinn2), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertEquals(0, parnu.getPackets().size());
        assertEquals(2, narva.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to tartu (2 steps)
        assertEquals(List.of(packetTallinn3, packetTallinn4, packetTallinn1, packetTallinn2), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertEquals(0, parnu.getPackets().size());
        assertEquals(2, narva.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to tartu (1 step)
        assertEquals(List.of(packetTallinn3, packetTallinn4, packetTallinn1, packetTallinn2), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(2, tartu.getPackets().size());
        assertEquals(0, parnu.getPackets().size());
        assertEquals(2, narva.getPackets().size());
        assertEquals(tartu, courier1.getLocation().get()); // at tartu
        assertEquals(List.of(packetTallinn3, packetTallinn4, packetTallinn1, packetTallinn2), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(5, tartu.getPackets().size());
        assertEquals(0, parnu.getPackets().size());
        assertEquals(2, narva.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to parnu (1 step)
        assertEquals(List.of(packetTallinn3), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(5, tartu.getPackets().size());
        assertEquals(0, parnu.getPackets().size());
        assertEquals(2, narva.getPackets().size());
        assertEquals(parnu, courier1.getLocation().get()); // at parnu
        assertEquals(List.of(packetTallinn3), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(5, tartu.getPackets().size());
        assertEquals(1, parnu.getPackets().size());
        assertEquals(2, narva.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to tallinn (2 steps)
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(5, tartu.getPackets().size());
        assertEquals(1, parnu.getPackets().size());
        assertEquals(2, narva.getPackets().size());
        assertFalse(courier1.getLocation().isPresent()); // moving to tallinn (1 step)
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(5, tartu.getPackets().size());
        assertEquals(1, parnu.getPackets().size());
        assertEquals(2, narva.getPackets().size());
        assertEquals(tallinn, courier1.getLocation().get()); // at tallinn
        assertEquals(List.of(), courier1.getPacketList());

        world.tick();

        assertEquals(0, tallinn.getPackets().size());
        assertEquals(5, tartu.getPackets().size());
        assertEquals(1, parnu.getPackets().size());
        assertEquals(2, narva.getPackets().size());
        assertEquals(tallinn, courier1.getLocation().get()); // stays at tallinn and waits for more packages
        assertEquals(List.of(), courier1.getPacketList());
    }
}
