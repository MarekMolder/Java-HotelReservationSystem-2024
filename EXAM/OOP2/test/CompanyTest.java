import ee.taltech.iti0202.exam.bus.Bus;
import ee.taltech.iti0202.exam.client.Client;
import ee.taltech.iti0202.exam.company.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompanyTest {
    private Bus bus1;
    private Bus bus2;
    private Bus bus3;
    private Client client1;
    private Client client2;
    private Client client3;
    @BeforeEach
    void setUp() {
        bus1 = new Bus("Tallinn", "Tartu", 5, 10.0);
        bus2 = new Bus("Tartu", "Tallinn", 5, 10.0);
        bus3 = new Bus("Tallinn", "Narva", 5, 10.0);

        client1 = new Client("Mari", 5, 25.0);
        client2 = new Client("Mari", 7, 25.0);
        client3 = new Client("Mari", 67, 0);
    }

    @Test
    void getClientsAndAddClients() {
        Company company = new Company();
        assertEquals(0, company.getClients().size());

        company.addClients(client1);

        assertEquals(1, company.getClients().size());
    }

    @Test
    void getBusesAndAddBusesAndIncreaseBusPopularity() {
        Company company = new Company();
        assertEquals(0, company.getBuses().size());

        company.addBus(bus1);

        assertEquals(1, company.getBuses().size());
        assertEquals(Map.of(bus1, 0), company.getBuses());

        company.increaseBusPopularity(bus1);
        assertEquals(1, company.getBuses().size());
        assertEquals(Map.of(bus1, 1), company.getBuses());
    }

    @Test
    void findBusesForStartPointAndDestination() {
        Company company = new Company();

        company.addBus(bus1);
        company.addBus(bus2);
        company.addBus(bus3);

        assertEquals(Map.of(), company.findBusesForStartPointAndDestination("Tallinn", "Pärnu"));
        assertEquals(Map.of(bus1, bus1.getPrice()), company.findBusesForStartPointAndDestination("Tallinn", "Tartu"));
    }

    @Test
    void sortPopularBuses() {
        Company company = new Company();

        company.addBus(bus1);
        company.addBus(bus2);
        company.addBus(bus3);

        company.increaseBusPopularity(bus1);
        company.increaseBusPopularity(bus1);

        company.increaseBusPopularity(bus2);
        company.increaseBusPopularity(bus2);
        company.increaseBusPopularity(bus2);

        company.increaseBusPopularity(bus3);

        assertEquals(new LinkedList<>(List.of(bus2, bus1, bus3)), company.sortPopularBuses());
    }

    @Test
    void sellTicketNoBusNoTicket() {
        Company company = new Company();

        assertFalse(company.sellTicket("Tallinn", "Tartu", client1));
    }

    @Test
    void sellTicketNoSuitableBusNoTicket() {
        Company company = new Company();

        company.addBus(bus1);

        assertFalse(company.sellTicket("Tallinn", "Pärnu", client1));
    }

    @Test
    void clientIsUnder6GetsFreeRide() {
        Company company = new Company();

        company.addBus(bus1);

        assertEquals(25.0, client1.getBalance());

        assertTrue(company.sellTicket("Tallinn", "Tartu", client1));

        assertEquals(25.0, client1.getBalance());
        assertEquals(1, client1.getTickets().size());
    }

    @Test
    void clientIsUnder6ButSeatsAreTakenSoNoRide() {
        Company company = new Company();

        company.addBus(bus1);
        bus1.takeSeat();
        bus1.takeSeat();
        bus1.takeSeat();
        bus1.takeSeat();
        bus1.takeSeat();

        assertEquals(25.0, client1.getBalance());

        assertFalse(company.sellTicket("Tallinn", "Tartu", client1));

        assertEquals(25.0, client1.getBalance());
        assertEquals(0, client1.getTickets().size());
    }

    @Test
    void clientGet10Discount() {
        Company company = new Company();

        company.addBus(bus1);

        assertEquals(25.0, client2.getBalance());

        assertTrue(company.sellTicket("Tallinn", "Tartu", client2));

        assertEquals(16.0, client2.getBalance());
        // 25 - 10 * 0.1 (9) = 16
        assertEquals(1, client2.getTickets().size());
    }

    @Test
    void clientGet35Discount() {
        Company company = new Company();

        company.addBus(bus1);

        client2.increaseCompany(company);
        client2.increaseCompany(company);
        client2.increaseCompany(company);

        assertEquals(25.0, client2.getBalance());

        assertTrue(company.sellTicket("Tallinn", "Tartu", client2));

        assertEquals(18.5, client2.getBalance());
        // 25 - 10 * 0.35 (6.5) = 18.5
        assertEquals(1, client2.getTickets().size());
    }

    @Test
    void clientDoesNotHaveEnoughMoneySoNoRide() {
        Company company = new Company();

        company.addBus(bus1);

        assertEquals(0, client3.getBalance());

        assertFalse(company.sellTicket("Tallinn", "Tartu", client3));

        assertEquals(0, client3.getBalance());
        assertEquals(0, client2.getTickets().size());
    }

    @Test
    void checkIfAllFieldsAreCorrect() {
        Company company = new Company();

        company.addBus(bus1);

        assertEquals(0, bus1.getSeatsTaken());
        assertEquals(25, client2.getBalance());

        assertTrue(company.sellTicket("Tallinn", "Tartu", client2));

        assertEquals(1, company.getHistory().size());
        assertEquals(Map.of(bus1, 1), company.getBuses());
        assertEquals(List.of(client2), company.getClients());
        assertEquals(Map.of(company, 1), client2.getCompanies());
        assertEquals(1, client2.getTickets().size());
        assertEquals(1, bus1.getSeatsTaken());
    }
}
