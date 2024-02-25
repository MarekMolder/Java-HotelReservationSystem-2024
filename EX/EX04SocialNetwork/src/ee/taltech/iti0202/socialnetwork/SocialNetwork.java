package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.Set;

public class SocialNetwork {
    private Set<Group> socialRegisters = new HashSet<>();

    public void registerGroup(Group group) {
        socialRegisters.add(group);
    }

    public Set<Group> getGroups() {
        return socialRegisters;
    }

    public Feed getFeedForUser(User user) {
        Set<Message> allMessages = new HashSet<>();
        for (Group group: getGroups()) {
            if (group.getParticipants().contains(user)) {
                allMessages.addAll(group.getMessages());
            }
        }
        return new Feed(user, allMessages);
    }

    public void banUser(User user) {
        for (Group group: socialRegisters) {
            if (group.getParticipants().contains(user)) {
                group.banUser(user);
            }
        }
    }
}