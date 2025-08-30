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
