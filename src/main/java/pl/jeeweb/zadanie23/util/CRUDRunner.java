package pl.jeeweb.zadanie23.util;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.jeeweb.zadanie23.entity.User;

public class CRUDRunner {

    private static User user;
    private static List allUsers;

    public static void create(String username, String password, String email) {
        user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setPrivilege("NORMAL");
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static List retrieve() {
        allUsers = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryResult = session.createQuery("from User");
            allUsers = queryResult.list();
            /*for (Object allUser : allUsers) {
                user = (User) allUser;
            }
            System.out.println("Database contents delivered...");*/
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
            return allUsers;
    }

    public static User retrieveFromUsername(String username) {
        user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();

            String queryString = "from User where username = :username";
            Query query = session.createQuery(queryString);
            query.setString("username", username);
            Object queryResult = query.uniqueResult();
            user = (User) queryResult;
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return user;
    }
    
    public static void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    /*public static void updateAll() {
     AnnotationConfiguration config
     = new AnnotationConfiguration();
     config.addAnnotatedClass(User.class);
     SessionFactory factory
     = config.configure().buildSessionFactory();
     Session session = factory.getCurrentSession();
     session.beginTransaction();
     List allUsers;
     System.out.println("Updating all records...");
     Query queryResult = session.createQuery("from User");
     allUsers = queryResult.list();
     System.out.println("# of rows: " + allUsers.size());
     for (int i = 0; i < allUsers.size(); i++) {
     User user = (User) allUsers.get(i);
     System.out.println(user);
     user.setPassword("password");
     session.update(user);
     }
     System.out.println("Database contents updated...");
     session.getTransaction().commit();
     }

     public static void deleteAll() {
     AnnotationConfiguration config = new AnnotationConfiguration();
     config.addAnnotatedClass(User.class);
     SessionFactory factory = config.configure().buildSessionFactory();
     Session session = factory.getCurrentSession();
     List allUsers;
     session.beginTransaction();
     Query queryResult = session.createQuery("from User");
     allUsers = queryResult.list();
     for (int i = 0; i < allUsers.size(); i++) {
     User user = (User) allUsers.get(i);
     System.out.println(user);
     session.delete(user);
     }
     session.getTransaction().commit();
     }
     }*/
}
