package client.presenter;


import client.mocks.MockHasClickHandlers;
import client.mocks.MockHasKeyDownHandlers;
import client.mocks.MockHasWidgets;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.gwtApp.client.GwtAppServiceAsync;
import com.gwtApp.client.presenter.MainPagePresenter;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainPagePresenterTest {
    private MainPagePresenter.Display display;
    private HandlerManager eventBus;
    private MockHasWidgets container;
    private MockHasClickHandlers logoutClickHandler;
    private GwtAppServiceAsync service;

    @Before
    public void setUp() throws Exception {

        display = EasyMock.createMock(MainPagePresenter.Display.class);
        eventBus = EasyMock.createMock(HandlerManager.class);
        service = EasyMock.createMock(GwtAppServiceAsync.class);

        container = new MockHasWidgets();
        logoutClickHandler = new MockHasClickHandlers();

        EasyMock.expect(display.exit()).andReturn(logoutClickHandler);
    }

    private MainPagePresenter getPresenter() {
        return new MainPagePresenter(service, eventBus, display);
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
    public void testMainPresenter() {
        EasyMock.expect(display.asWidget()).andReturn(null);
        replayAll();
        getPresenter().go(container);
        verifyAll();
    }

    @Test
    public void testGo() {
        Assert.fail("Not yet implemented");
    }

}
