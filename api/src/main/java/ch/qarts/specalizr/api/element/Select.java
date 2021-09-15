package ch.qarts.specalizr.api.element;


import ch.qarts.specalizr.api.query.ElementQueryComponent;

public class Select extends ElementBase implements Selectable {

    private Select(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    public static Select selector(final ElementQueryComponent... elementQueryComponentList) {
        return new Select(elementQueryComponentList);
    }

}
