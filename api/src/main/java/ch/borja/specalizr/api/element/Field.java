package ch.borja.specalizr.api.element;


import ch.borja.specalizr.api.query.ElementQueryComponent;
import ch.borja.specalizr.api.query.ElementVisitor;

public class Field extends ElementBase implements Writable, Validatable {

    private Field(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit((Writable) this);
    }

    public static Field field(final ElementQueryComponent... elementQueryComponentList) {
        return new Field(elementQueryComponentList);
    }

}
