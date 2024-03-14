package ee.taltech.iti0202.hotel.booking;

import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    Room room1 = new Room();
    Hotel hotel = new Hotel();
    Client client1 = new Client("Mati", 200);

    @Test
    public void testBookingConstructorMethods() {
        hotel.addRoomToHotel(room1);

        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), hotel);
        assertTrue(booking1.isPresent());

        Optional<Booking> booking2 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), hotel);
        assertFalse(booking2.isPresent());
    }

    @Test
    public void testBookingGetMethod() {
        hotel.addRoomToHotel(room1);
        Optional<Booking> booking1 = client1.bookRoom(room1, LocalDate.of(2022, 4, 12), hotel);

        Assertions.assertEquals(room1, booking1.get().getRoom(), "Booking getRoom is wrong");
        Assertions.assertEquals(LocalDate.of(2022, 4, 12), booking1.get().getDate(), "Booking getDate is wrong");
        Assertions.assertEquals(client1, booking1.get().getClient(), "Booking getClient is wrong");
    }
}