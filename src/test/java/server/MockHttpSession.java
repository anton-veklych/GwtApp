package server;

import org.easymock.EasyMock;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

/**
 * Created by adm on 09.03.2016.
 */
public class MockHttpSession  {
    static HttpSession session = new HttpSession() {
        public long getCreationTime() {
            return 0;
        }

        public String getId() {
            return "test";
        }

        public long getLastAccessedTime() {
            return 0;
        }

        public ServletContext getServletContext() {
            ServletContext context = EasyMock.createMock(ServletContext.class);

            return context;
        }

        public void setMaxInactiveInterval(int i) {

        }

        public int getMaxInactiveInterval() {
            return 0;
        }

        public HttpSessionContext getSessionContext() {
            return null;
        }

        public Object getAttribute(String s) {
            return null;
        }

        public Object getValue(String s) {
            return null;
        }

        public Enumeration<String> getAttributeNames() {
            return null;
        }

        public String[] getValueNames() {
            return new String[0];
        }

        public void setAttribute(String s, Object o) {

        }

        public void putValue(String s, Object o) {

        }

        public void removeAttribute(String s) {

        }

        public void removeValue(String s) {

        }

        public void invalidate() {

        }

        public boolean isNew() {
            return false;
        }
    };
    public static HttpSession getSession(){
        return session;
    }

}
