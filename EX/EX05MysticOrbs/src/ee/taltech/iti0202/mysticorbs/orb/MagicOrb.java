package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb{
    public MagicOrb(String creator) {
        super(creator);
    }

    public void charge(String resource, int amount) {
        if (!resource.toLowerCase().equals("dust") && !resource.trim().equals("")) {
            this.setEnergy((amount * resource.length() * 2));
        }
    }

    public String toString() {
        return String.format("MagicOrb by %s", getCreator());
    }
}
