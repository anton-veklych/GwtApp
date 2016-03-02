package com.gwtApp.client;



import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.gwtApp.Entity.User;


import java.util.Date;




@RemoteServiceRelativePath("GwtAppService")
public interface GwtAppService extends RemoteService {


    User getSessionAttribute();
    Boolean userLogining(User user);
    Boolean initialDB();
    String getTimeMessage(String locale, Date currentTime);
    void logout();

}
