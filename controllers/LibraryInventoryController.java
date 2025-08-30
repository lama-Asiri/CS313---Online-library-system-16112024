package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.Map;
import javafx.scene.control.Button;

public class LibraryInventoryController {

    @FXML private ListView<String> bookListView;  // Display book info as Strings
    @FXML private Label totalBooksLabel;
    @FXML private Button b1;

    private ObservableList<String> bookInfoList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        refreshInventory();  // Load books initially
    }

    // Load book titles and available copies into the ListView
    private void loadBooks() {
        bookInfoList.clear();  // Clear the current list

        // Retrieve the count of available copies for each book title from DB
        Map<String, Integer> titleCopiesMap = DBhandler.countNumberOfCopies();

        int totalBooks = titleCopiesMap.size();  // Total unique books

        // Populate bookInfoList with each book's title and available copies
        for (Map.Entry<String, Integer> entry : titleCopiesMap.entrySet()) {
            String bookTitle = entry.getKey();
            int availableCopies = entry.getValue();
            String bookInfo = bookTitle + ", Available Copies: " + availableCopies;
            bookInfoList.add(bookInfo);
        }

        // Update total books label
        totalBooksLabel.setText("Total Books: " + totalBooks);

        // Set the ObservableList as the source of items for the ListView
        bookListView.setItems(bookInfoList);
    }

    // Refresh the inventory display
    @FXML
    public void refreshInventory() {
        loadBooks();  // Reload the list of books
    }
}
