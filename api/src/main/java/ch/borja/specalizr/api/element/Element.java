package ch.borja.specalizr.api.element;


import ch.borja.specalizr.api.query.ElementQueryComponent;
import ch.borja.specalizr.api.query.ElementVisitor;

import java.util.List;

public interface Element {

    List<ElementQueryComponent> getElementQueryComponentList();

    <T> T accept(ElementVisitor<T> visitor);

    static <T extends Element> T a(final T t) {
        return t;
    }

    static <T extends Element> T an(final T t) {
        return t;
    }

}


