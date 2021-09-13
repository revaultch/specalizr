package ch.borja.specalizr.api.element;

import ch.borja.specalizr.api.query.ElementQueryComponent;
import ch.borja.specalizr.api.query.ElementVisitor;

public class Button extends ElementBase implements Clickable {

    private Button(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static Button button(final ElementQueryComponent... elementQueryComponentList) {
        return new Button(elementQueryComponentList);
    }

}
