package com.gwtApp.client;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtApp.Entity.Users;


import java.util.Date;

public interface GwtAppServiceAsync {

    void userLogining (String login, String pass, AsyncCallback<Boolean> async);
    void getTimeMessage (String locale, Date currentTime, AsyncCallback<String> async);
    void getSessionAttribute(AsyncCallback<Users> async);
    void logout(AsyncCallback<Void> async);
    void initialDB(AsyncCallback<Boolean> async);


}
