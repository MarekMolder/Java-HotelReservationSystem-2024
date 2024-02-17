package ee.taltech.iti0202.bookshelf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookshelfTest {
    Person mati = new Person("Mati", 200);
    Person melani = new Person("Melani", 10);
    Book book1 = new Book("Truth and Justice", "Tammsaare", 1926, 100);
    Book book2 = new Book("Inglid ja deemonid", "Brown", 2000, 1000);
    Book book3 = new Book("Da Vinci kood", "Brown", 2003, 100, melani);

    @Test
    public void testBookGetTitleFirstConstructor() {
        String expected = "Truth and Justice";
        String actual = book1.getTitle();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookGetAuthorFirstConstructor() {
        String expected = "Tammsaare";
        String actual = book1.getAuthor();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookGetYearOfPublishingFirstConstructor() {
        int expected = 1926;
        int actual = book1.getYearOfPublishing();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookGetPriceFirstConstructor() {
        int expected = 100;
        int actual = book1.getPrice();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookGetOwnerFirstConstructor() {
        mati.buyBook(book1);
        Person expected = mati;
        Person actual = book1.getOwner();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookGetOwnerSecondConstructor() {
        Person expected = melani;
        Person actual = book3.getOwner();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookBuyAlreadyOwner() {
        book1.buy(mati);
        boolean expected = book1.buy(mati);
        boolean actual = false;
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookBuyNotEnoughMoney() {
        boolean expected = book2.buy(melani);
        boolean actual = false;
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookSetOwner() {
        book2.setOwner(melani);
        Person expected = melani;
        Person actual = book2.getOwner();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookBuyCanBuy() {
        boolean expected = book1.buy(mati);
        boolean actual = true;
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookBuyBuyerNull() {
        boolean expected = book3.buy(null); ;
        boolean actual = true;
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookBuyFromOtherPerson() {
        boolean expected = book3.buy(mati);
        boolean actual = true;
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testPersonGetMoney() {
        int expected = melani.getMoney();
        int actual = 10;
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testPersonGetName() {
        String expected = melani.getName();
        String actual = "Melani";
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
}