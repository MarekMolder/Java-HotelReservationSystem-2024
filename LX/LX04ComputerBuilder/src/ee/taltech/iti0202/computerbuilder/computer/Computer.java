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
     * Constructs a new Computer.
     *
     * @param builder the computer builder
     */
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

    /**
     * Abstract builder class for creating a Computer instance.
     *
     * @param <T> the type of the builder
     */
    public static abstract class Builder<T extends Builder<T>> {

        private Component cpu;
        private Component gpu;
        private Component ram;
        private Component motherboard;
        private Component storage;
        private Component psu;
        private Component pcCase;

        /**
         * Sets the CPU component.
         *
         * @param cpu the CPU component
         * @return the computer builder
         */
        public T setCpu(Component cpu) {
            this.cpu = cpu;
            return self();
        }

        /**
         * Sets the GPU component.
         *
         * @param gpu the GPU component
         * @return the computer builder
         */
        public T setGpu(Component gpu) {
            this.gpu = gpu;
            return self();
        }

        /**
         * Sets the RAM component.
         *
         * @param ram the RAM component
         * @return the computer builder
         */
        public T setRam(Component ram) {
            this.ram = ram;
            return self();
        }

        /**
         * Sets the motherboard component.
         *
         * @param motherboard the motherboard component
         * @return the computer builder
         */
        public T setMotherboard(Component motherboard) {
            this.motherboard = motherboard;
            return self();
        }

        /**
         * Sets the storage component.
         *
         * @param storage the storage component
         * @return the computer builder
         */
        public T setStorage(Component storage) {
            this.storage = storage;
            return self();
        }

        /**
         * Sets the PSU component.
         *
         * @param psu the PSU component
         * @return the computer builder
         */
        public T setPsu(Component psu) {
            this.psu = psu;
            return self();
        }

        /**
         * Sets the PC case component.
         *
         * @param pcCase the PC case component
         * @return the computer builder
         */
        public T setPcCase(Component pcCase) {
            this.pcCase = pcCase;
            return self();
        }

        /**
         * Returns the instance of the builder.
         *
         * @return the instance of the builder
         */
        protected abstract T self();

        /**
         * Builds the computer.
         *
         * @return the computer
         */
        public abstract Computer build();
    }

    /**
     * Calculates the total performance of the computer.
     *
     * @return the total performance
     */
    public int calculateTotalPerformance() {
        return cpu.getPerformancePoints() + gpu.getPerformancePoints()
                + ram.getPerformancePoints() + motherboard.getPerformancePoints() + storage.getPerformancePoints();
    }

    /**
     * Calculates the total power consumption of the computer.
     *
     * @return the total power consumption
     */
    public int calculateTotalPower() {
        return cpu.getPowerConsumption() + gpu.getPowerConsumption()
                + ram.getPowerConsumption() + motherboard.getPowerConsumption() + storage.getPowerConsumption();
    }

    /**
     * Calculates the total price of the computer.
     *
     * @return the total price
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
     * Returns the list of components.
     *
     * @return the list of components
     */
    public List<Component> getComponents() {
        return components;
    }
}
