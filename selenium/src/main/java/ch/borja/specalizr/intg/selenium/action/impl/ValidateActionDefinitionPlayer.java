package ch.borja.specalizr.intg.selenium.action.impl;

import ch.borja.specalizr.api.action.definition.ValidationActionDefinition;
import ch.borja.specalizr.api.player.ActionDefinitionPlayer;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

import static ch.borja.specalizr.intg.selenium.action.impl.SeleniumUtils.safelyLocate;

@AllArgsConstructor
public class ValidateActionDefinitionPlayer implements ActionDefinitionPlayer<ValidationActionDefinition<?>> {

    private WebDriver webDriver;

    private SeleniumXPathQueryComponentResolver seleniumXPathQueryComponentVisitor;

    private SeleniumValidationConditionResolver seleniumValidationConditionVisitor;

    @Override
    public void play(final ValidationActionDefinition<?> validationActionDefinition) {
        final var seleniumValidator = validationActionDefinition.getElementValidationCondition().accept(this.seleniumValidationConditionVisitor);
        seleniumValidator.validate(safelyLocate(this.webDriver, validationActionDefinition.getValidatableElement().accept(this.seleniumXPathQueryComponentVisitor)));
    }
}
