package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class testDB extends exampleDB {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public boolean insert(int id, String name, int age, int gender) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO person VALUES(?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,age);
            preparedStatement.setInt(4,gender);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public void closeAll() {
        try {
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
