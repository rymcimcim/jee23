package pl.jeeweb.zadanie23.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.jeeweb.zadanie23.util.CRUDRunner;

public class RegisterServlet extends HttpServlet {

    private String username;
    private String password;
    private String email;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getUserData(request, response);
        RegisterServlet RS = new RegisterServlet();
        RS.addUser(username, password, email);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void getUserData(HttpServletRequest request, HttpServletResponse response) {
        username = request.getParameter("username");
        password = request.getParameter("password");
        email = request.getParameter("email");
    }

    public void addUser(String username, String password, String email) {
        CRUDRunner.create(username, password, email);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
