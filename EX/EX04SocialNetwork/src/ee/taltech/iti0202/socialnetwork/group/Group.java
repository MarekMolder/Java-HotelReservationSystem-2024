package ee.taltech.iti0202.socialnetwork.group;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {

    private String name;
    private User owner;
    private List<User> members;
    private List<Message> messageList;
    private Set<User> banList;

    /**
     * Constructs a new group with a name and an owner.
     *
     * @param name  The name of the group.
     * @param owner The owner of the group.
     */
    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.members = new ArrayList<>(Arrays.asList(owner));
        this.messageList = new ArrayList<>();
        this.banList = new HashSet<>();
        owner.addGroup(this);
    }

    /**
     * Gets the name of the group.
     *
     * @return The name of the group.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the group.
     *
     * @param name The new name of the group.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the owner of the group.
     *
     * @return The owner of the group.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets a new owner for the group if the provided user is a member.
     *
     * @param user The new owner candidate.
     */
    public void setOwner(User user) {
        if (members.contains(user)) {
            this.owner = user;
        }
    }

    /**
     * Adds a user to the group if not banned and not already a member.
     *
     * @param user The user to be added.
     */
    public void addUser(User user) {
        if (!members.contains(user) && !banList.contains(user)) {
            members.add(user);
            user.addGroup(this);
        }
    }

    /**
     * Gets a list of participants (members) in the group.
     *
     * @return The list of participants.
     */
    public List<User> getParticipants() {
        return members;
    }

    /**
     * Publishes a message to the group if the author is a member.
     *
     * @param message The message to be published.
     */
    public void publishMessage(Message message) {
        if (members.contains(message.getAuthor())) {
            messageList.add(message);
        }
    }

    /**
     * Gets the list of messages published in the group.
     *
     * @return The list of messages.
     */
    public List<Message> getMessages() {
        return messageList;
    }

    /**
     * Removes a user from the group, handling the case when the user is the owner.
     *
     * @param user The user to be removed.
     */
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

    /**
     * Bans a user from the group, removing them if they are a member.
     *
     * @param user The user to be banned.
     */
    public void banUser(User user) {
        banList.add(user);
        if (members.contains(user)) {
            removeUser(user);
        }
    }

    /**
     * Gets the set of users banned from the group.
     *
     * @return The set of banned users.
     */
    public Set<User> getBannedUsers() {
        return banList;
    }
}
