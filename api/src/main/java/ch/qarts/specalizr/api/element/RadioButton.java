package ch.qarts.specalizr.api.element;

import ch.qarts.specalizr.api.query.ElementQueryComponent;
import ch.qarts.specalizr.api.query.ElementVisitor;

public class RadioButton extends ElementBase implements Clickable, Toggable {

    private RadioButton(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static RadioButton radioButton(final ElementQueryComponent... elementQueryComponentList) {
        return new RadioButton(elementQueryComponentList);
    }

}
