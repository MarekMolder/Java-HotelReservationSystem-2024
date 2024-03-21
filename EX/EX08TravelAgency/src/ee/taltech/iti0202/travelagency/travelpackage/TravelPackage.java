package ee.taltech.iti0202.travelagency.travelpackage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TravelPackage {
    private final Integer id;
    private final String name;
    private final Integer price;
    private final LocalDate since;
    private final LocalDate until;
    private final String country;
    private final List<String> activities;
    private final EPackageType type;

    public TravelPackage(Integer id, String name, Integer price, LocalDate since, LocalDate until, String country, List<String> activities, EPackageType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.since = since;
        this.until = until;
        this.country = country;
        this.activities = activities;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public LocalDate getSince() {
        return since;
    }

    public LocalDate getUntil() {
        return until;
    }

    public String getCountry() {
        return country;
    }

    public List<String> getActivities() {
        return activities;
    }

    public EPackageType getType() {
        return type;
    }

    public Integer getTravelDuration() {
        return Math.toIntExact(ChronoUnit.DAYS.between(this.since, this.until));
    }
}
