package ch.qarts.specalizr.intg.selenium.action.impl.player;

import ch.qarts.specalizr.api.action.definition.validation.ContainsTextValidationCondition;
import ch.qarts.specalizr.api.action.definition.validation.ExistsValidationCondition;
import ch.qarts.specalizr.api.action.definition.validation.ValidationConditionVisitor;
import ch.qarts.specalizr.api.action.definition.validation.Validator;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class SeleniumValidationConditionResolver implements ValidationConditionVisitor<WebElement> {

    @Override
    public Validator<WebElement> visit(final ContainsTextValidationCondition containsTextValidationCondition) {
        return objectToBeValidated -> {
            assert Objects.requireNonNull(objectToBeValidated.getText()).contains(containsTextValidationCondition.getText())
                    || Objects.requireNonNull(objectToBeValidated.getAttribute("value")).contains(containsTextValidationCondition.getText());
        };
    }

    @Override
    public Validator<WebElement> visit(final ExistsValidationCondition existsValidationCondition) {
        return objectTo -> {
            assert objectTo != null;
        };
    }

}
