package ee.taltech.iti0202.hotel.hotel;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.time.LocalDate;
import java.util.*;

public class Hotel {

    public Set<Room> hotelRooms = new HashSet<>();
    public Map<Client, Integer> hotelClientsWithScore = new HashMap<>();
    public List<Client> hotelClients = new ArrayList<>();
    public Map<Client, Integer> bestClient = new HashMap<>();

    public Map<String, Integer> hotelReviewsAnonymus = new HashMap<>();

    public Map<Client, Integer> hotelReviews = new HashMap<>();

    public ArrayList<Booking> hotelBooked = new ArrayList<>();


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

    public Map<Client, Integer> getClientsWithScore() {
        return hotelClientsWithScore;
    }

    public List<Client> getClients() {
        return hotelClients;
    }

    public Map<String, Integer> getReviews() {
        return hotelReviewsAnonymus;
    }

    public Integer getReviewsScore() {
        int sum = hotelReviews.values().stream().mapToInt(i -> i).sum();
        return sum / hotelReviews.size();
    }

    public ArrayList<Booking> getBooking() {
        return hotelBooked;
    }

    /*
    Class muuda standardroomiks, kasutada castingut
     */
    public List<Room> LookUpFreeRoomType(Class room, LocalDate date) {
        List<Room> hotelSearchRoom = new ArrayList<>();
        for (Room suit : hotelRooms) {
            if (room.equals(suit.getClass())) {
                for (Booking booking : hotelBooked) {
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
            for (Booking booking : hotelBooked) {
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
