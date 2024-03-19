package ee.taltech.iti0202.zoo.animal;

public class Animal {

    private final String name;
    private String voice;
    private EAnimalType type;
    private Integer hungry;

    /**
     * Constructs a new Animal with the specified name, voice, type and hungry.
     * @param name
     * @param voice
     * @param type
     * @param hungry
     */
    public Animal(String name, String voice, EAnimalType type, Integer hungry) {
        this.name = name;
        this.voice = voice;
        this.type = type;
        if (hungry < 1) {
            throw new IllegalArgumentException();
        }
        this.hungry = hungry;
    }

    /**
     * Constructs a new Animal with the specified name and hungry.
     * @param name
     * @param hungry
     */
    public Animal(String name, Integer hungry) {
        this.name = name;
        if (hungry < 1) {
            throw new IllegalArgumentException();
        }
        this.hungry = hungry;
    }

    /**
     * Method to get animal name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get animal voice.
     * @return
     */
    public String getVoice() {
        return voice;
    }

    /**
     * Method to get animal hungry voice.
     * @return
     */
    public String getHungryVoice() {
        return "";
    }

    /**
     * Method to get animal type.
     * @return
     */
    public EAnimalType getType() {
        return type;
    }

    /**
     * Method to get animal hungry.
     * @return
     */
    public Integer getHungry() {
        return hungry;
    }

    /**
     * Method to set animal hungry -1.
     */
    public void setHungry() {
        if (this.hungry != 0 && this.getClass() != Lamb.class) {
            this.hungry -= 1;
        }
    }

    /**
     * Method to reset animal hungry back to 1.
     */
    public void resetHungry() {
        this.hungry = 1;
    }
}
