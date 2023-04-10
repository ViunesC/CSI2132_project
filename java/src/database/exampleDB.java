package database;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class exampleDB {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/project?user=root";
    private static final String username = "root";
    private static final String password = "a123456";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    protected Connection connection;

    public exampleDB() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void closeAll();
}
