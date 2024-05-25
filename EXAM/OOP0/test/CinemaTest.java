import ee.taltech.iti0202.exam.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CinemaTest {
    private Movie movie4;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;
    private Client client1;
    private Client client2;
    private Cinema cinema1;
    private Discount discount;

    @BeforeEach
    void setUp() {
        movie1 = new Movie("Kiired ja Vihased", 120, EType.ACTION, Year.of(2000), BigDecimal.valueOf(10));
        movie2 = new Movie("Kung fu Panda", 120, EType.ACTION, Year.of(2000), BigDecimal.valueOf(10));
        movie3 = new Movie("Spider", 120, EType.ACTION, Year.of(2000), BigDecimal.valueOf(10));
        movie4 = new Movie("Apollo", 120, EType.ACTION, Year.of(2000), BigDecimal.valueOf(10));
        cinema1 = new Cinema();
        client1 = new Client("Mari", 15, BigDecimal.valueOf(280));
        client2 = new Client("Mari", 30, BigDecimal.valueOf(320));
        discount = new Discount();
    }

    @Test
    void addClients() {
        cinema1.addClient(client1);
        cinema1.addClient(client2);
        assertEquals(2, cinema1.getClient().size());
    }

    @Test
    void addMovie() {
        cinema1.addMovie(movie1);
        cinema1.addMovie(movie2);
        assertEquals(2, cinema1.getMovies().size());
    }

    @Test
    void IncreaseMoviePopularity() {
        cinema1.addMovie(movie1);
        assertEquals(Map.of(movie1, 0), cinema1.getMovies());

        cinema1.increaseMoviePopularity(movie1);
        assertEquals(Map.of(movie1, 1), cinema1.getMovies());
    }

    @Test
    void searchFilmByName() {
        cinema1.addMovie(movie1);
        cinema1.addMovie(movie2);
        cinema1.addMovie(movie3);

        assertEquals(movie3, cinema1.searchFilmByName("Spider"));
    }

    @Test
    void searchFilmByNameDoesNotExist() {
        cinema1.addMovie(movie1);
        cinema1.addMovie(movie2);
        cinema1.addMovie(movie3);

        assertThrows(IllegalArgumentException.class, () -> cinema1.searchFilmByName("mis"));
    }

    @Test
    void getTopMovies() {
        cinema1.addMovie(movie1);
        cinema1.addMovie(movie2);
        cinema1.addMovie(movie3);
        cinema1.addMovie(movie4);

        cinema1.increaseMoviePopularity(movie1);
        cinema1.increaseMoviePopularity(movie1);

        cinema1.increaseMoviePopularity(movie2);
        cinema1.increaseMoviePopularity(movie2);
        cinema1.increaseMoviePopularity(movie2);
        cinema1.increaseMoviePopularity(movie2);

        cinema1.increaseMoviePopularity(movie3);
        cinema1.increaseMoviePopularity(movie3);
        cinema1.increaseMoviePopularity(movie3);


        assertEquals(new LinkedList<>(List.of(movie2, movie3, movie1, movie4)), cinema1.getTopMovies());
    }

    @Test
    void buyTicketNoDiscount() throws Exception {
        cinema1.addMovie(movie1);
        cinema1.addMovie(movie2);
        cinema1.addMovie(movie3);
        cinema1.addMovie(movie4);

        assertEquals(BigDecimal.valueOf(280), client1.getBalance());
        assertTrue(cinema1.buyTicket(cinema1, movie1, client1));
        assertEquals(BigDecimal.valueOf(270), client1.getBalance());
    }

    @Test
    void buyTicketMovieDoesNotExist() throws Exception {
        cinema1.addMovie(movie1);
        cinema1.addMovie(movie2);
        cinema1.addMovie(movie3);

        assertEquals(BigDecimal.valueOf(280), client1.getBalance());
        assertFalse(cinema1.buyTicket(cinema1, movie4, client1));
        assertEquals(BigDecimal.valueOf(280), client1.getBalance());
    }

    @Test
    void buyTicketDiscountPopularMovie() throws Exception {
        cinema1.addMovie(movie1);
        cinema1.addMovie(movie2);
        cinema1.addMovie(movie3);
        cinema1.addMovie(movie4);

        cinema1.increaseMoviePopularity(movie3);
        cinema1.increaseMoviePopularity(movie3);

        cinema1.giveStrategy(discount);

        assertEquals(BigDecimal.valueOf(320), client2.getBalance());
        assertTrue(cinema1.buyTicket(cinema1, movie3, client2));
        assertEquals(0, BigDecimal.valueOf(310.5).compareTo(client2.getBalance()));
    }

    @Test
    void buyTicketDiscountPopularMovieAndClientVisitsCinemaMoreThan3Times() throws Exception {
        cinema1.addMovie(movie1);
        cinema1.addMovie(movie2);
        cinema1.addMovie(movie3);
        cinema1.addMovie(movie4);

        cinema1.increaseMoviePopularity(movie3);
        cinema1.increaseMoviePopularity(movie3);

        cinema1.giveStrategy(discount);

        assertEquals(BigDecimal.valueOf(320), client2.getBalance());
        assertTrue(cinema1.buyTicket(cinema1, movie3, client2));
        assertTrue(cinema1.buyTicket(cinema1, movie3, client2));
        assertTrue(cinema1.buyTicket(cinema1, movie1, client2));
        assertTrue(cinema1.buyTicket(cinema1, movie1, client2));

        assertTrue(BigDecimal.valueOf(282.5).compareTo(client2.getBalance()) == 0);
    }

    @Test
    void buyTicketDiscountPopularMovieAndClientVisitsCinemaMoreThan3TimesAndSheIsUnder18() throws Exception {
        cinema1.addMovie(movie1);
        cinema1.addMovie(movie2);
        cinema1.addMovie(movie3);
        cinema1.addMovie(movie4);

        cinema1.increaseMoviePopularity(movie3);
        cinema1.increaseMoviePopularity(movie3);

        cinema1.giveStrategy(discount);

        assertEquals(BigDecimal.valueOf(280), client1.getBalance());
        assertTrue(cinema1.buyTicket(cinema1, movie3, client1));
        assertTrue(cinema1.buyTicket(cinema1, movie3, client1));
        assertTrue(cinema1.buyTicket(cinema1, movie1, client1));
        assertTrue(cinema1.buyTicket(cinema1, movie1, client1));

        assertTrue(BigDecimal.valueOf(246.50).compareTo(client1.getBalance()) == 0);
    }
}
