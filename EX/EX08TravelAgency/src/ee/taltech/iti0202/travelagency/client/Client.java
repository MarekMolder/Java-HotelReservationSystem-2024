package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
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

    Client(Integer id, String name, String email, Integer age, Integer phoneNumber, String homeAddress, Integer balance) {
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

    public Integer getId() {
        logger.log(Level.INFO, "Return client Id");
        return id;
    }

    public String getName() {
        logger.log(Level.INFO, "Return client name");
        return name;
    }

    public String getEmail() {
        logger.log(Level.INFO, "Return client Email");
        return email;
    }

    public Integer getAge() {
        logger.log(Level.INFO, "Return client age");
        return age;
    }

    public Integer getPhoneNumber() {
        logger.log(Level.INFO, "Return Client phone number");
        return phoneNumber;
    }

    public String getHomeAddress() {
        logger.log(Level.INFO, "Return Client home address");
        return homeAddress;
    }

    public EPersonStatus getStatus() {
        logger.log(Level.INFO, "Return Client status");
        if (packages.size() >= 5) {
            status = EPersonStatus.GOLD;
        } else if (packages.size() >= 3) {
            status = EPersonStatus.SILVER;
        }
        return status;
    }

    public Integer getBalance() {
        logger.log(Level.INFO, "Return Client balance");
        return balance;
    }

    public void setBalance(Integer price) {
        logger.log(Level.INFO, "Set client balance");
        balance -= price;
    }

    public Set<TravelPackage> getPackages() {
        logger.log(Level.INFO, "Return Client packages");
        return packages;
    }

    public void setPackages(TravelPackage travelPackage) {
        logger.log(Level.INFO, "Set client packages");
        packages.add(travelPackage);
    }

    public boolean buyPackage(TravelPackage travelPackage) {
        logger.log(Level.INFO, "client buys a package");
        if (!TravelAgency.travelAgencyPackages.contains(travelPackage) || this.balance < travelPackage.getPrice() || this.packages.contains(travelPackage)) {
            return false;
        }

        double price = travelPackage.getPrice();

        if (this.getStatus() == EPersonStatus.REGULAR) {
            if (this.balance >= price) {
                this.setBalance(travelPackage.getPrice());
            } else {
                return false;
            }
        } else if (this.getStatus() == EPersonStatus.SILVER && travelPackage.getTravelDuration() >= 5) {
            if (this.balance >= price * 0.95) {
                this.setBalance((int) (price * 0.95));
            } else {
                return false;
            }
        } else if (this.getStatus() == EPersonStatus.GOLD && travelPackage.getTravelDuration() >= 5) {
            if (this.balance >= price * 0.9) {
                this.setBalance((int) (price * 0.9));
            } else {
                return false;
            }
        }
        TravelAgency.travelAgencyClients.add(this);
        this.setPackages(travelPackage);
        TravelAgency.topClient.merge(this, 1, Integer::sum);
        TravelAgency.topTravelPackages.merge(travelPackage, 1, Integer::sum);

        return true;
    }
}
