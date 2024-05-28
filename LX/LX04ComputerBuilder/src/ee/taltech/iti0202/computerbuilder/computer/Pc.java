package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;

public class Pc extends Computer {

    private Pc(PcBuilder builder) {
        super(builder);
    }

    public static class PcBuilder extends Builder<PcBuilder> {
        @Override
        protected PcBuilder self() {
            return this;
        }

        @Override
        public Pc build() {
            return new Pc(this);
        }
    }
}
