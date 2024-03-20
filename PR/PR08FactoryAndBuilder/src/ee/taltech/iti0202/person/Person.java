package ee.taltech.iti0202.person;

public class Person {

    private final String idCode;
    private Integer age;
    private final String name;
    private final Boolean isMale;

    /**
     * Construct a new person with given parameters.
     * @param idCode id code for person
     * @param name name of person
     * @param age age of person
     * @param isMale boolean, true if person is male
     */
    public Person(String idCode, String name, Integer age, Boolean isMale) {
        this.idCode = idCode;
        this.name = name;
        if (age < 0) {
            throw new IllegalArgumentException();
        }
        this.isMale = isMale;
    }

    /**
     * Return id code of person.
     * @return id code
     */
    public String getIdCode() {
        return idCode;
    }

    /**
     * Return name of person.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Return age of person.
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Return true if person is male.
     * @return boolean
     */
    public Boolean isMale() {
        return isMale;
    }
}
