package ee.taltech.iti0202.hotel.booking;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.DeluxeRoom;
import ee.taltech.iti0202.hotel.rooms.DoubleRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingTest {
    Room room1 = new Room();
    Room room2 = new DoubleRoom();
    Room room3 = new DeluxeRoom();
    Hotel hotel1 = new Hotel();
    private Client client1;
    private Client client2;
    private Client client3;
    @BeforeEach
    void setUp() {
        room1 = new Room();
        room2 = new DoubleRoom();
        room3 = new DeluxeRoom();
        hotel1 = new Hotel();
        client1 = new Client("Mati", 10000);
        client2 = new Client("Kati", 10000);
        client3 = new Client("Kalle", 10000);
    }

    @Test
    void bookingGetRoom() {
        // setup
        hotel1.addRoomToHotel(room1);

        // what to test?
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to expect?
        assertTrue(booking1.isPresent());
        assertEquals(room1, booking1.get().getRoom());
    }
    @Test
    void bookingGetSince() {
        // setup
        hotel1.addRoomToHotel(room1);

        // what to test?
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to expect?
        assertEquals(LocalDate.of(2022, 4, 12), booking1.get().getSince());
    }

    @Test
    void bookingGetUntil() {
        // setup
        hotel1.addRoomToHotel(room1);

        // what to test?
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to expect?
        assertEquals(LocalDate.of(2022, 4, 17), booking1.get().getUntil());
    }

    @Test
    void bookingGetClient() {
        // setup
        hotel1.addRoomToHotel(room1);

        // what to test?
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);

        // what to expect?
        assertEquals(client1, booking1.get().getClient());
    }

    @Test
    void bookingGetPrice() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);

        // what to test?
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
                // date range (6 days) * StandardRoom price (40) = 240

        Optional<Booking> booking2 = client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 13), hotel1);
                // date range (2 days) * StandardRoom price (80) = 160

        Optional<Booking> booking3 = client3.bookRoom(room3, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 30), hotel1);
                // date range (30 days) * StandardRoom price (150) = 4500

        Optional<Booking> booking4 = client1.bookRoom(room1, LocalDate.of(2024, 3, 29), LocalDate.of(2024, 4, 2), hotel1);
                // date range (5 days) * StandardRoom price (40) = 200


        // what to expect
        assertEquals(240, booking1.get().getPrice());
        assertEquals(160, booking2.get().getPrice());
        assertEquals(4500, booking3.get().getPrice());
        assertEquals(200, booking4.get().getPrice());
    }

    @Test
    void testBookingGetDatesInRange() {
        // setup
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);

        List<LocalDate> book1 = new ArrayList<>();
        book1.add(LocalDate.of(2022, 4, 12));
        book1.add(LocalDate.of(2022, 4, 13));
        book1.add(LocalDate.of(2022, 4, 14));
        book1.add(LocalDate.of(2022, 4, 15));
        book1.add(LocalDate.of(2022, 4, 16));
        book1.add(LocalDate.of(2022, 4, 17));

        List<LocalDate> book2 = new ArrayList<>();
        book2.add(LocalDate.of(2022, 4, 12));
        book2.add(LocalDate.of(2022, 4, 13));

        List<LocalDate> book3 = new ArrayList<>();
        book3.add(LocalDate.of(2023, 4, 12));
        book3.add(LocalDate.of(2023, 4, 13));
        book3.add(LocalDate.of(2023, 4, 14));

        List<LocalDate> book4 = new ArrayList<>();
        book4.add(LocalDate.of(2024, 3, 29));
        book4.add(LocalDate.of(2024, 3, 30));
        book4.add(LocalDate.of(2024, 3, 31));
        book4.add(LocalDate.of(2024, 4, 1));
        book4.add(LocalDate.of(2024, 4, 2));

        // What to test?
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel1);
        Optional<Booking> booking2 = client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 13), hotel1);
        Optional<Booking> booking3 = client3.bookRoom(room3, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel1);
        Optional<Booking> booking4 = client1.bookRoom(room1, LocalDate.of(2024, 3, 29), LocalDate.of(2024, 4, 2), hotel1);

        // What to expect?
        assertEquals(book1, booking1.get().getDatesInRange(booking1.get().getSince(), booking1.get().getUntil()));
        assertEquals(book2, booking2.get().getDatesInRange(booking2.get().getSince(), booking2.get().getUntil()));
        assertEquals(book3, booking3.get().getDatesInRange(booking3.get().getSince(), booking3.get().getUntil()));
        assertEquals(book4, booking4.get().getDatesInRange(booking4.get().getSince(), booking4.get().getUntil()));
    }
}
