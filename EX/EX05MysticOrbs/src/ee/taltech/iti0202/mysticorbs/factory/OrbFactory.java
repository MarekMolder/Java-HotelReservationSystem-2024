package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.InfinityMagicOven;
import ee.taltech.iti0202.mysticorbs.oven.MagicOven;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.oven.SpaceOven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.*;

public class OrbFactory {
    private List<Oven> ovenList;
    public List<Orb> orbList;
    public List<Oven> cannotFixOvens;
    private final ResourceStorage resourceStorage;

    /**
     * Constructs a new Orbfactory with the specified resource storage.
     * @param resourceStorage
     */
    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
        this.ovenList = new ArrayList<>();
        orbList = new ArrayList<>();
        this.cannotFixOvens = new ArrayList<>();
    }

    /**
     * Method to add oven into factory.
     * @param oven
     */
    public void addOven(Oven oven) {
        if (!ovenList.contains(oven) && oven.getResourceStorage() == resourceStorage) {
            ovenList.add(oven);
        }
    }

    public List<Oven> getOvens() {
        return ovenList;
    }

    /**
     * Method to add orb in orbList.
     * @param orb
     */
    public void addOrb(Orb orb) {
        orbList.add(orb);
    }

    /**
     * Method to get and clear produced Orbs List.
     * @return
     */
    public List<Orb> getAndClearProducedOrbsList() {
        if (!orbList.isEmpty()) {
            List<Orb> result = new ArrayList<>(orbList);
            orbList.clear();
            return result;
        }
        return orbList;
    }

    /**
     * Method to produce Orbs.
     * @return
     */
    public int produceOrbs() {
        for (Oven oven : ovenList) {
            if (oven.isBroken()) {
                if (!cannotFixOvens.contains(oven)) {
                    if (oven.getClass() == SpaceOven.class) {
                        try {
                            ((SpaceOven) oven).fix();
                        } catch (CannotFixException e) {
                            continue;
                        }
                    } else if (oven.getClass() == MagicOven.class) {
                        try {
                            ((MagicOven) oven).fix();
                        } catch (CannotFixException e) {
                            continue;
                        }
                    }
                }
            }
            if (!oven.isBroken()) {
                Optional<Orb> craftedOrb = oven.craftOrb();
                craftedOrb.ifPresent(orb -> orbList.add(orb));
            }
        }
        return orbList.size();
    }

    /**
     * Method to produce Orbs in cycles.
     * @param cycles
     * @return
     */
    public int produceOrbs(int cycles) {
        for (int i = 0; i < cycles; i++) {
            for (Oven oven: ovenList) {
                if (oven.isBroken()) {
                    if (!cannotFixOvens.contains(oven)) {
                        if (oven.getClass() == SpaceOven.class) {
                            try {
                                ((SpaceOven) oven).fix();
                            } catch (CannotFixException e) {
                                continue;
                            }
                        } else if (oven.getClass() == MagicOven.class) {
                            try {
                                ((MagicOven) oven).fix();
                            } catch (CannotFixException e) {
                                continue;
                            }
                        }
                    }
                }
                if (!oven.isBroken()) {
                    Optional<Orb> craftedOrb = oven.craftOrb();
                    craftedOrb.ifPresent(orb -> orbList.add(orb));
                }
            }
        }
        return orbList.size();
    }

    /**
     * Method to get ovens that cannot be fixed.
     * @return
     */
    public List<Oven> getOvensThatCannotBeFixed() {
        for (Oven oven : ovenList) {
            if (!cannotFixOvens.contains(oven)) {
                 if (oven.getClass() == MagicOven.class && ((MagicOven) oven).fixed >= 10) {
                    cannotFixOvens.add(oven);
                } else if (oven.getClass() == Oven.class && oven.isBroken()) {
                     cannotFixOvens.add(oven);
                 }
            }
        }
        return cannotFixOvens;
    }

    /**
     * Method to get rid of Ovens that cannot be fixed.
     */
    public void getRidOfOvensThatCannotBeFixed() {
        for (Oven oven : cannotFixOvens) {
            ovenList.remove(oven);
        }
        cannotFixOvens.clear();
    }

    /**
     * Method to optimize Ovens Order
     */
    public void optimizeOvensOrder() {
        Collections.sort(ovenList, (o1, o2) -> compareTo(o1, o2));
    }

    public int compareTo(Oven oven1, Oven oven2) {
        if (!oven2.isBroken() && oven1.isBroken()) {
            return -1;
        } else if (oven2.isBroken() && !oven1.isBroken()) {
            return 1;
        }

        int insertedOven = getTypePriority(oven2.getClass());
        int thisOven = getTypePriority(oven1.getClass());

        if (insertedOven != thisOven) {
            return Integer.compare(thisOven, insertedOven);
        } else if (oven2.getCreatedOrbsAmount() != oven1.getCreatedOrbsAmount()) {
            return Integer.compare(oven1.getCreatedOrbsAmount(), oven2.getCreatedOrbsAmount());
        } else if (!Objects.equals(oven2.getName(), oven1.getName())) {
            return oven2.getName().compareTo(oven1.getName());
        } else {
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
