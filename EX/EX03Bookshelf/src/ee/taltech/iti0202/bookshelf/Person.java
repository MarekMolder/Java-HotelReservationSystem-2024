package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private Integer money;
    private List<Book> books = new ArrayList<>();

    public Person(String name, int money) {
        this.name = name;
        this.money = money;
        this.books = books;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

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

    public boolean sellBook(Book book) {
        if (book != null && book.getOwner() == this) {
            this.money += book.getPrice();
            book.setOwner(null);
            this.books.remove(book);
            return true;
        }
        return false;
    }

    public List<Book> getBooks() {
        return this.books;
    }
}
