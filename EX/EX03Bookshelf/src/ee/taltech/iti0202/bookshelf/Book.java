package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Book {
    private static int nextId = 0;
    private final int id;
    private Person bookOwner;
    private final String title;
    private final String author;
    private final Integer yearOfPublishing;
    private final Integer price;
    private static final Map<String, List<Book>> BOOKSLIST = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static Book lastBook;

    /**
     * Method to get next id.
     * @return nextid.
     */
    public static int getAndIncrementNextId() {
        return nextId++;
    }

    /**
     * Constructs a new book with the specified title, author, yearofpublishing, price.
     * @param title
     * @param author
     * @param yearOfPublishing
     * @param price
     */
    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.id = getAndIncrementNextId();
    }

    /**
     * Constructs a new book with the specified title, author, yearofpublishing, price.
     * @param title
     * @param author
     * @param yearOfPublishing
     * @param price
     * @param bookOwner
     */
    public Book(String title, String author, int yearOfPublishing, int price, Person bookOwner) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.bookOwner = bookOwner;
        this.id = getAndIncrementNextId();
    }

    /**
     * Method to get book title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method to get book author.
     * @return author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Method to get book year of publishing.
     * @return year of publishing.
     */
    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    /**
     * Method to get book owner.
     * @return owner.
     */
    public Person getOwner() {
        return bookOwner;
    }

    /**
     * Method to set book owner.
     * @param person
     */
    public void setOwner(Person person) {
        this.bookOwner = person;
    }

    /**
     * Method to get book price
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Method to get book id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Method for buying a book.
     * @param buyer
     * @return
     */
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

    /**
     * Help function for book of instance.
     * @param title
     * @param author
     * @param yearOfPublishing
     * @param price
     * @return help(title, author, yearOfPublishing, price).
     */
    public static Book of(String title, String author, int yearOfPublishing, int price) {
        return help(title, author, yearOfPublishing, price);
    }

    /**
     * onstructs a new book of with the specified title, author, yearofpublishing, price.
     * @param title
     * @param author
     * @param yearOfPublishing
     * @param price
     * @return existingbook || newbook
     */
    private static Book help(String title, String author, int yearOfPublishing, int price) {
        Book newBook = new Book(title, author, yearOfPublishing, price);

        if (BOOKSLIST.containsKey(author)) {
            List<Book> booksByAuthor = BOOKSLIST.get(author);

            for (Book existingBook : booksByAuthor) {
                if (existingBook.getTitle().equals(title) && existingBook.getAuthor().equals(author)
                        && existingBook.getYearOfPublishing() == yearOfPublishing) {
                    return existingBook;
                }
            }

            BOOKSLIST.get(author).add(newBook);
        } else {
            List<Book> booksByAuthor = new ArrayList<>();
            booksByAuthor.add(newBook);
            BOOKSLIST.put(newBook.getAuthor(), booksByAuthor);

        }
        lastBook = newBook;
        return newBook;
    }

    /**
     * Constructs a new book with the specified title, price.
     * @param title
     * @param price
     * @return
     */
    public static Book of(String title, int price) {
        if (lastBook == null) {
            return null;
        }

        String author = lastBook.getAuthor();
        int yearOfPublishing = lastBook.getYearOfPublishing();

        return help(title, author, yearOfPublishing, price);
    }

    /**
     * Method to get owners all books.
     * @param owner
     * @return owner books list.
     */
    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }

    /**
     * Method to remove book from all lists.
     * @param book
     * @return true || false.
     */
    public static boolean removeBook(Book book) {
        if (book != null && BOOKSLIST.containsKey(book.author)) {
            if (book.bookOwner != null) {
                book.bookOwner.sellBook(book);
                for (Book bo : BOOKSLIST.get(book.author)) {
                    if (bo == book) {
                        BOOKSLIST.get(book.author).remove(bo);
                        return true;
                    }
                }
            } else {
                for (Book bo : BOOKSLIST.get(book.author)) {
                    if (bo == book) {
                        BOOKSLIST.get(book.author).remove(bo);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method to find specific author books.
     * @param author
     * @return author book list.
     */
    public static List<Book> getBooksByAuthor(String author) {
        if (BOOKSLIST.containsKey(author)) {
            return BOOKSLIST.get(author);
        }
        return new ArrayList<>();
    }

}
