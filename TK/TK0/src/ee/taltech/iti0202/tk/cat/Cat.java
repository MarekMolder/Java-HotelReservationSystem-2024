package ee.taltech.iti0202.tk.cat;

public class Cat {
    private final String name;
    private Integer age;
    private String color;

    /**
     * Constructs a new Cat object with the specified name, age, and color.
     *
     * @param name  The name of the cat.
     * @param age   The age of the cat.
     * @param color The color of the cat.
     */
    public Cat(String name, Integer age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    /**
     * Constructs a new Cat object with the specified name.
     *
     * @param name The name of the cat.
     */
    public Cat(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the cat.
     *
     * @return The name of the cat.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the cat.
     *
     * @return The age of the cat.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Gets the color of the cat.
     *
     * @return The color of the cat.
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns a string representation of the cat, including its color, name, and age (if available).
     * If age and color are both null, only the name is returned.
     *
     * @return A string representation of the cat.
     */
    public String toString() {
        if (this.age == null && color == null) {
            return name;
        }
        return String.format("%s %s (%d)", color, name, age);
    }
}
