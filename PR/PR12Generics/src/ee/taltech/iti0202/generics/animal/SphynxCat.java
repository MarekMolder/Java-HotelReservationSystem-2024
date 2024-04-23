package ee.taltech.iti0202.generics.animal;

public class SphynxCat extends Animal {

    /**
     * Represents a type of animal specifically categorized as sphynx cat.
     * This class extends the {@link Animal} class.
     */
    public SphynxCat(String name) {
        super(name);
    }

    @Override
    public String sound() {
        return "Meeeeeoooooww!";
    }
}
