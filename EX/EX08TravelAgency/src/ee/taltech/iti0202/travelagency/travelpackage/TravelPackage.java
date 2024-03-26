package ee.taltech.iti0202.travelagency.travelpackage;

import ee.taltech.iti0202.travelagency.client.Client;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TravelPackage {
    private final Integer id;
    private final String name;
    private final Integer price;
    private final LocalDate since;
    private final LocalDate until;
    private final String country;
    private final List<String> activities;
    private final EPackageType type;
    Logger logger = Logger.getLogger(Client.class.getName());

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
        logger.log(Level.INFO, "Return package id");
        return id;
    }

    public String getName() {
        logger.log(Level.INFO, "Return package name");
        return name;
    }

    public Integer getPrice() {
        logger.log(Level.INFO, "Return package price");
        return price;
    }

    public LocalDate getSince() {
        logger.log(Level.INFO, "Return package start date");
        return since;
    }

    public LocalDate getUntil() {
        logger.log(Level.INFO, "Return package end date");
        return until;
    }

    public String getCountry() {
        logger.log(Level.INFO, "Return package destination");
        return country;
    }

    public List<String> getActivities() {
        logger.log(Level.INFO, "Return package activities");
        return activities;
    }

    public EPackageType getType() {
        logger.log(Level.INFO, "Return package type");
        return type;
    }

    public Integer getTravelDuration() {
        logger.log(Level.INFO, "Return package duration");
        return Math.toIntExact(ChronoUnit.DAYS.between(this.since, this.until));
    }
}
