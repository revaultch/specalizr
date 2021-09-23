package ch.qarts.specalizr.api.query;

import lombok.Getter;

public class NameQueryComponent extends ElementQueryComponent {

    @Getter
    private final ElementQueryComponent elementQueryComponent;


    protected NameQueryComponent( final ElementQueryComponent elementQueryComponent) {
        this.elementQueryComponent = elementQueryComponent;
    }

    /**
     * This kind of breaks the "don't test the inner guts" rule. <p>
     * However in some cases it can be very handy. And enforcing id's is generally a good thing.
     */

    public static NameQueryComponent name(final ElementQueryComponent elementQueryComponent) {
        return new NameQueryComponent(elementQueryComponent);
    }

    public static NameQueryComponent name(final String name) {
        return new NameQueryComponent(TextQueryComponent.text(name));
    }

}
