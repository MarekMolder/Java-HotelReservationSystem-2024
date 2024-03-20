package ee.taltech.iti0202.builder;

import ee.taltech.iti0202.person.Person;

public class PersonBuilder {
    private String idCode;
    private String name;
    private Integer age;
    private Boolean isMale;
    private Person person;

    /**
     * Method to get IdCode.
     * @param idCode
     */
    public PersonBuilder(String idCode) {
        this.idCode = idCode;
    }

    /**
     * Method to get Name;
     * @param name
     * @return name.
     */
    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method to get age;
     * @param age
     * @return age.
     */
    public PersonBuilder withAge(Integer age) {
        if (age > 0) {
            this.age = age;
            return this;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method to return isMale.
     * @param isMale
     * @return isMale.
     */
    public PersonBuilder isMale(Boolean isMale) {
        this.isMale = isMale;
        return this;
    }

    /**
     * Method to create person.
     * @return new Person.
     */
    public Person build() {
        return new Person(idCode, name, age, isMale);
    }
}
