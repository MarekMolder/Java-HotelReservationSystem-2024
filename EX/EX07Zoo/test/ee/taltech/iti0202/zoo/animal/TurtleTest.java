package ee.taltech.iti0202.zoo.animal;

import ee.taltech.iti0202.zoo.zoo.Zoo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurtleTest {
    Zoo zoo1 = new Zoo();

    @Test
    public void testTurtleTypeAmphibian() {
        Turtle turtle1 = new Turtle("Kiki", 5);
        assertEquals(EAnimalType.AMPHIBIAN, turtle1.getType());
    }

    @Test
    public void testTurtleMakesNoSound() {
        Turtle turtle1 = new Turtle("Kiki", 5);
        assertEquals("", turtle1.getVoice());
    }

    @Test
    public void testTurtleMakesNoSoundWhenHungry() {
        Turtle turtle1 = new Turtle("Kiki", 1);

        zoo1.nextDay();

        assertEquals("", turtle1.getVoice());
    }
}
