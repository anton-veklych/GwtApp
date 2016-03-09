package com.gwtApp.server.DAO;

import com.gwtApp.Entity.User;
import com.gwtApp.server.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;
    private Session session;
    private List<User> usersList = new ArrayList();

    public UserDAOImpl(){
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void initial(){
        Query query =  session.createSQLQuery("INSERT INTO Users (login, password, name, salt) VALUES\n" +
                "('ivan', '$2a$10$WCayMuaNzX12BUPHj50vCuzjcmM94gshna8OQnB2sR7voIRf/5mj.', 'Иван', 'salt1'),\n" +
                "('john', '$2a$10$3E/IrnB07CEruNXL52.47u9nXLvKyOSqZLSposk/x5Y7KFlDqEWsS', 'John', 'salt2')\n");
        int result = query.executeUpdate();
    }

    public List<User> getUsersList(User user) {
        Query query =  session.createQuery("from User where login = :login ");
        query.setParameter("login", user.getLogin());
        usersList = query.list();
        return usersList;
    }
}
