package com.gwtApp.client.login;


import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;


public interface LoginView {
    public HasText getUserInputBox();
    public HasText getPassInputBox();
    public Label getCompletionLabel1();

}
