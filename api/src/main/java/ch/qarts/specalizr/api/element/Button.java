package ch.qarts.specalizr.api.element;

import ch.qarts.specalizr.api.query.ElementQueryComponent;

public class Button extends ElementBase implements Clickable {

    protected Button(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    public static Button button(final ElementQueryComponent... elementQueryComponentList) {
        return new Button(elementQueryComponentList);
    }

}
