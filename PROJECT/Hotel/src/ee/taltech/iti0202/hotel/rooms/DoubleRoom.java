package ee.taltech.iti0202.hotel.rooms;

import java.math.BigDecimal;

public class DoubleRoom extends Room {

    public static final BigDecimal AMOUNT = BigDecimal.valueOf(80); // The price of the room

    /**
     * Constructs a Double room which price is set to 80.
     */
    public DoubleRoom() {
        setPrice(AMOUNT);
    }
}
