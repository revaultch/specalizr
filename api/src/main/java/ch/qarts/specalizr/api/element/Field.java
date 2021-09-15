package ch.qarts.specalizr.api.element;


import ch.qarts.specalizr.api.query.ElementQueryComponent;

public class Field extends ElementBase implements Writable, Validatable {

    private Field(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    public static Field field(final ElementQueryComponent... elementQueryComponentList) {
        return new Field(elementQueryComponentList);
    }

}
