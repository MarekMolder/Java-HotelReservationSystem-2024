package ee.taltech.iti0202.exam.company;

import ee.taltech.iti0202.exam.client.Client;

public interface DiscountStrategy {
    double getDiscount(Company company, Client client);
}
