package OnlineLibrarySystem.controllers;


import OnlineLibrarySystem.DB.DBhandler;
import static OnlineLibrarySystem.OnlineLibrarySystem.loadManageBookInventory;
import java.net.URL;
import java.util.ResourceBundle;
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

public class AddNewBookController {
    ObservableList<String> genrea=FXCollections.observableArrayList("fantasy", "science fiction", "romance", "mystery", "horror");
    @FXML
    private Label bookTitle, author, genre, ISBN, bookCover;
    
    @FXML
    private Text Title, txt;
    
    @FXML
    private TextField bookTitleTxt, authorTxt, ISBNTxt;
    
    @FXML
    private MenuButton menu;
    
    @FXML
    private MenuItem fantasy, scienceFiction, romance, mystery, horror;
    
    @FXML
    private Button addButton,backButton;
    @FXML
    private ChoiceBox genreBox;
    @FXML
    public void initialize(){
    genreBox.setItems(genrea);}
    @FXML
public void handleAddButton(ActionEvent event) {
    String title = bookTitleTxt.getText().trim();    // New title
    String author = authorTxt.getText().trim();      // New author
    String genre = (String) genreBox.getValue();     // Get the selected genre from the ChoiceBox
    String isbn = ISBNTxt.getText().trim();          // ISBN

    // Validation: Check if any required fields are empty
    if (title.isEmpty() || author.isEmpty() || genre == null || isbn.isEmpty()) {
        // Show an alert for missing fields
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all fields before adding the book.");
        alert.showAndWait();
        return;
    }

    // Validate if ISBN already exists in the database
    DBhandler dbHandler = new DBhandler();
    boolean isbnExists = dbHandler.checkIfISBNExists(isbn);  // Assuming a method to check for existing ISBNs

    if (isbnExists) {
        // Show an alert if the ISBN already exists
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("ISBN Error");
        alert.setHeaderText(null);
        alert.setContentText("This ISBN is already associated with an existing book. Please enter a different ISBN.");
        alert.showAndWait();
        return;
    }

    // Proceed to add the book if all fields are valid and ISBN is unique
    boolean success = dbHandler.addBook(title, author, genre, isbn);

    // Show success or failure message
    if (success) {
        txt.setText("Book Added Successfully!");
        // Optionally refresh the book list here if you're displaying it in the UI
    } else {
        txt.setText("Failed to Add Book.");
    }
}

    
    @FXML
    public void handleBackButton(ActionEvent event) {
        loadManageBookInventory();
    }
    
}
