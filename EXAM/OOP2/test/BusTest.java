import ee.taltech.iti0202.exam.bus.Bus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusTest {
    private Bus bus;

    @BeforeEach
    void setUp() {
        bus = new Bus("Tallinn", "Tartu", 5, 10.0);
    }

    @Test
    void getStartPoint() {
        assertEquals("Tallinn", bus.getStartPoint());
    }

    @Test
    void getDestination() {
        assertEquals("Tartu", bus.getDestination());
    }

    @Test
    void getMaxSeats() {
        assertEquals(5, bus.getMaxSeats());
    }

    @Test
    void getPrice() {
        assertEquals(10.0, bus.getPrice());
    }

    @Test
    void takeSeatAndGetSeatsTaken() {
        assertEquals(0, bus.getSeatsTaken());

        bus.takeSeat();
        bus.takeSeat();
        bus.takeSeat();

        assertEquals(3, bus.getSeatsTaken());
    }

    @Test
    void seatsAreFullCantTakeSeatAnymore() {
        assertEquals(0, bus.getSeatsTaken());

        bus.takeSeat();
        bus.takeSeat();
        bus.takeSeat();
        bus.takeSeat();
        bus.takeSeat();

        assertEquals(5, bus.getSeatsTaken());

        bus.takeSeat();

        assertEquals(5, bus.getSeatsTaken());
    }

}
