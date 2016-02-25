package com.gwtApp.server;

import com.gwtApp.server.TimeIntervals;

import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * Created by adm on 16.02.2016.
 */
public class Message {

    public ResourceBundle showResource(Locale locale){
        Locale localeDefault = new Locale("en", "US");
        ResourceBundle resourceBundle = null;

        try {
            resourceBundle = ResourceBundle.getBundle("resources", locale);
        } catch (MissingResourceException e){
            localeDefault.setDefault(localeDefault);
            resourceBundle = ResourceBundle.getBundle("resources", localeDefault.getDefault());
        }
        return resourceBundle;

    }

    public String showMessage(TimeIntervals tm, Date currentTime ) {

        if (tm.morning().getTime() < currentTime.getTime() && currentTime.getTime() <= tm.day().getTime()) {
            String st = "morning";
            return st;
        } else if (tm.day().getTime() < currentTime.getTime() && currentTime.getTime() <= tm.evening().getTime()) {
            String st = "day";
            return st;
        } else if (tm.evening().getTime() < currentTime.getTime() && currentTime.getTime() <= tm.night().getTime()) {
            String st = "evening";
            return st;
        } else  {
            String st = "night";
            return st;
        }
    }



}



