package ee.taltech.iti0202.tk.art;

public class Painting {
    private String title;
    private String author;

    /**
     * Constructs a new Painting object with the specified title and author.
     * @param title The name of the painting.
     * @param author The name of the author.
     */
    public Painting(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Constructs a new Painting object with the specified title.
     * @param title The name of the painting
     */
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

    /**
     * Returns a string representation of the Painting, including its title and author (if available).
     *
     * @return A string representation of the painting.
     */
    public String toString() {
        if (this.author == null) {
            return String.format("This is a painting named %s and made by an unknown artist.", title);
        }
        return String.format("This is a painting named %s and made by %s.", title, author);
    }
}
