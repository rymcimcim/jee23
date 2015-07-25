package pl.jeeweb.zadanie23.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import pl.jeeweb.zadanie23.entity.User;
import pl.jeeweb.zadanie23.util.CRUDRunner;

public class PremiumPrivFilter implements Filter {

    private String premiumUsername;
    private User u;
    HttpServletResponse hr;

    public PremiumPrivFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        premiumUsername = null;
        premiumUsername = request.getParameter("premiumUsername");
        hr = null;
        hr = (HttpServletResponse) response;
        if (!isEmptyField()) {
            if (isUserExists()) {
                checkPrivilege(request);
            } else {
                request.getRequestDispatcher("/errors/premiumError.jsp").forward(request, response);
            }
        } else {
            hr.sendRedirect("/errors/premiumBadData.jsp");
        }
        chain.doFilter(request, response);
    }

    private boolean isEmptyField() throws ServletException, IOException {
        return ("".equals(premiumUsername) || premiumUsername == null);
    }

    private boolean isUserExists() {
        u = null;
        u = CRUDRunner.retrieveFromUsername(premiumUsername);
        return u != null;
    }

    private void checkPrivilege(ServletRequest request) throws IOException, ServletException {
        String privilege = u.getPrivilege();
        if ("NORMAL".equals(privilege)) {
            request.setAttribute("privilege", "PREMIUM");
        } else if ("PREMIUM".equals(privilege)) {
            request.setAttribute("privilege", "NORMAL");
        } else {
            hr.sendRedirect("login.jsp");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
