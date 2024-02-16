package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private Integer money;
    private List<Book> books = new ArrayList<>();

    /**
     * Constructs a new person with the specified name, money and list of books he own.
     * @param name
     * @param money
     */
    public Person(String name, int money) {
        this.name = name;
        this.money = money;
        this.books = books;
    }

    /**
     * Method to get an overview of persons funds.
     * @return money.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Method to get persons name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method for buying a book.
     * @param book
     * @return true || false.
     */
    public boolean buyBook(Book book) {
        if (book != null && this.getMoney() >= book.getPrice() && book.getOwner() == null) {
            this.money -= book.getPrice();
            book.setOwner(this);
            this.books.add(book);
            return true;
        } else if (book != null && this.getMoney() >= book.getPrice() && book.getOwner() != null) {
            this.money -= book.getPrice();
            book.setOwner(this);
            this.books.add(book);
            return true;
        }
        return false;
    }

    /**
     * Method for selling a book.
     * @param book
     * @return true || false.
     */
    public boolean sellBook(Book book) {
        if (book != null && book.getOwner() == this) {
            this.money += book.getPrice();
            book.setOwner(null);
            this.books.remove(book);
            return true;
        }
        return false;
    }

    /**
     * Method to get persons books.
     * @return person books.
     */
    public List<Book> getBooks() {
        return this.books;
    }
}
