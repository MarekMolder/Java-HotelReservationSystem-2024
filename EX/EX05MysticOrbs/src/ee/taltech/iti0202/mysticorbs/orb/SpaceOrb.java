package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {
    public SpaceOrb(String creator) {
        super(creator);
        setEnergy(100);
    }

    public void charge(String resource, int amount) {

    }

    public String toString() {
        return String.format("SpaceOrb by %s", getCreator());
    }

    public boolean absorb(Orb orb) {
         if (this.getEnergy() > orb.getEnergy()) {
             this.setEnergy(this.getEnergy() + orb.getEnergy());
             orb.setEnergy(0);
             return true;
         }
        return false;
    }
}
