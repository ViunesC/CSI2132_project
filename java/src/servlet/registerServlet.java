package servlet;

import database.UserDB;
import entity.Customer;
import entity.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(urlPatterns = "/register")
public class registerServlet extends HttpServlet {
    private UserDB userDB;
    @Override
    public void init() throws ServletException {
        userDB = new UserDB();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);
        String role = req.getParameter("user_role");
        String name = req.getParameter("user_name");
        String email = req.getParameter("user_email");
        String phone_num = req.getParameter("user_phone");
        String ssn = req.getParameter("user_ssn");
        String address = req.getParameter("user_address");
        String password = req.getParameter("user_pw");

        System.out.printf("Form received. Detail info: Role(%s), Name(%s), Email(%s)\n", role, name, email);
        System.out.printf("...Phone(%s), SSN(%s), Address(%s), Password(%s)\n", phone_num, ssn, address, password);

        if (registerUser(role, name, email, phone_num, ssn, password, address)) {
            System.out.println("Registration success! Redirecting user to home page...");
            resp.sendRedirect("index.html");
        } else {
            System.out.println("Registration failed!");
        }
    }

    @Override
    public void destroy() {
        userDB.close();
    }

    private boolean registerUser(String role, String name, String email, String phone, String ssn, String password, String addr) {
        String fname = name.trim().split(" ")[0];
        String lname = name.trim().split(" ")[1];
        if (role.equals("Customer")) {
            Customer customer = new Customer(fname, lname, email, phone, ssn, password, addr);
            return userDB.insertToDB(customer);
        }

        Employee employee = new Employee(fname, lname, email, phone, ssn, password, "trainee",-1,"no_position");
        return userDB.insertToDB(employee);
    }
}
