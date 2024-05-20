package components;

import ee.taltech.iti0202.computerbuilder.components.Component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ComponentTest {
    private Component cpu;
    private Component keyboard;
    private Component keyboard2;

    @BeforeEach
    void setUp() {
        cpu = new Component("cpu", Component.Type.CPU, BigDecimal.valueOf(200), "ArvutiTark",
                300, 100);

        keyboard = new Component("keyboard", Component.Type.KEYBOARD, BigDecimal.valueOf(50), "ArvutiTark",
                0, 0);

    }

    @Test
    void getId() {
        assertNotEquals(cpu.getId(), keyboard.getId());
    }

    @Test
    void resetIdCounter() {
        Component.resetIdCounter();
        keyboard2 = new Component("keyboard2", Component.Type.KEYBOARD, BigDecimal.valueOf(50), "ArvutiTark",
                0, 0);
        assertEquals(1, keyboard2.getId());
    }

    @Test
    void getName() {
        assertEquals("cpu", cpu.getName());
        assertEquals("keyboard", keyboard.getName());
    }

    @Test
    void getType() {
        assertEquals(Component.Type.CPU, cpu.getType());
    }

    @Test
    void getPrice() {
        assertEquals(BigDecimal.valueOf(200), cpu.getPrice());
    }

    @Test
    void setPrice() {
        cpu.setPrice(BigDecimal.valueOf(50));
        assertEquals(BigDecimal.valueOf(50), cpu.getPrice());
    }

    @Test
    void getAmount() {
        assertEquals(1, cpu.getAmount());
    }

    @Test
    void setAmount() {
        cpu.setAmount(50);
        assertEquals(50, cpu.getAmount());
    }

    @Test
    void getPerformancePoints() {
        assertEquals(300, cpu.getPerformancePoints());
    }

    @Test
    void getPowerConsumptionPoints() {
        assertEquals(100, cpu.getPowerConsumption());
    }
}
