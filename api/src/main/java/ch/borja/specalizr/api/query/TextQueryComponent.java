package ch.borja.specalizr.api.query;

import lombok.Getter;
import lombok.ToString;

@ToString
public class TextQueryComponent extends ElementQueryComponent {

    @Getter
    private final String text;

    private TextQueryComponent(final String text) {
        this.text = text;
    }

    @Override
    public <T> T accept(final ElementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public static TextQueryComponent text(final String text) {
        return new TextQueryComponent(text);
    }

}
