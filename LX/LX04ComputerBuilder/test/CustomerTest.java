import ee.taltech.iti0202.computerbuilder.Customer;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Pc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    private Customer mari;
    private Component cpu;
    private Component gpu;
    private Component ram;
    private Component motherboard;
    private Component hdd;
    private Component psu;
    private Component pcCase;

    @BeforeEach
    void setUp() {
        mari = new Customer("Mari", BigDecimal.valueOf(2000));

        cpu = new Component("cpu", Component.Type.CPU, BigDecimal.valueOf(100), "ArvutiTark",
                300, 110);

        gpu = new Component("gpu", Component.Type.GPU, BigDecimal.valueOf(100), "ArvutiTark",
                300, 250);

        ram = new Component("ram", Component.Type.RAM, BigDecimal.valueOf(100), "ArvutiTark",
                300, 10);

        motherboard = new Component("motherboard",
                Component.Type.MOTHERBOARD, BigDecimal.valueOf(100), "ArvutiTark",
                300, 0);

        hdd = new Component("hdd", Component.Type.HDD, BigDecimal.valueOf(100), "ArvutiTark",
                300, 5);

        psu = new Component("psu", Component.Type.PSU, BigDecimal.valueOf(100), "ArvutiTark",
                0, 1000);

        pcCase = new Component("case", Component.Type.CASE, BigDecimal.valueOf(100), "ArvutiTark",
                0, 0);
    }

    @Test
    void getComputer() {
        Pc computer = new Pc(cpu, gpu, ram, motherboard, hdd, psu, pcCase);
        mari.addComputer(computer);
        assertEquals(List.of(computer),mari.getComputer());
    }

    @Test
    void getName() {
        assertEquals("Mari", mari.getName());
    }

    @Test
    void setName() {
        mari.setName("Mari2");
        assertEquals("Mari2", mari.getName());
    }

    @Test
    void getBalance() {
        assertEquals(BigDecimal.valueOf(2000), mari.getBalance());
    }

    @Test
    void setBalance() {
        mari.setBalance(BigDecimal.valueOf(2600));
        assertEquals(BigDecimal.valueOf(2600), mari.getBalance());
    }
}
