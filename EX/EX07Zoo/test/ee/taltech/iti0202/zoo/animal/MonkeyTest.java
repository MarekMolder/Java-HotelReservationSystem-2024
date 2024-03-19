package ee.taltech.iti0202.zoo.animal;

import ee.taltech.iti0202.zoo.zoo.Zoo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonkeyTest {
    Zoo zoo1 = new Zoo();

    @Test
    public void testMonkeyTypeMammal() {
        Monkey monkey = new Monkey("Mõngel", 5);

        assertEquals(EAnimalType.MAMMAL, monkey.getType());
    }

    @Test
    public void testMonkeyMakesSometimesUuhSometimesAahSound() {
        Monkey monkey = new Monkey("Mõngel", 5);

        String voice = monkey.getVoice();

        assertTrue(voice.equals("uuh") || voice.equals("ääh"));

        List<String> monkeySounds = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            monkeySounds.add(monkey.getVoice());
        }
        System.out.println(monkeySounds);
        Set<String> monkeySoundSet = new HashSet<>(monkeySounds);

        assertTrue(monkeySounds.contains("ääh"));
        assertTrue(monkeySounds.contains("uuh"));
        assertEquals(2, monkeySoundSet.size());
    }

    @Test
    public void testMonkeyMakesBananaSoundWhenHungry() {
        Monkey monkey = new Monkey("Mõngel", 1);
        zoo1.addAnimal(monkey);

        zoo1.nextDay();

        assertEquals("BANANA", monkey.getVoice());
    }
}