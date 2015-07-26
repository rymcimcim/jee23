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
import pl.jeeweb.zadanie23.entity.UserBean;
import pl.jeeweb.zadanie23.util.CRUDRunner;

public class LoginFilter implements Filter {

    private String username;
    private String password;

    public LoginFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        getLoginData(request, response);
        checkLoginFields(request, response);
        checkPassword(request, response, chain);
    }

    private void getLoginData(ServletRequest request, ServletResponse response) {
        username = request.getParameter("username");
        password = request.getParameter("password");
    }

    private void checkLoginFields(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        if ("".equals(username) || username == null || "".equals(password) || password == null) {
            HttpServletResponse hsr = (HttpServletResponse) response;
            hsr.sendRedirect("./errors/loginBadData.jsp");
        }
    }

    private void checkPassword(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        User u = CRUDRunner.retrieveFromUsername(username);
        if (!u.getPassword().equals(password)) {
            UserBean ub = null;
            request.setAttribute("userBean", ub);
            request.getRequestDispatcher("./regErrors/loginError.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
