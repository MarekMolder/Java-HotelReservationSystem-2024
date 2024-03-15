package ee.taltech.iti0202.stream.kittens;

public class Kitten {

    private final Gender gender;
    private final String name;
    private final int age;

    public Kitten(final String name, final Gender gender, final int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public enum Gender {MALE, FEMALE}
}