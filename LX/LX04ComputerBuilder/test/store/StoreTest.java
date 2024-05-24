package store;

import ee.taltech.iti0202.computerbuilder.Customer;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.EComputerType;
import ee.taltech.iti0202.computerbuilder.store.EUseCase;
import ee.taltech.iti0202.computerbuilder.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.CDATASection;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreTest {
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

    private Component cpu2;
    private Component gpu2;
    private Component ram2;
    private Component motherboard2;
    private Component hdd2;
    private Component psu2;
    private Component pcCase2;
    private Component keyboard2;
    private Component touchpad2;
    private Component screen2;
    private Component battery2;
    private Store store1;

    private Customer mari;
    private Customer mari2;

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



        cpu2 = new Component("cpu2", Component.Type.CPU, BigDecimal.valueOf(50), "ArvutiTark",
                100, 110);

        gpu2 = new Component("gpu2", Component.Type.GPU, BigDecimal.valueOf(50), "ArvutiTark",
                100, 250);

        ram2 = new Component("ram2", Component.Type.RAM, BigDecimal.valueOf(50), "ArvutiTark",
                100, 10);

        motherboard2 = new Component("motherboard2", Component.Type.MOTHERBOARD, BigDecimal.valueOf(50), "ArvutiTark",
                100, 0);

        hdd2 = new Component("hdd2", Component.Type.HDD, BigDecimal.valueOf(50), "ArvutiTark",
                100, 5);

        psu2 = new Component("psu2", Component.Type.PSU, BigDecimal.valueOf(50), "ArvutiTark",
                0, 500);

        pcCase2 = new Component("case2", Component.Type.CASE, BigDecimal.valueOf(50), "ArvutiTark",
                0, 0);

        keyboard2 = new Component("keyboard2", Component.Type.KEYBOARD, BigDecimal.valueOf(50), "ArvutiTark",
                100, 20);

        touchpad2 = new Component("touchpad2", Component.Type.TOUCHPAD, BigDecimal.valueOf(50), "ArvutiTark",
                100, 20);

        screen2 = new Component("screen2", Component.Type.SCREEN, BigDecimal.valueOf(50), "ArvutiTark",
                100, 20);

        battery2 = new Component("battery2", Component.Type.BATTERY, BigDecimal.valueOf(50), "ArvutiTark",
                100, 20);

        store1 = new Store("computer2", BigDecimal.valueOf(2000), BigDecimal.valueOf(1.1));

        mari = new Customer("Mari", BigDecimal.valueOf(2000));
        mari2 = new Customer("Mari2", BigDecimal.valueOf(0));
    }

    @Test
    void purchaseComponent() throws OutOfStockException, NotEnoughMoneyException, ProductNotFoundException, ProductAlreadyExistsException {
        Database.getInstance().resetEntireDatabase();
        assertThrows(ProductNotFoundException.class, () -> store1.purchaseComponent(1, mari));

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu);
        Database.getInstance().saveComponent(ram);
        Database.getInstance().saveComponent(motherboard);
        Database.getInstance().saveComponent(hdd);
        Database.getInstance().saveComponent(psu);
        Database.getInstance().saveComponent(pcCase);
        Database.getInstance().saveComponent(keyboard);
        Database.getInstance().saveComponent(touchpad);
        Database.getInstance().saveComponent(screen);
        Database.getInstance().saveComponent(battery);

        Database.getInstance().saveComponent(cpu2);
        Database.getInstance().saveComponent(gpu2);
        Database.getInstance().saveComponent(ram2);
        Database.getInstance().saveComponent(motherboard2);
        Database.getInstance().saveComponent(hdd2);
        Database.getInstance().saveComponent(psu2);
        Database.getInstance().saveComponent(pcCase2);
        Database.getInstance().saveComponent(keyboard2);
        Database.getInstance().saveComponent(touchpad2);
        Database.getInstance().saveComponent(screen2);
        Database.getInstance().saveComponent(battery2);

        assertThrows(NotEnoughMoneyException.class, () -> store1.purchaseComponent(1, mari2));

        assertEquals(cpu, store1.purchaseComponent(1, mari));

        assertThrows(OutOfStockException.class, () -> store1.purchaseComponent(1, mari));
    }

    @Test
    void orderComputer() throws ProductAlreadyExistsException, OutOfStockException, ProductNotFoundException {

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu);
        Database.getInstance().saveComponent(ram);
        Database.getInstance().saveComponent(motherboard);
        Database.getInstance().saveComponent(hdd);
        Database.getInstance().saveComponent(psu);
        Database.getInstance().saveComponent(pcCase);
        Database.getInstance().saveComponent(keyboard);
        Database.getInstance().saveComponent(touchpad);
        Database.getInstance().saveComponent(screen);
        Database.getInstance().saveComponent(battery);

        Database.getInstance().saveComponent(cpu2);
        Database.getInstance().saveComponent(gpu2);
        Database.getInstance().saveComponent(ram2);
        Database.getInstance().saveComponent(motherboard2);
        Database.getInstance().saveComponent(hdd2);
        Database.getInstance().saveComponent(psu2);
        Database.getInstance().saveComponent(pcCase2);
        Database.getInstance().saveComponent(keyboard2);
        Database.getInstance().saveComponent(touchpad2);
        Database.getInstance().saveComponent(screen2);
        Database.getInstance().saveComponent(battery2);


        assertEquals(BigDecimal.valueOf(2000), mari.getBalance());
        assertEquals(0, mari.getComputer().size());
        assertEquals(BigDecimal.valueOf(2000), store1.getBalance());

        assertTrue(store1.orderComputer(EUseCase.GAMING, EComputerType.PC, mari));

        assertEquals(BigDecimal.valueOf(1230.0), mari.getBalance());
        assertEquals(BigDecimal.valueOf(2770.0), store1.getBalance());
        assertEquals(1, mari.getComputer().size());
    }

    @Test
    void orderComputer2() throws ProductAlreadyExistsException, OutOfStockException, ProductNotFoundException {

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu);
        Database.getInstance().saveComponent(ram);
        Database.getInstance().saveComponent(motherboard);
        Database.getInstance().saveComponent(hdd);
        Database.getInstance().saveComponent(psu);
        Database.getInstance().saveComponent(pcCase);
        Database.getInstance().saveComponent(keyboard);
        Database.getInstance().saveComponent(touchpad);
        Database.getInstance().saveComponent(screen);
        Database.getInstance().saveComponent(battery);

        Database.getInstance().saveComponent(cpu2);
        Database.getInstance().saveComponent(gpu2);
        Database.getInstance().saveComponent(ram2);
        Database.getInstance().saveComponent(motherboard2);
        Database.getInstance().saveComponent(hdd2);
        Database.getInstance().saveComponent(psu2);
        Database.getInstance().saveComponent(pcCase2);
        Database.getInstance().saveComponent(keyboard2);
        Database.getInstance().saveComponent(touchpad2);
        Database.getInstance().saveComponent(screen2);
        Database.getInstance().saveComponent(battery2);

        assertEquals(BigDecimal.valueOf(2000), mari.getBalance());
        assertEquals(0, mari.getComputer().size());
        assertEquals(BigDecimal.valueOf(2000), store1.getBalance());

        assertTrue(store1.orderComputer(BigDecimal.valueOf(2000), EComputerType.PC, mari));

        assertEquals(BigDecimal.valueOf(1340.0), mari.getBalance());
        assertEquals(BigDecimal.valueOf(2660.0), store1.getBalance());
        assertEquals(1, mari.getComputer().size());
    }

    @Test
    void orderComputer3() throws ProductAlreadyExistsException, OutOfStockException, ProductNotFoundException {

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu);
        Database.getInstance().saveComponent(ram);
        Database.getInstance().saveComponent(motherboard);
        Database.getInstance().saveComponent(hdd);
        Database.getInstance().saveComponent(psu);
        Database.getInstance().saveComponent(pcCase);
        Database.getInstance().saveComponent(keyboard);
        Database.getInstance().saveComponent(touchpad);
        Database.getInstance().saveComponent(screen);
        Database.getInstance().saveComponent(battery);

        Database.getInstance().saveComponent(cpu2);
        Database.getInstance().saveComponent(gpu2);
        Database.getInstance().saveComponent(ram2);
        Database.getInstance().saveComponent(motherboard2);
        Database.getInstance().saveComponent(hdd2);
        Database.getInstance().saveComponent(psu2);
        Database.getInstance().saveComponent(pcCase2);
        Database.getInstance().saveComponent(keyboard2);
        Database.getInstance().saveComponent(touchpad2);
        Database.getInstance().saveComponent(screen2);
        Database.getInstance().saveComponent(battery2);

        assertEquals(BigDecimal.valueOf(2000), mari.getBalance());
        assertEquals(0, mari.getComputer().size());
        assertEquals(BigDecimal.valueOf(2000), store1.getBalance());

        assertTrue(store1.orderComputer(EComputerType.PC, mari));

        assertEquals(BigDecimal.valueOf(1230.0), mari.getBalance());
        assertEquals(BigDecimal.valueOf(2770.0), store1.getBalance());
        assertEquals(1, mari.getComputer().size());
    }

    @Test
    void orderComputer4() throws ProductAlreadyExistsException, OutOfStockException, ProductNotFoundException {

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu);
        Database.getInstance().saveComponent(ram);
        Database.getInstance().saveComponent(motherboard);
        Database.getInstance().saveComponent(hdd);
        Database.getInstance().saveComponent(psu);
        Database.getInstance().saveComponent(pcCase);
        Database.getInstance().saveComponent(keyboard);
        Database.getInstance().saveComponent(touchpad);
        Database.getInstance().saveComponent(screen);
        Database.getInstance().saveComponent(battery);

        Database.getInstance().saveComponent(cpu2);
        Database.getInstance().saveComponent(gpu2);
        Database.getInstance().saveComponent(ram2);
        Database.getInstance().saveComponent(motherboard2);
        Database.getInstance().saveComponent(hdd2);
        Database.getInstance().saveComponent(psu2);
        Database.getInstance().saveComponent(pcCase2);
        Database.getInstance().saveComponent(keyboard2);
        Database.getInstance().saveComponent(touchpad2);
        Database.getInstance().saveComponent(screen2);
        Database.getInstance().saveComponent(battery2);

        assertEquals(BigDecimal.valueOf(2000), mari.getBalance());
        assertEquals(0, mari.getComputer().size());
        assertEquals(BigDecimal.valueOf(2000), store1.getBalance());

        assertTrue(store1.orderComputer(BigDecimal.valueOf(500), EUseCase.GAMING, EComputerType.PC, mari));

        assertEquals(BigDecimal.valueOf(1560.0), mari.getBalance());
        assertEquals(1, mari.getComputer().size());
        assertEquals(BigDecimal.valueOf(2440.0), store1.getBalance());
    }

    @Test
    void orderComputerNotEnoughMoney() throws ProductAlreadyExistsException {

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu);
        Database.getInstance().saveComponent(ram);
        Database.getInstance().saveComponent(motherboard);
        Database.getInstance().saveComponent(hdd);
        Database.getInstance().saveComponent(psu);
        Database.getInstance().saveComponent(pcCase);
        Database.getInstance().saveComponent(keyboard);
        Database.getInstance().saveComponent(touchpad);
        Database.getInstance().saveComponent(screen);
        Database.getInstance().saveComponent(battery);

        Database.getInstance().saveComponent(cpu2);
        Database.getInstance().saveComponent(gpu2);
        Database.getInstance().saveComponent(ram2);
        Database.getInstance().saveComponent(motherboard2);
        Database.getInstance().saveComponent(hdd2);
        Database.getInstance().saveComponent(psu2);
        Database.getInstance().saveComponent(pcCase2);
        Database.getInstance().saveComponent(keyboard2);
        Database.getInstance().saveComponent(touchpad2);
        Database.getInstance().saveComponent(screen2);
        Database.getInstance().saveComponent(battery2);

        assertEquals(BigDecimal.valueOf(0), mari2.getBalance());
        assertEquals(0, mari2.getComputer().size());
        assertEquals(BigDecimal.valueOf(2000), store1.getBalance());

        assertThrows(IllegalArgumentException.class, () -> store1.orderComputer(EUseCase.GAMING, EComputerType.PC, mari2));
        assertThrows(IllegalArgumentException.class, () -> store1.orderComputer(BigDecimal.valueOf(500), EComputerType.PC, mari2));
        assertThrows(IllegalArgumentException.class, () -> store1.orderComputer(EComputerType.PC, mari2));
        assertThrows(IllegalArgumentException.class, () -> store1.orderComputer(BigDecimal.valueOf(500), EUseCase.GAMING, EComputerType.PC, mari2));

        assertEquals(BigDecimal.valueOf(0), mari2.getBalance());
        assertEquals(0, mari2.getComputer().size());
        assertEquals(BigDecimal.valueOf(2000), store1.getBalance());
    }

    @Test
    void getAvailableComponents() throws ProductAlreadyExistsException{

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu);
        Database.getInstance().saveComponent(ram);
        Database.getInstance().saveComponent(motherboard);
        Database.getInstance().saveComponent(hdd);
        Database.getInstance().saveComponent(psu);
        Database.getInstance().saveComponent(pcCase);
        Database.getInstance().saveComponent(keyboard);
        Database.getInstance().saveComponent(touchpad);
        Database.getInstance().saveComponent(screen);
        Database.getInstance().saveComponent(battery);

        Database.getInstance().saveComponent(cpu2);
        Database.getInstance().saveComponent(gpu2);
        Database.getInstance().saveComponent(ram2);
        Database.getInstance().saveComponent(motherboard2);
        Database.getInstance().saveComponent(hdd2);
        Database.getInstance().saveComponent(psu2);
        Database.getInstance().saveComponent(pcCase2);
        Database.getInstance().saveComponent(keyboard2);
        Database.getInstance().saveComponent(touchpad2);
        Database.getInstance().saveComponent(screen2);
        Database.getInstance().saveComponent(battery2);

        assertEquals(22, store1.getAvailableComponents().size());
    }

    @Test
    void getComponentsSortedByAmount() throws ProductAlreadyExistsException, ProductNotFoundException {

        Database.getInstance().resetEntireDatabase();

        Component.resetIdCounter();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().increaseComponentStock(45,5);

        Database.getInstance().saveComponent(gpu);
        Database.getInstance().increaseComponentStock(46, 1);

        Database.getInstance().saveComponent(ram);
        Database.getInstance().increaseComponentStock(47, 3);

        assertEquals(new LinkedList<>(List.of(gpu, ram, cpu)), store1.getComponentsSortedByAmount());
    }

    @Test
    void getComponentsSortedByName() throws ProductAlreadyExistsException {

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu);
        Database.getInstance().saveComponent(ram);

        assertEquals(new LinkedList<>(List.of(cpu, gpu, ram)), store1.getComponentsSortedByName());
    }

    @Test
    void getComponentsSortedByPrice() throws ProductAlreadyExistsException {

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu2);
        Database.getInstance().saveComponent(ram);

        assertEquals(new LinkedList<>(List.of(gpu2, cpu, ram)), store1.getComponentsSortedByPrice());
    }

    @Test
    void filterByType() throws ProductAlreadyExistsException {

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu2);
        Database.getInstance().saveComponent(ram);
        Database.getInstance().saveComponent(cpu2);
        Database.getInstance().saveComponent(motherboard);

        assertEquals(new LinkedList<>(List.of(cpu2, cpu)), store1.filterByType(Component.Type.CPU));
    }

    @Test
    void getInventoryValue() throws ProductAlreadyExistsException {

        Database.getInstance().resetEntireDatabase();

        Database.getInstance().saveComponent(cpu);
        Database.getInstance().saveComponent(gpu2);
        Database.getInstance().saveComponent(ram);
        Database.getInstance().saveComponent(cpu2);
        Database.getInstance().saveComponent(motherboard);

        assertTrue(store1.getInventoryValue().compareTo(BigDecimal.valueOf(440.0)) >= 0);
    }

    @Test
    void getName() {
        assertEquals("computer2", store1.getName());
    }

    @Test
    void setName() {
        store1.setName("store2");
        assertEquals("store2", store1.getName());
    }

    @Test
    void getBalance() {
        assertEquals(BigDecimal.valueOf(2000), store1.getBalance());
    }

    @Test
    void setBalance() {
        store1.setBalance(BigDecimal.valueOf(2500));
        assertEquals(BigDecimal.valueOf(2500), store1.getBalance());
    }

    @Test
    void getProfitMargin() {
        assertEquals(BigDecimal.valueOf(1.1), store1.getProfitMargin());
    }

    @Test
    void setProfitMargin() {
        store1.setProfitMargin(BigDecimal.valueOf(2));
        assertEquals(BigDecimal.valueOf(2), store1.getProfitMargin());
    }

    @Test
    void setProfitMarginWrongValue() {
        assertThrows(IllegalArgumentException.class, () -> store1.setProfitMargin(BigDecimal.valueOf(0.5)));
    }
}
