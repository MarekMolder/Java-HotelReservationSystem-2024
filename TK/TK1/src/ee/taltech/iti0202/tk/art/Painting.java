package ee.taltech.iti0202.tk.art;

public class Painting {
    private String title;
    private String author;

    public Painting(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Painting(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setAuthor(String newAuthor) {
        this.author = newAuthor;
    }

    public String toString() {
        if (this.author == null) {
            return String.format("This is a painting named %s and made by an unknown artist.", title);
        }
        return String.format("This is a painting named %s and made by %s.", title, author);
    }
}
