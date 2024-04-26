package ee.taltech.iti0202.generics.food;

public abstract class Food {
    private String name;

    /**
     * Constructs a new Food with the given name.
     * @param name the name of the food
     */
    public Food(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the food.
     * @return the name of the food
     */
    public String getName() {
        return name;
    }
}
