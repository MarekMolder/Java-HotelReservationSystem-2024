package ee.taltech.iti0202.zoo.zoo;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.animal.EAnimalType;
import ee.taltech.iti0202.zoo.animal.Lamb;
import ee.taltech.iti0202.zoo.animal.Monkey;
import ee.taltech.iti0202.zoo.animal.Turtle;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZooTest {
    Zoo zoo = new Zoo();
    Caretaker mati = new Caretaker("Mati", new ArrayList<>(Arrays.asList(EAnimalType.BIRD, EAnimalType.REPTILE)));
    Caretaker kati = new Caretaker("Kati", new ArrayList<>(List.of(EAnimalType.FISH)));

    Animal bird = new Animal("Tinker", "tsiutsiu", EAnimalType.BIRD, 3);
    Animal reptile = new Animal("Sam", "tsss", EAnimalType.REPTILE, 2);
    Animal fish = new Animal("Kalle", "mul-mul", EAnimalType.FISH, 1);
    Turtle turtle = new Turtle("Kiki", 1);
    Monkey monkey = new Monkey("Mõngel", 1);
    Lamb lamb = new Lamb("Vuulu", 1);


    @Test
    public void testZooAddAnimal() {
        zoo.addAnimal(bird);
        zoo.addAnimal(reptile);
        zoo.addAnimal(fish);

        assertEquals(3, zoo.zooAnimals.size());
        assertEquals(new ArrayList<Animal>(Arrays.asList(bird, reptile, fish)), zoo.zooAnimals);
    }

    @Test
    public void testZooAddCaretaker() {
        zoo.addCareTaker(mati);
        zoo.addCareTaker(kati);

        assertEquals(2, zoo.zooCaretakers.size());
        assertEquals(new ArrayList<Caretaker>(Arrays.asList(mati, kati)), zoo.zooCaretakers);
    }

    @Test
    public void testZooAnimalsThatNeedFeeding() {
        zoo.addAnimal(bird);
        zoo.addAnimal(reptile);
        zoo.addAnimal(fish);
        zoo.addAnimal(turtle);
        zoo.addAnimal(monkey);
        zoo.addAnimal(lamb);

        assertEquals(0, zoo.whatAnimalsNeedFeeding().size());

        zoo.nextDay();

        assertEquals(3, zoo.whatAnimalsNeedFeeding().size());
        assertEquals(new ArrayList<Animal>(Arrays.asList(fish, turtle, monkey)), zoo.whatAnimalsNeedFeeding());
    }

    @Test
    public void testZooHowAnimalsFeel() {
        zoo.addAnimal(bird);
        zoo.addAnimal(reptile);
        zoo.addAnimal(fish);
        zoo.addAnimal(turtle);
        zoo.addAnimal(lamb);

        Map<String, String> howAnimalsFeel = new LinkedHashMap<>();
        howAnimalsFeel.put("Tinker" + (EAnimalType.BIRD), "tsiutsiu");
        howAnimalsFeel.put("Sam" + (EAnimalType.REPTILE), "tsss");
        howAnimalsFeel.put("Kalle" + (EAnimalType.FISH), "mul-mul");
        howAnimalsFeel.put("Kiki" + (EAnimalType.AMPHIBIAN), "");
        howAnimalsFeel.put("Vuulu" + (EAnimalType.MAMMAL), "Mää");


        assertEquals(howAnimalsFeel, zoo.howAnimalsFeel());

        zoo.addAnimal(monkey);
        zoo.nextDay();

        Map<String, String> howAnimalsFeel2 = new LinkedHashMap<>();
        howAnimalsFeel2.put("Tinker" + (EAnimalType.BIRD), "tsiutsiu");
        howAnimalsFeel2.put("Sam" + (EAnimalType.REPTILE), "tsss");
        howAnimalsFeel2.put("Kalle" + (EAnimalType.FISH), "");
        howAnimalsFeel2.put("Kiki" + (EAnimalType.AMPHIBIAN), "");
        howAnimalsFeel2.put("Vuulu" + (EAnimalType.MAMMAL), "Mää");
        howAnimalsFeel2.put("Mõngel" + (EAnimalType.MAMMAL), "BANANA");

        assertEquals(howAnimalsFeel2, zoo.howAnimalsFeel());
    }

    @Test
    public void testZooGetMostEfficientCareTaker() {
        zoo.addAnimal(bird);
        zoo.addAnimal(reptile);
        zoo.addAnimal(fish);
        zoo.addAnimal(turtle);
        zoo.addAnimal(lamb);
        zoo.addAnimal(monkey);
        zoo.addCareTaker(kati);
        zoo.addCareTaker(mati);

        zoo.nextDay();

        assertEquals(kati, zoo.getMostEfficientCareTaker());

        for (int i = 0; i < 5; i++) {
            zoo.nextDay();
        }

        assertEquals(mati, zoo.getMostEfficientCareTaker());
    }

    @Test
    public void testZooFeedHungryAnimals() {
        zoo.addAnimal(bird);
        zoo.addAnimal(reptile);
        zoo.addAnimal(fish);
        zoo.addAnimal(turtle);
        zoo.addAnimal(lamb);
        zoo.addAnimal(monkey);
        zoo.addCareTaker(kati);
        zoo.addCareTaker(mati);

        for (int i = 0; i < 10; i++) {
            zoo.nextDay();
        }

        assertEquals(5, zoo.whatAnimalsNeedFeeding().size());

        zoo.feedHungryAnimals();

        assertEquals(2, zoo.whatAnimalsNeedFeeding().size());
    }
}
