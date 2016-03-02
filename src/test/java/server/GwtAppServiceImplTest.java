package server;

import com.gwtApp.server.GwtAppServiceImpl;
import org.junit.Assert;
import org.junit.Test;


public class GwtAppServiceImplTest {




    @Test
    public void gwtAppServiceImplTest(){
        GwtAppServiceImpl service = new GwtAppServiceImpl();
        Assert.assertEquals(true,service.initialDB());
    }

}
