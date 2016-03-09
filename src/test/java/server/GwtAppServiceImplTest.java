package server;


import com.gwtApp.Entity.User;
import com.gwtApp.server.GwtAppServiceImpl;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;


public class GwtAppServiceImplTest {

    GwtAppServiceImpl service = new GwtAppServiceImpl();

    @Test
    public  void getTimeMessageTest(){
        String locale = "en_US";
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, 7);
        Assert.assertEquals("Good morning,", service.getTimeMessage(locale, time.getTime()));
        locale = "ru_RU";
        time.set(Calendar.HOUR_OF_DAY, 14);
        Assert.assertEquals("Добрый День,", service.getTimeMessage(locale, time.getTime()));
    }

}
