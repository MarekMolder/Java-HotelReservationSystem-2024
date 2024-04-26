package ee.taltech.iti0202.transportation.person;

public class Person {

    private final int age;
    private final String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }
}
