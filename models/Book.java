package OnlineLibrarySystem.models;

public class Book {

    private String title;
    private String id;
    private String author;
    private String genre;
    private String isAvailable;

    public Book(String title, String id, String author, String genre, String isAvailable) {
        this.title = title;
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;

    }

    public Book(String title, String author, String id, String isAvailable) {
        this.title = title;
        this.id = id;
        this.author = author;
        this.isAvailable = "available";
    }

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = "available"; // Default availability

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String isAvailable() {
        return isAvailable;
    }

    public void setAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return title + " - " + author + " (ID: " + id + ")";
    }
}
