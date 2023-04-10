package servlet;

import database.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/info")
public class infoServlet extends HttpServlet {
    private UserDB userDB;

    @Override
    public void init() throws ServletException {
        userDB = new UserDB();
    }

    @Override
    public void destroy() {
        userDB.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String req_type = req.getParameter("req_type");

        if (req_type.equals("modify_customer_info")) {
            int id = Integer.parseInt(req.getParameter("customer_id"));
            String name = req.getParameter("customer_name");
            String address = req.getParameter("customer_address");
            String phone = req.getParameter("customer_phone");
            String email = req.getParameter("customer_email");
            String ssn = req.getParameter("customer_ssn");
            String password = req.getParameter("customer_pw");

            System.out.printf("Customer (id: %d) is attempting to modify info\n", id);

            if (updateCustomer(id, name, address, phone, email, ssn, password)) {
                System.out.println("Update successful! Redirect user to login page");
                resp.sendRedirect("index.html");
            }
        }

        if (req_type.equals("modify_employee_info")) {
            int id = Integer.parseInt(req.getParameter("employee_id"));
            String name = req.getParameter("employee_name");
            String role = req.getParameter("employee_role");
            String position = req.getParameter("employee_pos");
            String email = req.getParameter("employee_email");
            String ssn = req.getParameter("employee_ssn");
            String password = req.getParameter("employee_pw");

            System.out.printf("Employee (id: %d) is attempting to modify info\n", id);

            if (updateEmployee(id, name, role, position, email, ssn, password)) {
                System.out.println("Update successful! Redirect user to login page");
                resp.sendRedirect("index.html");
            }
        }
    }

    private boolean updateCustomer(int id, String name, String address, String phone, String email, String password, String ssn) {
        return userDB.modifyCustomer(id, name, address, phone, email, password, ssn);
    }

    private boolean updateEmployee(int id, String name, String role, String position, String email, String ssn, String password) {
        return userDB.modifyEmployee(id, name, role, position, email, ssn, password);
    }
}
