package ch.qarts.specalizr.api.element;

import ch.qarts.specalizr.api.query.ElementQueryComponent;

/**
 * An undefined visible element
 */
public class Item extends ElementBase implements Clickable, Validatable {

    protected Item(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    public static Item item(final ElementQueryComponent... elementQueryComponentList) {
        return new Item(elementQueryComponentList);
    }

}
