## CS 313 : Advanced Programming Language

## Term Project

# Title: Online Library System

# Section: 5C

# Group no. 1

No. Student Name Student Number

```
1 Sarah radhy alotaibi 444008953
2 Reem Rashed Alhijris 444008811
3 Lama Khaled Asiri 444008943
4 Remma Shabib Almutlaq 444008929
5 Joumana Alohali 444008871
```
First Semester 2024


Content

```
Mark
Obtain
```
Project Description

Class Diagram

GUI Design

Database Tables

Connection and Queries on Database

Appendix (code)

TOTAL SCORE /


## Contents

- 1. Project Description
- 2. Class Diagram
- 3. GUI Design
- 4. Database Tables
- 5. Connection and Queries on Database
- 6. Appendix


## 1. Project Description

The Online Library System is an advanced desktop application built using JavaFX and Oracle
Database, designed to optimize the management of library operations and enhance user
experience. This system serves as a comprehensive solution to ease the processes of book
borrowing, loan tracking, overdue notifications, and user management within a library
environment. The application is designed to cater to three types of users: admins, librarians, and
students, each with specific roles and privileges to ensure efficient library management.

Upon opening the system, users are greeted with a **login page** where they can sign in using
their email and password. If a student does not already have an account, they can register for a
new one directly from this screen. However, only students are allowed to create new accounts;
librarians and administrators will already have their accounts made by an admin to insure
integrity.

**For Students:**

Once logged in, students are directed to a personalized **student dashboard** where they can
browse through the entire library collection. They have the option to filter books by title, sort
them in reverse order. Students can easily borrow books using the book’s unique ID and keep
track of their own requests. If they no longer need a book, they can also return it directly from
their dashboard.

**For Librarians:**

Librarians have access to a **library management interface**. Here, they can manage the book
inventory with functionalities that include:

1. **Adding new books** to the system.
2. **Deleting books** from the collection.
3. **Updating existing book information** for corrections or new editions.

Additionally, librarians have a **tracking feature** for overdue loans. This feature lists all overdue
loans and allows librarians to search for specific overdue records. Upon selecting a particular
overdue loan, they can view detailed information, such as the overdue period and associated
fees, and can send automated email reminders to students who have yet to return the book.

Librarians also have access to a **request management system** where they can see all loan
requests from students. They can search for these requests and then decide to **approve, reject,
or keep them pending** based on the student’s eligibility and the availability of books.


**For Administrators:**

Administrators are granted control over **user management** within the system. They can view a
list of all users, including students and librarians, and have the ability to:

1. **Add new users** (either students, librarians, or additional administrators).
2. **Delete users** who no longer need access.
3. **Update existing user information** as necessary.

Beyond managing users, administrators can also access the **library inventory** , where they can
view a complete list of books, including the number of available copies for each title.
Additionally, they have access to **system settings** :

1. The **maximum number of books** a student can borrow at one time.
2. The **overdue fees** applied for late returns.
3. The **loan duration** , specifying how long a student can keep a book before it is
    considered overdue.


## 2. Class Diagram

**1. User Class (Abstract Class)**
    - **Attributes** :

```
o name: The user's full name.
```
```
o email: The user's email address (used for login).
```
```
o password: The user's password.
```
```
o gender: The user's gender.
```
```
o phoneNo: The user's phone number.
```
```
o role: The role of the user (e.g., student, librarian, admin).
```
- **Methods** :

```
o Full constructor for creating User objects.
```
```
o Constructor has all the attributes except role
```
```
o toString(): Returns a string representation of the user.
```
```
o Getters and setters for accessing and modifying user attributes.
```
- **Relationships** :

```
o The User class serves as a base class for Student, Librarian, and Admin.
```

**2. Student Class (Inherits from User)**
    - **Attributes** :

```
o loanLinkedList: A linked list of Loan objects associated with the student.
```
- **Methods** :

```
o Full constructor for initializing a Student.
```
```
o Getters, setters, and toString() methods.
```
- **Relationships** :

```
o A Student can have multiple loans through the Loan class.
```
**3. Librarian Class (Inherits from User)**
    - **Attributes** :

```
o libraryBranch: The branch of the library where the librarian works.
```
- **Methods** :

```
o Full constructor for creating a Librarian.
```
```
o constructor has all the attributes except role.
```
```
o Getters, setters, and toString() methods.
```
- **Relationships** :

```
o Librarians are responsible for managing books in the BookStore and processing loans.
```
**4. Admin Class (Inherits from User)**
    - **Attributes** :

```
o assignedDepartment: The department assigned to the admin.
```
- **Methods** :

```
o Full constructor for initializing an Admin.
```
```
o constructor has all the attributes except role.
```
```
o Getters, setters, and toString() methods.
```
**5. BookStore Class**
    - **Attributes** :

```
o books: A list of all Book objects available in the bookstore.
```

```
o users: A LinkedHashSet of User objects registered in the system.
```
- **Methods** :

```
o listAll() methods for listing all books.
```
```
o constructor BookStore(LinkedHashSet<User> user)
```
```
o Getters, setters, and toString() methods.
```
- **Relationships** :

```
o The BookStore maintains collections of both Book and User objects.
```
```
o BookStore implements Listable<Book>.
```
**6. Book Class**
    - **Attributes** :

```
o title: The title of the book.
```
```
o id: The unique identifier for the book.
```
```
o author: The author of the book.
```
```
o genre: The genre of the book.
```
```
o isAvailable: Indicates if the book is available.
```
- **Methods** :

```
o Full constructor for initializing a Book.
```
```
o Book(title, author, id, isAvailable) constructor
```
```
o Book(title, author, genre) constructor
```
```
o Getters, setters, and toString() methods.
```
- **Relationships** :

```
o Books are managed by librarians and associated with Loan objects.
```
**7. Loan Class**
    - **Attributes** :

```
o id: The unique identifier for the loan.
```
```
o LOANDATE: The date when the book was borrowed.
```
```
o RETURNDATE: The actual return date of the borrowed book.
```

```
o maxReturnDate: The maximum allowable return date for the book.
```
```
o borrowedBooks: An ArrayList of Book objects that are borrowed in this loan.
```
```
o status: The status of the loan (e.g., pending, approved, rejected).
```
- **Methods** :

```
o Constructor loan(ID, boorwedBooks: arrayList <Book>,status).
```
```
o Constructor loan(ID, boorwedBooks: arrayList <Book>,LOANDATE, RETURNDATE,status).
```
```
o getDaysOverdue(): Calculates the number of overdue days.
```
```
o calculateFee(): Calculates the fee for late returns.
```
```
o getBookID(): returns book ID
```
```
o Getters, setters, and toString() methods.
```
- **Relationships** :

```
o Loans are associated with Students and managed by Librarians.
```
**8. Interfaces**
    - **Listable<T> Interface** :

```
o Method: listAll(List<T>): Lists all entities (like books or users).
```

## 3. GUI Design

1 - User can log in


2 - If the user forgets the password, they can create a new one

**3** - If the user is a student and does not have an account, he can create an

account


4 - After logging in, if the user is a student, they will have access to their

personalized homepage. Here, they can: borrow books, filter books, return

books


5 - After logging in, if the user is a librarian, they can efficiently manage the

```
book inventory by: Adding New Books, Updating Book Details, Removing
Books
```



6 - librarians can track overdue books, access detailed information about these

loans, and send email notifications to students who have not yet returned their

borrowed books.


7 - Librarians also have the ability to process book loan requests by updating

the status to approved, rejected, or pending


8 - After logging in, if the user is an admin, they can efficiently manage the

system by adding, updating, or removing users. They also have oversight of

the entire library inventory and access to system settings





## 4. Database Tables

-- Dropping tables if they exist

DROP TABLE UserTable CASCADE CONSTRAINT;

CREATE TABLE UserTable (

userName VARCHAR(30) NOT NULL,

email VARCHAR(50) PRIMARY KEY,

userPassword VARCHAR(100) NOT NULL,

gender VARCHAR(6) CHECK (gender IN ('male', 'female')) NOT NULL,

phoneNo CHAR(12),

userRole VARCHAR(20) CHECK (userRole IN ('Admin', 'Librarian', 'Student')) NOT NULL

);

DROP TABLE Administrator CASCADE CONSTRAINT;

CREATE TABLE Administrator (

adminEmail VARCHAR(50) PRIMARY KEY REFERENCES UserTable(email) ON
DELETE CASCADE,

assignedDepartment VARCHAR(100) NOT NULL

);


DROP TABLE Librarian CASCADE CONSTRAINT;

CREATE TABLE Librarian (

librarianEmail VARCHAR(50) PRIMARY KEY REFERENCES UserTable(email)ON
DELETE CASCADE,

libraryBranch VARCHAR(100) NOT NULL

);

DROP TABLE Student CASCADE CONSTRAINT;

CREATE TABLE Student (

studentEmail VARCHAR(50) PRIMARY KEY REFERENCES UserTable(email)ON
DELETE CASCADE

);

DROP TABLE Book CASCADE CONSTRAINT;

CREATE TABLE Book (

title VARCHAR(200) NOT NULL,

bookID VARCHAR(20) PRIMARY KEY,

author VARCHAR(100) NOT NULL,

genre VARCHAR(50) CHECK (genre IN ('fantasy', 'science
fiction','romance','mestrey','horror'))NOT NULL ,

isAvailable VARCHAR(10) CHECK (isAvailable IN ('available', 'not available')) NOT NULL

);


DROP TABLE Loan CASCADE CONSTRAINT;

CREATE TABLE Loan (

userEmail VARCHAR(50) REFERENCES Student(studentEmail)ON DELETE CASCADE,

bookId VARCHAR(20) REFERENCES Book(bookID),

loanID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY
KEY,

loanDate DATE NOT NULL,

maxReturnDate DATE, -- New column for the maximum return date

returnDate DATE, -- Actual return date

status VARCHAR(20) CHECK (status IN ('pending', 'approved', 'rejected'))

);

-- Insert Admin account

CREATE OR REPLACE TRIGGER InsertUserRoleTables

AFTER INSERT ON UserTable

FOR EACH ROW

BEGIN

-- For Student Role

IF :NEW.userRole = 'Student' THEN

INSERT INTO Student (studentEmail)

VALUES (:NEW.email);

-- For Admin Role

ELSIF :NEW.userRole = 'Admin' THEN

INSERT INTO Administrator (adminEmail, assignedDepartment)

VALUES (:NEW.email, 'Default Department'); -- You can set a default or leave it as NULL
if needed


-- For Librarian Role

ELSIF :NEW.userRole = 'Librarian' THEN

INSERT INTO Librarian (librarianEmail, libraryBranch)

VALUES (:NEW.email, 'Default Branch'); -- Set a default library branch or leave it NULL
if required

END IF;

END;

/

INSERT INTO UserTable (userName, email, userPassword, gender, phoneNo, userRole)

VALUES ('Lama', 'lama@admin.com', 'admin', 'female', '1234567890', 'Admin');

INSERT INTO UserTable (userName, email, userPassword, gender, phoneNo, userRole)

VALUES ('a', 'a@a.a', 'a', 'female', '1234567890', 'Librarian');

INSERT INTO Book (title, bookID, author, genre, isAvailable)

VALUES ('The Hobbit', 'B001', 'J.R.R. Tolkien', 'fantasy', 'available');

INSERT INTO Book (title, bookID, author, genre, isAvailable)

VALUES ('1984', 'B002', 'George Orwell', 'science fiction', 'available');

INSERT INTO Book (title, bookID, author, genre, isAvailable)

VALUES ('The Da Vinci Code', 'B004', 'Dan Brown', 'mestrey', 'available');

INSERT INTO Book (title, bookID, author, genre, isAvailable)

VALUES ('Dracula', 'B005', 'Bram Stoker', 'horror', 'available');

INSERT INTO Book (title, bookID, author, genre, isAvailable)

VALUES ('1984', 'B003', 'George dd', 'science fiction', 'available');

INSERT INTO UserTable (userName, email, userPassword, gender, phoneNo, userRole)


VALUES ('st', 'st@ex.com', '123', 'female', '1234567890', 'Student');

--INSERT INTO Loan (userEmail, bookId, loanDate, status)

--VALUES ('st@ex.com', 'B003', SYSDATE, 'pending');

-- Example Insert for testing an overdue loan

--INSERT INTO Loan (userEmail, bookId, loanDate, status, returnDate)

--VALUES ('st@ex.com', 'B003', SYSDATE - 10, 'approved', SYSDATE - 5);

-- Insert a test loan where returnDate is overdue by 2 days

INSERT INTO Loan (userEmail, bookId, loanDate, maxReturnDate, returnDate, status)

VALUES (

'st@ex.com', -- Student email

'B003', -- Book ID (assuming this book exists)

SYSDATE, -- Loan date (current date)

SYSDATE + 14, -- maxReturnDate = loanDate + 14 days

SYSDATE + 16, -- returnDate = loanDate + 16 days (overdue by 2 days)

'approved' -- Status (approved)

);

SELECT loanId, maxReturnDate, returnDate

FROM Loan

WHERE returnDate > maxReturnDate;

SELECT * FROM Loan WHERE returnDate > maxReturnDate;


## 5. Connection and Queries on Database

Connection;

package OnlineLibrarySystem.DB;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

public class DBconnection {

private static final String URL = "jdbc:oracle:thin:@localhost:1522:xe";

private static final String USER = "system";

private static final String PASSWORD = "1234";

private static DBconnection instance;

private Connection connection;

private DBconnection() {

try {

connection = DriverManager.getConnection(URL, USER, PASSWORD);

} catch (SQLException e) {

e.printStackTrace();

throw new RuntimeException("Failed to connect to the database.");

}

}


public static DBconnection getInstance() throws SQLException {

if (instance == null|| instance.connection.isClosed()) {

instance = new DBconnection();

}

return instance;

}

public Connection getConnection() {

return connection;

}

}


Queries:

/*

* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this
license

* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

*/

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

### /**

### *

* @author lamo9

*/

public class DBhandler {


// Method to validate user login

public static String currrntUser;

public static String loginUser(String email, String password) {

String query = "SELECT userPassword, userRole FROM UserTable WHERE email=?";

currrntUser = email;

try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement
pstmt = conn.prepareStatement(query)) {

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


### }

public static boolean createStudentAccount(User user) {

String query = "INSERT INTO UserTable (userName, email, userPassword, gender,
phoneNo, userRole) "

+ "VALUES (?, ?, ?, ?, ?, 'Student')";

Connection conn = null; // Declare the connection outside the try block

try {

conn = DBconnection.getInstance().getConnection(); // Initialize connection

conn.setAutoCommit(false); // Disable auto-commit to manually handle transactions

try (PreparedStatement pstmt = conn.prepareStatement(query)) {

pstmt.setString(1, user.getName());

pstmt.setString(2, user.getEmail());

pstmt.setString(3, user.getPassword());

pstmt.setString(4, user.getGender());

pstmt.setString(5, user.getPhone());

pstmt.executeUpdate(); // Execute the insert query

conn.commit(); // Commit the transaction

return true; // Return true indicating successful insertion

} catch (SQLException e) {

// Handle any SQLException that happens during the PreparedStatement execution

e.printStackTrace();

if (conn != null) {


try {

conn.rollback(); // Rollback if there’s an error

} catch (SQLException rollbackException) {

rollbackException.printStackTrace(); // Handle potential rollback failure

}

}

}

} catch (SQLException e) {

// Handle the SQLException for getting the connection

e.printStackTrace();

if (conn != null) {

try {

conn.rollback(); // Rollback if there’s an error

} catch (SQLException rollbackException) {

rollbackException.printStackTrace(); // Handle potential rollback failure

}

}

} finally {

if (conn != null) {

try {

conn.setAutoCommit(true); // Reset auto-commit mode back to default

conn.close(); // Close the connection to free resources

} catch (SQLException closeException) {

closeException.printStackTrace(); // Handle connection closing failure

}

}


### }

return false; // Return false if the operation fails

}

//String title, String id, String author, String genre, boolean isAvailable

public boolean borrowBook(String bookId, String studentEmail) {

System.out.println(studentEmail);

System.out.println(bookId);

String query = "INSERT INTO Loan (userEmail, bookId, loanDate, returnDate, status)
VALUES (?, ?, SYSDATE, SYSDATE + 14, 'approved')";

System.out.println("1");

try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement
pstmt = conn.prepareStatement(query)) {

System.out.println("2");

pstmt.setString(1, studentEmail);

pstmt.setString(2, bookId);

System.out.println("3");

System.out.println("4");

int rowsAffected = pstmt.executeUpdate();

System.out.println("5");

return rowsAffected > 0; // Return true if the book is successfully borrowed

} catch (SQLException e) {

e.printStackTrace();

}

return false;

}


public List<Book> getAllBooks() {

List<Book> books = new ArrayList<>();

String query = "SELECT * FROM Book WHERE isAvailable = 'available'"; // Example
query, adjust to your schema

try (Connection conn = DBconnection.getInstance().getConnection(); Statement stmt =
conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

while (rs.next()) {

String id = rs.getString("bookID");

String title = rs.getString("title");

String author = rs.getString("author");

String isAvailable = rs.getString("isAvailable");

books.add(new Book(title, author, id,isAvailable)); // Assuming Book constructor:
(title, author, id)

}// public Book(String title, String author, int id)

} catch (SQLException e) {

e.printStackTrace();

}

return books;

}

public List<Book> getUniqueTitleBooks() {

List<Book> books = new ArrayList<>();

String query = "SELECT title, author, MIN(bookID) AS bookID FROM Book GROUP BY
title, author"; // Group by title and author

try (Connection conn = DBconnection.getInstance().getConnection(); Statement stmt =
conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {


while (rs.next()) {

String id = rs.getString("bookID");

String title = rs.getString("title");

String author = rs.getString("author");

books.add(new Book(title, author, id)); // Assuming Book constructor: (title, author,
id)

}

} catch (SQLException e) {

e.printStackTrace();

}

return books;

}

public static Map<String, Integer> countNumberOfCopies() {

String query = "SELECT title, COUNT(*) AS count FROM Book GROUP BY title";

Map<String, Integer> titleCopiesMap = new HashMap<>();

try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement
pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

// Loop through each result and add to the map

while (rs.next()) {

String title = rs.getString("title");

int count = rs.getInt("count");

titleCopiesMap.put(title, count); // Add title and count to the map

}


} catch (SQLException e) {

e.printStackTrace();

}

return titleCopiesMap; // Return the map containing title and count

}

// public static int countNumberOfCopies(){

// //String query = "SELECT title, COUNT(*) FROM Book WHERE title = '' GROUP BY
title";

// String query = "SELECT title, COUNT(*) FROM Book GROUP BY title";

// return 0;

// }

public static List<User> getAllUsers() {

List<User> users = new ArrayList<>();

String query = "SELECT * FROM UserTable"; // Query to fetch users from the database

try (Connection conn = DBconnection.getInstance().getConnection(); Statement stmt =
conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

while (rs.next()) {

String userName = rs.getString("userName");

String email = rs.getString("email");

String userPassword = rs.getString("userPassword");

String userRole = rs.getString("userRole");

String phoneNo = rs.getString("phoneNo");

String gender = rs.getString("gender");


// Create a User object based on the role

User user = createUserByRole(userName, email, userPassword, userRole, phoneNo,
gender);

if (user != null) {

users.add(user);

}

}

} catch (SQLException e) {

e.printStackTrace();

}

return users;

}

// Helper method to create user by role (based on email, not id)

private static User createUserByRole(String name, String email, String password, String role,
String phone, String gender) {

switch (role.toLowerCase()) {

case "student":

return new Student(name, email, password, gender, phone, "Student");

case "admin":

return new Admin(name, email, password, gender, phone, "Admin");

case "librarian":

return new Librarian(name, email, password, gender, phone, "Librarian");

default:

return null; // Role not recognized

}

}


public static boolean addUser(User user) {

System.out.println("start");

String checkEmailQuery = "SELECT COUNT(*) FROM UserTable WHERE email = ?";

String insertQuery = "INSERT INTO UserTable (userName, email, userPassword, gender,
phoneNo, userRole) VALUES (?, ?, ?, ?, ?, ?)";

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

// Insert new user if email doesn't exist

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

conn.rollback(); // Rollback if there’s an error

}

}


} catch (SQLException e) {

e.printStackTrace();

} finally {

if (conn != null) {

try {

conn.setAutoCommit(true); // Reset auto-commit mode

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

String updateQuery = "UPDATE UserTable SET userName = ?, userPassword = ?, gender
= ?, phoneNo = ?, userRole =? WHERE email = ?";

try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement
pstmt = conn.prepareStatement(updateQuery)) {

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

// Remove a user from the database (using email as the identifier)

public static boolean removeUser(String email) {

String query = "DELETE FROM UserTable WHERE email = ?";

try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement
pstmt = conn.prepareStatement(query)) {


pstmt.setString(1, email); // Use email for deletion

int rowsAffected = pstmt.executeUpdate();

return rowsAffected > 0; // Return true if the user was removed

} catch (SQLException e) {

e.printStackTrace();

}

return false;

}

// Search for users by email

public List<User> searchUsersByEmail(String email) {

List<User> users = new ArrayList<>();

String query = "SELECT * FROM users WHERE email LIKE ?";

try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement
pstmt = conn.prepareStatement(query)) {

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

String query = "INSERT INTO Book (title, bookID, author, genre, isAvailable) VALUES
(?, ?, ?, ?, 'available')";

try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement
pstmt = conn.prepareStatement(query)) {

pstmt.setString(1, title);

pstmt.setString(2, isbn); // Assuming ISBN is used as bookID

pstmt.setString(3, author);

pstmt.setString(4, genre);

int rowsAffected = pstmt.executeUpdate();

return rowsAffected > 0; // Returns true if insertion was successful

} catch (SQLException e) {

e.printStackTrace();

}

return false;

}


public boolean deleteBookByTitle(String title,String author) {

String query = "DELETE FROM Book WHERE title =? AND author=?";

try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement
pstmt = conn.prepareStatement(query)) {

pstmt.setString(1, title);

pstmt.setString(2, author);

int rowsAffected = pstmt.executeUpdate();

return rowsAffected > 0; // Return true if a book was deleted

} catch (SQLException e) {

e.printStackTrace();

}

return false;

}

//UPDATE Book SET title ='Alice', author = 'Lm', genre = 'horror' WHERE title = '1984' And
author ='George Orwell';

public boolean editBook(String oldTitle, String oldAuthor, Book newBook) {

String query = "UPDATE Book SET title = ?, author = ?, genre =? WHERE title =? AND
author = ?";

try (Connection conn = DBconnection.getInstance().getConnection();

PreparedStatement pstmt = conn.prepareStatement(query)) {

pstmt.setString(1, newBook.getTitle()); // Set new title

pstmt.setString(2, newBook.getAuthor()); // Set new author

pstmt.setString(3, newBook.getGenre()); // Set new genre


pstmt.setString(4, oldTitle); // Set old title for WHERE clause

pstmt.setString(5, oldAuthor); // Set old author for WHERE clause

int rowsAffected = pstmt.executeUpdate();

return rowsAffected > 0;

} catch (SQLException e) {

e.printStackTrace();

}

return false;

}

public List<Loan> getAllLoans() {

List<Loan> loans = new ArrayList<>();

String query = "SELECT * FROM Loan INNER JOIN Book ON Loan.bookId =
Book.bookId";

try (Connection conn = DBconnection.getInstance().getConnection(); Statement stmt =
conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

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

String query = "UPDATE Loan SET status =? WHERE loanId = ?";

try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement
pstmt = conn.prepareStatement(query)) {

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

// Step 1: Update the Loan record to mark it as returned

String updateLoanQuery = "UPDATE Loan SET returnDate = ?, status = 'approved'
WHERE bookId =? AND userEmail = ?";

try (PreparedStatement pstmtLoan = conn.prepareStatement(updateLoanQuery)) {

pstmtLoan.setDate(1, Date.valueOf(LocalDate.now()));

pstmtLoan.setString(2, bookId);

pstmtLoan.setString(3, studentEmail);

int loanRowsUpdated = pstmtLoan.executeUpdate();

if (loanRowsUpdated == 0) {

return false; // No rows updated, return failed

}

// Step 2: Update the Book record to mark it as available

String updateBookQuery = "UPDATE Book SET isAvailable = 'available' WHERE
bookID = ?";

try (PreparedStatement pstmtBook = conn.prepareStatement(updateBookQuery)) {

System.out.println("1");

pstmtBook.setString(1, bookId);

System.out.println("2");

int bookRowsUpdated = pstmtBook.executeUpdate();

System.out.println("3");

return bookRowsUpdated > 0; // Return true if both updates were successful

}


### }

} catch (SQLException e) {

e.printStackTrace();

return false;

}

}

public ResultSet getOverdueLoans() throws SQLException {

String query = "SELECT loanId, maxReturnDate, returnDate FROM Loan WHERE
returnDate > maxReturnDate";

Connection conn = DBconnection.getInstance().getConnection();

PreparedStatement stmt = conn.prepareStatement(query);

return stmt.executeQuery(); // This query fetches all overdue loans

}

public ResultSet getOverdueLoansById(String loanId) throws SQLException {

// Query to fetch overdue loan details by loan ID

String query = "SELECT loanID, maxReturnDate, returnDate FROM Loan WHERE loanID
= ?";

Connection conn = DBconnection.getInstance().getConnection();

PreparedStatement stmt = conn.prepareStatement(query);

stmt.setString(1, loanId); // Set the loan ID parameter

return stmt.executeQuery();

}

// Method to fetch borrow date by loanId (loanDate is the borrow date in the table)

public LocalDate getBorrowDateByLoanId(String loanId) {


String query = "SELECT loanDate FROM Loan WHERE loanId = ?";

try (Connection conn = DBconnection.getInstance().getConnection(); PreparedStatement
pstmt = conn.prepareStatement(query)) {

pstmt.setString(1, loanId);

ResultSet rs = pstmt.executeQuery();

if (rs.next()) {

return rs.getDate("loanDate").toLocalDate();

}

} catch (SQLException e) {

e.printStackTrace();

}

return null; // Return null if no result found

}

public void sendOverdueEmailNotification(int loanId) {

// Implement email notification logic here if needed

System.out.println("Sending email notification for loan ID: " + loanId);

}

public boolean checkIfISBNExists(String isbn) {

String query = "SELECT COUNT(*) FROM Book WHERE bookID = ?"; // Adjust the query
based on your table structure

try (Connection conn = DBconnection.getInstance().getConnection();

PreparedStatement stmt = conn.prepareStatement(query)) {

stmt.setString(1, isbn);

ResultSet rs = stmt.executeQuery();


if (rs.next()) {

int count = rs.getInt(1);

return count > 0; // If count is greater than 0, the ISBN exists

}

} catch (SQLException e) {

e.printStackTrace(); // Handle exception

}

return false; // Return false if no matching ISBN is found

}

### }


## 6. Appendix

```
Main:
package OnlineLibrarySystem;
```
```
import OnlineLibrarySystem.controllers.AdminDashboardController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
```
```
public class OnlineLibrarySystem extends Application {
```
```
static Stage primaryStage;
```
```
@Override
public void start(Stage stage) throws Exception {
primaryStage = stage;
//showLogin();
loadMain();
//loadView("/OnlineLibrarySystem/views/admin.fxml", "Create Account");
}
```
```
public static void showCreate() throws Exception {
```
```
loadView("/OnlineLibrarySystem/views/CreateAccount.fxml", "Create Account");
}
```
```
public static void showForgot() throws Exception {
```
```
loadView("/OnlineLibrarySystem/views/ForgotPass.fxml", "Reset Password");
}
```
```
public static void showLogin() throws Exception {
```
```
loadView("/OnlineLibrarySystem/views/loginView.fxml", "Login");
}
```

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
Logger.getLogger(OnlineLibrarySystem.class.getName()).log(Level.SEVERE, null,
ex);
}
}

public static void main(String[] args) {
launch(args);
}

}


Classes:

package OnlineLibrarySystem.models;

public abstract class User {

private String name, email, password, gender, phone,role;

public User(String name, String email, String password, String gender, String phone, String
role) {

this.name = name;

this.email = email;

this.password = password;

this.gender = gender;

this.phone = phone;

this.role = role;

}

public User(String name, String email, String password, String gender, String phone) {

this.name = name;

this.email = email;

this.password = password;

this.gender = gender;


this.phone = phone;

}

public void setName(String name) {

this.name = name;

}

public void setEmail(String email) {

this.email = email;

}

public void setPassword(String password) {

this.password = password;

}

public void setGender(String gender) {

this.gender = gender;

}

public String getName() {

return name;

}

public String getEmail() {

return email;


### }

public String getPassword() {

return password;

}

public String getGender() {

return gender;

}

public String getPhone() {

return phone;

}

public void setPhone(String phone) {

this.phone = phone;

}

public String getRole() {

return role;

}

public void setRole(String role) {

this.role = role;

}


@Override

public String toString() {

return "User{" + "name=" + name + ", email=" + email + ", password=" + password + ",
gender=" + gender + ", phone=" + phone + ", role=" + role + '}';

}

### }


package OnlineLibrarySystem.models;

import java.util.LinkedList;

public class Student extends User{

private LinkedList<Loan> loanLinkedList = new LinkedList<>();

public Student(String name, String email, String password, String gender, String
phoneNo,String role) {

super(name, email, password, gender, phoneNo,"Student");

}

public void setLoanLinkedList(LinkedList<Loan> loanLinkedList) {

this.loanLinkedList = loanLinkedList;

}

public LinkedList<Loan> getLoanLinkedList() {

return loanLinkedList;

}

@Override

public String toString() {

return super.toString()+"loans: "+getLoanLinkedList();

}

### }


package OnlineLibrarySystem.models;

public class Librarian extends User {

private String LibraryBranch;

public Librarian( String name, String email, String password, String gender, String
phoneNo,String LibraryBranch) {

super(name, email, password, gender, phoneNo,"Librarian");

this.LibraryBranch = LibraryBranch;

}

public Librarian( String name, String email, String password, String gender, String phoneNo)
{

super(name, email, password, gender, phoneNo,"Librarian");

### }

public void setLibraryBranch(String LibraryBranch) {

this.LibraryBranch = LibraryBranch;

}

public String getLibraryBranch() {

return LibraryBranch;

}


@Override

public String toString() {

return super.toString() + ", Library Branch" + getLibraryBranch();

### }

### }


package OnlineLibrarySystem.models;

public class Admin extends User {

private String assignedDepartment;

public Admin( String name, String email, String password, String gender, String
phoneNo,String role,String assignedDepartment) {

super(name, email, password, gender, phoneNo,"Admin");

this.assignedDepartment = assignedDepartment;

}

public Admin(String name, String email, String password, String gender, String phone, String
role) {

super(name, email, password, gender, phone, "Admin");

}

public void setAssignedDepartment(String assignedDepartment) {

this.assignedDepartment = assignedDepartment;

}

public String getAssignedDepartment() {

return assignedDepartment;

}

@Override

public String toString() {


return super.toString()+ ", Assigned Department"+getAssignedDepartment();

}

// @Override

// public User search(){

// return User;

// }

### }


package OnlineLibrarySystem.models;

import javafx.beans.property.SimpleStringProperty;

import javafx.beans.property.ObjectProperty;

import javafx.beans.property.SimpleObjectProperty;

import javafx.scene.image.Image;

public class Book {

private String title;

private String id;

private String author;

private String genre;

private String isAvailable;

// Constructor with SimpleStringProperty for all fields

public Book(String title, String id, String author, String genre, String isAvailable) {

this.title = title;

this.id = id;

this.author = author;

this.genre = genre;

this.isAvailable = isAvailable;


### }

public Book(String title, String author, String id,String isAvailable) {

this.title = title;

this.id = id;

this.author = author;

this.isAvailable = "available";

}

public Book(String title, String author, String genre) {

this.title = title;

this.author = author;

this.genre = genre;

this.isAvailable = "available"; // Default availability

### }

public String getTitle() {

return title;

}

public void setTitle(String title) {

this.title=title;

}


public String getAuthor() {

return author;

}

public void setAuthor(String author) {

this.author=author;

}

public String getGenre() {

return genre;

}

public void setGenre(String genre) {

this.genre=genre;

}

public String getId() {

return id;

}

public void setId(String id) {


this.id=id;

}

public String isAvailable() {

return isAvailable;

}

public void setAvailable(String isAvailable) {

this.isAvailable = isAvailable;

}

@Override

public String toString() {

return title + " - " + author + " (ID: " + id + ")";

}

}


package OnlineLibrarySystem.models;

import java.util.ArrayList;

import java.util.LinkedHashSet;

import java.util.List;

import javafx.scene.image.Image;

public class BookStore implements Listable<Book>{

private static List<Book> books = new ArrayList<>();

private LinkedHashSet<User> user;

// private static int nextId = 1;

public BookStore(LinkedHashSet<User> user) {

this.user = user;

}

public LinkedHashSet<User> getUser() {

return user;

}

public void setUser(LinkedHashSet<User> user) {

this.user = user;

}

static {


Image bookImage = new Image("file:C:\\Users\\saraa\\OneDrive\\Desktop\\book2.jpg");
//image wont show

### }

public static List<Book> getBooks() {

return books;

}

@Override

public String toString() {

return "BookStore{" + "user=" + user + '}';

}

@Override

public List<Book> listAll() {

return new ArrayList<>(books); // Returning all books

}

### }


package OnlineLibrarySystem.models;

import java.util.List;

public interface Listable<T> {

public List<T> listAll();

}


package OnlineLibrarySystem.models;

import OnlineLibrarySystem.models.Book;

import java.time.Duration;

import java.time.ZoneId;

import java.util.ArrayList;

import java.util.Date;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;

import java.time.temporal.Temporal;

public class Loan {

private int ID;

private LocalDate LOANDATE;

private LocalDate RETURNDATE ;

private LocalDate maxReturnDate;

private ArrayList<Book> borrowedBooks;

private String Status;

public Loan(int ID, ArrayList<Book> borrowedBooks, String Status) {

this.ID = ID;

this.borrowedBooks = borrowedBooks;

this.Status = Status;

}


//Loan loan = new Loan(id, loanDate, returnDate, null, status);

public Loan(int ID, ArrayList<Book> borrowedBooks, LocalDate LOANDATE, LocalDate
RETURNDATE, String Status) {

this.ID = ID;

this.LOANDATE = LOANDATE;

this.RETURNDATE = RETURNDATE;

this.borrowedBooks = borrowedBooks;

this.Status = Status;

this.maxReturnDate = LOANDATE.plusDays(14);

}

public long getDaysOverdue() {

if (RETURNDATE != null && RETURNDATE.isAfter(maxReturnDate)) {

return ChronoUnit.DAYS.between(maxReturnDate, (Temporal) RETURNDATE); //
Calculate days overdue

}

return 0; // If the book is returned on or before maxReturnDate, no overdue

}

public LocalDate getMaxReturnDate() {

return maxReturnDate;

}

public int getID() {

return ID;


### }

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

/* @Override

public String toString() {

return "ID:" + ID + ", date:" + date + ", borrowedBooks:" + borrowedBooks;

}*/

@Override

public String toString() {

return "Loan ID: " + ID + ", Status: " + Status + ", Books: " + borrowedBooks.toString();

}

public String getBookID() {

if (borrowedBooks != null && !borrowedBooks.isEmpty()) {

return borrowedBooks.get(0).getId(); // Assuming the loan contains a list of books

}

return null; // Return null if no books are borrowed


### }

// Calculate the fee based on days overdue

public double calculateFee() {

return getDaysOverdue() * 0.25;

}

### }


Controllers :

package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;

import static OnlineLibrarySystem.OnlineLibrarySystem.loadManageBookInventory;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Label;

import javafx.scene.control.MenuButton;

import javafx.scene.control.MenuItem;

import javafx.scene.control.TextField;

import javafx.scene.text.Text;

public class AddNewBookController {

ObservableList<String> genrea=FXCollections.observableArrayList("fantasy", "science
fiction", "romance", "mystery", "horror");

@FXML

private Label bookTitle, author, genre, ISBN, bookCover;


### @FXML

private Text Title, txt;

### @FXML

private TextField bookTitleTxt, authorTxt, ISBNTxt;

### @FXML

private MenuButton menu;

### @FXML

private MenuItem fantasy, scienceFiction, romance, mystery, horror;

### @FXML

private Button addButton,backButton;

@FXML

private ChoiceBox genreBox;

@FXML

public void initialize(){

genreBox.setItems(genrea);}

@FXML

public void handleAddButton(ActionEvent event) {

String title = bookTitleTxt.getText().trim(); // New title

String author = authorTxt.getText().trim(); // New author

String genre = (String) genreBox.getValue(); // Get the selected genre from the ChoiceBox

String isbn = ISBNTxt.getText().trim(); // ISBN


// Validation: Check if any required fields are empty

if (title.isEmpty() || author.isEmpty() || genre == null || isbn.isEmpty()) {

// Show an alert for missing fields

javafx.scene.control.Alert alert = new
javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);

alert.setTitle("Validation Error");

alert.setHeaderText(null);

alert.setContentText("Please fill in all fields before adding the book.");

alert.showAndWait();

return;

}

// Validate if ISBN already exists in the database

DBhandler dbHandler = new DBhandler();

boolean isbnExists = dbHandler.checkIfISBNExists(isbn); // Assuming a method to check for
existing ISBNs

if (isbnExists) {

// Show an alert if the ISBN already exists

javafx.scene.control.Alert alert = new
javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);

alert.setTitle("ISBN Error");

alert.setHeaderText(null);

alert.setContentText("This ISBN is already associated with an existing book. Please enter a
different ISBN.");

alert.showAndWait();

return;


### }

// Proceed to add the book if all fields are valid and ISBN is unique

boolean success = dbHandler.addBook(title, author, genre, isbn);

// Show success or failure message

if (success) {

txt.setText("Book Added Successfully!");

// Optionally refresh the book list here if you're displaying it in the UI

} else {

txt.setText("Failed to Add Book.");

}

}

### @FXML

public void handleBackButton(ActionEvent event) {

loadManageBookInventory();

}

### }


package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;

import OnlineLibrarySystem.models.Admin;

import OnlineLibrarySystem.models.Librarian;

import OnlineLibrarySystem.models.Student;

import OnlineLibrarySystem.models.User;

import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.stage.Stage;

//import models.User;

public class AddUserController {

@FXML private TextField emailField;

@FXML private TextField nameField;

@FXML private PasswordField passwordField;

@FXML private TextField phoneField;

@FXML private RadioButton maleRadio;

@FXML private RadioButton femaleRadio;

@FXML private RadioButton adminRadio;

@FXML private RadioButton librarianRadio;

@FXML private RadioButton studentRadio;


private AdminDashboardController adminController;

private boolean isUpdateMode = false; // Flag to indicate if updating

private int selectedIndex = -1; // Index of the user being updated

@FXML

private ToggleGroup roleGroup;

@FXML

private ToggleGroup genderGroup;

// Set the controller that will receive the added user

public void setAdminController(AdminDashboardController adminController) {

this.adminController = adminController;

}

// Method to set fields for update mode

public void initializeForUpdate(User selectedUser, int index) {

isUpdateMode = true;

selectedIndex = index;

// Pre-populate fields with existing user details

emailField.setText(selectedUser.getEmail());

nameField.setText(selectedUser.getName());

passwordField.setText(selectedUser.getPassword());

phoneField.setText(selectedUser.getPhone());

// Set radio buttons based on the user's gender

if ("Male".equals(selectedUser.getGender())) {


maleRadio.setSelected(true);

} else {

femaleRadio.setSelected(true);

}

// Set radio buttons based on the user's role

if ("Admin".equals(selectedUser.getRole())) {

adminRadio.setSelected(true);

} else if ("Librarian".equals(selectedUser.getRole())) {

librarianRadio.setSelected(true);

} else if ("Student".equals(selectedUser.getRole())) {

studentRadio.setSelected(true);

}

}

// Handle the "Save" button click

@FXML

public void saveUser() {

// Validate input fields (do not proceed if any field is empty)

if (emailField.getText().isEmpty() || nameField.getText().isEmpty() ||

passwordField.getText().isEmpty() || phoneField.getText().isEmpty() ||

(!maleRadio.isSelected() && !femaleRadio.isSelected()) ||

(!adminRadio.isSelected() && !librarianRadio.isSelected() && !studentRadio.isSelected()))
{

showError("Validation Error", "All fields must be filled.");

return;


### }

// Additional data validation

if (!isValidEmail(emailField.getText())) {

showError("Validation Error", "Please enter a valid email address.");

return;

}

if (!isValidPhone(phoneField.getText())) {

showError("Validation Error", "Please enter a valid phone number (10-15 digits).");

return;

}

if (!isValidPassword(passwordField.getText())) {

showError("Validation Error", "Password must be at least 8 characters long, contain one
uppercase letter, one lowercase letter, and one digit.");

return;

}

// Gather data from the input fields

String email = emailField.getText();

String name = nameField.getText();

String password = passwordField.getText();

String phone = phoneField.getText();

String gender = maleRadio.isSelected()? "male" : "female";

String role = getSelectedRole();


// Create a new User object

User newUser = null;

boolean isCreated = false;

if (studentRadio.isSelected()) {

newUser = new Student(name, email, password, gender, phone, "Student");

isCreated = DBhandler.addUser(newUser);

} else if (adminRadio.isSelected()) {

newUser = new Admin(name, email, password, gender, phone, "Admin");

isCreated = DBhandler.addUser(newUser);

} else if (librarianRadio.isSelected()) {

newUser = new Librarian(name, email, password, gender, phone, "Librarian");

isCreated = DBhandler.addUser(newUser);

}

if (!isCreated) {

showError("Database Error", "Failed to save the user to the database.");

return;

}

// Check if it's in update mode or add mode

if (isUpdateMode) {

// Update the existing user

adminController.updateUserInListView(selectedIndex, newUser);

} else {


// Add a new user

adminController.addUserToList(newUser);

}

// Close the window after saving

closeWindow();

}

// Utility methods for validation

private boolean isValidEmail(String email) {

return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

}

private boolean isValidPhone(String phone) {

return phone.matches("^\\d{10,15}$");

}

private boolean isValidPassword(String password) {

return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");

}

// Show error alert

private void showError(String title, String message) {

Alert alert = new Alert(Alert.AlertType.ERROR);

alert.setTitle(title);

alert.setHeaderText(null);


alert.setContentText(message);

alert.showAndWait();

}

// Get selected role from radio buttons

private String getSelectedRole() {

if (adminRadio.isSelected()) return "Admin";

if (librarianRadio.isSelected()) return "Librarian";

if (studentRadio.isSelected()) return "Student";

return null; // Shouldn't reach here if validation works properly

}

// Close the current window

private void closeWindow() {

((Stage) emailField.getScene().getWindow()).close();

}

### }


package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;

import OnlineLibrarySystem.models.User;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.stage.Stage;

import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import java.util.stream.Collectors;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.scene.Parent;

import javafx.scene.input.KeyEvent;

import javafx.stage.Modality;

public class AdminDashboardController {

@FXML

private ListView<User> userListView;

private List<User> users = new ArrayList<>();

private int selectedIndex = -1;

@FXML


private TextField searchField;

@FXML

private Button b1, b2, b3, b4, b5, b6;

private ObservableList<User> filteredUsers = FXCollections.observableArrayList();

// Initialize method to populate the ListView with data

@FXML

public void initialize() {

users = DBhandler.getAllUsers(); // Fetch all users from the database

if (users != null) {

filteredUsers.addAll(users); // Add all users to the filtered list

}

userListView.setItems(filteredUsers);

// Add key event listener to the search field to filter as the user types

searchField.setOnKeyReleased(event -> filterUsers());

// Initially hide the ListView

userListView.setVisible(false);

// Set the visibility of the ListView when a user is selected

userListView.setOnMouseClicked(event -> {

User selected = userListView.getSelectionModel().getSelectedItem();

if (selected != null) {

searchField.setText(selected.toString());


userListView.setVisible(true);

}

});

}

// Method to filter users based on search query

private void filterUsers() {

String searchText = searchField.getText().toLowerCase();

// Validate search input

if (searchText.isEmpty()) {

showError("Search Error", "Search field cannot be empty.");

userListView.setVisible(false);

return;

}

// Filter users by email

List<User> filteredList = users.stream()

.filter(user -> user.getEmail().toLowerCase().contains(searchText))

.collect(Collectors.toList());

filteredUsers.setAll(filteredList);

if (filteredList.isEmpty()) {

userListView.setVisible(false);

} else {


userListView.setVisible(true);

}

}

### @FXML

public void handleSearchButton() {

filterUsers();

}

### @FXML

public void handleSearch(KeyEvent event) {

filterUsers();

}

// Add new user

@FXML

public void addUser() {

try {

FXMLLoader loader = new
FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/AddUser.fxml"));

Parent root = loader.load();

AddUserController addUserController = loader.getController();

addUserController.setAdminController(this);

Stage stage = new Stage();

stage.setScene(new Scene(root));


stage.setTitle("Add User");

stage.show();

} catch (IOException e) {

e.printStackTrace();

showError("Error", "Failed to load Add User screen.");

}

}

// Update existing user

@FXML

public void updateUser() {

User selectedUser = userListView.getSelectionModel().getSelectedItem();

selectedIndex = userListView.getSelectionModel().getSelectedIndex();

if (selectedUser != null && selectedIndex >= 0) {

try {

FXMLLoader loader = new
FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/UpdateUser.fxml"));

Parent root = loader.load();

updateUserController updateUserController = loader.getController();

updateUserController.setAdminController(this);

updateUserController.initializeForUpdate(selectedUser, selectedIndex);

Stage stage = new Stage();

stage.setScene(new Scene(root));


stage.setTitle("Update User");

stage.show();

} catch (IOException e) {

e.printStackTrace();

showError("Error", "Failed to load Update User screen.");

}

} else {

showError("No Selection", "Please select a user to update.");

}

}

// Remove selected user

@FXML

public void removeUser() {

User selectedUser = userListView.getSelectionModel().getSelectedItem();

if (selectedUser != null) {

// Show confirmation dialog

Alert alert = new Alert(AlertType.CONFIRMATION);

alert.setTitle("Confirmation");

alert.setHeaderText("Are you sure you want to remove this user?");

alert.setContentText("User: " + selectedUser.getName());

alert.showAndWait().ifPresent(response -> {

if (response == ButtonType.OK) {

boolean success = DBhandler.removeUser(selectedUser.getEmail());


if (success) {

users.remove(selectedUser);

filteredUsers.remove(selectedUser);

userListView.getItems().remove(selectedUser);

showSuccess("User Removed", "The user has been successfully removed.");

} else {

showError("Error", "Failed to remove the user from the database.");

}

}

});

} else {

showError("No Selection", "Please select a user to remove.");

}

}

// Add new user to the list (called from the AddUserController)

public void addUserToList(User newUser) {

if (newUser != null) {

users.add(newUser);

filteredUsers.add(newUser);

userListView.refresh();

} else {

showError("Error", "User cannot be null.");

}

}


// Update user in the list (called from the UpdateUserController)

public void updateUserInListView(int index, User updatedUser) {

if (index >= 0 && updatedUser != null) {

users.set(index, updatedUser);

filteredUsers.set(index, updatedUser);

userListView.refresh();

} else {

showError("Error", "Invalid user data or index.");

}

}

// Show success message

private void showSuccess(String title, String message) {

Alert alert = new Alert(AlertType.INFORMATION);

alert.setTitle(title);

alert.setHeaderText(null);

alert.setContentText(message);

alert.showAndWait();

}

// Show error message

private void showError(String title, String message) {

Alert alert = new Alert(AlertType.ERROR);

alert.setTitle(title);

alert.setHeaderText(null);


alert.setContentText(message);

alert.showAndWait();

}

// Open Library Inventory screen

@FXML

public void openLibraryInventory() {

try {

FXMLLoader loader = new
FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/LibraryInventory.fxml"));

Parent root = loader.load();

Stage stage = new Stage();

stage.setScene(new Scene(root));

stage.setTitle("Library Inventory");

stage.show();

} catch (IOException e) {

e.printStackTrace();

showError("Error", "Failed to load Library Inventory screen.");

}

}

// Open System Settings screen

@FXML

public void openSystemSettings() {

try {


FXMLLoader loader = new
FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/SystemSettings.fxml"));

Parent root = loader.load();

Stage stage = new Stage();

stage.setTitle("System Settings");

stage.initModality(Modality.APPLICATION_MODAL);

stage.setScene(new Scene(root));

stage.showAndWait();

} catch (Exception e) {

e.printStackTrace();

showError("Error", "Failed to load System Settings screen.");

}

}

}


package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.models.Book;

import OnlineLibrarySystem.DB.DBhandler; // Import your DBHandler class

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.scene.control.ListView;

import javafx.scene.control.MenuBar;

import javafx.scene.control.TextField;


import javafx.scene.control.TitledPane;

import OnlineLibrarySystem.OnlineLibrarySystem;

import OnlineLibrarySystem.DB.DBhandler;

import java.util.Collections;

import java.util.Comparator;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.scene.control.Label;

import javafx.scene.control.MenuItem;

import java.util.logging.Level;

import java.util.logging.Logger;

public class BookView implements Initializable {

### @FXML

private Button Myorder;


### @FXML

private Button viewbook;

### @FXML

private Button borrw;

### @FXML

private MenuBar menub;

### @FXML

public ListView<Book> bookListView;

### @FXML

private TextField bookIdField;

### @FXML

private TitledPane tb;

### @FXML


private MenuItem byorder;

### @FXML

private MenuItem reversorder;

### @FXML

private Label massege;

public static ObservableList<Book> bookList = FXCollections.observableArrayList();

public static ObservableList<Book> borrowedBooks = FXCollections.observableArrayList();

private Book selectedBook;

public static BookView instance;

public static BookView getInstance() {

if (instance == null) {

System.out.println("BookView instance is not initialized yet!");

### }


return instance;

### }

### @FXML

private void handleByOrder(ActionEvent event) {

Collections.sort(bookList, Comparator.comparing(Book::getTitle)); // Sort by title

updateBookListView(); // Refresh the ListView to display sorted order

System.out.println("Books sorted by title.");

### }

### @FXML

public void handleReverseOrder(ActionEvent event) {

// Sort the bookList by title in descending order

FXCollections.sort(bookList, (book1, book2) ->
book2.getTitle().compareToIgnoreCase(book1.getTitle()));

// Update the ListView to reflect the sorted list


updateBookListView();

System.out.println("Books have been sorted by title in reverse order.");

### }

### @FXML

public void handleMyorderButton(ActionEvent event) throws IOException, Exception {

OnlineLibrarySystem.ShowOrder();

### }

public void updateBookListView() {

bookListView.setItems(bookList);

### }

### @FXML

public void handleborrwButton(ActionEvent event) throws IOException, Exception {


String bookIdToBorrow = bookIdField.getText().trim();

if (bookIdToBorrow.isEmpty()) {

massege.setText("Enter ID");

return;

### }

selectedBook = findBookById(bookIdToBorrow);

if (selectedBook == null) {

massege.setText(bookIdToBorrow + " is invalid ID");

return;

### }

// Get the current student's email (replace with actual logic)

String studentEmail = getCurrentStudentEmail();

DBhandler dbHandler = new DBhandler();


boolean success = dbHandler.borrowBook(bookIdToBorrow, studentEmail);

if (success) {

borrowedBooks.add(selectedBook);

bookList.remove(selectedBook);

bookListView.setItems(bookList); // Update the ListView

massege.setText("Book borrowed successfully!");

} else {

massege.setText("Failed to borrow book.");

### }

### }

private Book findBookById(String bookId) {

for (Book book : bookList) {

if (book.getId().equals(bookId)) {


return book;

### }

### }

return null; // Return null if no book is found with the given ID

### }

private String getCurrentStudentEmail() {

// Placeholder for getting the current logged-in user's email

return DBhandler.currrntUser; // Replace with actual logic

### }

@Override

public void initialize(URL location, ResourceBundle resources) {

instance = this; // Set the instance of BookView

loadBooksFromDatabase();


### }

private void loadBooksFromDatabase() {

try {

DBhandler dbHandler = new DBhandler();

bookList.setAll(dbHandler.getAllBooks()); // Load books from DB into the
ObservableList

bookListView.setItems(bookList); // Update the ListView

} catch (Exception e) {

e.printStackTrace();

### }

### }

### }


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

### @FXML

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

// Simulating sending an email notification (you can integrate JavaMail or other services
here)

System.out.println("Sending email notification about overdue loan...");

// In a real-world application, you would send an email using an SMTP service here.

// For now, it's a simple console log.

String message = "Your loan is overdue. Please return it as soon as possible. Overdue fee:
$"

+ overdueFeeField.getText();

System.out.println("Email sent: " + message);


// You could trigger a UI feedback (e.g., a success message) here as well

}

// Handle back action (close window or navigate to the previous screen)

@FXML

private void handleBack(ActionEvent event) {

OnlineLibrarySystem.OnlineLibrarySystem.loadView("/OnlineLibrarySystem/views/track.fxml"
, "Track");

// // Close the current window

// Stage stage = (Stage) Title.getScene().getWindow();

// stage.close();

}

}


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

### @FXML

public void handleBorrwwButton(ActionEvent event) throws IOException {

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

### }

### @FXML

public void handlecancelButton(ActionEvent event) throws IOException {

try {

OnlineLibrarySystem.ShowBook();

} catch (Exception ex) {

Logger.getLogger(BorrowController.class.getName()).log(Level.SEVERE, null, ex);

}

### }

@Override

public void initialize(URL url, ResourceBundle rb) {

LocalDate currentDate = LocalDate.now();

LocalDate futureDate = currentDate.plusDays(14);


date.setValue(futureDate);

date.setEditable(false);

### }

### }


package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.OnlineLibrarySystem;

import OnlineLibrarySystem.DB.DBhandler;

import OnlineLibrarySystem.models.Student;

import OnlineLibrarySystem.models.User;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.control.Alert.AlertType;

public class CreateAccountController {

### @FXML

private TextField nameField;

### @FXML

private TextField emailField;

### @FXML

private PasswordField passwordField;

### @FXML

private TextField phoneField;

### @FXML


private ToggleGroup gender;

### @FXML

private Button createBtn;

@FXML

private RadioButton fe;

@FXML

private RadioButton me;

@FXML

private Button backBtn;

### @FXML

private void initialize() {

gender = new ToggleGroup();

fe.setToggleGroup(gender);

me.setToggleGroup(gender);

}

### @FXML

private void handleCreateAccount(ActionEvent event) {

// Fetch user input

String name = nameField.getText().trim();

String email = emailField.getText().trim();

String password = passwordField.getText().trim();

String phone = phoneField.getText().trim();

RadioButton selectedGender = (RadioButton) gender.getSelectedToggle();


String gender1 = selectedGender != null? selectedGender.getText() : null;

// Validate input fields

if (name.isEmpty()) {

showAlert("Input Error", "Name cannot be empty.");

return;

}

if (email.isEmpty()) {

showAlert("Input Error", "Email cannot be empty.");

return;

}

if (!isValidEmail(email)) {

showAlert("Input Error", "Invalid email format. Please enter a valid email.");

return;

}

if (password.isEmpty()) {

showAlert("Input Error", "Password cannot be empty.");

return;

}

if (!isValidPassword(password)) {

showAlert("Input Error", "Password must be at least 8 characters long, contain one
uppercase letter, one lowercase letter, and one digit.");

return;

}

if (phone.isEmpty()) {

showAlert("Input Error", "Phone number cannot be empty.");


return;

}

if (!isValidPhone(phone)) {

showAlert("Input Error", "Invalid phone number format. Please enter a valid phone
number.");

return;

}

if (gender1 == null) {

showAlert("Input Error", "Please select a gender.");

return;

}

// Create a new User object

User newUser = new Student(name, email, password, gender1, phone, "Student");

// Call the DatabaseHandler method to create the account

boolean isCreated = DBhandler.createStudentAccount((Student) newUser);

if (isCreated) {

showAlert("Success", "Account created successfully!");

clearFields();

} else {

showAlert("Error", "Failed to create account. Please try again.");

}

}


### /**

* Validates if an email is in the correct format.

* @param email the email string to validate.

* @return true if the email is valid, false otherwise.

*/

private boolean isValidEmail(String email) {

String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

return email.matches(emailRegex);

}

### /**

* Validates if a password meets the required strength criteria.

* @param password the password string to validate.

* @return true if the password is valid, false otherwise.

*/

private boolean isValidPassword(String password) {

return password.length() >= 8 &&password.matches("^(?=.*[A-Z])(?=.*[a-
z])(?=.*\\d).{8,}$");

}

### /**

* Validates if a phone number is in a valid format.

* @param phone the phone number string to validate.

* @return true if the phone number is valid, false otherwise.

*/

private boolean isValidPhone(String phone) {


String phoneRegex = "^\\d{10,15}$"; // Adjust the range if needed

return phone.matches(phoneRegex);

}

### /**

* Utility method to show alerts.

* @param title the title of the alert dialog.

* @param message the message to display in the alert dialog.

*/

private void showAlert(String title, String message) {

Alert alert = new Alert(Alert.AlertType.ERROR);

alert.setTitle(title);

alert.setHeaderText(null);

alert.setContentText(message);

alert.showAndWait();

}

### /**

* Clears all input fields after successful account creation.

*/

private void clearFields() {

nameField.clear();

emailField.clear();

passwordField.clear();

phoneField.clear();

gender.selectToggle(null);


### }

### @FXML

private void handleBack(ActionEvent event) throws Exception {

// Navigate back to the login screen

OnlineLibrarySystem.showLogin();

}

// Utility method to show alerts

### }


package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;

import static OnlineLibrarySystem.OnlineLibrarySystem.loadEditExitingBook;

import static OnlineLibrarySystem.OnlineLibrarySystem.loadManageBookInventory;

import OnlineLibrarySystem.models.Book;

//import static cs313reemproject.CS313ReemProject.loadEditExitingBook;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Label;

import javafx.scene.control.MenuButton;

import javafx.scene.control.MenuItem;

import javafx.scene.control.TextField;

import javafx.scene.text.Text;

public class EditBookAfterSelectingController {

//cs313reemproject.controllers.EditBookAfterSelectingController


### @FXML

private Label bookTitle, author, genre, ISBN, bookCover;

### @FXML

private Text Title, txt;

### @FXML

private TextField bookTitleTxt, authorTxt, ISBNTxt, bookTitleTxt1, authorTxt1;

### @FXML

private MenuButton menu;

### @FXML

private MenuItem fantasy, scienceFiction, romance, mystery, horror;

### @FXML

private Button saveButton, backButton;

ObservableList<String> genrea = FXCollections.observableArrayList("fantasy", "science
fiction", "romance", "mystery", "horror");

@FXML

private ChoiceBox genreBox;

### @FXML

public void initialize() {

genreBox.setItems(genrea);


### }

### @FXML

public void handleSaveButton(ActionEvent event) {

// Get the new values from the text fields

String newTitle = bookTitleTxt.getText().trim(); // New title

String newAuthor = authorTxt.getText().trim(); // New author

String newGenre = (String) genreBox.getValue(); // Get the selected genre from the
ChoiceBox

// Get the old title and author from the previous scene

String oldTitle = bookTitleTxt1.getText().trim(); // Old title field

String oldAuthor = authorTxt1.getText().trim(); // Old author field

// Validation: Check if required fields are empty

if (newTitle.isEmpty() || newAuthor.isEmpty() || oldTitle.isEmpty() || oldAuthor.isEmpty())
{

// Show an alert for missing fields

javafx.scene.control.Alert alert = new
javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);

alert.setTitle("Validation Error");

alert.setHeaderText(null);

alert.setContentText("Please fill in all fields before saving.");

alert.showAndWait();

return;

}


// Validation: Check if a genre is selected

if (newGenre == null) {

// Show an alert for missing genre

javafx.scene.control.Alert alert = new
javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);

alert.setTitle("Validation Error");

alert.setHeaderText(null);

alert.setContentText("Please select a genre.");

alert.showAndWait();

return;

}

// Create a Book object with the new details

Book updatedBook = new Book(newTitle, newAuthor, newGenre);

// Call the DBhandler method to update the book details

DBhandler dbHandler = new DBhandler();

boolean success = dbHandler.editBook(oldTitle, oldAuthor, updatedBook);

// Show confirmation or failure message

if (success) {

txt.setText("Changes Saved Successfully!");

} else {

txt.setText("Failed to Save Changes. Please Try Again.");

}

}


### @FXML

public void handleBackButton() {

loadManageBookInventory();

}

// This method sets the previous book details

public void setBookDetails(String title, String author) {

bookTitleTxt1.setText(title); // Old title field

authorTxt1.setText(author);

bookTitleTxt.setText(title); // New title field

authorTxt.setText(author); // Author field

}

//genreTxt

### }


package OnlineLibrarySystem.controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import OnlineLibrarySystem.OnlineLibrarySystem;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class ForgotPassController {

### @FXML

private Button backBtn, b1;

### @FXML

private TextField emailField;

### @FXML

private void handleBack(ActionEvent event) throws Exception {

OnlineLibrarySystem.showLogin();

}


### @FXML

public void handleForget(ActionEvent event) {

String email = emailField.getText().trim();

// Validate the email input

if (email.isEmpty()) {

showAlert("Input Error", "Email field cannot be empty.");

return;

}

if (isValidEmail(email)) {

// Placeholder for email sending functionality

showAlert("Forgot Password", "An email has been sent to the provided address.");

} else {

showAlert("Invalid Email", "Please enter a valid email address.");

}

}

// Email validation method using regex

private boolean isValidEmail(String email) {

String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +

"(?:[a-zA-Z0- 9 - ]+\\.)+[a-zA-Z]{2,7}$";

Pattern pattern = Pattern.compile(emailRegex);

Matcher matcher = pattern.matcher(email);

return matcher.matches();

}


// Utility method to show alerts

private void showAlert(String title, String message) {

Alert alert = new Alert(Alert.AlertType.INFORMATION);

alert.setTitle(title);

alert.setHeaderText(null);

alert.setContentText(message);

alert.showAndWait();

}

}


package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.control.ListCell;

import javafx.scene.control.ListView;

import javafx.scene.layout.HBox;

import java.util.Map;

import javafx.scene.control.Button;

public class LibraryInventoryController {

@FXML private ListView<String> bookListView; // Display book info as Strings

@FXML private Label totalBooksLabel;

@FXML private Button b1;

private ObservableList<String> bookInfoList = FXCollections.observableArrayList();

### @FXML

public void initialize() {

refreshInventory(); // Load books initially

}


// Load book titles and available copies into the ListView

private void loadBooks() {

bookInfoList.clear(); // Clear the current list

// Retrieve the count of available copies for each book title from DB

Map<String, Integer> titleCopiesMap = DBhandler.countNumberOfCopies();

int totalBooks = titleCopiesMap.size(); // Total unique books

// Populate bookInfoList with each book's title and available copies

for (Map.Entry<String, Integer> entry : titleCopiesMap.entrySet()) {

String bookTitle = entry.getKey();

int availableCopies = entry.getValue();

String bookInfo = bookTitle + ", Available Copies: " + availableCopies;

bookInfoList.add(bookInfo);

}

// Update total books label

totalBooksLabel.setText("Total Books: " + totalBooks);

// Set the ObservableList as the source of items for the ListView

bookListView.setItems(bookInfoList);

}

// Refresh the inventory display


### @FXML

public void refreshInventory() {

loadBooks(); // Reload the list of books

}

}


### /*

* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this
license

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

public class MainController {

### @FXML


private Text txt;

### @FXML

private Button manageButton;

### @FXML

private Button trackButton;

### @FXML

private Button processButton;

### @FXML

public void handlemanageButton(ActionEvent event) {

loadManageBookInventory();

}

### @FXML

public void handletrackButton(ActionEvent event) {

showTrack();

}

### @FXML

public void handleprocessButton(ActionEvent event) {

show();

}

}


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

### @FXML

private ListView<Loan> loanRequestListView;

@FXML

private TextField searchField;

@FXML private Button back;


@FXML private Button b2,b3,b4,b5,b6;

private ObservableList<Loan> loanRequests = FXCollections.observableArrayList();

private DBhandler dbHandler = new DBhandler();

### @FXML

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

### @FXML

public void rejectRequest() {

updateLoanStatus("rejected");

}

### @FXML

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

### @FXML

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


### @FXML

private void handleBack(ActionEvent event) throws Exception {

OnlineLibrarySystem.loadMain();

}

### }


package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBconnection;

import OnlineLibrarySystem.DB.DBhandler;

import static OnlineLibrarySystem.OnlineLibrarySystem.loadAddNewBook;

import static OnlineLibrarySystem.OnlineLibrarySystem.loadEditBook;

import static OnlineLibrarySystem.OnlineLibrarySystem.loadEditExitingBook;

import static OnlineLibrarySystem.OnlineLibrarySystem.loadMain;

import static OnlineLibrarySystem.OnlineLibrarySystem.loadRemoveBook;

import java.net.URL;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;

import java.util.ResourceBundle;

import java.util.stream.Collectors;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;


import javafx.scene.control.ListView;

import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;

import javafx.scene.text.Text;

import OnlineLibrarySystem.models.Book; // Import your Book model class

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;

import javafx.stage.Stage;

public class ManageBookInventoryController implements Initializable {

### @FXML

private Text Title;

### @FXML

private Button searchButton;

### @FXML

private TextField searchTextField;

### @FXML


private Text txt;

### @FXML

private ListView<String> suggestionsList;

private ObservableList<String> bookTitles;

### @FXML

private Button addNewBookButton;

### @FXML

private Button EditBookButton;

### @FXML

private Button removeBookButton;

### @FXML

private Button backButton;

@Override

public void initialize(URL location, ResourceBundle resources) {

// Initialize book titles with unique titles and authors from the database

DBhandler dbHandler = new DBhandler();

List<Book> uniqueBooks = dbHandler.getUniqueTitleBooks();

// Extract titles and authors from uniqueBooks, formatted as "Title by Author"


bookTitles = FXCollections.observableArrayList(

uniqueBooks.stream()

.map(book -> book.getTitle() + " by " + book.getAuthor())

.collect(Collectors.toList())

);

// Set up suggestions on typing

searchTextField.setOnKeyReleased(this::handleSearch);

suggestionsList.setVisible(false);

// Set up click event for selecting an item from the suggestions

suggestionsList.setOnMouseClicked(event -> {

String selected = suggestionsList.getSelectionModel().getSelectedItem();

if (selected != null) {

searchTextField.setText(selected);

suggestionsList.setVisible(false);

txt.setText("Selected Book: " + selected);

}

});

}

@FXML

public void handleEditBookButton(ActionEvent event) {

String selectedBook = searchTextField.getText().trim(); // Get the selected book from the text
field

if (selectedBook.isEmpty() || !selectedBook.contains(" by ")) {


// Show an alert if the input is invalid

Alert alert = new Alert(Alert.AlertType.ERROR);

alert.setTitle("Invalid Input");

alert.setHeaderText(null);

alert.setContentText("Please select a book in the format 'Title by Author' before editing.");

alert.showAndWait();

return;

}

// Extract the title and author from the selected item (assumes format "Title by Author")

String[] bookParts = selectedBook.split(" by ");

String title = bookParts[0];

String author = bookParts[1];

try {

// Load the EditBookAfterSelecting scene

FXMLLoader loader = new
FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/editBookAfterSelecting.FX
ML"));

Parent root = loader.load();

// Get the controller for the new scene

EditBookAfterSelectingController controller = loader.getController();

// Set the previous title and author in the fields in EditBookAfterSelectingController

controller.setBookDetails(title, author);


// Show the scene (assuming you have a stage)

Stage stage = (Stage) searchButton.getScene().getWindow();

stage.setScene(new Scene(root));

stage.show();

} catch (IOException e) {

e.printStackTrace();

}

}

### @FXML

public void handleremoveBookButton(ActionEvent event) {

String sentence = searchTextField.getText().trim(); // Example input

if (sentence.isEmpty() || !sentence.contains(" by ")) {

// Show an alert if the input is invalid

Alert alert = new Alert(Alert.AlertType.ERROR);

alert.setTitle("Invalid Input");

alert.setHeaderText(null);

alert.setContentText("Please select a book in the format 'Title by Author' before
removing.");

alert.showAndWait();

return;

}

String[] parts = sentence.split(" by ", 2); // Split the string into two parts at " by "

String titleToDelete = parts[0].trim(); // Extract the title and remove any extra spaces


String authorToDelete = parts[1].trim(); // Extract the author and remove any extra spaces

// Confirmation alert

Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

alert.setTitle("Remove Book Confirmation");

alert.setHeaderText(null);

alert.setContentText("When Removing This Book, It Will Be Permanently Lost.");

if (alert.showAndWait().get() == ButtonType.OK) {

boolean isDeleted = new DBhandler().deleteBookByTitle(titleToDelete, authorToDelete);

if (isDeleted) {

txt.setText("Removing is done successfully.");

} else {

txt.setText("Failed to remove the book. Please check the title.");

}

}

}

### @FXML

public void handlesearchButton(ActionEvent event) {

if (searchTextField.getText().trim().isEmpty()) {

// Show an alert if the search field is empty

Alert alert = new Alert(Alert.AlertType.ERROR);

alert.setTitle("Search Error");

alert.setHeaderText(null);


alert.setContentText("Please enter a book title or author to search.");

alert.showAndWait();

return;

}

suggestionsList.setVisible(false); // Hide suggestions on search

}

private void handleSearch(KeyEvent event) {

String input = searchTextField.getText().toLowerCase();

// Filter the list based on the input

List<String> filteredSuggestions = bookTitles.stream()

.filter(title -> title.toLowerCase().startsWith(input))

.collect(Collectors.toList());

// Update ListView with filtered suggestions

suggestionsList.getItems().setAll(filteredSuggestions);

// Show the ListView only if there are matching suggestions

suggestionsList.setVisible(!filteredSuggestions.isEmpty());

}

private List<String> getBookTitlesFromDB() {

List<String> titles = new ArrayList<>();


String query = "SELECT title, author, MIN(bookID) AS bookID FROM Book GROUP BY
title, author"; // Adjust to match your schema

try (Connection conn = DBconnection.getInstance().getConnection();

Statement stmt = conn.createStatement();

ResultSet rs = stmt.executeQuery(query)) {

while (rs.next()) {

titles.add(rs.getString("title"));

}

} catch (SQLException e) {

e.printStackTrace();

}

return titles;

}

### @FXML

public void handleBackButton(ActionEvent event) {

loadMain();

}

### @FXML

public void handleaddNewBookButton(ActionEvent event) {

loadAddNewBook();

}

### }


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

### /**

* FXML Controller class

*

* @author joman

*/

public class OrderController implements Initializable {

### @FXML


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

orderListArea.setItems(borrowedBooks); // Update the orderListArea with borrowed
books

}

}


### @FXML

public void handleBackbutton(ActionEvent event) throws Exception {

OnlineLibrarySystem.ShowBook();

}

### @FXML

private void handleReturnButton(ActionEvent event) throws IOException, Exception {

String bookIdToReturn = returntext.getText().trim();

if (bookIdToReturn.isEmpty()) {

masseg.setText("Enter ID");

return;

}

Book bookToReturn = findBookInBorrowedBooks(bookIdToReturn);

if (bookToReturn != null) {

// Update the database to mark the book as returned and available

boolean isReturned = dbHandler.returnBook(bookToReturn.getId(),
getCurrentStudentEmail());

if (isReturned) {

borrowedBooks.remove(bookToReturn); // Remove the book from the borrowed list

BookView.bookList.add(bookToReturn); // Add it back to the available books list

orderListArea.setItems(borrowedBooks); // Refresh the ListView

BookView.getInstance().bookListView.setItems(BookView.bookList); // Update the
main book view

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

return null; // Book not found in borrowed books

}

private String getCurrentStudentEmail() {

// Placeholder for getting the current logged-in user's email

return DBhandler.currrntUser; // Replace with actual logic

### }

public void initialize(URL url, ResourceBundle rb) {

setBorrowedBooks(BookView.borrowedBooks);

orderListArea.setItems(BookView.borrowedBooks);


// orderListArea.setItems(borrowedBooks);

}

### }


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

### @FXML

private TextField reqdate;

### @FXML

private Button rejbtn,apprbtn,back;

### @FXML

private Label title,bookAvailableLabel,bookNotAvailableLabel;

### @FXML

private void handlereqDateAction(ActionEvent event) {

System.out.println(" clicked ");


System.out.println(reqdate.getText());

}

### @FXML

private void handleRejectBtn(ActionEvent event) {

Alert alert = new Alert(Alert.AlertType.WARNING);

alert.setTitle("Request Rejected");

alert.setHeaderText(null);

alert.setContentText("The request dated " + reqdate.getText() + " has been rejected.");

alert.showAndWait();

System.out.println("Reject button clicked for request date: " + reqdate.getText());

}

### @FXML

private void handleApproveAction() {

Alert alert = new Alert(Alert.AlertType.INFORMATION);

alert.setTitle("Request Approved");

alert.setHeaderText(null);

alert.setContentText("The request dated " + reqdate.getText() + " has been approved.");

alert.showAndWait();

System.out.println("Approve button clicked for request date: " + reqdate.getText());

}

### @FXML

public void handleBack(ActionEvent event) throws Exception {


OnlineLibrarySystem.OnlineLibrarySystem.show();

}

### }


package OnlineLibrarySystem.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;

import javafx.scene.control.TextField;

import javafx.stage.Stage;

public class SystemSettingsController {

// UI Elements

@FXML private TextField maxBooksField;

@FXML private TextField overdueFeesField;

@FXML private TextField loanDurationField;

// Settings (stored in-memory)

private int maxBooks = 5; // Default value

private double overdueFees = 0.50; // Default value

private int loanDuration = 14; // Default value

### @FXML

public void initialize() {

// Initialize the fields with default values

maxBooksField.setText(String.valueOf(maxBooks));

overdueFeesField.setText(String.valueOf(overdueFees));

loanDurationField.setText(String.valueOf(loanDuration));

}


// Method to save the settings when the user clicks "Save"

@FXML

public void saveSettings() {

try {

// Parse the values entered by the user

maxBooks = Integer.parseInt(maxBooksField.getText());

overdueFees = Double.parseDouble(overdueFeesField.getText());

loanDuration = Integer.parseInt(loanDurationField.getText());

// Confirm successful save

Alert alert = new Alert(Alert.AlertType.INFORMATION);

alert.setTitle("Settings Saved");

alert.setHeaderText(null);

alert.setContentText("System settings have been updated successfully.");

alert.showAndWait();

// Close the window after saving

closeWindow();

} catch (NumberFormatException e) {

// Show an error alert if any input is invalid

showError("Invalid Input", "Please enter valid numbers for all fields.");

}

}


// Close the current window

private void closeWindow() {

((Stage) maxBooksField.getScene().getWindow()).close();

}

// Show an error alert with the specified message

private void showError(String title, String message) {

Alert alert = new Alert(Alert.AlertType.ERROR);

alert.setTitle(title);

alert.setHeaderText(null);

alert.setContentText(message);

alert.showAndWait();

}

// Getters for the settings

public int getMaxBooks() {

return maxBooks;

}

public double getOverdueFees() {

return overdueFees;

}

public int getLoanDuration() {

return loanDuration;

}}


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

### @FXML

private Button back, select, searchButton;

@FXML


private ListView<String> overdueLoanListView;

@FXML

private TextField loanIdSearchField;

private ObservableList<String> overdueLoans = FXCollections.observableArrayList();

private DBhandler dbHandler = new DBhandler();

### @FXML

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

if (returnDate != null && maxReturnDate != null && returnDate.after(maxReturnDate))
{


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

### @FXML

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

javafx.scene.control.Alert alert = new
javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);

alert.setTitle("Selection Error");

alert.setHeaderText(null);

alert.setContentText("Please select a loan from the list to view details.");

alert.showAndWait();

}

}

### @FXML

public void handleSearch(ActionEvent event) {

String loanId = loanIdSearchField.getText().trim(); // Get text from TextField

if (loanId.isEmpty()) {

// Show an alert if the search field is empty

javafx.scene.control.Alert alert = new
javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);

alert.setTitle("Search Error");

alert.setHeaderText(null);

alert.setContentText("Please enter a loan ID to search.");

alert.showAndWait();

} else {

searchOverdueLoansById(loanId); // Proceed with the search if loan ID is provided


### }

### }

public void showLoanDetails(String loanId, LocalDate borrowDate, LocalDate dueDate) {

try {

FXMLLoader loader = new
FXMLLoader(getClass().getResource("/OnlineLibrarySystem/views/bookeddetails.fxml"));

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

overdueLoans.clear(); // Clear the list first

try {

ResultSet rs = dbHandler.getOverdueLoansById(loanId); // Execute the query for
specific loanId

System.out.println("Searching for overdue loans with ID: " + loanId);


boolean foundOverdueLoans = false; // Flag to check if the loan is overdue

while (rs.next()) {

int loanIdResult = rs.getInt("loanID"); // Retrieve loanId

Date maxReturnDate = rs.getDate("maxReturnDate"); // Retrieve maxReturnDate

Date returnDate = rs.getDate("returnDate"); // Retrieve returnDate

// Debugging output to verify data retrieval

System.out.println("Loan ID: " + loanIdResult + ", Max Return Date: " +
maxReturnDate + ", Return Date: " + returnDate);

if (returnDate != null && maxReturnDate != null &&
returnDate.after(maxReturnDate)) {

String loanInfo = "Loan #" + loanIdResult + " - Max Return: " + maxReturnDate + "

- Return: " + returnDate;

overdueLoans.add(loanInfo); // Add to the list if overdue

foundOverdueLoans = true;

}

}

if (!foundOverdueLoans) {

System.out.println("No overdue loans found for loan ID: " + loanId);

}

} catch (SQLException e) {

e.printStackTrace(); // Print exception if there’s any error


### }

// Update the ListView with the overdue loans

overdueLoanListView.setItems(overdueLoans);

System.out.println("ListView updated with overdue loans.");

}

### @FXML

public void handleBack() {

// Navigate back to the main menu or previous screen

loadMain();

}

}


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

public class editBcontroller {

### @FXML

private ListView<Loan> loanRequestListView;

@FXML

private TextField searchField;

@FXML private Button back;


private ObservableList<Loan> loanRequests = FXCollections.observableArrayList();

private DBhandler dbHandler = new DBhandler();

### @FXML

public void initialize() {

### }

### @FXML

public void approveRequest() {

updateLoanStatus("approved");

}

### @FXML

public void rejectRequest() {

updateLoanStatus("rejected");

}

### @FXML

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

showAlert("No Selection", "Please select a request to update.");

}

}

### @FXML

public void handleSearchButton() {

String searchText = searchField.getText().toLowerCase();

List<Loan> filteredRequests = loanRequests.stream()

.filter(req -> String.valueOf(req.getID()).contains(searchText))

.collect(Collectors.toList());

loanRequestListView.setItems(FXCollections.observableArrayList(filteredRequests));

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


package OnlineLibrarySystem.controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;

import OnlineLibrarySystem.OnlineLibrarySystem;

import OnlineLibrarySystem.DB.DBhandler;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;

import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

public class loginController {

### @FXML


private TextField emailTxtF;

@FXML

private PasswordField passwordTxtf;

@FXML

private Button btnLogin;

@FXML

private Label CreateLbl,lbl3,text3,text4,text6,text7;

@FXML

private Label ResLbl;

@FXML private VBox pane;

@FXML private HBox text2;

@FXML private GridPane grid,grid2;

### /**

* Initializes the controller class.

*/

### @FXML

private void handleLoginBtn(ActionEvent event) throws Exception {

String email = emailTxtF.getText().trim();

String password = passwordTxtf.getText().trim();

// Validate inputs

if (email.isEmpty()) {


showAlert("Input Error", "Email cannot be empty.");

return;

}

if (!isValidEmail(email)) {

showAlert("Input Error", "Invalid email format. Please enter a valid email.");

return;

}

if (password.isEmpty()) {

showAlert("Input Error", "Password cannot be empty.");

return;

}

// Attempt to log in

String role = DBhandler.loginUser(email, password);

if (role == null) {

showAlert("Invalid Credentials", "Incorrect email or password. Please try again.");

return;

}

// Navigate to the appropriate scene based on role

switch (role) {

case "Admin":

OnlineLibrarySystem.loadAdmin();

break;

case "Librarian":


OnlineLibrarySystem.loadMain();

break;

case "Student":

OnlineLibrarySystem.ShowBook();

break;

default:

showAlert("Access Denied", "Unknown user role.");

}

}

### /**

* Validates if an email is in the correct format.

* @param email the email string to validate.

* @return true if the email is valid, false otherwise.

*/

private boolean isValidEmail(String email) {

String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

return email.matches(emailRegex);

}

### /**

* Shows an alert dialog with the provided title and message.

* @param title the title of the alert dialog.

* @param message the message to display in the alert dialog.

*/

private void showAlert(String title, String message) {


Alert alert = new Alert(Alert.AlertType.ERROR);

alert.setTitle(title);

alert.setContentText(message);

alert.showAndWait();

}

### @FXML

private void handleCreateLbl(MouseEvent event) throws Exception {

OnlineLibrarySystem.showCreate();

}

@FXML

private void handleForgotLbl(MouseEvent event) throws Exception {

OnlineLibrarySystem.showForgot();

}

### }

package OnlineLibrarySystem.controllers;

import OnlineLibrarySystem.DB.DBhandler;

import OnlineLibrarySystem.models.Admin;

import OnlineLibrarySystem.models.Librarian;


import OnlineLibrarySystem.models.Student;

import OnlineLibrarySystem.models.User;

import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.stage.Stage;

public class updateUserController {

@FXML private TextField emailField;

@FXML private TextField nameField;

@FXML private PasswordField passwordField;

@FXML private TextField phoneField;

@FXML private RadioButton maleRadio;

@FXML private RadioButton femaleRadio;

@FXML private RadioButton adminRadio;

@FXML private RadioButton librarianRadio;

@FXML private RadioButton studentRadio;

@FXML private Button b1;

private AdminDashboardController adminController;

private boolean isUpdateMode = false;

private int selectedIndex = -1;

@FXML private ToggleGroup roleGroup;

@FXML private ToggleGroup genderGroup;


public void setAdminController(AdminDashboardController adminController) {

this.adminController = adminController;

}

public void initializeForUpdate(User selectedUser, int index) {

isUpdateMode = true;

selectedIndex = index;

// Populate fields with user data for update

emailField.setText(selectedUser.getEmail());

nameField.setText(selectedUser.getName());

passwordField.setText(selectedUser.getPassword());

phoneField.setText(selectedUser.getPhone());

maleRadio.setSelected("male".equals(selectedUser.getGender()));

femaleRadio.setSelected("female".equals(selectedUser.getGender()));

switch (selectedUser.getRole()) {

case "Admin":

adminRadio.setSelected(true);

break;

case "Librarian":

librarianRadio.setSelected(true);

break;

case "Student":

studentRadio.setSelected(true);

break;

}

}


// Handle saving user data (both creating and updating)

@FXML

public void saveUser() {

if (isInputValid()) {

String email = emailField.getText();

String name = nameField.getText();

String password = passwordField.getText();

String phone = phoneField.getText();

String gender = maleRadio.isSelected()? "male" : "female";

String role = getSelectedRole();

User user = createUserObject(name, email, password, gender, phone, role);

boolean isOperationSuccessful;

if (isUpdateMode) {

// Update existing user

isOperationSuccessful = DBhandler.updateUser(user);

if (isOperationSuccessful) {

adminController.updateUserInListView(selectedIndex, user);

}

} else {

// Add new user

isOperationSuccessful = DBhandler.addUser(user);

if (isOperationSuccessful) {


adminController.addUserToList(user);

}

}

if (isOperationSuccessful) {

closeWindow();

} else {

showError("Error", "Operation failed. Please try again.");

}

}

}

// Validate user input

private boolean isInputValid() {

if (emailField.getText().isEmpty() || nameField.getText().isEmpty() ||

passwordField.getText().isEmpty() || phoneField.getText().isEmpty() ||

(!maleRadio.isSelected() && !femaleRadio.isSelected()) ||

(!adminRadio.isSelected() && !librarianRadio.isSelected() && !studentRadio.isSelected()))
{

showError("Validation Error", "All fields must be filled.");

return false;

}

if (!isValidEmail(emailField.getText())) {

showError("Validation Error", "Please enter a valid email address.");

return false;


### }

if (!isValidPhone(phoneField.getText())) {

showError("Validation Error", "Please enter a valid phone number (10-15 digits).");

return false;

}

if (!isValidPassword(passwordField.getText())) {

showError("Validation Error", "Password must be at least 8 characters long, contain one
uppercase letter, one lowercase letter, and one digit.");

return false;

}

return true;

}

// Validate email format

private boolean isValidEmail(String email) {

return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

}

// Validate phone number format

private boolean isValidPhone(String phone) {

return phone.matches("^\\d{10,15}$");

}


// Validate password strength

private boolean isValidPassword(String password) {

return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");

}

private String getSelectedRole() {

if (adminRadio.isSelected()) return "Admin";

if (librarianRadio.isSelected()) return "Librarian";

if (studentRadio.isSelected()) return "Student";

return null;

}

private User createUserObject(String name, String email, String password, String gender, String
phone, String role) {

switch (role) {

case "Admin":

return new Admin(name, email, password, gender, phone, role);

case "Librarian":

return new Librarian(name, email, password, gender, phone, role);

case "Student":

return new Student(name, email, password, gender, phone, role);

default:

return null;

}

}


private void closeWindow() {

((Stage) emailField.getScene().getWindow()).close();

}

private void showError(String title, String message) {

Alert alert = new Alert(Alert.AlertType.ERROR);

alert.setTitle(title);

alert.setHeaderText(null);

alert.setContentText(message);

alert.showAndWait();

}

### }


