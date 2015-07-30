package pl.jeeweb.zadanie23.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import pl.jeeweb.zadanie23.entity.Address;
import pl.jeeweb.zadanie23.util.CRUDRunner;

public class AddAddrFilter implements Filter {

    private String type, username;
    private int post1, post2, house_nr, flat_nr;

    public AddAddrFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        getFormData((HttpServletRequest)request);
        if (isSecondType("zameldowania") || isSecondType("zamieszkania")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (!isValidNumbers()) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private void getFormData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        type = request.getParameter("type");
        post1 = Integer.parseInt(request.getParameter("post1"));
        post2 = Integer.parseInt(request.getParameter("post2"));
        house_nr = Integer.parseInt(request.getParameter("house_nr"));
        flat_nr = Integer.parseInt(request.getParameter("flat_nr"));
        username = (String)session.getAttribute("username");
    }

    private boolean isSecondType(String typ) {
        if (typ.equals(type)) {
            List<Address> addressList = CRUDRunner.retriveUserAddresses(username);
            int i = 0;
            for (Address a : addressList) {
                if (typ.equals(a.getType())) {
                    i++;
                }
            }
            return i > 0;
        } else {
            return false;
        }
    }

    private boolean isValidNumbers() {
        return ((post1 > 9 && post1 < 100) && (post2 > 99 && post2 < 1000)
                && (house_nr > 0 && house_nr < 1000) 
                && (flat_nr > 0 && flat_nr < 1000));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
