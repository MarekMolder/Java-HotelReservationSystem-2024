package ee.taltech.iti0202.zoo.animal;

public class AnimalBuilder {
    private String name;
    private String voice;
    private EAnimalType type;
    private Integer hungry;

    /**
     * Method to build constructor name.
     * @param name
     * @return
     */
    public AnimalBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method to build constructor voice.
     * @param voice
     * @return
     */
    public AnimalBuilder setVoice(String voice) {
        this.voice = voice;
        return this;
    }

    /**
     * Method to build constructor type.
     * @param type
     * @return
     */
    public AnimalBuilder setType(EAnimalType type) {
        this.type = type;
        return this;
    }

    /**
     * Method to set hungry.
     * @param hungry
     * @return
     */
    public AnimalBuilder setHungry(Integer hungry) {
        this.hungry = hungry;
        return this;
    }

    /**
     * Method to create animal.
     * @return
     */
    public Animal createAnimal() {
        return new Animal(name, voice, type, hungry);
    }
}
