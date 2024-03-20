package ee.taltech.iti0202.builder;

import ee.taltech.iti0202.person.Person;

public class PersonBuilder {
    private String idCode;
    private String name;
    private Integer age;
    private Boolean isMale;

    /**
     * Method to get IdCode.
     * @param idCode
     * @return
     */
    public PersonBuilder setIdCode(String idCode) {
        this.idCode = idCode;
        return this;
    }

    /**
     * Method to get Name;
     * @param name
     * @return
     */
    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method to get age;
     * @param age
     * @return
     */
    public PersonBuilder withAge(Integer age) {
        if (age < 0) {
            throw new IllegalArgumentException();
        } else {
            this.age = age;
            return this;
        }
    }

    /**
     * Method to return isMale.
     * @param isMale
     * @return
     */
    public PersonBuilder withIsMale(Boolean isMale) {
        this.isMale = isMale;
        return this;
    }

    /**
     * Method to create person.
     * @return
     */
    public Person createPerson() {
        return new Person(idCode, name, age, isMale);
    }
}
