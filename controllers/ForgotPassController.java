package OnlineLibrarySystem.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import OnlineLibrarySystem.OnlineLibrarySystem;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassController {

    @FXML
    private Button backBtn, b1;

    @FXML
    private TextField emailField;

    @FXML
    private void handleBack(ActionEvent event) throws Exception {
        OnlineLibrarySystem.showLogin();
    }

    @FXML
    public void handleForget(ActionEvent event) {
        String email = emailField.getText().trim();

        // Validate the email input
        if (email.isEmpty()) {
            showAlert("Input Error", "Email field cannot be empty.");
            return;
        }

        if (isValidEmail(email)) {
            // Placeholder for email sending functionality
            showAlert("Forgot Password", "An email has been sent to the provided address.");
        } else {
            showAlert("Invalid Email", "Please enter a valid email address.");
        }
    }

    // Email validation method using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Utility method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
