package ee.taltech.iti0202.exam;

public interface DiscountStrategy {
    double getDiscount(Cinema cinema, Movie movie, Client client);
}
