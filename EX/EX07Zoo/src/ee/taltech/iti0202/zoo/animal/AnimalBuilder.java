package ee.taltech.iti0202.zoo.animal;

public class AnimalBuilder {
    private String name;
    private String voice;
    private EAnimalType type;
    private Integer hungry;

    public AnimalBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AnimalBuilder setVoice(String voice) {
        this.voice = voice;
        return this;
    }

    public AnimalBuilder setType(EAnimalType type) {
        this.type = type;
        return this;
    }

    public AnimalBuilder setHungry(Integer hungry) {
        this.hungry = hungry;
        return this;
    }

    public Animal createAnimal() {
        return new Animal(name, voice, type, hungry);
    }
}