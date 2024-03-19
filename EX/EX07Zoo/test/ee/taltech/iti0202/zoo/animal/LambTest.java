package ee.taltech.iti0202.zoo.animal;

import ee.taltech.iti0202.zoo.zoo.Zoo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LambTest {
    Zoo zoo1 = new Zoo();

    @Test
    public void testLambTypeMammal() {
        Lamb lamb = new Lamb("Vuulu", 5);
        assertEquals(EAnimalType.MAMMAL, lamb.getType());
    }

    @Test
    public void testLambMakesAlwaysSameSound() {
        Lamb lamb = new Lamb("Vuulu",5);
        assertEquals("Mää", lamb.getVoice());
    }

    @Test
    public void testLambDoesntGetHungry() {
        Lamb lamb = new Lamb("Vuulu", 1);
        zoo1.addAnimal(lamb);

        zoo1.nextDay();

        assertEquals(1, lamb.getHungry());

        for (int i = 0; i < 5; i++) {
            zoo1.nextDay();
        }

        assertEquals(1, lamb.getHungry());
    }
}