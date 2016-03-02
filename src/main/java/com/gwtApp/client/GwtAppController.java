package com.gwtApp.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.gwtApp.client.Page.MainPage;
import com.gwtApp.client.event.LogOutEvent;
import com.gwtApp.client.event.LogOutEventHandler;
import com.gwtApp.client.event.LoginEvent;
import com.gwtApp.client.event.LoginEventHandler;
import com.gwtApp.client.login.LoginViewImpl;
import com.gwtApp.client.presenter.LoginPresenter;
import com.gwtApp.client.presenter.MainPagePresenter;
import com.gwtApp.client.presenter.Presenter;

public class GwtAppController implements Presenter {

    private HandlerManager eventBus;
    private HasWidgets container;
    private GwtAppServiceAsync gwtAppService;
    private LoginPresenter loginPresenter;
    private MainPagePresenter mainPagePresenter;

    public GwtAppController(HandlerManager eventBus){
        this.eventBus = eventBus;
        gwtAppService = GWT.create(GwtAppService.class);
        loginPresenter = new LoginPresenter(gwtAppService, eventBus, new LoginViewImpl());
        mainPagePresenter = new MainPagePresenter(gwtAppService, eventBus, new MainPage());
       bind();
    }

    private void bind() {
        eventBus.addHandler(LoginEvent.TYPE, loginHandler);
        eventBus.addHandler(LogOutEvent.TYPE, logOutHandler);
    }

    LogOutEventHandler logOutHandler = new LogOutEventHandler() {
        public void logOut(LogOutEvent logOutEvent) {
            loginPresenter.go(container);
        }
    };

    LoginEventHandler loginHandler = new LoginEventHandler() {
        public void onLogin(LoginEvent loginEvent) {
            mainPagePresenter.go(container);
        }
    };

    public void go(HasWidgets container){
        this.container = container;
    loginPresenter.go(container);
    }
}
