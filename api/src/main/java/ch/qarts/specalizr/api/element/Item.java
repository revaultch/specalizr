package ch.qarts.specalizr.api.element;

import ch.qarts.specalizr.api.query.ElementQueryComponent;
import ch.qarts.specalizr.api.query.ElementVisitor;

/**
 * An undefined visible element
 */
public class Item extends ElementBase implements Clickable, Validatable {

    private Item(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static Item item(final ElementQueryComponent... elementQueryComponentList) {
        return new Item(elementQueryComponentList);
    }

}
