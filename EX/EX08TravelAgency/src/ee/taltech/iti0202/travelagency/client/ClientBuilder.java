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

    public ClientBuilder setId(Integer id) {
        if (id != null) {
            this.id = id;
            return this;
        }
        throw new IllegalArgumentException();
    }

    public ClientBuilder setName(String name) {
        if (name != null) {
            this.name = name;
            return this;
        }
        throw new IllegalArgumentException();
    }

    public ClientBuilder setEmail(String email) {
        if (name != null) {
            this.email = email;
            return this;
        }
        throw new IllegalArgumentException();
    }

    public ClientBuilder setAge(Integer age) {
        if (age != null && age > 0) {
            this.age = age;
            return this;
        }
        throw new IllegalArgumentException();
    }

    public ClientBuilder setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ClientBuilder setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public ClientBuilder setStatus(EPersonStatus status) {
        this.status = status;
        return this;
    }

    public ClientBuilder setMoney(Integer money) {
        this.money = money;
        return this;
    }

    public ClientBuilder setPackages(Set<TravelPackage> packages) {
        this.packages = packages;
        return this;
    }

    public Client createClient() {
        return new Client(id, name, email, age, phoneNumber, homeAddress, money);
    }
}