package pl.jeeweb.zadanie23.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.jeeweb.zadanie23.util.CRUDRunner;

public class AddAddrServlet extends HttpServlet {

    private String type, province, city, street, username;
    private int post1, post2, house_nr, flat_nr;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getFormData(request);
        addAddress(request, response);
    }
    
    private void getFormData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        type = request.getParameter("type");
        province = request.getParameter("province");
        city = request.getParameter("city");
        street = request.getParameter("street");
        post1 =  Integer.parseInt(request.getParameter("post1"));
        post2 =  Integer.parseInt(request.getParameter("post2"));
        house_nr =  Integer.parseInt(request.getParameter("house_nr"));
        flat_nr =  Integer.parseInt(request.getParameter("flat_nr"));
        username = (String)session.getAttribute("username");
    }

    private void addAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CRUDRunner.createAddress(username, type, province, city, post1, post2, street, house_nr, flat_nr);
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
