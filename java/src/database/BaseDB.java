package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDB {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/project?user=root";
    private static final String username = "root";
    private static final String password = "a123456";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    protected Connection connection;

    public BaseDB() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
