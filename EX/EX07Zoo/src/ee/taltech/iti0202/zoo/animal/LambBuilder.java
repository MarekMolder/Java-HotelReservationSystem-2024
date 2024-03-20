package ee.taltech.iti0202.zoo.animal;

public class LambBuilder {
    private String name;
    private Integer hungry;

    public LambBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LambBuilder setHungry(Integer hungry) {
        this.hungry = hungry;
        return this;
    }

    public Lamb createLamb() {
        return new Lamb(name, hungry);
    }
}