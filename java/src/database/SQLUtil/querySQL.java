package database.SQLUtil;

public class querySQL {
    /* Insertion of new data */

    // Register new customer
    public static final String REGISTER_CUSTOMER = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?)";
    // Add new employee
    public static final String REGISTER_EMPLOYEE = "INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?)";
    // Add new booking
    public static final String INSERT_NEW_BOOKING = "INSERT INTO Booking VALUES(?,?,?,?,?)";
    // Add new renting
    public static final String INSERT_NEW_RENTING = "INSERT INTO Renting VALUES(?,?,?,?,?,?)";
    // Add new chain
    public static final String INSERT_NEW_CHAIN = "INSERT INTO hotelChain VALUES(?,?,?,?,?,?)";
    // Add new hotel
    public static final String INSERT_NEW_HOTEL = "INSERT INTO hotel VALUES(?,?,?,?,?,?,?)";
    // Add new Room
    public static final String INSERT_NEW_ROOM = "INSERT INTO room VALUES(?,?,?,?,?,?,?,?,?)";

    /* Update on existing data */

    // Modify Customer
    public static final String UPDATE_CUSTOMER = "UPDATE Customer";
    // Modify Employee
    public static final String UPDATE_EMPLOYEE = "UPDATE Employee";
    // Modify Hotel
    // Modify Room

    /* Deletion of existing data */

    // Delete Booking
    // Delete Renting
    // Delete Hotel
    // Delete Room
    // Delete Customer
    // Delete Employee

    /* Select Queries */

    // Customer-side
    public static final String FIND_CUSTOMER_VIA_CREDS = "SELECT * FROM Customer WHERE customer_email = ? AND password = ?";
    public static final String FIND_CUSTOMER_VIA_ID = "SELECT * FROM Customer WHERE customer_ID = ?";
    public static final String FIND_HOTEL_VIA_ID = "SELECT * FROM Hotel WHERE hotel_ID = ?";
    public static final String FIND_HOTEL_VIA_INFO = "SELECT * FROM Hotel WHERE";
    public static final String FIND_CHAIN_VIA_NAME = "SELECT * FROM HotelChain WHERE chain_name = ?";
    public static final String FIND_ROOM_VIA_INFO = "SELECT * FROM Room WHERE";
    public static final String FIND_BOOKING_VIA_ROOM = "SELECT * FROM Booking WHERE room_ID = ?";
    public static final String FIND_ALL_CHAIN_AS_NAME = "SELECT DISTINCT Chain_name FROM hotelchain";
    public static final String FIND_ALL_HOTEL_CATEGORY = "SELECT DISTINCT category FROM hotel";
    // Employee-side
    public static final String FIND_EMPLOYEE_VIA_CREDS = "SELECT * FROM Employee WHERE employee_email = ? AND password = ?";
    public static final String FIND_CUSTOMER_VIA_INFO = "SELECT * FROM Customer WHERE customer_name = ? AND customer_phone = ?";
    public static final String FIND_RENTING_VIA_ROOM = "SELECT * FROM Renting WHERE room_ID = ?";
    public static final String FIND_BOOKING_VIA_CUSTOMER = "SELECT * FROM Booking WHERE customer_ID = ?";
    public static final String FIND_HOTEL_ID_VIA_EMPLOYEE_ID = "SELECT hotel_ID FROM Employee WHERE employee_ID = ?";
    public static final String FIND_CUSTOMER_ID_VIA_NAME = "SELECT customer_ID FROM Customer WHERE customer_name = ?";
    public static final String FIND_EMPLOYEE_VIA_ID = "SELECT * FROM Employee WHERE employee_ID = ?";
    // Admin-side
    public static final String FIND_ADMIN_VIA_CREDS = "SELECT * FROM Admin WHERE admin_email = ? AND password = ?";
}
