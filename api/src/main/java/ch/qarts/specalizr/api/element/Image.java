package ch.qarts.specalizr.api.element;

import ch.qarts.specalizr.api.query.ElementQueryComponent;

public class Image extends ElementBase implements Clickable {

    protected Image(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    public static Image image(final ElementQueryComponent... elementQueryComponentList) {
        return new Image(elementQueryComponentList);
    }

}
