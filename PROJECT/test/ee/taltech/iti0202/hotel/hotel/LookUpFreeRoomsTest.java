package ee.taltech.iti0202.hotel.hotel;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.reservationSystem.ReservationSystem;
import ee.taltech.iti0202.hotel.rooms.DoubleRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LookUpFreeRoomsTest {
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    private DoubleRoom room5;
    private DoubleRoom room6;
    private DoubleRoom room7;
    private Hotel hotel1;
    private Client client1;
    private Client client2;
    private ReservationSystem reservationSystem;


    @BeforeEach
    void setUp() {
        room1 = new Room();
        room2 = new Room();
        room3 = new Room();
        room4 = new DoubleRoom();
        room5 = new DoubleRoom();
        room6 = new DoubleRoom();
        room7 = new DoubleRoom();
        hotel1 = new HotelBuilder()
                .setCountry(ECountryAndCitys.ESTONIA)
                .setCity("Tallinn").createHotel();
        reservationSystem = new ReservationSystem();
        client1 = new Client("Mati", 10000);
        client2 = new Client("Mati", 10000);
    }

    @Test
    void lookUpFreeRoomsType() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);
        hotel1.addRoomToHotel(room7);
        reservationSystem.addHotelToSystem(hotel1);

        Set<Room> expected = new LinkedHashSet<>();
        expected.add(room4);
        expected.add(room5);
        expected.add(room6);
        expected.add(room7);

        // what to expect?
        Set<Room> actual = hotel1.lookUpFreeRoomsType(DoubleRoom.class, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17));
        assertEquals(expected, actual);

        // new setup
        reservationSystem.bookRoomInHotel(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        reservationSystem.bookRoomInHotel(room6, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client2);
        Set<Room> expected1 = new LinkedHashSet<>();
        expected1.add(room4);
        expected1.add(room7);

        // what to expect?
        Set<Room> actual1 = hotel1.lookUpFreeRoomsType(DoubleRoom.class, LocalDate.of(2022, 4, 15), LocalDate.of(2022, 4, 16));
        assertEquals(expected1, actual1);
    }

    @Test
    void lookUpFreeRoomDate() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room4);
        hotel1.addRoomToHotel(room5);
        hotel1.addRoomToHotel(room6);
        hotel1.addRoomToHotel(room7);
        reservationSystem.addHotelToSystem(hotel1);

        Set<Room> expected = new LinkedHashSet<>();
        expected.add(room1);
        expected.add(room2);
        expected.add(room3);
        expected.add(room4);
        expected.add(room5);
        expected.add(room6);
        expected.add(room7);

        // what to expect?
        Set<Room> actual = hotel1.lookUpFreeRoomDate(LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17));
        assertEquals(expected, actual);

        // new setup
        reservationSystem.bookRoomInHotel(room5, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client1);
        reservationSystem.bookRoomInHotel(room6, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1, client2);
        Set<Room> expected1 = new LinkedHashSet<>();
        expected1.add(room1);
        expected1.add(room2);
        expected1.add(room3);
        expected1.add(room4);
        expected1.add(room7);

        // what to expect?
        Set<Room> actual1 = hotel1.lookUpFreeRoomDate( LocalDate.of(2022, 4, 15), LocalDate.of(2022, 4, 16));
        assertEquals(expected1, actual1);
    }
}
