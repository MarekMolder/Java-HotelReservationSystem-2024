import ee.taltech.iti0202.exam.EType;
import ee.taltech.iti0202.exam.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {
    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("Kiired ja Vihased", 120, EType.ACTION, Year.of(2000), BigDecimal.valueOf(10));
    }

    @Test
    void getName() {
        assertEquals("Kiired ja Vihased", movie.getName());
    }

    @Test
    void getYear() {
        assertEquals(Year.of(2000), movie.getYear());
    }

    @Test
    void getLength() {
        assertEquals(120, movie.getLength());
    }

    @Test
    void getType() {
        assertEquals(EType.ACTION, movie.getType());
    }

    @Test
    void getPrice() {
        assertEquals(BigDecimal.valueOf(10), movie.getPrice());
    }

}
