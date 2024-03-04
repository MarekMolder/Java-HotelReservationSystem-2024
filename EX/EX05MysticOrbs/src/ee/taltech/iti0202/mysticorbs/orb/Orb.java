package ee.taltech.iti0202.mysticorbs.orb;

public class Orb {
    private final String creator;
    public int energy;

    /**
     * Constructs a new Orb with the specified creator.
     * @param creator
     */
    public Orb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }

    /**
     * Method that charges the orb.
     * @param resource
     * @param amount
     */
    public void charge(String resource, int amount) {
        if (!resource.equalsIgnoreCase("dust") && !resource.trim().isEmpty()) {
            if (amount > 0) {
                this.energy += (amount * resource.length());
            }
        }
    }

    public int getEnergy() {
        return this.energy;
    }

    public int setEnergy(Integer amount) {
        return this.energy = amount;
    }

    public String toString() {
        return String.format("Orb by %s", creator);
    }

    public String getCreator() {
        return this.creator;
    }
}
