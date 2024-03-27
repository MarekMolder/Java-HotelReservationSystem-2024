package ee.taltech.iti0202.hotel.hotel;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a hotel from which you can purchase bookings.
 */
public class Hotel {

    private Set<Room> hotelRooms = new HashSet<>(); //a set of the rooms in hotel
    private Set<Client> hotelClients = new HashSet<>(); //a set of the clients in hotel
    private Map<Client, Integer> hotelClientBookings = new HashMap<>();
    // a map of clients to the number of bookings they have made at the hotel

    private Map<Client, List<Object>> hotelReviews = new HashMap<>();
    // a map of clients and reviews they have made to hotel

    private Map<Client, Integer> hotelReviewsScores = new HashMap<>();
    // a map of clients and scores they have given for the hotel

    private Set<Booking> hotelBookings = new HashSet<>(); // a set of the bookings

    /**
     * This method is used to get rooms in a hotel
     * @return A set of rooms in hotel.
     */
    public Set<Room> getHotelRooms() {
        return hotelRooms;
    }

    /**
     * This method is used to get clients in a hotel.
     * @return A set of clients in hotel.
     */
    public Set<Client> getHotelClients() {
        return hotelClients;
    }

    /**
     * This method is used to get map of clients and number of their bookings.
     * @return A map of clients and number of their bookings.
     */
    public Map<Client, Integer> getHotelClientBookings() {
        return hotelClientBookings;
    }

    /**
     * The method is used to get a map of clients and their reviews.
     * @return A map of clients and their reviews.
     */
    public Map<Client, List<Object>> getHotelReviews() {
        return hotelReviews;
    }

    /**
     * The method is used to get a map of hotel clients and their review score.
     * @return A map of hotel clients and their review score.
     */
    public Map<Client, Integer> getHotelReviewsScores() {
        return hotelReviewsScores;
    }

    /**
     * This method is used to get a set of bookings in hotel.
     * @return A set of bookings.
     */
    public Set<Booking> getHotelBookings() {
        return hotelBookings;
    }

    /**
     * This method is used to add room to hotel.
     * @param room The room to be added in hotel.
     * @return True if the room is not in another hotel, otherwise false.
     */
    public Boolean addRoomToHotel(Room room) {
        if (room != null) {
            if (room.setHotel(this)) {
                hotelRooms.add(room);
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to get an arithmetic of the score.
     * @return Arithmetic score.
     */
    public Double getReviewsArithmeticScore() {
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

    /**
     * This method is used to Look up free rooms of a specific type within a given date range.
     * @param room The type of room to be looked up.
     * @param since The start date of the booking period.
     * @param until The end date of the booking period.
     * @return A set of rooms that are available within the specified date range.
     */
    public Set<Room> lookUpFreeRoomsType(Class room, LocalDate since, LocalDate until) {
        Set<Room> hotelSearchRoom = new LinkedHashSet<>();
        for (Room suit : hotelRooms) {
            if (room.equals(suit.getClass())) {
                if (isRoomAvailable(since, until, suit)) {
                    hotelSearchRoom.add(suit);
                }
                    }
                }
        Set<Room> result = new LinkedHashSet<>(hotelSearchRoom);
        hotelSearchRoom.clear();
        return result;
    }

    /**
     * This method is used to look up free rooms within a given date range.
     * @param since The start date of the booking period.
     * @param until The end date of the booking period
     * @return A set of rooms that are available within the specific date range.
     */
    public Set<Room> lookUpFreeRoomDate(LocalDate since, LocalDate until) {
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

    /**
     * This method is used to look up if specific room is available within a given date range.
     * @param since The start date of the booking period.
     * @param until The end date of the booking period.
     * @param room The room to be looked up
     * @return True if the room is available, otherwise false.
     */
    public boolean isRoomAvailable(LocalDate since, LocalDate until, Room room) {
        List<Room> notAvailable = new ArrayList<>();
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

    /**
     * This method is used to retrieve a list of dates within the specific range.
     * @param since The start date of the range.
     * @param until The end date of the range.
     * @return A list of dates within the specific range.
     */
    public List<LocalDate> getDatesInRange(LocalDate since, LocalDate until) {
        List<LocalDate> datesInRange = new ArrayList<>();
        while (!since.isAfter(until)) {
            datesInRange.add(since);
            since = since.plusDays(1);
        }
        return datesInRange;
    }

    /**
     * This method is used to Sort clients based on their booking count and review scores.
     * @return A list of clients sorted in descending order by their booking count and then by their review scores.
     */
    public List<Client> sortClients() {
        return hotelClientBookings.entrySet().stream()
                .sorted(Map.Entry.<Client, Integer>comparingByValue().reversed()
                        .thenComparing((e1, e2) ->
                                hotelReviewsScores.get(e2.getKey()).compareTo(hotelReviewsScores.get(e1.getKey()))))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
