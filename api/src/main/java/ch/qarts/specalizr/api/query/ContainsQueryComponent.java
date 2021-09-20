package ch.qarts.specalizr.api.query;

import lombok.Getter;

public class ContainsQueryComponent extends ElementQueryComponent {

    @Getter
    private final TextQueryComponent textLocation;

    private ContainsQueryComponent(final TextQueryComponent textLocation) {
        this.textLocation = textLocation;
    }

    public static ContainsQueryComponent containing(final TextQueryComponent textVisibleElementQuery) {
        return new ContainsQueryComponent(textVisibleElementQuery);
    }


}
