package ee.taltech.iti0202.generics.animalbox;

import ee.taltech.iti0202.generics.animal.Animal;
import ee.taltech.iti0202.generics.food.Food;

import java.util.Optional;

public class AnimalBox<T extends Animal> {
    private T animal;

    /**
     * Stores the specified animal in the box.
     * @param animal the animal to be stored in the box, can be {@code null}
     */
    public void put(T animal) {
        this.animal = animal;
    }

    /**
     * Returns the sound made by the stored animal.
     * If no animal is stored, returns an empty string.
     * @return the sound of the stored animal, or an empty string if no animal is present
     */
    public String sound() {
        if (animal == null) {
            return "";
        } else {
            return animal.sound();
        }
    }

    /**
     * Feeds the stored animal with the specified food and returns a description of the action.
     * If no animal is present, it indicates that there is no animal to feed.
     * @param food the food to feed to the animal, not null
     * @return a string describing the feeding action or a message indicating no animal to feed
     */
    public String feed(Food food) {
        if (animal == null) {
            return "No animal to feed.";
        } else {
            return animal.getName() + " was fed with " + food.getName() + ".";
        }
    }

    /**
     * Retrieves the stored animal as an {@link Optional}. This method provides a safe way to
     * access the potentially {@code null} animal stored.
     * @return an {@link Optional} containing the stored animal or an empty {@link Optional} if no animal is stored
     */
    public Optional<T> getAnimal() {
        return Optional.ofNullable(animal);
    }
}
