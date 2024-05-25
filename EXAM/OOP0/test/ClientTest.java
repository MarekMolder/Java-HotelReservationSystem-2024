import ee.taltech.iti0202.exam.Cinema;
import ee.taltech.iti0202.exam.Client;
import ee.taltech.iti0202.exam.EType;
import ee.taltech.iti0202.exam.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    private Client client;
    private Cinema cinema;

    @BeforeEach
    void setUp() {
        client = new Client("Mari", 20, BigDecimal.valueOf(1000));
        cinema = new Cinema();
    }

    @Test
    void getName() {
        assertEquals("Mari", client.getName());
    }

    @Test
    void getAge() {
        assertEquals(20, client.getAge());
    }

    @Test
    void getBalance() {
        assertEquals(BigDecimal.valueOf(1000), client.getBalance());
    }

    @Test
    void setBalance() {
        client.setBalance(BigDecimal.valueOf(20));
        assertEquals(BigDecimal.valueOf(980), client.getBalance());
    }

    @Test
    void getVisits() {
        client.addVisit(cinema);
        assertEquals(Map.of(cinema, 1), client.getVisits());

        client.addVisit(cinema);
        client.addVisit(cinema);
        client.addVisit(cinema);
        client.addVisit(cinema);

        assertEquals(Map.of(cinema, 5), client.getVisits());
    }
}
