package ee.taltech.iti0202.zoo.animal;

public class Turtle  extends Animal {

    /**
     * Constructs a new Turtle with the specified name and hungry.
     * @param name
     * @param hungry
     */
    public Turtle(String name, Integer hungry) {
        super(name, hungry);
    }

    @Override
    public String getVoice() {
        return "";
    }

    @Override
    public EAnimalType getType() {
        return EAnimalType.AMPHIBIAN;
    }

}
