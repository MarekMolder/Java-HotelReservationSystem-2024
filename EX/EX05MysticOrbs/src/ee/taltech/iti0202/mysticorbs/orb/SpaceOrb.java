package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {

    /**
     * Constructs a new Orb with the specified creator.
     * Is a specialized oven that inherits from the base Oven class.
     * @param creator
     */
    public SpaceOrb(String creator) {
        super(creator);
        setEnergy(100);
    }

    /**
     * Method that charges the Orb.
     * @param resource
     * @param amount
     */
    public void charge(String resource, int amount) {
    }

    /**
     * Method to format a string.
     * @return
     */
    public String toString() {
        return String.format("SpaceOrb by %s", getCreator());
    }

    /**
     * Method to absorb another orb.
     * @param orb
     * @return
     */
    public boolean absorb(Orb orb) {
         if (this.getEnergy() > orb.getEnergy()) {
             this.setEnergy(this.getEnergy() + orb.getEnergy());
             orb.setEnergy(0);
             return true;
         }
        return false;
    }
}
