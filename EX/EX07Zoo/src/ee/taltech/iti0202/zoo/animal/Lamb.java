package ee.taltech.iti0202.zoo.animal;

public class Lamb extends Animal {

    public Lamb(String name) {
        super(name);
    }

    @Override
    public String getVoice() {
        return "Mää";
    }

    @Override
    public EAnimalType getType() {
        return EAnimalType.MAMMAL;
    }

    @Override
    public Integer getHungry() {
        return 1;
    }

}
