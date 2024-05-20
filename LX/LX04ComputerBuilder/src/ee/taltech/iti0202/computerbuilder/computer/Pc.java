package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;

public class Pc extends Computer {
    public Pc(Component cpu, Component gpu, Component ram, Component motherboard, Component storage, Component psu, Component pcCase) {
        super(cpu, gpu, ram, motherboard, storage, psu, pcCase);
    }
}
