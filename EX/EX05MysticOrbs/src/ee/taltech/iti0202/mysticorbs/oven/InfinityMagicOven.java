package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

public class InfinityMagicOven extends MagicOven{
    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    public boolean isBroken() {
        return false;
    }
}
