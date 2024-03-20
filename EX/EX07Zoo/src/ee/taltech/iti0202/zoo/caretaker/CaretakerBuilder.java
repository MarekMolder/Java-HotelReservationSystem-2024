package ee.taltech.iti0202.zoo.caretaker;

import ee.taltech.iti0202.zoo.animal.EAnimalType;

import java.util.ArrayList;

public class CaretakerBuilder {
    private String name;
    private ArrayList<EAnimalType> task;

    /**
     * Method to set name
     * @param name
     * @return
     */
    public CaretakerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method to set task.
     * @param task
     * @return
     */
    public CaretakerBuilder setTask(ArrayList<EAnimalType> task) {
        this.task = task;
        return this;
    }

    /**
     * Method to create caretaker.
     * @return
     */
    public Caretaker createCaretaker() {
        return new Caretaker(name, task);
    }
}
