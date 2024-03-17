package ee.taltech.iti0202.zoo.caretaker;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.animal.EAnimalType;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {

    private final String name;
    private final ArrayList<EAnimalType> task;

    public Caretaker(String name, ArrayList<EAnimalType> task) {
        this.name = name;
        this.task = task;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<EAnimalType> getTask() {
        return this.task;
    }

    public boolean feedAnimals(List<Animal> animal) {
        for (Animal a : animal) {
            if (this.getTask().contains(a.getType())) {
                a.resetHungry();
                animal.remove(a);
                return true;
            }
        }
        return false;
    }
}
