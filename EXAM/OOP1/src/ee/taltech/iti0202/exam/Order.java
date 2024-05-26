package ee.taltech.iti0202.exam;

public class Order {
    private Car car;
    private int time;
    private Client client;

    public Order (Car car, int time, Client client) {
        this.car = car;
        this.time = time;
        this.client = client;
    }
}
