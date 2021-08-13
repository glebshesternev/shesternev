package com.shesternev.jsp.servlet;

import com.shesternev.jsp.model.User;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private User user;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        user = new User("test", "test", "test");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setAttribute("user", user);
        if ("update".equals(action)) {
            request.getRequestDispatcher("views/update.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("views/user.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("submit".equals(action)) {
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setPassword(request.getParameter("password"));
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("views/user.jsp").forward(request, response);
    }
}
