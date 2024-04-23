package ee.taltech.iti0202.generics.animalbox;

import ee.taltech.iti0202.generics.animal.Animal;
import ee.taltech.iti0202.generics.food.Food;

import java.util.Optional;

public class AnimalBox<T extends Animal> {
    private T animal;

    public void put(T animal) {
        this.animal = animal;
    }

    public String sound() {
        if (animal == null) {
            return "";
        } else {
            return animal.sound();
        }
    }

    public String feed(Food food) {
        if (animal == null) {
            return "No animal to feed.";
        } else {
            return animal.getName() + " was fed with " + food.getName() + ".";
        }
    }

    public Optional<T> getAnimal() {
        return Optional.ofNullable(animal);
    }
}
