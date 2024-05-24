package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public abstract class Computer {
    Component cpu;
    Component gpu;
    Component ram;
    Component motherboard;
    Component storage;
    Component psu;
    Component pcCase;
    List<Component> components;

    /**
     * Constructs a Computer with the specified components.
     *
     * @param cpu the CPU component
     * @param gpu the GPU component
     * @param ram the RAM component
     * @param motherboard the motherboard component
     * @param storage the storage component
     * @param psu the power supply unit component
     * @param pcCase the case component
     */
    public Computer(Component cpu, Component gpu, Component ram, Component motherboard,
                    Component storage, Component psu, Component pcCase) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.motherboard = motherboard;
        this.storage = storage;
        this.psu = psu;
        this.pcCase = pcCase;
        this.components = new LinkedList<>();
        this.components.add(cpu);
        this.components.add(gpu);
        this.components.add(ram);
        this.components.add(motherboard);
        this.components.add(storage);
        this.components.add(psu);
        this.components.add(pcCase);
    }

    /**
     * Calculates the total performance of the computer.
     * @return the total performance points of all components
     */
    public int calculateTotalPerformance() {
        return cpu.getPerformancePoints() + gpu.getPerformancePoints()
                + ram.getPerformancePoints() + motherboard.getPerformancePoints() + storage.getPerformancePoints();
    }

    /**
     * Calculates the total power consumption of the computer.
     * @return the total power consumption of all components
     */
    public int calculateTotalPower() {
        return cpu.getPowerConsumption() + gpu.getPowerConsumption()
                + ram.getPowerConsumption() + motherboard.getPowerConsumption() + storage.getPowerConsumption();
    }

    /**
     * Calculates the total price of the computer.
     * @return the total price of all components
     */
    public BigDecimal calculateTotalPrice() {
        return cpu.getPrice()
                .add(gpu.getPrice())
                .add(ram.getPrice())
                .add(motherboard.getPrice())
                .add(storage.getPrice())
                .add(psu.getPrice())
                .add(pcCase.getPrice());
    }

    /**
     * Returns the list of all components in the computer.
     * @return the list of components
     */
    public List<Component> getComponents() {
        return components;
    }
}
