package ee.taltech.iti0202.zoo.animal;

public class Lamb extends Animal {

    /**
     * Constructs a new Lamb with the specified name and hungry.
     * @param name
     * @param hungry
     */
    public Lamb(String name, Integer hungry) {
        super(name, hungry);
    }

    @Override
    public String getVoice() {
        return "Mää";
    }

    @Override
    public EAnimalType getType() {
        return EAnimalType.MAMMAL;
    }
}
