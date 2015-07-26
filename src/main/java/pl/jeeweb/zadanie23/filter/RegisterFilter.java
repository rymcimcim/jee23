package pl.jeeweb.zadanie23.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pl.jeeweb.zadanie23.entity.User;
import pl.jeeweb.zadanie23.entity.UserBean;
import pl.jeeweb.zadanie23.util.HibernateUtil;

public class RegisterFilter implements Filter {

    private String email;
    private String password;
    private String cpassword;
    HttpServletResponse hsr;
    private UserBean ub;

    public RegisterFilter() {
    }

    private boolean checkEmailField(ServletRequest request) throws ServletException, IOException {
        email = request.getParameter("email");
        EmailValidator validator = EmailValidator.getInstance();
        boolean validEmail = validator.isValid(email);
        return validEmail;
    }

    private boolean isPasswordValid(ServletRequest request) throws IOException {
        password = request.getParameter("password");
        cpassword = request.getParameter("cpassword");
        return password.equals(cpassword);
    }

    private void checkIfUserExist(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String username = request.getParameter("username");
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username));
            User user = (User) criteria.uniqueResult();
            if (user != null) {
                ub = null;
                request.setAttribute("userBean", ub);
                request.getRequestDispatcher("regErrors/regError.jsp").forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        if (!checkEmailField(request)) {
            ub = null;
            request.setAttribute("userBean", ub);
            request.getRequestDispatcher("regErrors/emailError.jsp").forward(request, response);
        } else {
            if (isPasswordValid(request)) {
                checkIfUserExist(request, response, chain);
            } else {
                ub = null;
                request.setAttribute("userBean", ub);
                request.getRequestDispatcher("regErrors/passError.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
