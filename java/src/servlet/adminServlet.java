package servlet;

import database.HotelDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/adminMod")
public class adminServlet extends HttpServlet {
    private HotelDB hotelDB;

    @Override
    public void init() throws ServletException {
        hotelDB = new HotelDB();
    }

    @Override
    public void destroy() {
        hotelDB.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String req_type = req.getParameter("req_type");

        switch (req_type) {
            case "updateChain":
                System.out.println("Admin request: Update hotel chain");
                if (updateChain(req)) {
                    System.out.println("Hotel chain have been updated. Redirect user to result");
                }
                break;
            case "updateHotel":
                System.out.println("Admin request: Update hotel");
                if (updateHotel(req)) {
                    System.out.println("Hotel have been updated. Redirect user to result");
                }
                break;
            case "updateRoom":
                System.out.println("Admin request: Update room");
                if (updateRoom(req)) {
                    System.out.println("Room have been updated. Redirect user to result");
                }
                break;
        }

        resp.sendRedirect("/database_app/Home/UpdateConfirm_page.html");
    }

    private boolean updateChain(HttpServletRequest req) {
        String id = req.getParameter("chainID");
        String name = req.getParameter("chainName");
        String email = req.getParameter("chainEmail");
        String address = req.getParameter("chainAddr");
        String phone = req.getParameter("chainPhone");
        String num_hotels = req.getParameter("numberHotels");

        return hotelDB.updateDB(id, name, email, address, phone, num_hotels);
    }

    private boolean updateHotel(HttpServletRequest req) {
        String chain_id = req.getParameter("chainID");
        String hotel_id = req.getParameter("hotelID");
        String name = req.getParameter("hotelName");
        String email = req.getParameter("hotelEmail");
        String address = req.getParameter("hotelAddr");
        String category = req.getParameter("category");
        String area = req.getParameter("area");

        return hotelDB.updateDB(chain_id, hotel_id, name, email, address, category, area);
    }

    private boolean updateRoom(HttpServletRequest req) {
        String room_id = req.getParameter("roomID");
        String hotel_id = req.getParameter("hotelID");
        String type = req.getParameter("type");
        String capacity = req.getParameter("capacity");
        String amenities = req.getParameter("amenities");
        String price = req.getParameter("price");
        String view = req.getParameter("view");
        String CBE = req.getParameter("extend");
        String problems = req.getParameter("problems");

        return hotelDB.updateDB(room_id, hotel_id, type, capacity, amenities, price, view, CBE, problems);
    }
}
