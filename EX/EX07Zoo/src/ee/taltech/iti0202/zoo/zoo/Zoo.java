package ee.taltech.iti0202.zoo.zoo;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zoo {

    public ArrayList<Animal> zooAnimals = new ArrayList<>();

    public ArrayList<Caretaker> zooCareTakers = new ArrayList<>();

    public void addAnimal (Animal animal) {
        zooAnimals.add(animal);
    }

    public void addCareTaker (Caretaker caretaker) {
        zooCareTakers.add(caretaker);
    }

    public List<Animal> whatAnimalsNeedFeeding() {
        List<Animal> animalsThatNeedFeeding = new ArrayList<>();
        for (Animal animal: zooAnimals) {
            if (animal.getHungry() == 0) {
                animalsThatNeedFeeding.add(animal);
            }
        }
        return animalsThatNeedFeeding;
    }

    public Map<String, String> HowAnimalsFeel() {
        Map<String, String> howAnimalsFeel = new HashMap<>();
        for (Animal animal: zooAnimals) {
            if (animal.getHungry() == 0) {
                howAnimalsFeel.put(animal.getName() + (animal.getType()), animal.getHungryVoice());
            } else {
                howAnimalsFeel.put(animal.getName() + (animal.getType()), animal.getVoice());
            }
        }
        return howAnimalsFeel;
    }

    public Caretaker getMostEfficentCareTaker() {
        Map<Caretaker, Integer> bestWorker = new HashMap<>();
        for (Caretaker caretaker : zooCareTakers) {
            for (Animal animal : zooAnimals) {
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

    public void feedHungryAnimals() {
        for (Caretaker caretaker : zooCareTakers) {
            caretaker.feedAnimals(whatAnimalsNeedFeeding());
        }
    }

    public void nextDay() {
        for (Animal animal: zooAnimals) {
            animal.setHungry();
        }
    }
}
