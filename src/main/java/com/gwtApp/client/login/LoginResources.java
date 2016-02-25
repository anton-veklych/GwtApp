package com.gwtApp.client.login;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface LoginResources extends ClientBundle{

    interface CssExampleInterface extends CssResource {
        String box();
        String blackText();
        String loginButton();
    }

    @ClientBundle.Source(value = "Login.css")
    CssExampleInterface style();

}