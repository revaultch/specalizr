package ch.qarts.specalizr.api.action.definition.validation;

import ch.qarts.specalizr.api.action.definition.ElementValidationCondition;

public class DoesNotExistValidationCondition implements ElementValidationCondition {

    private DoesNotExistValidationCondition() {
    }

    public static DoesNotExistValidationCondition doesNotExist() {
        return new DoesNotExistValidationCondition();
    }

    @Override
    public <T> Validator<T> accept(final ValidationConditionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
