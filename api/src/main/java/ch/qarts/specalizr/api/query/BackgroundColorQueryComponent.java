package ch.qarts.specalizr.api.query;

import ch.qarts.specalizr.api.color.Colors;
import lombok.Getter;

public class BackgroundColorQueryComponent extends ElementQueryComponent {

    @Getter
    private final Colors color;

    private BackgroundColorQueryComponent(final Colors color) {
        this.color = color;
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static BackgroundColorQueryComponent backgroundColor(final Colors color) {
        return new BackgroundColorQueryComponent(color);
    }

}
