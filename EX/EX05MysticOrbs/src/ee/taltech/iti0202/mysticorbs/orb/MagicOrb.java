package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb{
    public MagicOrb(String creator) {
        super(creator);
    }

    public void charge(String resource, int amount) {
        if (!resource.equalsIgnoreCase("dust") && !resource.trim().isEmpty()) {
            if (amount > 0) {
                this.energy += this.setEnergy(amount * resource.length() * 2);
            }
        }
    }

    public String toString() {
        return String.format("MagicOrb by %s", getCreator());
    }
}
