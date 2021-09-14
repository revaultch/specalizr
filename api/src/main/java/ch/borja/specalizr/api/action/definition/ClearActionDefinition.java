package ch.borja.specalizr.api.action.definition;

import ch.borja.specalizr.api.action.ActionDefinition;
import ch.borja.specalizr.api.element.Writable;
import lombok.Getter;
import lombok.ToString;

@ToString
public class ClearActionDefinition<T extends Writable> implements ActionDefinition {

    @Getter
    private final T writableElement;

    private ClearActionDefinition(final T writableElement) {
        this.writableElement = writableElement;
    }

    public static <T extends Writable> ClearActionDefinition<T> clear(final T writable) {
        return new ClearActionDefinition<T>(writable);
    }

}
