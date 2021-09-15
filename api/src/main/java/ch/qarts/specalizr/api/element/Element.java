package ch.qarts.specalizr.api.element;


import ch.qarts.specalizr.api.query.ElementQueryComponent;

import java.util.List;

public interface Element {

    List<ElementQueryComponent> getElementQueryComponentList();

    static <T extends Element> T a(final T t) {
        return t;
    }

    static <T extends Element> T an(final T t) {
        return t;
    }

}


