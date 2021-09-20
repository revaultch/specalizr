package ch.qarts.specalizr.intg.selenium.action.impl.player;

import ch.qarts.specalizr.api.action.definition.ValidationActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl.ElementResolverFacade;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

import static ch.qarts.specalizr.intg.selenium.action.impl.SeleniumUtils.safelyLocate;

@AllArgsConstructor
public class ValidateActionDefinitionPlayer implements ActionDefinitionPlayer<ValidationActionDefinition<?>> {

    private WebDriver webDriver;

    private ElementResolverFacade elementResolverFacade;

    private SeleniumValidationConditionResolver seleniumValidationConditionVisitor;

    @Override
    public void play(final ValidationActionDefinition<?> validationActionDefinition) {
        final var seleniumValidator = validationActionDefinition.getElementValidationCondition().accept(this.seleniumValidationConditionVisitor);
        seleniumValidator.validate(safelyLocate(this.webDriver, this.elementResolverFacade.resolve(validationActionDefinition.getValidatableElement())));
    }
}
