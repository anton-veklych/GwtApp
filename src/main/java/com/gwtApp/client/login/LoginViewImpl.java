package com.gwtApp.client.login;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;
import com.gwtApp.Entity.User;
import com.gwtApp.client.presenter.LoginPresenter;

import java.util.ArrayList;
import java.util.List;


public class LoginViewImpl extends Composite implements LoginView, LoginPresenter.Display {
    private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);


    @UiTemplate("LoginViewImpl.ui.xml")
    interface LoginUiBinder extends UiBinder<Widget, LoginViewImpl> {
    }

    @UiField(provided = true)
    final LoginResources res;

    public LoginViewImpl() {
        this.res = GWT.create(LoginResources.class);
        res.style().ensureInjected();
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiField
    TextBox loginBox;
    @UiField
    TextBox passwordBox;
    @UiField
    Label completionLabel1;
    @UiField
    Label completionLabel2;
    @UiField
    Button buttonSubmit;


    public User getUser(){
        User user = new User();
        user.setLogin(loginBox.getText());
        user.setPassword(passwordBox.getText());
        return user;
    }

    public HasClickHandlers buttonSubmit(){
        return buttonSubmit;
    }

    public void setErrorMsg(String msg){
        completionLabel1.setText(msg);
    }

    public List<HasKeyDownHandlers> keyDownHandlers(){
        final List<HasKeyDownHandlers> list = new ArrayList<HasKeyDownHandlers>();
        list.add(loginBox);
        list.add(passwordBox);
        return list;
    }


    public HasText getUserInputBox() {
        return loginBox;
    }


    public HasText getPassInputBox() {
        return passwordBox;
    }

    public Label getCompletionLabel1() {
        return completionLabel1;
    }

    public Widget asWidget() {
        return this;
    }

}