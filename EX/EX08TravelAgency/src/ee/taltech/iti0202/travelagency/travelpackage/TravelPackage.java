package ee.taltech.iti0202.travelagency.travelpackage;

import ee.taltech.iti0202.travelagency.client.Client;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TravelPackage {
    private static int nextId = 1;
    private final int number;
    private final String name;
    private final Integer price;
    private final LocalDate since;
    private final LocalDate until;
    private final String country;
    private final List<String> activities;
    private final EPackageType type;
    Logger logger = Logger.getLogger(Client.class.getName());

    public int getAndIncrementNextId() {
        return nextId++;
    }

    /**
     * Constructs a TravelPackage with the specified attributes.
     * @param name       The name of the travel package.
     * @param price      The price of the travel package.
     * @param since      The start date of the travel package.
     * @param until      The end date of the travel package.
     * @param country    The destination country of the travel package.
     * @param activities The list of activities included in the travel package.
     * @param type       The type of the travel package.
     */
    public TravelPackage(String name, Integer price, LocalDate since,
                         LocalDate until, String country, List<String> activities, EPackageType type) {
        this.number = getAndIncrementNextId();
        this.name = name;
        this.price = price;
        this.since = since;
        this.until = until;
        this.country = country;
        this.activities = activities;
        this.type = type;
    }

    /**
     * Retrieves the ID of the travel package.
     *
     * @return The ID of the travel package.
     */
    public Integer getId() {
        logger.log(Level.INFO, "Returns package id: " + number);
        return number;
    }

    /**
     * Retrieves the name of the travel package.
     *
     * @return The name of the travel package.
     */
    public String getName() {
        logger.log(Level.INFO, "Returns package name: " + name);
        return name;
    }

    /**
     * Retrieves the price of the travel package.
     *
     * @return The price of the travel package.
     */
    public Integer getPrice() {
        logger.log(Level.INFO, "Returns package price: " + price);
        return price;
    }

    /**
     * Retrieves the start date of the travel package.
     *
     * @return The start date of the travel package.
     */
    public LocalDate getSince() {
        logger.log(Level.INFO, "Returns package start date: " + since);
        return since;
    }

    /**
     * Retrieves the end date of the travel package.
     *
     * @return The end date of the travel package.
     */
    public LocalDate getUntil() {
        logger.log(Level.INFO, "Returns package end: " + until);
        return until;
    }

    /**
     * Retrieves the destination country of the travel package.
     *
     * @return The destination country of the travel package.
     */
    public String getCountry() {
        logger.log(Level.INFO, "Returns package destination: " + country);
        return country;
    }

    /**
     * Retrieves the list of activities included in the travel package.
     *
     * @return The list of activities included in the travel package.
     */
    public List<String> getActivities() {
        logger.log(Level.INFO, "Returns package activities: " + activities);
        return activities;
    }

    /**
     * Retrieves the type of the travel package.
     *
     * @return The type of the travel package.
     */
    public EPackageType getType() {
        logger.log(Level.INFO, "Returns package type: " + type);
        return type;
    }

    /**
     * Calculates and retrieves the duration of the travel package in days.
     *
     * @return The duration of the travel package in days.
     */
    public Integer getTravelDuration() {
        logger.log(Level.INFO, "Return package duration: "
                + Math.toIntExact(ChronoUnit.DAYS.between(this.since, this.until)));
        return Math.toIntExact(ChronoUnit.DAYS.between(this.since, this.until));
    }
}
