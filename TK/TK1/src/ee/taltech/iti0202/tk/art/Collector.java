package ee.taltech.iti0202.tk.art;

import java.util.ArrayList;
import java.util.List;

public class Collector {
    private final List<Painting> paintings;

    /**
     * Constructs a new Collector object with an empty list of paintings.
     */
    public Collector() {
        this.paintings = new ArrayList<>();
    }

    /**
     * Adds a painting to the list of owned paintings if it is not already present.
     * @param painting A painting to be added.
     * @return True if the pointing was added successfully, false if the painting is already in the list.
     */
    public boolean addPainting(Painting painting) {
        List<Painting> sameName = new ArrayList<>();
        for (Painting paintings : paintings) {
            if (paintings.getTitle() == painting.getTitle()) {
                sameName.add(painting);
            }
        }
        if (paintings.contains(painting) || !sameName.isEmpty()) {
            return false;
        } else {
            paintings.add(painting);
            return true;

        }
    }

    /**
     * Sells a Painting from the current person to another person.
     * @param painting Painting to be sold.
     * @param fellowCollector Collector who buys the painting.
     * @return True if the sale was successful, false otherwise.
     */
    public boolean sellPainting(Painting painting, Collector fellowCollector) {
        if (paintings.contains(painting) && fellowCollector != this) {
                if (fellowCollector.addPainting(painting)) {
                    return true;
            }
        }
        return false;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }
}
