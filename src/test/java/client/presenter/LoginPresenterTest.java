package client.presenter;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.gwtApp.Entity.User;
import com.gwtApp.client.event.LoginEvent;
import org.easymock.EasyMock;
import client.mocks.MockHasClickHandlers;
import client.mocks.MockHasKeyDownHandlers;
import client.mocks.MockHasWidgets;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.gwtApp.client.GwtAppServiceAsync;
import com.gwtApp.client.presenter.LoginPresenter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LoginPresenterTest {

    private LoginPresenter.Display display;
    private HandlerManager eventBus;
    private List<HasKeyDownHandlers> hasKeyDownHandlers;
    private MockHasWidgets container;
    private MockHasClickHandlers hasClickHandlers;
    private GwtAppServiceAsync service;

    @Before
    public void setUp() throws Exception {

        display = EasyMock.createMock(LoginPresenter.Display.class);
        eventBus = EasyMock.createMock(HandlerManager.class);
        service = EasyMock.createMock(GwtAppServiceAsync.class);

        container = new MockHasWidgets();
        hasClickHandlers = new MockHasClickHandlers();
        hasKeyDownHandlers = new ArrayList<HasKeyDownHandlers>();
        hasKeyDownHandlers.add(new MockHasKeyDownHandlers());

        EasyMock.expect(display.buttonSubmit()).andReturn(hasClickHandlers);
        EasyMock.expect(display.keyDownHandlers()).andReturn(hasKeyDownHandlers);
    }

    @After
    public void tearDown() throws Exception {
        Assert.assertEquals("expected click handler to be added ", 1, hasClickHandlers.addClickHandlerCnt);

        for(HasKeyDownHandlers h : hasKeyDownHandlers) {
            Assert.assertEquals("expected has key down handler to be added ", 1, ((MockHasKeyDownHandlers) h).addHandlerCount);
        }
    }

    private LoginPresenter getPresenter() {
        return new LoginPresenter(service, eventBus, display);
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
    public void testLoginPresenter() {
        replayAll();
        getPresenter();
        verifyAll();
    }

    @Test
    public void testGo() {

        EasyMock.expect(display.asWidget()).andReturn(null);
        replayAll();
        getPresenter().go(container);
        verifyAll();

        Assert.assertEquals("expected clear ", 1, container.clearCount);
        Assert.assertEquals("expected add ", 1, container.addCount);
    }


     @Test
    public void testLoginClickHandlerUserInvalid() {
        display.setErrorMsg(null);
        EasyMock.expectLastCall();
        EasyMock.expect(display.getUser()).andReturn(new User());
        display.setErrorMsg("Please enter a username and password!");
        EasyMock.expectLastCall();

        replayAll();
        getPresenter().loginClickHandler.onClick(null);
        verifyAll();
    }

   @Test
    public void testLoginClickHandlerUserValid() {
        User user = new User();
        user.setLogin("name");
        user.setPassword("pass");
        display.setErrorMsg(null);
        EasyMock.expectLastCall().once();
        EasyMock.expect(display.getUser()).andReturn(user);
        service.userLogining(EasyMock.eq(user), EasyMock.<AsyncCallback<Boolean>>anyObject());
        EasyMock.expectLastCall().once();

        replayAll();
        getPresenter().loginClickHandler.onClick(null);
        verifyAll();
    }

    @Test
    public void testKeyDownHandlerUserInvalid() {
        display.setErrorMsg(null);
        EasyMock.expectLastCall().times(2);
        EasyMock.expect(display.getUser()).andReturn(new User());
        display.setErrorMsg("Please enter a username and password!");
        EasyMock.expectLastCall().once();

        KeyDownEvent evt = EasyMock.createMock(KeyDownEvent.class);
        EasyMock.expect(evt.getNativeKeyCode()).andReturn(KeyCodes.KEY_ENTER);

        EasyMock.replay(evt);
        replayAll();
        getPresenter().keyDownHandler.onKeyDown(evt);
        verifyAll();
        EasyMock.verify(evt);
    }

    @Test
    public void testKeyDownHandlerUserValid() {
        display.setErrorMsg(null);
        EasyMock.expectLastCall().times(2);
        User user = new User();
        user.setLogin("name");
        user.setPassword("pass");
        EasyMock.expect(display.getUser()).andReturn(user);
        EasyMock.expectLastCall().once();

        KeyDownEvent evt = EasyMock.createMock(KeyDownEvent.class);
        EasyMock.expect(evt.getNativeKeyCode()).andReturn(KeyCodes.KEY_ENTER);

        service.userLogining(EasyMock.eq(user), EasyMock.<AsyncCallback<Boolean>>anyObject());
        EasyMock.expectLastCall().once();

        EasyMock.replay(evt);
        replayAll();
        getPresenter().keyDownHandler.onKeyDown(evt);
        verifyAll();
        EasyMock.verify(evt);
    }

    @Test
    public void testCallBackFailure() {
        Throwable t = new Exception();
        display.setErrorMsg("Fail user!!!");
        EasyMock.expectLastCall().once();

        replayAll();
        getPresenter().callback.onFailure(t);
        verifyAll();

    }

    @Test
    public void testCallbackSuccess(){
        Boolean b = true;
        HasText text = EasyMock.createMock(HasText.class);
        eventBus.fireEvent(EasyMock.<LoginEvent>anyObject());
        EasyMock.expectLastCall().once();
        EasyMock.expect(display.getPassInputBox()).andReturn(text);

        replayAll();
        getPresenter().callback.onSuccess(b);
        verifyAll();
    }

}
