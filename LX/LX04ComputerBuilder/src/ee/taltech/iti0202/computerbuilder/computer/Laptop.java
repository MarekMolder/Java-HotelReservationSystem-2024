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
        this.components.add(keyboard);
        this.components.add(touchpad);
        this.components.add(screen);
        this.components.add(battery);
    }

    @Override
    public int calculateTotalPerformance() {
        return super.calculateTotalPerformance() + keyboard.getPerformancePoints() + touchpad.getPerformancePoints() + screen.getPerformancePoints() + battery.getPerformancePoints();
    }

    @Override
    public int calculateTotalPower() {
        return super.calculateTotalPower() + keyboard.getPowerConsumption() + touchpad.getPowerConsumption() + screen.getPowerConsumption() + battery.getPowerConsumption();
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
