package ee.taltech.iti0202.hotel.hotel;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.time.LocalDate;
import java.util.*;

public class Hotel {

    public Set<Room> hotelRooms = new HashSet<>();
    public Set<Client> hotelClients = new HashSet<>();
    public Map<Client, Integer> hotelClientBooking = new HashMap<>();

    public Map<Client, List<Object>> hotelReviews = new HashMap<>();

    public ArrayList<Booking> hotelBookings = new ArrayList<>();


    public Boolean addRoomToHotel(Room room) {
        if (room != null) {
            if (room.setHotel(this)) {
                hotelRooms.add(room);
                return true;
            }
        }
        return false;
    }

    public Set<Room> getRooms() {
        return hotelRooms;
    }

    public Set<Client> getClients() {
        return hotelClients;
    }

    public Map<Client, List<Object>> getHotelReviews() {
        return hotelReviews;
    }


    public Integer getReviewsScore() {
        int sum = 0;
        int count = 0;
        for (List<Object> score : hotelReviews.values()) {
            sum += (int) score.get(1);
            count++;
        }
        return sum / count;
    }

    public ArrayList<Booking> getBooking() {
        return hotelBookings;
    }

    public List<Room> LookUpFreeRoomType(Class room, LocalDate date) {
        List<Room> hotelSearchRoom = new ArrayList<>();
        for (Room suit : hotelRooms) {
            if (room.equals(suit.getClass())) {
                for (Booking booking : hotelBookings) {
                    if (booking.getRoom() != suit && booking.getDate() != date) {
                        hotelSearchRoom.add(suit);
                    }
                }
            }
        }
        List<Room> result = new ArrayList<>(hotelSearchRoom);
        hotelSearchRoom.clear();
        return result;
    }

    public List<Room> LookUpFreeRoomDate(LocalDate date) {
        List<Room> hotelSearchRoom = new ArrayList<>();
        for (Room suit : hotelRooms) {
            for (Booking booking : hotelBookings) {
                if (booking.getRoom() != suit && booking.getDate() != date) {
                    hotelSearchRoom.add(suit);
                }
            }
        }
        List<Room> result = new ArrayList<>(hotelSearchRoom);
        hotelSearchRoom.clear();
        return result;
    }

    /**
     * Vaja teha.
     *
     * @return
     */
    public void sortClients() {
    }
}
