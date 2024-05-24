package ee.taltech.iti0202.bookscraper;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApolloScraper {

    public static final int BOOKS_TO_FETCH = 110;

    public static void scrape(String topic) {
        String baseUrl = "https://www.apollo.ee/raamatud/eestikeelsed-raamatud/" + topic;
        int booksToFetch = BOOKS_TO_FETCH;

        try {
            Map<String, String> bookLinks = new HashMap<>();
            int fetchedBooks = 0;
            int currentPage = 1;

            while (fetchedBooks < booksToFetch) {
                String url = baseUrl + "?page=" + currentPage;
                Document doc = Jsoup.connect(url).get();

                Elements bookElements = doc.select(".product-wrapper");
                for (Element bookElement : bookElements) {
                    String title = bookElement.select(".product-name").text().trim();
                    String link = bookElement.select("a").attr("href");
                    bookLinks.put(title, link);
                    fetchedBooks++;
                    if (fetchedBooks >= booksToFetch) {
                        break;
                    }
                }

                currentPage++;
            }

            saveBookLinksToJson(bookLinks, "book_links_" + topic + ".json");
            System.out.println("Raamatute lingid on salvestatud faili.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveBookLinksToJson(Map<String, String> bookLinks, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(bookLinks);
            writer.write(json);
            System.out.println("Fail " + fileName + " on edukalt salvestatud.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
