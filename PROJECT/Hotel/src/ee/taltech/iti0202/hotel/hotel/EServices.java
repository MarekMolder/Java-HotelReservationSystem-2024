package ee.taltech.iti0202.hotel.hotel;

public enum EServices {
    BREAKFAST(20),
    LUNCH(30),
    DINNER(30),
    CLEANING(10),
    SPA(50);

    private Integer price;

    EServices(Integer price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
