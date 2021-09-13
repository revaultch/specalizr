package ch.borja.specalizr.api.element;

import ch.borja.specalizr.api.query.ElementQueryComponent;

import java.util.Arrays;
import java.util.List;

public abstract class ElementBase implements Element {

    protected final List<ElementQueryComponent> elementQueryComponentList;

    protected ElementBase(final ElementQueryComponent... elementQueryComponentList) {
        this.elementQueryComponentList = Arrays.asList(elementQueryComponentList);
    }

    @Override
    public List<ElementQueryComponent> getElementQueryComponentList() {
        return this.elementQueryComponentList;
    }

}
