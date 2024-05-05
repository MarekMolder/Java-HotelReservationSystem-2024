package ee.taltech.iti0202.hotel.reservationSystem;

import ee.taltech.iti0202.hotel.booking.Booking;
import ee.taltech.iti0202.hotel.booking.BookingBuilder;
import ee.taltech.iti0202.hotel.client.Client;
import ee.taltech.iti0202.hotel.hotel.ECountryAndCitys;
import ee.taltech.iti0202.hotel.hotel.EServices;
import ee.taltech.iti0202.hotel.hotel.Hotel;
import ee.taltech.iti0202.hotel.rooms.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class ReservationSystem {

    public static final double ZERO_POINT_FIVE = 0.5;
    private DiscountStrategy strategy;
    private Set<Hotel> hotels = new HashSet<>(); //a set of hotels in reservation system

    /**
     * Reservation system that can use strategy.
     */
    public ReservationSystem() {
        this.strategy = null;
    }

    /**
     * This method is used to give reservation system a strategy.
     * @param strategy strategy
     */
    public void giveStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * This method is used to get a strategy.
     * @return strategy if reservation system has one, otherwise throws exception.
     */
    public DiscountStrategy getStrategy() throws Exception {
        if (this.strategy == null) {
            throw new Exception("Reservation system doesn't have strategy");
        }
        return this.strategy;
    }

    /**
     * This method is used to add hotel to reservation System
     * @param hotel hotel to be added
     * @return true if hotel is added to reservation system, otherwise false.
     */
    public Boolean addHotelToSystem(Hotel hotel) {
        if (hotel != null && !hotels.contains(hotel)) {
                this.hotels.add(hotel);
                return true;
            }
        return false;
    }

    /**
     * This method is used to get hotels.
     * @return set of hotels.
     */
    public Set<Hotel> getHotels() {
        return hotels;
    }

    /**
     * This method is used to filter out hotel based on country and city.
     * @param country country where hotel is situated
     * @param city city where hotel is situated
     * @return set of hotels that match the criteria.
     */
    public Set<Hotel> lookUpHotel(ECountryAndCitys country, String city) {
        Set<Hotel> hotelMatch = new LinkedHashSet<>();
        for (Hotel hotel: hotels) {
            if (hotel.getHotelCity().equals(city) && hotel.getHotelCountry().equals(country)) {
                hotelMatch.add(hotel);
            }
        }
        return hotelMatch;
    }

    /**
     * This method is used to filter out hotel based on city
     * @param city city where hotel is situated.
     * @return set of hotels that match the criteria.
     */
    public Set<Hotel> lookUpHotel(String city) {
        Set<Hotel> hotelMatch = new LinkedHashSet<>();
        for (Hotel hotel: hotels) {
            if (hotel.getHotelCity().equals(city)) {
                hotelMatch.add(hotel);
            }
        }
        return hotelMatch;
    }

    /**
     * This method is used to filter out hotel based on country
     * @param country country where hotel is situated.
     * @return set of hotels that match the criteria.
     */
    public Set<Hotel> lookUpHotel(ECountryAndCitys country) {
        Set<Hotel> hotelMatch = new LinkedHashSet<>();
        for (Hotel hotel: hotels) {
            if (hotel.getHotelCountry().equals(country)) {
                hotelMatch.add(hotel);
            }
        }
        return hotelMatch;
    }

    /**
     * This method is used to filter out hotel based on review score
     * @param score review score that hotels has.
     * @return set of hotels that match the criteria.
     */
    public Set<Hotel> lookUpHotel(Integer score) {
        Set<Hotel> hotelMatch = new LinkedHashSet<>();
        for (Hotel hotel: hotels) {
            if (hotel.getReviewsArithmeticScore() >= score) {
                hotelMatch.add(hotel);
            }
        }
        return hotelMatch;
    }

    /**
     * This method is used filter out hotel based on country, city and review score.
     * @param country country where hotel is situated.
     * @param city city where hotel is situated.
     * @param score review score that hotel has
     * @return set of hotels that match the criteria.
     */
    public Set<Hotel> lookUpHotel(ECountryAndCitys country, String city, Integer score) {
        Set<Hotel> hotelMatch = new LinkedHashSet<>();
        for (Hotel hotel: hotels) {
            if (hotel.getHotelCity().equals(city)
                    && hotel.getHotelCountry().equals(country)
                    && hotel.getReviewsArithmeticScore() >= score) {
                hotelMatch.add(hotel);
            }
        }
        return hotelMatch;
    }

    /**
     * This method is used to filter out hotel rooms based on price.
     * @param hotel hotel to be looked at.
     * @param price price.
     * @param client client.
     * @return set of rooms that match the criteria.
     */
    public Set<Room> lookUpHotelRooms(Hotel hotel, BigDecimal price, Client client) {
        Set<Room> hotelSearchRoom = new LinkedHashSet<>();
        for (Room suit : hotel.getHotelRooms()) {
            if (hotel.getHotelClients().size() >= 3) {
                if (suit.getPrice().multiply(BigDecimal
                        .valueOf(1 - (hotel.getDiscount(client)))).compareTo(price) <= 0) {
                    hotelSearchRoom.add(suit);
                }
            } else {
                if (suit.getPrice().compareTo(price) <= 0) {
                    hotelSearchRoom.add(suit);
                }
            }
        }
        return hotelSearchRoom;
    }

    /**
     * This method is used to book a room in a hotel for a specific duration.
     * @param room The room to be booked.
     * @param since The start date of the booking.
     * @param until The end date of the booking.
     * @param hotel The hotel where the room is to be booked.
     * @return Optional containing the booking object if conditions are met, otherwise Optional empty.
     */
    public Optional<Booking> bookRoomInHotel(Room room, LocalDate since, LocalDate until, Hotel hotel, Client client) {
        if (hotels.contains(hotel)
                && hotel.getHotelRooms().contains(room)
                && hotel.isRoomAvailable(since, until, room)) {

            double hotelDiscount = hotel.getDiscount(client);
            double reservationSystemDiscount = getDiscount(hotel, since, until);
            BigDecimal roomPrice = room.getPrice()
                    .multiply(BigDecimal.valueOf(hotel.getDatesInRange(since, until).size()));

            BigDecimal price = roomPrice.multiply(BigDecimal.valueOf(1 - (hotelDiscount + reservationSystemDiscount)));

                if (client.getBalance().compareTo(price) >= 0) {
                    Booking booking = new Booking(room, since, until, client);
                    hotel.getHotelBookings().add(booking);
                    client.getBookings().add(booking);
                    hotel.getHotelClients().add(client);
                    client.subtractBalance(price);
                    hotel.getHotelClientBookings().merge(client, 1, Integer::sum);

                    if (hotel.getMonthlyBookings().containsKey(since.getMonth())) {
                        hotel.getMonthlyBookings().merge(since.getMonth(), 1, Integer::sum);
                    } else {
                        hotel.getMonthlyBookings().put(since.getMonth(), 1);
                    }

                    return Optional.of(booking);
                }
            }
        return Optional.empty();
    }

    /**
     * This method is used to remove booking.
     * @param booking The booking object to be removed.
     * @param hotel The hotel where the booking is made.
     * @return True if the removal was successful, false otherwise.
     */
    public Boolean removeBookingInHotel(Booking booking, Hotel hotel, Client client) {
        if (hotels.contains(hotel)
                && hotel.getHotelBookings().contains(booking)
                && client.getBookings().contains(booking)) {

                client.getBookings().remove(booking);
                hotel.getHotelBookings().remove(booking);
                client.addBalance(booking.getPrice().multiply(BigDecimal.valueOf(ZERO_POINT_FIVE)));
                if (hotel.getHotelClientBookings().containsKey(client)
                        && hotel.getHotelClientBookings().get(client) > 1) {
                    hotel.getHotelClientBookings().merge(client, 1, Integer::min);
                    return true;
                } else {
                    hotel.getHotelClients().remove(client);
                    hotel.getHotelClientBookings().remove(client);
                    return true;
                }
            }
        return false;
    }

    /**
     * This method is used to book service for your booking.
     * @param booking booking which will get new service.
     * @param hotel hotel where booking is made.
     * @param client client who made the booking.
     * @param service service which will be bought.
     * @return new Booking if service is bought, empty booking if service wasn't bought.
     */
    public Optional<Booking> bookServices(Booking booking, Hotel hotel, Client client, EServices service) {
        if (hotels.contains(hotel)
                && hotel.getHotelBookings().contains(booking)
                && client.getBookings().contains(booking)
                && hotel.getServices().containsKey(service)
                && client.getBalance().compareTo(BigDecimal.valueOf(service.getPrice())) >= 0) {

            BookingBuilder builder = new BookingBuilder(booking);
            builder.addService(service);

            client.subtractBalance(BigDecimal.valueOf(service.getPrice()));
            Booking updatedBooking = builder.createBooking();
            client.updateBooking(booking, updatedBooking);
            hotel.updateBooking(booking, updatedBooking);
            client.getClientServices().add(service);

            return Optional.of(updatedBooking);
                }
        return Optional.empty();
    }

    /**
     * This method is used to get discount from strategy.
     * @param hotel hotel where booking is made.
     * @param since booking start date.
     * @param until booking end date.
     * @return discount
     */
    public double getDiscount(Hotel hotel, LocalDate since, LocalDate until) {
        if (strategy == null) {
            return 0.0;
        }

        if (strategy instanceof MonthDiscountStrategy) {
            return ((MonthDiscountStrategy) strategy).getDiscount(hotel, since, until);
        } else if (strategy instanceof TimeDiscountStrategy) {
            return ((TimeDiscountStrategy) strategy).getDiscount(hotel, since, until);
        } else {
            return ((BothDiscountStrategy) strategy).getDiscount(hotel, since, until);
        }
    }
}
