package ch.qarts.specalizr.api.action;

import ch.qarts.specalizr.api.player.ActionDefinitionPlayerRegistry;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Enables action chaining. <p>
 * Action chaining allows the creation of a human-readable workflow in the following form <p>
 * <PRE>
 * var actions = first(click(a(button(having(backgroundColor(BLUE).and(containing(text("hola"))))))))
 * .then(write("hello").into(a(field(near(button(with(text("hola"))))))))
 * .then(write("hello").into(a(field(with(label("Message"))))))
 * .then(select("abcd").from(a(selector(with(selectedText("this is it"))))))
 * .then(validate(that(panel(with(text("done")))), exists()))
 * .andLastly(validate(that(panel(with(text("done")))), containsText("do")));
 * </PRE>
 * Chain starts with {@link #first(ActionDefinition)} as this method creates an {@link ActionChain} instance. <p>
 * Further chaining is performed by using instance method {@link #then(ActionDefinition)}  <p>
 * For better readability finish the chain with {@link #andLastly(ActionDefinition)} <p>
 */
@Log4j
public class ActionChain {

    @Getter
    private final List<ActionDefinition> actionDefinitionList = new ArrayList<>();

    private ActionChain() {
    }

    public static void play(@NonNull final ActionChain actionChain, @NonNull final ActionDefinitionPlayerRegistry actionChainPlayer) {
        for (final var actionDefinition : actionChain.getActionDefinitionList()) {
            ActionChain.log.debug(format("playing ... %s", actionDefinition));
            final var actionDefinitionPlayer = actionChainPlayer.forType((Class<ActionDefinition>) actionDefinition.getClass());
            actionDefinitionPlayer.play(actionDefinition);
        }
    }

    public <T extends ActionDefinition> ActionChain then(final T action) {
        this.actionDefinitionList.add(action);
        return this;
    }

    public <T extends ActionDefinition> ActionChain andLastly(final T action) {
        this.actionDefinitionList.add(action);
        return this;
    }

    public static <T extends ActionDefinition> ActionChain first(final T action) {
        final var actionChainBuilder = new ActionChain();
        actionChainBuilder.getActionDefinitionList().add(action);
        return actionChainBuilder;
    }

}
