package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;
import java.math.BigDecimal;


public final class Laptop extends Computer {

    private final Component keyboard;
    private final Component touchpad;
    private final Component screen;
    private final Component battery;

    /**
     * Constructs a new Laptop.
     *
     * @param builder the laptop builder
     */
    private Laptop(LaptopBuilder builder) {
        super(builder);
        this.keyboard = builder.keyboard;
        this.touchpad = builder.touchpad;
        this.screen = builder.screen;
        this.battery = builder.battery;
        this.components.add(keyboard);
        this.components.add(touchpad);
        this.components.add(screen);
        this.components.add(battery);
    }

    /**
     * Builder class for creating a Laptop instance.
     */
    public static class LaptopBuilder extends Builder<LaptopBuilder> {
        /** The keyboard component. */
        private Component keyboard;
        /** The touchpad component. */
        private Component touchpad;
        /** The screen component. */
        private Component screen;
        /** The battery component. */
        private Component battery;

        /**
         * Sets the keyboard component.
         *
         * @param keyboard the keyboard component
         * @return the laptop builder
         */
        public LaptopBuilder setKeyboard(Component keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        /**
         * Sets the touchpad component.
         *
         * @param touchpad the touchpad component
         * @return the laptop builder
         */
        public LaptopBuilder setTouchpad(Component touchpad) {
            this.touchpad = touchpad;
            return this;
        }

        /**
         * Sets the screen component.
         *
         * @param screen the screen component
         * @return the laptop builder
         */
        public LaptopBuilder setScreen(Component screen) {
            this.screen = screen;
            return this;
        }

        /**
         * Sets the battery component.
         *
         * @param battery the battery component
         * @return the laptop builder
         */
        public LaptopBuilder setBattery(Component battery) {
            this.battery = battery;
            return this;
        }

        @Override
        protected LaptopBuilder self() {
            return this;
        }

        @Override
        public Laptop build() {
            return new Laptop(this);
        }
    }

    @Override
    public int calculateTotalPerformance() {
        return super.calculateTotalPerformance() + keyboard.getPerformancePoints()
                + touchpad.getPerformancePoints() + screen.getPerformancePoints() + battery.getPerformancePoints();
    }

    @Override
    public int calculateTotalPower() {
        return super.calculateTotalPower() + keyboard.getPowerConsumption()
                + touchpad.getPowerConsumption() + screen.getPowerConsumption() + battery.getPowerConsumption();
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return super.calculateTotalPrice()
                .add(keyboard.getPrice())
                .add(touchpad.getPrice())
                .add(screen.getPrice())
                .add(battery.getPrice());
    }
}
