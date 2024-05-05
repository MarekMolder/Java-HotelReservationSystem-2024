package ee.taltech.iti0202.hotel.hotel;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.review.Review;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
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

    public static final double FIFTEEN = 0.15;
    public static final double TEN = 0.1;
    public static final double FIVE = 0.05;
    private final ECountryAndCitys country;
    // Country where hotel is situated
    private final String city;
    // City where hotel is situated
    private final HashMap<EServices, Integer> hotelServices;
    // a map of services and their prices that hotel provides
    private Set<Room> hotelRooms = new HashSet<>();
    // a set of the rooms in hotel
    private Set<Client> hotelClients = new HashSet<>();
    // a set of the clients in hotel
    private Map<Client, Integer> hotelClientBookings = new HashMap<>();
    // a map of clients to the number of bookings they have made at the hotel
    private Map<Client, Review> hotelReviews = new HashMap<>();
    // a map of clients and reviews they have made to hotel
    private List<Booking> hotelBookings = new ArrayList<>();
    // a set of the bookings
    private Map<Month, Integer> monthlyBookings = new HashMap<>();
    // a map of monthly bookings

    /**
     * Constructs a new hotel with country and city.
     * @param country country where hotel is situated.
     * @param city city where hotel is situated.
     */
    public Hotel(ECountryAndCitys country, String city) {
        this.country = country;
        this.city = city;
        this.hotelRooms = new HashSet<>();
        this.hotelClients = new HashSet<>();
        this.hotelClientBookings = new HashMap<>();
        this.hotelReviews = new HashMap<>();
        this.hotelBookings = new ArrayList<>();
        this.hotelServices = new HashMap<>();
        this.monthlyBookings = new HashMap<>();
    }

    /**
     * This method is used to get hotel country.
     * @return country where hotel is situated.
     */
    public ECountryAndCitys getHotelCountry() {
        return country;
    }

    /**
     * This method is used to get hotel city.
     * @return city where hotel is situated.
     */
    public String getHotelCity() {
        return city;
    }

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
    public Map<Client, Review> getHotelReviews() {
        return hotelReviews;
    }

    /**
     * This method is used to get a set of bookings in hotel.
     * @return A set of bookings.
     */
    public List<Booking> getHotelBookings() {
        return hotelBookings;
    }

    /**
     * This method is used to add service in to the hotel.
     * @param service service to be added.
     */
    public void addService(EServices service) {
        hotelServices.put(service, service.getPrice());
    }

    /**
     * This method is used to get all services that hotel provides.
     * @return services that hotel provides.
     */
    public HashMap<EServices, Integer> getServices() {
        return hotelServices;
    }

    /**
     * This method is used to get monthly bookings.
     * @return monthly bookings.
     */
    public Map<Month, Integer> getMonthlyBookings() {
        return monthlyBookings;
    }

    /**
     * This method is used to add room to hotel.
     * @param room The room to be added in hotel.
     * @return True if the room is not in another hotel, otherwise false.
     */
    public Boolean addRoomToHotel(Room room) {
        if (room != null) {
            if (room.addHotel(this)) {
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
        for (Review review : hotelReviews.values()) {
            int score = review.getScore();
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
     * @param room  The type of room to be looked up.
     * @param since The start date of the booking period.
     * @param until The end date of the booking period.
     * @return A set of rooms that are available within the specified date range.
     */
    public Set<Room> lookUpFreeRoomsType(Class<? extends Room> room, LocalDate since, LocalDate until) {
        Set<Room> hotelSearchRoom = new LinkedHashSet<>();
        for (Room suit : hotelRooms) {
            if (room.equals(suit.getClass()) && isRoomAvailable(since, until, suit)) {
                hotelSearchRoom.add(suit);
            }
        }
        return hotelSearchRoom;
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
            if (isRoomAvailable(since, until, suit)) {
                hotelSearchRoom.add(suit);
            }
        }
        return hotelSearchRoom;
    }

    /**
     * This method is used to look up free rooms within a given day of the week.
     * @param day day of the week to be looked up
     * @return A set of rooms that are available within the specific day of the week.
     */
    public Set<Room> lookUpFreeRoomDay(DayOfWeek day) {
        Set<Room> hotelSearchRoom = new LinkedHashSet<>();
        for (Room suit : hotelRooms) {
            if (isRoomAvailable(day, suit)) {
                hotelSearchRoom.add(suit);
            }
        }
        return hotelSearchRoom;
    }

    /**
     * This method is used to look up if specific room is available within a given date range.
     * @param since The start date of the booking period.
     * @param until The end date of the booking period.
     * @param room  The room to be looked up
     * @return True if the room is available, otherwise false.
     */
    public boolean isRoomAvailable(LocalDate since, LocalDate until, Room room) {
        List<Room> notAvailable = new ArrayList<>();
        for (Booking booking : hotelBookings) {
            if (booking.getRoom().equals(room)) {
                List<LocalDate> dateList = booking.getDatesInRange(booking.getSince(), booking.getUntil());
                for (LocalDate date : dateList) {
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
     * This method is used to look up if specific room is available within a given date range.
     * @param room  The room to be looked up
     * @return True if the room is available, otherwise false.
     */
    public boolean isRoomAvailable(DayOfWeek day, Room room) {
        List<Room> notAvailable = new ArrayList<>();
        for (Booking booking : hotelBookings) {
            if (booking.getRoom().equals(room)) {
                List<LocalDate> dateList = booking.getDatesInRange(booking.getSince(), booking.getUntil());
                for (LocalDate date : dateList) {
                    if (day.equals(date.getDayOfWeek())) {
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
     * This method is used to Sort clients based on their booking count, review scores and arithmetic cost of services.
     * @return A list of clients sorted in descending order by their booking count, then by their review scores
     * and then by their arithmetic cost of services.
     */
    public List<Client> sortClients() {
        return hotelClientBookings.entrySet().stream()
                .sorted(Map.Entry.<Client, Integer>comparingByValue().reversed()
                        .thenComparing((e1, e2) -> {
                            Integer score1 = hotelReviews.containsKey(e1.getKey())
                                    ? hotelReviews.get(e1.getKey()).getScore() : 0;
                            Integer score2 = hotelReviews.containsKey(e2.getKey())
                                    ? hotelReviews.get(e2.getKey()).getScore() : 0;
                            return Integer.compare(score2, score1);
                        })
                        .thenComparing((entry1, entry2) -> {
                            Client client1 = entry1.getKey();
                            Client client2 = entry2.getKey();
                            double score1 = hotelClients.contains(client1)
                                    ? client1.getClientServiceArithmetic() : 0.0;
                            double score2 = hotelClients.contains(client2)
                                    ? client2.getClientServiceArithmetic() : 0.0;
                            return Double.compare(score2, score1);
                        }))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to give discount for top 3 client.
     * @param client client who will get discount.
     * @return discount %.
     */
    public double getDiscount(Client client) {
        if (this.getHotelClients().size() >= 3) {
            List<Client> sort = sortClients().subList(0, 3);
            if (sort.getFirst().equals(client)) {
                return FIFTEEN;
            } else if (sort.get(1).equals(client)) {
                return TEN;
            } else if (sort.get(2).equals(client)) {
                return FIVE;
            }
        }
        return 0;
    }

    /**
     * This method is used when client is buying services then it updates hotel booking.
     * @param oldBooking old booking to be updated.
     * @param newBooking new booking to be added.
     * @return true if updating booking was successful, otherwise false.
     */
    public boolean updateBooking(Booking oldBooking, Booking newBooking) {
        int index = hotelBookings.indexOf(oldBooking);
        if (index != -1) {
            hotelBookings.set(index, newBooking);
            return true;
        }
        return false;
    }
}
