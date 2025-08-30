package OnlineLibrarySystem.controllers;


import OnlineLibrarySystem.DB.DBconnection;
import OnlineLibrarySystem.DB.DBhandler;
import static OnlineLibrarySystem.OnlineLibrarySystem.loadAddNewBook;
import static OnlineLibrarySystem.OnlineLibrarySystem.loadEditBook;
import static OnlineLibrarySystem.OnlineLibrarySystem.loadEditExitingBook;
import static OnlineLibrarySystem.OnlineLibrarySystem.loadMain;
import static OnlineLibrarySystem.OnlineLibrarySystem.loadRemoveBook;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import OnlineLibrarySystem.models.Book; // Import your Book model class
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ManageBookInventoryController implements Initializable {

    @FXML
    private Text Title;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Text txt;

    @FXML
    private ListView<String> suggestionsList;

    private ObservableList<String> bookTitles;

    @FXML
    private Button addNewBookButton;

    @FXML
    private Button EditBookButton;

    @FXML
    private Button removeBookButton;

    @FXML
    private Button backButton;

 @Override
public void initialize(URL location, ResourceBundle resources) {
    // Initialize book titles with unique titles and authors from the database
    DBhandler dbHandler = new DBhandler();
    List<Book> uniqueBooks = dbHandler.getUniqueTitleBooks();
    
    // Extract titles and authors from uniqueBooks, formatted as "Title by Author"
    bookTitles = FXCollections.observableArrayList(
        uniqueBooks.stream()
            .map(book -> book.getTitle() + " by " + book.getAuthor())
            .collect(Collectors.toList())
    );

    // Set up suggestions on typing
    searchTextField.setOnKeyReleased(this::handleSearch);
    suggestionsList.setVisible(false);

    // Set up click event for selecting an item from the suggestions
    suggestionsList.setOnMouseClicked(event -> {
        String selected = suggestionsList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            searchTextField.setText(selected);
            suggestionsList.setVisible(false);
            txt.setText("Selected Book: " + selected);
        }
    });
}
@FXML
public void handleEditBookButton(ActionEvent event) {
    String selectedBook = searchTextField.getText().trim(); // Get the selected book from the text field

    if (selectedBook.isEmpty() || !selectedBook.contains(" by ")) {
        // Show an alert if the input is invalid
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("Please select a book in the format 'Title by Author' before editing.");
        alert.showAndWait();
        return;
    }

    // Extract the title and author from the selected item (assumes format "Title by Author")
    String[] bookParts = selectedBook.split(" by ");
    String title = bookParts[0];
    String author = bookParts[1];

    try {
        // Load the EditBookAfterSelecting scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/editBookAfterSelecting.FXML"));
        Parent root = loader.load();

        // Get the controller for the new scene
        EditBookAfterSelectingController controller = loader.getController();

        // Set the previous title and author in the fields in EditBookAfterSelectingController
        controller.setBookDetails(title, author);

        // Show the scene (assuming you have a stage)
        Stage stage = (Stage) searchButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
public void handleremoveBookButton(ActionEvent event) {
    String sentence = searchTextField.getText().trim(); // Example input

    if (sentence.isEmpty() || !sentence.contains(" by ")) {
        // Show an alert if the input is invalid
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("Please select a book in the format 'Title by Author' before removing.");
        alert.showAndWait();
        return;
    }

    String[] parts = sentence.split(" by ", 2); // Split the string into two parts at " by "
    String titleToDelete = parts[0].trim();  // Extract the title and remove any extra spaces
    String authorToDelete = parts[1].trim(); // Extract the author and remove any extra spaces

    // Confirmation alert
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Remove Book Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("When Removing This Book, It Will Be Permanently Lost.");

    if (alert.showAndWait().get() == ButtonType.OK) {
        boolean isDeleted = new DBhandler().deleteBookByTitle(titleToDelete, authorToDelete);

        if (isDeleted) {
            txt.setText("Removing is done successfully.");
        } else {
            txt.setText("Failed to remove the book. Please check the title.");
        }
    }
}

@FXML
public void handlesearchButton(ActionEvent event) {
    if (searchTextField.getText().trim().isEmpty()) {
        // Show an alert if the search field is empty
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Search Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a book title or author to search.");
        alert.showAndWait();
        return;
    }
    suggestionsList.setVisible(false); // Hide suggestions on search
}



    private void handleSearch(KeyEvent event) {
        String input = searchTextField.getText().toLowerCase();

        // Filter the list based on the input
        List<String> filteredSuggestions = bookTitles.stream()
                .filter(title -> title.toLowerCase().startsWith(input))
                .collect(Collectors.toList());

        // Update ListView with filtered suggestions
        suggestionsList.getItems().setAll(filteredSuggestions);

        // Show the ListView only if there are matching suggestions
        suggestionsList.setVisible(!filteredSuggestions.isEmpty());
    }

    private List<String> getBookTitlesFromDB() {
        List<String> titles = new ArrayList<>();
        String query = "SELECT title, author, MIN(bookID) AS bookID FROM Book GROUP BY title, author";  // Adjust to match your schema
        try (Connection conn = DBconnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                titles.add(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titles;
    }


    @FXML
    public void handleBackButton(ActionEvent event) {
        loadMain();
    }

    @FXML
    public void handleaddNewBookButton(ActionEvent event) {
        loadAddNewBook();
    }

 


    }





