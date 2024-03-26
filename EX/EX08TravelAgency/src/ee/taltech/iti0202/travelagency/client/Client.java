package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static final double SILVERPRICE = 0.95;
    public static final double GOLDPRICE = 0.9;
    public static final int DURATION = 5;
    private final Integer id;
    private final String name;
    private final String email;
    private final Integer age;
    private final Integer phoneNumber;
    private final String homeAddress;
    private EPersonStatus status;
    private Integer balance;
    private final Set<TravelPackage> packages;
    Logger logger = Logger.getLogger(Client.class.getName());

    Client(Integer id, String name, String email, Integer age,
           Integer phoneNumber, String homeAddress, Integer balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.status = EPersonStatus.REGULAR;
        this.balance = balance;
        this.packages = new HashSet<>();
        logger.log(Level.INFO, String.format("Created client with %d", id));
    }

    /**
     * Retrieves the ID of the client.
     *
     * @return The ID of the client.
     */
    public Integer getId() {
        logger.log(Level.INFO, "Return client Id");
        return id;
    }

    /**
     * Retrieves the name of the client.
     *
     * @return The name of the client.
     */
    public String getName() {
        logger.log(Level.INFO, "Return client name");
        return name;
    }

    /**
     * Retrieves the email of the client.
     *
     * @return The email of the client.
     */
    public String getEmail() {
        logger.log(Level.INFO, "Return client Email");
        return email;
    }

    /**
     * Retrieves the age of the client.
     *
     * @return The age of the client.
     */
    public Integer getAge() {
        logger.log(Level.INFO, "Return client age");
        return age;
    }

    /**
     * Retrieves the phone number of the client.
     *
     * @return The phone number of the client.
     */
    public Integer getPhoneNumber() {
        logger.log(Level.INFO, "Return Client phone number");
        return phoneNumber;
    }

    /**
     * Retrieves the home address of the client.
     *
     * @return The home address of the client.
     */
    public String getHomeAddress() {
        logger.log(Level.INFO, "Return Client home address");
        return homeAddress;
    }

    /**
     * Retrieves the status of the client based on the number of travel packages purchased.
     *
     * @return The status of the client.
     */
    public EPersonStatus getStatus() {
        logger.log(Level.INFO, "Return Client status");
        if (packages.size() >= 5) {
            status = EPersonStatus.GOLD;
        } else if (packages.size() >= 3) {
            status = EPersonStatus.SILVER;
        }
        return status;
    }

    /**
     * Retrieves the balance of the client.
     *
     * @return The balance of the client.
     */
    public Integer getBalance() {
        logger.log(Level.INFO, "Return Client balance");
        return balance;
    }

    /**
     * Sets the balance of the client.
     *
     * @param price The price to deduct from the balance.
     */
    public void setBalance(Integer price) {
        logger.log(Level.INFO, "Set client balance");
        balance -= price;
    }

    /**
     * Retrieves the travel packages purchased by the client.
     *
     * @return The set of travel packages purchased by the client.
     */
    public Set<TravelPackage> getPackages() {
        logger.log(Level.INFO, "Return Client packages");
        return packages;
    }

    /**
     * Adds a travel package to the client's purchased packages.
     *
     * @param travelPackage The travel package to be added.
     */
    public void setPackages(TravelPackage travelPackage) {
        logger.log(Level.INFO, "Set client packages");
        packages.add(travelPackage);
    }

    /**
     * Allows the client to buy a travel package from a travel agency.
     *
     * @param travelPackage The travel package to buy.
     * @param travelAgency The travel agency from which to buy the package.
     * @return true if the purchase is successful, false otherwise.
     */
    public boolean buyPackage(TravelPackage travelPackage, TravelAgency travelAgency) {
        logger.log(Level.INFO, "client buys a package");
        if (!travelAgency.travelAgencyPackages.contains(travelPackage) || this.packages.contains(travelPackage)) {
            return false;
        }

        double price = travelPackage.getPrice();

        if (this.getStatus() == EPersonStatus.REGULAR) {
            if (this.balance >= price) {
                this.setBalance(travelPackage.getPrice());
            } else {
                return false;
            }
        } else if (this.getStatus() == EPersonStatus.SILVER && travelPackage.getTravelDuration() >= DURATION) {
            if (this.balance >= price * SILVERPRICE) {
                this.setBalance((int) (price * SILVERPRICE));
            } else {
                return false;
            }
        } else if (this.getStatus() == EPersonStatus.GOLD && travelPackage.getTravelDuration() >= DURATION) {
            if (this.balance >= price * GOLDPRICE) {
                this.setBalance((int) (price * GOLDPRICE));
            } else {
                return false;
            }
        }
        travelAgency.travelAgencyClients.add(this);
        this.setPackages(travelPackage);
        travelAgency.topClient.merge(this, 1, Integer::sum);
        travelAgency.topTravelPackages.merge(travelPackage, 1, Integer::sum);

        return true;
    }
}
