package ch.borja.specalizr.api.action.definition.validation;

import ch.borja.specalizr.api.action.definition.ElementValidationCondition;
import lombok.Getter;

public class ContainsTextValidationCondition implements ElementValidationCondition {

    @Getter
    private final String text;

    private ContainsTextValidationCondition(final String text) {
        this.text = text;
    }


    public static ContainsTextValidationCondition containsText(final String text) {
        return new ContainsTextValidationCondition(text);
    }

    @Override
    public <T> Validator<T> accept(final ValidationConditionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
