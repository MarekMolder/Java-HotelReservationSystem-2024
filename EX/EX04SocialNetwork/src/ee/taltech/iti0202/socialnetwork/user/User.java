package ee.taltech.iti0202.socialnetwork.user;

import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private final String name;
    protected Integer age;
    private final Set<Group> groupList;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.groupList = new HashSet<>();
    }
    public User(String name) {
        this.name = name;
        this.groupList = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        if (age == null) {
            return null;
        }
        return age;
    }
    
    public void addGroup(Group group) {
        groupList.add(group);
    }

    public void removeGroup(Group group) {
        groupList.remove(group);
    }
    
    public Set<Group> getGroups() {
        return groupList;
    }
}