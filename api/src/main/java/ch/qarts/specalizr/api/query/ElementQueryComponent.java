package ch.qarts.specalizr.api.query;

import ch.qarts.specalizr.api.element.Item;

import static ch.qarts.specalizr.api.element.Item.item;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;

public abstract class ElementQueryComponent {

    public static <T> T having(final T any) {
        return any;
    }

    public static <T> T with(final T any) {
        return any;
    }

    public static <T> T that(final T any) {
        return any;
    }

    public static Item that(final String text) {
        return item(with(text(text)));
    }

    public static <T> T an(final T any) {
        return any;
    }

}
