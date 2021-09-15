package ch.qarts.specalizr.api.element;

import ch.qarts.specalizr.api.query.ElementQueryComponent;
import ch.qarts.specalizr.api.query.ElementVisitor;

public class CheckBox extends ElementBase implements Clickable, Toggable {

    private CheckBox(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static CheckBox checkBox(final ElementQueryComponent... elementQueryComponentList) {
        return new CheckBox(elementQueryComponentList);
    }

}
