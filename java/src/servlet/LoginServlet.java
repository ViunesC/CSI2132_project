package servlet;

import database.HotelDB;
import database.UserDB;
import entity.Utility.AccountPackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDB userDB;
    private HotelDB hotelDB;

    @Override
    public void init() throws ServletException {
        userDB = new UserDB();
        hotelDB = new HotelDB();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("user_role");
        String email = req.getParameter("user_email");
        String password = req.getParameter("user_pw");

        System.out.printf("User: Email(%s), Password(%s) is attempting to login as (%s)\n", email, password, role);

        int FLAG;
        if (role.equals("Customer")) {
            FLAG = 1;
        } else if (role.equals("Employee")) {
            FLAG = 0;
        } else {
            FLAG = -1;
        }

        AccountPackage account = login(email,password, FLAG);
        if (account != null) {
            HttpSession session = req.getSession();
            session.setAttribute("userInfo", account);
            if (FLAG == 1) {
                session.setAttribute("ChainList", getInfoList(0));
                session.setAttribute("CategoryList", getInfoList(1));
                resp.sendRedirect("customer");
            } else if (FLAG == 0) {
                resp.sendRedirect("employee");
            } else {
                resp.sendRedirect("admin");
            }
        } else {
            System.out.println("An error has occurred during login.");
        }

    }

    @Override
    public void destroy() {
        hotelDB.close();
        userDB.close();
    }

    private AccountPackage login(String email, String password, int FLAG) {
        try {
            return userDB.findUserInDB(email, password, FLAG);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<String> getInfoList(int flag) {
        List<String> list =  hotelDB.getHotelInfo_list(flag);
        System.out.printf("List retrieved! Sample info: %s, %s\n", list.get(new Random().nextInt(list.size())), list.get(new Random().nextInt(list.size())));
        return list;
    }
}
