package ee.taltech.iti0202.zoo.zoo;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;

import java.util.*;

public class Zoo {

    public ArrayList<Animal> zooAnimals = new ArrayList<>();

    public ArrayList<Caretaker> zooCaretakers = new ArrayList<>();

    public void addAnimal(Animal animal) {
        zooAnimals.add(animal);
    }

    public void addCareTaker(Caretaker caretaker) {
        zooCaretakers.add(caretaker);
    }

    /**
     * Method to check what animals need feeding.
     * @return
     */
    public List<Animal> whatAnimalsNeedFeeding() {
        List<Animal> animalsThatNeedFeeding = new ArrayList<>();
        for (Animal animal: zooAnimals) {
            if (animal.getHungry() == 0) {
                animalsThatNeedFeeding.add(animal);
            }
        }
        return animalsThatNeedFeeding;
    }

    /**
     * Metod to check how animals feel.
     * @return
     */
    public Map<String, String> howAnimalsFeel() {
        Map<String, String> howAnimalsFeel = new LinkedHashMap<>();
        for (Animal animal: zooAnimals) {
            if (animal.getHungry() == 0) {
                howAnimalsFeel.put(animal.getName() + (animal.getType()), animal.getHungryVoice());
            } else {
                howAnimalsFeel.put(animal.getName() + (animal.getType()), animal.getVoice());
            }
        }
        return howAnimalsFeel;
    }

    /**
     * Method to get most efficient caretaker.
     * @return
     */
    public Caretaker getMostEfficientCareTaker() {
        Map<Caretaker, Integer> bestWorker = new HashMap<>();
        for (Caretaker caretaker : zooCaretakers) {
            for (Animal animal : whatAnimalsNeedFeeding()) {
                if (caretaker.getTask().contains(animal.getType())) {
                    if (bestWorker.containsKey(caretaker)) {
                        bestWorker.merge(caretaker, 1, Integer::sum);
                    } else {
                        bestWorker.put(caretaker, 1);
                    }
                }
            }
        }
        return bestWorker.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    /**
     * Method to feed hungry animals.
     */
    public void feedHungryAnimals() {
        for (Caretaker caretaker : zooCaretakers) {
            caretaker.feedAnimals(whatAnimalsNeedFeeding());
        }
    }

    /**
     * Method to reduce animals hungry by 1.
     */
    public void nextDay() {
        for (Animal animal: zooAnimals) {
            animal.setHungry();
        }
    }
}
