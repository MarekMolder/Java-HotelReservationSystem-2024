package ee.taltech.iti0202.exam;

import java.util.List;

public class Discount implements DiscountStrategy{

    @Override
    public double getDiscount(Cinema cinema, Movie movie, Client client) {
        double totalPrice = 0;

        if (cinema.getMovies().size() >= 3) {
            List<Movie> sortedMovies = cinema.getTopMovies().subList(0, 3);
            if (sortedMovies.contains(movie)) {
                totalPrice += 0.05;
            }
        }
        if (client.getVisits().containsKey(cinema)) {
            if (client.getVisits().get(cinema) >= 3) {
                totalPrice += 0.05;
            }
        }
        if (client.getAge() <= 18) {
            totalPrice += 0.10;
        }
        return totalPrice;
    }
}
