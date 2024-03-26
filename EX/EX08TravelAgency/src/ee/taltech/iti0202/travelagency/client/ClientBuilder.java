package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.util.Set;

public class ClientBuilder {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private Integer phoneNumber;
    private String homeAddress;
    private EPersonStatus status;
    private Integer money;
    private Set<TravelPackage> packages;

    /**
     * Sets the ID of the client.
     *
     * @param id The ID of the client.
     * @return This ClientBuilder instance.
     * @throws IllegalArgumentException if the provided ID is null.
     */
    public ClientBuilder setId(Integer id) {
        if (id != null) {
            this.id = id;
            return this;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Sets the name of the client.
     *
     * @param name The name of the client.
     * @return This ClientBuilder instance.
     * @throws IllegalArgumentException if the provided name is null.
     */
    public ClientBuilder setName(String name) {
        if (name != null) {
            this.name = name;
            return this;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Sets the email of the client.
     *
     * @param email The email of the client.
     * @return This ClientBuilder instance.
     * @throws IllegalArgumentException if the provided email is null.
     */
    public ClientBuilder setEmail(String email) {
        if (email != null) {
            this.email = email;
            return this;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Sets the age of the client.
     *
     * @param age The age of the client.
     * @return This ClientBuilder instance.
     * @throws IllegalArgumentException if the provided age is null or less than or equal to 0.
     */
    public ClientBuilder setAge(Integer age) {
        if (age != null && age > 0) {
            this.age = age;
            return this;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Sets the phone number of the client.
     *
     * @param phoneNumber The phone number of the client.
     * @return This ClientBuilder instance.
     */
    public ClientBuilder setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * Sets the home address of the client.
     *
     * @param homeAddress The home address of the client.
     * @return This ClientBuilder instance.
     */
    public ClientBuilder setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    /**
     * Sets the money of the client.
     *
     * @param money The amount of money the client has.
     * @return This ClientBuilder instance.
     */
    public ClientBuilder setMoney(Integer money) {
        this.money = money;
        return this;
    }

    /**
     * Constructs a Client object with the provided attributes.
     *
     * @return The constructed Client object.
     */
    public Client createClient() {
        return new Client(id, name, email, age, phoneNumber, homeAddress, money);
    }
}
