package ee.taltech.iti0202.builder;

import ee.taltech.iti0202.person.Person;

public class PersonBuilder {
    private String idCode;
    private String name;
    private Integer age;
    private Boolean isMale;

    public PersonBuilder setIdCode(String idCode) {
        this.idCode = idCode;
        return this;
    }

    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder withAge(Integer age) {
        if (age < 0) {
            throw new IllegalArgumentException();
        } else {
            this.age = age;
            return this;
        }
    }

    public PersonBuilder withIsMale(Boolean isMale) {
        this.isMale = isMale;
        return this;
    }

    public Person createPerson() {
        return new Person(idCode, name, age, isMale);
    }
}
