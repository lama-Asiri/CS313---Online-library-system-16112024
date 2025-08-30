package  OnlineLibrarySystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SystemSettingsController {

    // UI Elements
    @FXML private TextField maxBooksField;
    @FXML private TextField overdueFeesField;
    @FXML private TextField loanDurationField;

    // Settings (stored in-memory)
    private int maxBooks = 5;  // Default value
    private double overdueFees = 0.50;  // Default value
    private int loanDuration = 14;  // Default value

    @FXML
    public void initialize() {
        // Initialize the fields with default values
        maxBooksField.setText(String.valueOf(maxBooks));
        overdueFeesField.setText(String.valueOf(overdueFees));
        loanDurationField.setText(String.valueOf(loanDuration));
    }

    // Method to save the settings when the user clicks "Save"
    @FXML
    public void saveSettings() {
        try {
            // Parse the values entered by the user
            maxBooks = Integer.parseInt(maxBooksField.getText());
            overdueFees = Double.parseDouble(overdueFeesField.getText());
            loanDuration = Integer.parseInt(loanDurationField.getText());

            // Confirm successful save
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Settings Saved");
            alert.setHeaderText(null);
            alert.setContentText("System settings have been updated successfully.");
            alert.showAndWait();

            // Close the window after saving
            closeWindow();

        } catch (NumberFormatException e) {
            // Show an error alert if any input is invalid
            showError("Invalid Input", "Please enter valid numbers for all fields.");
        }
    }

    // Close the current window
    private void closeWindow() {
        ((Stage) maxBooksField.getScene().getWindow()).close();
    }

    // Show an error alert with the specified message
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Getters for the settings
    public int getMaxBooks() {
        return maxBooks;
    }

    public double getOverdueFees() {
        return overdueFees;
    }

    public int getLoanDuration() {
        return loanDuration;
    }
}
