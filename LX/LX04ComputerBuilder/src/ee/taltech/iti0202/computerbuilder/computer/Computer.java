package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;

import java.math.BigDecimal;

public abstract class Computer {
    Component cpu;
    Component gpu;
    Component ram;
    Component motherboard;
    Component storage;
    Component psu;
    Component pcCase;

    int totalPerformancePoints;
    int totalPowerConsumption;
    BigDecimal totalPrice;

    public Computer(Component cpu, Component gpu, Component ram, Component motherboard, Component storage, Component psu, Component pcCase) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.motherboard = motherboard;
        this.storage = storage;
        this.psu = psu;
        this.pcCase = pcCase;

        this.totalPerformancePoints = calculateTotalPerformance();
        this.totalPowerConsumption = calculateTotalPower();
        this.totalPrice = calculateTotalPrice();
    }

    int calculateTotalPerformance() {
        return cpu.getPerformancePoints() + gpu.getPerformancePoints() + ram.getPerformancePoints() + motherboard.getPerformancePoints() + storage.getPerformancePoints();
    }

    int calculateTotalPower() {
        return cpu.getPowerConsumption() + gpu.getPowerConsumption() + ram.getPowerConsumption() + motherboard.getPowerConsumption() + storage.getPowerConsumption();
    }

    BigDecimal calculateTotalPrice() {
        return cpu.getPrice()
                .add(gpu.getPrice())
                .add(ram.getPrice())
                .add(motherboard.getPrice())
                .add(storage.getPrice())
                .add(psu.getPrice())
                .add(pcCase.getPrice());
    }

    // Getters and other methods...
}
