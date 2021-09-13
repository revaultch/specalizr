package ch.borja.specalizr.api.action.definition.validation;

public interface ValidationConditionVisitor<T> {

    Validator<T> visit(ContainsTextValidationCondition containsTextValidationCondition);

    Validator<T> visit(ExistsValidationCondition existsValidationCondition);
    
}
