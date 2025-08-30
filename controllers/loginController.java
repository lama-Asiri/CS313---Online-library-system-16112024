/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package OnlineLibrarySystem.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import OnlineLibrarySystem.OnlineLibrarySystem;
 
import OnlineLibrarySystem.DB.DBhandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author lamo9
 */
public class loginController  {

    @FXML
    private TextField emailTxtF;
    @FXML
    private PasswordField passwordTxtf;
    @FXML
    private Button btnLogin;
    @FXML
    private Label CreateLbl,lbl3,text3,text4,text6,text7;
    @FXML
    private Label ResLbl;
    @FXML private VBox pane;
    @FXML private HBox text2;
    @FXML private GridPane grid,grid2;
    

    /**
     * Initializes the controller class.
     */
     

  @FXML
private void handleLoginBtn(ActionEvent event) throws Exception {
    String email = emailTxtF.getText().trim();
    String password = passwordTxtf.getText().trim();

    // Validate inputs
    if (email.isEmpty()) {
        showAlert("Input Error", "Email cannot be empty.");
        return;
    }
    if (!isValidEmail(email)) {
        showAlert("Input Error", "Invalid email format. Please enter a valid email.");
        return;
    }
    if (password.isEmpty()) {
        showAlert("Input Error", "Password cannot be empty.");
        return;
    }

    // Attempt to log in
    String role = DBhandler.loginUser(email, password);

    if (role == null) {
        showAlert("Invalid Credentials", "Incorrect email or password. Please try again.");
        return;
    }

    // Navigate to the appropriate scene based on role
    switch (role) {
        case "Admin":
            OnlineLibrarySystem.loadAdmin();
            break;
        case "Librarian":
            OnlineLibrarySystem.loadMain();
            break;
        case "Student":
            OnlineLibrarySystem.ShowBook();
            break;
        default:
            showAlert("Access Denied", "Unknown user role.");
    }
}

/**
 * Validates if an email is in the correct format.
 * @param email the email string to validate.
 * @return true if the email is valid, false otherwise.
 */
private boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    return email.matches(emailRegex);
}

/**
 * Shows an alert dialog with the provided title and message.
 * @param title the title of the alert dialog.
 * @param message the message to display in the alert dialog.
 */
private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
}

     @FXML
    private void handleCreateLbl(MouseEvent  event) throws Exception  {
        OnlineLibrarySystem.showCreate();
    }
     @FXML
    private void handleForgotLbl(MouseEvent  event) throws Exception  {
        OnlineLibrarySystem.showForgot();
    }
    
}
