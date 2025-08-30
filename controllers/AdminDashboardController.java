package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;
import OnlineLibrarySystem.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;

public class AdminDashboardController {
    @FXML
    private ListView<User> userListView;
    private List<User> users = new ArrayList<>();
    private int selectedIndex = -1;
    @FXML
    private TextField searchField;
    @FXML
    private Button b1, b2, b3, b4, b5, b6;

    private ObservableList<User> filteredUsers = FXCollections.observableArrayList();

    // Initialize method to populate the ListView with data
    @FXML
    public void initialize() {
        users = DBhandler.getAllUsers(); // Fetch all users from the database
        if (users != null) {
            filteredUsers.addAll(users); // Add all users to the filtered list
        }
        userListView.setItems(filteredUsers);

        // Add key event listener to the search field to filter as the user types
        searchField.setOnKeyReleased(event -> filterUsers());

        // Initially hide the ListView
        userListView.setVisible(false);

        // Set the visibility of the ListView when a user is selected
        userListView.setOnMouseClicked(event -> {
            User selected = userListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                searchField.setText(selected.toString());
                userListView.setVisible(true);
            }
        });
    }

    // Method to filter users based on search query
    private void filterUsers() {
        String searchText = searchField.getText().toLowerCase();

        // Validate search input
        if (searchText.isEmpty()) {
            showError("Search Error", "Search field cannot be empty.");
            userListView.setVisible(false);
            return;
        }

        // Filter users by email
        List<User> filteredList = users.stream()
                .filter(user -> user.getEmail().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        filteredUsers.setAll(filteredList);

        if (filteredList.isEmpty()) {
            userListView.setVisible(false);
        } else {
            userListView.setVisible(true);
        }
    }

    @FXML
    public void handleSearchButton() {
        filterUsers();
    }

    @FXML
    public void handleSearch(KeyEvent event) {
        filterUsers();
    }

    // Add new user
    @FXML
    public void addUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/AddUser.fxml"));
            Parent root = loader.load();
            AddUserController addUserController = loader.getController();
            addUserController.setAdminController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add User");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Failed to load Add User screen.");
        }
    }

    // Update existing user
    @FXML
    public void updateUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        selectedIndex = userListView.getSelectionModel().getSelectedIndex();

        if (selectedUser != null && selectedIndex >= 0) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/UpdateUser.fxml"));
                Parent root = loader.load();

                updateUserController updateUserController = loader.getController();
                updateUserController.setAdminController(this);
                updateUserController.initializeForUpdate(selectedUser, selectedIndex);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Update User");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showError("Error", "Failed to load Update User screen.");
            }
        } else {
            showError("No Selection", "Please select a user to update.");
        }
    }

    // Remove selected user
    @FXML
    public void removeUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            // Show confirmation dialog
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to remove this user?");
            alert.setContentText("User: " + selectedUser.getName());

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    boolean success = DBhandler.removeUser(selectedUser.getEmail());
                    if (success) {
                        users.remove(selectedUser);
                        filteredUsers.remove(selectedUser);
                        userListView.getItems().remove(selectedUser);

                        showSuccess("User Removed", "The user has been successfully removed.");
                    } else {
                        showError("Error", "Failed to remove the user from the database.");
                    }
                }
            });
        } else {
            showError("No Selection", "Please select a user to remove.");
        }
    }

    // Add new user to the list (called from the AddUserController)
    public void addUserToList(User newUser) {
        if (newUser != null) {
            users.add(newUser);
            filteredUsers.add(newUser);
            userListView.refresh();
        } else {
            showError("Error", "User cannot be null.");
        }
    }

    // Update user in the list (called from the UpdateUserController)
    public void updateUserInListView(int index, User updatedUser) {
        if (index >= 0 && updatedUser != null) {
            users.set(index, updatedUser);
            filteredUsers.set(index, updatedUser);
            userListView.refresh();
        } else {
            showError("Error", "Invalid user data or index.");
        }
    }

    // Show success message
    private void showSuccess(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Show error message
    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Open Library Inventory screen
    @FXML
    public void openLibraryInventory() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/LibraryInventory.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Library Inventory");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Failed to load Library Inventory screen.");
        }
    }

    // Open System Settings screen
    @FXML
    public void openSystemSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/SystemSettings.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("System Settings");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error", "Failed to load System Settings screen.");
        }
    }
}
