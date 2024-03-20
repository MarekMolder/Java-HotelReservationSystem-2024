package ee.taltech.iti0202.factory;

import ee.taltech.iti0202.polygon.*;

public class PolygonFactory {

    /**
     * Factory makes a new Polygon with given amount of sides.
     * @param numberOfSides number of sides on the polygon
     * @return new Polygon class with correct number of sides ( numberOfSides = 4 -> new Square() )
     */
    public static Polygon getPolygon(int numberOfSides) {
        if (numberOfSides == 3) {
            return new Triangle();
        } else if (numberOfSides == 4) {
            return new Square();
        } else if (numberOfSides == 5) {
            return new Pentagon();
        } else if (numberOfSides == 6) {
            return new Hexagon();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
