package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.Set;

public class SocialNetwork {
    private Set<Group> socialRegisters = new HashSet<>();

    /**
     * Registers a group in the social network.
     *
     * @param group The group to be registered.
     */
    public void registerGroup(Group group) {
        socialRegisters.add(group);
    }

    /**
     * Gets the set of all registered groups in the social network.
     *
     * @return The set of registered groups.
     */
    public Set<Group> getGroups() {
        return socialRegisters;
    }

    /**
     * Retrieves the feed for a specific user based on their participation in groups.
     *
     * @param user The user for whom the feed is requested.
     * @return A Feed object containing messages from groups the user is a part of.
     */
    public Feed getFeedForUser(User user) {
        Set<Message> allMessages = new HashSet<>();
        for (Group group: getGroups()) {
            if (group.getParticipants().contains(user)) {
                allMessages.addAll(group.getMessages());
            }
        }
        return new Feed(user, allMessages);
    }

    /**
     * Bans a user from all groups in the social network.
     *
     * @param user The user to be banned.
     */
    public void banUser(User user) {
        for (Group group: getGroups()) {
            if (group.getParticipants().contains(user)) {
                group.banUser(user);
            }
            group.banUser(user);
        }
    }
}
