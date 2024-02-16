package ee.taltech.iti0202.bookshelf;

import java.util.*;

public class Book {
    private static int nextId = 0;
    private int id;
    private Person bookOwner;
    private String title;
    private String author;
    private Integer yearOfPublishing;
    private Integer price;
    private static Map<String, List<Book>> ofBooks = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
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
        return bookOwner;
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
        return help(title, author, yearOfPublishing, price);
    }

    private static Book help(String title, String author, int yearOfPublishing, int price) {
        Book newBook = new Book(title, author, yearOfPublishing, price);
        if (ofBooks.containsKey(author)) {
            List<Book> booksByAuthor = ofBooks.get(author);
            for (Book existingBook : booksByAuthor) {
                if (existingBook.getTitle().equals(title) && existingBook.getAuthor().equals(author)
                        && existingBook.getYearOfPublishing() == yearOfPublishing) {
                    return existingBook;
                } else {
                    ofBooks.get(author).add(newBook);
                    lastBook = newBook;
                    return newBook;
                }
            }
        } else {
            List<Book> booksByAuthor = new ArrayList<>();
            booksByAuthor.add(newBook);
            ofBooks.put(newBook.getAuthor(), booksByAuthor);
            lastBook = newBook;
            return newBook;
        }
        return newBook;
    }

    public static Book of(String title, int price) {
        if (lastBook == null) {
            return null;
        }

        String author = lastBook.getAuthor();
        int yearOfPublishing = lastBook.getYearOfPublishing();

        return help(title, author, yearOfPublishing, price);
    }

    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }

    public static boolean removeBook(Book book) {
        if (book != null && ofBooks.containsKey(book.author)) {
            if (book.bookOwner != null) {
                book.bookOwner.sellBook(book);
                    for (Book bo : ofBooks.get(book.author)) {
                        if (bo == book) {
                            ofBooks.get(book.author).remove(bo);
                            return true;
                        }
                    }
                } else {
                    for (Book bo : ofBooks.get(book.author)) {
                        if (bo == book) {
                            ofBooks.get(book.author).remove(bo);
                            return true;
                        }
                    }
                }
            }
        return false;
    }

    public static List<Book> getBooksByAuthor(String author) {
        if (ofBooks.containsKey(author)) {
            return ofBooks.get(author);
        }
        return new ArrayList<>();
    }

}