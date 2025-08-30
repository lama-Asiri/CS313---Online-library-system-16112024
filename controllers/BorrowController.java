package OnlineLibrarySystem.controllers;
 


import OnlineLibrarySystem.models.Book;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
 
import OnlineLibrarySystem.OnlineLibrarySystem;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
 
/**
* FXML Controller class
*
* @author joman
*/
public class BorrowController implements Initializable {
@FXML
    private Button borrw;
    @FXML
    private Button cancel;
    @FXML 
    private DatePicker date;
 
    private Book selectedBook;
    
 
    public void setBook(Book book) {
        selectedBook = book;
    }
  
   @FXML 
        public  void handleBorrwwButton(ActionEvent event) throws IOException  {
     try {
         
             BookView bookView = BookView.getInstance();
                     if (bookView != null&& selectedBook != null) {
                // Remove the selected book from the book list and add it to the borrowed books
                         
                BookView.bookList.remove(selectedBook);
                     
                BookView.borrowedBooks.add(selectedBook);
// borrowedBooks.add(selectedBook);
                // Update the ListView in BookView and BorrowController
                bookView.updateBookListView(); 
                
                OnlineLibrarySystem.ShowBook();
                
            } else {
                System.out.println("BookView instance is null!");
            }
                     bookView.updateBookListView();

        OnlineLibrarySystem.ShowBook();

     } catch (Exception ex) { 
         Logger.getLogger(BorrowController.class.getName()).log(Level.SEVERE, null, ex);
     }
 
  }
         @FXML 
        public  void handlecancelButton(ActionEvent event) throws IOException  {
     try {
         OnlineLibrarySystem.ShowBook();
     } catch (Exception ex) {
         Logger.getLogger(BorrowController.class.getName()).log(Level.SEVERE, null, ex);
     }
 
  }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(14);
        date.setValue(futureDate);
        date.setEditable(false);
 
    }    
}

