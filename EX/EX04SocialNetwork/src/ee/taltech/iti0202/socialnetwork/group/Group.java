
package ee.taltech.iti0202.socialnetwork.group;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.*;

public class Group {

    private String name;
    private User owner;
    private final List<User> members;
    private final List<Message> messageList;
    private final Set<User> banList;

    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.members = new ArrayList<>(Arrays.asList(owner));
        this.messageList = new ArrayList<>();
        this.banList = new HashSet<>();
        owner.addGroup(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }
    
    public void setOwner(User user) {
        if (members.contains(user)) {
            this.owner = user;
        }
    }

    public void addUser(User user) {
        if (!members.contains(user) && !banList.contains(user)) {
            members.add(user);
            user.addGroup(this);
        }
    }

    public List<User> getParticipants() {
        return members;
    }

    public void publishMessage(Message message) {
        if (members.contains(message.getAuthor())) {
            messageList.add(message);
        }
    }

    public List<Message> getMessages() {
        return messageList;
    }
    
    public void removeUser(User user) {
        if (members.contains(user)) {
            if (user.equals(owner)) {
                members.remove(user);
                user.removeGroup(this);
                messageList.removeIf(message -> message.getAuthor().equals(user));
                if (!members.isEmpty()) {
                    owner = members.getFirst();
                } else {
                    owner = null;
                }
            } else {
                members.remove(user);
                user.removeGroup(this);
                messageList.removeIf(message -> message.getAuthor().equals(user));
            }
        }
    }
    
    public void banUser(User user) {
        banList.add(user);
        if (members.contains(user)) {
            this.removeUser(user);
        }
    }
    
    public Set<User> getBannedUsers() {
        return banList;
    }
    
}