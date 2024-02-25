package ee.taltech.iti0202.tk.cat;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private final List<Cat> cats;

    public Person() {
        this.cats = new ArrayList<>();
    }

    public boolean addCat(Cat cat) {
        if (cats.contains(cat)) {
            return false;
        } else {
            cats.add(cat);
            return true;
        }
    }

    public void removeCat(Cat cat) {
        if (cats.contains(cat)) {
            cats.remove(cat);
        }
    }

    public List<Cat> getCats() {
        return cats;
    }

    public boolean sellCat(Person sellTo, Cat cat) {
        if (sellTo != this && this.getCats().contains(cat)) {
            sellTo.addCat(cat);
            this.removeCat(cat);
            return true;
        }
        return false;
    }
}
