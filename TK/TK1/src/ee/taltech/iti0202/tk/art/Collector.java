package ee.taltech.iti0202.tk.art;

import java.util.ArrayList;
import java.util.List;

public class Collector {
    private final List<Painting> paintings;

    public Collector() {
        this.paintings = new ArrayList<>();
    }

    boolean addPainting(Painting painting) {
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

    boolean sellPainting(Painting painting, Collector fellowCollector) {
        if (paintings.contains(painting) && fellowCollector != this) {
                if (fellowCollector.addPainting(painting)) {
                    return true;
            }
        }
        return false;
    }

    List<Painting> getPaintings() {
        return paintings;
    }
}
