package ee.taltech.iti0202.socialnetwork.admin;

import ee.taltech.iti0202.socialnetwork.SocialNetwork;
import ee.taltech.iti0202.socialnetwork.user.User;

public class Admin extends User {
    private Integer age;
    
    public Admin(String name) {
        super(name);
    }

    public Admin(String name, Integer age) {
        super(name);
        this.age = age;
    }

    public void banUserFromSocialNetwork(User user, SocialNetwork socialNetwork) {
        if (!(user instanceof Admin)) {
            socialNetwork.banUser(user);
        }
    }

    public String getName() {
        return "(Admin) " + super.getName();
    }

    public Integer getAge() {
        return age;
    }
}