package ee.taltech.iti0202.exam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cinema {
    private List<Client> clients;
    private Map<Movie, Integer> movies;
    private DiscountStrategy strategy;

    public Cinema() {
        this.clients = new ArrayList<Client>();
        this.movies = new HashMap<Movie, Integer>();
        this.strategy = null;
    }

    public void giveStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public DiscountStrategy getStrategy() throws Exception {
        return this.strategy;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Client> getClient() {
        return clients;
    }

    public void addMovie(Movie movie) {
        if (!movies.containsKey(movie)) {
            this.movies.put(movie, 0);
        }
    }

    public Map<Movie, Integer> getMovies() {
        return movies;
    }

    public void increaseMoviePopularity(Movie movie) {
        movies.merge(movie, 1, Integer::sum);
    }

    public Movie searchFilmByName(String name) {
        for (Movie movie : this.movies.keySet()) {
            if (movie.getName().equals(name)) {
                return movie;
            }
        }
        throw new IllegalArgumentException("Movie not found");
    }

    public List<Movie> getTopMovies() {
        return movies.entrySet().stream()
                .sorted(Map.Entry.<Movie, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public boolean buyTicket(Cinema cinema, Movie movie, Client client) throws Exception {
        BigDecimal price = calculateTicketPrice(cinema, movie, client);
        if (cinema.getMovies().containsKey(movie)) {
            if (price.compareTo(client.getBalance()) <= 0) {
                increaseMoviePopularity(movie);
                client.addVisit(cinema);
                client.setBalance(price);
                addClient(client);
                return true;
            }
        }
        return false;
    }

    public BigDecimal calculateTicketPrice(Cinema cinema, Movie movie, Client client) throws Exception {
        if (cinema.getStrategy() == null) {
            return movie.getPrice();
        } else {
            return movie.getPrice().multiply(BigDecimal.valueOf(
                    1 - (cinema.getStrategy().getDiscount(cinema, movie, client))));
        }
    }
}
