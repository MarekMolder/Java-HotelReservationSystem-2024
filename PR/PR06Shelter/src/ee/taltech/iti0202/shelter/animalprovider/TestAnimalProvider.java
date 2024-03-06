package ee.taltech.iti0202.shelter.animalprovider;

import ee.taltech.iti0202.shelter.animal.Animal;
import ee.taltech.iti0202.shelter.animal.Hirola;
import ee.taltech.iti0202.shelter.animalprovider.AnimalProvider;

import java.util.ArrayList;
import java.util.List;

public class TestAnimalProvider implements AnimalProvider {
    @Override
    public List<Animal> provide(Animal.Type type) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Hirola("red"));
        return animals;
    }
}