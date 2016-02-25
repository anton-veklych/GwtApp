package com.gwtApp.client;



import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.gwtApp.Entity.Users;


import java.util.Date;




@RemoteServiceRelativePath("GwtAppService")
public interface GwtAppService extends RemoteService {


    Users getSessionAttribute();
    Boolean userLogining(String login, String pass);
    Boolean initialDB();
    String getTimeMessage(String locale, Date currentTime);
    void logout();

}
