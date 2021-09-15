package ch.qarts.specalizr.api.query;

import lombok.Getter;

public class AttributeQueryComponent extends ElementQueryComponent {

    @Getter
    private final String attributeName;

    @Getter
    private final ElementQueryComponent elementQueryComponent;


    private AttributeQueryComponent(final String attributeName, final ElementQueryComponent elementQueryComponent) {
        this.attributeName = attributeName;
        this.elementQueryComponent = elementQueryComponent;
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static AttributeQueryComponent placeholder(final ElementQueryComponent elementQueryComponent) {
        return new AttributeQueryComponent("placeholder", elementQueryComponent);
    }

    /**
     * This kind of breaks the "don't test the inner guts" rule. <p>
     * However in some cases it can be very handy. And enforcing id's is generally a good thing.
     */
    public static AttributeQueryComponent id(final String elementQueryComponent) {
        return new AttributeQueryComponent("id", TextQueryComponent.text(elementQueryComponent));
    }

    public static AttributeQueryComponent name(final String elementQueryComponent) {
        return new AttributeQueryComponent("name", TextQueryComponent.text(elementQueryComponent));
    }

}
