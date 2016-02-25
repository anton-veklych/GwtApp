package com.gwtApp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.gwtApp.client.login.LoginViewImpl;




public class GwtApp implements EntryPoint {

    public void onModuleLoad() {
        final GwtAppServiceAsync rpcService = GWT.create(GwtAppService.class);
        rpcService.initialDB(new AsyncCallback<Boolean>() {
            public void onFailure(Throwable caught) {
                Window.alert("Data Bases not created");
            }
            public void onSuccess(Boolean b) {
               if(b == false)
                   Window.alert("Data Bases not created");
            }
            }

            );

            final LoginViewImpl loginView = new LoginViewImpl();

            RootPanel.get("content").

            add(loginView);

        }
    }