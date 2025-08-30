package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;
import static OnlineLibrarySystem.OnlineLibrarySystem.loadEditExitingBook;
import static OnlineLibrarySystem.OnlineLibrarySystem.loadManageBookInventory;
import OnlineLibrarySystem.models.Book;
//import static cs313reemproject.CS313ReemProject.loadEditExitingBook;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EditBookAfterSelectingController {
//cs313reemproject.controllers.EditBookAfterSelectingController

    @FXML
    private Label bookTitle, author, genre, ISBN, bookCover;

    @FXML
    private Text Title, txt;

    @FXML
    private TextField bookTitleTxt, authorTxt, ISBNTxt, bookTitleTxt1, authorTxt1;

    @FXML
    private MenuButton menu;

    @FXML
    private MenuItem fantasy, scienceFiction, romance, mystery, horror;

    @FXML
    private Button saveButton, backButton;

    ObservableList<String> genrea = FXCollections.observableArrayList("fantasy", "science fiction", "romance", "mystery", "horror");
    @FXML
    private ChoiceBox genreBox;

    @FXML
    public void initialize() {
        genreBox.setItems(genrea);
    }

    @FXML
    public void handleSaveButton(ActionEvent event) {
        // Get the new values from the text fields
        String newTitle = bookTitleTxt.getText().trim();    // New title
        String newAuthor = authorTxt.getText().trim();      // New author
        String newGenre = (String) genreBox.getValue();     // Get the selected genre from the ChoiceBox

        // Get the old title and author from the previous scene
        String oldTitle = bookTitleTxt1.getText().trim();   // Old title field
        String oldAuthor = authorTxt1.getText().trim();     // Old author field

        // Validation: Check if required fields are empty
        if (newTitle.isEmpty() || newAuthor.isEmpty() || oldTitle.isEmpty() || oldAuthor.isEmpty()) {
            // Show an alert for missing fields
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields before saving.");
            alert.showAndWait();
            return;
        }

        // Validation: Check if a genre is selected
        if (newGenre == null) {
            // Show an alert for missing genre
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a genre.");
            alert.showAndWait();
            return;
        }

        // Create a Book object with the new details
        Book updatedBook = new Book(newTitle, newAuthor, newGenre);

        // Call the DBhandler method to update the book details
        DBhandler dbHandler = new DBhandler();
        boolean success = dbHandler.editBook(oldTitle, oldAuthor, updatedBook);

        // Show confirmation or failure message
        if (success) {
            txt.setText("Changes Saved Successfully!");
        } else {
            txt.setText("Failed to Save Changes. Please Try Again.");
        }
    }

    @FXML
    public void handleBackButton() {
        loadManageBookInventory();
    }

    // This method sets the previous book details
    public void setBookDetails(String title, String author) {
        bookTitleTxt1.setText(title); // Old title field
        authorTxt1.setText(author);
        bookTitleTxt.setText(title);   // New title field
        authorTxt.setText(author);     // Author field
    }
//genreTxt

}
