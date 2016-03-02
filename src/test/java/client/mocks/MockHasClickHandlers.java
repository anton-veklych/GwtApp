package client.mocks;


import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class MockHasClickHandlers implements HasClickHandlers {

    public int addClickHandlerCnt;
    public int fireEventCnt;

    public HandlerRegistration addClickHandler(ClickHandler arg0) {
        addClickHandlerCnt++;
        return null;
    }

    public void fireEvent(GwtEvent<?> arg0) {
        fireEventCnt++;
    }
}
