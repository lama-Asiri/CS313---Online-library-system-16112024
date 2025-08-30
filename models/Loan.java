package OnlineLibrarySystem.models;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class Loan {

    private int ID;

    private LocalDate LOANDATE;
    private LocalDate RETURNDATE;
    private LocalDate maxReturnDate;
    private ArrayList<Book> borrowedBooks;
    private String Status;

    public Loan(int ID, ArrayList<Book> borrowedBooks, String Status) {
        this.ID = ID;
        this.borrowedBooks = borrowedBooks;
        this.Status = Status;
    }

    public Loan(int ID, ArrayList<Book> borrowedBooks, LocalDate LOANDATE, LocalDate RETURNDATE, String Status) {
        this.ID = ID;
        this.LOANDATE = LOANDATE;
        this.RETURNDATE = RETURNDATE;
        this.borrowedBooks = borrowedBooks;
        this.Status = Status;
        this.maxReturnDate = LOANDATE.plusDays(14);
    }

    public long getDaysOverdue() {
        if (RETURNDATE != null && RETURNDATE.isAfter(maxReturnDate)) {
            return ChronoUnit.DAYS.between(maxReturnDate, (Temporal) RETURNDATE); // Calculate days overdue
        }
        return 0; // If the book is returned on or before maxReturnDate, no overdue
    }

    public LocalDate getMaxReturnDate() {
        return maxReturnDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return Status;
    }

    public void setLOANDATE(LocalDate LOANDATE) {
        this.LOANDATE = LOANDATE;
    }

    public void setRETURNDATE(LocalDate RETURNDATE) {
        this.RETURNDATE = RETURNDATE;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public LocalDate getLOANDATE() {
        return LOANDATE;
    }

    public LocalDate getRETURNDATE() {
        return RETURNDATE;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return "Loan ID: " + ID + ", Status: " + Status + ", Books: " + borrowedBooks.toString();
    }

    public String getBookID() {
        if (borrowedBooks != null && !borrowedBooks.isEmpty()) {
            return borrowedBooks.get(0).getId(); // Assuming the loan contains a list of books
        }
        return null; // Return null if no books are borrowed
    }

    // Calculate the fee based on days overdue
    public double calculateFee() {
        return getDaysOverdue() * 0.25;
    }

}
