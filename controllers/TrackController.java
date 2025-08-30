package OnlineLibrarySystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import OnlineLibrarySystem.DB.DBhandler;
import static OnlineLibrarySystem.OnlineLibrarySystem.loadMain;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class TrackController {

    @FXML
    private Button back, select, searchButton;
    @FXML
    private ListView<String> overdueLoanListView;
    @FXML
    private TextField loanIdSearchField;

    private ObservableList<String> overdueLoans = FXCollections.observableArrayList();
    private DBhandler dbHandler = new DBhandler();

   @FXML
public void initialize() {
    loadOverdueLoans();
}

private void loadOverdueLoans() {
    overdueLoans.clear();
    try {
        // Fetch all overdue loans from the database
        ResultSet rs = dbHandler.getOverdueLoans();

        while (rs.next()) {
            int loanId = rs.getInt("loanID"); // Loan ID
            Date maxReturnDate = rs.getDate("maxReturnDate"); // Max return date
            Date returnDate = rs.getDate("returnDate"); // Actual return date

            // Only consider overdue loans where returnDate is after maxReturnDate
            if (returnDate != null && maxReturnDate != null && returnDate.after(maxReturnDate)) {
                String loanInfo = "Loan #" + loanId + " - Return Date: " + returnDate + " (Overdue)";
                overdueLoans.add(loanInfo); // Add overdue loan to the list
            }
        }

    } catch (SQLException e) {
        e.printStackTrace(); // Handle any SQL exceptions
    }

    // Update the ListView to display overdue loans
    overdueLoanListView.setItems(overdueLoans);

    // Debugging output to confirm list update
    System.out.println("Overdue loans list updated: " + overdueLoans.size() + " items.");
}


// Method to manually convert java.sql.Date to LocalDate
    private LocalDate convertToLocalDate(Date sqlDate) {
        if (sqlDate != null) {
            // Extract year, month, and day from sqlDate and create LocalDate
            int year = sqlDate.getYear() + 1900; // Adjust for 1900 offset in java.sql.Date
            int month = sqlDate.getMonth() + 1; // Adjust for 0-based month index
            int day = sqlDate.getDate(); // Day of the month
            return LocalDate.of(year, month, day); // Return LocalDate
        }
        return null; // Return null if sqlDate is null
    }

  @FXML
public void handleSelectedAction(ActionEvent event) {
    String selectedLoan = overdueLoanListView.getSelectionModel().getSelectedItem();

    if (selectedLoan != null) {
        try {
            String[] loanDetails = selectedLoan.split(" - ");
            String loanId = loanDetails[0].split("#")[1].trim();
            String dueDateString = loanDetails[1].split(":")[1].trim();

            // Remove any non-date text, such as "(Overdue)"
            if (dueDateString.contains(" ")) {
                dueDateString = dueDateString.split(" ")[0];
            }

            LocalDate dueDate = LocalDate.parse(dueDateString);

            // Fetch the borrow date and display details
            LocalDate borrowDate = dbHandler.getBorrowDateByLoanId(loanId);
            showLoanDetails(loanId, borrowDate, dueDate);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error handling selected loan action.");
        }
    } else {
        // Show an alert if no loan is selected
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle("Selection Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select a loan from the list to view details.");
        alert.showAndWait();
    }
}

@FXML
public void handleSearch(ActionEvent event) {
    String loanId = loanIdSearchField.getText().trim();  // Get text from TextField

    if (loanId.isEmpty()) {
        // Show an alert if the search field is empty
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle("Search Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a loan ID to search.");
        alert.showAndWait();
    } else {
        searchOverdueLoansById(loanId);  // Proceed with the search if loan ID is provided
    }
}


    public void showLoanDetails(String loanId, LocalDate borrowDate, LocalDate dueDate) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/bookeddetails.fxml"));
            Parent root = loader.load();
            BookeddetailsController detailsController = loader.getController();
            detailsController.setLoanDetails(borrowDate, dueDate);

            Stage stage = (Stage) overdueLoanListView.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void searchOverdueLoansById(String loanId) {
        overdueLoans.clear();  // Clear the list first

        try {
            ResultSet rs = dbHandler.getOverdueLoansById(loanId);  // Execute the query for specific loanId
            System.out.println("Searching for overdue loans with ID: " + loanId);

            boolean foundOverdueLoans = false;  // Flag to check if the loan is overdue

            while (rs.next()) {
                int loanIdResult = rs.getInt("loanID");  // Retrieve loanId
                Date maxReturnDate = rs.getDate("maxReturnDate");  // Retrieve maxReturnDate
                Date returnDate = rs.getDate("returnDate");  // Retrieve returnDate

                // Debugging output to verify data retrieval
                System.out.println("Loan ID: " + loanIdResult + ", Max Return Date: " + maxReturnDate + ", Return Date: " + returnDate);

                if (returnDate != null && maxReturnDate != null && returnDate.after(maxReturnDate)) {
                    String loanInfo = "Loan #" + loanIdResult + " - Max Return: " + maxReturnDate + " - Return: " + returnDate;
                    overdueLoans.add(loanInfo);  // Add to the list if overdue
                    foundOverdueLoans = true;
                }
            }

            if (!foundOverdueLoans) {
                System.out.println("No overdue loans found for loan ID: " + loanId);
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print exception if there’s any error
        }

        // Update the ListView with the overdue loans
        overdueLoanListView.setItems(overdueLoans);
        System.out.println("ListView updated with overdue loans.");
    }

    @FXML
    public void handleBack() {
        // Navigate back to the main menu or previous screen
        loadMain();
    }
}
