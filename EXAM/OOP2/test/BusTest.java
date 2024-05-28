import ee.taltech.iti0202.exam.bus.Bus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusTest {
    private Bus bus;

    @BeforeEach
    void setUp() {
        bus = new Bus("Kiired ja Vihased", 120, EType.ACTION, Year.of(2000), BigDecimal.valueOf(10));
    }

    @Test
    void getName() {
        assertEquals("Mari", client.getName());
    }

}
