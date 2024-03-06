package ee.taltech.iti0202.shelter.animal;

public class TestAnimalShelter {
    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter(new TestAnimalProvider());
        System.out.println(shelter.getAnimals(Animal.Type.WOMBAT, "red", 10));

        // more "tests" here
    }
}