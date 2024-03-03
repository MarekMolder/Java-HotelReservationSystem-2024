package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable{
    private int fixed;

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.fixed = 0;
    }

    public Optional<Orb> craftOrb() {
        if (!this.isBroken() && getResourceStorage().hasEnoughResource("meteorite stone", 1)
                && getResourceStorage().hasEnoughResource("star fragment", 15)) {
            Orb orb = new SpaceOrb(getName());
            getResourceStorage().takeResource("meteorite stone", 1);
            getResourceStorage().takeResource("star fragment", 15);
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
        return this.orbs >= 25 && this.fixed < 5;
    }

    @Override
    public void fix() throws CannotFixException {
        if (!this.isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else {
            if (this.fixed > 5) {
                throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
            } else {
                if (!getResourceStorage().hasEnoughResource("liquid silver", 40)
                        && !getResourceStorage().hasEnoughResource("star essence", 10)) {
                    throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
                } else {
                    if (getResourceStorage().hasEnoughResource("liquid silver", 40)) {
                        getResourceStorage().takeResource("liquid silver", 40);
                    } else {
                        getResourceStorage().takeResource("star essence", 10);
                    }
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
