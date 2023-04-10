package servlet;

import database.HotelDB;
import database.OrderDB;
import database.UserDB;
import entity.*;
import entity.Utility.AccountPackage;
import entity.Utility.RoomPackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class homeServlet extends HttpServlet {
    private final int FLAG_EMPLOYEE = 1;
    private UserDB userDB;
    private HotelDB hotelDB;
    private OrderDB orderDB;

    @Override
    public void init() throws ServletException {
        userDB = new UserDB();
        hotelDB = new HotelDB();
        orderDB = new OrderDB();
    }

    @Override
    public void destroy() {
        userDB.close();
        hotelDB.close();
        orderDB.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String req_type = req.getParameter("req_type");
        HttpSession session = req.getSession();

        switch(req_type) {
            case "Search_for_room":
                System.out.println("Request received: Searching for rooms");
                if (searchForRoom(req)) {
                    System.out.println("Search complete. Redirecting user to result");
                    resp.sendRedirect("customer_result");
                }
                break;
            case "Modify_info":
                System.out.println("Request received. Pulling user's info from database");
                try {
                    int id = ((AccountPackage) session.getAttribute("userInfo")).getId();
                    System.out.printf("User id: %d\n", id);
                    Customer customer = searchCustomer(id);
                    session.setAttribute("user_profile", customer);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("Pull complete. Redirecting user to profile");
                resp.sendRedirect("customer_info");
                break;
            case "Modify_info_employee":
                System.out.println("Request received. Pulling user's info from database");
                try {
                    int id = ((AccountPackage) session.getAttribute("userInfo")).getId();
                    System.out.printf("User id: %d\n", id);
                    session.setAttribute("user_profile", searchEmployee(id));
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("Pull complete. Redirecting user to profile");
                resp.sendRedirect("employee_info");
                break;
            case "Search_for_booking":
                System.out.println("Request received: Search for bookings");
                if (searchBooking(req)) {
                    System.out.println("Booking retrieved. Redirect user to result");
                    resp.sendRedirect("employee_result");
                }
                break;
            case "Search_for_room_employee":
                System.out.println("Request received: Search for available rooms");
                Date start = Date.valueOf(req.getParameter("startD"));
                Date end = Date.valueOf(req.getParameter("endD"));
                String capacity = req.getParameter("room_cap");
                String priceRange = req.getParameter("price");
                int employee_id = ((AccountPackage) session.getAttribute("userInfo")).getId();
                List<Room> rooms = searchForRoom(employee_id, start, end, capacity, priceRange);

                session.setAttribute("available_room_list", rooms);
                session.setAttribute("start_date_str", start.toString());
                session.setAttribute("end_date_str", end.toString());
                System.out.println("Room retrieved. Redirect user to result");
                resp.sendRedirect("employee_rent_result");
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private boolean searchForRoom(HttpServletRequest req) {
        String area = req.getParameter("room_area");
        String chain = req.getParameter("chain");
        String category = req.getParameter("category");
        String startD_str = req.getParameter("startD");
        String endD_str = req.getParameter("endD");
        Date start_date = Date.valueOf(startD_str);
        Date end_date = Date.valueOf(endD_str);
        String capacityStr = req.getParameter("room_cap");
        String priceRange = req.getParameter("price");
        int capacity;

        if (capacityStr.equals("")) {
            capacity = -1;
        } else {
            capacity = Integer.parseInt(capacityStr);
        }

        List<Hotel> hotel_list = searchHotel(area, chain, category);
        if (hotel_list.isEmpty()) {
            throw new UnsupportedOperationException("Empty hotel result, try again");
        }

        List<Room> temp;
        List<RoomPackage> available_rooms = new ArrayList<>();

        try {
            for (Hotel hotel : hotel_list) {
                temp = hotelDB.findRoom(hotel, capacity, priceRange);
                for (Room room : temp) {
                    if (orderDB.isAvailable(room.getId(), start_date, end_date)) {
                        available_rooms.add(RoomPackage.createPackage(room, hotel));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        HttpSession session = req.getSession();
        session.setAttribute("room_list", available_rooms);
        session.setAttribute("start_date_str", startD_str);
        session.setAttribute("end_date_str", endD_str);
        return true;
    }

    private List<Room> searchForRoom(int employee_id, Date start, Date end, String capacity, String priceRange) {

        List<Room> rooms;
        List<Room> available_rooms = new ArrayList<>();
        int cap;

        if (capacity.equals("")) {
            cap = -1;
        } else {
            cap = Integer.parseInt(capacity);
        }

        try {
            int Hotel_id = userDB.findHotelID(employee_id);

            rooms = hotelDB.findRoom(hotelDB.findHotel(Hotel_id), cap, priceRange);
            for (Room room : rooms) {
                if (orderDB.isAvailable(room.getId(), start, end)) {
                    available_rooms.add(room);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return available_rooms;
    }

    private List<Hotel> searchHotel(String area, String hotel_chain, String category) {
        if (area == null || area.equals("")) {
            throw new IllegalArgumentException("Area cannot be null");
        }

        if (!hotel_chain.equals("")) {
            return hotelDB.findHotel(hotelDB.findChain(hotel_chain).getChain_id(), category, area);
        }

        return hotelDB.findHotel(-1, category, area);
    }

    private Customer searchCustomer(int id) {
        return userDB.findUserInDB(id);
    }

    private Employee searchEmployee(int id) {
        return userDB.findUserInDB(id, FLAG_EMPLOYEE);
    }

    private boolean searchBooking(HttpServletRequest req) {
        String name = req.getParameter("customer_name");
        String phone = req.getParameter("customer_phone");

        AccountPackage account = userDB.findUserInDB(name, phone);
        List<Order> order_list = orderDB.findOrder(account.getId());

        if (order_list.isEmpty()) {
            return false;
        }

        HttpSession session = req.getSession();
        session.setAttribute("order_list", order_list);
        return true;
    }
}
