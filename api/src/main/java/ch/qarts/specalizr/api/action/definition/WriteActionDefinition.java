package ch.qarts.specalizr.api.action.definition;

import ch.qarts.specalizr.api.action.ActionDefinition;
import ch.qarts.specalizr.api.element.Writable;
import lombok.Getter;

public class WriteActionDefinition<T extends Writable> implements ActionDefinition {

    @Getter
    private final String text;

    @Getter
    private final T writableElement;

    private WriteActionDefinition(final String text, final T writableElement) {
        this.text = text;
        this.writableElement = writableElement;
    }

    public WriteActionDefinition<T> into(final T writable) {
        return new WriteActionDefinition<T>(this.text, writable);
    }

    public static <T extends Writable> WriteActionDefinition<T> write(final String text) {
        return new WriteActionDefinition<T>(text, null);
    }

}
