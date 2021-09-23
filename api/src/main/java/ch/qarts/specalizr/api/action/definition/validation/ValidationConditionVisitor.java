package ch.qarts.specalizr.api.action.definition.validation;

public interface ValidationConditionVisitor<T> {

    Validator<T> visit(ContainsTextValidationCondition containsTextValidationCondition);

    Validator<T> visit(ExistsValidationCondition existsValidationCondition);

    Validator<T> visit(DoesNotExistValidationCondition doesNotExistValidationCondition);
}
