package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {

    public static final int CLAY_NEEDED = 25;
    public int fixed;
    private int magicNumber;

    /**
     * Constructs a new Oven with the specified name and resource storage.
     * Is a specializzed oven that inherits from the base Oven class.
     * @param name
     * @param resourceStorage
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.magicNumber = 0;
        fixed = 0;
    }

    public boolean isBroken() {
        if (this.orbs - (this.fixed * 5) >= 5) {
            return true;
        }
        return false;
    }

    /**
     * Method to make a new orb.
     * @return
     */
    public Optional<Orb> craftOrb() {
        if (!this.isBroken() && getResourceStorage().hasEnoughResource("gold".toLowerCase(), 1)
                && getResourceStorage().hasEnoughResource("dust".toLowerCase(), 3)) {
            if (this.magicNumber % 2 == 0) {
                Orb orb = new Orb(getName());
                getResourceStorage().takeResource("gold", 1);
                getResourceStorage().takeResource("dust", 3);
                this.orbs++;
                this.magicNumber++;
                orb.charge("gold", 1);
                return Optional.of(orb);
            } else {
                Orb orb = new MagicOrb(getName());
                getResourceStorage().takeResource("gold", 1);
                getResourceStorage().takeResource("dust", 3);
                this.orbs++;
                this.magicNumber++;
                orb.charge("gold", 1);
                return Optional.of(orb);
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void fix() throws CannotFixException {
        int multiplier = (fixed == 0) ? 1 : fixed;
        if (!this.isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else {
            if (fixed >= 10) {
                throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
            } else {
                if (!getResourceStorage().hasEnoughResource("clay", CLAY_NEEDED * multiplier)
                        && getResourceStorage().hasEnoughResource("freezing powder", 100 * multiplier)
                        || !getResourceStorage().hasEnoughResource("clay", CLAY_NEEDED * multiplier)
                        && !getResourceStorage().hasEnoughResource("freezing powder", 100 * multiplier)
                        || getResourceStorage().hasEnoughResource("clay", CLAY_NEEDED * multiplier)
                        && !getResourceStorage().hasEnoughResource("freezing powder", 100 * multiplier)) {
                    throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
                } else {
                    getResourceStorage().takeResource("clay", CLAY_NEEDED * multiplier);
                    getResourceStorage().takeResource("freezing powder", 100 * multiplier);
                    fixed += 1;
                }
            }
        }
    }

    @Override
    public int getTimesFixed() {
        return fixed;
    }
}
