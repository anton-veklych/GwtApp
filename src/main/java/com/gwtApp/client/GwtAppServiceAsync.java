package com.gwtApp.client;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtApp.Entity.User;


import java.util.Date;

public interface GwtAppServiceAsync {

    void userLogining (User user, AsyncCallback<Boolean> async);
    void getTimeMessage (String locale, Date currentTime, AsyncCallback<String> async);
    void getSessionAttribute(AsyncCallback<User> async);
    void logout(AsyncCallback<Void> async);
    void initialDB(AsyncCallback<Boolean> async);


}
