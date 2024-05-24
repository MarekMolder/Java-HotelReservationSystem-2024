package computer;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Laptop;
import ee.taltech.iti0202.computerbuilder.computer.Pc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerTest {
    private Component cpu;
    private Component gpu;
    private Component ram;
    private Component motherboard;
    private Component hdd;
    private Component psu;
    private Component pcCase;
    private Component keyboard;
    private Component touchpad;
    private Component screen;
    private Component battery;

    @BeforeEach
    void setUp() {
        cpu = new Component("cpu", Component.Type.CPU, BigDecimal.valueOf(100), "ArvutiTark",
                200, 110);

        gpu = new Component("gpu", Component.Type.GPU, BigDecimal.valueOf(100), "ArvutiTark",
                200, 250);

        ram = new Component("ram", Component.Type.RAM, BigDecimal.valueOf(100), "ArvutiTark",
                200, 10);

        motherboard = new Component("motherboard", Component.Type.MOTHERBOARD, BigDecimal.valueOf(100), "ArvutiTark",
                200, 0);

        hdd = new Component("hdd", Component.Type.HDD, BigDecimal.valueOf(100), "ArvutiTark",
                200, 5);

        psu = new Component("psu", Component.Type.PSU, BigDecimal.valueOf(100), "ArvutiTark",
                0, 500);

        pcCase = new Component("case", Component.Type.CASE, BigDecimal.valueOf(100), "ArvutiTark",
                0, 0);

        keyboard = new Component("keyboard", Component.Type.KEYBOARD, BigDecimal.valueOf(100), "ArvutiTark",
                200, 20);

        touchpad = new Component("touchpad", Component.Type.TOUCHPAD, BigDecimal.valueOf(100), "ArvutiTark",
                200, 20);

        screen = new Component("screen", Component.Type.SCREEN, BigDecimal.valueOf(100), "ArvutiTark",
                200, 20);

        battery = new Component("battery", Component.Type.BATTERY, BigDecimal.valueOf(100), "ArvutiTark",
                200, 20);

    }

    @Test
    void calculateTotalPerformance() {
        Pc computer = new Pc(cpu, gpu, ram, motherboard, hdd, psu, pcCase);
        Laptop computer2 = new Laptop(cpu, gpu, ram, motherboard,
                hdd, psu, pcCase, keyboard, touchpad, screen, battery);

        assertEquals(1000, computer.calculateTotalPerformance());
        assertEquals(1800, computer2.calculateTotalPerformance());
    }

    @Test
    void calculateTotalPower() {
        Pc computer = new Pc(cpu, gpu, ram, motherboard, hdd, psu, pcCase);
        Laptop computer2 = new Laptop(cpu, gpu, ram, motherboard,
                hdd, psu, pcCase, keyboard, touchpad, screen, battery);

        assertEquals(375, computer.calculateTotalPower());
        assertEquals(455, computer2.calculateTotalPower());
    }

    @Test
    void calculateTotalPrice() {
        Pc computer = new Pc(cpu, gpu, ram, motherboard, hdd, psu, pcCase);
        Laptop computer2 = new Laptop(cpu, gpu, ram, motherboard,
                hdd, psu, pcCase,keyboard, touchpad, screen, battery);

        assertEquals(BigDecimal.valueOf(700), computer.calculateTotalPrice());
        assertEquals(BigDecimal.valueOf(1100), computer2.calculateTotalPrice());
    }

    @Test
    void getComponents() {
        Pc computer = new Pc(cpu, gpu, ram, motherboard, hdd, psu, pcCase);
        Laptop computer2 = new Laptop(cpu, gpu, ram, motherboard,
                hdd, psu, pcCase,keyboard, touchpad, screen, battery);

        assertEquals(List.of(cpu, gpu, ram, motherboard, hdd, psu, pcCase), computer.getComponents());
        assertEquals(List.of(cpu, gpu, ram, motherboard, hdd,
                psu, pcCase, keyboard, touchpad, screen, battery), computer2.getComponents());
    }
}
