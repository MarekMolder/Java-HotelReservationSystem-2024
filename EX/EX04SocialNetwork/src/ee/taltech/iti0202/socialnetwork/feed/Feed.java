package ee.taltech.iti0202.socialnetwork.feed;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.Set;

public class Feed {
    private Set<Message> messages;
    private User user;

    /**
     * Constructs a new feed for a user with a set of messages.
     *
     * @param user     The user associated with the feed.
     * @param messages The set of messages in the feed.
     */
    public Feed(User user, Set<Message> messages) {
        this.user = user;
        this.messages = messages;
    }

    /**
     * Gets the user associated with the feed.
     *
     * @return The user associated with the feed.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the set of messages in the feed.
     *
     * @return The set of messages in the feed.
     */
    public  Set<Message> getMessages() {
        return messages;
    }
}
