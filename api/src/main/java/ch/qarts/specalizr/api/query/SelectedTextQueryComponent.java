package ch.qarts.specalizr.api.query;

import lombok.Getter;

public class SelectedTextQueryComponent extends ElementQueryComponent {

    @Getter
    private final String text;

    private SelectedTextQueryComponent(final String text) {
        this.text = text;
    }

    public static SelectedTextQueryComponent selectedText(final String text) {
        return new SelectedTextQueryComponent(text);
    }

}
