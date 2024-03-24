package ee.taltech.iti0202.hotel.hotel;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Hotel {

    public Set<Room> hotelRooms = new HashSet<>();
    public Set<Client> hotelClients = new HashSet<>();
    public Map<Client, Integer> hotelClientBooking = new HashMap<>();

    public Map<Client, List<Object>> hotelReviews = new HashMap<>();

    public Map<Client, Integer> hotelReviewsScores = new HashMap<>();

    public Set<Booking> hotelBookings = new HashSet<>();


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


    public Double getReviewsScore() {
        int sum = 0;
        int count = 0;
        for (Integer score : hotelReviewsScores.values()) {
            sum += score;
            count++;
        }
        if (count == 0) {
            return 0.0;
        } else {
            return ((double) sum / count);
        }
    }

    public Set<Booking> getBooking() {
        return hotelBookings;
    }

    public Set<Room> LookUpFreeRoomsType(Class room, LocalDate since, LocalDate until) {
        Set<Room> hotelSearchRoom = new LinkedHashSet<>();
        for (Room suit : hotelRooms) {
            if (room.equals(suit.getClass())) {
                if (isRoomAvailable(since, until, suit))
                        hotelSearchRoom.add(suit);
                    }
                }
        Set<Room> result = new LinkedHashSet<>(hotelSearchRoom);
        hotelSearchRoom.clear();
        return result;
    }

    public Set<Room> LookUpFreeRoomDate(LocalDate since, LocalDate until) {
        Set<Room> hotelSearchRoom = new LinkedHashSet<>();
        for (Room suit : hotelRooms) {
            if (isRoomAvailable(until, since, suit)) {
                hotelSearchRoom.add(suit);
            }
        }
        Set<Room> result = new LinkedHashSet<>(hotelSearchRoom);
        hotelSearchRoom.clear();
        return result;
    }

    public boolean isRoomAvailable(LocalDate since, LocalDate until, Room room) {
        List<Room> notAvailable= new ArrayList<>();
            for (Booking booking: hotelBookings) {
                if (booking.getRoom().equals(room)) {
                   List<LocalDate> dateList = booking.getDatesInRange(booking.getSince(), booking.getUntil());
                        for (LocalDate date: dateList) {
                            if (this.getDatesInRange(since, until).contains(date)) {
                                notAvailable.add(room);
                                break;
                            }
                        }
                    }
                }
        return notAvailable.isEmpty();
    }

    public List<LocalDate> getDatesInRange(LocalDate since, LocalDate until) {
        List<LocalDate> datesInRange = new ArrayList<>();
        while (!since.isAfter(until)) {
            datesInRange.add(since);
            since = since.plusDays(1);
        }
        return datesInRange;
    }

    /**
     * Vaja teha.
     *
     * @return
     */

    public List<Client> sortClients() {
        return hotelClientBooking.entrySet().stream()
                .sorted(Map.Entry.<Client, Integer>comparingByValue().reversed()
                        .thenComparing((e1, e2) -> hotelReviewsScores.get(e2.getKey()).compareTo(hotelReviewsScores.get(e1.getKey()))))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
