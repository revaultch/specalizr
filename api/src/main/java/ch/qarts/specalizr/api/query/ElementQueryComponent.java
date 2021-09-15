package ch.qarts.specalizr.api.query;

public abstract class ElementQueryComponent {

    abstract public <T> T accept(ElementVisitor<T> visitor);

    public static <T> T having(final T any) {
        return any;
    }

    public static <T> T with(final T any) {
        return any;
    }

    public static <T> T that(final T any) {
        return any;
    }

    public static <T> T an(final T any) {
        return any;
    }

}
