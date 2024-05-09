package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dto.ToyDTO;
import services.impl.toy.ToyServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/toy.json")
public class ToyJSON extends HttpServlet {
    public ToyServiceImpl service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        ToyDTO toy = mapper.readValue(jsonStream, ToyDTO.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Detalle de producto desde json</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Detalle de producto desde json!</h1>");
            out.println("<ul>");
            out.println("<li>Id: " + toy.toy_id() + "</li>");
            out.println("<li>Name: " + toy.toy_name() + "</li>");
            out.println("<li>Type: " + toy.toy_category() + "</li>");
            out.println("<li>Price: " + toy.toy_price() + "</li>");
            out.println("<li>Stock: " + toy.toy_stock() + "</li>");
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        ToyDTO toyDTO = mapper.readValue(JsonStream,
                ToyDTO.class);
        int id = toyDTO.toy_id();
        try {
            service.search(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        try {
            out.println(service.search(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("</body></html>");
    }
}
