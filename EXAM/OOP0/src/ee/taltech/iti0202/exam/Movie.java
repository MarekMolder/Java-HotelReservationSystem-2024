package ee.taltech.iti0202.exam;

import java.math.BigDecimal;
import java.time.Year;

public class Movie {
    private String name;
    private Year year;
    private int length;
    private EType type;
    private BigDecimal price;

    public Movie (String name, int length, EType type, Year year, BigDecimal price) {
        this.name = name;
        this.length = length;
        this.type = type;
        this.year = year;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Year getYear() {
        return year;
    }

    public int getLength() {
        return length;
    }

    public EType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
