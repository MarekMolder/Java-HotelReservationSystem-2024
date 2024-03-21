package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.travelpackage.EPackageType;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
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
    public void testClientGetName() {
        assertEquals("Mari", mari.getName());
        assertEquals("Juri", juri.getName());
    }

    @Test
    public void testClientWhenClientDoesntHaveName() {
        assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setName(null).createClient());
    }

    @Test
    public void testClientGetId() {
        assertEquals(589722312, mari.getId());
        assertEquals(589722314, juri.getId());
    }

    @Test
    public void testClientWhenClientDoesntHaveId() {
        assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setId(null).createClient());
    }

    @Test
    public void testClientGetEmail() {
        assertEquals("Mari@gmail.com", mari.getEmail());
        assertEquals("Juri@gmail.com", juri.getEmail());
    }

    @Test
    public void testClientWhenClientDoesntHaveEmail() {
        assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setEmail(null).createClient());
    }

    @Test
    public void testClientGetAge() {
        assertEquals(20, mari.getAge());
        assertEquals(25, juri.getAge());
    }

    @Test
    public void testClientWhenClientDoesntHaveAge() {
        assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setAge(null).createClient());
    }

    @Test
    public void testClientGetPhoneNumber() {
        assertEquals(56561010, mari.getPhoneNumber());
        assertNull(juri.getPhoneNumber());
    }

    @Test
    public void testClientGetHomeAddress() {
        assertEquals("Juhani-10", mari.getHomeAddress());
        assertNull(juri.getHomeAddress());
    }

    @Test
    public void testClientGetStatus() {
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);

        assertEquals(EPersonStatus.REGULAR, mari.getStatus());
        assertEquals(EPersonStatus.REGULAR, juri.getStatus());

        mari.buyPackage(italy);
        mari.buyPackage(estonia);
        mari.buyPackage(france);
        mari.buyPackage(america);
        mari.buyPackage(africa);

        juri.buyPackage(italy);
        juri.buyPackage(estonia);
        juri.buyPackage(africa);

        assertEquals(EPersonStatus.GOLD, mari.getStatus());
        assertEquals(EPersonStatus.SILVER, juri.getStatus());
    }

    @Test
    public void testClientGetStatusWhenHaveBoughtPackages() {
        assertEquals(EPersonStatus.REGULAR, mari.getStatus());
        assertEquals(EPersonStatus.REGULAR, juri.getStatus());
    }

    @Test
    public void testClientGetMoney() {
        assertEquals(500, mari.getMoney());
        assertEquals(200, juri.getMoney());
    }

    @Test
    public void testClientGetPackages() {
        assertEquals(new HashSet<>(), mari.getPackages());
        assertEquals(new HashSet<>(), juri.getPackages());
    }

    @Test
    public void testClientSetMoney() {
        mari.setMoney(400);
        juri.setMoney(300);
        assertEquals(100, mari.getMoney());
        assertEquals(-100, juri.getMoney());
    }

    @Test
    public void testClientSetPackages() {
        mari.setPackages(estonia);
        mari.setPackages(africa);
        mari.setPackages(estonia);

        assertEquals(2, mari.getPackages().size());
        assertEquals(new HashSet<>(Arrays.asList(estonia, africa)), mari.getPackages());
    }

    @Test
    public void testClientBuyPackage() {
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);


        assertTrue(mari.buyPackage(estonia));
        assertTrue(mari.buyPackage(italy));
        assertTrue(mari.buyPackage(africa));
        assertTrue(mari.buyPackage(france));
        assertTrue(mari.buyPackage(america));
    }

    @Test
    public void testClientBuyPackageButSheAlreadyBoughtIt() {
        travelAgency.addTravelPackages(italy);

        assertTrue(mari.buyPackage(italy));
        assertFalse(mari.buyPackage(italy));
    }

    @Test
    public void testClientBuyPackageButTravelAgencyDoesntSellThis() {
        assertFalse(mari.buyPackage(italy));
    }

    @Test
    public void testClientBuyPackageButDoesntHaveEnoughMoney() {
        assertFalse(juri.buyPackage(france));
    }

    @Test
    public void testClientBuyPackageWhenStatusIsRegular() {
        travelAgency.addTravelPackages(france);

        assertEquals(500, mari.getMoney());

        mari.buyPackage(france);

        assertEquals(200, mari.getMoney());
    }

    @Test
    public void testClientBuyPackageWhenStatusIsSilverAndPackagesAreMoreThanFiveDays() {
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);
        travelAgency.addTravelPackages(japan);

        assertEquals(500, mari.getMoney());

        mari.setPackages(italy);
        mari.setPackages(america);
        mari.setPackages(japan);
        mari.buyPackage(france);
        mari.buyPackage(estonia);
        mari.buyPackage(africa);

        assertEquals(123, mari.getMoney());
    }

    @Test
    public void testClientBuyPackageWhenStatusIsGoldAndPackagesAreMoreThanFiveDays() {
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);
        travelAgency.addTravelPackages(japan);

        assertEquals(500, mari.getMoney());

        mari.setPackages(italy);
        mari.setPackages(america);
        mari.setPackages(japan);
        mari.setPackages(estonia);
        mari.setPackages(africa);
        mari.buyPackage(france);

        assertEquals(230, mari.getMoney());
    }
}