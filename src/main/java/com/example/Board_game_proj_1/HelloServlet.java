package com.example.Board_game_proj_1;

import dao.MechanicsDao;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        MechanicsDao mechDao = new MechanicsDao();
        mechDao.uploadFromAPI();

        //getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request,response);
    }

    public void destroy() {
    }
}