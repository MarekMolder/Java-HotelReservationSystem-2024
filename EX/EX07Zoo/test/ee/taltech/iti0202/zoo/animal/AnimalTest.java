package ee.taltech.iti0202.zoo.animal;

import ee.taltech.iti0202.zoo.zoo.Zoo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimalTest {
    Zoo zoo = new Zoo();
    Animal bird = new Animal("Tinker", "tsiutsiu", EAnimalType.BIRD, 5);
    Animal reptile = new Animal("Sam", "tsss", EAnimalType.REPTILE, 5);
    Animal fish = new Animal("Kalle", "mul-mul", EAnimalType.FISH, 5);

    @Test
    public void testAnimalThrowExceptionWhenHungryLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> new Animal("Sam", "tsss", EAnimalType.MAMMAL, 0));
        assertThrows(IllegalArgumentException.class, () -> new Animal("Sam", 0));
    }

    @Test
    public void testAnimalGetName() {
        assertEquals("Tinker", bird.getName());
        assertEquals("Sam", reptile.getName());
        assertEquals("Kalle", fish.getName());
    }

    @Test
    public void testAnimalGetVoice() {
        assertEquals("tsiutsiu", bird.getVoice());
        assertEquals("tsss", reptile.getVoice());
        assertEquals("mul-mul", fish.getVoice());
    }

    @Test
    public void testAnimalGetHungryVoice() {
        assertEquals("", bird.getHungryVoice());
        assertEquals("", reptile.getHungryVoice());
        assertEquals("", fish.getHungryVoice());
    }

    @Test
    public void testAnimalGetType() {
        assertEquals(EAnimalType.BIRD, bird.getType());
        assertEquals(EAnimalType.REPTILE, reptile.getType());
        assertEquals(EAnimalType.FISH, fish.getType());
    }

    @Test
    public void testAnimalGetHungry() {
        assertEquals(5, bird.getHungry());
        assertEquals(5, reptile.getHungry());
        assertEquals(5, fish.getHungry());
    }

    @Test
    public void testAnimalResetHungry() {
        zoo.addAnimal(bird);
        zoo.addAnimal(reptile);
        zoo.addAnimal(fish);

        for (int i = 0; i < 10; i++) {
            zoo.nextDay();
        }

        assertEquals(0, bird.getHungry());
        assertEquals(0, reptile.getHungry());
        assertEquals(0, fish.getHungry());

        bird.resetHungry();
        reptile.resetHungry();
        fish.resetHungry();

        assertEquals(1, bird.getHungry());
        assertEquals(1, reptile.getHungry());
        assertEquals(1, fish.getHungry());
    }

}
