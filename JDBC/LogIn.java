package JDBC;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LogIn extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        // Basic validation
        if (userId == null || password == null) {
            res.getWriter().print("Please provide UserId and Password.");
            return;
        }

        int index = userId.indexOf("@gmail.com");
        int special = 0, letters = 0, capital = 0;

        for (int i = 0; i < password.length(); i++) {
            letters++;
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') capital++;
            else if ((password.charAt(i) >= 'a' && password.charAt(i) <= 'z') || (password.charAt(i) >= '0' && password.charAt(i) <= '9')) continue;
            else special++;
        }

        if (index < 1 || special < 1 || letters < 8 || letters > 15 || capital < 1) {
            res.getWriter().print("Wrong UserId or Password!...");
            req.getRequestDispatcher("/sighnin.html").include(req, res); // Check if this file exists
        } else {
            // Perform database validation here
            // Sample response if validation is successful
            res.getWriter().println("<p>Login successful!</p>");
            req.getRequestDispatcher("newuser").forward(req, res);
        }
    }
}
