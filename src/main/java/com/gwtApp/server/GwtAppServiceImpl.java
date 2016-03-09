package com.gwtApp.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.gwtApp.Entity.User;
import com.gwtApp.client.GwtAppService;
import com.gwtApp.server.DAO.UserDAO;
import com.gwtApp.server.DAO.UserDAOImpl;
import org.mindrot.jbcrypt.BCrypt;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class GwtAppServiceImpl extends RemoteServiceServlet implements GwtAppService{
    private UserDAO userDAO;
    private List<User> usersList = new ArrayList();
    private HttpSession userSession = null;

    public GwtAppServiceImpl() {
        userDAO = new UserDAOImpl();
    }

   public void initialDB(){
       userDAO.initial();
   }


    public Boolean userLogining(User user) {
        usersList = userDAO.getUsersList(user);
        User u = usersList.get(0);
        String pw = user.getPassword()+u.getSalt();
        //Boolean b = BCrypt.checkpw(pw, u.getPassword());
        if(BCrypt.checkpw(pw, u.getPassword())){
            HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
            userSession = httpServletRequest.getSession();
            userSession.setAttribute("user", u);
            return true;
        }else return false;
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