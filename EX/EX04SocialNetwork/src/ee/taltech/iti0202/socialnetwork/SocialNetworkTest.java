package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.admin.Admin;
import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SocialNetworkTest {
    User user1 = new User("user1");
    User user2 = new User("user2", 15);
    Group group1 = new Group("group1", user1);
    Group group2 = new Group("group2", user1);
    Message message1 = new Message("title1", "content1", user1);
    Feed feed1 = new Feed(user1, new HashSet<>(Arrays.asList(message1)));
    Admin admin = new Admin("Username", 25);
    Admin admin1 = new Admin("juri");
    SocialNetwork socialNetwork = new SocialNetwork();
    @Test
    public void testUserGetName() {
        String expected = "user1";
        String actual = user1.getName();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testUserGetAge() {
        Integer expected = 15;
        Integer actual = user2.getAge();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testUserAddGroup() {
        user2.addGroup(group1);
        Set<Group> expected = new HashSet<>(Arrays.asList(group1));
        Set actual = user2.getGroups();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testUserRemoveGroup() {
        user2.addGroup(group1);
        user2.removeGroup(group1);
        Set<Group> expected = new HashSet<>();
        Set actual = user2.getGroups();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testMessageGetTitle() {
        String expected = "title1";
        String actual = message1.getTitle();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testMessageGetMessage() {
        String expected = "content1";
        String actual = message1.getMessage();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testMessageGetAuthor() {
        User expected = user1;
        User actual = message1.getAuthor();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testGroupSetName() {
        group1.setName("uus");
        String expected = "uus";
        String actual = group1.getName();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testGroupSetOwner() {
        group1.addUser(user2);
        group1.setOwner(user2);
        User expected = user2;
        User actual = group1.getOwner();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testGroupAddUser() {
        group1.addUser(user2);
        List<User> expected = new ArrayList<>(Arrays.asList(user1, user2));
        List<User> actual = group1.getParticipants();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testGroupPublishMessage() {
        group1.publishMessage(message1);
        List<Message> expected = new ArrayList<>(Arrays.asList(message1));
        List<Message> actual = group1.getMessages();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testGroupRemoveUser() {
        group1.addUser(user2);
        group1.removeUser(user1);
        List<User> expected = new ArrayList<>(Arrays.asList(user2));
        List<User> actual = group1.getParticipants();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testGroupBanUser() {
        group1.addUser(user2);
        group1.banUser(user1);
        Set<User> expected = new HashSet<>(Arrays.asList(user1));
        Set<User> actual = group1.getBannedUsers();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testFeedGetUser() {
        User expected = user1;
        User actual = feed1.getUser();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testAdminGetName() {
        String expected = "(Admin) Username";
        String actual = admin.getName();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testAdminGetAgeWhenNoAge() {
        Integer expected = null;
        Integer actual = admin1.getAge();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testAdminGetAge() {
        Integer expected = 25;
        Integer actual = admin.getAge();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testAdminBanUserFromSocialNetwork() {
        socialNetwork.registerGroup(group1);
        group1.addUser(user2);
        admin.banUserFromSocialNetwork(user1, socialNetwork);
        Set<Group> expected = new HashSet<>(Arrays.asList(group2));;
        Set<Group> actual = user1.getGroups();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testSocialNetworkGetGroups() {
        socialNetwork.registerGroup(group1);
        Set<Group> expected = new HashSet<>(Arrays.asList(group1));;
        Set<Group> actual = socialNetwork.getGroups();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

    @Test
    public void testSocialNetworkGetFeedForUser() {
        socialNetwork.registerGroup(group1);
        User expected = user1;
        User actual = socialNetwork.getFeedForUser(user1).getUser();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }

}
