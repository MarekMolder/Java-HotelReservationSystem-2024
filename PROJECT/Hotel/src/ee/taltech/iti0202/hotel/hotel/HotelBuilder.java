package ee.taltech.iti0202.hotel.hotel;

public class HotelBuilder {
    private ECountryAndCitys country;
    private String city;

    /**
     * Sets the country for the hotel being built.
     * @param country the country to set for this hotel
     * @return this builder instance for chaining method calls
     */
    public HotelBuilder setCountry(ECountryAndCitys country) {
        this.country = country;
        return this;
    }

    /**
     * Sets the city for the hotel being built. The city is validated against the list of valid cities for the specified country.
     * If the city is not valid, an error message is printed and the city is not set.
     * @param city the city to set for this hotel
     * @return this builder instance for chaining method calls
     */
    public HotelBuilder setCity(String city) {
        if (isValidCity(city.toLowerCase())) {
            this.city = city;
        } else {
            System.out.println("Error: The city does not belong to the selected country.");
        }
        return this;
    }

    /**
     * Checks if the provided city is valid for the currently set country.
     * @param city the city to validate
     * @return true if the city is a valid city for the set country, false otherwise
     */
    private boolean isValidCity(String city) {
        for (String validCity : country.getCity()) {
            if (validCity.equals(city)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new Hotel instance using the current builder state.
     * This method checks if both the country and city have been set and are valid before creating the hotel.
     * @return the newly created Hotel instance
     * @throws IllegalStateException if the country or city have not been set or are invalid
     */
    public Hotel createHotel() {
        if (country == null || city == null) {
            throw new IllegalStateException("You must provide country and city");
        }
        return new Hotel(country, city);
    }
}
