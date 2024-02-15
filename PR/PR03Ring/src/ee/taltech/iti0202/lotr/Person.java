package ee.taltech.iti0202.lotr;

public class Person {
    private String race;
    private String name;
    private Ring ring;

    /**
     * Constructs a new person with the specified race, name and ring.
     * @param race
     * @param name
     * @param ring
     */
    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    /**
     * Constructs a new Person with the specified race, name.
     * @param race
     * @param name
     */
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    /**
     * constructs a new Ring.
     * @param ring
     */
    public void setRing(Ring ring) {
        this.ring = ring;
    }

    /**
     * Method to check if he is a real Sauron.
     * If person name is Sauron, ring type is THE_ONE and ring material is GOLD.
     * return "Affirmative".
     * If person name is Sauron, ring type is THE_ONE and ring material is not GOLD.
     * return "No, the ring is fake!".
     * If person name is not Sauron but the ring type is THE_ONE and ring material is GOLD.
     * return "No, he just stole the ring".
     * If person name is Sauron but ring type is not THE_ONE or getRing() is null.
     * return "No, but he's claiming to be".
     * Else.
     * @return "No".
     */
    public String isSauron() {
        Ring.Type ringType = getRing().getType();
        Ring.Material ringMaterial = getRing().getMaterial();
        if (getName().equals("Sauron") && getRing() != null) {
            if (ringType == Ring.Type.THE_ONE && ringMaterial == Ring.Material.GOLD) {
                return "Affirmative";
            } else if (ringType == Ring.Type.THE_ONE && ringMaterial != Ring.Material.GOLD) {
                return "No, the ring is fake!";
            } else if (getName() == "Sauron" && ringType != Ring.Type.THE_ONE) {
                return "No, but he's claiming to be";
            }
        } else if (getName() != "Sauron" && ringType == Ring.Type.THE_ONE && ringMaterial == Ring.Material.GOLD) {
            return "No, he just stole the ring";
        } else if (getName() == "Sauron" && getRing() == null) {
            return "No, but he's claiming to be";
        } else {
            return "No";
        }
        return null;
    }

    /**
     * Method to get race.
     * @return race.
     */
    public String getRace() {
        return race;
    }

    /**
     * Method to get name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get ring.
     * @return ring.
     */
    public Ring getRing() {
        return ring;
    }
}
