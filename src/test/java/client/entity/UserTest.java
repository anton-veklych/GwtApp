package client.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.gwtApp.Entity.User;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;
    private static final String Login = "name";
    private static final String Pass = "pass";

    @Before
    public void setUp() {
        user = new User();
    }


    @Test
    public void testUserStringString() {
        user = new User();
        user.setLogin(Login);
        user.setPassword(Pass);
        assertEquals(Login, user.getLogin());
        assertEquals(Pass, user.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertNull(user.getLogin());
    }


    @Test
    public void testGetPassword() {
        assertNull(user.getPassword());
    }

    @Test
    public void testSetUsername() {
        user.setLogin(Login);
        assertEquals(Login, user.getLogin());
    }

    @Test
    public void testSetPassword() {
        user.setPassword(Pass);
        assertEquals(Pass, user.getPassword());
    }



}