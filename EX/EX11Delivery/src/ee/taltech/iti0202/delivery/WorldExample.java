package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class WorldExample {
    public static void main(String[] args) {
        World world = new World();

        Location tallinn = world.addLocation("Tallinn", new ArrayList<>(), new ArrayList<>()).get();
        Location tartu = world.addLocation("Tartu", List.of("Tallinn"), List.of(3)).get();

        Packet packetTallinn1 = new Packet("tal1", tartu);
        Packet packetTallinn2 = new Packet("tal2", tartu);
        Packet packetTartu1 = new Packet("tartu1", tallinn);
        Packet packetTartu2 = new Packet("tartu2", tallinn);
        tallinn.addPacket(packetTallinn1);
        tallinn.addPacket(packetTallinn2);
        tartu.addPacket(packetTartu1);
        tartu.addPacket(packetTartu2);

        System.out.println(tallinn);  // Tallinn   PACKETS: tal1,tal2
        System.out.println(tartu); //Tartu   PACKETS: tartu2,tartu1
        System.out.println();

        Action actionMati1 = new Action(tartu);
        actionMati1.addTake("tal1");
        Action actionMati2 = new Action(tallinn);
        Strategy strategyMati = new DummyStrategy(List.of(actionMati1, actionMati2));

        Action actionKati1 = new Action(tallinn);
        actionKati1.addTake("tartu1");
        actionKati1.addTake("tartu2");
        Action actionKati2 = new Action(tartu);
        actionKati2.addDeposit("tartu1");
        actionKati2.addTake("tal2");
        Strategy strategyKati = new DummyStrategy(List.of(actionKati1, actionKati2));

        Courier courier1 = world.addCourier("Mati", "Tallinn").get();
        Courier courier2 = world.addCourier("Kati", "Tartu").get();
        world.giveStrategy("Mati", strategyMati);
        world.giveStrategy("Kati", strategyKati);

        System.out.println(courier1); // Mati (Tallinn). PACKETS:
        System.out.println(courier2); // Kati (Tartu). PACKETS:
        System.out.println();

        world.tick();
        System.out.println("TICK");

        System.out.println(tallinn);  // Tallinn   PACKETS: tal2
        System.out.println(tartu);    // Tartu   PACKETS:
        System.out.println(courier1); // Mati (null). PACKETS: tal1
        System.out.println(courier2); // Kati (null). PACKETS: tartu1,tartu2
        System.out.println();

        world.tick();
        System.out.println("TICK");

        System.out.println(courier1);  // same
        System.out.println(courier2);  // same
        System.out.println();

        world.tick();
        System.out.println("TICK");

        System.out.println(courier1);  // Mati (Tartu). PACKETS: tal1
        System.out.println(courier2);  // // Kati (Tallinn). PACKETS: tartu1,tartu2
        System.out.println();
        world.tick();
        System.out.println("TICK");

        System.out.println(courier1);  // Mati (null). PACKETS: tal1
        System.out.println(courier2);  // Kati (null). PACKETS: tartu2,tal2
        System.out.println();

        System.out.println(tallinn);   // Tallinn   PACKETS: tartu1
        System.out.println(tartu);     // Tartu   PACKETS:
    }
}