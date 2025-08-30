package OnlineLibrarySystem.models;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class BookStore implements Listable<Book> {

    private static List<Book> books = new ArrayList<>();
    private LinkedHashSet<User> user;

    public BookStore(LinkedHashSet<User> user) {
        this.user = user;
    }

    public LinkedHashSet<User> getUser() {
        return user;
    }

    public void setUser(LinkedHashSet<User> user) {
        this.user = user;
    }

    public static List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "BookStore{" + "user=" + user + '}';
    }

    @Override
    public List<Book> listAll() {
        return new ArrayList<>(books); // Returning all books
    }

}
