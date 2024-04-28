package ee.taltech.iti0202.hotel.hotel;

public class HotelBuilder {
    private ECountryAndCitys country;
    private String city;

    public HotelBuilder setCountry(ECountryAndCitys country) {
        this.country = country;
        return this;
    }

    public HotelBuilder setCity(String city) {
        if (isValidCity(city.toLowerCase())) {
            this.city = city;
        } else {
            System.out.println("Error: The city does not belong to the selected country.");
        }
        return this;
    }

    private boolean isValidCity(String city) {
        for (String validCity : country.getCity()) {
            if (validCity.equals(city)) {
                return true;
            }
        }
        return false;
    }

    public Hotel createHotel() {
        if (country == null || city == null) {
            throw new IllegalStateException("You must provide country and city");
        }
        return new Hotel(country, city);
    }
}