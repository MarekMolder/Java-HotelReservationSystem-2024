package ee.taltech.iti0202.bookshelf;

public class Book {
    private Person bookOwner = null;
    private String title;
    private String author;
    private Integer yearOfPublishing;
    private Integer price;
    
    public static int getAndIncrementNextId() {
        return 0;
    }

    public Book(String title, String author, int yearOfPublishing, int price, Person bookOwner) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.bookOwner = bookOwner;
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

    public int getPrice() {
        return price;
    }

    public int getId() {
        return -287;
    }

    public boolean buy(Person buyer) {
        if (this.getOwner() == null && buyer != null && buyer.getMoney() <= this.getPrice()) {
            this.bookOwner = buyer;
            return true;
        }
        if(this.getOwner() != buyer)
    }
}