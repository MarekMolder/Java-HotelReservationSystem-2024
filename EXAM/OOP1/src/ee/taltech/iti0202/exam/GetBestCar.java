package ee.taltech.iti0202.exam;

import java.math.BigDecimal;

public class GetBestCar implements CarStrategy{
    @Override
    public Car getCar(Company company, Client client, int time) {
        BigDecimal balance = client.getBalance();
        Car car = null;
        BigDecimal price = BigDecimal.valueOf(0);
        int gasItTakes = 0;

        for (Car car1: company.getCars().keySet()) {
            price = car1.getPrice().multiply(BigDecimal.valueOf(time)).multiply(company.getDiscount(client));
            gasItTakes = car1.getGasItTakesPerMinute() * time;

            if (gasItTakes >= car1.getGasBalance() && price.compareTo(client.getBalance()) <= 0) {
                if (car == null) {
                    car = car1;
                } else {
                    if (car1.getPrice().compareTo(car.getPrice()) < 0) {
                        car = car1;
                    }
                }
            }
        }
        return car;
    }
}
