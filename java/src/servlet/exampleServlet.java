package servlet;

import database.testDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/welcome")
public class exampleServlet extends HttpServlet {
    private testDB database;

    @Override
    public void init() throws ServletException {
        database = new testDB();
        System.out.println("[Message] Servlet initialized!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[Alert] New access to servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =  Integer.parseInt(req.getParameter("uid"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        int sex = Integer.parseInt(req.getParameter("sex"));

        database.insert(id,name,age,sex);
    }

    @Override
    public void destroy() {
        System.out.println("[Message] Servlet terminated!");
        database.closeAll();
    }
}
