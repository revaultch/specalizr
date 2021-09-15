package ch.qarts.specalizr.api.action.definition;

import ch.qarts.specalizr.api.action.ActionDefinition;
import ch.qarts.specalizr.api.element.Writable;
import lombok.Getter;
import lombok.NonNull;

public class PressActionDefinition<T extends Writable> implements ActionDefinition {

    @Getter
    private final Key key;

    @Getter
    private final T writableElement;

    private PressActionDefinition(@NonNull final Key key, final T writableElement) {
        this.key = key;
        this.writableElement = writableElement;
    }

    public PressActionDefinition<T> on(final T writableElement) {
        return new PressActionDefinition<>(this.key, this.writableElement);
    }

    public static @NonNull <T extends Writable> PressActionDefinition<T> press(final Key key) {
        return new PressActionDefinition<T>(key, null);
    }


}
