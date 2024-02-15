package ee.taltech.iti0202.lotr;

public class Person {
    private String race;
    private String name;
    private Ring ring;

    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }
    public void setRing(Ring ring) {
        this.ring = ring;
    }
    public String isSauron() {
        Ring.Type ringType = getRing().getType();
        Ring.Material ringMaterial = getRing().getMaterial();
        if (getName().equals("Sauron")) {;
            if (ringType == Ring.Type.THE_ONE && ringMaterial == Ring.Material.GOLD) {
                return "Affirmative";
            } else if (ringType == Ring.Type.THE_ONE && ringMaterial != Ring.Material.GOLD) {
                return "No, the ring is fake!";
            } else if (ringType != Ring.Type.THE_ONE || getRing() == null) {
                return "No, but he's claiming to be";
            }
        } else if (getName() != "Sauron" && ringType == Ring.Type.THE_ONE && ringMaterial == Ring.Material.GOLD) {
            return "No, he just stole the ring";
        } else {
            return "No";
        }
        return null;
    }

    public String getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

    public Ring getRing() {
        return ring;
    }
}
