package com.gwtApp.client.Page;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;
import com.gwtApp.client.presenter.MainPagePresenter;


public class MainPage extends Composite implements MainPageView, MainPagePresenter.Display {
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

    public MainPage() {
        super();
        this.res = GWT.create(MainPageResources.class);
        res.style().ensureInjected();
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setUserMessage(String msg){
        message.setText(msg);
    }

    public HasClickHandlers exit(){
        return exit;
    }

    public Label getMessage(){
        return  message;
    }

    public Button getExit(){ return exit; }

    public Widget asWidget() {
        return this;
    }


}