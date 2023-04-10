package servlet;

import database.OrderDB;
import database.UserDB;
import entity.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

@WebServlet(urlPatterns = "/search")
public class searchServlet extends HttpServlet {
    private OrderDB orderDB;
    private UserDB userDB;

    @Override
    public void init() throws ServletException {
        orderDB = new OrderDB();
        userDB = new UserDB();
    }

    @Override
    public void destroy() {
        userDB.close();
        orderDB.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String req_type = req.getParameter("req_type");

        if (req_type.equals("Book_room")) {
            Date start = Date.valueOf(req.getParameter("startD"));
            Date end = Date.valueOf(req.getParameter("endD"));
            String room_id = req.getParameter("room_id");
            int customer_id = Integer.parseInt(req.getParameter("customer_id"));

            if (bookRoom(room_id, customer_id, start, end)) {
                System.out.println("Booking confirmed! Redirect user to home page");
                resp.sendRedirect("/database_app/Home/BookingConfirm_page.html");
            }
        }

        if (req_type.equals("Rent_room")) {
            Date start = Date.valueOf(req.getParameter("startD"));
            Date end = Date.valueOf(req.getParameter("endD"));
            String room_id = req.getParameter("room_id");
            int customer_id = Integer.parseInt(req.getParameter("customer_id"));
            int employee_id = Integer.parseInt(req.getParameter("employee_id"));

            if (rentRoom(room_id, customer_id, employee_id, start, end)) {
                System.out.println("Renting confirmed! Redirect user to home page");
                resp.sendRedirect("/database_app/Home/RentingConfirm_page.html");
            }
        }

        if (req_type.equals("Rent_room_with_name")) {
            Date start = Date.valueOf(req.getParameter("startD"));
            Date end = Date.valueOf(req.getParameter("endD"));
            String room_id = req.getParameter("room_id");
            int customer_id = userDB.findUserByName(req.getParameter("customer_name"));
            int employee_id = Integer.parseInt(req.getParameter("employee_id"));

            if (rentRoom(room_id, customer_id, employee_id, start, end)) {
                System.out.println("Renting confirmed! Redirect user to home page");
                resp.sendRedirect("/database_app/Home/RentingConfirm_page.html");
            }
        }
    }

    private boolean bookRoom(String roomID, int customerID, Date start, Date end) {
        String booking_id = String.valueOf(new Random().nextInt(1000000));
        return orderDB.insertToDB(new Order(booking_id, roomID, customerID, start, end));
    }

    private boolean rentRoom(String roomID, int customerID, int employeeID, Date start, Date end) {
        String booking_id = String.valueOf(new Random().nextInt(1000000));
        return orderDB.insertToDB(new Order(booking_id, roomID, customerID, employeeID, start, end));
    }
}
