import ee.taltech.iti0202.exam.bus.Bus;
import ee.taltech.iti0202.exam.client.Client;
import ee.taltech.iti0202.exam.client.Ticket;
import ee.taltech.iti0202.exam.company.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    private Client client;
    private Bus bus;
    private Company company;

    @BeforeEach
    void setUp() {
        client = new Client("Mari", 5, 25.0);
        bus = new Bus("Tallinn", "Tartu", 5, 10.0);
        company = new Company();
    }

    @Test
    void getName() {
        assertEquals("Mari", client.getName());
    }

    @Test
    void getAge() {
        assertEquals(5, client.getAge());
    }

    @Test
    void getBalanceAndSetBalance() {
        assertEquals(25.0, client.getBalance());

        client.setBalance(10.0);

        assertEquals(15.0, client.getBalance());
    }

    @Test
    void getTicketsAndAddTickets() {
        Ticket ticket = new Ticket(company, bus, 10.0, client, bus.getStartPoint(), bus.getDestination());
        assertEquals(0, client.getTickets().size());

        client.addTicket(ticket);

        assertEquals(1, client.getTickets().size());
    }

    @Test
    void getCompaniesAndIncreaseCompany() {
        assertEquals(0, client.getCompanies().size());

        client.increaseCompany(company);

        assertEquals(1, client.getCompanies().size());
        assertEquals(Map.of(company, 1), client.getCompanies());

        client.increaseCompany(company);
        assertEquals(1, client.getCompanies().size());
        assertEquals(Map.of(company, 2), client.getCompanies());
    }
}
