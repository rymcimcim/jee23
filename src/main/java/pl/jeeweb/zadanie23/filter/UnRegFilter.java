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
import javax.servlet.http.HttpServletResponse;
import pl.jeeweb.zadanie23.entity.UserBean;

public class UnRegFilter implements Filter {

    public UnRegFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rp = (HttpServletResponse) response;
        Cookie[] cookies = rq.getCookies();
        if (cookies == null) {
            UserBean ub = (UserBean) request.getAttribute("userBean");
            if (ub == null) {
                chain.doFilter(request, response);
            } else
                rp.sendRedirect("register.jsp");
        } else
            rp.sendRedirect("../login.jsp");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
