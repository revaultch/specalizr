package ch.qarts.specalizr.api.action.definition;

import ch.qarts.specalizr.api.action.ActionDefinition;
import ch.qarts.specalizr.api.element.Clickable;
import lombok.Getter;

public class ClickActionDefinition<T extends Clickable> implements ActionDefinition {

    @Getter
    private final T clickableElement;

    private ClickActionDefinition(final T clickableElement) {
        this.clickableElement = clickableElement;
    }

    public static <T extends Clickable> ClickActionDefinition<T> click(final T element) {
        return new ClickActionDefinition<T>(element);
    }


}
