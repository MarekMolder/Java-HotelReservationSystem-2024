package ee.taltech.iti0202.generics.animal;

public class PersianCat extends Animal {

    /**
     * Represents a type of animal specifically categorized as persian cat.
     * This class extends the {@link Animal} class.
     */
    public PersianCat(String name) {
        super(name);
    }

    @Override
    public String sound() {
        return "Prrr-prrr";
    }
}
