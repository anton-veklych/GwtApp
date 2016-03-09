package client.presenter;


import client.mocks.MockHasClickHandlers;
import client.mocks.MockHasWidgets;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtApp.Entity.User;
import com.gwtApp.client.GwtAppServiceAsync;
import com.gwtApp.client.event.LogOutEvent;
import com.gwtApp.client.event.LoginEvent;
import com.gwtApp.client.presenter.MainPagePresenter;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class MainPagePresenterTest {
    private MainPagePresenter.Display display;
    private HandlerManager eventBus;
    private MockHasWidgets container;
    private MockHasClickHandlers logoutClickHandler;
    private GwtAppServiceAsync service;
    private String locale;
    private Date time;
    private Void v;

    @Before
    public void setUps() throws Exception {

        display = EasyMock.createMock(MainPagePresenter.Display.class);
        eventBus = EasyMock.createMock(HandlerManager.class);
        service = EasyMock.createMock(GwtAppServiceAsync.class);
        locale = "ru_RU";
        time = EasyMock.createMock(Date.class);


        container = new MockHasWidgets();
        logoutClickHandler = new MockHasClickHandlers();
    }

    private MainPagePresenter getPresenter() {
        MainPagePresenter mainPagePresenter = new MainPagePresenter(service, eventBus, display, locale);
        return  mainPagePresenter;
    }

    private void replayAll() {
        EasyMock.replay(service);
        EasyMock.replay(eventBus);
        EasyMock.replay(display);
    }

    private void verifyAll() {
        EasyMock.verify(service);
        EasyMock.verify(eventBus);
        EasyMock.verify(display);
    }

    @Test
    public void testMainPagePresenter() {
        replayAll();
        getPresenter();
        verifyAll();
    }

    @Test
    public void testGo() {
        EasyMock.expect(display.asWidget()).andReturn(null);
        MainPagePresenter mainPage =  getPresenter();
        mainPage.setDate(time);
        service.getSessionAttribute(EasyMock.<AsyncCallback<User>>anyObject());
        service.getTimeMessage(EasyMock.eq(locale), EasyMock.eq(time), EasyMock.<AsyncCallback<String>>anyObject());
        EasyMock.expect(display.exit()).andReturn(logoutClickHandler);
        replayAll();
        mainPage.go(container);
        verifyAll();
    }

    @Test
    public void testMessageCallback(){
        MainPagePresenter mainPage =  getPresenter();
        User user = new User();
        user.setName("ivan");
        String st = "message";
        mainPage.setUser(user);
        display.setUserMessage("message ivan!");
        replayAll();
        mainPage.messageCallback.onSuccess(st);
        verifyAll();
    }

    @Test
    public void testUserCallback(){
        MainPagePresenter mainPage =  getPresenter();
        User user = new User();
        user.setName("ivan");
        replayAll();
        mainPage.userCallback.onSuccess(user);
        verifyAll();
    }

    @Test
    public void testLogoutCallback(){
        eventBus.fireEvent(EasyMock.<LogOutEvent>anyObject());
        EasyMock.expectLastCall().once();
        replayAll();
        getPresenter().logoutCallback.onSuccess(v);
        verifyAll();
    }

}
