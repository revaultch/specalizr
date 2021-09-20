package ch.qarts.specalizr.api.query;

import lombok.Getter;


public class LabelQueryComponent extends ElementQueryComponent {

    @Getter
    private final ElementQueryComponent elementQueryComponent;

    private LabelQueryComponent(final ElementQueryComponent elementQueryComponent) {
        this.elementQueryComponent = elementQueryComponent;
    }

    public static LabelQueryComponent label(final ElementQueryComponent elementQueryComponent) {
        return new LabelQueryComponent(elementQueryComponent);
    }

}
