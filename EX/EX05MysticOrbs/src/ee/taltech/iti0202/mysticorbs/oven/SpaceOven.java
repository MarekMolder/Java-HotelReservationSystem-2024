package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {
    public static final int STAR_FRAGMENT_AMOUNT = 15;
    public static final int MAXIMUM_ORBS = 25;
    public static final int LIQUID_SILVER_AMOUNT = 40;
    private int fixed;

    /**
     * Constructs a new Oven with the specified name and resource storage.
     * Is a specializzed oven that inherits from the base Oven class.
     * @param name
     * @param resourceStorage
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.fixed = 0;
    }

    /**
     * Method for making an orb.
     * @return Optional.of(orb)
     */
    public Optional<Orb> craftOrb() {
        if (!this.isBroken() && getResourceStorage().hasEnoughResource("meteorite stone", 1)
                && getResourceStorage().hasEnoughResource("star fragment", STAR_FRAGMENT_AMOUNT)) {
            Orb orb = new SpaceOrb(getName());
            getResourceStorage().takeResource("meteorite stone", 1);
            getResourceStorage().takeResource("star fragment", STAR_FRAGMENT_AMOUNT);
            this.orbs++;
            return Optional.of(orb);
        } else if (getResourceStorage().hasEnoughResource("pearl", 1)
                && getResourceStorage().hasEnoughResource("silver", 1)) {
            Orb orb = new Orb(getName());
            getResourceStorage().takeResource("pearl", 1);
            getResourceStorage().takeResource("silver", 1);
            this.orbs++;
            orb.charge("pearl", 1);
            orb.charge("silver", 1);
            return Optional.of(orb);
        } else {
            return Optional.empty();
        }
    }

    public boolean isBroken() {
        return this.orbs >= MAXIMUM_ORBS && this.fixed < 5;
    }

    @Override
    public void fix() throws CannotFixException {
        if (!this.isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else {
            if (this.fixed >= 5) {
                throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
            } else {
                if (!getResourceStorage().hasEnoughResource("liquid silver", LIQUID_SILVER_AMOUNT)
                        && !getResourceStorage().hasEnoughResource("star essence", 10)) {
                    throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
                } else {
                    if (getResourceStorage().hasEnoughResource("liquid silver", LIQUID_SILVER_AMOUNT)) {
                        getResourceStorage().takeResource("liquid silver", LIQUID_SILVER_AMOUNT);
                    } else {
                        getResourceStorage().takeResource("star essence", 10);
                    }
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
