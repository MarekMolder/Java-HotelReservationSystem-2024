package ee.taltech.iti0202.hotel.rooms;

public class DeluxeRoom extends Room {

    public static final int AMOUNT = 150; // The price of the room

    /**
     * Constructs a deluxe room which price is set to 300.
     */
    public DeluxeRoom() {
        setPrice(AMOUNT);
    }
}
