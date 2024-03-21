package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.travelagency.TravelAgency;
import ee.taltech.iti0202.travelagency.travelpackage.TravelPackage;

import java.util.HashSet;
import java.util.Set;

public class Client {
    private final Integer id;
    private final String name;
    private final String email;
    private final Integer age;
    private final Integer phoneNumber;
    private final String homeAddress;
    private EPersonStatus status;
    private Integer money;
    private final Set<TravelPackage> packages;

    Client(Integer id, String name, String email, Integer age, Integer phoneNumber, String homeAddress, Integer money) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.status = EPersonStatus.REGULAR;
        this.money = money;
        this.packages = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public EPersonStatus getStatus() {
        if (packages.size() >= 5) {
            status = EPersonStatus.GOLD;
        } else if (packages.size() >= 3) {
            status = EPersonStatus.SILVER;
        }
        return status;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer price) {
        money -= price;
    }

    public Set<TravelPackage> getPackages() {
        return packages;
    }

    public void setPackages(TravelPackage travelPackage) {
        packages.add(travelPackage);
    }

    public boolean buyPackage(TravelPackage travelPackage) {
        if (!TravelAgency.travelAgencyPackages.contains(travelPackage) || this.money < travelPackage.getPrice() || this.packages.contains(travelPackage)) {
            return false;
        }

        double price = travelPackage.getPrice();

        if (this.getStatus() == EPersonStatus.REGULAR) {
            if (this.money >= price) {
                this.setMoney(travelPackage.getPrice());
            } else {
                return false;
            }
        } else if (this.getStatus() == EPersonStatus.SILVER && travelPackage.getTravelDuration() >= 5) {
            if (this.money >= price * 0.95) {
                this.setMoney((int) (price * 0.95));
            } else {
                return false;
            }
        } else if (this.getStatus() == EPersonStatus.GOLD && travelPackage.getTravelDuration() >= 5) {
            if (this.money >= price * 0.9) {
                this.setMoney((int) (price * 0.9));
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
