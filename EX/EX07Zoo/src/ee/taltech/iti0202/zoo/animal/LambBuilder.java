package ee.taltech.iti0202.zoo.animal;

public class LambBuilder {
    private String name;
    private Integer hungry;

    /**
     * Method to set name.
     * @param name
     * @return
     */
    public LambBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method to set hungry.
     * @param hungry
     * @return
     */
    public LambBuilder setHungry(Integer hungry) {
        this.hungry = hungry;
        return this;
    }

    /**
     * Method to create lamb.
     * @return
     */
    public Lamb createLamb() {
        return new Lamb(name, hungry);
    }
}
