package client.mocks;


import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.Iterator;

public class MockHasWidgets implements HasWidgets {
    public int addCount;
    public int clearCount;
    public int removeCount;


    public void add(Widget arg0) {
        addCount++;
    }

    public void clear() {
        clearCount++;
    }

    public Iterator<Widget> iterator() {
        throw new UnsupportedOperationException("not implemented");
    }

    public boolean remove(Widget arg0) {
        throw new UnsupportedOperationException("not implemented");
    }
}
