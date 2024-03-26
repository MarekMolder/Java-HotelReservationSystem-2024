package ee.taltech.iti0202.travelagency.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TravelAgency {

    public Set<Client> travelAgencyClients = new HashSet<>();

    public Set<TravelPackage> travelAgencyPackages = new HashSet<>();

    public Map<TravelPackage, Integer> topTravelPackages = new HashMap<>();

    public Map<Client, Integer> topClient = new HashMap<>();
    Logger logger = Logger.getLogger(Client.class.getName());

    /**
     * Adds a client to the travel agency's clients set.
     *
     * @param client The client to be added.
     */
    public void addClient(Client client) {
        logger.log(Level.INFO, "Add client to travelAgencyClients Set");
        travelAgencyClients.add(client);
    }

    /**
     * Adds a travel package to the travel agency's packages set.
     *
     * @param travelPackage The travel package to be added.
     */
    public void addTravelPackages(TravelPackage travelPackage) {
        logger.log(Level.INFO, "Add package to travelAgencyPackages Set");
        travelAgencyPackages.add(travelPackage);
    }

    /**
     * Retrieves the set of clients associated with the travel agency.
     *
     * @return The set of clients associated with the travel agency.
     */
    public Set<Client> getClient() {
        logger.log(Level.INFO, "Return travel agency clients");
        return travelAgencyClients;
    }

    /**
     * Retrieves the set of travel packages associated with the travel agency.
     *
     * @return The set of travel packages associated with the travel agency.
     */
    public Set<TravelPackage> getTravelAgencyPackages() {
        logger.log(Level.INFO, "Return travel agency packages");
        return travelAgencyPackages;
    }

    /**
     * Retrieves the top client associated with the travel agency based on the number of purchases.
     *
     * @return The top client associated with the travel agency.
     */
    public Client getTopClient() {
        logger.log(Level.INFO, "Return travel agency top client");
        return topClient.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    /**
     * Retrieves the top travel package associated with the travel agency based on the number of purchases.
     *
     * @return The top travel package associated with the travel agency.
     */
    public TravelPackage getTopTravelPackage() {
        logger.log(Level.INFO, "Return travel agency top package");
        return topTravelPackages.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }
}
