package ch.borja.specalizr.intg.selenium.action.registry;

import ch.borja.specalizr.api.action.ActionDefinition;
import ch.borja.specalizr.api.action.definition.*;
import ch.borja.specalizr.api.player.ActionDefinitionPlayer;
import ch.borja.specalizr.api.player.ActionDefinitionPlayerRegistry;
import ch.borja.specalizr.intg.selenium.action.impl.*;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

@AllArgsConstructor
public class SeleniumPlayer implements ActionDefinitionPlayerRegistry {

    private final WebDriver webDriver;

    private final SeleniumXPathQueryComponentResolver seleniumXPathQueryComponentVisitor;

    private final SeleniumValidationConditionResolver seleniumValidationConditionVisitor;

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ActionDefinition> ActionDefinitionPlayer<T> forType(final Class<T> type) {
        if (type.equals(ClickActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new ClickActionDefinitionPlayer(this.webDriver, this.seleniumXPathQueryComponentVisitor);
        } else if (type.equals(WriteActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new WriteActionDefinitionPlayer(this.webDriver, this.seleniumXPathQueryComponentVisitor);
        } else if (type.equals(ValidationActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new ValidateActionDefinitionPlayer(this.webDriver, this.seleniumXPathQueryComponentVisitor, this.seleniumValidationConditionVisitor);
        } else if (type.equals(CheckUncheckActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new CheckUncheckActionDefinitionPlayer(this.webDriver, this.seleniumXPathQueryComponentVisitor);
        } else if (type.equals(SelectActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new SelectActionDefinitionPlayer(this.webDriver, this.seleniumXPathQueryComponentVisitor);
        } else if (type.equals(PressActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new HitActionDefinitionPlayer(this.webDriver, this.seleniumXPathQueryComponentVisitor);
        } else if (type.equals(ClearActionDefinition.class)) {
            return (ActionDefinitionPlayer<T>) new ClearActionDefinitionPlayer(this.webDriver, this.seleniumXPathQueryComponentVisitor);
        } else {
            throw new IllegalStateException(format("No player found for type %s", type));
        }
    }
}
