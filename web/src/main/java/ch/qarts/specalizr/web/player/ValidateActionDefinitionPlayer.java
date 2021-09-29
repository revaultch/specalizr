package ch.qarts.specalizr.web.player;

import ch.qarts.specalizr.api.action.definition.ValidationActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.web.utils.NoElementFound;
import ch.qarts.specalizr.web.ElementResolverFacade;
import lombok.AllArgsConstructor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static ch.qarts.specalizr.web.utils.SeleniumUtils.safelyLocate;

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
        } catch (NoElementFound | TimeoutException e) {
            // ignore
        }
        seleniumValidator.validate(webElement);
    }
}
