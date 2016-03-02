package com.gwtApp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;





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


        HandlerManager eventBus = new HandlerManager(null);
           GwtAppController gwtAppController = new GwtAppController(eventBus);
        gwtAppController.go(RootPanel.get("content"));

        }
    }