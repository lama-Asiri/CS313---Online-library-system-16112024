package OnlineLibrarySystem.controllers;
 
import OnlineLibrarySystem.DB.DBhandler;
import OnlineLibrarySystem.OnlineLibrarySystem;
import OnlineLibrarySystem.models.Book;
import OnlineLibrarySystem.models.Loan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
 
public class MainController_1 {
 
    @FXML
    private ListView<Loan> loanRequestListView;
    @FXML
    private TextField searchField;
    @FXML private Button back;
    @FXML private Button b2,b3,b4,b5,b6;
 
    private ObservableList<Loan> loanRequests = FXCollections.observableArrayList();
 
private DBhandler dbHandler = new DBhandler();

@FXML
public void initialize() {
    // Fetch loan data from the database
    List<Loan> loans = dbHandler.getAllLoans();
    loanRequests.addAll(loans);
    loanRequestListView.setItems(loanRequests);
    loanRequestListView.setCellFactory(param -> new ListCell<Loan>() {
        @Override
        protected void updateItem(Loan loan, boolean empty) {
            super.updateItem(loan, empty);
            if (empty || loan == null) {
                setText(null);
            } else {
                setText("ID: " + loan.getID() + ", Status: " + loan.getStatus() +
                        ", Book: " + loan.getBorrowedBooks().get(0).getTitle());
            }
        }
    });
}
@FXML
public void approveRequest() {
    updateLoanStatus("approved");
}

@FXML
public void rejectRequest() {
    updateLoanStatus("rejected");
}

@FXML
public void markPending() {
    updateLoanStatus("pending");
}

private void updateLoanStatus(String newStatus) {
    Loan selectedRequest = loanRequestListView.getSelectionModel().getSelectedItem();
    if (selectedRequest != null) {
        boolean success = dbHandler.updateLoanStatus(selectedRequest.getID(), newStatus);
        if (success) {
            selectedRequest.setStatus(newStatus);
            loanRequestListView.refresh();
        } else {
            showAlert("Database Error", "Failed to update loan status.");
        }
    } else {
        // Show an alert if no request is selected
        showAlert("No Selection", "Please select a loan request to update.");
    }
}

@FXML
public void handleSearchButton() {
    String searchText = searchField.getText().toLowerCase();

    if (searchText.isEmpty()) {
        // Show an alert if the search field is empty
        showAlert("Search Error", "Please enter a loan ID or keyword to search.");
    } else {
        List<Loan> filteredRequests = loanRequests.stream()
                .filter(req -> String.valueOf(req.getID()).contains(searchText))
                .collect(Collectors.toList());
        loanRequestListView.setItems(FXCollections.observableArrayList(filteredRequests));
    }
}

private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

@FXML
private void handleBack(ActionEvent event) throws Exception {
    OnlineLibrarySystem.loadMain();
}

 
 
 

}