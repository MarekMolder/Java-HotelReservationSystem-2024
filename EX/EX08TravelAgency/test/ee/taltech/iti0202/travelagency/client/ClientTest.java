package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.travelpackage.EPackageType;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client mari;
    private Client juri;
    private Client tonu;
    private Client kalle;
    private Client malle;
    private TravelAgency travelAgency;
    private TravelPackage italy;
    private TravelPackage estonia;
    private TravelPackage france;
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

        tonu = new ClientBuilder()
                .setName("Tonu")
                .setEmail("Tonu@gmail.com")
                .setAge(30)
                .setMoney(0).createClient();

        kalle = new ClientBuilder()
                .setName("Kalle")
                .setEmail("Kalle@gmail.com")
                .setAge(18)
                .setMoney(0).createClient();

        malle = new ClientBuilder()
                .setName("Malle")
                .setEmail("Malle@gmail.com")
                .setAge(25)
                .setMoney(0).createClient();

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
    void getId() {
        assertNotEquals(juri.getId(), mari.getId());
        assertNotEquals(kalle.getId(), malle.getId());
        System.out.println(new ArrayList<>(Arrays.asList(mari.getId(), juri.getId(),
                tonu.getId(), malle.getId(), kalle.getId())));
    }

    @Test
    void getName() {
        assertEquals("Mari", mari.getName());
        assertEquals("Juri", juri.getName());
    }

    @Test
    void getName_clientDoesNotHaveName_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setName(null).createClient());
    }

    @Test
    void testClientGetEmail() {
        assertEquals("Mari@gmail.com", mari.getEmail());
        assertEquals("Juri@gmail.com", juri.getEmail());
    }

    @Test
    void getEmail_clientDoesNotHaveName_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setEmail(null).createClient());
    }

    @Test
    void getAge() {
        assertEquals(20, mari.getAge());
        assertEquals(25, juri.getAge());
    }

    @Test
    void getAge_clientDoesNotHaveAge_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().setAge(null).createClient());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(56561010, mari.getPhoneNumber());
        assertNull(juri.getPhoneNumber());
    }

    @Test
    void getHomeAddress() {
        assertEquals("Juhani-10", mari.getHomeAddress());
        assertNull(juri.getHomeAddress());
    }

    @Test
    void getStatus() {
        // setup
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);

        // what to expect?
        assertEquals(EPersonStatus.REGULAR, mari.getStatus());
        assertEquals(EPersonStatus.REGULAR, juri.getStatus());

        // new setup
        mari.buyPackage(italy, travelAgency);
        mari.buyPackage(estonia, travelAgency);
        mari.buyPackage(france, travelAgency);
        mari.buyPackage(america, travelAgency);
        mari.buyPackage(africa, travelAgency);
        juri.buyPackage(italy, travelAgency);
        juri.buyPackage(estonia, travelAgency);
        juri.buyPackage(africa, travelAgency);

        // what to expect?
        assertEquals(EPersonStatus.GOLD, mari.getStatus());
        assertEquals(EPersonStatus.SILVER, juri.getStatus());
    }

    @Test
    void getMoney() {
        assertEquals(500, mari.getBalance());
        assertEquals(200, juri.getBalance());
    }

    @Test
    void getPackages() {
        // setup
        juri.setPackages(italy);
        juri.setPackages(estonia);
        juri.setPackages(africa);

        assertEquals(new HashSet<>(), mari.getPackages());
        assertEquals(new HashSet<>(Arrays.asList(italy, estonia, africa)), juri.getPackages());
    }

    @Test
    void setMoney() {
        // setup
        mari.setBalance(400);
        // 500 - 400 = 100
        juri.setBalance(300);
        // 200 - 300 = -100

        // what to expect
        assertEquals(100, mari.getBalance());
        assertEquals(-100, juri.getBalance());
    }

    @Test
    void setPackages() {
        // what to test?
        mari.setPackages(estonia);
        mari.setPackages(africa);
        mari.setPackages(estonia);

        // what to expect?
        assertEquals(2, mari.getPackages().size());
        assertEquals(new HashSet<>(Arrays.asList(estonia, africa)), mari.getPackages());
    }

    @Test
    void buyPackage() {
        // setup
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);


        // what to expect
        assertTrue(mari.buyPackage(estonia, travelAgency));
        assertTrue(mari.buyPackage(italy, travelAgency));
    }

    @Test
    void buyPackage_clientAlreadyHasThisPackage_canNotBuyPackage() {
        // setup
        travelAgency.addTravelPackages(italy);

        // what to expect
        assertTrue(mari.buyPackage(italy, travelAgency));
        assertFalse(mari.buyPackage(italy, travelAgency));
    }

    @Test
    void buyPackage_travelAgencyDoesNotHaveThisPackage_canNotBuyPackage() {
        assertFalse(mari.buyPackage(italy, travelAgency));
    }

    @Test
    void buyPackage_clientDoesNotHaveEnoughMoney_canNotBuyPackage() {
        // juri have 200, france cost 300
        assertFalse(juri.buyPackage(france, travelAgency));
    }

    @Test
    void buyPackage_clientStatusIsRegular() {
        // setup
        travelAgency.addTravelPackages(france);

        // what to expect?
        assertEquals(500, mari.getBalance());
        mari.buyPackage(france, travelAgency);
        // 500 - 300 = 200
        assertEquals(200, mari.getBalance());
    }

    @Test
    void buyPackage_clientStatusIsSilver_PackagesDurationAreMoreThanFiveDays() {
        // setup
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);
        travelAgency.addTravelPackages(japan);
        mari.setPackages(italy);
        mari.setPackages(america);
        mari.setPackages(japan);

        assertEquals(500, mari.getBalance());

        mari.buyPackage(france, travelAgency);
        // 500 - 300 * 0.95 = 215
        mari.buyPackage(estonia, travelAgency);
        // 215 - 50 * 0.95 = 167.5

        // what to expect
        assertEquals(167.5, mari.getBalance());
    }

    @Test
    void buyPackage_clientStatusIsGold_PackagesDurationAreMoreThanFiveDays() {
        // setup
        travelAgency.addTravelPackages(italy);
        travelAgency.addTravelPackages(estonia);
        travelAgency.addTravelPackages(africa);
        travelAgency.addTravelPackages(france);
        travelAgency.addTravelPackages(america);
        travelAgency.addTravelPackages(japan);
        mari.setPackages(italy);
        mari.setPackages(america);
        mari.setPackages(japan);
        mari.setPackages(estonia);
        mari.setPackages(africa);

        assertEquals(500, mari.getBalance());

        mari.buyPackage(france, travelAgency);
        // 500 - 300 * 0.9 = 230

        // what to expect?
        assertEquals(230, mari.getBalance());
    }

    @Test
    void buyPackage_NotEnoughMoney_butTheyHaveStatus_canNotBuyPackage() {
        // setup
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

        // what to expect
        assertFalse(tonu.buyPackage(france, travelAgency));
        assertFalse(kalle.buyPackage(france, travelAgency));
        assertFalse(malle.buyPackage(france, travelAgency));
    }
}
