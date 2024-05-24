package database;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatabaseTest {
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
    void setUp() throws ProductAlreadyExistsException {
        cpu = new Component("cpu", Component.Type.CPU, BigDecimal.valueOf(100), "ArvutiTark",
                300, 110);

        gpu = new Component("gpu", Component.Type.GPU, BigDecimal.valueOf(100), "ArvutiTark",
                300, 250);

        ram = new Component("ram", Component.Type.RAM, BigDecimal.valueOf(100), "ArvutiTark",
                300, 10);

        motherboard = new Component("motherboard", Component.Type.MOTHERBOARD, BigDecimal.valueOf(100), "ArvutiTark",
                300, 0);

        hdd = new Component("hdd", Component.Type.HDD, BigDecimal.valueOf(100), "ArvutiTark",
                300, 5);

        psu = new Component("psu", Component.Type.PSU, BigDecimal.valueOf(100), "ArvutiTark",
                0, 1000);

        pcCase = new Component("case", Component.Type.CASE, BigDecimal.valueOf(100), "ArvutiTark",
                0, 0);

        keyboard = new Component("keyboard", Component.Type.KEYBOARD, BigDecimal.valueOf(100), "ArvutiTark",
                300, 20);

        touchpad = new Component("touchpad", Component.Type.TOUCHPAD, BigDecimal.valueOf(100), "ArvutiTark",
                300, 20);

        screen = new Component("screen", Component.Type.SCREEN, BigDecimal.valueOf(100), "ArvutiTark",
                300, 20);

        battery = new Component("battery", Component.Type.BATTERY, BigDecimal.valueOf(100), "ArvutiTark",
                300, 20);

    }

    @Test
    void saveComponent() throws ProductAlreadyExistsException {
        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);

        assertEquals(1, Database.getInstance().getComponents().size());
    }

    @Test
    void saveComponentProductAlreadyExists() throws ProductAlreadyExistsException {
        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        assertEquals(1, Database.getInstance().getComponents().size());

        assertThrows(ProductAlreadyExistsException.class, () -> Database.getInstance().saveComponent(cpu));
    }

    @Test
    void deleteComponent() throws ProductAlreadyExistsException, ProductNotFoundException {
        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        assertEquals(1, Database.getInstance().getComponents().size());

        Database.getInstance().deleteComponent(23);
        assertEquals(0, Database.getInstance().getComponents().size());
    }

    @Test
    void deleteComponentProductNotFound() throws ProductAlreadyExistsException, ProductNotFoundException {
        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        assertEquals(1, Database.getInstance().getComponents().size());

        Database.getInstance().deleteComponent(1);
        assertEquals(0, Database.getInstance().getComponents().size());

        assertThrows(ProductNotFoundException.class, () -> Database.getInstance().deleteComponent(1));
    }

    @Test
    void IncreaseComponentStockAndDecrease() throws ProductAlreadyExistsException,
            ProductNotFoundException, OutOfStockException {
        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        assertEquals(1, Database.getInstance().getComponents().size());

        Database.getInstance().increaseComponentStock(1, 10);
        assertEquals(11, cpu.getAmount());

        assertThrows(IllegalArgumentException.class, () ->
                Database.getInstance().increaseComponentStock(1, -5));
        assertThrows(ProductNotFoundException.class, () ->
                Database.getInstance().increaseComponentStock(2, 15));

        Database.getInstance().decreaseComponentStock(1, 5);
        assertEquals(6, cpu.getAmount());

        assertThrows(OutOfStockException.class, () -> Database.getInstance().decreaseComponentStock(1, 7));
        assertThrows(ProductNotFoundException.class, () ->
                Database.getInstance().decreaseComponentStock(2, 15));
        assertThrows(IllegalArgumentException.class, () ->
                Database.getInstance().decreaseComponentStock(1, -5));
    }

    @Test
    void getComponentsAndResetComponents() throws ProductAlreadyExistsException {
        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu);

        assertEquals(2, Database.getInstance().getComponents().size());

        Database.getInstance().resetEntireDatabase();

        assertEquals(0, Database.getInstance().getComponents().size());
    }
}
