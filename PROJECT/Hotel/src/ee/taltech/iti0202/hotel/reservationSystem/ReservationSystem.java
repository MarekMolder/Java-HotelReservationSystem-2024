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

    private DiscountStrategy strategy;
    private Set<Hotel> hotels = new HashSet<>(); //a set of the hotels in reservation system

    public ReservationSystem() {
        this.strategy = null;
    }

    public void giveStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public Boolean addHotelToSystem(Hotel hotel) {
        if (hotel != null && !hotels.contains(hotel)) {
                this.hotels.add(hotel);
                return true;
            }
        return false;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public Set<Hotel> lookUpHotel(ECountryAndCitys country, String city) {
        Set<Hotel> hotelMatch = new LinkedHashSet<>();
        for (Hotel hotel: hotels) {
            if (hotel.getHotelCity().equals(city) && hotel.getHotelCountry().equals(country)) {
                hotelMatch.add(hotel);
            }
        }
        return hotelMatch;
    }

    public Set<Hotel> lookUpHotel(String city) {
        Set<Hotel> hotelMatch = new LinkedHashSet<>();
        for (Hotel hotel: hotels) {
            if (hotel.getHotelCity().equals(city)) {
                hotelMatch.add(hotel);
            }
        }
        return hotelMatch;
    }

    public Set<Hotel> lookUpHotel(ECountryAndCitys country) {
        Set<Hotel> hotelMatch = new LinkedHashSet<>();
        for (Hotel hotel: hotels) {
            if (hotel.getHotelCountry().equals(country)) {
                hotelMatch.add(hotel);
            }
        }
        return hotelMatch;
    }

    public Set<Hotel> lookUpHotel(Integer score) {
        Set<Hotel> hotelMatch = new LinkedHashSet<>();
        for (Hotel hotel: hotels) {
            if (hotel.getReviewsArithmeticScore() >= score) {
                hotelMatch.add(hotel);
            }
        }
        return hotelMatch;
    }

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

    public Set<Room> lookUpHotelRooms(Hotel hotel, BigDecimal price, Client client) {
        Set<Room> hotelSearchRoom = new LinkedHashSet<>();
        for (Room suit : hotel.getHotelRooms()) {
            if (hotel.getHotelClients().size() >= 3) {
                if (suit.getPrice().multiply(BigDecimal.valueOf(1 - (hotel.getDiscount(client)))).compareTo(price) <= 0) {
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
            BigDecimal roomPrice = room.getPrice().multiply(BigDecimal.valueOf(hotel.getDatesInRange(since, until).size()));

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
                client.addBalance(booking.getPrice().multiply(BigDecimal.valueOf(0.5)));
                if (hotel.getHotelClientBookings().containsKey(client) && hotel.getHotelClientBookings().get(client) > 1) {
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

    public Boolean bookServices(Booking booking, Hotel hotel, Client client, EServices service) {
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
            client.getClientServices().add(service);
            return true;
                }
        return false;
    }

    //    public Boolean bookServices(Booking booking, Hotel hotel, Client client, EServices service) {
    //        if (hotels.contains(hotel)
    //                && hotel.getHotelBookings().contains(booking)
    //                && client.getBookings().contains(booking)
    //                && hotel.getServices().containsKey(service)
    //                && client.getBalance().compareTo(BigDecimal.valueOf(service.getPrice())) >= 0) {
    //
    //                    client.subtractBalance(BigDecimal.valueOf(service.getPrice()));
    //                    booking.addService(service);
    //                    return true;
    //                }
    //        return false;
    //    }

    public Boolean removeService(Booking booking, Hotel hotel, Client client, EServices service) {
        if (hotels.contains(hotel)
                && hotel.getHotelBookings().contains(booking)
                && client.getBookings().contains(booking)
                && booking.getService().contains(service)) {

            client.addBalance(BigDecimal.valueOf(service.getPrice()));
            booking.removeService(service);
            client.getClientServices().remove(service);
            return true;
        }
        return false;
    }

    public double getDiscount(Hotel hotel, LocalDate since, LocalDate until) {
        if (strategy == null) {
            return 0.0;
        }

        if (strategy instanceof MonthDiscountStrategy) {
            return ((MonthDiscountStrategy) strategy).getDiscount(hotel, since, until);
        } else if (strategy instanceof TimeDiscountStrategy) {
            return ((TimeDiscountStrategy) strategy).getDiscount(hotel, since, until);
        } else if (strategy instanceof BothDiscountStrategy) {
            return ((BothDiscountStrategy) strategy).getDiscount(hotel, since, until);
        }
        return 0.0;
    }
}
