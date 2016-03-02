package server;

import com.gwtApp.server.Message;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;


public class MessageTest {
    @Test
    public  void messageTest() throws Exception{
        Locale locale =  Locale.getDefault();
        Message message = new Message();
        Locale localeFR = new Locale("fr", "FR");
        Locale localeEN = new Locale("en", "US");
        Locale localeRU = new Locale("ru", "US");

        ResourceBundle resource = ResourceBundle.getBundle("resources", locale);
        ResourceBundle resourceBundle = message.showResource(locale);
        Assert.assertEquals(resource, resourceBundle);


        Locale.setDefault(localeFR);
        resourceBundle = message.showResource(Locale.getDefault());
        resource = ResourceBundle.getBundle("resources", localeEN);
        Assert.assertEquals(resource, resourceBundle);

        Locale.setDefault(localeEN);
        resourceBundle = message.showResource(Locale.getDefault());
        resource = ResourceBundle.getBundle("resources", localeEN);
        Assert.assertEquals(resource, resourceBundle);

        Locale.setDefault(localeRU);
        resourceBundle = message.showResource(Locale.getDefault());
        resource = ResourceBundle.getBundle("resources", localeEN);
        Assert.assertEquals(resource, resourceBundle);
    }
}
