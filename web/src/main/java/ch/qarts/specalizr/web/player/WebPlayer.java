package ch.qarts.specalizr.web.player;

import ch.qarts.specalizr.api.action.ActionDefinition;
import ch.qarts.specalizr.api.action.definition.*;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayerRegistry;
import ch.qarts.specalizr.web.ElementResolverFacade;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

@AllArgsConstructor
public class WebPlayer implements ActionDefinitionPlayerRegistry {

    private final WebDriver webDriver;

    private final ElementResolverFacade elementResolverFacade;

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ActionDefinition> ActionDefinitionPlayer<T> forType(final Class<T> type) {
        if (type.equals(ClickActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new ClickActionDefinitionPlayer(this.webDriver, this.elementResolverFacade);
        } else if (type.equals(WriteActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new WriteActionDefinitionPlayer(this.webDriver, this.elementResolverFacade);
        } else if (type.equals(ValidationActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new ValidateActionDefinitionPlayer(this.webDriver, this.elementResolverFacade, new SeleniumValidationConditionResolver());
        } else if (type.equals(CheckUncheckActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new CheckUncheckActionDefinitionPlayer(this.webDriver, this.elementResolverFacade);
        } else if (type.equals(SelectActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new SelectActionDefinitionPlayer(this.webDriver, this.elementResolverFacade);
        } else if (type.equals(PressActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new HitActionDefinitionPlayer(this.webDriver, this.elementResolverFacade);
        } else if (type.equals(ClearActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new ClearActionDefinitionPlayer(this.webDriver, this.elementResolverFacade);
        } else {
            throw new IllegalStateException(format("No player found for type %s", type));
        }
    }
}
