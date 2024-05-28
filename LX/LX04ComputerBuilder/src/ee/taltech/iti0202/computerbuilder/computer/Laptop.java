package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;
import java.math.BigDecimal;

public class Laptop extends Computer {
    Component keyboard;
    Component touchpad;
    Component screen;
    Component battery;

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

    public static class LaptopBuilder extends Builder<LaptopBuilder> {
        private Component keyboard;
        private Component touchpad;
        private Component screen;
        private Component battery;

        public LaptopBuilder setKeyboard(Component keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public LaptopBuilder setTouchpad(Component touchpad) {
            this.touchpad = touchpad;
            return this;
        }

        public LaptopBuilder setScreen(Component screen) {
            this.screen = screen;
            return this;
        }

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
