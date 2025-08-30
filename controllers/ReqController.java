package OnlineLibrarySystem.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReqController {

    @FXML
    private TextField reqdate;
    
    @FXML
    private Button rejbtn,apprbtn,back;
    
    @FXML
    private Label title,bookAvailableLabel,bookNotAvailableLabel;

    @FXML
    private void handlereqDateAction(ActionEvent event) {
        System.out.println(" clicked ");
        System.out.println(reqdate.getText());
    }

    @FXML
    private void handleRejectBtn(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Request Rejected");
        alert.setHeaderText(null);
        alert.setContentText("The request dated " + reqdate.getText() + " has been rejected.");
        alert.showAndWait();
        System.out.println("Reject button clicked for request date: " + reqdate.getText());
    }

    @FXML
    private void handleApproveAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Request Approved");
        alert.setHeaderText(null);
        alert.setContentText("The request dated " + reqdate.getText() + " has been approved.");
        alert.showAndWait();
        System.out.println("Approve button clicked for request date: " + reqdate.getText());
    }

    @FXML
    public void handleBack(ActionEvent event) throws Exception {
        OnlineLibrarySystem.OnlineLibrarySystem.show();
    }

}
