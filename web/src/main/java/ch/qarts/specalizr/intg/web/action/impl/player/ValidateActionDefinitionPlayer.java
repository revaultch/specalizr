package ch.qarts.specalizr.intg.web.action.impl.player;

import ch.qarts.specalizr.api.action.definition.ValidationActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.intg.web.action.impl.NoElementFound;
import ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl.ElementResolverFacade;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static ch.qarts.specalizr.intg.web.action.impl.SeleniumUtils.safelyLocate;

@AllArgsConstructor
public class ValidateActionDefinitionPlayer implements ActionDefinitionPlayer<ValidationActionDefinition<?>> {

    private WebDriver webDriver;

    private ElementResolverFacade elementResolverFacade;

    private SeleniumValidationConditionResolver seleniumValidationConditionVisitor;

    @Override
    public void play(final ValidationActionDefinition<?> validationActionDefinition) {
        final var seleniumValidator = validationActionDefinition.getElementValidationCondition().accept(this.seleniumValidationConditionVisitor);
        WebElement webElement = null;
        try {
            webElement = safelyLocate(this.webDriver, this.elementResolverFacade.resolve(validationActionDefinition.getValidatableElement()));
        } catch (NoElementFound nef) {
            // ignore here
        }
        seleniumValidator.validate(webElement);
    }
}
