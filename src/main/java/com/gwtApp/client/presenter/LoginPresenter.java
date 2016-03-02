package com.gwtApp.client.presenter;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtApp.Entity.User;
import com.gwtApp.client.GwtAppServiceAsync;
import com.gwtApp.client.event.LoginEvent;

import java.util.List;


public class LoginPresenter implements Presenter{

    public interface Display{
        User getUser();
        HasClickHandlers buttonSubmit();
        List<HasKeyDownHandlers> keyDownHandlers();
        void setErrorMsg(String msg);
        HasText getPassInputBox();
        Widget asWidget();
    }


    private GwtAppServiceAsync gwtAppService;
    private HandlerManager eventBus;
    private Display display;


    public LoginPresenter(GwtAppServiceAsync gwtAppService, HandlerManager eventBus, LoginPresenter.Display display){
        this.gwtAppService = gwtAppService;
        this.eventBus = eventBus;
        this.display = display;
        bind();
    }

    private void bind() {
        display.buttonSubmit().addClickHandler(loginClickHandler);
        for(HasKeyDownHandlers h : display.keyDownHandlers()) {
            h.addKeyDownHandler(keyDownHandler);
        }
    }


    private User user;
    public boolean isValid() {
        return
                (user.getLogin() != null) && !(user.getLogin().isEmpty()) &&
                        (user.getPassword() != null) && !(user.getPassword().isEmpty());
    }
    private void doLogin() {
        display.setErrorMsg(null);
        user = display.getUser();
        if(isValid()) {
            gwtAppService.userLogining(user, new AsyncCallback<Boolean>(){
                public void onFailure(Throwable caught) {
                    display.setErrorMsg("Fail user!!!");
                }
                public void onSuccess(Boolean b) {
                    if (b==true) {
                        eventBus.fireEvent(new LoginEvent());
                        display.getPassInputBox().setText("");
                    } else {
                        display.setErrorMsg("Password failed!!!");
                    }

                }
            });
        } else {
            display.setErrorMsg("Please enter a username and password!");
        }
    }

    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());

    }


    public  ClickHandler loginClickHandler = new ClickHandler() {
        public void onClick(ClickEvent evt) {
            doLogin();
        }
    };


    public KeyDownHandler keyDownHandler = new KeyDownHandler() {
        public void onKeyDown(KeyDownEvent evt) {
            display.setErrorMsg(null);
            if(KeyCodes.KEY_ENTER == evt.getNativeKeyCode()) {
                doLogin();
            }
        }
    };


}
