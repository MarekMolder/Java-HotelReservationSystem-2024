package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {

    private int fixed;
    private int magicNumber;

    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.magicNumber = 0;
        this.fixed = 0;
    }

    public boolean isBroken() {
        return this.orbs >= 5;
    }

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
        int Multiplier = (this.fixed == 0) ? 1 : this.fixed;
        if (!this.isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else {
            if (this.fixed > 10) {
                throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
            } else {
                if (!getResourceStorage().hasEnoughResource("clay", 25 * Multiplier)
                        && getResourceStorage().hasEnoughResource("freezing powder", 100 * Multiplier)
                        || !getResourceStorage().hasEnoughResource("clay", 25 * Multiplier)
                        && !getResourceStorage().hasEnoughResource("freezing powder", 100 * Multiplier)
                        || getResourceStorage().hasEnoughResource("clay", 25 * Multiplier)
                        && !getResourceStorage().hasEnoughResource("freezing powder", 100 * Multiplier)) {
                    throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
                } else {
                    getResourceStorage().takeResource("clay", 25 * Multiplier);
                    getResourceStorage().takeResource("freezing powder", 100 * Multiplier);
                    this.orbs = 0;
                    this.fixed += 1;
                }
            }
        }
    }

    @Override
    public int getTimesFixed() {
        return this.fixed;
    }
}
