package OnlineLibrarySystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class BookeddetailsController {

    @FXML
    private Label Title, Bdate, Ddate, DateOverdueLabel, OverdueFeeLabel;
    @FXML
    private TextField borrowDateField, dueDateField, days, overdueFeeField;
    @FXML
    private Button b1,b2;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private double overdueFeeRate = 1.5; // Example fee rate per day
    private final int maxReturnDays = 14; // Max return period (14 days)

    public void setLoanDetails(LocalDate borrowDate, LocalDate dueDate) {
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;

        // Set the borrow and due dates in the text fields
        borrowDateField.setText(borrowDate.toString());
        dueDateField.setText(dueDate.toString());

        // Calculate days overdue and fees
        long daysOverdue = calculateDaysOverdue(dueDate);

        double fee = calculateOverdueFee(daysOverdue);

        // Populate the fields
        days.setText(String.valueOf(daysOverdue));
        overdueFeeField.setText("$" + String.format("%.2f", fee));
    }

    private long calculateDaysOverdue(LocalDate dueDate) {
        LocalDate currentDate = LocalDate.now();
        if (dueDate.isAfter(currentDate)) {

            long daysOverdue = ChronoUnit.DAYS.between(currentDate, dueDate);

            return daysOverdue;
        } else {
            return 0;
        }
    }

    private double calculateOverdueFee(long daysOverdue) {
        // If the return date is later than dueDate + maxReturnDays, it's overdue
        if (daysOverdue > 0) {
            return daysOverdue * overdueFeeRate;
        } else {
            return 0.0;
        }
    }

    // Handle sending an email notification for overdue loan
    @FXML
    private void handleSendEmailNotification(ActionEvent event) {
        // Simulating sending an email notification (you can integrate JavaMail or other services here)
        System.out.println("Sending email notification about overdue loan...");

        // In a real-world application, you would send an email using an SMTP service here.
        // For now, it's a simple console log.
        String message = "Your loan is overdue. Please return it as soon as possible. Overdue fee: $"
                + overdueFeeField.getText();
        System.out.println("Email sent: " + message);

        // You could trigger a UI feedback (e.g., a success message) here as well
    }

    // Handle back action (close window or navigate to the previous screen)
    @FXML
    private void handleBack(ActionEvent event) {
        OnlineLibrarySystem.OnlineLibrarySystem.loadView("/OnlineLibrarySystem/views/track.fxml", "Track");
//        // Close the current window
//        Stage stage = (Stage) Title.getScene().getWindow();
//        stage.close();
    }
}
