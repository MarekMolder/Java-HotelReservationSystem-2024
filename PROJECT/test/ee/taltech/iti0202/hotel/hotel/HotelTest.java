package ee.taltech.iti0202.hotel.hotel;

/*
class HotelTest {
Room room1 = new Room();
    Room room2 = new Room();
    Room room3 = new Room();
    Room room4 = new Room();
    Room room5 = new Room();
    Room room6 = new Room();
    Room room7 = new Room();
    Room room8 = new Room();
    Room room9 = new Room();
    Room room10 = new Room();
    Room room11 = new Room();
    Room room12 = new Room();
    Room room13 = new DeluxeRoom();
    Hotel hotel1 = new Hotel();
    Hotel hotel2 = new Hotel();
    Client client1 = new Client("Mati", 1000000);
    Client client2 = new Client("Kati", 1000000);
    Client client3 = new Client("Jüri", 1000000);
    Client client4 = new Client("Tõnu", 1000000);
    Client client5 = new Client("Laura", 1000000);
    Client client6 = new Client("Kalle", 10000000);

    @Test
    public void testHotelAddRoom() {
        assertTrue(hotel1.addRoomToHotel(room1));
        assertFalse(hotel2.addRoomToHotel(room1));
    }

    @Test
    public void testHotelGetReviewScore() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);


        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), hotel1);
        client2.bookRoom(room2, LocalDate.of(2022, 4, 12), hotel1);
        client3.bookRoom(room3, LocalDate.of(2022, 4, 12), hotel1);

        client1.writeReview("Väga lahe hotell", 5, hotel1);
        client2.writeReview("Cool", 3, hotel1);
        client2.writeReview("Hea", 4, hotel1);

        Assertions.assertEquals(4, hotel1.getReviewsScore(), "Review Score should be 4");

        Map<Client, List<Object>> expectedReviews = new HashMap<>();
        expectedReviews.put(client1, new ArrayList<>(Arrays.asList("Väga lahe hotell", 5)));
        expectedReviews.put(client2, new ArrayList<>(Arrays.asList("Cool", 3)));
        Assertions.assertEquals(expectedReviews, hotel1.getHotelReviews(), "Review Score should be 4");

        Set<Client> clientList = new HashSet<>();
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
        Assertions.assertEquals(clientList, hotel1.getClients(), "Review Score should be 4");
    }

    @Test
    public void testHotelClients() {
        hotel2.addRoomToHotel(room1);
        hotel2.addRoomToHotel(room2);
        hotel2.addRoomToHotel(room3);
        hotel2.addRoomToHotel(room4);
        hotel2.addRoomToHotel(room5);
        hotel2.addRoomToHotel(room6);
        hotel2.addRoomToHotel(room7);
        hotel2.addRoomToHotel(room8);
        hotel2.addRoomToHotel(room9);
        hotel2.addRoomToHotel(room10);
        hotel2.addRoomToHotel(room11);
        hotel2.addRoomToHotel(room12);

        client6.bookRoom(room12, LocalDate.of(2022, 4, 12), hotel2);
        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), hotel2);
        client1.bookRoom(room2, LocalDate.of(2022, 4, 12), hotel2);
        client2.bookRoom(room7, LocalDate.of(2022, 4, 12), hotel2);
        client3.bookRoom(room8, LocalDate.of(2022, 4, 12), hotel2);
        client4.bookRoom(room9, LocalDate.of(2022, 4, 12), hotel2);
        client1.bookRoom(room3, LocalDate.of(2022, 4, 12), hotel2);
        client2.bookRoom(room4, LocalDate.of(2022, 4, 12), hotel2);
        client2.bookRoom(room5, LocalDate.of(2022, 4, 12), hotel2);
        client2.bookRoom(room6, LocalDate.of(2022, 4, 12), hotel2);
        client4.bookRoom(room10, LocalDate.of(2022, 4, 12), hotel2);
        client5.bookRoom(room11, LocalDate.of(2022, 4, 12), hotel2);

        Map<Client, Integer> expectedClients = new HashMap<>();
        expectedClients.put(client1, 3);
        expectedClients.put(client2, 4);
        expectedClients.put(client3, 1);
        expectedClients.put(client4, 2);
        expectedClients.put(client5, 1);
        expectedClients.put(client6, 1);


    }
    @Test
    public void testHotelLookUpFreeRoomType() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);


        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), hotel1);

        Assertions.assertEquals(2, hotel1.LookUpFreeRoomType(Room.class, LocalDate.of(2022, 4, 12)).size());
        Assertions.assertEquals(0, hotel1.LookUpFreeRoomType(DoubleRoom.class, LocalDate.of(2022, 4, 12)).size());

    }

    @Test
    public void testHotelLookUpFreeRoomDate() {
        hotel1.addRoomToHotel(room1);
        hotel1.addRoomToHotel(room2);
        hotel1.addRoomToHotel(room3);
        hotel1.addRoomToHotel(room13);


        client1.bookRoom(room1, LocalDate.of(2022, 4, 12), hotel1);

        Assertions.assertEquals(3, hotel1.LookUpFreeRoomDate(LocalDate.of(2022, 4, 12)).size());
        Assertions.assertEquals(0, hotel2.LookUpFreeRoomDate(LocalDate.of(2022, 4, 12)).size());

    }
}
 */
