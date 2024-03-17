package ee.taltech.iti0202.zoo.animal;

public class Turtle  extends Animal{
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
