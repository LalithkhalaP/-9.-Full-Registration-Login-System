package user;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
      Connection con;

    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb", "root", "lalli@2007"
            );
        } catch (Exception e) {
            throw new ServletException("DB Connection error: " + e.getMessage());
        }
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String action = req.getParameter("action");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            if ("register".equals(action)) {
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username, password) VALUES(?, ?)"
                );
                ps.setString(1, username);
                ps.setString(2, password);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    out.println("Registration Successful!<br>");
                    out.println("<a href='login.html'>Go to Login</a>");
                } else {
                    out.println("Registration Failed!<br>");
                    out.println("<a href='register.html'>Try Again</a>");
                }
                ps.close();

            } else if ("login".equals(action)) {
                PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?"
                );
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    out.println("Login Successful! Welcome " + username + "<br>");
                    out.println("<a href='home.html'>Go to Home</a>");
                } else {
                    out.println("Invalid Username or Password<br>");
                    out.println("<a href='login.html'>Try Again</a>");
                }
                rs.close();
                ps.close();
            } else {
                out.println("No action specified!<br>");
                out.println("<a href='register.html'>Register</a> | <a href='login.html'>Login</a>");
            }
        } catch (SQLException e) {
            out.println("Error: " + e.getMessage());
        }
    }


    public void destroy() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
