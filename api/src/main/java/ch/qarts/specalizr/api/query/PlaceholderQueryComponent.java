package ch.qarts.specalizr.api.query;

import lombok.Getter;

public class PlaceholderQueryComponent extends ElementQueryComponent {

    @Getter
    private final ElementQueryComponent elementQueryComponent;


    private PlaceholderQueryComponent(final ElementQueryComponent elementQueryComponent) {
        this.elementQueryComponent = elementQueryComponent;
    }

    public static PlaceholderQueryComponent placeholder(final ElementQueryComponent elementQueryComponent) {
        return new PlaceholderQueryComponent(elementQueryComponent);
    }


}
