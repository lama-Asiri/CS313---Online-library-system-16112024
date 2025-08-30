/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package OnlineLibrarySystem.controllers;

//import static cs313reemproject.CS313ReemProject.loadManageBookInventory;
//import static cs313reemproject.CS313ReemProject.show;
//import static cs313reemproject.CS313ReemProject.showTrack;
import static OnlineLibrarySystem.OnlineLibrarySystem.loadManageBookInventory;
import static OnlineLibrarySystem.OnlineLibrarySystem.show;
import static OnlineLibrarySystem.OnlineLibrarySystem.showTrack;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Remy <3
 */
public class MainController {

    @FXML
    private Text txt;

    @FXML
    private Button manageButton;

    @FXML
    private Button trackButton;

    @FXML
    private Button processButton;

    @FXML
    public void handlemanageButton(ActionEvent event) {
        loadManageBookInventory();
    }

    @FXML
    public void handletrackButton(ActionEvent event) {
        showTrack();
    }

    @FXML
    public void handleprocessButton(ActionEvent event) {
        show();
    }

}
