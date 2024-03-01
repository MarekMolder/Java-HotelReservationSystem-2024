package ee.taltech.iti0202.mysticorbs.orb;

public class Orb {
    private final String creator;
    private int energy;

    public Orb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }

    public void charge(String resource, int amount) {
        if (!resource.toLowerCase().equals("dust") && !resource.trim().equals("")) {
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
    
    public String toString(){
        return String.format("Orb by %s", creator);
    }

    public String getCreator(){
        return this.creator;
    }


}
