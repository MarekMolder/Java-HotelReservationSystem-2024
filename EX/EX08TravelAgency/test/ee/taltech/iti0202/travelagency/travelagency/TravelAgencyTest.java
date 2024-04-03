package ee.taltech.iti0202.travelagency.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.client.ClientBuilder;
import ee.taltech.iti0202.travelagency.travelpackage.EPackageType;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelAgencyTest {

    private Client mari;
    private Client juri;
    private TravelAgency travelAgency;
    private TravelPackage italy;
    private TravelPackage france;
    private TravelPackage estonia;
    private TravelPackage africa;
    private TravelPackage america;
    private TravelPackage japan;

    @BeforeEach
    void setUp() {
        mari = new ClientBuilder()
                .setName("Mari")
                .setEmail("Mari@gmail.com")
                .setAge(20)
                .setPhoneNumber(56561010)
                .setHomeAddress("Juhani-10")
                .setMoney(500).createClient();

        juri = new ClientBuilder()
                .setName("Juri")
                .setEmail("Juri@gmail.com")
                .setAge(25)
                .setMoney(200).createClient();

        travelAgency = new TravelAgency();

        italy = new TravelPackage("italy", 50, LocalDate.of(2024, 3, 21),
                LocalDate.of(2024, 3, 26), "Italy", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

        france = new TravelPackage("france", 300, LocalDate.of(2024, 3, 21),
                LocalDate.of(2024, 3, 26), "France", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

        estonia = new TravelPackage("estonia", 50, LocalDate.of(2024, 3, 21),
                LocalDate.of(2024, 3, 26), "Estonia", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

        africa = new TravelPackage("africa", 50, LocalDate.of(2024, 3, 21),
                LocalDate.of(2024, 3, 26), "Africa", new ArrayList<>(List.of("eating")), EPackageType.HIKINGTRIP);

        america = new TravelPackage("america", 50, LocalDate.of(2024, 3, 21),
                LocalDate.of(2024, 3, 26), "America", new ArrayList<>(List.of("eating")), EPackageType.BEACHVACATION);

        japan = new TravelPackage("japan", 50, LocalDate.of(2024, 3, 21),
                LocalDate.of(2024, 3, 26), "Japan", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);
    }

    @Test
    void addClient() {
        // setup
        travelAgency.addClient(mari);
        travelAgency.addClient(juri);
        travelAgency.addClient(mari);

        // what to expect
        assertEquals(2, travelAgency.getClient().size());
        assertEquals(new HashSet<>(Arrays.asList(mari, juri)), travelAgency.getClient());
    }

    @Test
    void addTravelPackage() {
        // setup
        travelAgency.addTravelPackages(japan);
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(japan);

        // what to expect
        assertEquals(2, travelAgency.getTravelAgencyPackages().size());
        assertEquals(new HashSet<>(Arrays.asList(japan, italy)), travelAgency.getTravelAgencyPackages());
    }

    @Test
    void getTopClient() {
        // setup
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

        // what to expect?
        assertEquals(mari, travelAgency.getTopClient());
    }

    @Test
    void getTopPackage() {
        // setup
        travelAgency.addTravelPackages(japan);
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);

        mari.buyPackage(japan, travelAgency);
        mari.buyPackage(italy, travelAgency);
        juri.buyPackage(america, travelAgency);
        juri.buyPackage(japan, travelAgency);

        // what to expect?
        assertEquals(japan, travelAgency.getTopTravelPackage());
    }
}
