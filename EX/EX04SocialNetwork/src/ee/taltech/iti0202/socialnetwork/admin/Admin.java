package ee.taltech.iti0202.socialnetwork.admin;

import ee.taltech.iti0202.socialnetwork.SocialNetwork;
import ee.taltech.iti0202.socialnetwork.user.User;

public class Admin extends User {
    private Integer age;

    /**
     * Constructs a new admin with a given name.
     *
     * @param name The name of the admin.
     */
    public Admin(String name) {
        super(name);
    }

    /**
     * Constructs a new admin with a given name and age.
     *
     * @param name The name of the admin.
     * @param age  The age of the admin.
     */
    public Admin(String name, Integer age) {
        super(name);
        this.age = age;
    }

    /**
     * Bans a user from the social network if the user is not an admin.
     *
     * @param user           The user to be banned.
     * @param socialNetwork  The social network where the user will be banned.
     */
    public void banUserFromSocialNetwork(User user, SocialNetwork socialNetwork) {
        if (!(user instanceof Admin)) {
            socialNetwork.banUser(user);
        }
    }

    /**
     * Gets the name of the admin, with "(Admin)" appended.
     *
     * @return The name of the admin.
     */
    public String getName() {
        return "(Admin) " + super.getName();
    }

    /**
     * Gets the age of the admin.
     *
     * @return The age of the admin. Returns null if age is not specified.
     */
    public Integer getAge() {
        if (age == null) {
            return null;
        }
        return age;
    }
}
