package ch.borja.specalizr.api.element;


import ch.borja.specalizr.api.query.ElementQueryComponent;
import ch.borja.specalizr.api.query.ElementVisitor;

public class Select extends ElementBase implements Selectable {

    private Select(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static Select selector(final ElementQueryComponent... elementQueryComponentList) {
        return new Select(elementQueryComponentList);
    }

}
