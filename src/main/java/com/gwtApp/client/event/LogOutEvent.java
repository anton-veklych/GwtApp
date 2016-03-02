package com.gwtApp.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by adm on 29.02.2016.
 */
public class LogOutEvent extends GwtEvent<LogOutEventHandler> {

    public static Type<LogOutEventHandler> TYPE = new Type<LogOutEventHandler>();


    @Override
    protected void dispatch(LogOutEventHandler handler) {
        handler.logOut(this);
    }

    @Override
    public Type<LogOutEventHandler> getAssociatedType() {
        return TYPE;
    }
}
