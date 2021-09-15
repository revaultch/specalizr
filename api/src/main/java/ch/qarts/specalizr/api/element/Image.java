package ch.qarts.specalizr.api.element;

import ch.qarts.specalizr.api.query.ElementQueryComponent;
import ch.qarts.specalizr.api.query.ElementVisitor;

public class Image extends ElementBase implements Clickable {

    private Image(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static Image image(final ElementQueryComponent... elementQueryComponentList) {
        return new Image(elementQueryComponentList);
    }

}
