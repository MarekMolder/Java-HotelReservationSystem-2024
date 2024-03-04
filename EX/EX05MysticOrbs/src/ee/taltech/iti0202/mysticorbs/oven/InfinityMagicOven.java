package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

public class InfinityMagicOven extends MagicOven {

    /**
     * Constructs a new Oven with the specified name and resource storage.
     * Is a specializzed oven that inherits from the base Oven class.
     * @param name
     * @param resourceStorage
     */
    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    public boolean isBroken() {
        return false;
    }
}
