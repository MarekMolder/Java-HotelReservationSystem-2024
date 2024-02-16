package ee.taltech.iti0202.bookshelf;

import java.util.*;
import java.util.stream.Collectors;

public class Book {
    private static int nextId = 0;
    private int id;
    private Person bookOwner;
    private String title;
    private String author;
    private Integer yearOfPublishing;
    private Integer price;
    private static HashMap<String, Book> ofBooks = new LinkedHashMap<>();
    private static Book lastBook;

    
    public static int getAndIncrementNextId() {
        return nextId++;
    }

    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.id = getAndIncrementNextId();
    }

    public Book(String title, String author, int yearOfPublishing, int price, Person bookOwner) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.bookOwner = bookOwner;
        this.id = getAndIncrementNextId();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public Person getOwner() {
        return bookOwner ;
    }

    public void setOwner(Person person) {
        this.bookOwner = person;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public boolean buy(Person buyer) {
        if (this.getOwner() == null && buyer != null && buyer.getMoney() >= this.getPrice()) {
            buyer.buyBook(this);
            this.bookOwner = buyer;
            return true;
        } else if (this.getOwner() != buyer && buyer != null && buyer.getMoney() >= this.getPrice()) {
            this.bookOwner.sellBook(this);
            buyer.buyBook(this);
            this.bookOwner = buyer;
            return true;
        } else if (buyer == null && bookOwner != null) {
            this.bookOwner.sellBook(this);
            return true;
        }
        return false;
    }

    public static Book of(String title, String author, int yearOfPublishing, int price) {
        String identificator = title + author + yearOfPublishing;
        if (ofBooks.containsKey(identificator)) {
            lastBook = ofBooks.get(identificator);
            return ofBooks.get(identificator);
        } else {
            Book newBook = new Book(title, author, yearOfPublishing, price);
            ofBooks.put(identificator, newBook);
            lastBook = ofBooks.get(identificator);
            return newBook;
        }
    }

    public static Book of(String title, int price) {
        if (lastBook == null) {
            return null;
        }

        String author = lastBook.getAuthor();
        int yearOfPublishing = lastBook.getYearOfPublishing();
        String identificator = title + author + yearOfPublishing;

        if (ofBooks.containsKey(identificator)) {
            return ofBooks.get(identificator);
        } else {
            Book uusRaamat = new Book(title, author, yearOfPublishing, price);
            ofBooks.put(identificator, uusRaamat);
            lastBook = uusRaamat;
            return uusRaamat;
        }
    }

    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }

    public static boolean removeBook(Book book) {
        if (book != null) {
            String identificator = book.title + book.author + book.yearOfPublishing;
            if (ofBooks.containsKey(identificator)) {
                if(book.bookOwner != null) {
                    book.bookOwner.sellBook(book);
                    ofBooks.remove(identificator);
                    return true;
                } else {
                    ofBooks.remove(identificator);
                    return true;
                }
            }
        }
        return false;
    }

    public static List<Book> getBooksByAuthor(String author) {
        return ofBooks.values()
                .parallelStream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public AbstractMap<String, Book> getOfBooks () {
        return ofBooks;
    }
}