package pl.jeeweb.zadanie23.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        clearLoginData(request, response);
    }

    private void clearLoginData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie killUsername = new Cookie("username", null);
        killUsername.setMaxAge(0);
        killUsername.setValue("");
        response.addCookie(killUsername);
        Cookie killPassword = new Cookie("password", null);
        killPassword.setMaxAge(0);
        killPassword.setValue("");
        response.addCookie(killPassword);
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
