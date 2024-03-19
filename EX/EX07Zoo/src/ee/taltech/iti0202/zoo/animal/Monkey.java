package ee.taltech.iti0202.zoo.animal;

import java.util.Random;

public class Monkey extends Animal {

    /**
     * Constructs a new Monkey with the specified name and hungry.
     * @param name
     * @param hungry
     */
    public Monkey(String name, Integer hungry) {
        super(name, hungry);
    }

    @Override
    public String getVoice() {
        if (getHungry() > 0) {
            String[] voices = {"uuh", "ääh"};
            Random random = new Random();
            String randomVoice = voices[random.nextInt(voices.length)];
            return randomVoice;
        }
        return getHungryVoice();
    }

    @Override
    public EAnimalType getType() {
        return EAnimalType.MAMMAL;
    }

    @Override
    public String getHungryVoice() {
        return "BANANA";
    }

}
