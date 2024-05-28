package ee.taltech.iti0202.exam.company;

import ee.taltech.iti0202.exam.client.Client;

public class Discount implements DiscountStrategy {

    public static final int FREE_TICKET_FOR_THAT_AGE = 6;
    public static final double ONE_DISCOUNT = 0.10;
    public static final double SECOND_DISCOUNT = 0.25;
    public static final int MINIMUM_AGE = 7;
    public static final int MAXIMUM_AGE = 19;
    public static final int OLD_AGE = 65;


    @Override
    public double getDiscount(Company company, Client client) {
        double totalDiscount = 1;

        if (client.getAge() <= FREE_TICKET_FOR_THAT_AGE) {
            return 0;
        } else if (client.getAge() >= MINIMUM_AGE && client.getAge() <= MAXIMUM_AGE
                || client.getAge() >= OLD_AGE) {
            totalDiscount -= ONE_DISCOUNT;
        }

        if (client.getCompanies().containsKey(company) && client.getCompanies().get(company) % 3 == 0) {
            totalDiscount -= SECOND_DISCOUNT;
        }
        return totalDiscount;
    }
}
