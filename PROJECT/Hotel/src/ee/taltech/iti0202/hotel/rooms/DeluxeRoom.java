package ee.taltech.iti0202.hotel.rooms;

import java.math.BigDecimal;

public class DeluxeRoom extends Room {

    public static final BigDecimal AMOUNT = BigDecimal.valueOf(150); // The price of the room (BigDecimal.valueOf 150)

    /**
     * Constructs a deluxe room which price is set to 150.
     */
    public DeluxeRoom() {
        setPrice(AMOUNT);
    }
}
