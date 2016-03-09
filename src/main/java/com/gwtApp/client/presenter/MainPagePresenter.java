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
    private User user;
    private Date date = new Date();
    private String locale;

    public MainPagePresenter(GwtAppServiceAsync gwtAppService, HandlerManager eventBus, MainPagePresenter.Display display, String locale){
        this.gwtAppService = gwtAppService;
        this.eventBus = eventBus;
        this.display = display;
        this.locale = locale;

    }

    private void bind() {
        display.exit().addClickHandler(logoutClickHandler);
        setMsg(locale);
    }

    public void go( HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
        bind();
    }


    public void setMsg(String locale){
        gwtAppService.getSessionAttribute(userCallback);
        gwtAppService.getTimeMessage(locale, date, messageCallback);
    }


    public AsyncCallback<User> userCallback = new AsyncCallback<User>() {
        public void onFailure(Throwable caught) {
        }
        public void onSuccess(User users) {
            user = users;
        }
    };

    public AsyncCallback<String> messageCallback = new AsyncCallback<String>() {
        public void onFailure(Throwable caught) {
        }
        public void onSuccess(String st) {
            display.setUserMessage(st + " " + user.getName() + "!");
        }
    };

    ClickHandler logoutClickHandler = new ClickHandler() {
        public void onClick(ClickEvent evt) {
            gwtAppService.logout(logoutCallback);
        }
    };

    public AsyncCallback<Void> logoutCallback = new AsyncCallback<Void>() {
        public void onFailure(Throwable t) {
        }
        public void onSuccess(Void v) {
            eventBus.fireEvent(new LogOutEvent());

        }
    };

    public void setDate(Date time){
        this.date = time;
    }

    public void setUser(User user){
        this.user = user;
    }

}
