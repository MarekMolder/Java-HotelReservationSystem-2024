package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb {

    /**
     * Constructs a new Orb with the specified creator.
     * Is a specialized oven that inherits from the base Orb class.
     * @param creator
     */
    public MagicOrb(String creator) {
        super(creator);
    }

    /**
     * Method that charges the orb.
     * @param resource
     * @param amount
     */
    public void charge(String resource, int amount) {
        if (!resource.equalsIgnoreCase("dust") && !resource.trim().isEmpty()) {
            if (amount > 0) {
                this.energy += this.setEnergy(amount * resource.length() * 2);
            }
        }
    }

    /**
     * Method that formats a string.
     * @return
     */
    public String toString() {
        return String.format("MagicOrb by %s", getCreator());
    }
}
