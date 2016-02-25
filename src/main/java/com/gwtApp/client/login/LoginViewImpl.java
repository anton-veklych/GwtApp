package com.gwtApp.client.login;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.gwtApp.client.GwtAppService;
import com.gwtApp.client.GwtAppServiceAsync;
import com.gwtApp.client.Page.MainPage;



public class LoginViewImpl extends Composite implements LoginView {
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

    private Boolean tooShort = false;


    @UiHandler("buttonSubmit")
    void doClickSubmit(ClickEvent event) {
        if (tooShort) {
            Window.alert("Login or Password is too short!");
        } else {
            userEntry();
        }
    }

    private void userEntry(){
        final GwtAppServiceAsync rpcService = GWT.create(GwtAppService.class);
        String login = loginBox.getValue();
        String password = passwordBox.getText();

        rpcService.userLogining(login, password, new AsyncCallback<Boolean>() {


            public void onFailure(Throwable caught) {
                Window.alert("Fail Users");
            }

            public void onSuccess(Boolean b) {
                if (b==true) {

                    RootPanel.get("content").clear();
                    RootPanel.get("content").add(new MainPage());
                } else {
                    Window.alert("Password failed!!!");
                }

            }
        });
    }

    @UiHandler("loginBox")
    void handleLoginChange(ValueChangeEvent<String> event) {
        if (event.getValue().length() < 2) {
            completionLabel1.setText("LoginViewImpl too short (Size must be > 2)");
            tooShort = true;
        } else {
            tooShort = false;
            completionLabel1.setText("");
        }
    }

    @UiHandler("loginBox" )
    void handleLoginChange(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            userEntry();
        }
    }

    @UiHandler("passwordBox" )
    void handlePasswordKeyboardKey(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            userEntry();
        }
    }

    @UiHandler("passwordBox")
    void handlePasswordChange(ValueChangeEvent<String> event) {
        if (event.getValue().length() < 2) {
            tooShort = true;
            completionLabel2.setText("Password too short (Size must be > 2)");
        } else {
            tooShort = false;
            completionLabel2.setText("");
        }
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
}