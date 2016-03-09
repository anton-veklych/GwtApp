package com.gwtApp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.gwtApp.server.GwtAppServiceImpl;


public class GwtApp implements EntryPoint {

    public void onModuleLoad() {
        GwtAppServiceAsync gwtAppService = GWT.create(GwtAppService.class);
        gwtAppService.initialDB(new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                Window.alert("Database not created!!!");
            }
            public void onSuccess(Void result) {}
        });
        HandlerManager eventBus = new HandlerManager(null);
           GwtAppController gwtAppController = new GwtAppController(eventBus);
        gwtAppController.go(RootPanel.get("content"));

        }
    }