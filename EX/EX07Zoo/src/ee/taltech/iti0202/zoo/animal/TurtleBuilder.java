package ee.taltech.iti0202.zoo.animal;

public class TurtleBuilder {
    private String name;
    private Integer hungry;

    /**
     * Method to set name.
     * @param name
     * @return
     */
    public TurtleBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method to set hungry.
     * @param hungry
     * @return
     */
    public TurtleBuilder setHungry(Integer hungry) {
        this.hungry = hungry;
        return this;
    }

    /**
     * Method to create turtle.
     * @return
     */
    public Turtle createTurtle() {
        return new Turtle(name, hungry);
    }
}
