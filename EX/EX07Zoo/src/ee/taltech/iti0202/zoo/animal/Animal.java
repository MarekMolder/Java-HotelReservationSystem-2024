package ee.taltech.iti0202.zoo.animal;

public abstract class Animal {

    private final String name;
    private String voice;
    private EAnimalType type;
    private Integer hungry;

    public Animal(String name, String voice, EAnimalType type, Integer hungry) {
        this.name = name;
        this.voice = voice;
        this.type = type;
        if (hungry <= 1) {
            throw new IllegalArgumentException();
        }
        this.hungry = hungry;
    }

    public Animal(String name, Integer hungry) {
        this.name = name;
        if (hungry <= 1) {
            throw new IllegalArgumentException();
        }
        this.hungry = hungry;
    }

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getVoice() {
        return voice;
    }

    public String getHungryVoice() {
        return "";
    }

    public EAnimalType getType() {
        return type;
    }

    public Integer getHungry() {
        return hungry;
    }

    public void setHungry() {
        if (this.hungry != 0) {
            this.hungry -= 1;
        }
    }

    public void resetHungry() {
        this.hungry = 1;
    }
}
