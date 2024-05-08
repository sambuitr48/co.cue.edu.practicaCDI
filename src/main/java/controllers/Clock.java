package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Works by giving the current time using the LocalTime class system and creates a DateTimeFormatter
//Object to format the time in a specific format ("hh:mm:ss")
//**Public Access**/
@WebServlet({"/clock.json"})
public class Clock extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        resp.setHeader("refresh", "1");
        LocalTime hour = LocalTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");
        try (PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset =\"UTF-8\">");
            out.println("<title>The updated time</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>The updated time!</h1>");
            out.println("<h3>" + hour.format(df) + "</h3>");
            out.println("</body>");
            out.println("/html");
        }
    }
}
