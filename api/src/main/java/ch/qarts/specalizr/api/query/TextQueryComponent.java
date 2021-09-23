package ch.qarts.specalizr.api.query;

import lombok.Getter;
import lombok.ToString;

@ToString
public class TextQueryComponent extends ElementQueryComponent {

    @Getter
    private final String text;

    protected TextQueryComponent(final String text) {
        this.text = text;
    }

    public static TextQueryComponent text(final String text) {
        return new TextQueryComponent(text);
    }

}
