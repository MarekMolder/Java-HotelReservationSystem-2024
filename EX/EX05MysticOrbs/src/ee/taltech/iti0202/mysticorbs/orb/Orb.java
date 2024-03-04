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

    /**
     * Method to set energy.
     * @param amount
     * @return
     */
    public int setEnergy(Integer amount) {
        if (amount == null) {
            return 0;
        }
        this.energy = amount;
        return this.energy;
    }

    /**
     * Method to format a string.
     * @return
     */
    public String toString() {
        return String.format("Orb by %s", creator);
    }

    public String getCreator() {
        return this.creator;
    }
}
