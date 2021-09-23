package ch.qarts.specalizr.api.action.definition;

import ch.qarts.specalizr.api.action.ActionDefinition;
import ch.qarts.specalizr.api.element.Clickable;
import ch.qarts.specalizr.api.element.Item;
import lombok.Getter;

import static ch.qarts.specalizr.api.element.Item.item;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;

public class ClickActionDefinition<T extends Clickable> implements ActionDefinition {

    @Getter
    private final T clickableElement;

    private ClickActionDefinition(final T clickableElement) {
        this.clickableElement = clickableElement;
    }

    public static <T extends Clickable> ClickActionDefinition<T> click(final T element) {
        return new ClickActionDefinition<T>(element);
    }

    public static ClickActionDefinition<Item> click(final String text) {
        return click(item(with(text(text))));
    }


}
