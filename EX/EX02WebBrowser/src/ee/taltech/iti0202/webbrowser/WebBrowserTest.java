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
        List<String> expected = new ArrayList<>
                (Arrays.asList("google.com", "facebook.com", "amazon.com", "facebook.com"));
        List<String> actual = webBrowser.getHistory();
        assertEquals(expected, actual, () -> String.format("Expected: '%s' , but got '%s'", expected, actual));
    }
}
