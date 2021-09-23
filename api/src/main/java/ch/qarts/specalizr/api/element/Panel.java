package ch.qarts.specalizr.api.element;


import ch.qarts.specalizr.api.query.ElementQueryComponent;

public class Panel extends ElementBase implements Clickable, Validatable {

    protected Panel(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    public static Panel panel(final ElementQueryComponent... elementQueryComponentList) {
        return new Panel(elementQueryComponentList);
    }

}
