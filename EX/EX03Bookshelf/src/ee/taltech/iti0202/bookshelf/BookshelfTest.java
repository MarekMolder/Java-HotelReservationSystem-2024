package ee.taltech.iti0202.bookshelf;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookshelfTest {
    public static final int HOW_MUCH_MONEY_MATI_HAVE = 200;
    Person mati = new Person("Mati", HOW_MUCH_MONEY_MATI_HAVE);
    Person melani = new Person("Melani", 10);
    Person juri = new Person("Jüri", 1000);
    Book book1 = new Book("Truth and Justice", "Tammsaare", 1926, 100);
    Book book2;

    {
        int yearOfPublishing = 2000;
        book2 = new Book("Inglid ja deemonid", "Brown", yearOfPublishing, 1000);
    }

    Book book3;

    {
        int yearOfPublishing = 2003;
        book3 = new Book("Da Vinci kood", "Brown", yearOfPublishing, 100, melani);
    }

    Book book4;

    {
        int yearOfPublishing = 2024;
        book4 = new Book("Kuidas saavutada finantsvabadus", "Saage", yearOfPublishing, 100);
    }

    Book book5 = Book.of("Java EX01", "Ago Luberg", 2018, 3);

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
    @Test
    public void testPersonBuyBook() {
        boolean expected = mati.buyBook(book3);
        boolean actual = true;
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testPersonGetBooks() {
        juri.buyBook(book4);
        List<Book> expected = new ArrayList<>(Arrays.asList(book4));
        List<Book> actual = juri.getBooks();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBook2GetBooksByOwner() {
        juri.buyBook(book5);
        List<Book> expected = new ArrayList<>(Arrays.asList(book5));
        List<Book> actual = Book.getBooksByOwner(juri);
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBook2RemoveBookWhenOwner() {
        juri.buyBook(book5);
        boolean expected = true;
        boolean actual = Book.removeBook(book5);
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBook2GetAuthorBooks() {
        Book harry1 = Book.of("Harry Potter: The Philosopher's Stone", "J. K. rowling", 1997, 1000);
        Book harry2 = Book.of("Harry Potter: The Chamber of Secrets", "J. K. Rowling", 1998, 1000);
        List<Book> expected = new ArrayList<>(Arrays.asList(harry1, harry2));
        List<Book> actual = Book.getBooksByAuthor("j. k. rowling");
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBook2RemoveBookWhenNoOwner() {
        boolean expected = true;
        boolean actual = Book.removeBook(book5);
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBook2OfBooks() {
        Book b1 = Book.of("Java EX01", "Ago Luberg", 2018, 3);
        Book b2 = Book.of("Java EX02",4);
        String expected = "Ago Luberg";
        String actual = b2.getAuthor();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
}
