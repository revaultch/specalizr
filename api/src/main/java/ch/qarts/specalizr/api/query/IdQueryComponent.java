package ch.qarts.specalizr.api.query;

import lombok.Getter;

public class IdQueryComponent extends ElementQueryComponent {

    @Getter
    private final ElementQueryComponent elementQueryComponent;


    protected IdQueryComponent(final ElementQueryComponent elementQueryComponent) {
        this.elementQueryComponent = elementQueryComponent;
    }

    /**
     * This kind of breaks the "don't test the inner guts" rule. <p>
     * However in some cases it can be very handy. And enforcing id's is generally a good thing.
     */
    public static IdQueryComponent id(final ElementQueryComponent elementQueryComponent) {
        return new IdQueryComponent(elementQueryComponent);
    }

    public static IdQueryComponent id(final String id) {
        return new IdQueryComponent(TextQueryComponent.text(id));
    }

}
