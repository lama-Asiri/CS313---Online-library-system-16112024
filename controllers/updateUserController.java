package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;
import OnlineLibrarySystem.models.Admin;
import OnlineLibrarySystem.models.Librarian;
import OnlineLibrarySystem.models.Student;
import OnlineLibrarySystem.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class updateUserController {

    @FXML private TextField emailField;
    @FXML private TextField nameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField phoneField;
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private RadioButton adminRadio;
    @FXML private RadioButton librarianRadio;
    @FXML private RadioButton studentRadio;
    @FXML private Button b1;

    private AdminDashboardController adminController;
    private boolean isUpdateMode = false;
    private int selectedIndex = -1;

    @FXML private ToggleGroup roleGroup;
    @FXML private ToggleGroup genderGroup;

    public void setAdminController(AdminDashboardController adminController) {
        this.adminController = adminController;
    }
public void initializeForUpdate(User selectedUser, int index) {
    isUpdateMode = true;
    selectedIndex = index;
    // Populate fields with user data for update
    emailField.setText(selectedUser.getEmail());
    nameField.setText(selectedUser.getName());
    passwordField.setText(selectedUser.getPassword());
    phoneField.setText(selectedUser.getPhone());
    maleRadio.setSelected("male".equals(selectedUser.getGender()));
    femaleRadio.setSelected("female".equals(selectedUser.getGender()));

    switch (selectedUser.getRole()) {
        case "Admin":
            adminRadio.setSelected(true);
            break;
        case "Librarian":
            librarianRadio.setSelected(true);
            break;
        case "Student":
            studentRadio.setSelected(true);
            break;
    }
}


    // Handle saving user data (both creating and updating)
@FXML
public void saveUser() {
    if (isInputValid()) {
        String email = emailField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();
        String gender = maleRadio.isSelected() ? "male" : "female";
        String role = getSelectedRole();

        User user = createUserObject(name, email, password, gender, phone, role);
        boolean isOperationSuccessful;

        if (isUpdateMode) {
            // Update existing user
            isOperationSuccessful = DBhandler.updateUser(user);
            if (isOperationSuccessful) {
                adminController.updateUserInListView(selectedIndex, user);
            }
        } else {
            // Add new user
            isOperationSuccessful = DBhandler.addUser(user);
            if (isOperationSuccessful) {
                adminController.addUserToList(user);
            }
        }

        if (isOperationSuccessful) {
            closeWindow();
        } else {
            showError("Error", "Operation failed. Please try again.");
        }
    }
}

// Validate user input
private boolean isInputValid() {
    if (emailField.getText().isEmpty() || nameField.getText().isEmpty() ||
        passwordField.getText().isEmpty() || phoneField.getText().isEmpty() ||
        (!maleRadio.isSelected() && !femaleRadio.isSelected()) ||
        (!adminRadio.isSelected() && !librarianRadio.isSelected() && !studentRadio.isSelected())) {
        showError("Validation Error", "All fields must be filled.");
        return false;
    }

    if (!isValidEmail(emailField.getText())) {
        showError("Validation Error", "Please enter a valid email address.");
        return false;
    }

    if (!isValidPhone(phoneField.getText())) {
        showError("Validation Error", "Please enter a valid phone number (10-15 digits).");
        return false;
    }

    if (!isValidPassword(passwordField.getText())) {
        showError("Validation Error", "Password must be at least 8 characters long, contain one uppercase letter, one lowercase letter, and one digit.");
        return false;
    }

    return true;
}

// Validate email format
private boolean isValidEmail(String email) {
    return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
}

// Validate phone number format
private boolean isValidPhone(String phone) {
    return phone.matches("^\\d{10,15}$");
}

// Validate password strength
private boolean isValidPassword(String password) {
    return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
}

private String getSelectedRole() {
    if (adminRadio.isSelected()) return "Admin";
    if (librarianRadio.isSelected()) return "Librarian";
    if (studentRadio.isSelected()) return "Student";
    return null;
}

private User createUserObject(String name, String email, String password, String gender, String phone, String role) {
    switch (role) {
        case "Admin":
            return new Admin(name, email, password, gender, phone, role);
        case "Librarian":
            return new Librarian(name, email, password, gender, phone, role);
        case "Student":
            return new Student(name, email, password, gender, phone, role);
        default:
            return null;
    }
}

private void closeWindow() {
    ((Stage) emailField.getScene().getWindow()).close();
}

private void showError(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

}
