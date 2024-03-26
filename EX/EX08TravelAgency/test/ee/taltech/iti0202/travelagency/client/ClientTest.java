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

    Client tonu = new ClientBuilder()
            .setId(589722314)
            .setName("Tonu")
            .setEmail("Tonu@gmail.com")
            .setAge(25)
            .setMoney(0).createClient();

    Client kalle = new ClientBuilder()
            .setId(589722314)
            .setName("Kalle")
            .setEmail("Kalle@gmail.com")
            .setAge(25)
            .setMoney(0).createClient();

    Client malle = new ClientBuilder()
            .setId(589722314)
            .setName("Malle")
            .setEmail("Malle@gmail.com")
            .setAge(25)
            .setMoney(0).createClient();

    TravelAgency travelAgency = new TravelAgency();

    TravelPackage italy = new TravelPackage("italy", 50, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "Italy", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

    TravelPackage france = new TravelPackage("france", 300, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "France", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

    TravelPackage estonia = new TravelPackage("estonia", 50, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "Estonia", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

    TravelPackage africa = new TravelPackage("africa", 50, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "Africa", new ArrayList<>(List.of("eating")), EPackageType.HIKINGTRIP);

    TravelPackage america = new TravelPackage("america", 50, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "America", new ArrayList<>(List.of("eating")), EPackageType.BEACHVACATION);

    TravelPackage japan = new TravelPackage("japan", 50, LocalDate.of(2024, 3,21),
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

        mari.buyPackage(italy, travelAgency);
        mari.buyPackage(estonia, travelAgency);
        mari.buyPackage(france, travelAgency);
        mari.buyPackage(america, travelAgency);
        mari.buyPackage(africa, travelAgency);

        juri.buyPackage(italy, travelAgency);
        juri.buyPackage(estonia, travelAgency);
        juri.buyPackage(africa, travelAgency);

        assertEquals(EPersonStatus.GOLD, mari.getStatus());
        assertEquals(EPersonStatus.SILVER, juri.getStatus());
    }

    @Test
    public void testClientGetMoney() {
        assertEquals(500, mari.getBalance());
        assertEquals(200, juri.getBalance());
    }

    @Test
    public void testClientGetPackages() {
        assertEquals(new HashSet<>(), mari.getPackages());
        assertEquals(new HashSet<>(), juri.getPackages());
    }

    @Test
    public void testClientSetMoney() {
        mari.setBalance(400);
        juri.setBalance(300);
        assertEquals(100, mari.getBalance());
        assertEquals(-100, juri.getBalance());
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


        assertTrue(mari.buyPackage(estonia, travelAgency));
        assertTrue(mari.buyPackage(italy, travelAgency));
        assertTrue(mari.buyPackage(africa, travelAgency));
        assertTrue(mari.buyPackage(france, travelAgency));
        assertTrue(mari.buyPackage(america, travelAgency));
    }

    @Test
    public void testClientBuyPackageButSheAlreadyBoughtIt() {
        travelAgency.addTravelPackages(italy);

        assertTrue(mari.buyPackage(italy, travelAgency));
        assertFalse(mari.buyPackage(italy, travelAgency));
    }

    @Test
    public void testClientBuyPackageButTravelAgencyDoesntSellThis() {
        assertFalse(mari.buyPackage(italy, travelAgency));
    }

    @Test
    public void testClientBuyPackageButDoesntHaveEnoughMoney() {
        assertFalse(juri.buyPackage(france, travelAgency));
    }

    @Test
    public void testClientBuyPackageWhenStatusIsRegular() {
        travelAgency.addTravelPackages(france);

        assertEquals(500, mari.getBalance());

        mari.buyPackage(france, travelAgency);

        assertEquals(200, mari.getBalance());
    }

    @Test
    public void testClientBuyPackageWhenStatusIsSilverAndPackagesAreMoreThanFiveDays() {
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);
        travelAgency.addTravelPackages(japan);

        assertEquals(500, mari.getBalance());

        mari.setPackages(italy);
        mari.setPackages(america);
        mari.setPackages(japan);
        mari.buyPackage(france, travelAgency);
        mari.buyPackage(estonia, travelAgency);
        mari.buyPackage(africa, travelAgency);

        assertEquals(123, mari.getBalance());
    }

    @Test
    public void testClientBuyPackageWhenStatusIsGoldAndPackagesAreMoreThanFiveDays() {
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);
        travelAgency.addTravelPackages(japan);

        assertEquals(500, mari.getBalance());

        mari.setPackages(italy);
        mari.setPackages(america);
        mari.setPackages(japan);
        mari.setPackages(estonia);
        mari.setPackages(africa);
        mari.buyPackage(france, travelAgency);

        assertEquals(230, mari.getBalance());
    }

    @Test
    public void testClientBuyPackageButNotEnoughMoney() {
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);
        travelAgency.addTravelPackages(japan);

        assertEquals(0, tonu.getBalance());
        assertEquals(0, kalle.getBalance());
        assertEquals(0, malle.getBalance());

        tonu.setPackages(italy);
        tonu.setPackages(america);
        tonu.setPackages(japan);
        tonu.setPackages(estonia);
        tonu.setPackages(africa);
        kalle.setPackages(italy);
        kalle.setPackages(america);
        kalle.setPackages(japan);

        assertFalse(tonu.buyPackage(france, travelAgency));
        assertFalse(kalle.buyPackage(france, travelAgency));
        assertFalse(malle.buyPackage(france, travelAgency));
    }
}
