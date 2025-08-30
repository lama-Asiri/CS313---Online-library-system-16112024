package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;
import OnlineLibrarySystem.models.Admin;
import OnlineLibrarySystem.models.Librarian;
import OnlineLibrarySystem.models.Student;
import OnlineLibrarySystem.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
//import models.User;

public class AddUserController {

    @FXML private TextField emailField;
    @FXML private TextField nameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField phoneField;
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private RadioButton adminRadio;
    @FXML private RadioButton librarianRadio;
    @FXML private RadioButton studentRadio;

    
    
    private AdminDashboardController adminController;
    private boolean isUpdateMode = false; // Flag to indicate if updating
    private int selectedIndex = -1; // Index of the user being updated
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    private ToggleGroup genderGroup;

    // Set the controller that will receive the added user
    public void setAdminController(AdminDashboardController adminController) {
        this.adminController = adminController;
    }

    // Method to set fields for update mode
    public void initializeForUpdate(User selectedUser, int index) {
        isUpdateMode = true;
        selectedIndex = index;

        // Pre-populate fields with existing user details
        emailField.setText(selectedUser.getEmail());
        nameField.setText(selectedUser.getName());
        passwordField.setText(selectedUser.getPassword());
        phoneField.setText(selectedUser.getPhone());

        // Set radio buttons based on the user's gender
        if ("Male".equals(selectedUser.getGender())) {
            maleRadio.setSelected(true);
        } else {
            femaleRadio.setSelected(true);
        }

        // Set radio buttons based on the user's role
        if ("Admin".equals(selectedUser.getRole())) {
            adminRadio.setSelected(true);
        } else if ("Librarian".equals(selectedUser.getRole())) {
            librarianRadio.setSelected(true);
        } else if ("Student".equals(selectedUser.getRole())) {
            studentRadio.setSelected(true);
        }
    }

    // Handle the "Save" button click
    @FXML
public void saveUser() {
    // Validate input fields (do not proceed if any field is empty)
    if (emailField.getText().isEmpty() || nameField.getText().isEmpty() ||
        passwordField.getText().isEmpty() || phoneField.getText().isEmpty() ||
        (!maleRadio.isSelected() && !femaleRadio.isSelected()) ||
        (!adminRadio.isSelected() && !librarianRadio.isSelected() && !studentRadio.isSelected())) {
        showError("Validation Error", "All fields must be filled.");
        return;
    }

    // Additional data validation
    if (!isValidEmail(emailField.getText())) {
        showError("Validation Error", "Please enter a valid email address.");
        return;
    }

    if (!isValidPhone(phoneField.getText())) {
        showError("Validation Error", "Please enter a valid phone number (10-15 digits).");
        return;
    }

    if (!isValidPassword(passwordField.getText())) {
        showError("Validation Error", "Password must be at least 8 characters long, contain one uppercase letter, one lowercase letter, and one digit.");
        return;
    }

    // Gather data from the input fields
    String email = emailField.getText();
    String name = nameField.getText();
    String password = passwordField.getText();
    String phone = phoneField.getText();
    String gender = maleRadio.isSelected() ? "male" : "female";
    String role = getSelectedRole();

    // Create a new User object
    User newUser = null;
    boolean isCreated = false;

    if (studentRadio.isSelected()) {
        newUser = new Student(name, email, password, gender, phone, "Student");
        isCreated = DBhandler.addUser(newUser);
    } else if (adminRadio.isSelected()) {
        newUser = new Admin(name, email, password, gender, phone, "Admin");
        isCreated = DBhandler.addUser(newUser);
    } else if (librarianRadio.isSelected()) {
        newUser = new Librarian(name, email, password, gender, phone, "Librarian");
        isCreated = DBhandler.addUser(newUser);
    }

    if (!isCreated) {
        showError("Database Error", "Failed to save the user to the database.");
        return;
    }

    // Check if it's in update mode or add mode
    if (isUpdateMode) {
        // Update the existing user
        adminController.updateUserInListView(selectedIndex, newUser);
    } else {
        // Add a new user
        adminController.addUserToList(newUser);
    }

    // Close the window after saving
    closeWindow();
}

// Utility methods for validation
private boolean isValidEmail(String email) {
    return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
}

private boolean isValidPhone(String phone) {
    return phone.matches("^\\d{10,15}$");
}

private boolean isValidPassword(String password) {
    return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
}

// Show error alert
private void showError(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    // Get selected role from radio buttons
    private String getSelectedRole() {
        if (adminRadio.isSelected()) return "Admin";
        if (librarianRadio.isSelected()) return "Librarian";
        if (studentRadio.isSelected()) return "Student";
        return null; // Shouldn't reach here if validation works properly
    }

    // Close the current window
    private void closeWindow() {
        ((Stage) emailField.getScene().getWindow()).close();
    }

 
}
