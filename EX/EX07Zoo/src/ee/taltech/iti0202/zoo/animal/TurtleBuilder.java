package ee.taltech.iti0202.zoo.animal;

public class TurtleBuilder {
    private String name;
    private Integer hungry;

    public TurtleBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TurtleBuilder setHungry(Integer hungry) {
        this.hungry = hungry;
        return this;
    }

    public Turtle createTurtle() {
        return new Turtle(name, hungry);
    }
}