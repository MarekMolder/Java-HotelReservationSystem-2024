package ee.taltech.iti0202.generics.food;

public abstract class Food {
    private String name;

    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
