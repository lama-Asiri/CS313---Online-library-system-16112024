package OnlineLibrarySystem.controllers;
 
import OnlineLibrarySystem.OnlineLibrarySystem;
import OnlineLibrarySystem.DB.DBhandler;
import OnlineLibrarySystem.models.Student;
import OnlineLibrarySystem.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
 
public class CreateAccountController {
 
    @FXML
    private TextField nameField;
 
    @FXML
    private TextField emailField;
 
    @FXML
    private PasswordField passwordField;
 
    @FXML
    private TextField phoneField;
 
    @FXML
    private ToggleGroup gender;
 
    @FXML
    private Button createBtn;
   @FXML
    private RadioButton fe;
    @FXML
    private RadioButton me;
    @FXML
    private Button backBtn;
 
    @FXML
    private void initialize() {
         gender = new ToggleGroup();
        fe.setToggleGroup(gender);
        me.setToggleGroup(gender);
    }
 
   @FXML
private void handleCreateAccount(ActionEvent event) {
    // Fetch user input
    String name = nameField.getText().trim();
    String email = emailField.getText().trim();
    String password = passwordField.getText().trim();
    String phone = phoneField.getText().trim();
    RadioButton selectedGender = (RadioButton) gender.getSelectedToggle();
    String gender1 = selectedGender != null ? selectedGender.getText() : null;

    // Validate input fields
    if (name.isEmpty()) {
        showAlert("Input Error", "Name cannot be empty.");
        return;
    }
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
    if (!isValidPassword(password)) {
        showAlert("Input Error",  "Password must be at least 8 characters long, contain one uppercase letter, one lowercase letter, and one digit.");
        return;
    }
    if (phone.isEmpty()) {
        showAlert("Input Error", "Phone number cannot be empty.");
        return;
    }
    if (!isValidPhone(phone)) {
        showAlert("Input Error", "Invalid phone number format. Please enter a valid phone number.");
        return;
    }
    if (gender1 == null) {
        showAlert("Input Error", "Please select a gender.");
        return;
    }

    // Create a new User object
    User newUser = new Student(name, email, password, gender1, phone, "Student");

    // Call the DatabaseHandler method to create the account
    boolean isCreated = DBhandler.createStudentAccount((Student) newUser);

    if (isCreated) {
        showAlert("Success", "Account created successfully!");
        clearFields();
    } else {
        showAlert("Error", "Failed to create account. Please try again.");
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
 * Validates if a password meets the required strength criteria.
 * @param password the password string to validate.
 * @return true if the password is valid, false otherwise.
 */
private boolean isValidPassword(String password) {
    return password.length() >= 8 &&password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
}

/**
 * Validates if a phone number is in a valid format.
 * @param phone the phone number string to validate.
 * @return true if the phone number is valid, false otherwise.
 */
private boolean isValidPhone(String phone) {
    String phoneRegex = "^\\d{10,15}$"; // Adjust the range if needed
    return phone.matches(phoneRegex);
}

/**
 * Utility method to show alerts.
 * @param title the title of the alert dialog.
 * @param message the message to display in the alert dialog.
 */
private void showAlert(String title, String message) {
 Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

/**
 * Clears all input fields after successful account creation.
 */
private void clearFields() {
    nameField.clear();
    emailField.clear();
    passwordField.clear();
    phoneField.clear();
    gender.selectToggle(null);
}

 
    @FXML
    private void handleBack(ActionEvent event) throws Exception {
        // Navigate back to the login screen
        OnlineLibrarySystem.showLogin();
    }
 
    // Utility method to show alerts

}

