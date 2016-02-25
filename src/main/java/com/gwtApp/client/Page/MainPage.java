package com.gwtApp.client.Page;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.gwtApp.Entity.Users;
import com.gwtApp.client.GwtAppService;
import com.gwtApp.client.GwtAppServiceAsync;
import com.gwtApp.client.login.LoginViewImpl;


import java.util.Date;


public class MainPage extends Composite implements MainPageView {
    private static MainPageUiBinder uiBinder = GWT.create(MainPageUiBinder.class);

    @UiTemplate("MainPage.ui.xml")
    interface MainPageUiBinder extends UiBinder<Widget, MainPage> {
    }

    @UiField(provided = true)
    final MainPageResources res;

    @UiField
    Label message;

    @UiField
    Button exit;

    Users user;

    public MainPage() {
        super();
        this.res = GWT.create(MainPageResources.class);
        res.style().ensureInjected();
        initWidget(uiBinder.createAndBindUi(this));

        Date date = new Date();
        LocaleInfo locale = LocaleInfo.getCurrentLocale();
        String clientLocale = locale.getLocaleName();
        final GwtAppServiceAsync rpcService = com.google.gwt.core.client.GWT.create(GwtAppService.class);

        rpcService.getSessionAttribute(new AsyncCallback<Users>() {
            public void onFailure(Throwable caught) {
            }

            public void onSuccess(Users users) {
                user = users;
            }
        });

        rpcService.getTimeMessage(clientLocale, date, new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
            }

            public void onSuccess(String st) {
                message.setText(st + " " + user.getName() + "!");
            }
        });


        exit.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                rpcService.logout(new AsyncCallback<Void>() {
                    public void onFailure(Throwable caught) {
                    }

                    public void onSuccess(Void v) {
                    }
                });
                RootPanel.get("content").clear();
                RootPanel.get("content").add(new LoginViewImpl());
            }
        });
    }

    public Label getMessage(){
        return  message;
    }

    public Button getExit(){ return exit; }


}