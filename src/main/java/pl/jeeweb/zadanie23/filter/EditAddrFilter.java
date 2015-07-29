package pl.jeeweb.zadanie23.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import pl.jeeweb.zadanie23.entity.Address;
import pl.jeeweb.zadanie23.entity.User;
import pl.jeeweb.zadanie23.util.CRUDRunner;

public class EditAddrFilter implements Filter {
    
    private int id;
    private Cookie[] cookies;
    
    public EditAddrFilter() {
    }     
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        getAddresData((HttpServletRequest) request);
        if (isValidAddress()) {
            chain.doFilter(request, response);
        } else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    private void getAddresData(HttpServletRequest request) {
        id = Integer.parseInt(request.getParameter("id"));
        cookies = request.getCookies();
    }
    
    private boolean isValidAddress() {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    String username = c.getValue();
                    User user = CRUDRunner.retrieveFromUsername(username);
                    Address addr = CRUDRunner.getAddress(id);
                    return addr.getUser().getId() == user.getId();
                }
            }
        }
        return false;
    }

    @Override
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
   
}