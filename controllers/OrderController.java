package OnlineLibrarySystem.controllers;
 
import OnlineLibrarySystem.DB.DBhandler;
import OnlineLibrarySystem.models.Book;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import OnlineLibrarySystem.OnlineLibrarySystem;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
 
/**
* FXML Controller class
*
* @author joman
*/
public class OrderController implements Initializable {
 
    @FXML
    private TitledPane ttb;
    @FXML
    public ListView<Book> orderListArea;
    private ObservableList<Book> borrowedBooks;
    @FXML
    private Button returnn;
    @FXML
    private TextField returntext;
    @FXML
    private Button backtobook;
      @FXML
    private Label masseg;
    // This method sets the borrowedBooks list
   private DBhandler dbHandler = new DBhandler(); // Initialize database handler
 

     public void setBorrowedBooks(ObservableList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
        if (orderListArea != null) {
            orderListArea.setItems(borrowedBooks); // Update the orderListArea with borrowed books
        }
    }
 
 

    @FXML
    public void handleBackbutton(ActionEvent event) throws Exception {
        OnlineLibrarySystem.ShowBook();
    }

@FXML
    private void handleReturnButton(ActionEvent event) throws IOException, Exception {
        String bookIdToReturn = returntext.getText().trim();
        if (bookIdToReturn.isEmpty()) {
            masseg.setText("Enter ID");
            return;
        }
 
        Book bookToReturn = findBookInBorrowedBooks(bookIdToReturn);
        if (bookToReturn != null) {
            // Update the database to mark the book as returned and available
            boolean isReturned = dbHandler.returnBook(bookToReturn.getId(), getCurrentStudentEmail());
 
            if (isReturned) {
                borrowedBooks.remove(bookToReturn);  // Remove the book from the borrowed list
                BookView.bookList.add(bookToReturn);  // Add it back to the available books list
                orderListArea.setItems(borrowedBooks);  // Refresh the ListView
                BookView.getInstance().bookListView.setItems(BookView.bookList);  // Update the main book view
                masseg.setText("Book returned successfully.");
            } else {
                masseg.setText("Database error occurred. Try again.");
            }
            OnlineLibrarySystem.ShowBook();
        } else {
            masseg.setText("Invalid ID");
        }
    }
private Book findBookInBorrowedBooks(String bookId) {
        for (Book book : borrowedBooks) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;  // Book not found in borrowed books
    }
 private String getCurrentStudentEmail() {

        // Placeholder for getting the current logged-in user's email
    
        return DBhandler.currrntUser;  // Replace with actual logic

    }
    public void initialize(URL url, ResourceBundle rb) {
 
        setBorrowedBooks(BookView.borrowedBooks);
        orderListArea.setItems(BookView.borrowedBooks);
 
//    orderListArea.setItems(borrowedBooks); 
    }
 
}

