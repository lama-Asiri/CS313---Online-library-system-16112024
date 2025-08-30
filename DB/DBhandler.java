package OnlineLibrarySystem.DB;

import OnlineLibrarySystem.models.Admin;
import OnlineLibrarySystem.models.Book;
import OnlineLibrarySystem.models.Librarian;
import OnlineLibrarySystem.models.Loan;
import OnlineLibrarySystem.models.Student;
import OnlineLibrarySystem.models.User;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBhandler {

    // Method to validate user login
    public static String currrntUser;

    public static String loginUser(String email, String password) {
        String query = "SELECT userPassword, userRole FROM UserTable WHERE email=?";
        currrntUser = email;
        try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("userPassword");
                String role = rs.getString("userRole");

                // Check if password matches
                if (storedPassword.equals(password)) {
                    return role;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Invalid login
    }

    public static boolean createStudentAccount(User user) {
        String query = "INSERT INTO UserTable (userName, email, userPassword, gender, phoneNo, userRole) "
                + "VALUES (?, ?, ?, ?, ?, 'Student')";
        Connection conn = null;
        try {
            conn = DBconnection.getInstance().getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, user.getPassword());
                pstmt.setString(4, user.getGender());
                pstmt.setString(5, user.getPhone());

                pstmt.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {

                e.printStackTrace();
                if (conn != null) {
                    try {
                        conn.rollback();  
                    } catch (SQLException rollbackException) {
                        rollbackException.printStackTrace();  
                    }
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); 
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace(); 
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);  
                    conn.close();  
                } catch (SQLException closeException) {
                    closeException.printStackTrace(); 
                }
            }
        }
        return false;  
    }


    public boolean borrowBook(String bookId, String studentEmail) {
        System.out.println(studentEmail);
        System.out.println(bookId);
        String query = "INSERT INTO Loan (userEmail, bookId, loanDate, returnDate, status) VALUES (?, ?, SYSDATE, SYSDATE + 14, 'pending')";
        System.out.println("1");
        try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            System.out.println("2");
            pstmt.setString(1, studentEmail);
            pstmt.setString(2, bookId);
            System.out.println("3");

            System.out.println("4");
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("5");
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Book WHERE isAvailable = 'available'";  
        try (Connection conn = DBconnection.getInstance().getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String id = rs.getString("bookID");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isAvailable = rs.getString("isAvailable");
                books.add(new Book(title, author, id, isAvailable)); 
            }// public Book(String title, String author, int id)
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getUniqueTitleBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT title, author, MIN(bookID) AS bookID FROM Book GROUP BY title, author";  

        try (Connection conn = DBconnection.getInstance().getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String id = rs.getString("bookID");
                String title = rs.getString("title");
                String author = rs.getString("author");
                books.add(new Book(title, author, id)); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static Map<String, Integer> countNumberOfCopies() {
        String query = "SELECT title, COUNT(*) AS count FROM Book GROUP BY title";
        Map<String, Integer> titleCopiesMap = new HashMap<>();

        try (Connection conn = DBconnection.getInstance().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

           
            while (rs.next()) {
                String title = rs.getString("title");
                int count = rs.getInt("count");
                titleCopiesMap.put(title, count); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titleCopiesMap; 
    }


    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM UserTable";  
        try (Connection conn = DBconnection.getInstance().getConnection(); 
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String userName = rs.getString("userName");
                String email = rs.getString("email");
                String userPassword = rs.getString("userPassword");
                String userRole = rs.getString("userRole");
                String phoneNo = rs.getString("phoneNo");
                String gender = rs.getString("gender");

              
                User user = createUserByRole(userName, email, userPassword, userRole, phoneNo, gender);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    private static User createUserByRole(String name, String email, String password, String role, String phone, String gender) {
        switch (role.toLowerCase()) {
            case "student":
                return new Student(name, email, password, gender, phone, "Student");
            case "admin":
                return new Admin(name, email, password, gender, phone, "Admin");
            case "librarian":
                return new Librarian(name, email, password, gender, phone, "Librarian");
            default:
                return null; 
        }
    }

    public static boolean addUser(User user) {
        System.out.println("start");
        String checkEmailQuery = "SELECT COUNT(*) FROM UserTable WHERE email = ?";
        String insertQuery = "INSERT INTO UserTable (userName, email, userPassword, gender, phoneNo, userRole) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        System.out.println("starting process");

        try {

            System.out.println("enter try");
            conn = DBconnection.getInstance().getConnection();
            conn.setAutoCommit(false); // Disable auto-commit
            System.out.println("checking email");
            // Check if email already exists
            try (
                    PreparedStatement checkStmt = conn.prepareStatement(checkEmailQuery)) {
                System.out.println("check");
                checkStmt.setString(1, user.getEmail());
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Email already exists");
                    return false;
                }
            }
            System.out.println("start insert");
         
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                System.out.println(user.getRole());
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, user.getPassword());
                pstmt.setString(4, user.getGender());
                pstmt.setString(5, user.getPhone());
                pstmt.setString(6, user.getRole());
                System.out.println("insertd");

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("User added successfully");
                    conn.commit(); // Commit the transaction
                    return true;
                } else {
                    System.out.println("User insertion failed");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                if (conn != null) {
                    conn.rollback();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); 
                    conn.close(); // Close connection
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean updateUser(User user) {
        System.out.println("starting update");
        String updateQuery = "UPDATE UserTable SET userName = ?, userPassword = ?, gender = ?, phoneNo = ?, userRole = ? WHERE email = ?";
        try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            System.out.println("entering intializing");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getGender());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getRole());
            pstmt.setString(6, user.getEmail());
            System.out.println("done of rows");

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("rows affected");
            if (rowsAffected > 0) {
                System.out.println("its true");
                System.out.println("User updated successfully!");
                return true;
            } else {
                System.out.println("email prob");
                System.out.println("No user found with this email to update.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("its false");
        return false;
    }


    public static boolean removeUser(String email) {
        String query = "DELETE FROM UserTable WHERE email = ?";
        try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);  
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

// Search for users by email
    public List<User> searchUsersByEmail(String email) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE email LIKE ?";
        try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + email + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String userEmail = rs.getString("email");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                // Create the correct user subclass based on role
                User user = createUserByRole(userEmail, name, password, role, phone, gender);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean addBook(String title, String author, String genre, String isbn) {
        String query = "INSERT INTO Book (title, bookID, author, genre, isAvailable) VALUES (?, ?, ?, ?, 'available')";
        try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, isbn); 
            pstmt.setString(3, author);
            pstmt.setString(4, genre);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBookByTitle(String title, String author) {
        String query = "DELETE FROM Book WHERE title = ? AND author=?";
        try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean editBook(String oldTitle, String oldAuthor, Book newBook) {
        String query = "UPDATE Book SET title = ?, author = ?, genre = ? WHERE title = ? AND author = ?";
        try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, newBook.getTitle());   
            pstmt.setString(2, newBook.getAuthor()); 
            pstmt.setString(3, newBook.getGenre());  

            pstmt.setString(4, oldTitle);            
            pstmt.setString(5, oldAuthor);          

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM Loan INNER JOIN Book ON Loan.bookId = Book.bookId";

        try (Connection conn = DBconnection.getInstance().getConnection();
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int loanId = rs.getInt("loanId");
                String status = rs.getString("status");
                String bookTitle = rs.getString("title");
                String bookAuthor = rs.getString("author");
                String bookId = rs.getString("bookId");

                Book book = new Book(bookTitle, bookAuthor, bookId);
                ArrayList<Book> borrowedBooks = new ArrayList<>();
                borrowedBooks.add(book);

                Loan loan = new Loan(loanId, borrowedBooks, status);
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    public boolean updateLoanStatus(int loanId, String newStatus) {
        String query = "UPDATE Loan SET status = ? WHERE loanId = ?";
        try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            System.out.println(newStatus);
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, loanId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean returnBook(String bookId, String studentEmail) {
        try (Connection conn = DBconnection.getInstance().getConnection()) {
           
            String updateLoanQuery = "UPDATE Loan SET returnDate = ?, status = 'approved' WHERE bookId = ? AND userEmail = ?";
            try (PreparedStatement pstmtLoan = conn.prepareStatement(updateLoanQuery)) {
                pstmtLoan.setDate(1, Date.valueOf(LocalDate.now()));
                pstmtLoan.setString(2, bookId);
                pstmtLoan.setString(3, studentEmail);

                int loanRowsUpdated = pstmtLoan.executeUpdate();

                if (loanRowsUpdated == 0) {
                    return false; 
                }
            
                String updateBookQuery = "UPDATE Book SET isAvailable = 'available' WHERE bookID = ?";
                try (PreparedStatement pstmtBook = conn.prepareStatement(updateBookQuery)) {
                    System.out.println("1");
                    pstmtBook.setString(1, bookId);
                    System.out.println("2");
                    int bookRowsUpdated = pstmtBook.executeUpdate();
                    System.out.println("3");
                    return bookRowsUpdated > 0;  // Return true if both updates were successful
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getOverdueLoans() throws SQLException {
        String query = "SELECT loanId, maxReturnDate, returnDate FROM Loan WHERE returnDate > maxReturnDate";
        Connection conn = DBconnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery(); // This query fetches all overdue loans
    }

    public ResultSet getOverdueLoansById(String loanId) throws SQLException {
  
        String query = "SELECT loanID, maxReturnDate, returnDate FROM Loan WHERE loanID = ?";
        Connection conn = DBconnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, loanId);  // Set the loan ID parameter
        return stmt.executeQuery();
    }

    // Method to fetch borrow date by loanId (loanDate is the borrow date in the table)
    public LocalDate getBorrowDateByLoanId(String loanId) {
        String query = "SELECT loanDate FROM Loan WHERE loanId = ?";
        try (Connection conn = DBconnection.getInstance().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, loanId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDate("loanDate").toLocalDate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }

    public void sendOverdueEmailNotification(int loanId) {
 
        System.out.println("Sending email notification for loan ID: " + loanId);
    }

    public boolean checkIfISBNExists(String isbn) {
        String query = "SELECT COUNT(*) FROM Book WHERE bookID = ?"; 

        try (Connection conn = DBconnection.getInstance().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return false; 
    }

}
