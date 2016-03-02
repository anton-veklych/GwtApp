package com.gwtApp.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtApp.Entity.User;
import com.gwtApp.client.GwtAppServiceAsync;
import com.gwtApp.client.event.LogOutEvent;

import java.util.Date;


public class MainPagePresenter implements Presenter{

    public  interface Display{
        HasClickHandlers exit();
        void setUserMessage(String msg);
        Widget asWidget();
    }

    private GwtAppServiceAsync gwtAppService;
    private HandlerManager eventBus;
    private Display display;

    public MainPagePresenter(GwtAppServiceAsync gwtAppService, HandlerManager eventBus, MainPagePresenter.Display display){
        this.gwtAppService = gwtAppService;
        this.eventBus = eventBus;
        this.display = display;
    }

    private void bind(){
        display.exit().addClickHandler(logoutClickHandler);
        setMsg();
    }

    public void go( HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
        bind();
    }

    User user;
    public void setMsg(){
        Date date = new Date();
        LocaleInfo locale = LocaleInfo.getCurrentLocale();
        String clientLocale = locale.getLocaleName();

        gwtAppService.getSessionAttribute(new AsyncCallback<User>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(User users) {
                user = users;
            }
        });

        gwtAppService.getTimeMessage(clientLocale, date, new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(String st) {
                display.setUserMessage(st + " " + user.getName() + "!");
            }
        });
    }

    ClickHandler logoutClickHandler = new ClickHandler() {
        public void onClick(ClickEvent evt) {
            gwtAppService.logout(new AsyncCallback<Void>() {
                public void onFailure(Throwable t) {
                }
                public void onSuccess(Void v) {
                    eventBus.fireEvent(new LogOutEvent());
                }
            });
        }
    };

}
