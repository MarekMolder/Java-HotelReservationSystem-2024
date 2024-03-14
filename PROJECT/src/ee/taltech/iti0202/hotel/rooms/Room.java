package ee.taltech.iti0202.hotel.rooms;

import ee.taltech.iti0202.hotel.hotel.Hotel;

public class Room {
    private int nextRoomNumber = 1;
    private final int number;
    int price;

    private Hotel hotel;

    public int getAndIncrementNextRoomNumber() {
        return nextRoomNumber++;
    }

    public Room() {
        this.number = getAndIncrementNextRoomNumber();
        this.price = 100;
        this.hotel = null;
    }

    public Boolean setHotel(Hotel hotel) {
        if (this.hotel == null) {
            this.hotel = hotel;
            return true;
        } else {
            return false;
        }
    }

    public Integer getPrice() {
        return this.price;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setPrice(Integer amount) {
        this.price = amount;
    }
}
