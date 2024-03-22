package ee.taltech.iti0202.hotel.booking;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.DeluxeRoom;
import ee.taltech.iti0202.hotel.rooms.DoubleRoom;
import ee.taltech.iti0202.hotel.rooms.Room;
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
    Hotel hotel = new Hotel();
    Client client1 = new Client("Mati", 10000);
    Client client2 = new Client("Juri", 10000);
    Client client3 = new Client("Kalle", 10000);

    @Test
    public void testBookingGetRoom() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room2);
        hotel.addRoomToHotel(room3);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client1.bookRoom(room2, LocalDate.of(2022, 5, 12), LocalDate.of(2022, 4, 13), hotel);
        Optional<Booking> booking3 = client1.bookRoom(room3, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 4, 14), hotel);

        assertTrue(booking1.isPresent());
        assertTrue(booking2.isPresent());
        assertTrue(booking3.isPresent());
        assertEquals(room1, booking1.get().getRoom());
        assertEquals(room2, booking2.get().getRoom());
        assertEquals(room3, booking3.get().getRoom());
    }
    @Test
    public void testBookingGetSince() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room2);
        hotel.addRoomToHotel(room3);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client1.bookRoom(room2, LocalDate.of(2022, 5, 12), LocalDate.of(2022, 4, 13), hotel);
        Optional<Booking> booking3 = client1.bookRoom(room3, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 4, 14), hotel);

        assertEquals(LocalDate.of(2022, 4, 12), booking1.get().getSince());
        assertEquals(LocalDate.of(2022, 5, 12), booking2.get().getSince());
        assertEquals(LocalDate.of(2023, 5, 12), booking3.get().getSince());
    }

    @Test
    public void testBookingGetUntil() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room2);
        hotel.addRoomToHotel(room3);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client1.bookRoom(room2, LocalDate.of(2022, 5, 12), LocalDate.of(2022, 4, 13), hotel);
        Optional<Booking> booking3 = client1.bookRoom(room3, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 4, 14), hotel);

        assertEquals(LocalDate.of(2022, 4, 17), booking1.get().getUntil());
        assertEquals( LocalDate.of(2022, 4, 13), booking2.get().getUntil());
        assertEquals(LocalDate.of(2023, 4, 14), booking3.get().getUntil());
    }


    @Test
    public void testBookingGetClient() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room2);
        hotel.addRoomToHotel(room3);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client2.bookRoom(room2, LocalDate.of(2022, 5, 12), LocalDate.of(2022, 4, 13), hotel);
        Optional<Booking> booking3 = client3.bookRoom(room3, LocalDate.of(2023, 5, 12), LocalDate.of(2023, 4, 14), hotel);

        assertEquals(client1, booking1.get().getClient());
        assertEquals(client2, booking2.get().getClient());
        assertEquals(client3, booking3.get().getClient());
    }

    @Test
    public void testBookingGetDatesInRange() {
        hotel.addRoomToHotel(room1);
        hotel.addRoomToHotel(room2);
        hotel.addRoomToHotel(room3);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 17), hotel);
        Optional<Booking> booking2 = client2.bookRoom(room2, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 4, 13), hotel);
        Optional<Booking> booking3 = client3.bookRoom(room3, LocalDate.of(2023, 4, 12), LocalDate.of(2023, 4, 14), hotel);
        Optional<Booking> booking4 = client1.bookRoom(room1, LocalDate.of(2024, 3, 29), LocalDate.of(2024, 4, 2), hotel);

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


        assertEquals(book1, booking1.get().getDatesInRange(booking1.get().getSince(), booking1.get().getUntil()));
        assertEquals(book2, booking2.get().getDatesInRange(booking2.get().getSince(), booking2.get().getUntil()));
        assertEquals(book3, booking3.get().getDatesInRange(booking3.get().getSince(), booking3.get().getUntil()));
        assertEquals(book4, booking4.get().getDatesInRange(booking4.get().getSince(), booking4.get().getUntil()));
    }
}