package ch.qarts.specalizr.api.element;

import ch.qarts.specalizr.api.query.ElementQueryComponent;

public class CheckBox extends ElementBase implements Clickable, Toggable {

    private CheckBox(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    public static CheckBox checkbox(final ElementQueryComponent... elementQueryComponentList) {
        return new CheckBox(elementQueryComponentList);
    }

}
