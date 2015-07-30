package pl.jeeweb.zadanie23.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.jeeweb.zadanie23.entity.Address;
import pl.jeeweb.zadanie23.util.CRUDRunner;

public class EditAddrServlet extends HttpServlet {

    private String type, province, city, street, username;
    private int id, post1, post2, house_nr, flat_nr;
    private Address address;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getAddresData(request);
        updateAddress();
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private void getAddresData(HttpServletRequest request) {
        id = Integer.parseInt(request.getParameter("id"));
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
    
    private void updateAddress() {
        address = CRUDRunner.getAddress(id);
        address.setType(type);
        address.setProvince(province);
        address.setCity(city);
        address.setStreet(street);
        address.setPost1(post1);
        address.setPost2(post2);
        address.setHouse_nr(house_nr);
        address.setFlat_nr(flat_nr);
        CRUDRunner.updateAddress(address);
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
