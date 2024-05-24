package ee.taltech.iti0202.bookscraper;

import java.util.ArrayList;
import java.util.List;

import static ee.taltech.iti0202.bookscraper.BookScraper.scrapeBooks;

public class ApolloStore {

    private final ArrayList<Book> books;
    private final ArrayList<Author> authors;

    public ApolloStore() {
        this.books = new ArrayList<Book>();
        this.authors = new ArrayList<Author>();
    }

    public void scrapeBookForStore(ETopics topic) {
        List<Book> scraped = scrapeBooks(topic.getTopicUrl());
        books.addAll(scraped);
    }

    public List<Book> getBooksByTopic(String topic) {
        List<Book> topicBooks = new ArrayList<>();
        for (Book book : books) {
            if (book != null && book.getTopic().equals(topic)) {
                topicBooks.add(book);
            }
        }
        return topicBooks;
    }

    public List<Book> getBooksByAuthor(String firstName, String lastName) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().getFirstName().equals(firstName) && book.getAuthor().getLastName().equals(lastName)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
