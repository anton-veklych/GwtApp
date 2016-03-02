package client.mocks;


import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class MockHasKeyDownHandlers implements HasKeyDownHandlers {
    public int addHandlerCount;
    public int fireEventCount;

    public HandlerRegistration addKeyDownHandler(KeyDownHandler arg0) {
        addHandlerCount++;
        return null;
    }

    public void fireEvent(GwtEvent<?> arg0) {
        fireEventCount++;
    }
}
