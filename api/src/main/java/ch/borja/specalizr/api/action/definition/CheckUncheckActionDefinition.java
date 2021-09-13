package ch.borja.specalizr.api.action.definition;

import ch.borja.specalizr.api.action.ActionDefinition;
import ch.borja.specalizr.api.element.Toggable;
import lombok.Getter;

import static ch.borja.specalizr.api.action.definition.CheckUncheckActionDefinition.Actions.*;


public class CheckUncheckActionDefinition<T extends Toggable> implements ActionDefinition {

    public enum Actions {
        CHECK,
        UNCHECK,
        TOGGLE
    }

    @Getter
    private final T toggable;

    @Getter
    private final Actions action;


    private CheckUncheckActionDefinition(final T toggable, final Actions action) {
        this.toggable = toggable;
        this.action = action;
    }

    public static <T extends Toggable> CheckUncheckActionDefinition<T> check(final T checkable) {
        return new CheckUncheckActionDefinition<>(checkable, CHECK);
    }

    public static <T extends Toggable> CheckUncheckActionDefinition<T> uncheck(final T checkable) {
        return new CheckUncheckActionDefinition<>(checkable, UNCHECK);
    }

    public static <T extends Toggable> CheckUncheckActionDefinition<T> toggle(final T checkable) {
        return new CheckUncheckActionDefinition<>(checkable, TOGGLE);
    }

}
