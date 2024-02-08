package ee.taltech.iti0202.webbrowser;

import javax.naming.ldap.SortKey;
import java.util.*;
import java.util.stream.Collectors;

public class WebBrowser {
    private String homePage = "google.com";
    Stack<String> back = new Stack<>();
    Stack<String> forward = new Stack<>();
    List<String> history = new ArrayList<>();
    List<String> bookmark = new ArrayList<>();
    String currentPage;

    public WebBrowser() {
        homePage();
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        //TODO: implement
        goTo(homePage);
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        //TODO: implement
        if (back.peek() != null) {
            forward.push(currentPage);
            currentPage = back.pop();
            history.add(currentPage);
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        //TODO: implement
        if (!forward.isEmpty()) {
            back.push(currentPage);
            currentPage = forward.pop();
            history.add(currentPage);
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url where to go
     */
    public void goTo(String url) {
        //TODO: implement
        if (back.isEmpty() || !currentPage.equals(url)){
            back.push(currentPage);
            currentPage = url;
            forward.clear();
            history.add(currentPage);
        }
    }

    /**
     * Add the current webpage as a bookmark.
     */
    public void addAsBookmark() {
        //TODO: implement
        bookmark.add(currentPage);
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        //TODO: implement
        this.bookmark.remove(bookmark);
    }

    public List<String> getBookmarks() {
        //TODO: implement
        return bookmark;
    }

    public void setHomePage(String homePage) {
        //TODO: implement
        this.homePage = homePage;
    }


    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        //TODO: implement
        Map<String, Integer> totalCount = new LinkedHashMap<>();
        for (int i = 0; i < history.size(); i++) {
            totalCount.merge(history.get(i), 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> sortedEntries = totalCount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        // Get the top 3 visited pages
        List<Map.Entry<String, Integer>> top3Entries = sortedEntries.subList(0, Math.min(3, sortedEntries.size()));

        return top3Entries.stream()
                .map(entry -> String.format("%s - %d visits", entry.getKey(), entry.getValue())).collect(Collectors.joining("\n"));
    }

    /**
     * Returns a list of all visited pages.
     * <p>
     * Not to be confused with pages in your back-history.
     * <p>
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     * @return list of all visited pages
     */
    public List<String> getHistory() {
        //TODO: implement
        return history;
    }


    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        //TODO: implement
        return currentPage;
    }

    public static void main(String[] args) {
        WebBrowser webBrowser = new WebBrowser();
        System.out.println(webBrowser.getCurrentUrl());
        webBrowser.setHomePage("neti.ee");
        webBrowser.goTo("facebook.com");
        webBrowser.goTo("facebook.com");
        System.out.println(webBrowser.getHistory());
        System.out.println(webBrowser.getTop3VisitedPages());
    }
}

/*
        System.out.println(webBrowser.getCurrentUrl());
        webBrowser.setHomePage("neti.ee");
        webBrowser.goTo("facebook.com");
        System.out.println(webBrowser.getCurrentUrl());
        webBrowser.goTo("google.com");
        System.out.println(webBrowser.getCurrentUrl());
        webBrowser.back();
        System.out.println(webBrowser.getCurrentUrl());
        webBrowser.addAsBookmark();
        webBrowser.forward();
        System.out.println(webBrowser.getCurrentUrl());
        webBrowser.homePage();
        System.out.println(webBrowser.getCurrentUrl());
        webBrowser.addAsBookmark();
        System.out.println(webBrowser.getBookmarks());
        System.out.println(webBrowser.getHistory()); //- > [google.com, facebook.com]
        System.out.println(webBrowser.getTop3VisitedPages());
 */