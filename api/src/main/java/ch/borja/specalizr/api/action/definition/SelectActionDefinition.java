package ch.borja.specalizr.api.action.definition;

import ch.borja.specalizr.api.action.ActionDefinition;
import ch.borja.specalizr.api.element.Selectable;
import lombok.Getter;


public class SelectActionDefinition<T extends Selectable> implements ActionDefinition {

    @Getter
    private final String text;

    @Getter
    private final T selectableVisibleElement;

    private SelectActionDefinition(final String text, final T selectableVisibleElement) {
        this.text = text;
        this.selectableVisibleElement = selectableVisibleElement;
    }

    public SelectActionDefinition<T> from(final T element) {
        return new SelectActionDefinition<T>(this.text, element);
    }

    public static <T extends Selectable> SelectActionDefinition<T> select(final String text) {
        return new SelectActionDefinition<T>(text, null);
    }

}
