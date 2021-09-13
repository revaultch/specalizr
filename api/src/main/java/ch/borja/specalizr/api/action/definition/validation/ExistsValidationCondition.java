package ch.borja.specalizr.api.action.definition.validation;

import ch.borja.specalizr.api.action.definition.ElementValidationCondition;

public class ExistsValidationCondition implements ElementValidationCondition {

    private ExistsValidationCondition() {
    }

    public static ExistsValidationCondition exists() {
        return new ExistsValidationCondition();
    }

    @Override
    public <T> Validator<T> accept(final ValidationConditionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
