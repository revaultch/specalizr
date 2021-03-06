package ch.qarts.specalizr.api.query;

import lombok.Getter;


public class LabelQueryComponent extends ElementQueryComponent {

    @Getter
    private final ElementQueryComponent elementQueryComponent;

    protected LabelQueryComponent(final ElementQueryComponent elementQueryComponent) {
        this.elementQueryComponent = elementQueryComponent;
    }

    public static LabelQueryComponent label(final ElementQueryComponent elementQueryComponent) {
        return new LabelQueryComponent(elementQueryComponent);
    }

    public static LabelQueryComponent label(final String text) {
        return new LabelQueryComponent(TextQueryComponent.text(text));
    }
}
