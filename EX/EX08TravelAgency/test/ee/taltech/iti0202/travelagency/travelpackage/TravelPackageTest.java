package ee.taltech.iti0202.travelagency.travelpackage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TravelPackageTest {
    TravelPackage italy = new TravelPackage("italy", 50, LocalDate.of(2024, 3,21),
            LocalDate.of(2024, 3, 26), "Italy", new ArrayList<>(List.of("eating")), EPackageType.CULTURALTRIP);

    TravelPackage france = new TravelPackage("france", 300, LocalDate.of(2024, 3,19),
            LocalDate.of(2024, 3, 26), "France", new ArrayList<>(List.of("sightseeing")), EPackageType.BEACHVACATION);

    @Test
    public void testTravelPackageGetName() {
        assertEquals("italy", italy.getName());
        assertEquals("france", france.getName());
    }

    @Test
    public void testTravelPackageGetId() {
        assertEquals(3, italy.getId());
        assertEquals(4, france.getId());
    }

    @Test
    public void testTravelPackageGetPrice() {
        assertEquals(50, italy.getPrice());
        assertEquals(300, france.getPrice());
    }

    @Test
    public void testTravelPackageGetSince() {
        assertEquals(LocalDate.of(2024, 3,21), italy.getSince());
        assertEquals(LocalDate.of(2024, 3,19), france.getSince());
    }

    @Test
    public void testTravelPackageGetUntil() {
        assertEquals(LocalDate.of(2024, 3, 26), italy.getUntil());
        assertEquals(LocalDate.of(2024, 3, 26), france.getUntil());
    }

    @Test
    public void testTravelPackageGetCountry() {
        assertEquals("Italy", italy.getCountry());
        assertEquals("France", france.getCountry());
    }

    @Test
    public void testTravelPackageGetActivities() {
        assertEquals(new ArrayList<>(List.of("eating")), italy.getActivities());
        assertEquals(new ArrayList<>(List.of("sightseeing")), france.getActivities());
    }

    @Test
    public void testTravelPackageGetType() {
        assertEquals(EPackageType.CULTURALTRIP, italy.getType());
        assertEquals(EPackageType.BEACHVACATION, france.getType());
    }

    @Test
    public void testTravelPackageGetTravelDuration() {
        assertEquals(5, italy.getTravelDuration());
        assertEquals(7, france.getTravelDuration());
    }
}