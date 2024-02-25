package ee.taltech.iti0202.tk.cat;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private final List<Cat> cats;

    /**
     * Constructs a new Person object with an empty list of cats.
     */
    public Person() {
        this.cats = new ArrayList<>();
    }

    /**
     * Adds a cat to the list of owned cats if it is not already present.
     *
     * @param cat The cat to be added.
     * @return True if the cat was added successfully, false if the cat is already in the list.
     */
    public boolean addCat(Cat cat) {
        if (cats.contains(cat)) {
            return false;
        } else {
            cats.add(cat);
            return true;
        }
    }

    /**
     * Removes a cat from the list of owned cats if it is present.
     *
     * @param cat The cat to be removed.
     */
    public void removeCat(Cat cat) {
        if (cats.contains(cat)) {
            cats.remove(cat);
        }
    }

    /**
     * Gets the list of cats owned by the person.
     *
     * @return The list of owned cats.
     */
    public List<Cat> getCats() {
        return cats;
    }

    /**
     * Sells a cat from the current person to another person.
     *
     * @param sellTo The person to sell the cat to.
     * @param cat    The cat to be sold.
     * @return True if the sale was successful, false otherwise (e.g., if the target person is the same as the seller or if the cat is not owned by the seller).
     */
    public boolean sellCat(Person sellTo, Cat cat) {
        if (sellTo != this && this.getCats().contains(cat)) {
            sellTo.addCat(cat);
            this.removeCat(cat);
            return true;
        }
        return false;
    }
}
