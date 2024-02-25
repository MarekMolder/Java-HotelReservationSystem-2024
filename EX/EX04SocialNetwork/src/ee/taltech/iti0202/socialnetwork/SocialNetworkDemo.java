package ee.taltech.iti0202.socialnetwork;

public class SocialNetworkDemo {
/*/
 public static void main(String[] args) {
        User user1 = new User("user1");
        System.out.println(user1.getName()); // user1
        System.out.println(user1.getAge()); // null

        User user2 = new User("user2", 10);
        System.out.println(user2.getName()); // user2
        System.out.println(user2.getAge()); // 10

        Admin admin = new Admin("Username", 25);
        System.out.println(admin.getName()); // (Admin) Username
        System.out.println(admin.getAge()); // 25

        User user3 = new User("user2", 20);

        Group group1 = new Group("group1", user1);
        System.out.println(group1.getName()); // group1
        System.out.println(group1.getOwner()); // user1

        group1.setName("newName");
        System.out.println(group1.getName()); // newName

        Group group2 = new Group("group2", user3);

        group1.addUser(user2);
        System.out.println(group1.getParticipants()); // [user1, user2]
        System.out.println(user2.getGroups()); // [group1]

        group1.setOwner(user2);
        System.out.println(group1.getOwner()); // user2

        group1.removeUser(user1);
        System.out.println(group1.getParticipants()); // [user2]
        System.out.println(user1.getGroups()); // []

        Message message1 = new Message("title1", "content1", user2);
        System.out.println(message1.getTitle()); // title1
        System.out.println(message1.getMessage()); // content1
        System.out.println(message1.getAuthor()); // user2

        group1.publishMessage(message1);
        System.out.println(group1.getMessages()); // [message1]

        Message message2 = new Message("title2", "content2", user3);
        group2.publishMessage(message2);

        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.registerGroup(group1);
        socialNetwork.registerGroup(group2);
        System.out.println(socialNetwork.getGroups()); // [group1, newGroup]

        System.out.println(socialNetwork.getFeedForUser(user2).getMessages()); // [message1]

        group1.banUser(user2);
        System.out.println(group1.getParticipants()); // []
        System.out.println(group1.getBannedUsers()); // [user2]

        admin.banUserFromSocialNetwork(user3, socialNetwork);
        System.out.println(group2.getParticipants()); // []
    }
 */

}