package ee.taltech.iti0202.socialnetwork.user;

import ee.taltech.iti0202.socialnetwork.group.Group;

import java.util.HashSet;
import java.util.Set;

public class User {

    private String name;
    private Integer age;
    private Set<Group> groupList;

    /**
     * Constructs a new user with a given name and age.
     *
     * @param name The name of the user.
     * @param age  The age of the user. Can be null if not specified.
     */
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.groupList = new HashSet<>();
    }

    /**
     * Constructs a new user with a given name and no specified age.
     *
     * @param name The name of the user.
     */
    public User(String name) {
        this.name = name;
        this.groupList = new HashSet<>();
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }


    /**
     * Gets the age of the user.
     *
     * @return The age of the user. Returns null if age is not specified.
     */
    public Integer getAge() {
        if (age == null) {
            return null;
        }
        return age;
    }

    /**
     * Adds a group to the user's list of groups.
     *
     * @param group The group to be added.
     */
    public void addGroup(Group group) {
        groupList.add(group);
    }

    /**
     * Removes a group from the user's list of groups.
     *
     * @param group The group to be removed.
     */
    public void removeGroup(Group group) {
        groupList.remove(group);
    }

    /**
     * Gets an unmodifiable set of groups to which the user belongs.
     *
     * @return An unmodifiable set of groups.
     */
    public Set<Group> getGroups() {
        return groupList;
    }
}
