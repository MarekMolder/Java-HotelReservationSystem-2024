package ee.taltech.iti0202.bookscraper;

public class Book {
    private String language;
    private String topic;
    private String title;
    private String publisher;
    private Author author;
    private String link;
    private String publicationYear;
    private int pageCount;
    private double price;


    public Book(String language, String topic, String title, String publisher, Author author, String link,
                String publicationYear, int pageCount, double price) {
        this.language = language;
        this.topic = topic;
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.link = link;
        this.publicationYear = publicationYear;
        this.pageCount = pageCount;
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTopic() {
        return topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "language='" + language + '\'' +
                ", topic='" + topic + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author=" + author +
                ", link='" + link + '\'' +
                ", publicationYear=" + publicationYear +
                ", pageCount=" + pageCount +
                ", price=" + price +
                '}';
    }
}

