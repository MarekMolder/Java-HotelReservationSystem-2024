package ee.taltech.iti0202.hotel.rooms;

public class DoubleRoom extends Room {

    public static final int AMOUNT = 80; // The price of the room

    /**
     * Constructs a Double room which price is set to 200.
     */
    public DoubleRoom() {
        setPrice(AMOUNT);
    }
}
