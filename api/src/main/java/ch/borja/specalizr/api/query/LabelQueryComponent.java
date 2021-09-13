package ch.borja.specalizr.api.query;

import lombok.Getter;


public class LabelQueryComponent extends ElementQueryComponent {

    @Getter
    private final ElementQueryComponent elementQueryComponent;

    private LabelQueryComponent(final ElementQueryComponent elementQueryComponent) {
        this.elementQueryComponent = elementQueryComponent;
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static LabelQueryComponent label(final ElementQueryComponent elementQueryComponent) {
        return new LabelQueryComponent(elementQueryComponent);
    }

}
