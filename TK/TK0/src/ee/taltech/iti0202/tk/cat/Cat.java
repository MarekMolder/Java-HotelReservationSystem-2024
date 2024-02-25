package ee.taltech.iti0202.tk.cat;

public class Cat {
    private final String name;
    private Integer age;
    private String color;

    public Cat(String name, Integer age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        if (this.age == null && color == null) {
            return name;
        }
        return String.format("%s %s (%d)", color, name, age);
    }
}
