package ee.taltech.iti0202.zoo.animal;

import java.util.Random;

public class Monkey extends Animal {
    public Monkey(String name, String voice, Integer hungry) {
        super(name, hungry);
    }

    @Override
    public String getVoice() {
        String[] voices = {"uuh", "aah"};
        Random random = new Random();
        String randomVoice = voices[random.nextInt(voices.length)];
        return randomVoice;
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
