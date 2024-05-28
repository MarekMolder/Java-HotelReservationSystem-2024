package ee.taltech.iti0202.exam.company;

import ee.taltech.iti0202.exam.client.Client;

public class Discount implements DiscountStrategy {
    @Override
    public double getDiscount(Company company, Client client) {
        double totalDiscount = 1;

        if (client.getAge() <= 6) {
            return 0;
        } else if (client.getAge() >= 7 && client.getAge() <= 19 || client.getAge() >= 65) {
            totalDiscount -= 0.10;
        }

        if (client.getCompanies().containsKey(company) && client.getCompanies().get(company) % 3 == 0) {
            totalDiscount -= 0.25;
        }
        return totalDiscount;
    }
}
