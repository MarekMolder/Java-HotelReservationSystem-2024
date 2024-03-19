package ee.taltech.iti0202.zoo.caretaker;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.animal.EAnimalType;
import ee.taltech.iti0202.zoo.zoo.Zoo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaretakerTest {
    Zoo zoo = new Zoo();
    Caretaker mati = new Caretaker("Mati", new ArrayList<>(Arrays.asList(EAnimalType.MAMMAL, EAnimalType.REPTILE)));
    Caretaker kati = new Caretaker("Kati", new ArrayList<>(Arrays.asList(EAnimalType.BIRD, EAnimalType.FISH)));

    Animal bird = new Animal("Tinker", "tsiutsiu", EAnimalType.BIRD, 1);
    Animal reptile = new Animal("Sam", "tsss", EAnimalType.REPTILE, 1);
    Animal fish = new Animal("Kalle", "mul-mul", EAnimalType.FISH, 1);

    @Test
    public void testCaretakerGetName() {
        assertEquals("Mati", mati.getName());
        assertEquals("Kati", kati.getName());
    }

    @Test
    public void testCaretakerGetTask() {
        assertEquals(new ArrayList<>(Arrays.asList(EAnimalType.MAMMAL, EAnimalType.REPTILE)), mati.getTask());
        assertEquals(new ArrayList<>(Arrays.asList(EAnimalType.BIRD, EAnimalType.FISH)), kati.getTask());
    }

    @Test
    public void testCaretakersFeedAnimals() {
        zoo.addAnimal(bird);
        zoo.addAnimal(reptile);
        zoo.addAnimal(fish);

        zoo.nextDay();

        List<Animal> animalsThatNeedFeeding = new ArrayList<>();
        animalsThatNeedFeeding.add(bird);
        animalsThatNeedFeeding.add(reptile);
        animalsThatNeedFeeding.add(fish);

        mati.feedAnimals(animalsThatNeedFeeding);
        assertEquals(new ArrayList<>(Arrays.asList(bird, fish)), animalsThatNeedFeeding);

        kati.feedAnimals(animalsThatNeedFeeding);
        assertEquals(new ArrayList<>(), animalsThatNeedFeeding);
    }

    @Test
    public void testCaretakersFeedAnimalsWhenTheyAreNotHungry() {
        List<Animal> animalsThatNeedFeeding = new ArrayList<>();
        animalsThatNeedFeeding.add(bird);
        animalsThatNeedFeeding.add(reptile);
        animalsThatNeedFeeding.add(fish);

        mati.feedAnimals(animalsThatNeedFeeding);
        assertEquals(new ArrayList<>(Arrays.asList(bird, reptile, fish)), animalsThatNeedFeeding);
    }
}
