package ee.taltech.iti0202.shelter;

import ee.taltech.iti0202.shelter.animal.Animal;
import ee.taltech.iti0202.shelter.animalprovider.TestAnimalProvider;
import ee.taltech.iti0202.shelter.shelter.AnimalShelter;

public class TestAnimalShelter {
    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter(new TestAnimalProvider());
        System.out.println(shelter.getAnimals(Animal.Type.WOMBAT, "red", 10));

        // more "tests" here
    }
}