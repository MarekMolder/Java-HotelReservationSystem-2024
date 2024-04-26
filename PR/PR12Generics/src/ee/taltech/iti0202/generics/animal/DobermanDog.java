package ee.taltech.iti0202.generics.animal;

public class DobermanDog extends Animal {

    /**
     * Represents a type of animal specifically categorized as doberman dog.
     * This class extends the {@link Animal} class.
     */
    public DobermanDog(String name) {
        super(name);
    }

    @Override
    public String sound() {
        return "Woof!";
    }
}
