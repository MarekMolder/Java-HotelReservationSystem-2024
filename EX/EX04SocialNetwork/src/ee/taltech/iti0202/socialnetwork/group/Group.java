package ee.taltech.iti0202.socialnetwork.group;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.*;

public class Group {

    private String name;
    private User owner;
    private List<User> members;
    private List<Message> messageList;
    private Set<User> banList;

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
            members.remove(user);
            if (user.equals(owner)) {
                if (members.size() > 0) {
                    owner = members.getFirst();
                } else {
                    owner = null;
                }
            }
            List<Message> removeMessages = new ArrayList<>();
            for (Message message: messageList) {
                if (message.getAuthor().equals(user)) {
                    removeMessages.add(message);
                }
            }
            for (Message message: removeMessages) {
                messageList.remove(message);
            }
            user.removeGroup(this);
        }
    }
    
    public void banUser(User user) {
        banList.add(user);
        if (members.contains(user)) {
            removeUser(user);
        }
    }
    
    public Set<User> getBannedUsers() {
        return banList;
    }
    
}