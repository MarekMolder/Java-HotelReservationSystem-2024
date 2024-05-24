package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public Computer(Component cpu, Component gpu, Component ram, Component motherboard, Component storage, Component psu, Component pcCase) {
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

    public int calculateTotalPerformance() {
        return cpu.getPerformancePoints() + gpu.getPerformancePoints() + ram.getPerformancePoints() + motherboard.getPerformancePoints() + storage.getPerformancePoints();
    }

    public int calculateTotalPower() {
        return cpu.getPowerConsumption() + gpu.getPowerConsumption() + ram.getPowerConsumption() + motherboard.getPowerConsumption() + storage.getPowerConsumption();
    }

    public BigDecimal calculateTotalPrice() {
        return cpu.getPrice()
                .add(gpu.getPrice())
                .add(ram.getPrice())
                .add(motherboard.getPrice())
                .add(storage.getPrice())
                .add(psu.getPrice())
                .add(pcCase.getPrice());
    }
    public List<Component> getComponents() {
        return components;
    }
}
