package ee.taltech.iti0202.webbrowser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebBrowserTest {
    @Test
    public void testSetHomePage() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.setHomePage("neti.ee");
        String expected = "neti.ee";
        String actual = webBrowser.getHomePage();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBackSavesHistory() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("amazon.com");
        webBrowser.back();
        List<String> expected = new ArrayList<>(
                Arrays.asList("google.com", "facebook.com", "amazon.com", "facebook.com"));
        List<String> actual = webBrowser.getHistory();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBack() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("amazon.com");
        webBrowser.back();
        String expected = "facebook.com";
        String actual = webBrowser.getCurrentUrl();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testMoveBackMoreThanThereIs() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("amazon.com");
        webBrowser.back();
        webBrowser.back();
        webBrowser.back();
        String expected = "google.com";
        String actual = webBrowser.getCurrentUrl();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testForward() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("amazon.com");
        webBrowser.goTo("youtube.com");
        webBrowser.goTo("moodle.com");
        webBrowser.back();
        webBrowser.back();
        webBrowser.back();
        webBrowser.forward();
        webBrowser.forward();
        String expected = "youtube.com";
        String actual = webBrowser.getCurrentUrl();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testMoveForwardMoreThanThereIs() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("amazon.com");
        webBrowser.goTo("youtube.com");
        webBrowser.goTo("moodle.com");
        webBrowser.back();
        webBrowser.back();
        webBrowser.back();
        webBrowser.forward();
        webBrowser.forward();
        webBrowser.forward();
        webBrowser.forward();
        webBrowser.forward();
        String expected = "moodle.com";
        String actual = webBrowser.getCurrentUrl();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testNewPageThenForward() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("amazon.com");
        webBrowser.goTo("youtube.com");
        webBrowser.goTo("moodle.com");
        webBrowser.back();
        webBrowser.back();
        webBrowser.back();
        webBrowser.goTo("moodle.com");
        webBrowser.forward();
        webBrowser.forward();
        String expected = "moodle.com";
        String actual = webBrowser.getCurrentUrl();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testBookmark() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.addAsBookmark();
        webBrowser.goTo("amazon.com");
        webBrowser.addAsBookmark();
        List<String> expected = new ArrayList<>(
                Arrays.asList("facebook.com", "amazon.com"));
        List<String> actual = webBrowser.getBookmarks();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testRemoveBookmark() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.addAsBookmark();
        webBrowser.goTo("amazon.com");
        webBrowser.addAsBookmark();
        webBrowser.goTo("youtube.com");
        webBrowser.addAsBookmark();
        webBrowser.removeBookmark("facebook.com");
        List<String> expected = new ArrayList<>(
                Arrays.asList("amazon.com", "youtube.com"));
        List<String> actual = webBrowser.getBookmarks();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testTop3VisitedPages() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("amazon.com");
        webBrowser.goTo("youtube.com");
        webBrowser.goTo("moodle.com");
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("amazon.com");
        webBrowser.goTo("youtube.com");
        webBrowser.goTo("moodle.com");
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("amazon.com");
        webBrowser.goTo("youtube.com");
        webBrowser.goTo("moodle.com");
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("moodle.com");
        String expected = "facebook.com - 4 visits\nmoodle.com - 4 visits\namazon.com - 3 visits";
        String actual = webBrowser.getTop3VisitedPages();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testGoTo() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        String expected = "facebook.com";
        String actual = webBrowser.getCurrentUrl();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testHistorySamePage() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("facebook.com");
        List<String> expected = new ArrayList<>(
                Arrays.asList("google.com", "facebook.com"));
        List<String> actual = webBrowser.getHistory();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testHistory() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("youtube.com");
        List<String> expected = new ArrayList<>(
                Arrays.asList("google.com", "facebook.com", "youtube.com"));
        List<String> actual = webBrowser.getHistory();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
    @Test
    public void testHistoryBackAndForward() {
        WebBrowser webBrowser = new WebBrowser();
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("youtube.com");
        webBrowser.back();
        webBrowser.forward();
        List<String> expected = new ArrayList<>(
                Arrays.asList("google.com", "facebook.com", "youtube.com", "facebook.com", "youtube.com"));
        List<String> actual = webBrowser.getHistory();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
}
