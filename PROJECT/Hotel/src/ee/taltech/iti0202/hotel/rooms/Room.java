package ee.taltech.iti0202.hotel.rooms;

import ee.taltech.iti0202.hotel.hotel.Hotel;

import java.math.BigDecimal;

/**
 * Represents a room which will be added in hotel.
 */
public class Room {
    private static final BigDecimal PRICE = BigDecimal.valueOf(40); // Room price (BigDecimal.valueOf 40)
    private static int nextRoomNumber = 1; // A unique number for every room
    private final int number; // A unique number for room
    BigDecimal price; // The room price (BigDecimal)
    private Hotel hotel; // The hotel where the room is added

    /**
     * Constructs a room with a unique number and price (100).
     */
    public Room() {
        this.number = getAndIncrementNextRoomNumber();
        this.price = PRICE;
        this.hotel = null;
    }

    /**
     * This method is used to give every room a unique number.
     * @return a unique number.
     */
    private static int getAndIncrementNextRoomNumber() {
        return nextRoomNumber++;
    }

    /**
     * This method is used to set room in a specific hotel.
     * @param hotel The hotel where room is added.
     * @return True if room is not in other hotel, otherwise false.
     */
    public Boolean addHotel(Hotel hotel) {
        if (this.hotel == null) {
            this.hotel = hotel;
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to get hotel where the room belongs.
     * @return The hotel where the room belongs.
     */
    public Hotel getHotel() {
        return this.hotel;
    }

    /**
     * This method is used to get price of the room.
     *
     * @return The price of the room.
     */
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * This method is used to get number of the room.
     * @return A unique number of the room.
     */
    public Integer getNumber() {
        return this.number;
    }

    /**
     * This method is used to set price for the room.
     * @param amount The amount which will be set for the room price.
     */
    public void setPrice(BigDecimal amount) {
        this.price = amount;
    }
}
