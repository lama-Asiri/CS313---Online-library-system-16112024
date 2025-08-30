package OnlineLibrarySystem;

import OnlineLibrarySystem.controllers.AdminDashboardController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OnlineLibrarySystem extends Application {

    static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showLogin();
        //loadMain();
        //loadView("/OnlineLibrarySystem/views/admin.fxml", "Create Account");
    }

    public static void showCreate() throws Exception {

        loadView("/OnlineLibrarySystem/views/CreateAccount.fxml", "Create Account");
    }

    public static void showForgot() throws Exception {

        loadView("/OnlineLibrarySystem/views/ForgotPass.fxml", "Reset Password");
    }

    public static void showLogin() throws Exception {

        loadView("/OnlineLibrarySystem/views/loginView.fxml", "Login");
    }

    public static void ShowOrder() throws IOException, Exception {

        loadView("/OnlineLibrarySystem/views/Order.fxml", "Order");

    }

    public static void Showborrow() throws IOException, Exception {

        loadView("/OnlineLibrarySystem/views/Borrow.fxml", "borrw massge");

    }

    public static void ShowBook() throws IOException, Exception {

        loadView("/OnlineLibrarySystem/views/BookView.fxml", "view book");

    }

    public static void loadAdmin() {
        loadView("/OnlineLibrarySystem/views/admin.FXML", "admin");
    }

    public static void loadMain() {
        loadView("/OnlineLibrarySystem/views/Main.FXML", "main");
    }

    public static void loadManageBookInventory() {
        loadView("/OnlineLibrarySystem/views/manageBookInventory.FXML", "main");
    }

    public static void loadAddNewBook() {
        loadView("/OnlineLibrarySystem/views/AddNewBook.FXML", "main");
    }

    public static void loadEditExitingBook() {
        loadView("/OnlineLibrarySystem/views/EditBook.FXML", "main");
    }

    public static void loadRemoveBook() {
        loadView("/OnlineLibrarySystem/views/RemoveBook.FXML", "main");
    }

    public static void loadEditBook() {
        loadView("/OnlineLibrarySystem/views/editBookAfterSelecting.FXML", "main");
    }

    public static void showreq() {
        loadView("/OnlineLibrarySystem/views/req.fxml", "req");
    }

    public static void showDetails() {
        loadView("/OnlineLibrarySystem/views/bookeddetails.fxml", "details");
    }

    public static void showTrack() {
        loadView("/OnlineLibrarySystem/views/track.fxml", "track");
    }

    public static void show() {
        loadView("/OnlineLibrarySystem/views/Main_1.fxml", "Main");
    }

    public static void loadView(String fxmlPath, String title) {
        Parent root;
        try {
            root = FXMLLoader.load(OnlineLibrarySystem.class.getResource(fxmlPath));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(OnlineLibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
