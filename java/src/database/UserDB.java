package database;

import database.SQLUtil.querySQL;
import database.exception.UserNotFoundException;
import entity.Customer;
import entity.Employee;
import entity.Utility.AccountPackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDB extends BaseDB {
    public UserDB() {
        super();
    }

    /**
     * Insert new customer into 'customer' database
     * @return
     */
    public boolean insertToDB(Customer customer) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(querySQL.REGISTER_CUSTOMER);
            String fullName = customer.getFirst_name() + " " + customer.getLast_name();
            String dateStr = customer.getRegister_date().toString();

            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhone_num());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getPassword());
            preparedStatement.setString(7, customer.getSsn());
            preparedStatement.setString(8, dateStr);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * Insert new employee into 'employee' database
     * @return
     */
    public boolean insertToDB(Employee employee) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(querySQL.REGISTER_EMPLOYEE);
            String fullName = employee.getFirst_name() + " " + employee.getLast_name();

            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, employee.getSsn());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getPassword());
            preparedStatement.setString(6, employee.getRole());
            preparedStatement.setInt(7, employee.getHotel_id());
            preparedStatement.setString(8, employee.getPosition());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * Find User in database via credential (for login purpose)
     * @return
     */
    public AccountPackage findUserInDB(String email, String password, int FLAG) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AccountPackage account = null;
        String sqlQuery = "";
        if (FLAG == 0) {
            sqlQuery = querySQL.FIND_EMPLOYEE_VIA_CREDS;
        } else if (FLAG == 1) {
            sqlQuery = querySQL.FIND_CUSTOMER_VIA_CREDS;
        } else {
            sqlQuery = querySQL.FIND_ADMIN_VIA_CREDS;
        }

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                if (FLAG != -1) {
                    String name = resultSet.getString(2);
                    account = AccountPackage.createPackage(name, email, password, id, FLAG);
                } else {
                    account = AccountPackage.createPackage("Admin #" + id, email, password, id, FLAG);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (account == null) {
            throw new UserNotFoundException("Cannot find required user");
        }

        return account;
    }

    /**
     * Find user in database via personal info (for employee-side operation)
     * @return
     */
    public AccountPackage findUserInDB(String fullName, String phoneNum) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AccountPackage account = null;

        try {
            preparedStatement = connection.prepareStatement(querySQL.FIND_CUSTOMER_VIA_INFO);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, phoneNum);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String email = resultSet.getString(5);
                String password = resultSet.getString(6);
                account = AccountPackage.createPackage(fullName, email, password, id, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (account == null) {
            throw new UserNotFoundException("Cannot find required user");
        }

        return account;
    }

    public Customer findUserInDB(int customerID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = null;

        try {
            preparedStatement = connection.prepareStatement(querySQL.FIND_CUSTOMER_VIA_ID);
            preparedStatement.setInt(1, customerID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String[] name = resultSet.getString(2).split(" ");
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String email = resultSet.getString(5);
                String password = resultSet.getString(6);
                String ssn = resultSet.getString(7);
                customer = new Customer(name[0], name[1], email, phone, ssn, password, address);
                customer.setId(customerID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (customer == null) {
            throw new UserNotFoundException("User does not exist");
        }

        return customer;
    }

    public Employee findUserInDB(int employeeID, int FLAG) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee employee = null;

        try {
            preparedStatement = connection.prepareStatement(querySQL.FIND_EMPLOYEE_VIA_ID);
            preparedStatement.setInt(1, employeeID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String fname = resultSet.getString(2).split(" ")[0];
                String lname = resultSet.getString(2).split(" ")[1];
                String ssn = resultSet.getString(3);
                String email = resultSet.getString(4);
                String password = resultSet.getString(5);
                String role = resultSet.getString(6);
                int hotel_id = resultSet.getInt(7);
                String position = resultSet.getString(8);
                employee = new Employee(fname, lname, email, "null", ssn, password, role, hotel_id, position);
                employee.setId(employeeID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (employee == null) {
            throw new UserNotFoundException("User does not exist");
        }

        return employee;
    }

    public boolean modifyCustomer(int id, String name, String address, String phone, String email, String password, String ssn) {
        if (name.equals("") && address.equals("") && phone.equals("") && email.equals("") && password.equals("") && ssn.equals("")) {
            return false;
        }

        String query = querySQL.UPDATE_CUSTOMER + " SET";
        int flag = 0;

        if (!name.equals("")) {
            query += " customer_name = '" + name + "'";
            ++flag;
        }

        if (!address.equals("")) {
            if (flag != 0) {
                query += ",";
            }
            query += " customer_address = '" + address + "'";
            ++flag;
        }

        if (!phone.equals("")) {
            if (flag != 0) {
                query += ",";
            }
            query += " customer_phone = '" + phone + "'";
            ++flag;
        }

        if (!email.equals("")) {
            if (flag != 0) {
                query += ",";
            }
            query += " customer_email = '" + email + "'";
            ++flag;
        }

        if (!password.equals("")) {
            if (flag != 0) {
                query += ",";
            }
            query += " password = '" + password + "'";
            ++flag;
        }

        if (!ssn.equals("")) {
            if (flag != 0) {
                query += ",";
            }
            query += " SSN = '" + ssn + "'";
            ++flag;
        }

        query += " WHERE customer_ID = " + id;
        Statement statement = null;

        try {
            statement = connection.createStatement();
            flag = statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (flag != 1) {
            throw new UserNotFoundException("something went wrong");
        }

        return true;
    }

    public boolean modifyEmployee(int id, String name, String role, String position, String email, String ssn, String password) {
        if (name.equals("") && role.equals("") && position.equals("") && email.equals("") && password.equals("") && ssn.equals("")) {
            return false;
        }

        String query = querySQL.UPDATE_EMPLOYEE + " SET";
        int flag = 0;

        if (!name.equals("")) {
            query += " employee_name = '" + name + "'";
            ++flag;
        }

        if (!role.equals("")) {
            if (flag != 0) {
                query += ",";
            }
            query += " role_name = '" + role + "'";
            ++flag;
        }

        if (!position.equals("")) {
            if (flag != 0) {
                query += ",";
            }
            query += " position = '" + position + "'";
            ++flag;
        }

        if (!email.equals("")) {
            if (flag != 0) {
                query += ",";
            }
            query += " employee_email = '" + email + "'";
            ++flag;
        }

        if (!password.equals("")) {
            if (flag != 0) {
                query += ",";
            }
            query += " password = '" + password + "'";
            ++flag;
        }

        if (!ssn.equals("")) {
            if (flag != 0) {
                query += ",";
            }
            query += " SSN = '" + ssn + "'";
            ++flag;
        }

        query += " WHERE employee_ID = " + id;
        Statement statement = null;

        try {
            statement = connection.createStatement();
            flag = statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (flag != 1) {
            throw new UserNotFoundException("something went wrong");
        }

        return true;
    }
    public int findHotelID(int employee_id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int hotel_id = -1;

        try {
            preparedStatement = connection.prepareStatement(querySQL.FIND_HOTEL_ID_VIA_EMPLOYEE_ID);
            preparedStatement.setInt(1, employee_id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                hotel_id = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (hotel_id == -1) {
            throw new RuntimeException("Hotel not found");
        }

        return hotel_id;
    }

    public int findUserByName(String name) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int uid = -1;

        try {
            preparedStatement = connection.prepareStatement(querySQL.FIND_CUSTOMER_ID_VIA_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                uid = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (uid == -1) {
            throw new UserNotFoundException("Customer not found!");
        }

        return uid;
    }

    @Override
    public void close() {
        super.close();
    }
}
