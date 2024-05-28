package ee.taltech.iti0202.exam.bus;

public class Bus {
    private final String startPoint;
    private final String destination;
    private final int maxSeats;
    private final double price;
    private int seatsTaken;

    /**
     * Constructs a new bus.
     */
    public Bus(String startPoint, String destination, int maxSeats, Double price) {
        this.startPoint = startPoint;
        this.destination = destination;
        this.maxSeats = maxSeats;
        this.price = price;
        this.seatsTaken = 0;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getDestination() {
        return destination;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Adds a takenSeat to bus.
     */
    public void takeSeat() {
        if (seatsTaken < maxSeats) {
            seatsTaken++;
        }
    }

    public int getSeatsTaken() {
        return seatsTaken;
    }

}
