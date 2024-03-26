package ee.taltech.iti0202.travelagency.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.client.ClientBuilder;
import ee.taltech.iti0202.travelagency.travelpackage.EPackageType;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelAgencyTest {
    Client mari = new ClientBuilder()
            .setId(589722312)
            .setName("Mari")
            .setEmail("Mari@gmail.com")
            .setAge(20)
            .setPhoneNumber(56561010)
            .setHomeAddress("Juhani-10")
            .setMoney(500).createClient();

    Client juri = new ClientBuilder()
            .setId(589722314)
            .setName("Juri")
            .setEmail("Juri@gmail.com")
            .setAge(25)
            .setMoney(200).createClient();

    TravelAgency travelAgency = new TravelAgency();

    TravelPackage italy = new TravelPackage(1, "italy", 50, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "Italy", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

    TravelPackage france = new TravelPackage(2, "france", 300, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "France", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

    TravelPackage estonia = new TravelPackage(3, "estonia", 50, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "Estonia", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

    TravelPackage africa = new TravelPackage(4, "africa", 50, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "Africa", new ArrayList<>(List.of("eating")), EPackageType.HIKINGTRIP);

    TravelPackage america = new TravelPackage(5, "america", 50, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "America", new ArrayList<>(List.of("eating")), EPackageType.BEACHVACATION);

    TravelPackage japan = new TravelPackage(6, "japan", 50, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "Japan", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

    @Test
    public void testTravelAgencyAddClient() {
        travelAgency.addClient(mari);
        travelAgency.addClient(juri);
        travelAgency.addClient(mari);

        assertEquals(2, travelAgency.getClient().size());
        assertEquals(new HashSet<>(Arrays.asList(mari, juri)), travelAgency.getClient());
    }

    @Test
    public void testTravelAgencyAddTravelPackage() {
        travelAgency.addTravelPackages(japan);
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(japan);

        assertEquals(2, travelAgency.getTravelAgencyPackages().size());
        assertEquals(new HashSet<>(Arrays.asList(japan, italy)), travelAgency.getTravelAgencyPackages());
    }

    @Test
    public void testTravelAgencyGetTopClient() {
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);
        travelAgency.addTravelPackages(japan);

        mari.buyPackage(italy, travelAgency);
        mari.buyPackage(estonia, travelAgency);
        mari.buyPackage(africa, travelAgency);
        mari.buyPackage(japan, travelAgency);
        juri.buyPackage(italy, travelAgency);
        juri.buyPackage(estonia, travelAgency);

        assertEquals(mari, travelAgency.getTopClient());
    }

    @Test
    public void testTravelAgencyGetTopPackage() {
        travelAgency.addTravelPackages(japan);
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);

        mari.buyPackage(japan, travelAgency);
        mari.buyPackage(italy, travelAgency);
        juri.buyPackage(america, travelAgency);
        juri.buyPackage(japan, travelAgency);

        assertEquals(japan, travelAgency.getTopTravelPackage());
    }
}