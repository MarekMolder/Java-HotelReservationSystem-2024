package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.MagicOven;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.oven.SpaceOven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrbFactory {
    private List<Oven> ovenList;
    public static List<Orb> orbList;
    public List<Oven> cannotFixOvens;
    private final ResourceStorage resourceStorage;

    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
        this.ovenList = new ArrayList<>();
        orbList = new ArrayList<>();
        this.cannotFixOvens = new ArrayList<>();
    }

    public void addOven(Oven oven) {
        if (!ovenList.contains(oven) && oven.getResourceStorage() == resourceStorage) {
            ovenList.add(oven);
        }
    }

    public List<Oven> getOvens() {
        return ovenList;
    }

    public static void addOrb(Orb orb) {
        orbList.add(orb);
    }

    public List<Orb> getAndClearProducedOrbsList() {
        if(!orbList.isEmpty()) {
            List<Orb> result = new ArrayList<>(orbList);
            orbList.clear();
            return result;
        }
        return orbList;
    }

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
            Optional<Orb> craftedOrb = oven.craftOrb();
            craftedOrb.ifPresent(orb -> orbList.add(orb));
        }
        return orbList.size();
    }

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
                Optional<Orb> craftedOrb = oven.craftOrb();
                craftedOrb.ifPresent(orb -> orbList.add(orb));
            }
        }
        return orbList.size();
    }

    public List<Oven> getOvensThatCannotBeFixed() {
        for (Oven oven : ovenList) {
            if (!cannotFixOvens.contains(oven)) {
                 if (oven.getClass() == MagicOven.class && MagicOven.fixed > 10) {
                    cannotFixOvens.add(oven);
                } else if (oven.getClass() == Oven.class && oven.isBroken()) {
                     cannotFixOvens.add(oven);
                 }
            }
        }
        return cannotFixOvens;
    }

    public void getRidOfOvensThatCannotBeFixed() {
        for (Oven oven : cannotFixOvens) {
            ovenList.remove(oven);
        }
        cannotFixOvens.clear();
    }

    public void optimizeOvensOrder() {
        ovenList.sort(Comparable::compareTo);
        ovenList.reversed();
    }
}
