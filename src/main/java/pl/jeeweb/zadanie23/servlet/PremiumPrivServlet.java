package pl.jeeweb.zadanie23.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.jeeweb.zadanie23.entity.User;
import pl.jeeweb.zadanie23.util.CRUDRunner;

public class PremiumPrivServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        grantTakePremium(request);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    private void grantTakePremium(HttpServletRequest request) throws ServletException, IOException {
        String premiumUsername = request.getParameter("premiumUsername");
        String privilege = (String)request.getAttribute("privilege");
        User user = CRUDRunner.retrieveFromUsername(premiumUsername);
        user.setPrivilege(privilege);
        CRUDRunner.updateUser(user);
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
