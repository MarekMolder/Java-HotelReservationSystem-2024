package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Objects;
import java.util.Optional;

public class Oven implements Comparable<Oven> {

    private final String name;
    private final ResourceStorage resourceStorage;
    public int orbs;
    public int fixed;

    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
        this.orbs = 0;
        this.fixed = 0;
    }

    public String getName() {
        return this.name;
    }

    public ResourceStorage getResourceStorage() {
        return this.resourceStorage;
    }

    public int getCreatedOrbsAmount() {
        return this.orbs;
    }

    public boolean isBroken() {
        return this.orbs >= 15;
    }

    public Optional<Orb> craftOrb() {
        if (!this.isBroken() && resourceStorage.hasEnoughResource("pearl".toLowerCase(), 1)
                && resourceStorage.hasEnoughResource("silver".toLowerCase(), 1)) {
            Orb orb = new Orb(getName());
            resourceStorage.takeResource("pearl".toLowerCase(), 1);
            resourceStorage.takeResource("silver".toLowerCase(), 1);
            orbs++;
            orb.charge("pearl".toLowerCase(), 1);
            orb.charge("silver".toLowerCase(), 1);
            return Optional.of(orb);
        } else {
            return Optional.empty();
        }
    }

    public int compareTo(Oven o) {
        if (!o.isBroken() && this.isBroken()) {
            return -1;
        } else if (o.isBroken() && !this.isBroken()) {
            return 1;
        }

        int insertedOven = getTypePriority(o.getClass());
        int thisOven = getTypePriority(this.getClass());

        if (insertedOven != thisOven) {
            return Integer.compare(thisOven, insertedOven);
        }else if (this.getCreatedOrbsAmount() != o.getCreatedOrbsAmount()) {
            return Integer.compare(o.getCreatedOrbsAmount(), this.getCreatedOrbsAmount());
        } else if (!Objects.equals(this.name, o.name)) {
            return this.name.compareTo(o.name);
        }else {
            return 0;
        }
    }

    private int getTypePriority(Class<? extends Oven> clazz) {
        if (clazz.equals(SpaceOven.class)) {
            return 4;
        } else if (clazz.equals(InfinityMagicOven.class)) {
            return 3;
        } else if (clazz.equals(MagicOven.class)) {
            return 2;
        } else {
            return 1; // Oven
        }
    }
}
