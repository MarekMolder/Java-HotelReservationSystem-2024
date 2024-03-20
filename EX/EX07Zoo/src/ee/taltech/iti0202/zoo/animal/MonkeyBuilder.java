package ee.taltech.iti0202.zoo.animal;

public class MonkeyBuilder {
    private String name;
    private Integer hungry;

    /**
     * Method to set name.
     * @param name
     * @return
     */
    public MonkeyBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method to set hungry.
     * @param hungry
     * @return
     */
    public MonkeyBuilder setHungry(Integer hungry) {
        this.hungry = hungry;
        return this;
    }

    /**
     * Method to create monkey.
     * @return
     */
    public Monkey createMonkey() {
        return new Monkey(name, hungry);
    }
}
