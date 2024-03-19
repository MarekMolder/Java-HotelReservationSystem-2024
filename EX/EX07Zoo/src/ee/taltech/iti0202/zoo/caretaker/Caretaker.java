package ee.taltech.iti0202.zoo.caretaker;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.animal.EAnimalType;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {

    private final String name;
    private final ArrayList<EAnimalType> task;

    /**
     * Constructs a new Caretaker with the specified name and task.
     * @param name
     * @param task
     */
    public Caretaker(String name, ArrayList<EAnimalType> task) {
        this.name = name;
        this.task = task;
    }

    /**
     * Method to get caretaker name.
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to get caretaker task.
     * @return
     */
    public ArrayList<EAnimalType> getTask() {
        return this.task;
    }

    /**
     * Method to feed animals.
     * @param animal
     */
    public void feedAnimals(List<Animal> animal) {
        List<Animal> animalsAreFed = new ArrayList<>();

        for (Animal a : animal) {
            if (a.getHungry() == 0) {
                if (this.getTask().contains(a.getType())) {
                    a.resetHungry();
                    animalsAreFed.add(a);
                }
            }
        }
        animal.removeAll(animalsAreFed);
    }
}
