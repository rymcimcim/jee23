package pl.jeeweb.zadanie23.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pl.jeeweb.zadanie23.entity.User;
import pl.jeeweb.zadanie23.entity.UserBean;
import pl.jeeweb.zadanie23.util.HibernateUtil;

public class LoginServlet extends HttpServlet {

    private String username;
    private String password;
    private User user;
    private UserBean userBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getUserData(request, response);
        checkData(request, response);
    }

    private void checkData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (user != null) {
            Cookie un = new Cookie("username", user.getUsername());
            un.setMaxAge(60 * 5);
            response.addCookie(un);
            Cookie psw = new Cookie("password", password);
            psw.setMaxAge(60 * 5);
            response.addCookie(psw);
            userBean = new UserBean();
            userBean.setUsername(user.getUsername());
            userBean.setId(user.getId());
            userBean.setEmail(user.getEmail());
            userBean.setPrivilege(user.getPrivilege());
            userBean.setDescription();
            request.setAttribute("userBean", userBean);
            String page = request.getParameter("page");
            if (page != null) {
                request.getRequestDispatcher(page).forward(request, response);
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("/errors/loginError.jsp");
        }
    }

    private void getUserData(HttpServletRequest request, HttpServletResponse response) {

        username = request.getParameter("username");
        password = request.getParameter("password");
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username));
            criteria.add(Restrictions.eq("password", password));

            user = (User) criteria.uniqueResult();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
