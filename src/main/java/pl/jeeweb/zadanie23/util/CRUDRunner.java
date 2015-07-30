package pl.jeeweb.zadanie23.util;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pl.jeeweb.zadanie23.entity.Address;
import pl.jeeweb.zadanie23.entity.User;

public class CRUDRunner {

    private static User user;
    private static Address address;
    private static List allUsers;
    private static List addressList;

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
            tx = session.beginTransaction();
            String queryString = "from User where username = :username";
            Query query = session.createQuery(queryString);
            query.setString("username", username);
            Object queryResult = query.uniqueResult();
            user = (User) queryResult;
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return user;
    }

    public static void createAddress(String username, String type, String province, String city,
            int post1, int post2, String street, int house_nr, int flat_nr) {
        user = null;
        user = (User) retrieveFromUsername(username);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            address = new Address();
            address.setUser(user);
            address.setType(type);
            address.setProvince(province);
            address.setCity(city);
            address.setPost1(post1);
            address.setPost2(post2);
            address.setStreet(street);
            address.setHouse_nr(house_nr);
            address.setFlat_nr(flat_nr);
            session.save(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
    
    public static Address getAddress(int id) {
        address = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Address.class);
            criteria.add(Restrictions.eq("addr_id", id));
            address = (Address)criteria.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return address;
    }
    
    public static void deleteAddress(int i) {
        address = null;
        address = getAddress(i);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
             tx = session.beginTransaction();
             session.delete(address);
             tx.commit();
         } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static List retriveUserAddresses(String username) {
        addressList = null;
        user = null;
        user = (User) retrieveFromUsername(username);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Address.class);
            criteria.add(Restrictions.eq("user", user));
            addressList = criteria.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return addressList;
    }

    public static void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
    
    public static void updateAddress(Address address) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(address);
            tx.commit();
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
