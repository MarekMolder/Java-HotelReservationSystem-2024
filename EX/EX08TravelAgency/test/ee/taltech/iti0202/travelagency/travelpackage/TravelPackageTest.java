package ee.taltech.iti0202.travelagency.travelpackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TravelPackageTest {

    private TravelPackage italy;
    private TravelPackage france;
    @BeforeEach
    void setUp() {
        italy = new TravelPackage("italy", 50, LocalDate.of(2024, 3, 21),
                LocalDate.of(2024, 3, 26), "Italy", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);
        france = new TravelPackage("france", 300, LocalDate.of(2024, 3, 19),
                LocalDate.of(2024, 3, 26), "France", new ArrayList<>(List.of("sightseeing")), EPackageType.BEACHVACATION);
    }
    @Test
    void getId() {
        assertNotEquals(italy.getId(), france.getId());
    }

    @Test
    void getName() {
        assertEquals("italy", italy.getName());
        assertEquals("france", france.getName());
    }

    @Test
    void getPrice() {
        assertEquals(50, italy.getPrice());
        assertEquals(300, france.getPrice());
    }

    @Test
    void getSince() {
        assertEquals(LocalDate.of(2024, 3, 21), italy.getSince());
        assertEquals(LocalDate.of(2024, 3, 19), france.getSince());
    }

    @Test
    void getUntil() {
        assertEquals(LocalDate.of(2024, 3, 26), italy.getUntil());
        assertEquals(LocalDate.of(2024, 3, 26), france.getUntil());
    }

    @Test
    void getCountry() {
        assertEquals("Italy", italy.getCountry());
        assertEquals("France", france.getCountry());
    }

    @Test
    void getActivities() {
        assertEquals(new ArrayList<>(List.of("eating")), italy.getActivities());
        assertEquals(new ArrayList<>(List.of("sightseeing")), france.getActivities());
    }

    @Test
    void getType() {
        assertEquals(EPackageType.CULTURALTRIP, italy.getType());
        assertEquals(EPackageType.BEACHVACATION, france.getType());
    }

    @Test
    void getTravelDuration() {
        assertEquals(5, italy.getTravelDuration());
        assertEquals(7, france.getTravelDuration());
    }
}
