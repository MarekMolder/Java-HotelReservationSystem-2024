package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;

import java.math.BigDecimal;

public class Laptop extends Computer {
    Component keyboard;
    Component touchpad;
    Component screen;
    Component battery;

    public Laptop(Component cpu, Component gpu, Component ram, Component motherboard, Component storage, Component psu, Component pcCase,
                          Component keyboard, Component touchpad, Component screen, Component battery) {
        super(cpu, gpu, ram, motherboard, storage, psu, pcCase);
        this.keyboard = keyboard;
        this.touchpad = touchpad;
        this.screen = screen;
        this.battery = battery;
    }

    @Override
    protected int calculateTotalPerformance() {
        return super.calculateTotalPerformance() + keyboard.getPerformancePoints() + touchpad.getPerformancePoints() + screen.getPerformancePoints() + battery.getPerformancePoints();
    }

    @Override
    protected int calculateTotalPower() {
        return super.calculateTotalPower() + keyboard.getPowerConsumption() + touchpad.getPowerConsumption() + screen.getPowerConsumption() + battery.getPowerConsumption();
    }

    @Override
    protected BigDecimal calculateTotalPrice() {
        return super.calculateTotalPrice()
                .add(keyboard.getPrice())
                .add(touchpad.getPrice())
                .add(screen.getPrice())
                .add(battery.getPrice());
    }
}
