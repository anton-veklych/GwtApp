package com.gwtApp.client.Page;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;


public interface MainPageResources extends ClientBundle {

    interface CssExampleInterface extends CssResource {
        String label();
        String button();
    }

    @ClientBundle.Source(value = "MainPage.css")
    CssExampleInterface style();
}
