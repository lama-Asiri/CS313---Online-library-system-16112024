package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.models.Book;

import OnlineLibrarySystem.DB.DBhandler;  // Import your DBHandler class

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.scene.control.ListView;

import javafx.scene.control.MenuBar;

import javafx.scene.control.TextField;

import javafx.scene.control.TitledPane;

import OnlineLibrarySystem.OnlineLibrarySystem;
import OnlineLibrarySystem.DB.DBhandler;

import java.util.Collections;

import java.util.Comparator;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.scene.control.Label;

import javafx.scene.control.MenuItem;

import java.util.logging.Level;

import java.util.logging.Logger;

/**
 *
 * FXML Controller class
 *
 *
 *
 * @author joman
 *
 */
public class BookView implements Initializable {

    @FXML

    private Button Myorder;

    @FXML

    private Button viewbook;

    @FXML

    private Button borrw;

    @FXML

    private MenuBar menub;

    @FXML

    public ListView<Book> bookListView;

    @FXML

    private TextField bookIdField;

    @FXML

    private TitledPane tb;

    @FXML

    private MenuItem byorder;

    @FXML

    private MenuItem reversorder;

    @FXML

    private Label massege;

    public static ObservableList<Book> bookList = FXCollections.observableArrayList();

    public static ObservableList<Book> borrowedBooks = FXCollections.observableArrayList();

    private Book selectedBook;

    public static BookView instance;

    public static BookView getInstance() {

        if (instance == null) {

            System.out.println("BookView instance is not initialized yet!");

        }

        return instance;

    }

    @FXML

    private void handleByOrder(ActionEvent event) {

        Collections.sort(bookList, Comparator.comparing(Book::getTitle));  // Sort by title

        updateBookListView();  // Refresh the ListView to display sorted order

        System.out.println("Books sorted by title.");

    }

    @FXML

    public void handleReverseOrder(ActionEvent event) {

        // Sort the bookList by title in descending order
        FXCollections.sort(bookList, (book1, book2) -> book2.getTitle().compareToIgnoreCase(book1.getTitle()));

        // Update the ListView to reflect the sorted list
        updateBookListView();

        System.out.println("Books have been sorted by title in reverse order.");

    }

    @FXML

    public void handleMyorderButton(ActionEvent event) throws IOException, Exception {

        OnlineLibrarySystem.ShowOrder();

    }

    public void updateBookListView() {

        bookListView.setItems(bookList);

    }

    @FXML

 

    public void handleborrwButton(ActionEvent event) throws IOException, Exception {

        String bookIdToBorrow = bookIdField.getText().trim();
 
        if (bookIdToBorrow.isEmpty()) {

            massege.setText("Enter ID");

            return;

        }
 
        selectedBook = findBookById(bookIdToBorrow);
 
        if (selectedBook == null) {

            massege.setText(bookIdToBorrow + " is invalid ID");

            return;

        }
 
        // Get the current student's email (replace with actual logic)

        String studentEmail = getCurrentStudentEmail();
 
        DBhandler dbHandler = new DBhandler();

        boolean success = dbHandler.borrowBook(bookIdToBorrow, studentEmail);
 
        if (success) {

            borrowedBooks.add(selectedBook);

            bookList.remove(selectedBook);

            bookListView.setItems(bookList);  // Update the ListView

            massege.setText("Book borrowed successfully!");

        } else {

            massege.setText("Failed to borrow book.");

        }

    }


  private Book findBookById(String bookId) {

        for (Book book : bookList) {

            if (book.getId().equals(bookId)) {

                return book;

            }

        }

        return null;  // Return null if no book is found with the given ID

    }


private String getCurrentStudentEmail() {

        // Placeholder for getting the current logged-in user's email
    
        return DBhandler.currrntUser;  // Replace with actual logic

    }

       @Override


public void initialize(URL location, ResourceBundle resources) {
    instance = this;  // Set the instance of BookView
    loadBooksFromDatabase();
}


  private void loadBooksFromDatabase() {

        try {

            DBhandler dbHandler = new DBhandler();

            bookList.setAll(dbHandler.getAllBooks());  // Load books from DB into the ObservableList

            bookListView.setItems(bookList);  // Update the ListView

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}
