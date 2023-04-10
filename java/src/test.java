import java.sql.*;

public class test {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/project?user=root";
    private static final String username = "root";
    private static final String password = "a123456";

    public static void main(String[] args) throws Exception {
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        Class.forName(JDBC_DRIVER);

        Connection connection = DriverManager.getConnection(URL, username, password);

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person VALUES(?, ?, ?, ?)");
        /*preparedStatement.setInt(1,23);
        preparedStatement.setString(2,"James");
        preparedStatement.setInt(3,19);
        preparedStatement.setInt(4,1);
        preparedStatement.executeUpdate();

        preparedStatement.setInt(1,37);
        preparedStatement.setString(2,"Jessie");
        preparedStatement.setInt(3,27);
        preparedStatement.setInt(4,2);
        preparedStatement.executeUpdate();*/

        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM person";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            int gender = resultSet.getInt(4);
            String genderStr = "None";
            switch(gender) {
                case 1:
                    genderStr = "male";
                    break;
                case 2:
                    genderStr = "female";
                    break;
                case 3:
                    genderStr = "other";
                    break;
            }
            System.out.printf("Name: %s, Age: %d, Gender: %s\n", name, age, genderStr);
        }


        resultSet.close();
        preparedStatement.close();
        statement.close();
        connection.close();
    }
}
