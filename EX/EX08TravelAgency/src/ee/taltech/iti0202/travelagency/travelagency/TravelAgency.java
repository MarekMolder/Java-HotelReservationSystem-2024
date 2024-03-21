package ee.taltech.iti0202.travelagency.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TravelAgency {

    public static Set<Client> travelAgencyClients = new HashSet<>();

    public static Set<TravelPackage> travelAgencyPackages = new HashSet<>();

    public static Map<TravelPackage, Integer> topTravelPackages = new HashMap<>();

    public static Map<Client, Integer> topClient = new HashMap<>();

    public void addClient(Client client) {
        travelAgencyClients.add(client);
    }

    public void addTravelPackages(TravelPackage travelPackage) {
        travelAgencyPackages.add(travelPackage);
    }

    public Set<Client> getClient() {
        return travelAgencyClients;
    }

    public Set<TravelPackage> getTravelAgencyPackages() {
        return travelAgencyPackages;
    }

    public Client getTopClient() {
        return topClient.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    public TravelPackage getTopTravelPackage() {
        return topTravelPackages.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }
}
