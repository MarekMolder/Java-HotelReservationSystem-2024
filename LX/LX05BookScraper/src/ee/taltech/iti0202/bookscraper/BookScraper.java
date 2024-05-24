package ee.taltech.iti0202.bookscraper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookScraper {

    private static final String BASE_URL = "https://www.apollo.ee";

    public static List<Book> scrapeBooks(String topic) {
        String title2 = null;
        String fileName = "book_links_" + topic + ".json";
        File file = new File(fileName);

        if (!file.exists()) {
            ApolloScraper.scrape(topic);
        } else {
            System.out.println("Fail nimega " + fileName
                    + " juba eksisteerib. Raamatute lingid loetakse olemasolevast failist.");
        }

        Map<String, String> bookLinks = readBookLinksFromFile(fileName);
        List<Book> books = new ArrayList<>();

        for (String title : bookLinks.keySet()) {
            title2 = title.replaceAll("[/:\\-?]", "_");
            String bookFileName = "book_" + title2 + ".json";
            File bookFile = new File(bookFileName);
            if (bookFile.exists()) {
                Book book = readBookFromJsonFile(bookFileName);
                if (book != null) {
                    boolean containsBook = books.stream()
                            .anyMatch(b -> b.getTitle().equals(book.getTitle()));
                    if (!containsBook) {
                        books.add(book);
                    }
                }
            } else {
                String link = bookLinks.get(title);
                Book book = scrapeAndCreateBook(link, topic);
                boolean containsBook = books.stream()
                        .anyMatch(b -> b.getTitle().equals(book.getTitle()));
                if (!containsBook) {
                    books.add(book);
                }
            }
        }
        return books;
    }

    public static Book scrapeAndCreateBook(String link, String topic) {
        Book book = null;
        String title2 = null;
        try {
            String fullLink = BASE_URL + link;
            Document doc = Jsoup.connect(fullLink).get();

            String title = doc.select(".product__main-info__content-title").text();

            title2 = title.replaceAll("[/:\\-?]", "_");

            String outputFilename = "book_" + title2 + ".json";

            if (!fileExists(outputFilename)) {
                String publisher = doc.select(".info:contains(Kirjastus) .info__text").text();
                String language = doc.select(".info:contains(Keel) .info__text").text();

                String authorFullName = doc.select(".info:contains(Autor) .info__text").text();
                String[] authorNameParts = authorFullName.split(" ");
                String authorFirstName = authorNameParts.length > 0 ? authorNameParts[0] : "";
                String authorLastName = authorNameParts.length > 1 ? authorNameParts[1] : "";
                Author author = new Author(authorFirstName, authorLastName);

                String publicationYearText = doc.select(".info:contains(Ilmumisaasta) .info__text").text();
                String pageCountText = doc.select(".info:contains(Leheküljed) .info__text").text();
                String priceText = doc.select(".responsive-ml.block.mt8.align-left.lh18 .lh18").text();

                String[] priceParts = priceText.split(" ");

                double price = 0.0;
                if (priceParts.length > 0) {
                    try {
                        price = Double.parseDouble(priceParts[0].replace(",", "."));
                    } catch (NumberFormatException ex) {
                        System.out.println("Viga numbrilise väärtuse teisendamisel hinnale: " + priceParts[0]);
                        ex.printStackTrace();
                    }
                }

                int pageCount = pageCountText.isEmpty() ? 0 : Integer.parseInt(pageCountText);

                book = new Book(language, topic, title, publisher, author,
                        fullLink, publicationYearText, pageCount, price);
                saveBookToJson(book, outputFilename);
                System.out.println("Wrote data for " + title + " to " + outputFilename);
            } else {
                System.out.println("File for " + title + " already exists. Skipping...");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }

    public static boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    public static void saveBookToJson(Book book, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(book, writer);
            System.out.println("Fail " + fileName + " on edukalt salvestatud.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> readBookLinksFromFile(String fileName) {
        Map<String, String> bookLinks = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            bookLinks = gson.fromJson(br, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookLinks;
    }

    public static Book readBookFromJsonFile(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Book.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
