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

    public static Set<Client> travelAgencyClients = new HashSet<>();

    public static Set<TravelPackage> travelAgencyPackages = new HashSet<>();

    public static Map<TravelPackage, Integer> topTravelPackages = new HashMap<>();

    public static Map<Client, Integer> topClient = new HashMap<>();
    Logger logger = Logger.getLogger(Client.class.getName());

    public void addClient(Client client) {
        logger.log(Level.INFO, "Add client to travelAgencyClients Set");
        travelAgencyClients.add(client);
    }

    public void addTravelPackages(TravelPackage travelPackage) {
        logger.log(Level.INFO, "Add package to travelAgencyPackages Set");
        travelAgencyPackages.add(travelPackage);
    }

    public Set<Client> getClient() {
        logger.log(Level.INFO, "Return travel agency clients");
        return travelAgencyClients;
    }

    public Set<TravelPackage> getTravelAgencyPackages() {
        logger.log(Level.INFO, "Return travel agency packages");
        return travelAgencyPackages;
    }

    public Client getTopClient() {
        logger.log(Level.INFO, "Return travel agency top client");
        return topClient.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    public TravelPackage getTopTravelPackage() {
        logger.log(Level.INFO, "Return travel agency top package");
        return topTravelPackages.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }
}
