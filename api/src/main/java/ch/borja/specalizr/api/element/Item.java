package ch.borja.specalizr.api.element;

import ch.borja.specalizr.api.query.ElementQueryComponent;
import ch.borja.specalizr.api.query.ElementVisitor;

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
