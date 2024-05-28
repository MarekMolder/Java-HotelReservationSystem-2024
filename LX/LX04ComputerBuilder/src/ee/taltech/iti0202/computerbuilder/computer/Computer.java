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

    protected Computer(Builder<?> builder) {
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.ram = builder.ram;
        this.motherboard = builder.motherboard;
        this.storage = builder.storage;
        this.psu = builder.psu;
        this.pcCase = builder.pcCase;
        this.components = new LinkedList<>();
        this.components.add(cpu);
        this.components.add(gpu);
        this.components.add(ram);
        this.components.add(motherboard);
        this.components.add(storage);
        this.components.add(psu);
        this.components.add(pcCase);
    }

    public static abstract class Builder<T extends Builder<T>> {
        private Component cpu;
        private Component gpu;
        private Component ram;
        private Component motherboard;
        private Component storage;
        private Component psu;
        private Component pcCase;

        public T setCpu(Component cpu) {
            this.cpu = cpu;
            return self();
        }

        public T setGpu(Component gpu) {
            this.gpu = gpu;
            return self();
        }

        public T setRam(Component ram) {
            this.ram = ram;
            return self();
        }

        public T setMotherboard(Component motherboard) {
            this.motherboard = motherboard;
            return self();
        }

        public T setStorage(Component storage) {
            this.storage = storage;
            return self();
        }

        public T setPsu(Component psu) {
            this.psu = psu;
            return self();
        }

        public T setPcCase(Component pcCase) {
            this.pcCase = pcCase;
            return self();
        }

        protected abstract T self();

        public abstract Computer build();
    }

    public int calculateTotalPerformance() {
        return cpu.getPerformancePoints() + gpu.getPerformancePoints()
                + ram.getPerformancePoints() + motherboard.getPerformancePoints() + storage.getPerformancePoints();
    }

    public int calculateTotalPower() {
        return cpu.getPowerConsumption() + gpu.getPowerConsumption()
                + ram.getPowerConsumption() + motherboard.getPowerConsumption() + storage.getPowerConsumption();
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
