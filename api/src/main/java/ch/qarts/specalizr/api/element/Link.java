package ch.qarts.specalizr.api.element;


import ch.qarts.specalizr.api.query.ElementQueryComponent;

public class Link extends ElementBase implements Clickable {

    protected Link(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    public static Link link(final ElementQueryComponent... elementQueryComponentList) {
        return new Link(elementQueryComponentList);
    }

}
