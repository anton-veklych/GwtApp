package com.gwtApp.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.gwtApp.Entity.User;
import com.gwtApp.client.GwtAppService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class GwtAppServiceImpl extends RemoteServiceServlet implements GwtAppService{
    private SessionFactory sessionFactory;
    private Session session;
    private List<User> usersList = new ArrayList();
    private HttpSession userSession;

    public GwtAppServiceImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public Boolean initialDB() {
        Query query =  session.createSQLQuery("INSERT INTO Users (login, password, name, salt) VALUES\n" +
                "('ivan', '$2a$10$WCayMuaNzX12BUPHj50vCuzjcmM94gshna8OQnB2sR7voIRf/5mj.', 'Иван', 'salt1'),\n" +
                "('john', '$2a$10$3E/IrnB07CEruNXL52.47u9nXLvKyOSqZLSposk/x5Y7KFlDqEWsS', 'John', 'salt2')\n");
        int result = query.executeUpdate();
         query =  session.createQuery("from User");
        usersList = query.list();
        User user = usersList.get(0);
        if(user == null){
            return false;
        } else {
            return true;
        }
    }


    public Boolean userLogining(User user) {
        Query query =  session.createQuery("from User where login = :login ");
        query.setParameter("login", user.getLogin());
        usersList = query.list();
        User u = usersList.get(0);
        String pw = user.getPassword()+u.getSalt();
        //String hashpass = BCrypt.hashpw(pw, BCrypt.gensalt());
        Boolean b = BCrypt.checkpw(pw, u.getPassword());
        if(b==true){
            HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
            userSession = httpServletRequest.getSession();
            userSession.setAttribute("user", u);
        }
        return b;
    }

    public String getTimeMessage(String locale, Date currentTime){
        TimeIntervals timeIntervals = new TimeIntervals();
        Locale loc = new Locale(locale);
        Message message = new Message();
        ResourceBundle resourceBundle = message.showResource(loc);
        String st = resourceBundle.getString(message.showMessage(timeIntervals, currentTime));
        return st;
    }

    public User getSessionAttribute(){
        User user = (User)userSession.getAttribute("user");
        return user;
    }

    public void logout() {
        HttpServletRequest request = this.getThreadLocalRequest();
        HttpSession session = request.getSession(false);
        if (session == null)
            return;
        session.invalidate();
    }
}