import ee.taltech.iti0202.bookscraper.ApolloStore;
import ee.taltech.iti0202.bookscraper.ETopics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApolloScraperTest {
    private ApolloStore apolloStore;


    @BeforeEach
    void setUp() {
        apolloStore = new ApolloStore();
    }

    @Test
    void getAllBooks() {
        assertEquals(0, apolloStore.getAllBooks().size());

        apolloStore.scrapeBookForStore(ETopics.AJALUGU);
        apolloStore.scrapeBookForStore(ETopics.MEELELAHUTUS);

        assertEquals(220, apolloStore.getAllBooks().size());
    }

    @Test
    void getBooksByTopic() {
        assertEquals(0, apolloStore.getAllBooks().size());

        apolloStore.scrapeBookForStore(ETopics.AJALUGU);
        apolloStore.scrapeBookForStore(ETopics.MEELELAHUTUS);

        assertEquals(110, apolloStore.getBooksByTopic(ETopics.AJALUGU.getTopicUrl()).size());
    }

    @Test
    void getBooksByAuthor() {
        assertEquals(0, apolloStore.getAllBooks().size());

        apolloStore.scrapeBookForStore(ETopics.AJALUGU);
        apolloStore.scrapeBookForStore(ETopics.MEELELAHUTUS);
        assertEquals(220, apolloStore.getAllBooks().size());

        assertEquals(1, apolloStore.getBooksByAuthor("Elmar", "Vrager").size());
        assertEquals("Pilke Hiiumaa minevikust", apolloStore.getBooksByAuthor("Elmar", "Vrager").getFirst().getTitle());
    }
}
